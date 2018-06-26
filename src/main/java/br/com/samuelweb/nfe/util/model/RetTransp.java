package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class RetTransp {

    @NfeCampo(tipo = BigDecimal.class, id = "X12", tag = "vServ"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VSERV)
    private BigDecimal vServ;

    @NfeCampo(tipo = BigDecimal.class, id = "X13", tag = "vBCRet"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VBCRET)
    private BigDecimal vbcRet;

    @NfeCampo(tipo = BigDecimal.class, id = "X14", tag = "pICMSRet"
            , tamanhoMinimo = 1, tamanhoMaximo = 5, ocorrencias = 1
            , decimais = 4, precisao = 7
            , descricao = NfeConsts.DSC_PICMSRET)
    private BigDecimal picmsRet;
    
    @NfeCampo(tipo = BigDecimal.class, id = "X15", tag = "vICMSRet"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VICMSRET)
    private BigDecimal vicmsRet;
    
    @NfeCampo(id = "X16", tag = "CFOP"
            , tamanhoMinimo = 4, tamanhoMaximo = 4
            , ocorrencias = 1, descricao = NfeConsts.DSC_CFOP)
    private String cfop;
    
    @NfeCampo(id = "X17", tag = "cMunFG"
            , tamanhoMinimo = 7, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = NfeConsts.DSC_CMUNFG)
    private String cMunFG;

    public TNFe.InfNFe.Transp.RetTransp build(){
        TNFe.InfNFe.Transp.RetTransp repTransp = new TNFe.InfNFe.Transp.RetTransp();

        if (this.vServ != null){
            repTransp.setVServ(this.vServ.toString());
        }

        if (this.vbcRet != null){
            repTransp.setVBCRet(this.vbcRet.toString());
        }

        if (this.picmsRet != null){
            repTransp.setPICMSRet(this.picmsRet.toString());
        }

        if (this.vicmsRet != null) {
            repTransp.setVICMSRet(this.vicmsRet.toString());
        }

        repTransp.setCFOP(this.cfop);
        repTransp.setCMunFG(this.cMunFG);
        return repTransp;
    }

    public BigDecimal getvServ() {
        return vServ;
    }

    public void setvServ(BigDecimal vServ) {
        this.vServ = vServ;
    }

    public BigDecimal getVbcRet() {
        return vbcRet;
    }

    public void setVbcRet(BigDecimal vbcRet) {
        this.vbcRet = vbcRet;
    }

    public BigDecimal getPicmsRet() {
        return picmsRet;
    }

    public void setPicmsRet(BigDecimal picmsRet) {
        this.picmsRet = picmsRet;
    }

    public BigDecimal getVicmsRet() {
        return vicmsRet;
    }

    public void setVicmsRet(BigDecimal vicmsRet) {
        this.vicmsRet = vicmsRet;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getcMunFG() {
        return cMunFG;
    }

    public void setcMunFG(String cMunFG) {
        this.cMunFG = cMunFG;
    }
}
