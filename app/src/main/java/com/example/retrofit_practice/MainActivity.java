package com.example.retrofit_practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.textV1);
        TextView textView2 = findViewById(R.id.textV2);
        TextView textView3 = findViewById(R.id.textV3);
        TextView weatherD = findViewById(R.id.wetaherD);
        Button btn = findViewById(R.id.btn);
        ImageView img = findViewById(R.id.imageView);
        Server server = new Server();

        ImageUpdate imageUpdate = new ImageUpdate();


        btn.setOnClickListener(v -> server.sendRequest(new CallbackResponse() {
            @Override
            public void onResponse(MethodResult mr) {
                Log.d("method3", mr.toString());

                String text;

                text = getString(R.string.temp) + " " + ((int) mr.getTemp()-273) + getString(R.string.celsius);
                textView1.setText(text);

                text = getString(R.string.pres) + " " + ((int) mr.getPres());
                textView2.setText(text);

                text = getString(R.string.hum) + " " + ((int) mr.getHum());
                textView3.setText(text);

                text = getString(R.string.weather) + " " + mr.getDescription();
                weatherD.setText(text);

                img.setImageResource(imageUpdate.setImage(mr));
            }

            @Override
            public void onFailure(Throwable T) {
                Log.d("no", "nuh-uh");
            }
        }));
    }
}
