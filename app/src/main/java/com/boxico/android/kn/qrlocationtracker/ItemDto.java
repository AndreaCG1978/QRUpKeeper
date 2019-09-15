package com.boxico.android.kn.qrlocationtracker;

public class ItemDto {

    private long id = -1;
    private String name;
    private String description;
    private String identification;
    private double latitude;
    private double longitude;

    public ItemDto(long itemId, String name, String description, String identification, double latitude, double longitude) {
        super();
        this.setId(itemId);
        this.setName(name);
        this.setDescription(description);
        this.setIdentification(identification);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    public ItemDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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
}
