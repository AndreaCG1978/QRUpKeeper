package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AireAcond extends AbstractArtefactDto {


    @SerializedName("funciona_ok")
    @Expose
    private String funciona_ok;

    @SerializedName("temperatura")
    @Expose
    private String temperatura;


    public AireAcond() {

    }

    @Override
    public String getFunciona_ok() {
        return funciona_ok;
    }

    @Override
    public void setFunciona_ok(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public AireAcond(int id, String name, int codigo, int idForm, int idRemoteDB, String funciona_ok, String temperatura, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.funciona_ok = funciona_ok;
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "AireAcond{" +
                "funciona_ok='" + funciona_ok + '\'' +
                ", temperatura='" + temperatura + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
