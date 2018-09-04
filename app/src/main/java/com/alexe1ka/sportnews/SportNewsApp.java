package com.alexe1ka.sportnews;

import android.app.Application;

import com.alexe1ka.sportnews.network.SportNewsApi;
import com.alexe1ka.sportnews.repository.NewsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SportNewsApp extends Application {
    private static SportNewsApi sSportNewsApi;
    private static NewsRepository sNewsRepository;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        Gson gson = new GsonBuilder().setLenient().create();

        mRetrofit = new Retrofit.Builder().
                baseUrl("http://mikonatoruri.win/").
                addConverterFactory(GsonConverterFactory.create(gson)).//конвертер json
                build();
        sSportNewsApi = mRetrofit.create(SportNewsApi.class);
        sNewsRepository = NewsRepository.getInstance();
    }

    public static NewsRepository getNewsRepository() {
        return sNewsRepository;
    }



    public static SportNewsApi getSportNewsApi() {
        return sSportNewsApi;
    }
}
