package com.example.retrofitfirstattempt.data;


import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofitfirstattempt.api.GetDataService;
import com.example.retrofitfirstattempt.api.RetrofitClientInstance;
import com.example.retrofitfirstattempt.db.RetroPhotoDao;
import com.example.retrofitfirstattempt.db.RetroPhotoDatabase;
import com.example.retrofitfirstattempt.model.RetroPhoto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *     Use the Webservice (via Retrofit) the first time user launch the app
 *     Use the Webservice instead of database when last fetching from API of user’s data was more than 3 minutes ago.
 *     Otherwise, use the database (via Room).
 */
public class RetroPhotoRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 3;
    private static final String LOG_TAG = RetroPhotoRepository.class.getSimpleName();
    private final GetDataService webservice;
    private final RetroPhotoDao retroPhotoDao;
    private final Executor executor;
    private final Context context;
    private static RetroPhotoRepository instance;

    private RetroPhotoRepository(GetDataService webservice, RetroPhotoDao dao, Executor executor, Context context) {
        this.webservice = webservice;
        this.retroPhotoDao = dao;
        this.executor = executor;
        this.context = context;
    }

    public static RetroPhotoRepository getInstance(Context context) {
        if(instance == null) {
            RetroPhotoDao dao = RetroPhotoDatabase.getInstance(context).retroPhotoDao();
            instance = new RetroPhotoRepository(
                    RetrofitClientInstance.getApi(),dao, Executors.newSingleThreadExecutor(),context);
        }
        return instance;
    }

    public LiveData<List<RetroPhoto>> getPhotosByAlbumId(int albumId) {
        refreshPhotosByAlbumId(albumId); // we try to refresh from the api
        return retroPhotoDao.findPhotosByAlbumIdInRoom(albumId);
    }

    private void refreshPhotosByAlbumId(int albumId) {
        executor.execute(() -> {
            boolean photosAreFresh = (retroPhotoDao.hasFreshPhotoInAlbum(albumId,getMaxRefreshTime(new Date())) != null);

            if(!photosAreFresh) {
                webservice.getPhotosByAlbumIdFromApi(albumId).enqueue(new Callback<List<RetroPhoto>>() {
                    @Override
                    public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                            //executor.execute(() -> {
                                Log.d(LOG_TAG,"Data refreshed from network !");
                            //});

                            // insert new data in the database

                            executor.execute(() -> {
                                for (RetroPhoto photo:response.body()) {
                                    photo.setLastRefresh(new Date());
                                    retroPhotoDao.save(photo);
                                }
                            });
                        }
                        else {
                            Toast.makeText(context, "Refresh data from network is empty", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                        Toast.makeText(context, "Error on refreshing data from network", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }

    public LiveData<List<RetroPhoto>> findPhotosByNameInAlbum(String photoTitle, int albumId) {
        refreshPhotosByAlbumId(albumId); // we try to refresh from the api
        if(!photoTitle.isEmpty()) {
            return retroPhotoDao.findPhotosByNameAndAlbumInRoom("%" + photoTitle + "%", albumId);
        }
        else {
            return retroPhotoDao.findPhotosByAlbumIdInRoom(albumId);
        }
    }
}
