package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarAAMM;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJ;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCPF;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCodigoUf;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class RefNfp {

    @NfeCampo(tipo = Integer.class
            , id ="BA11", tag = "cUF"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_CUF, validadores = {ValidarCodigoUf.class})
    private String cuf;

    @NfeCampo(tipo = String.class
            , id = "BA12", tag = "AAMM", validadores = {ValidarAAMM.class}
            , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_AAMM)
    private String aamm;

    @NfeCampo(tipo = String.class
            , id = "BA13", tag = "CNPJ", validadores = {ValidarCNPJ.class}
            , tamanhoMinimo = 14, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpj;

    @NfeCampo(tipo = String.class
            , id = "BA14", tag = "CPF", validadores = {ValidarCPF.class}
            , tamanhoMinimo = 11, tamanhoMaximo = 11, ocorrencias = 0
            , descricao = DfeConsts.DSC_CPF)
    private String cpf;

    @NfeCampo(tipo = String.class
            , id = "BA15", tag = "IE"
            , tamanhoMinimo = 2, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_IE)
    private String ie;

    @NfeCampo(tipo = Integer.class
            , id ="B20f", tag = "mod"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_MOD)
    private String mod;

    @NfeCampo(tipo = Integer.class
            , id ="B20g", tag = "ser"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = DfeConsts.DSC_SERIE)
    private String serie;

    @NfeCampo(tipo = Integer.class
            , id ="B20h", tag = "nNF"
            , tamanhoMinimo = 1, tamanhoMaximo = 9, ocorrencias = 1
            , descricao = DfeConsts.DSC_NNF)
    private String nnf;


    public TNFe.InfNFe.Ide.NFref.RefNFP build(){
        TNFe.InfNFe.Ide.NFref.RefNFP refNFP = new TNFe.InfNFe.Ide.NFref.RefNFP();
        refNFP.setCUF(this.getCuf());
        refNFP.setAAMM(this.getAamm());
        if (this.getCnpj() != null) {
            refNFP.setCNPJ(this.getCnpj());
        } else {
            refNFP.setCPF(this.getCpf());
        }
        refNFP.setIE(this.getIe());
        refNFP.setMod(this.getMod());
        refNFP.setSerie(this.getSerie());
        refNFP.setNNF(this.getNnf());
        return refNFP;
    }

    public String getCuf() {
        return cuf;
    }

    public void setCuf(String cuf) {
        this.cuf = cuf;
    }

    public String getAamm() {
        return aamm;
    }

    public void setAamm(String aamm) {
        this.aamm = aamm;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNnf() {
        return nnf;
    }

    public void setNnf(String nnf) {
        this.nnf = nnf;
    }
}
