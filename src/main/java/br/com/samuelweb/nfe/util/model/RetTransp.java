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

        if (this.getvServ() != null){
            repTransp.setVServ(this.getvServ().toString());
        }

        if (this.getVbcRet()!= null){
            repTransp.setVBCRet(this.getVbcRet().toString());
        }

        if (this.getPicmsRet() != null){
            repTransp.setPICMSRet(this.getPicmsRet().toString());
        }

        if (this.getVicmsRet() != null) {
            repTransp.setVICMSRet(this.getVicmsRet().toString());
        }

        repTransp.setCFOP(this.getCfop());
        repTransp.setCMunFG(this.getcMunFG());
        return repTransp;
    }

    public BigDecimal getvServ() {
        return vServ;
    }

    public BigDecimal getVbcRet() {
        return vbcRet;
    }

    public BigDecimal getPicmsRet() {
        return picmsRet;
    }

    public BigDecimal getVicmsRet() {
        return vicmsRet;
    }

    public String getCfop() {
        return cfop;
    }

    public String getcMunFG() {
        return cMunFG;
    }

    public RetTransp setvServ(BigDecimal vServ) {
        this.vServ = vServ;
        return this;
    }

    public RetTransp setVbcRet(BigDecimal vbcRet) {
        this.vbcRet = vbcRet;
        return this;
    }

    public RetTransp setPicmsRet(BigDecimal picmsRet) {
        this.picmsRet = picmsRet;
        return this;
    }

    public RetTransp setVicmsRet(BigDecimal vicmsRet) {
        this.vicmsRet = vicmsRet;
        return this;
    }

    public RetTransp setCfop(String cfop) {
        this.cfop = cfop;
        return this;
    }

    public RetTransp setcMunFG(String cMunFG) {
        this.cMunFG = cMunFG;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}