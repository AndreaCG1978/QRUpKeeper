package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

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

    public String getFunciona_ok() {
        return funciona_ok;
    }

    public void setFunciona_ok(String funciona_ok) {
        this.funciona_ok = funciona_ok;
    }


    public AireCrac(int id, String name, int codigo, int idForm, int idRemoteDB, String temperatura, String funciona_ok, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.temperatura = temperatura;
        this.funciona_ok = funciona_ok;
    }

    @NotNull
    @Override
    public String toString() {
        String temp;

        if(name != null && !name.equals("")){
            temp = "░ AIRE CRAH[" + name + "]:" + ConstantsAdmin.ENTER;
        }else{
            temp = "░ AIRE CRAH:" + ConstantsAdmin.ENTER;
        }

        if (funciona_ok.equals("1")) {
            temp = temp + "<FUNCIONA: SI> ";
        } else {
            temp = temp + "<FUNCIONA: NO> ";
        }

        if (temperatura != null && !temperatura.equals("")) {
            temp = temp + "<|TEMP °C=" + temperatura + "|> ";
        }else{
            temp = temp + "<|TEMP °C=-- |> ";
        }

        if (description != null && !description.equals("")) {
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
