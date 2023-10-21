package com.example.retrofit_practice;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    Retrofit api = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void sendRequest(final CallbackResponse cr) {
        Api api1 = api.create(Api.class);
        Call<String> call = api1.getApi(56.9718363, 23.96427, "616d6301b5f3df53c8ac834263baa03a");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("name", response.body());
                    cr.onResponse(response.body());
                } else {
                    Log.e("API Error", "Error Status Code: " + response.code());
                    try {
                        Log.e("API Error", "Error Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cr.onFailure(new RuntimeException("Unsuccessful response: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                cr.onFailure(t);
            }
        });
    }
}
