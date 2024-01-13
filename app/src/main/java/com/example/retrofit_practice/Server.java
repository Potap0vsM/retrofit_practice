package com.example.retrofit_practice;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server extends AppCompatActivity{
    private final String LOG_TAG = Server.class.getName();
    Retrofit api = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    public void sendRequest(final CallbackResponse cr) {

        MethodResult methodResult = new MethodResult();

        Api api1 = api.create(Api.class);
        //Call<WeatherResponse> call = api1.getApi(56.9718363, 23.96427, "433433db0e6798772821078c9a682131");
        Call<WeatherResponse> call = api1.getApi(-77.26012196227117, 2.072663407562435, "433433db0e6798772821078c9a682131");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    methodResult.setHum(response.body().getMain().getHumidity());
                    methodResult.setPres(response.body().getMain().getPressure());
                    methodResult.setTemp(response.body().getMain().getTemp());
                    methodResult.setMain(response.body().getWeather().get(0).getMain());
                    methodResult.setDescription(response.body().getWeather().get(0).getDescription());
                    methodResult.setIcon(response.body().getWeather().get(0).getIcon());

                    cr.onResponse(methodResult);
                    Log.d(LOG_TAG, methodResult.toString());
                } else {
                    Log.e(LOG_TAG, "Error Status Code: " + response.code());
                    try {
                        Log.e(LOG_TAG, "Error Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cr.onFailure(new RuntimeException("Unsuccessful response: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                cr.onFailure(t);
                Log.e(LOG_TAG, call.toString());
                Log.e(LOG_TAG, t.toString());
            }
        });
    }
}
