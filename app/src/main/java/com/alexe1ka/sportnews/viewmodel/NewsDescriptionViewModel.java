package com.alexe1ka.sportnews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.alexe1ka.sportnews.SportNewsApp;
import com.alexe1ka.sportnews.model.articles.ArticleDescription;
import com.alexe1ka.sportnews.repository.NewsRepository;

public class NewsDescriptionViewModel extends AndroidViewModel {
    private static final String TAG = NewsDescriptionViewModel.class.getSimpleName();
    private MutableLiveData<ArticleDescription> mArticleDescriptionMutableLiveData;
    private NewsRepository mNewsRepository;

    public NewsDescriptionViewModel(@NonNull Application application) {
        super(application);
        mNewsRepository = SportNewsApp.getNewsRepository();
    }

    public MutableLiveData<ArticleDescription> getArticleDescriptionMutableLiveData() {
        return mArticleDescriptionMutableLiveData;
    }

    public void init(String url) {
        if (mArticleDescriptionMutableLiveData != null) {
            return;
        }
        mArticleDescriptionMutableLiveData = mNewsRepository.getArticle(url);
    }


}
