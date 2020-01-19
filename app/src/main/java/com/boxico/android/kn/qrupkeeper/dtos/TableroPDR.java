package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @Override
    public String toString() {
        return "TableroPDR{" +
                "pottotRA='" + pottotRA + '\'' +
                ", pottotRB='" + pottotRB + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
