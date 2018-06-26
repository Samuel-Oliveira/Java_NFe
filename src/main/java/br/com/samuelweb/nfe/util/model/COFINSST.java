package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class COFINSST {

    @NfeCampo(tipo = BigDecimal.class
            , id = "T02", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "T03", tag = "pCofins", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = DfeConsts.DSC_PCOFINS)
    private BigDecimal pCofins;

    @NfeCampo(tipo = BigDecimal.class
            , id = "T04", tag = "qBCProd", decimais = 4, precisao = 16
            , tamanhoMinimo = 1, tamanhoMaximo = 16, ocorrencias = 1
            , descricao = DfeConsts.DSC_QBCPROD)
    private BigDecimal qBCProd;

    @NfeCampo(tipo = BigDecimal.class
            , id = "T05", tag = "vAliqProd", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VALIQPROD)
    private BigDecimal vAliqProd;

    @NfeCampo(tipo = BigDecimal.class
            , id = "T06", tag = "vCofins", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VCOFINS)
    private BigDecimal vCofins;

    public TNFe.InfNFe.Det.Imposto.COFINSST build() {
        TNFe.InfNFe.Det.Imposto.COFINSST cofinsst = new TNFe.InfNFe.Det.Imposto.COFINSST();
        if (this.vBC != null && this.pCofins != null
                && this.vBC.add(this.pCofins).compareTo(BigDecimal.ZERO) > 0) {
            cofinsst.setVBC(this.vBC.toString());
            cofinsst.setPCOFINS(this.pCofins.toString());
        }
        if (this.qBCProd != null && this.vAliqProd != null
                && this.qBCProd.add(this.vAliqProd).compareTo(BigDecimal.ZERO) > 0) {
            cofinsst.setQBCProd(this.qBCProd.toString());
            cofinsst.setVAliqProd(this.vAliqProd.toString());
        }
        if (this.vCofins != null) {
            cofinsst.setVCOFINS(this.vCofins.toString());
        }
        return cofinsst;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    public BigDecimal getpCofins() {
        return pCofins;
    }

    public void setpCofins(BigDecimal pCofins) {
        this.pCofins = pCofins;
    }

    public BigDecimal getqBCProd() {
        return qBCProd;
    }

    public void setqBCProd(BigDecimal qBCProd) {
        this.qBCProd = qBCProd;
    }

    public BigDecimal getvAliqProd() {
        return vAliqProd;
    }

    public void setvAliqProd(BigDecimal vAliqProd) {
        this.vAliqProd = vAliqProd;
    }

    public BigDecimal getvCofins() {
        return vCofins;
    }

    public void setvCofins(BigDecimal vCofins) {
        this.vCofins = vCofins;
    }
}
