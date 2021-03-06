package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

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

    @NotNull
    @Override
    public String toString() {
        String temp;

        if(name != null && !name.equals("")){
            temp = "░ PRESURIZACION CAÑERIA[" + name + "]:" + ConstantsAdmin.ENTER;
        }else{
            temp = "░ PRESURIZACION CAÑERIA:" + ConstantsAdmin.ENTER;
        }

        if(alarma.equals("1")){
            temp = temp + "<ALARMA: SI> ";
        }else{
            temp = temp + "<ALARMA: NO> ";
        }
        if(encendido.equals("1")){
            temp = temp + "<ENCENDIDO: SI> ";
        }else{
            temp = temp + "<ENCENDIDO: NO> ";
        }
        if(description != null && !description.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
