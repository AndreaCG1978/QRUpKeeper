package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Presostato extends AbstractArtefactDto {


    @SerializedName("aire_ok")
    @Expose
    private String aire_ok;

    @SerializedName("aire_presion")
    @Expose
    private String aire_presion;

    @SerializedName("agua_ok")
    @Expose
    private String agua_ok;

    @SerializedName("agua_presion")
    @Expose
    private String agua_presion;


    public Presostato() {

    }

    public String getAire_ok() {
        return aire_ok;
    }

    public void setAire_ok(String aire_ok) {
        this.aire_ok = aire_ok;
    }

    public String getAire_presion() {
        return aire_presion;
    }

    public void setAire_presion(String aire_presion) {
        this.aire_presion = aire_presion;
    }

    public String getAgua_ok() {
        return agua_ok;
    }

    public void setAgua_ok(String agua_ok) {
        this.agua_ok = agua_ok;
    }

    public String getAgua_presion() {
        return agua_presion;
    }

    public void setAgua_presion(String agua_presion) {
        this.agua_presion = agua_presion;
    }

    public Presostato(int id, String name, int codigo, int idForm, int idRemoteDB, String aire_ok, String aire_presion, String agua_ok, String agua_presion) {
        super(id, name, codigo, idForm, idRemoteDB);
        this.aire_ok = aire_ok;
        this.aire_presion = aire_presion;
        this.agua_ok = agua_ok;
        this.agua_presion = agua_presion;
    }
}
