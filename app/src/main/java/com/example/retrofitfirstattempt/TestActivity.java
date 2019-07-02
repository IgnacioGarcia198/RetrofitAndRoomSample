package com.example.retrofitfirstattempt;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofitfirstattempt.api.GetDataService;
import com.example.retrofitfirstattempt.api.RetrofitClientInstance;
import com.example.retrofitfirstattempt.model.RetroPhoto;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private static String LOG_TAG = TestActivity.class.getSimpleName();
    private GetDataService service = RetrofitClientInstance.getApi();
    private Executor executor = Executors.newSingleThreadExecutor();
    private static final int ALBUM_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getPhotosTest();
    }

    private void getPhotosTest() {
        executor.execute(() -> {
            service.getPhotosByAlbumIdFromApi(ALBUM_ID).enqueue(new Callback<List<RetroPhoto>>() {
                @Override
                public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                    if (response.isSuccessful()) {
                        Log.d(LOG_TAG,"Data refreshed from network !");
                        Log.d(LOG_TAG,"FIRST TITLE: " + response.body().get(0).getTitle());
                        // insert new data in the database

                    /*executor.execute(() -> {
                        for (RetroPhoto photo : response.body()) {
                            photo.setLastRefresh(new Date());
                            retroPhotoDao.save(photo);
                        }
                    });*/
                    } else {
                        Log.d(LOG_TAG,"Refresh data from network is empty");
                    }
                }

                @Override
                public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                    Log.d(LOG_TAG,"Error on refreshing data from network");
                }
            });

        });
    }
}
