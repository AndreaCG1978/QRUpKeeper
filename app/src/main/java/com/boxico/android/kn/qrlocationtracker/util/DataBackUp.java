package com.boxico.android.kn.qrlocationtracker.util;

public class DataBackUp {
    String url = null;
    double distance = 0;
    private double latitude;
    private double longitude;

    public DataBackUp(String url, double distance, double latitude, double longitude) {
        super();
        this.url = url;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DataBackUp() {

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
