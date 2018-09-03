package com.alexe1ka.sportnews;

import android.app.Application;

import com.alexe1ka.sportnews.network.SportNewsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SportNewsApp extends Application {
    private static SportNewsApi sSportNewsApi;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        Gson gson = new GsonBuilder().setLenient().create();

        mRetrofit = new Retrofit.Builder().
                baseUrl("https://api.github.com").
                addConverterFactory(GsonConverterFactory.create(gson)).//конвертер json
                build();
        sSportNewsApi = mRetrofit.create(SportNewsApi.class);
    }

    public static SportNewsApi getSportNewsApi() {
        return sSportNewsApi;
    }
}
