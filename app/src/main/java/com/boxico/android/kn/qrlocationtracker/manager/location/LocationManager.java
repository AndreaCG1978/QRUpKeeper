package com.boxico.android.kn.qrlocationtracker.manager.location;

import android.app.Activity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationManager {

    private FusedLocationProviderClient locationProvider;

    private FusedLocationProviderClient getLocationProvider() {
        return locationProvider;
    }

    private void setLocationProvider(FusedLocationProviderClient locationProvider) {
        this.locationProvider = locationProvider;
    }


    public void initialize(Activity act){
        locationProvider = LocationServices.getFusedLocationProviderClient(act);
    }
}
