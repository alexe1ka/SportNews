package com.alexe1ka.sportnews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alexe1ka.sportnews.model.events.Events;

public class NewsListViewModel extends AndroidViewModel {
    public static final String TAG = NewsListViewModel.class.getSimpleName();

    private LiveData<Events> mEventsLiveData;

    public NewsListViewModel(@NonNull Application application) {
        super(application);
    }
}
