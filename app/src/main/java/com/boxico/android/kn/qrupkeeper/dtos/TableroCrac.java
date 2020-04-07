package com.boxico.android.kn.qrupkeeper.dtos;

import com.boxico.android.kn.qrupkeeper.util.ConstantsAdmin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class TableroCrac extends AbstractArtefactDto {

    @SerializedName("kwr")
    @Expose
    private String kwr;

    @SerializedName("kws")
    @Expose
    private String kws;

    @SerializedName("kwt")
    @Expose
    private String kwt;

    @SerializedName("par")
    @Expose
    private String par;

    @SerializedName("pas")
    @Expose
    private String pas;

    @SerializedName("pat")
    @Expose
    private String pat;

    public TableroCrac() {
        super();
    }


    public String getKwr() {
        return kwr;
    }

    public void setKwr(String kwr) {
        this.kwr = kwr;
    }

    public String getKws() {
        return kws;
    }

    public void setKws(String kws) {
        this.kws = kws;
    }

    public String getKwt() {
        return kwt;
    }

    public void setKwt(String kwt) {
        this.kwt = kwt;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public TableroCrac(int id, String name, String kwr, String kws, String kwt, String par, String pas, String pat, int codigo, int idForm, int idRemoteDB, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.kwr = kwr;
        this.kws = kws;
        this.kwt = kwt;
        this.par = par;
        this.pas = pas;
        this.pat = pat;
    }

    @NotNull
    @Override
    public String toString() {
        String temp;

        if(name != null && !name.equals("")){
            temp = "░ T. CRAH[" + name + "]:" + ConstantsAdmin.ENTER;
        }else{
            temp = "░ T. CRAH:" + ConstantsAdmin.ENTER;
        }

        if(kwr != null && !kwr.equals("")){
            temp = temp + "<|KW_R=" + kwr + "|>";
        }else{
            temp = temp + "<|KW_R=-- |>";
        }
        temp = temp + " ";
        if(kws != null && !kws.equals("")) {
            temp = temp + "<|KW_S=" + kws + "|>";
        }else{
            temp = temp + "<|KW_S=--|>";
        }
        temp = temp + " ";
        if(kwt != null && !kwt.equals("")){
            temp = temp + "<|KW_T=" + kwt + "|>";
        }else{
            temp = temp + "<|KW_T=--|>";
        }
        temp = temp +  ConstantsAdmin.ENTER;
        if(par != null && !par.equals("")){
            temp = temp + "<|A_R=" + par + "|>";
        }else{
            temp = temp + "<|A_R=--|>";
        }
        temp = temp + " ";
        if(pas != null && !pas.equals("")){
            temp = temp + "<|A_S=" + pas + "|>";
        }else{
            temp = temp + "<|A_S=--|>";
        }
        temp = temp + " ";
        if(pat != null && !pat.equals("")){
            temp = temp + "<|A_T=" + pat + "|>";
        }else{
            temp = temp + "<|A_T=--|>";
        }

        if(description != null && !description.equals("")){
            temp = temp + ConstantsAdmin.ENTER;
            temp = temp + " (" + description + ")";
        }
        return temp;
    }
}
