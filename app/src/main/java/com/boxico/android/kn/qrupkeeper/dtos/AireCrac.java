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


    public AireCrac(int id, String name, int codigo, int idForm, int idRemoteDB, String temperatura, String funciona_ok) {
        super(id, name, codigo, idForm, idRemoteDB);
        this.temperatura = temperatura;
        this.funciona_ok = funciona_ok;
    }
}