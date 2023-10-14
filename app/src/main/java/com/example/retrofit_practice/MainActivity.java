package com.example.retrofit_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        Server server = new Server();

        btn.setOnClickListener(v -> Log.d("name", server.sendRequest(new CallbackResponse() {
            @Override
            public void onResponse(String s) {
                Log.d("name", s);
            }

            @Override
            public void onFailure(Throwable T) {

            }
        })));
    }


}