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

    @SerializedName("out")
    @Expose
    private String out;



    public AireChiller() {

    }

    public String getComp1_ok() {
        return comp1_ok;
    }

    public void setComp1_ok(String comp1_ok) {
        this.comp1_ok = comp1_ok;
    }

    public String getComp2_ok() {
        return comp2_ok;
    }

    public void setComp2_ok(String comp2_ok) {
        this.comp2_ok = comp2_ok;
    }

    public String getComp1_load() {
        return comp1_load;
    }

    public void setComp1_load(String comp1_load) {
        this.comp1_load = comp1_load;
    }

    public String getComp2_load() {
        return comp2_load;
    }

    public void setComp2_load(String comp2_load) {
        this.comp2_load = comp2_load;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }


    public AireChiller(int id, String name, int codigo, int idForm, int idRemoteDB, String comp1_ok, String comp2_ok, String comp1_load, String comp2_load, String out, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.comp1_ok = comp1_ok;
        this.comp2_ok = comp2_ok;
        this.comp1_load = comp1_load;
        this.comp2_load = comp2_load;
        this.out = out;
    }
}
