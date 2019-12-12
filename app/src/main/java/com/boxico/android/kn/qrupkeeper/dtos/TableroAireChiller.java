package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableroAireChiller extends AbstractArtefactDto {


    @SerializedName("kwr")
    @Expose
    private String kwr;

    @SerializedName("kws")
    @Expose
    private String kws;

    @SerializedName("kwt")
    @Expose
    private String kwt;

    @SerializedName("par")
    @Expose
    private String par;

    @SerializedName("pas")
    @Expose
    private String pas;

    @SerializedName("pat")
    @Expose
    private String pat;

    public TableroAireChiller() {
        super();
    }


    public String getKwr() {
        return kwr;
    }

    public void setKwr(String kwr) {
        this.kwr = kwr;
    }

    public String getKws() {
        return kws;
    }

    public void setKws(String kws) {
        this.kws = kws;
    }

    public String getKwt() {
        return kwt;
    }

    public void setKwt(String kwt) {
        this.kwt = kwt;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }
}
