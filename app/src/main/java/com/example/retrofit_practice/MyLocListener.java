package com.example.retrofit_practice;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

public class MyLocListener implements LocationListener {
    private LocListenerInterface locListenerInterface;
    @Override
    public void onLocationChanged(@NonNull Location location) {
        locListenerInterface.OnLocationChanged(location);
    }

    public void setLocListenerInterface(LocListenerInterface locListenerInterface) {
        this.locListenerInterface = locListenerInterface;
    }
}
