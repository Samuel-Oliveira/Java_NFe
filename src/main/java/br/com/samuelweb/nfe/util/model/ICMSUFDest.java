package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

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
        if (this.getvBCUFDest() != null) {
            icmsufDest.setVBCUFDest(this.getvBCUFDest().toString());
        }
        if (this.getvBCFCPUFDest() != null) {
            icmsufDest.setVBCFCPUFDest(this.getvBCFCPUFDest().toString());
        }
        if (this.getpFCPUFDest() != null) {
            icmsufDest.setPFCPUFDest(this.getpFCPUFDest().toString());
        }
        if (this.getpICMSUFDest() != null) {
            icmsufDest.setPICMSUFDest(this.getpICMSUFDest().toString());
        }
        if (this.getpICMSInter() != null) {
            icmsufDest.setPICMSInter(this.getpICMSInter().toString());
        }
        if (this.getpICMSInterPart() != null) {
            icmsufDest.setPICMSInterPart(this.getpICMSInterPart().toString());
        }
        if (this.getvFCPUFDest() != null) {
            icmsufDest.setVFCPUFDest(this.getvFCPUFDest().toString());
        }
        if (this.getvICMSUFDest() != null) {
            icmsufDest.setVICMSUFDest(this.getvICMSUFDest().toString());
        }
        if (this.getvICMSUFRemet() != null) {
            icmsufDest.setVICMSUFRemet(this.getvICMSUFRemet().toString());
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
