package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

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

    public TNFe.InfNFe.Det.Imposto.PISST build() {
        TNFe.InfNFe.Det.Imposto.PISST pisst = new TNFe.InfNFe.Det.Imposto.PISST();
        if (this.vBc != null && this.pPis != null
                && this.vBc.add(this.pPis).compareTo(BigDecimal.ZERO) > 0) {
            if (this.vBc != null) {
                pisst.setVBC(this.vBc.toString());
            }
            if (this.pPis != null) {
                pisst.setPPIS(this.pPis.toString());
            }
            if (this.vPIS != null) {
                pisst.setVPIS(this.vPIS.toString());
            }
        }
        if (this.qBCProd != null && this.vAliqProd != null
                && this.qBCProd.add(this.vAliqProd).compareTo(BigDecimal.ZERO) > 0) {
            if (this.qBCProd != null) {
                pisst.setQBCProd(this.qBCProd.toString());
            }
            if (this.vAliqProd != null) {
                pisst.setVAliqProd(this.vAliqProd.toString());
            }
            if (this.vPIS != null) {
                pisst.setVPIS(this.vPIS.toString());
            }
        }
        return pisst;
    }
}
