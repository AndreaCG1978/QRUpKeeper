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

    @SerializedName("description")
    @Expose
    protected String description;

    @SerializedName("codigo")
    @Expose
    protected int code = -1;


    @SerializedName("idForm")
    @Expose
    protected int idForm = -1;

    @SerializedName("idRemoteDB")
    @Expose
    protected int idRemoteDB = -1;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdRemoteDB() {
        return idRemoteDB;
    }

    public void setIdRemoteDB(int idRemoteDB) {
        this.idRemoteDB = idRemoteDB;
    }

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

    public AbstractArtefactDto(int id, String name, int codigo, int idForm, int idRemoteDB, String desc) {
        this.id = id;
        this.name = name;
        this.code = codigo;
        this.idForm = idForm;
        this.idRemoteDB = idRemoteDB;
        this.description = desc;
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

    public void setFunciona_ok(String s){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public String getAuto() {
        return "";
    }

    public String getNivelcomb75() {
        return "";
    }

    public String getPrecalent() {
        return "";
    }

    public String getCargadorbat() {
        return "";
    }

    public String getTemperatura() {
        return "";
    }

    public String getPercent_comb() {
        return "";
    }

    public String getFunciona_ok() {return "";}

    public String getComp1Ok() {return "";}

    public String getComp2Ok() {return "";}

    public String getComp1Load() {return "";}

    public String getComp2Load() {return "";}

    public String getAtr_out() {return "";}

    public String getEnergiaAOk(){return "";}

    public String getEnergiaBOk(){return "";}

    public String getPresion(){return "";}

    public String getAireOk() {return "";}

    public String getAguaOk() {return "";}

    public String getAirePresion() {return "";}

    public String getAguaPresion() {return "";}

    public String getPotTotRA()  {return "";}

    public String getPotTotRB()  {return "";}

    public String getArranque()   {return "";}

    public String getCorreas()   {return "";}

    public String getEngrase()   {return "";}

    public String getFuncionamiento()   {return "";}

    public String getLimpieza()   {return "";}

    public String getTiempo()   {return "";}

    public String getEncendido() {return "";}
}
