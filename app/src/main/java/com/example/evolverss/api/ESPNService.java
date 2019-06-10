package com.example.evolverss.api;

import com.example.evolverss.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ESPNService {

    @GET("/rpm/news")
    Call<Feed> listRPM();

    @GET("/mlb/news")
    Call<Feed> listMLB();

    @GET("/nhl/news")
    Call<Feed> listNHL();

    @GET("/nba/news")
    Call<Feed> listNBA();

    @GET("/nfl/news")
    Call<Feed> listNFL();
}
