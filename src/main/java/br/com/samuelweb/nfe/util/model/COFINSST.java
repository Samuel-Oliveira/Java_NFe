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
        if (this.getvBC() != null && this.getpCofins() != null
                && this.getvBC().add(this.getpCofins()).compareTo(BigDecimal.ZERO) > 0) {
            cofinsst.setVBC(this.getvBC().toString());
            cofinsst.setPCOFINS(this.getpCofins().toString());
        }
        if (this.getqBCProd() != null && this.getvAliqProd() != null
                && this.getqBCProd().add(this.getvAliqProd()).compareTo(BigDecimal.ZERO) > 0) {
            cofinsst.setQBCProd(this.getqBCProd().toString());
            cofinsst.setVAliqProd(this.getvAliqProd().toString());
        }
        if (this.getvCofins() != null) {
            cofinsst.setVCOFINS(this.getvCofins().toString());
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
