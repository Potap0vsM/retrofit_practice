package com.example.retrofit_practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        Server server = new Server();

        btn.setOnClickListener(v -> server.sendRequest(new CallbackResponse() {
            @Override
            public void onResponse(String s) {
                Log.d("name", s);
            }

            @Override
            public void onFailure(Throwable T) {
                Log.e("error", "Error in request", T);
            }
        }));
    }
}
