package com.example.retrofit_practice;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationListener;

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
