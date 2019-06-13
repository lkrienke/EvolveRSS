package com.example.evolverss.api;

import com.example.evolverss.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ESPNService {

    @GET("{category}/news")
    Call<Feed> getNews(@Path("category") String category);
}
