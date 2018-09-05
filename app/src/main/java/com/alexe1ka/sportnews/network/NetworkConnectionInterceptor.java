package com.alexe1ka.sportnews.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class NetworkConnectionInterceptor implements Interceptor {

    private static final String TAG = NetworkConnectionInterceptor.class.getSimpleName();

    public abstract boolean isInternetAvailable();

    public abstract void onInternetUnavailable();


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!isInternetAvailable()) {
            onInternetUnavailable();
        }
        Response response = chain.proceed(request);
        Log.d(TAG, "intercept: response code: "+response.code());
        Log.d(TAG, "intercept: response header: "+response.headers());
        Log.d(TAG, "intercept: response body: "+response.body());

        return chain.proceed(request);
    }
}
