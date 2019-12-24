package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;


public class DatacenterForm implements Serializable {

    @SerializedName("id")
    @Expose
    protected int id = -1;

    @SerializedName("description")
    @Expose
    protected String description = "";


    @SerializedName("nroForm")
    @Expose
    protected String nroForm;

    @SerializedName("inspectorId")
    @Expose
    protected int inspectorId = -1;

    @SerializedName("datacenterId")
    @Expose
    protected int datacenterId = -1;

    @SerializedName("fecha")
    @Expose
    protected String fecha;

    protected String datacenterName;


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

    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }

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
