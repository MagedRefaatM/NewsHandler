package com.newshandler.model.network;

import com.newshandler.model.entities.Rss;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Call<Rss> getNews(@Url String url);
}