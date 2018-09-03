package com.alexe1ka.sportnews.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alexe1ka.sportnews.SportNewsApp;
import com.alexe1ka.sportnews.model.events.Events;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    public LiveData<Events> getEvents(String kindOfEvents) {
        final MutableLiveData<Events> eventsMutableLiveData = new MutableLiveData<>();
        SportNewsApp.getSportNewsApi().getEvents(kindOfEvents).enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {

            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });
        return eventsMutableLiveData;
    }


}
