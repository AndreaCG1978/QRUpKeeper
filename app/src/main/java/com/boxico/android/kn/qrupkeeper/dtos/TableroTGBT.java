package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableroTGBT  extends AbstractDto{


    @SerializedName("kwr")
    @Expose
    private float kwr;

    @SerializedName("kws")
    @Expose
    private float kws;

    @SerializedName("kwt")
    @Expose
    private float kwt;

    @SerializedName("ar")
    @Expose
    private float ar;

    @SerializedName("as")
    @Expose
    private float as;

    @SerializedName("at")
    @Expose
    private float at;

    public TableroTGBT() {
        super();
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public float getKwr() {
        return kwr;
    }

    public void setKwr(float kwr) {
        this.kwr = kwr;
    }

    public float getKws() {
        return kws;
    }

    public void setKws(float kws) {
        this.kws = kws;
    }

    public float getKwt() {
        return kwt;
    }

    public void setKwt(float kwt) {
        this.kwt = kwt;
    }

    public float getAr() {
        return ar;
    }

    public void setAr(float ar) {
        this.ar = ar;
    }

    public float getAs() {
        return as;
    }

    public void setAs(float as) {
        this.as = as;
    }

    public float getAt() {
        return at;
    }

    public void setAt(float at) {
        this.at = at;
    }

    public TableroTGBT(long itemId, String name, String description, float kwr, float kws, float kwt, float ar, float as, float at) {

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
