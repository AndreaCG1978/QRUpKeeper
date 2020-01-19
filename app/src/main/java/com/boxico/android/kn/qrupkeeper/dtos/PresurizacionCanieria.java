package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresurizacionCanieria extends AbstractArtefactDto {


    @SerializedName("alarma")
    @Expose
    private String alarma;

    @SerializedName("encendido")
    @Expose
    private String encendido;


    public PresurizacionCanieria() {

    }

    @Override
    public String getAlarma() {
        return alarma;
    }

    @Override
    public void setAlarma(String alarma) {
        this.alarma = alarma;
    }

    public String getEncendido() {
        return encendido;
    }

    public void setEncendido(String encendido) {
        this.encendido = encendido;
    }

    public PresurizacionCanieria(int id, String name, int codigo, int idForm, int idRemoteDB, String alarma, String encendido, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.alarma = alarma;
        this.encendido = encendido;
    }

    @Override
    public String toString() {
        return "PresurizacionCanieria{" +
                "alarma='" + alarma + '\'' +
                ", encendido='" + encendido + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
