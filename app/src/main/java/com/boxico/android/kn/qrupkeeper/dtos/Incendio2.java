package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Incendio2 extends AbstractArtefactDto {


    @SerializedName("funciona_ok")
    @Expose
    private String funciona_ok;


    @SerializedName("energiaA_ok")
    @Expose
    private String energiaA_ok;

    @SerializedName("fm200_ok")
    @Expose
    private String fm200_ok;


    public Incendio2() {

    }

    @Override
    public String getFunciona_ok() {
        return funciona_ok;
    }

    @Override
    public void setFunciona_ok(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }


    public String getEnergiaAOk() {
        return energiaA_ok;
    }

    public void setEnergiaAOk(String energiaA_ok) {
        this.energiaA_ok = energiaA_ok;
    }

    public String getFm200Ok() {
        return fm200_ok;
    }

    public void setFm200Ok(String fm200_ok) {
        this.fm200_ok = fm200_ok;
    }


    public Incendio2(int id, String name, int codigo, int idForm, int idRemoteDB, String funciona_ok, String energiaA_ok, String fm200_ok, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.funciona_ok = funciona_ok;
        this.energiaA_ok = energiaA_ok;
        this.fm200_ok = fm200_ok;
    }

    @NotNull
    @Override
    public String toString() {
        String temp;
        if(name != null && !name.equals("")){
            temp = "░ Incendio[" + name + "]:" + ConstantsAdmin.ENTER;
        }else{
            temp = "░ Incendio:" + ConstantsAdmin.ENTER;
        }

        if(funciona_ok.equals("1")){
            temp = temp + "<FUNCIONA: SI> ";
        }else{
            temp = temp + "<FUNCIONA: NO> ";
        }
        temp = temp + ConstantsAdmin.ENTER;
        if(energiaA_ok.equals("1")){
            temp = temp + "<ENERGIA A: SI> ";
        }else{
            temp = temp + "<ENERGIA A: NO> ";
        }
        temp = temp + ConstantsAdmin.ENTER;
        if(fm200_ok.equals("1")){
            temp = temp + "<FM200: SI> ";
        }else{
            temp = temp + "<FM200: NO> ";
        }
        if(description != null && !description.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
