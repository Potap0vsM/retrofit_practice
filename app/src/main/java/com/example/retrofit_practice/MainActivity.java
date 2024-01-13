package com.example.retrofit_practice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity implements LocListenerInterface {

    private TextView checkCord;
    private MyLocListener gpsTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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

    private LocationManager locationManager;

    private void init(){
        checkCord = findViewById(R.id.checkCord);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        gpsTracking = new MyLocListener();
        gpsTracking.setLocListenerInterface(this);
        checkPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == RESULT_OK){
            checkPermissions();
        }
    }

    private void checkPermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, (LocationListener) gpsTracking);
        }
    }

    @Override
    public void OnLocationChanged(Location loc) {
        checkCord.setText(String.valueOf(loc));
    }
}
