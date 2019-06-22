package com.example.retrofitfirstattempt.api;

import com.example.retrofitfirstattempt.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotosFromApi();

    @GET("/photos/{photoid}")
    Call<RetroPhoto> getPhotoByIdFromApi(@Path("photoid") int photoId);

    @GET("/photos")
    Call<List<RetroPhoto>> getPhotosByAlbumIdFromApi(@Query("albumId") int albumId);
}
