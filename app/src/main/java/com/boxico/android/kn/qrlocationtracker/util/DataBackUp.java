package com.boxico.android.kn.qrlocationtracker.util;

public class DataBackUp {
    String url = null;
    double distance = 0;
    private double latitude;
    private double longitude;
    private double latitudeOrigin;
    private double longitudeOrigin;

    public DataBackUp(String url, double distance, double latitude, double longitude,double latitudeOrigin, double longitudeOrigin ) {
        super();
        this.url = url;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latitudeOrigin = latitudeOrigin;
        this.longitudeOrigin = longitudeOrigin;
    }

    public DataBackUp() {

    }

    public double getLatitudeOrigin() {
        return latitudeOrigin;
    }

    public void setLatitudeOrigin(double latitudeOrigin) {
        this.latitudeOrigin = latitudeOrigin;
    }

    public double getLongitudeOrigin() {
        return longitudeOrigin;
    }

    public void setLongitudeOrigin(double longitudeOrigin) {
        this.longitudeOrigin = longitudeOrigin;
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
