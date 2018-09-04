package com.alexe1ka.sportnews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.alexe1ka.sportnews.model.events.Events;
import com.alexe1ka.sportnews.repository.NewsRepository;

public class NewsListViewModel extends AndroidViewModel {
    public static final String TAG = NewsListViewModel.class.getSimpleName();

    private MutableLiveData<Events> mEventsLiveData;
    private NewsRepository mNewsRepository = new NewsRepository();

    public NewsListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Events> getEventsLiveData() {
        return mEventsLiveData;
    }

    public void init(String kindOfSport) {

        if (mEventsLiveData != null) {
            return;
        }
//        mEventsLiveData = mNewsRepository.getFakeEvents(kindOfSport);
        mEventsLiveData = mNewsRepository.getEvents(kindOfSport);
    }


}
