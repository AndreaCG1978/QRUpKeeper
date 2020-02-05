package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadUPS extends AbstractArtefactDto {


    @SerializedName("percent_r")
    @Expose
    private String percent_r;

    @SerializedName("percent_s")
    @Expose
    private String percent_s;

    @SerializedName("percent_t")
    @Expose
    private String percent_t;

    @SerializedName("alarma")
    @Expose
    private String alarma;

    public LoadUPS() {

    }

    public String getPercent_r() {
        return percent_r;
    }

    public void setPercent_r(String percent_r) {
        this.percent_r = percent_r;
    }

    public String getPercent_s() {
        return percent_s;
    }

    public void setPercent_s(String percent_s) {
        this.percent_s = percent_s;
    }

    public String getPercent_t() {
        return percent_t;
    }

    public void setPercent_t(String percent_t) {
        this.percent_t = percent_t;
    }

    public String getAlarma() {
        return alarma;
    }

    public void setAlarma(String alarma) {
        this.alarma = alarma;
    }

    public LoadUPS(int id, String name, String percent_r, String percent_s, String percent_t, String alarma, int codigo, int idForm, int idRemoteDB, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.percent_r = percent_r;
        this.percent_s = percent_s;
        this.percent_t = percent_t;
        this.alarma = alarma;
    }

    public String getPat() {
        return percent_t;
    }
    public String getPar() {
        return percent_r;
    }
    public String getPas() {
        return percent_s;
    }

    @Override
    public String toString() {
        String temp = null;

        temp = "░ LOAD UPS: " + name;

        if(percent_r != null && !percent_r.equals("")){
            temp = temp + " | ";
            temp = temp + "PORCENTAJE R:" + percent_r;
        }
        if(percent_s != null && !percent_s.equals("")){
            temp = temp + " | ";
            temp = temp + "PORCENTAJE S:" + percent_s;
        }
        if(percent_t != null && !percent_t.equals("")){
            temp = temp + " | ";
            temp = temp + "PORCENTAJE T:" + percent_t;
        }

        if(alarma.equals("1")){
            temp = temp + " | ";
            temp = temp + "ALARMA SI";
        }else{
            temp = temp + " | ";
            temp = temp + "ALARMA NO";
        }
        if(description != null && !description.equals("")){
            temp = temp + " | " + description;
        }
        return temp;
    }
}
