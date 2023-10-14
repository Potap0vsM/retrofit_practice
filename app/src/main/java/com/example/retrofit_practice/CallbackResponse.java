package com.example.retrofit_practice;

public interface CallbackResponse {

    void onResponse(String s);

    void onFailure(Throwable T);
}
