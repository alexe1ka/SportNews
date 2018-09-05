package com.alexe1ka.sportnews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alexe1ka.sportnews.SportNewsApp;
import com.alexe1ka.sportnews.model.events.Events;
import com.alexe1ka.sportnews.repository.NewsRepository;

public class NewsListViewModel extends AndroidViewModel {
    public static final String TAG = NewsListViewModel.class.getSimpleName();


    private MutableLiveData<Events> mEventsLiveData;
    private NewsRepository mNewsRepository;


    public NewsListViewModel(@NonNull Application application) {
        super(application);
        mNewsRepository = SportNewsApp.getNewsRepository();
    }

    public MutableLiveData<Events> getEventsLiveData() {
        Log.d(TAG, "getEventsLiveData: ");
        return mEventsLiveData;
    }

    public MutableLiveData<Events> loadOtherEvents(String sportName) {
        Log.d(TAG, "loadOtherEvents: " + sportName);
        return mEventsLiveData = mNewsRepository.getEvents(sportName);
    }

//    public MutableLiveData<Events> loadOtherEvents(String sportName) {
//
//        return mEventsLiveData.postValue(mNewsRepository.getEvents(sportName).getValue());
//    }


    public void init(String kindOfSport) {
        if (mEventsLiveData != null) {
            return;
        }
        mEventsLiveData = mNewsRepository.getEvents(kindOfSport);
    }
}
