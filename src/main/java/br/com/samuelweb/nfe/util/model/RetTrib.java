package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class RetTrib {

    @NfeCampo(tipo = BigDecimal.class, id = "W24", tag = "vRetPIS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VRETPIS)
    private BigDecimal vRetPIS;

    @NfeCampo(tipo = BigDecimal.class, id = "W25", tag = "vRetCOFINS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VRETCOFINS)
    private BigDecimal vRetCOFINS;

    @NfeCampo(tipo = BigDecimal.class, id = "W26", tag = "vRetCSLL"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VRETCSLL)
    private BigDecimal vRetCSLL;

    @NfeCampo(tipo = BigDecimal.class, id = "W27", tag = "vBCIRRF"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VBCIRRF)
    private BigDecimal vbcirrf;

    @NfeCampo(tipo = BigDecimal.class, id = "W28", tag = "vIRRF"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VIRRF)
    private BigDecimal virrf;

    @NfeCampo(tipo = BigDecimal.class, id = "W29", tag = "vBCRetPrev"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VBCRETPREV)
    private BigDecimal vbcRetPrev;

    @NfeCampo(tipo = BigDecimal.class, id = "W30", tag = "vRetPrev"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VRETPREV)
    private BigDecimal vRetPrev;

    public TNFe.InfNFe.Total.RetTrib build() {
        TNFe.InfNFe.Total.RetTrib retTrib = new TNFe.InfNFe.Total.RetTrib();

        if ((this.getvRetPIS() != null && this.getvRetPIS().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getvRetCOFINS() != null && this.getvRetCOFINS().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getvRetCSLL() != null && this.getvRetCSLL().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getVbcirrf() != null && this.getVbcirrf().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getVirrf() != null && this.getVirrf().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getVbcRetPrev() != null && this.getVbcRetPrev().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getvRetPrev() != null && this.getvRetPrev().compareTo(BigDecimal.ZERO) > 0)) {

            if (this.getvRetPIS() != null) {
                retTrib.setVRetPIS(this.getvRetPIS().toString());
            }
            if (this.getvRetCOFINS() != null) {
                retTrib.setVRetCOFINS(this.getvRetCOFINS().toString());
            }
            if (this.getvRetCSLL() != null) {
                retTrib.setVRetCSLL(this.getvRetCSLL().toString());
            }
            if (this.getVbcirrf() != null) {
                retTrib.setVBCIRRF(this.getVbcirrf().toString());
            }
            if (this.getVirrf() != null) {
                retTrib.setVIRRF(this.getVirrf().toString());
            }
            if (this.getVbcRetPrev() != null) {
                retTrib.setVBCRetPrev(this.getVbcRetPrev().toString());
            }
            if (this.getvRetPrev() != null) {
                retTrib.setVRetPrev(this.getvRetPrev().toString());
            }
            return retTrib;
        }
        return null;
    }

    public BigDecimal getvRetPIS() {
        return vRetPIS;
    }

    public void setvRetPIS(BigDecimal vRetPIS) {
        this.vRetPIS = vRetPIS;
    }

    public BigDecimal getvRetCOFINS() {
        return vRetCOFINS;
    }

    public void setvRetCOFINS(BigDecimal vRetCOFINS) {
        this.vRetCOFINS = vRetCOFINS;
    }

    public BigDecimal getvRetCSLL() {
        return vRetCSLL;
    }

    public void setvRetCSLL(BigDecimal vRetCSLL) {
        this.vRetCSLL = vRetCSLL;
    }

    public BigDecimal getVbcirrf() {
        return vbcirrf;
    }

    public void setVbcirrf(BigDecimal vbcirrf) {
        this.vbcirrf = vbcirrf;
    }

    public BigDecimal getVirrf() {
        return virrf;
    }

    public void setVirrf(BigDecimal virrf) {
        this.virrf = virrf;
    }

    public BigDecimal getVbcRetPrev() {
        return vbcRetPrev;
    }

    public void setVbcRetPrev(BigDecimal vbcRetPrev) {
        this.vbcRetPrev = vbcRetPrev;
    }

    public BigDecimal getvRetPrev() {
        return vRetPrev;
    }

    public void setvRetPrev(BigDecimal vRetPrev) {
        this.vRetPrev = vRetPrev;
    }
}
