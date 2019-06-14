package com.example.evolverss.api;

import com.example.evolverss.model.Feed;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ESPNService {

    @GET("{category}/news")
    Observable<Feed> getNews(@Path("category") String category);
}
