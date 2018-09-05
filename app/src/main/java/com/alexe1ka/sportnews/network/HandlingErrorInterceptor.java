package com.alexe1ka.sportnews.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class HandlingErrorInterceptor implements Interceptor {

    public abstract void onServerError(String error);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(chain.request());
        if (response.body() != null) {
            if (response.body().string().equals("missed category")) {
                onServerError("missed category");
            }
        }

        switch (response.code()) {
            case 400://bad request
                onServerError("bad request");
                break;
            case 403://forbidden
                onServerError("forbidden");
                break;
            case 404://not found
                onServerError("not found");
                break;
            case 405://method not allowed
                onServerError("method not allowed");
                break;
            case 500://Internal Server Error
                onServerError("Internal Server Error");
                break;
            case 501://Not Implemented
                onServerError("Not Implemented");
                break;
            case 503://Service Unavailable
                onServerError("Service Unavailable");
                break;
            case 520://Unknown Error
                onServerError("Unknown Error");
                break;
            default:
        }
        return chain.proceed(request);
    }
}
