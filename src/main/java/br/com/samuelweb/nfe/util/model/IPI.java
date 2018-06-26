package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.CSTIpi;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJ;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi;

import java.math.BigDecimal;

public class IPI {
    
    @NfeCampo(tipo = String.class
            , id = "O03", tag = "CNPJProd", validadores = {ValidarCNPJ.class}
            , tamanhoMinimo = 14, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = NfeConsts.DSC_CNPJPROD)
    private String CNPJProd;
    
    @NfeCampo(tipo = String.class
            , id = "O04", tag = "cSelo"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = NfeConsts.DSC_CSELO)
    private String cSelo;
    
    @NfeCampo(tipo = Integer.class
            , id = "O05", tag = "qSelo"
            , tamanhoMinimo = 1, tamanhoMaximo = 12, ocorrencias = 0
            , descricao = NfeConsts.DSC_QSELO)
    private Integer qSelo;
    
    @NfeCampo(tipo = String.class
            , id = "O06", tag = "cEnq", valorDefault = "999"
            , tamanhoMinimo = 3, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = NfeConsts.DSC_CENQ)
    private String cEnq;

    @NfeCampo(tipo = CSTIpi.class
            , id = "O09", tag = "CST"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_CST)
    private CSTIpi cst;

    @NfeCampo(tipo = BigDecimal.class
            , id = "O10", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBC)
    private BigDecimal vBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "O11", tag = "qUnid", decimais = 4, precisao = 16
            , tamanhoMinimo = 1, tamanhoMaximo = 16, ocorrencias = 0
            , descricao = NfeConsts.DSC_QUNID)
    private BigDecimal qUnid;

    @NfeCampo(tipo = BigDecimal.class
            , id = "O12", tag = "vUnid", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VUNID)
    private BigDecimal vUnid;

    @NfeCampo(tipo = BigDecimal.class
            , id = "O13", tag = "pIPI ", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PIPI)
    private BigDecimal pIPI;

    @NfeCampo(tipo = BigDecimal.class
            , id = "O14", tag = "vIPI", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VIPI)
    private BigDecimal vIPI;

    public TIpi build() {
        TIpi ipi = new TIpi();
        if (this.cst != null) {
            this.cst.getMontaImposto().build(ipi, this);
        }
        ipi.setCEnq(this.cEnq);
        ipi.setCNPJProd(this.CNPJProd);
        ipi.setCSelo(this.cSelo);
        if (this.qSelo != null) {
            ipi.setQSelo(this.qSelo.toString());
        }
        return ipi;
    }

    public String getCNPJProd() {
        return CNPJProd;
    }

    public void setCNPJProd(String CNPJProd) {
        this.CNPJProd = CNPJProd;
    }

    public String getcSelo() {
        return cSelo;
    }

    public void setcSelo(String cSelo) {
        this.cSelo = cSelo;
    }

    public Integer getqSelo() {
        return qSelo;
    }

    public void setqSelo(Integer qSelo) {
        this.qSelo = qSelo;
    }

    public String getcEnq() {
        return cEnq;
    }

    public void setcEnq(String cEnq) {
        this.cEnq = cEnq;
    }

    public CSTIpi getCst() {
        return cst;
    }

    public void setCst(CSTIpi cst) {
        this.cst = cst;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    public BigDecimal getqUnid() {
        return qUnid;
    }

    public void setqUnid(BigDecimal qUnid) {
        this.qUnid = qUnid;
    }

    public BigDecimal getvUnid() {
        return vUnid;
    }

    public void setvUnid(BigDecimal vUnid) {
        this.vUnid = vUnid;
    }

    public BigDecimal getpIPI() {
        return pIPI;
    }

    public void setpIPI(BigDecimal pIPI) {
        this.pIPI = pIPI;
    }

    public BigDecimal getvIPI() {
        return vIPI;
    }

    public void setvIPI(BigDecimal vIPI) {
        this.vIPI = vIPI;
    }
}
