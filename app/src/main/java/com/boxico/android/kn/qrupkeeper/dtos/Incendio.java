package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Incendio extends AbstractArtefactDto {


    @SerializedName("funciona_ok")
    @Expose
    private String funciona_ok;

    @SerializedName("presion")
    @Expose
    private String presion;

    @SerializedName("energiaA_ok")
    @Expose
    private String energiaA_ok;

    @SerializedName("energiaB_ok")
    @Expose
    private String energiaB_ok;


    public Incendio() {

    }

    public String getFunciona_ok() {
        return funciona_ok;
    }

    public void setFunciona_ok(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getEnergiaA_ok() {
        return energiaA_ok;
    }

    public void setEnergiaA_ok(String energiaA_ok) {
        this.energiaA_ok = energiaA_ok;
    }

    public String getEnergiaB_ok() {
        return energiaB_ok;
    }

    public void setEnergiaB_ok(String energiaB_ok) {
        this.energiaB_ok = energiaB_ok;
    }


    public Incendio(int id, String name, int codigo, int idForm, int idRemoteDB, String funciona_ok, String presion, String energiaA_ok, String energiaB_ok) {
        super(id, name, codigo, idForm, idRemoteDB);
        this.funciona_ok = funciona_ok;
        this.presion = presion;
        this.energiaA_ok = energiaA_ok;
        this.energiaB_ok = energiaB_ok;
    }
}
