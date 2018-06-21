package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import javax.xml.bind.annotation.XmlElement;
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

    public TNFe.InfNFe.Total.RetTrib build(){
        TNFe.InfNFe.Total.RetTrib retTrib = new TNFe.InfNFe.Total.RetTrib();

        if ((this.vRetPIS.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vRetCOFINS.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vRetCSLL.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vbcirrf.compareTo(BigDecimal.ZERO) > 0) ||
                (this.virrf.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vbcRetPrev.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vRetPrev.compareTo(BigDecimal.ZERO) > 0) ){

            if (this.vRetPIS != null){
                retTrib.setVRetPIS(this.vRetPIS.toString());
            }
            if (this.vRetCOFINS != null){
                retTrib.setVRetCOFINS(this.vRetCOFINS.toString());
            }
            if (this.vRetCSLL!= null){
                retTrib.setVRetCSLL(this.vRetCSLL.toString());
            }
            if (this.vbcirrf!= null){
                retTrib.setVBCIRRF(this.vbcirrf.toString());
            }
            if (this.virrf!= null){
                retTrib.setVIRRF(this.virrf.toString());
            }
            if (this.vbcRetPrev!= null){
                retTrib.setVBCRetPrev(this.vbcRetPrev.toString());
            }
            if (this.vRetPrev!= null){
                retTrib.setVRetPrev(this.vRetPrev.toString());
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
