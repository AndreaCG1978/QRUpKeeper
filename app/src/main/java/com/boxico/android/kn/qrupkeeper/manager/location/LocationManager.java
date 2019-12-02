package com.boxico.android.kn.qrupkeeper.manager.location;

import android.app.Activity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationManager {

    private FusedLocationProviderClient locationProvider;

    public static String NETWORK_PROVIDER;

    public FusedLocationProviderClient getLocationProvider() {
        return locationProvider;
    }

    public void setLocationProvider(FusedLocationProviderClient locationProvider) {
        this.locationProvider = locationProvider;
    }


    public void initialize(Activity act){
        locationProvider = LocationServices.getFusedLocationProviderClient(act);
        NETWORK_PROVIDER = android.location.LocationManager.NETWORK_PROVIDER;
    }
}
