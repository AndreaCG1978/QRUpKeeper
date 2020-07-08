package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class TableroPDR extends AbstractArtefactDto {


    @SerializedName("pottotRA")
    @Expose
    private String pottotRA;

    @SerializedName("pottotRB")
    @Expose
    private String pottotRB;


    public TableroPDR() {

    }

    public String getPottotRA() {
        return pottotRA;
    }

    public void setPottotRA(String pottotRA) {
        this.pottotRA = pottotRA;
    }

    public String getPottotRB() {
        return pottotRB;
    }

    public void setPottotRB(String pottotRB) {
        this.pottotRB = pottotRB;
    }


    public TableroPDR(int id, String name, int codigo, int idForm, int idRemoteDB, String pottotRA, String pottotRB, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.pottotRA = pottotRA;
        this.pottotRB = pottotRB;
    }

    @NotNull
    @Override
    public String toString() {
        String temp;

        if(name != null && !name.equals("")){
            temp = "░ T. distribución Rack[" + name + "]:" + ConstantsAdmin.ENTER;
        }else{
            temp = "░ T. distribución Rack:" + ConstantsAdmin.ENTER;
        }

        if(pottotRA != null && !pottotRA.equals("")){
            temp = temp + "<|RA POT.TOT.:" + pottotRA + " < 200kW|> " ;
        }
        if(pottotRB != null && !pottotRB.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "<|RB POT.TOT.:" + pottotRB + " < 200kW|> " ;
        }
        if(description != null && !description.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
