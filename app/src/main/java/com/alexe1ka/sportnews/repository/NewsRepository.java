package com.alexe1ka.sportnews.repository;

import android.arch.lifecycle.MutableLiveData;

import com.alexe1ka.sportnews.SportNewsApp;
import com.alexe1ka.sportnews.model.events.Event;
import com.alexe1ka.sportnews.model.events.Events;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    public MutableLiveData<Events> getEvents(String kindOfEvents) {
        MutableLiveData<Events> eventsMutableLiveData = new MutableLiveData<>();
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

    public MutableLiveData<Events> getFakeEvents() {
        MutableLiveData<Events> eventsMutableLiveData = new MutableLiveData<>();
        Events events = new Events();
        List<Event> eventList = new ArrayList<>();
        Event event = new Event("Title", "Coeff", "Time", "Place", "Preview", "Article");
        Event event1 = new Event("Title1", "Coeff1", "Time1", "Place1", "Preview1", "Article1");
        Event event2 = new Event("Title2", "Coeff2", "Time2", "Place2", "Preview2", "Article2");
        eventList.add(event);
        eventList.add(event1);
        eventList.add(event2);
        events.setEvents(eventList);
        eventsMutableLiveData.setValue(events);
        return eventsMutableLiveData;

    }


}
