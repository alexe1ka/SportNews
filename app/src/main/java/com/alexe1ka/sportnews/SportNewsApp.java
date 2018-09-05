package com.alexe1ka.sportnews;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alexe1ka.sportnews.network.HandlingErrorInterceptor;
import com.alexe1ka.sportnews.network.InternetConnectionListener;
import com.alexe1ka.sportnews.network.InternetErrorListener;
import com.alexe1ka.sportnews.network.NetworkConnectionInterceptor;
import com.alexe1ka.sportnews.network.SportNewsApi;
import com.alexe1ka.sportnews.repository.NewsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SportNewsApp extends Application {
    private static final String TAG = SportNewsApp.class.getSimpleName();
    private static SportNewsApi sSportNewsApi;
    private static NewsRepository sNewsRepository;
    private Retrofit mRetrofit;
    private InternetConnectionListener mInternetConnectionListener;
    private InternetErrorListener mInternetErrorListener;
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        SportNewsApp.sContext = getApplicationContext();
        Gson gson = new GsonBuilder().setLenient().create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://mikonatoruri.win/")
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))//конвертер json
                .build();
        sSportNewsApi = mRetrofit.create(SportNewsApi.class);
        sNewsRepository = NewsRepository.getInstance();
    }

    private OkHttpClient provideOkHttpClient() {
        //для обработки ошибок сервера
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new NetworkConnectionInterceptor() {
                    @Override
                    public boolean isInternetAvailable() {
                        return SportNewsApp.this.isInternetAvailable();
                    }

                    @Override
                    public void onInternetUnavailable() {
                        if (mInternetConnectionListener != null) {
                            mInternetConnectionListener.onInternetUnavailable();
                        }
                    }
                })
                .addInterceptor(new HandlingErrorInterceptor() {
                    @Override
                    public void onServerError(String error) {
                        mInternetErrorListener.onError(error);
                    }
                })
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        return okhttpClient;
    }

    public void setInternetConnectionListener(InternetConnectionListener listener) {
        mInternetConnectionListener = listener;
    }

    public void removeInternetConnectionListener() {
        mInternetConnectionListener = null;
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Context getAppContext() {
        return SportNewsApp.sContext;
    }

    public static NewsRepository getNewsRepository() {
        return sNewsRepository;
    }

    public static SportNewsApi getSportNewsApi() {
        return sSportNewsApi;
    }

    public void setInternetErrorListener(InternetErrorListener internetErrorListener) {
        mInternetErrorListener = internetErrorListener;
    }
}
