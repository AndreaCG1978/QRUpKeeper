package com.boxico.android.kn.qrlocationtracker.util;

public class DataBackUp {
    String url = "https://www.google.com";
    double distance = 0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private double latitudeOrigin = 0.0;
    private double longitudeOrigin = 0.0;
    private String radio = "0.00005";

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public DataBackUp(String url, double distance, double latitude, double longitude, double latitudeOrigin, double longitudeOrigin, String radio) {
        super();
        this.url = url;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latitudeOrigin = latitudeOrigin;
        this.longitudeOrigin = longitudeOrigin;
        this.radio = radio;
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
