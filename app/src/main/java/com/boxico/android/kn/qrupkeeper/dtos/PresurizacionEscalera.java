package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresurizacionEscalera extends AbstractArtefactDto {


    @SerializedName("arranque")
    @Expose
    private String arranque;

    @SerializedName("tiemp")
    @Expose
    private String tiemp;

    @SerializedName("funcionamiento")
    @Expose
    private String funcionamiento;

    @SerializedName("engrase")
    @Expose
    private String engrase;

    @SerializedName("limpieza")
    @Expose
    private String limpieza;

    @SerializedName("correas")
    @Expose
    private String correas;


    public PresurizacionEscalera() {

    }

    public String getArranque() {
        return arranque;
    }

    public void setArranque(String arranque) {
        this.arranque = arranque;
    }

    public String getTiemp() {
        return tiemp;
    }

    public void setTiemp(String tiemp) {
        this.tiemp = tiemp;
    }

    public String getFuncionamiento() {
        return funcionamiento;
    }

    public void setFuncionamiento(String funcionamiento) {
        this.funcionamiento = funcionamiento;
    }

    public String getEngrase() {
        return engrase;
    }

    public void setEngrase(String engrase) {
        this.engrase = engrase;
    }

    public String getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(String limpieza) {
        this.limpieza = limpieza;
    }

    public String getCorreas() {
        return correas;
    }

    public void setCorreas(String correas) {
        this.correas = correas;
    }

    public PresurizacionEscalera(int id, String name, int codigo, int idForm, int idRemoteDB, String arranque, String tiemp, String funcionamiento, String engrase, String limpieza, String correas, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.arranque = arranque;
        this.tiemp = tiemp;
        this.funcionamiento = funcionamiento;
        this.engrase = engrase;
        this.limpieza = limpieza;
        this.correas = correas;
    }

    @Override
    public String toString() {
        String temp = null;

        temp = "░ ESTRACTOR AIRE: " + name;
        temp = temp + " | ";
        if (arranque.equals("1")) {
            temp = temp + "ARRANQUE OK";
        } else {
            temp = temp + "ARRANQUE FALLA";
        }

        temp = temp + " | ";
        if (correas.equals("1")) {
            temp = temp + "CORREAS OK";
        } else {
            temp = temp + "CORREAS FALLA";
        }

        temp = temp + " | ";
        if (engrase.equals("1")) {
            temp = temp + "ENGRASE OK";
        } else {
            temp = temp + "ENGRASE FALLA";
        }

        temp = temp + " | ";
        if (funcionamiento.equals("1")) {
            temp = temp + "FUNCIONAMIENTO OK";
        } else {
            temp = temp + "FUNCIONAMIENTO FALLA";
        }

        temp = temp + " | ";
        if (limpieza.equals("1")) {
            temp = temp + "LIMPIEZA OK";
        } else {
            temp = temp + "LIMPIEZA FALLA";
        }

        temp = temp + " | ";
        if (tiemp.equals("1")) {
            temp = temp + "TIEMPO OK";
        } else {
            temp = temp + "TIEMPO FALLA";
        }

        if (description != null && !description.equals("")) {
            temp = temp + " | " + description;
        }
        return temp;

    }
}
