package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inspector implements Serializable {
    @SerializedName("id")
    @Expose
    private long id = -1;

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("code")
    @Expose
    private String code;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
