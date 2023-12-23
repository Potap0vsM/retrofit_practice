package com.example.retrofit_practice;

public interface CallbackResponse {
    void onResponse(MethodResult mr);

    void onFailure(Throwable T);
}
