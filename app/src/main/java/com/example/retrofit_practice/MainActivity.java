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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity implements LocListenerInterface {

    private MyLocListener myLocationListener;

    private double lat;
    private double lon;

    Server server = new Server();
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView weatherD;
    private ImageView img;
    ImageUpdate imageUpdate = new ImageUpdate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        textView1 = findViewById(R.id.textV1);
        textView2 = findViewById(R.id.textV2);
        textView3 = findViewById(R.id.textV3);
        weatherD = findViewById(R.id.wetaherD);
        img = findViewById(R.id.imageView);
        ImageButton refresh = findViewById(R.id.imageButton);

        img.setImageResource(0);

        refresh.setOnClickListener(v -> onRefreshData());

        onRefreshData();
    }

    private LocationManager locationManager;

    private void init(){
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        myLocationListener = new MyLocListener();
        myLocationListener.setLocListenerInterface(this);
        checkPermissions();
    }


    private void onRefreshData() {
        server.sendRequest(new CallbackResponse() {
            @Override
            public void onResponse(MethodResult mr) {
                Log.d("method3", mr.toString());

                String text;

                text = getString(R.string.temp) + " " + ((int) mr.getTemp() - 273) + getString(R.string.celsius);
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
        }, lat, lon);
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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, myLocationListener);
        }
    }

    @Override
    public void OnLocationChanged(Location loc) {
        lat = loc.getLatitude();
        lon = loc.getLongitude();
    }
}
