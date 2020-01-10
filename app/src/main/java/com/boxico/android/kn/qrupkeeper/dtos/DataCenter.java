package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataCenter implements Serializable{
    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("cantAAcondSalaBateria")
    @Expose
    private int cantAAcondSalaBateria;

    @SerializedName("cantAireChiller")
    @Expose
    private int cantAireChiller;

    @SerializedName("cantAireCrac")
    @Expose
    private int cantAireCrac;

    @SerializedName("cantEstractorAire")
    @Expose
    private int cantEstractorAire;

    @SerializedName("cantGrupoElectrogeno")
    @Expose
    private int cantGrupoElectrogeno;

    @SerializedName("cantIncendio")
    @Expose
    private int cantIncendio;

    @SerializedName("cantLoadUps")
    @Expose
    private int cantLoadUps;

    @SerializedName("cantPresostato")
    @Expose
    private int cantPresostato;

    @SerializedName("cantPresurizacionCanieria")
    @Expose
    private int cantPresurizacionCanieria;

    @SerializedName("cantPresurizacionEscalera")
    @Expose
    private int cantPresurizacionEscalera;

    @SerializedName("cantTableroAireChiller")
    @Expose
    private int cantTableroAireChiller;

    @SerializedName("cantTableroCrac")
    @Expose
    private int cantTableroCrac;

    @SerializedName("cantTableroInUps")
    @Expose
    private int cantTableroInUps;

    @SerializedName("cantTableroPDR")
    @Expose
    private int cantTableroPDR;

    @SerializedName("cantTableroTGBT")
    @Expose
    private int cantTableroTGBT;

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

    @Override
    public String toString() {
        return description;
    }

    public int getCantAAcondSalaBateria() {
        return cantAAcondSalaBateria;
    }

    public void setCantAAcondSalaBateria(int cantAAcondSalaBateria) {
        this.cantAAcondSalaBateria = cantAAcondSalaBateria;
    }

    public int getCantAireChiller() {
        return cantAireChiller;
    }

    public void setCantAireChiller(int cantAireChiller) {
        this.cantAireChiller = cantAireChiller;
    }

    public int getCantAireCrac() {
        return cantAireCrac;
    }

    public void setCantAireCrac(int cantAireCrac) {
        this.cantAireCrac = cantAireCrac;
    }

    public int getCantEstractorAire() {
        return cantEstractorAire;
    }

    public void setCantEstractorAire(int cantEstractorAire) {
        this.cantEstractorAire = cantEstractorAire;
    }

    public int getCantGrupoElectrogeno() {
        return cantGrupoElectrogeno;
    }

    public void setCantGrupoElectrogeno(int cantGrupoElectrogeno) {
        this.cantGrupoElectrogeno = cantGrupoElectrogeno;
    }

    public int getCantIncendio() {
        return cantIncendio;
    }

    public void setCantIncendio(int cantIncendio) {
        this.cantIncendio = cantIncendio;
    }

    public int getCantLoadUps() {
        return cantLoadUps;
    }

    public void setCantLoadUps(int cantLoadUps) {
        this.cantLoadUps = cantLoadUps;
    }

    public int getCantPresostato() {
        return cantPresostato;
    }

    public void setCantPresostato(int cantPresostato) {
        this.cantPresostato = cantPresostato;
    }

    public int getCantPresurizacionCanieria() {
        return cantPresurizacionCanieria;
    }

    public void setCantPresurizacionCanieria(int cantPresurizacionCanieria) {
        this.cantPresurizacionCanieria = cantPresurizacionCanieria;
    }

    public int getCantPresurizacionEscalera() {
        return cantPresurizacionEscalera;
    }

    public void setCantPresurizacionEscalera(int cantPresurizacionEscalera) {
        this.cantPresurizacionEscalera = cantPresurizacionEscalera;
    }

    public int getCantTableroAireChiller() {
        return cantTableroAireChiller;
    }

    public void setCantTableroAireChiller(int cantTableroAireChiller) {
        this.cantTableroAireChiller = cantTableroAireChiller;
    }

    public int getCantTableroCrac() {
        return cantTableroCrac;
    }

    public void setCantTableroCrac(int cantTableroCrac) {
        this.cantTableroCrac = cantTableroCrac;
    }

    public int getCantTableroInUps() {
        return cantTableroInUps;
    }

    public void setCantTableroInUps(int cantTableroInUps) {
        this.cantTableroInUps = cantTableroInUps;
    }

    public int getCantTableroPDR() {
        return cantTableroPDR;
    }

    public void setCantTableroPDR(int cantTableroPDR) {
        this.cantTableroPDR = cantTableroPDR;
    }

    public int getCantTableroTGBT() {
        return cantTableroTGBT;
    }

    public void setCantTableroTGBT(int cantTableroTGBT) {
        this.cantTableroTGBT = cantTableroTGBT;
    }

    public int getCantMaxArtefact(int code){
        int result = 0;
        switch (code){
            case 101:
                result = this.getCantTableroTGBT();
                break;
            case 102:
                result = this.getCantTableroAireChiller();
                break;
            case 103:
                result = this.getCantTableroCrac();
                break;
            case 104:
                result = this.getCantTableroInUps();
                break;
            case 105:
                result = this.getCantLoadUps();
                break;
            case 106:
                result = this.getCantGrupoElectrogeno();
                break;
        }
        return result;
    }
}
