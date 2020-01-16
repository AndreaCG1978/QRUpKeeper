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

    public String getFuncionaOk() {
        return funciona_ok;
    }

    public void setFuncionaOk(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }


    public AireCrac(int id, String name, int codigo, int idForm, int idRemoteDB, String temperatura, String funciona_ok, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.temperatura = temperatura;
        this.funciona_ok = funciona_ok;
    }

    @Override
    public String toString() {
        return "AireCrac{" +
                "temperatura='" + temperatura + '\'' +
                ", funciona_ok='" + funciona_ok + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
