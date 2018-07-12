package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.enumeration.CSTPis;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCSTPis;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class PIS {

    @NfeCampo(tipo = CSTPis.class
            , id = "Q06", tag = "CST"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_CST)
    private CSTPis CST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "Q07", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "Q08", tag = "pPIS", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_PPIS)
    private BigDecimal pPIS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "Q09", tag = "vPIS", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VPIS)
    private BigDecimal vPIS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "Q10", tag = "qBCProd", decimais = 4, precisao = 16
            , tamanhoMinimo = 1, tamanhoMaximo = 16, ocorrencias = 0
            , descricao = DfeConsts.DSC_QBCPROD)
    private BigDecimal qBCProd;

    @NfeCampo(tipo = BigDecimal.class
            , id = "Q11", tag = "vAliqProd", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VALIQPROD)
    private BigDecimal vAliqProd;

    public boolean isEmpty() {
        return (CST == null)
            && (vBC == null)
            && (pPIS == null)
            && (vPIS == null)
            && (qBCProd == null)
            && (vAliqProd == null);
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }

    public TNFe.InfNFe.Det.Imposto.PIS build() {
        if (this.getCST() == null) {
            return null;
        }
        TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();
        this.getCST().getMontaImposto().build(pis, this);
        return pis;
    }

    public CSTPis getCST() {
        return CST;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public BigDecimal getpPIS() {
        return pPIS;
    }

    public BigDecimal getvPIS() {
        return vPIS;
    }

    public BigDecimal getqBCProd() {
        return qBCProd;
    }

    public BigDecimal getvAliqProd() {
        return vAliqProd;
    }

    public PIS setCST(CSTPis CST) {
        this.CST = CST;
        return this;
    }

    public PIS setvBC(BigDecimal vBC) {
        this.vBC = vBC;
        return this;
    }

    public PIS setpPIS(BigDecimal pPIS) {
        this.pPIS = pPIS;
        return this;
    }

    public PIS setvPIS(BigDecimal vPIS) {
        this.vPIS = vPIS;
        return this;
    }

    public PIS setqBCProd(BigDecimal qBCProd) {
        this.qBCProd = qBCProd;
        return this;
    }

    public PIS setvAliqProd(BigDecimal vAliqProd) {
        this.vAliqProd = vAliqProd;
        return this;
    }

}
