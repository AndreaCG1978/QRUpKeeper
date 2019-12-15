package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class AbstractArtefactDto {

    @SerializedName("id")
    @Expose
    protected int id = -1;

    @SerializedName("name")
    @Expose
    protected String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractArtefactDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AbstractArtefactDto() {
    }
}
