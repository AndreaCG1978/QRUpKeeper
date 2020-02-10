package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
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

    @Override
    public String getAlarma() {
        return alarma;
    }

    @Override
    public void setAlarma(String alarma) {
        this.alarma = alarma;
    }

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


    public GrupoElectrogeno() {

    }

    public GrupoElectrogeno(int id, String name, int codigo, int idForm, int idRemoteDB, String percent_comb, String temperatura, String nivelcomb75, String alarma, String auto, String precalent, String cargadorbat, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
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
        String temp = null;
        temp = "░ G. ELECTROGENO[" + name + "]:" + ConstantsAdmin.ENTER;


        if (percent_comb!= null && !percent_comb.equals("")) {
            temp = temp + "<|COMB%=" + percent_comb + "|> ";
        }else{
            temp = temp + "<|COMB%=-- |> ";
        }
        if (temperatura != null && !temperatura.equals("")) {
            temp = temp + "<|TEMP °C=" + temperatura + "|> ";
        }else{
            temp = temp + "<|TEMP °C=-- |> ";
        }

        if (auto.equals("1")) {
            temp = temp + "<AUTO SI> ";
        } else {
            temp = temp + "<AUTO NO> ";
        }
        if (alarma.equals("1")) {
            temp = temp + "<ALARMA SI> ";
        } else {
            temp = temp + "<ALARMA NO> ";
        }

        if (nivelcomb75.equals("1")) {
            temp = temp + "<NIVEL COMB 75% OK> ";
        } else {
            temp = temp + "<NIVEL COMB 75% FALLA> ";
        }

        temp = temp + ConstantsAdmin.ENTER;

        if (precalent.equals("1")) {
            temp = temp + "<PRE CALENT OK> ";
        } else {
            temp = temp + "<PRE CALENT FALLA> ";
        }

        if (cargadorbat.equals("1")) {
            temp = temp + "<CARGADOR BAT OK> ";
        } else {
            temp = temp + "<CARGADOR BAT FALLA>";
        }

        if (description != null && !description.equals("")) {
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + "(" + description + ")";
        }
        return temp;
    }
}
