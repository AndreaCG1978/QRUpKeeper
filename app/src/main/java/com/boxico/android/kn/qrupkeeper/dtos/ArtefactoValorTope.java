package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtefactoValorTope {

    @SerializedName("codigo")
    @Expose
    private int codigo = -1;

    @SerializedName("datacenterId")
    @Expose
    private int datacenterId = -1;

    @SerializedName("indexCampo")
    @Expose
    private int indexCampo  = -1;

    @SerializedName("tope")
    @Expose
    private String tope  = null;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(int datacenterId) {
        this.datacenterId = datacenterId;
    }

    public int getIndexCampo() {
        return indexCampo;
    }

    public void setIndexCampo(int indexCampo) {
        this.indexCampo = indexCampo;
    }

    public String getTope() {
        return tope;
    }

    public void setTope(String tope) {
        this.tope = tope;
    }
}
