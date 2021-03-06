package com.alexe1ka.sportnews.network;

import com.alexe1ka.sportnews.model.articles.ArticleDescription;
import com.alexe1ka.sportnews.model.events.Events;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SportNewsApi {
    @GET("list.php?")
    Call<Events> getEvents(@Query("category") String category);

    @GET("post.php?")
    Call<ArticleDescription> getArticles(@Query("article") String article);
}
