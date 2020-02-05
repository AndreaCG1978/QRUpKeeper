package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AireChiller extends AbstractArtefactDto {


    @SerializedName("comp1_ok")
    @Expose
    private String comp1_ok;

    @SerializedName("comp2_ok")
    @Expose
    private String comp2_ok;

    @SerializedName("comp1_load")
    @Expose
    private String comp1_load;

    @SerializedName("comp2_load")
    @Expose
    private String comp2_load;

    @SerializedName("atr_out")
    @Expose
    private String atr_out;



    public AireChiller() {

    }

    public String getAtr_out() {
        return atr_out;
    }

    public void setAtr_out(String atr_out) {
        this.atr_out = atr_out;
    }

    public String getComp1Ok() {
        return comp1_ok;
    }

    public void setComp1Ok(String comp1_ok) {
        this.comp1_ok = comp1_ok;
    }

    public String getComp2Ok() {
        return comp2_ok;
    }

    public void setComp2Ok(String comp2_ok) {
        this.comp2_ok = comp2_ok;
    }

    public String getComp1Load() {
        return comp1_load;
    }

    public void setComp1Load(String comp1_load) {
        this.comp1_load = comp1_load;
    }

    public String getComp2Load() {
        return comp2_load;
    }

    public void setComp2Load(String comp2_load) {
        this.comp2_load = comp2_load;
    }




    public AireChiller(int id, String name, int codigo, int idForm, int idRemoteDB, String comp1_ok, String comp2_ok, String comp1_load, String comp2_load, String out, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.comp1_ok = comp1_ok;
        this.comp2_ok = comp2_ok;
        this.comp1_load = comp1_load;
        this.comp2_load = comp2_load;
        this.atr_out = out;
    }

    @Override
    public String toString() {
        String temp = null;

        temp = "░ AIRE CHILLER: " + name;
        temp = temp + " | ";
        if(comp1_ok.equals("1")){
            temp = temp + "COMP1 OK";
        }else{
            temp = temp + "COMP1 FALLA";
        }

        if(comp1_load != null && !comp1_load.equals("")){
            temp = temp + " | ";
            temp = temp + "COMP1_LOAD:" + comp1_load;
        }
        temp = temp + " | ";
        if(comp2_ok.equals("1")){
            temp = temp + "COMP2 OK";
        }else{
            temp = temp + "COMP2 FALLA";
        }

        if(comp2_load != null && !comp2_load.equals("")){
            temp = temp + " | ";
            temp = temp + "COMP2_LOAD:" + comp2_load;
        }
        if(description != null && !description.equals("")){
            temp = temp + " | " + description;
        }
        return temp;
    }
}
