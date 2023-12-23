package com.example.retrofit_practice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("weather")
    Call<WeatherResponse> getApi(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String key);
}
