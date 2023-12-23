package com.example.retrofit_practice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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


        btn.setOnClickListener(v -> server.sendRequest(new CallbackResponse() {
            @Override
            public void onResponse(MethodResult mr) {
                Log.d("method3", mr.toString());

                String text;

                text = getString(R.string.temp) + ((int) mr.getTemp()-273) + getString(R.string.celsius);
                textView1.setText(text);

                text = getString(R.string.pres) + ((int) mr.getPres());
                textView2.setText(text);

                text = getString(R.string.hum) + ((int) mr.getHum());
                textView3.setText(text);

                text = getString(R.string.weather) + mr.getDescription();
                weatherD.setText(text);

                switch (mr.getMain()){
                    case "Clear sky":
                        img.setImageResource(R.drawable.clear);
                        break;
                    case "Few clouds":
                        img.setImageResource(R.drawable.few);
                        break;
                    case "Scattered clouds":
                        img.setImageResource(R.drawable.scattered);
                        break;
                    case "Broken clouds":
                        img.setImageResource(R.drawable.broken);
                        break;
                    case "Shower rain":
                        img.setImageResource(R.drawable.shower);
                        break;
                    case "Rain":
                        img.setImageResource(R.drawable.rain);
                        break;
                    case "Thunderstorm":
                        img.setImageResource(R.drawable.thunder);
                        break;
                    case "Snow":
                        img.setImageResource(R.drawable.snow);
                        break;
                    case "Mist":
                        img.setImageResource(R.drawable.mist);
                        break;
                }
            }

            @Override
            public void onFailure(Throwable T) {
                Log.d("no", "nuh-uh");
            }
        }));
    }
}
