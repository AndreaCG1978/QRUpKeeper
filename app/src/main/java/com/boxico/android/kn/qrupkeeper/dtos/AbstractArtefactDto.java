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

    @SerializedName("codigo")
    @Expose
    protected int code = -1;



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

    public AbstractArtefactDto(int id, String name, int codigo) {
        this.id = id;
        this.name = name;
        this.code = codigo;
    }

    public AbstractArtefactDto() {
    }

    public  String getKwr(){
        return "";
    }

    public  String getKws(){
        return "";
    }

    public  String getKwt(){
        return "";
    }

    public  String getPar(){
        return "";
    }

    public  String getPas(){
        return "";
    }

    public  String getPat(){
        return "";
    }

    public  String getAlarma(){
        return "";
    }

    public String getPercent_r() {
        return "";
    }

    public String getPercent_s() {
        return "";
    }

    public String getPercent_t() {
        return "";
    }


    public void setPat(String toString) {
    }

    public void setKwr(String toString) {
    }

    public void setKws(String toString) {
    }

    public void setKwt(String toString) {
    }

    public void setPar(String toString) {
    }

    public void setPas(String toString) {
    }

    public void setPercent_r(String toString) {
    }

    public void setPercent_s(String toString) {
    }

    public void setPercent_t(String toString) {
    }

    public void setAlarma(String s) {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
