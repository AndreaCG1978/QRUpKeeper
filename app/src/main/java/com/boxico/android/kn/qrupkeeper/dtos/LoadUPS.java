package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadUPS {

    @SerializedName("id")
    @Expose
    private long id = -1;

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("percent_r")
    @Expose
    private double percent_r;

    @SerializedName("percent_s")
    @Expose
    private double percent_s;

    @SerializedName("percent_t")
    @Expose
    private double percent_t;

    @SerializedName("alarma")
    @Expose
    private boolean alarma;


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

    public double getPercent_r() {
        return percent_r;
    }

    public void setPercent_r(double percent_r) {
        this.percent_r = percent_r;
    }

    public double getPercent_s() {
        return percent_s;
    }

    public void setPercent_s(double percent_s) {
        this.percent_s = percent_s;
    }

    public double getPercent_t() {
        return percent_t;
    }

    public void setPercent_t(double percent_t) {
        this.percent_t = percent_t;
    }

    public boolean isAlarma() {
        return alarma;
    }

    public void setAlarma(boolean alarma) {
        this.alarma = alarma;
    }

    public LoadUPS(long id, String name, String description, double percent_r, double percent_s, double percent_t, boolean alarma) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.percent_r = percent_r;
        this.percent_s = percent_s;
        this.percent_t = percent_t;
        this.alarma = alarma;
    }
}
