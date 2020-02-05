package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AireCrac extends AbstractArtefactDto {


    @SerializedName("temperatura")
    @Expose
    private String temperatura;

    @SerializedName("funciona_ok")
    @Expose
    private String funciona_ok;

    public AireCrac() {

    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getFunciona_ok() {
        return funciona_ok;
    }

    public void setFunciona_ok(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }


    public AireCrac(int id, String name, int codigo, int idForm, int idRemoteDB, String temperatura, String funciona_ok, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.temperatura = temperatura;
        this.funciona_ok = funciona_ok;
    }

    @Override
    public String toString() {
        String temp = null;

        temp = "░ AIRE CRAC: " + name;
        temp = temp + " | ";
        if (funciona_ok.equals("1")) {
            temp = temp + "FUNCIONA OK";
        } else {
            temp = temp + "FALLA";
        }

        if (temperatura != null && !temperatura.equals("")) {
            temp = temp + " | ";
            temp = temp + "TEMPERATURA:" + temperatura;
        }

        if (description != null && !description.equals("")) {
            temp = temp + " | " + description;
        }
        return temp;
    }
}
