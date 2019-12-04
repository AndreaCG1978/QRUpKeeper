package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableroInUps {
    @SerializedName("id")
    @Expose
    private long id = -1;

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("kwr")
    @Expose
    private double kwr;

    @SerializedName("kws")
    @Expose
    private double kws;

    @SerializedName("kwt")
    @Expose
    private double kwt;

    @SerializedName("ar")
    @Expose
    private double ar;

    @SerializedName("as")
    @Expose
    private double as;

    @SerializedName("at")
    @Expose
    private double at;

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

    public double getKwr() {
        return kwr;
    }

    public void setKwr(double kwr) {
        this.kwr = kwr;
    }

    public double getKws() {
        return kws;
    }

    public void setKws(double kws) {
        this.kws = kws;
    }

    public double getKwt() {
        return kwt;
    }

    public void setKwt(double kwt) {
        this.kwt = kwt;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getAs() {
        return as;
    }

    public void setAs(double as) {
        this.as = as;
    }

    public double getAt() {
        return at;
    }

    public void setAt(double at) {
        this.at = at;
    }

    public TableroInUps(long itemId, String name, String description, double kwr, double kws, double kwt, double ar, double as, double at) {

        super();
        this.setId(itemId);
        this.setName(name);
        this.setDescription(description);
        this.setKwr(kwr);
        this.setKws(kws);
        this.setKwt(kwt);
        this.setAr(ar);
        this.setAs(as);
        this.setAt(at);

    }
}
