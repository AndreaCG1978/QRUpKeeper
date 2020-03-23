package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DatacenterForm implements Serializable {

    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("description")
    @Expose
    private String description = "";


    @SerializedName("nroForm")
    @Expose
    private String nroForm;

    @SerializedName("inspectorId")
    @Expose
    private int inspectorId = -1;

    @SerializedName("datacenterId")
    @Expose
    private int datacenterId = -1;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    private String datacenterName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNroForm() {
        return nroForm;
    }

    public void setNroForm(String nroForm) {
        this.nroForm = nroForm;
    }
/*
    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }
*/
    public int getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(int datacenterId) {
        this.datacenterId = datacenterId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDatacenterName() {
        return datacenterName;
    }

    public void setDatacenterName(String datacenterName) {
        this.datacenterName = datacenterName;
    }
}
