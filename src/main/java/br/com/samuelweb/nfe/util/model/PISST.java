package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class PISST {

    @NfeCampo(tipo = BigDecimal.class
            , id = "R02", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vBc;

    @NfeCampo(tipo = BigDecimal.class
            , id = "R03", tag = "pPis", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = DfeConsts.DSC_PPIS)
    private BigDecimal pPis;

    @NfeCampo(tipo = BigDecimal.class
            , id = "R04", tag = "qBCProd", decimais = 4, precisao = 16
            , tamanhoMinimo = 1, tamanhoMaximo = 16, ocorrencias = 0
            , descricao = DfeConsts.DSC_QBCPROD)
    private BigDecimal qBCProd;

    @NfeCampo(tipo = BigDecimal.class
            , id = "R05", tag = "vAliqProd", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VALIQPROD)
    private BigDecimal vAliqProd;

    @NfeCampo(tipo = BigDecimal.class
            , id = "R06", tag = "vPIS", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VPIS)
    private BigDecimal vPIS;

    public boolean isEmpty() {
        return (vBc == null)
            && (pPis == null)
            && (qBCProd == null)
            && (vAliqProd == null)
            && (vPIS == null);
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }

    public TNFe.InfNFe.Det.Imposto.PISST build() {
        TNFe.InfNFe.Det.Imposto.PISST pisst = new TNFe.InfNFe.Det.Imposto.PISST();
        if (this.getvBc() != null && this.getpPis() != null
                && this.getvBc().add(this.getpPis()).compareTo(BigDecimal.ZERO) > 0) {
            if (this.getvBc() != null) {
                pisst.setVBC(this.getvBc().toString());
            }
            if (this.getpPis() != null) {
                pisst.setPPIS(this.getpPis().toString());
            }
            if (this.getvPIS() != null) {
                pisst.setVPIS(this.getvPIS().toString());
            }
        }
        if (this.getqBCProd() != null && this.getvAliqProd() != null
                && this.getqBCProd().add(this.getvAliqProd()).compareTo(BigDecimal.ZERO) > 0) {
            if (this.getqBCProd() != null) {
                pisst.setQBCProd(this.getqBCProd().toString());
            }
            if (this.getvAliqProd() != null) {
                pisst.setVAliqProd(this.getvAliqProd().toString());
            }
            if (this.getvPIS() != null) {
                pisst.setVPIS(this.getvPIS().toString());
            }
        }
        return pisst;
    }

    public BigDecimal getvBc() {
        return vBc;
    }

    public BigDecimal getpPis() {
        return pPis;
    }

    public BigDecimal getqBCProd() {
        return qBCProd;
    }

    public BigDecimal getvAliqProd() {
        return vAliqProd;
    }

    public BigDecimal getvPIS() {
        return vPIS;
    }

    public PISST setvBc(BigDecimal vBc) {
        this.vBc = vBc;
        return this;
    }

    public PISST setpPis(BigDecimal pPis) {
        this.pPis = pPis;
        return this;
    }

    public PISST setqBCProd(BigDecimal qBCProd) {
        this.qBCProd = qBCProd;
        return this;
    }

    public PISST setvAliqProd(BigDecimal vAliqProd) {
        this.vAliqProd = vAliqProd;
        return this;
    }

    public PISST setvPIS(BigDecimal vPIS) {
        this.vPIS = vPIS;
        return this;
    }

}