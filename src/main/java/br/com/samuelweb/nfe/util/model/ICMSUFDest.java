package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class ICMSUFDest {

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA03", tag = "vBCUFDest", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VBCUFDEST)
    private BigDecimal vBCUFDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA04", tag = "vBCFCPUFDest", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCFCPUFDEST)
    private BigDecimal vBCFCPUFDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA05", tag = "pFCPUFDest", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PFCPUFDEST)
    private BigDecimal pFCPUFDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA07", tag = "pICMSUFDest", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = NfeConsts.DSC_PICMSUFDEST)
    private BigDecimal pICMSUFDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA09", tag = "pICMSInter", decimais = 2, precisao = 4
            , tamanhoMinimo = 2, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_PICMSINTER)
    private BigDecimal pICMSInter;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA11", tag = "pICMSInterPart", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = NfeConsts.DSC_PICMSINTERPART)
    private BigDecimal pICMSInterPart;
    
    @NfeCampo(tipo = BigDecimal.class
            , id = "NA13", tag = "vFCPUFDest", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VFCPUFDEST)
    private BigDecimal vFCPUFDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA15", tag = "vICMSUFDest", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VICMSUFDEST)
    private BigDecimal vICMSUFDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "NA17", tag = "vICMSUFRemet", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VICMSUFREMET)
    private BigDecimal vICMSUFRemet;

    public TNFe.InfNFe.Det.Imposto.ICMSUFDest build() {
        TNFe.InfNFe.Det.Imposto.ICMSUFDest icmsufDest = new TNFe.InfNFe.Det.Imposto.ICMSUFDest();
        if (this.vBCUFDest != null) {
            icmsufDest.setVBCUFDest(this.vBCUFDest.toString());
        }
        if (this.vBCFCPUFDest != null) {
            icmsufDest.setVBCFCPUFDest(this.vBCFCPUFDest.toString());
        }
        if (this.pFCPUFDest != null) {
            icmsufDest.setPFCPUFDest(this.pFCPUFDest.toString());
        }
        if (this.pICMSUFDest != null) {
            icmsufDest.setPICMSUFDest(this.pICMSUFDest.toString());
        }
        if (this.pICMSInter != null) {
            icmsufDest.setPICMSInter(this.pICMSInter.toString());
        }
        if (this.pICMSInterPart != null) {
            icmsufDest.setPICMSInterPart(this.pICMSInterPart.toString());
        }
        if (this.vFCPUFDest != null) {
            icmsufDest.setVFCPUFDest(this.vFCPUFDest.toString());
        }
        if (this.vICMSUFDest != null) {
            icmsufDest.setVICMSUFDest(this.vICMSUFDest.toString());
        }
        if (this.vICMSUFRemet != null) {
            icmsufDest.setVICMSUFRemet(this.vICMSUFRemet.toString());
        }
        return icmsufDest;
    }

    public BigDecimal getvBCUFDest() {
        return vBCUFDest;
    }

    public void setvBCUFDest(BigDecimal vBCUFDest) {
        this.vBCUFDest = vBCUFDest;
    }

    public BigDecimal getvBCFCPUFDest() {
        return vBCFCPUFDest;
    }

    public void setvBCFCPUFDest(BigDecimal vBCFCPUFDest) {
        this.vBCFCPUFDest = vBCFCPUFDest;
    }

    public BigDecimal getpFCPUFDest() {
        return pFCPUFDest;
    }

    public void setpFCPUFDest(BigDecimal pFCPUFDest) {
        this.pFCPUFDest = pFCPUFDest;
    }

    public BigDecimal getpICMSUFDest() {
        return pICMSUFDest;
    }

    public void setpICMSUFDest(BigDecimal pICMSUFDest) {
        this.pICMSUFDest = pICMSUFDest;
    }

    public BigDecimal getpICMSInter() {
        return pICMSInter;
    }

    public void setpICMSInter(BigDecimal pICMSInter) {
        this.pICMSInter = pICMSInter;
    }

    public BigDecimal getpICMSInterPart() {
        return pICMSInterPart;
    }

    public void setpICMSInterPart(BigDecimal pICMSInterPart) {
        this.pICMSInterPart = pICMSInterPart;
    }

    public BigDecimal getvFCPUFDest() {
        return vFCPUFDest;
    }

    public void setvFCPUFDest(BigDecimal vFCPUFDest) {
        this.vFCPUFDest = vFCPUFDest;
    }

    public BigDecimal getvICMSUFDest() {
        return vICMSUFDest;
    }

    public void setvICMSUFDest(BigDecimal vICMSUFDest) {
        this.vICMSUFDest = vICMSUFDest;
    }

    public BigDecimal getvICMSUFRemet() {
        return vICMSUFRemet;
    }

    public void setvICMSUFRemet(BigDecimal vICMSUFRemet) {
        this.vICMSUFRemet = vICMSUFRemet;
    }
}
