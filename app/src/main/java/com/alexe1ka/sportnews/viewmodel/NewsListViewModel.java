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
    private static final String TAG = NewsListViewModel.class.getSimpleName();


    private MutableLiveData<Events> mEventsLiveData;
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    private NewsRepository mNewsRepository;


    public NewsListViewModel(@NonNull Application application) {
        super(application);
        mNewsRepository = SportNewsApp.getNewsRepository();
    }

    public MutableLiveData<Boolean> getShowProgress() {
        Log.d(TAG, "getShowProgress: ");
        return showProgress;
    }

    public MutableLiveData<Events> getEventsLiveData() {
        Log.d(TAG, "getEventsLiveData: ");
        return mEventsLiveData;
    }

    public MutableLiveData<Events> loadOtherEvents(String sportName) {
        Log.d(TAG, "loadOtherEvents: " + sportName);
        mEventsLiveData = mNewsRepository.getEvents(sportName);

        return mEventsLiveData;
    }


    public void init(String kindOfSport) {
        if (mEventsLiveData != null) {
            return;
        }
        mEventsLiveData = mNewsRepository.getEvents(kindOfSport);
    }
}
