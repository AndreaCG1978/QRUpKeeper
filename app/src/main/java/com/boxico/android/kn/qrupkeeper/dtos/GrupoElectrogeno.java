package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrupoElectrogeno extends AbstractArtefactDto {


    @SerializedName("percent_comb")
    @Expose
    private String percent_comb;

    @SerializedName("temperatura")
    @Expose
    private String temperatura;

    @SerializedName("nivelcomb75")
    @Expose
    private String nivelcomb75;

    @SerializedName("alarma")
    @Expose
    private String alarma;

    @SerializedName("auto")
    @Expose
    private String auto;

    @SerializedName("precalent")
    @Expose
    private String precalent;

    @SerializedName("cargadorbat")
    @Expose
    private String cargadorbat;


    public String getPercent_comb() {
        return percent_comb;
    }

    public void setPercent_comb(String percent_comb) {
        this.percent_comb = percent_comb;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getNivelcomb75() {
        return nivelcomb75;
    }

    public void setNivelcomb75(String nivelcomb75) {
        this.nivelcomb75 = nivelcomb75;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getPrecalent() {
        return precalent;
    }

    public void setPrecalent(String precalent) {
        this.precalent = precalent;
    }

    public String getCargadorbat() {
        return cargadorbat;
    }

    public void setCargadorbat(String cargadorbat) {
        this.cargadorbat = cargadorbat;
    }

    public String getAlarma() {
        return alarma;
    }

    public void setAlarma(String alarma) {
        this.alarma = alarma;
    }

    public GrupoElectrogeno() {

    }

    public GrupoElectrogeno(int id, String name, int codigo, int idForm, int idRemoteDB, String percent_comb, String temperatura, String nivelcomb75, String alarma, String auto, String precalent, String cargadorbat) {
        super(id, name, codigo, idForm, idRemoteDB);
        this.percent_comb = percent_comb;
        this.temperatura = temperatura;
        this.nivelcomb75 = nivelcomb75;
        this.alarma = alarma;
        this.auto = auto;
        this.precalent = precalent;
        this.cargadorbat = cargadorbat;
    }


    @Override
    public String toString() {
        return "GrupoElectrogeno{" +
                "percent_comb='" + percent_comb + '\'' +
                ", temperatura='" + temperatura + '\'' +
                ", nivelcomb75='" + nivelcomb75 + '\'' +
                ", alarma='" + alarma + '\'' +
                ", auto='" + auto + '\'' +
                ", precalent='" + precalent + '\'' +
                ", cargadorbat='" + cargadorbat + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
