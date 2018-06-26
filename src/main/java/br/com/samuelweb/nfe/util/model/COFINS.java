package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.enumeration.CSTCofins;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCSTCofins;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class COFINS {

    @NfeCampo(tipo = CSTCofins.class
            , id = "S06", tag = "CST"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_CST)
    private CSTCofins cst;

    @NfeCampo(tipo = BigDecimal.class
            , id = "S07", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "S08", tag = "pCOFINS", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_PCOFINS)
    private BigDecimal pCOFINS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "S11", tag = "vCOFINS", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_VCOFINS)
    private BigDecimal vCOFINS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "S09", tag = "qBCProd", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_QBCPROD)
    private BigDecimal qBCProd;

    @NfeCampo(tipo = BigDecimal.class
            , id = "S10", tag = "vAliqProd", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VALIQPROD)
    private BigDecimal vAliqProd;

    public TNFe.InfNFe.Det.Imposto.COFINS build() {
        TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
        if (this.cst != null) {
            this.cst.getMontaImposto().build(cofins, this);
        }
        return cofins;
    }

    public CSTCofins getCst() {
        return cst;
    }

    public void setCst(CSTCofins cst) {
        this.cst = cst;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    public BigDecimal getpCOFINS() {
        return pCOFINS;
    }

    public void setpCOFINS(BigDecimal pCOFINS) {
        this.pCOFINS = pCOFINS;
    }

    public BigDecimal getvCOFINS() {
        return vCOFINS;
    }

    public void setvCOFINS(BigDecimal vCOFINS) {
        this.vCOFINS = vCOFINS;
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
}
