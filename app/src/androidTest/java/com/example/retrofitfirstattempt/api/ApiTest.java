package com.example.retrofitfirstattempt.api;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.retrofitfirstattempt.model.RetroPhoto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class ApiTest {

    private static String LOG_TAG = ApiTest.class.getSimpleName();
    private GetDataService service;// = RetrofitClientInstance.getApi();
    private Executor executor = Executors.newSingleThreadExecutor();
    Context context = InstrumentationRegistry.getTargetContext();
    private static final int ALBUM_ID = 1;


    @Test
    public void getPhotosByAlbumIdFromApiTest() {

    }
}
