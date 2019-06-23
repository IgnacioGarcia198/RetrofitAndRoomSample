package com.example.retrofitfirstattempt.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static GetDataService serviceApi;

    private static Retrofit getRetrofitInstance() {
        //Initializing HttpLoggingInterceptor to receive the HTTP event logs
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Building the HTTPClient with the logger
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GetDataService getApi() {
        if(serviceApi == null) {
            serviceApi = getRetrofitInstance().create(GetDataService.class);
        }
        return serviceApi;
    }
}
