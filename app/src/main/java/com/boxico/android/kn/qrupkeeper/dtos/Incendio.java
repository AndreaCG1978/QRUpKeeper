package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
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

    @Override
    public String getFunciona_ok() {
        return funciona_ok;
    }

    @Override
    public void setFunciona_ok(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getEnergiaAOk() {
        return energiaA_ok;
    }

    public void setEnergiaAOk(String energiaA_ok) {
        this.energiaA_ok = energiaA_ok;
    }

    public String getEnergiaBOk() {
        return energiaB_ok;
    }

    public void setEnergiaBOk(String energiaB_ok) {
        this.energiaB_ok = energiaB_ok;
    }


    public Incendio(int id, String name, int codigo, int idForm, int idRemoteDB, String funciona_ok, String presion, String energiaA_ok, String energiaB_ok, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.funciona_ok = funciona_ok;
        this.presion = presion;
        this.energiaA_ok = energiaA_ok;
        this.energiaB_ok = energiaB_ok;
    }

    @Override
    public String toString() {
        String temp = null;

        temp = "░ INCENDIO:" + ConstantsAdmin.ENTER;
        if(funciona_ok.equals("1")){
            temp = temp + "<FUNCIONA OK> ";
        }else{
            temp = temp + "<NO FUNCIONA> ";
        }

        temp = temp + ConstantsAdmin.ENTER;
        if(energiaA_ok.equals("1")){
            temp = temp + "<ENERGIA A OK> ";
        }else{
            temp = temp + "<ENERGIA A FALLA> ";
        }
        if(energiaB_ok.equals("1")){
            temp = temp + "<ENERGIA B OK> ";
        }else{
            temp = temp + "<ENERGIA B FALLA> ";
        }
        temp = temp + ConstantsAdmin.ENTER;
        if(presion != null && !presion.equals("")){
            temp = temp + "<|PRESION=" + presion + "|> ";
        }else{
            temp = temp + "<|PRESION=-- |> ";
        }
        if(description != null && !description.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
