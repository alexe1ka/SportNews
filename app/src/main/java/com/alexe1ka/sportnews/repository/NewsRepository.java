package com.alexe1ka.sportnews.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.alexe1ka.sportnews.SportNewsApp;
import com.alexe1ka.sportnews.model.events.Event;
import com.alexe1ka.sportnews.model.events.Events;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    public static final String TAG = NewsRepository.class.getSimpleName();
    private static NewsRepository INSTANCE = null;

    private NewsRepository() {
    }

    public MutableLiveData<Events> getEvents(String kindOfEvents) {
        MutableLiveData<Events> eventsMutableLiveData = new MutableLiveData<>();
        SportNewsApp.getSportNewsApi().getEvents(kindOfEvents).enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Log.d(TAG, "onResponse: " + response.headers());
                Log.d(TAG, "onResponse: " + response.body());


                if (response.isSuccessful()) {
                    eventsMutableLiveData.postValue(response.body());

                } else {
                    switch (response.code()) {

                        //TODO реализовать
                        case 404:

                            break;
                        case 500:

                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });
        return eventsMutableLiveData;
    }

    public MutableLiveData<Events> getFakeEvents(String kindOfSport) {
        MutableLiveData<Events> eventsMutableLiveData = new MutableLiveData<>();
        Events events = new Events();
        List<Event> eventList = new ArrayList<>();
        Event event;
        Event event1;
        Event event2;
        switch (kindOfSport) {
            case "football":
                event = new Event("Title_football", "Coeff_football", "Time_football", "Place_football", "Preview_football", "Article_football");
                event1 = new Event("Title1", "Coeff1", "Time1", "Place1", "Preview1", "Article1");
                event2 = new Event("Title2", "Coeff2", "Time2", "Place2", "Preview2", "Article2");
                break;
            case "hockey":
                event = new Event("Title_hockey", "Coeff_hockey", "Time_hockey", "Place_hockey", "Preview_hockey", "Article_hockey");
                event1 = new Event("Title1", "Coeff1", "Time1", "Place1", "Preview1", "Article1");
                event2 = new Event("Title2", "Coeff2", "Time2", "Place2", "Preview2", "Article2");
                break;
            default:
                throw new UnsupportedOperationException();
        }

        eventList.add(event);
        eventList.add(event1);
        eventList.add(event2);
        events.setEvents(eventList);
        eventsMutableLiveData.setValue(events);
        return eventsMutableLiveData;
    }

    public static NewsRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (NewsRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NewsRepository();
                }
            }
        }
        return INSTANCE;
    }
}
