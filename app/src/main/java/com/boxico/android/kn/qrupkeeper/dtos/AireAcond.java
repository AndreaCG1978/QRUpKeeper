package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

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

    @NotNull
    @Override
    public String toString() {
        String temp;
        if(name != null && !name.equals("")){
            temp = "░ AIRE ACONDICIONADO[" + name + "]:" + ConstantsAdmin.ENTER;
        }else{
            temp = "░ AIRE ACONDICIONADO:" + ConstantsAdmin.ENTER;
        }

        if(funciona_ok.equals("1")){
            temp = temp + "<FUNCIONA: SI>";
        }else{
            temp = temp + "<FUNCIONA: NO>";
        }
        if(temperatura != null && !temperatura.equals("")){
            temp = temp + "<|TEMPERATURA=" + temperatura + "|> ";
        }else{
            temp = temp + "<|TEMPERATURA=-- |> ";
        }
        if(description != null && !description.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
