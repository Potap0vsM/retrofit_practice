package com.example.retrofit_practice;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("data/2.5/weather?")
    Call<String> getApi(@Query("lat") double lat, @Query("lon") double lon, @Query("key") String key);
}
