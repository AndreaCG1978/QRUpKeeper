package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class AbstractDto {

    @SerializedName("id")
    @Expose
    protected long id = -1;

    @SerializedName("name")
    @Expose
    protected String name;


    @SerializedName("description")
    @Expose
    protected String description;


    @SerializedName("nroForm")
    @Expose
    protected String nroForm;

    @SerializedName("inspectorId")
    @Expose
    protected int inspectorId = -1;

    @SerializedName("datacenterId")
    @Expose
    protected int datacenterId = -1;

    @SerializedName("datetime")
    @Expose
    protected Timestamp datetime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}
