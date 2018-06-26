package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.*;
import br.com.samuelweb.nfe.util.validators.impl.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class ICMS {

    @NfeCampo(tipo = Integer.class
            , id = "N11", tag = "orig", validadores = {ValidarOrigemMercadora.class}
            , tamanhoMinimo = 0, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = DfeConsts.DSC_ORIG)
    private Integer orig;

    @NfeCampo(tipo = CSTIcms.class
            , id = "N12", tag = "CST"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 0
            , descricao = DfeConsts.DSC_CST)
    private CSTIcms CST;

    @NfeCampo(tipo = CSOSNIcms.class
            , id = "N12a", tag = "CSOSN"
            , tamanhoMinimo = 3, tamanhoMaximo = 3, ocorrencias = 0
            , descricao = DfeConsts.DSC_CSOSN)
    private CSOSNIcms CSOSN;

    @NfeCampo(tipo = DeterminacaoBaseIcms.class
            , id = "N13", tag = "modBC"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 0
            , descricao = NfeConsts.DSC_MODBC)
    private DeterminacaoBaseIcms modBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N14", tag = "pRedBC", decimais = 4, precisao = 5
            , tamanhoMinimo = 1, tamanhoMaximo = 5, ocorrencias = 0
            , descricao = DfeConsts.DSC_PREDBC)
    private BigDecimal pRedBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N15", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N16", tag = "pICMS", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_PICMS)
    private BigDecimal pICMS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N16a", tag = "vICMSOp", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VICMS)
    private BigDecimal vICMSOp;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N16b", tag = "pDif", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_PICMS)
    private BigDecimal pDif;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N16c", tag = "vICMSDif", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VICMS)
    private BigDecimal vICMSDif;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N17", tag = "vICMS", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VICMS)
    private BigDecimal vICMS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N17a", tag = "vBCFCP", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCFCP)
    private BigDecimal vBCFCP;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N17b", tag = "pFCP", decimais = 2, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PFCP)
    private BigDecimal pFCP;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N17c", tag = "vFCP", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VFCP)
    private BigDecimal vFCP;

    @NfeCampo(tipo = DeterminacaoBaseIcmsST.class
            , id = "N18", tag = "modBCST", validadores = {ValidarDeterminacaoBaseIcmsST.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 0
            , descricao = NfeConsts.DSC_MODBCST)
    private DeterminacaoBaseIcmsST modBCST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N19", tag = "pMVAST", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PMVAST)
    private BigDecimal pMVAST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N20", tag = "pRedBCST", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PMVAST)
    private BigDecimal pRedBCST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N21", tag = "vBCST", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VBCST)
    private BigDecimal vBCST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N22", tag = "pICMSST", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PICMSST)
    private BigDecimal pICMSST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N23", tag = "vICMSST", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VICMSST)
    private BigDecimal vICMSST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N23a", tag = "vBCFCPST", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCFCPST)
    private BigDecimal vBCFCPST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N23b", tag = "pFCPST", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PFCPST)
    private BigDecimal pFCPST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N23d", tag = "vFCPST", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VFCPST)
    private BigDecimal vFCPST;

    @NfeCampo(tipo = String.class
            , id = "N24", tag = "UFST"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 0
            , descricao = NfeConsts.DSC_UFST)
    private String UFST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N25", tag = "pBCOp", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PBCOP)
    private BigDecimal pBCOp;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N26", tag = "vBCSTRet", decimais = 2, precisao = 13
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCSTRET)
    private BigDecimal vBCSTRet;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N26a", tag = "pST", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PST)
    private BigDecimal pST;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N27", tag = "vICMSSTRet", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_PBCOP)
    private BigDecimal vICMSSTRet;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N27a", tag = "vBCFCPSTRet", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCFCPST)
    private BigDecimal vBCFCPSTRet;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N27b", tag = "pFCPSTRet", decimais = 4, precisao = 7
            , tamanhoMinimo = 1,  tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PFCPSTRET)
    private BigDecimal pFCPSTRet;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N27d", tag = "vFCPSTRet", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VFCPSTRET)
    private BigDecimal vFCPSTRet;

    @NfeCampo(tipo = MotivoDesoneracaoICMS.class
            , id = "N28", tag = "motDesICMS", validadores = {ValidarMotivoDesoneracaoICMS.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 2, ocorrencias = 0
            , descricao = NfeConsts.DSC_MOTDESICMS)
    private MotivoDesoneracaoICMS motDesICMS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N28a", tag = "vICMSDeson", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VICMSDESON)
    private BigDecimal vICMSDeson;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N29", tag = "pCredSN", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PCREDSN)
    private BigDecimal pCredSN;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N30", tag = "vCredICMSSN", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VCREDICMSSN)
    private BigDecimal vCredICMSSN;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N31", tag = "vBCSTDest", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCICMSSTDEST)
    private BigDecimal vBCSTDest;

    @NfeCampo(tipo = BigDecimal.class
            , id = "N32", tag = "vICMSSTDest", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VBCICMSSTDEST)
    private BigDecimal vICMSSTDest;

    public TNFe.InfNFe.Det.Imposto.ICMS build() {
        TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
        if (this.getCST() != null) {
            this.getCST().getMontaImposto().build(icms, this);
        }
        if (this.getCSOSN() != null) {
            this.getCSOSN().getMontaImposto().build(icms, this);
        }
        return icms;
    }

    public Integer getOrig() {
        return orig;
    }

    public void setOrig(Integer orig) {
        this.orig = orig;
    }

    public CSTIcms getCST() {
        return CST;
    }

    public void setCST(CSTIcms CST) {
        this.CST = CST;
    }

    public CSOSNIcms getCSOSN() {
        return CSOSN;
    }

    public void setCSOSN(CSOSNIcms CSOSN) {
        this.CSOSN = CSOSN;
    }

    public DeterminacaoBaseIcms getModBC() {
        return modBC;
    }

    public void setModBC(DeterminacaoBaseIcms modBC) {
        this.modBC = modBC;
    }

    public BigDecimal getpRedBC() {
        return pRedBC;
    }

    public void setpRedBC(BigDecimal pRedBC) {
        this.pRedBC = pRedBC;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    public BigDecimal getpICMS() {
        return pICMS;
    }

    public void setpICMS(BigDecimal pICMS) {
        this.pICMS = pICMS;
    }

    public BigDecimal getvICMSOp() {
        return vICMSOp;
    }

    public void setvICMSOp(BigDecimal vICMSOp) {
        this.vICMSOp = vICMSOp;
    }

    public BigDecimal getpDif() {
        return pDif;
    }

    public void setpDif(BigDecimal pDif) {
        this.pDif = pDif;
    }

    public BigDecimal getvICMSDif() {
        return vICMSDif;
    }

    public void setvICMSDif(BigDecimal vICMSDif) {
        this.vICMSDif = vICMSDif;
    }

    public BigDecimal getvICMS() {
        return vICMS;
    }

    public void setvICMS(BigDecimal vICMS) {
        this.vICMS = vICMS;
    }

    public BigDecimal getvBCFCP() {
        return vBCFCP;
    }

    public void setvBCFCP(BigDecimal vBCFCP) {
        this.vBCFCP = vBCFCP;
    }

    public BigDecimal getpFCP() {
        return pFCP;
    }

    public void setpFCP(BigDecimal pFCP) {
        this.pFCP = pFCP;
    }

    public BigDecimal getvFCP() {
        return vFCP;
    }

    public void setvFCP(BigDecimal vFCP) {
        this.vFCP = vFCP;
    }

    public DeterminacaoBaseIcmsST getModBCST() {
        return modBCST;
    }

    public void setModBCST(DeterminacaoBaseIcmsST modBCST) {
        this.modBCST = modBCST;
    }

    public BigDecimal getpMVAST() {
        return pMVAST;
    }

    public void setpMVAST(BigDecimal pMVAST) {
        this.pMVAST = pMVAST;
    }

    public BigDecimal getpRedBCST() {
        return pRedBCST;
    }

    public void setpRedBCST(BigDecimal pRedBCST) {
        this.pRedBCST = pRedBCST;
    }

    public BigDecimal getvBCST() {
        return vBCST;
    }

    public void setvBCST(BigDecimal vBCST) {
        this.vBCST = vBCST;
    }

    public BigDecimal getpICMSST() {
        return pICMSST;
    }

    public void setpICMSST(BigDecimal pICMSST) {
        this.pICMSST = pICMSST;
    }

    public BigDecimal getvICMSST() {
        return vICMSST;
    }

    public void setvICMSST(BigDecimal vICMSST) {
        this.vICMSST = vICMSST;
    }

    public BigDecimal getvBCFCPST() {
        return vBCFCPST;
    }

    public void setvBCFCPST(BigDecimal vBCFCPST) {
        this.vBCFCPST = vBCFCPST;
    }

    public BigDecimal getpFCPST() {
        return pFCPST;
    }

    public void setpFCPST(BigDecimal pFCPST) {
        this.pFCPST = pFCPST;
    }

    public BigDecimal getvFCPST() {
        return vFCPST;
    }

    public void setvFCPST(BigDecimal vFCPST) {
        this.vFCPST = vFCPST;
    }

    public String getUFST() {
        return UFST;
    }

    public void setUFST(String UFST) {
        this.UFST = UFST;
    }

    public BigDecimal getpBCOp() {
        return pBCOp;
    }

    public void setpBCOp(BigDecimal pBCOp) {
        this.pBCOp = pBCOp;
    }

    public BigDecimal getvBCSTRet() {
        return vBCSTRet;
    }

    public void setvBCSTRet(BigDecimal vBCSTRet) {
        this.vBCSTRet = vBCSTRet;
    }

    public BigDecimal getpST() {
        return pST;
    }

    public void setpST(BigDecimal pST) {
        this.pST = pST;
    }

    public BigDecimal getvICMSSTRet() {
        return vICMSSTRet;
    }

    public void setvICMSSTRet(BigDecimal vICMSSTRet) {
        this.vICMSSTRet = vICMSSTRet;
    }

    public BigDecimal getvBCFCPSTRet() {
        return vBCFCPSTRet;
    }

    public void setvBCFCPSTRet(BigDecimal vBCFCPSTRet) {
        this.vBCFCPSTRet = vBCFCPSTRet;
    }

    public BigDecimal getpFCPSTRet() {
        return pFCPSTRet;
    }

    public void setpFCPSTRet(BigDecimal pFCPSTRet) {
        this.pFCPSTRet = pFCPSTRet;
    }

    public BigDecimal getvFCPSTRet() {
        return vFCPSTRet;
    }

    public void setvFCPSTRet(BigDecimal vFCPSTRet) {
        this.vFCPSTRet = vFCPSTRet;
    }

    public MotivoDesoneracaoICMS getMotDesICMS() {
        return motDesICMS;
    }

    public void setMotDesICMS(MotivoDesoneracaoICMS motDesICMS) {
        this.motDesICMS = motDesICMS;
    }

    public BigDecimal getvICMSDeson() {
        return vICMSDeson;
    }

    public void setvICMSDeson(BigDecimal vICMSDeson) {
        this.vICMSDeson = vICMSDeson;
    }

    public BigDecimal getpCredSN() {
        return pCredSN;
    }

    public void setpCredSN(BigDecimal pCredSN) {
        this.pCredSN = pCredSN;
    }

    public BigDecimal getvCredICMSSN() {
        return vCredICMSSN;
    }

    public void setvCredICMSSN(BigDecimal vCredICMSSN) {
        this.vCredICMSSN = vCredICMSSN;
    }

    public BigDecimal getvBCSTDest() {
        return vBCSTDest;
    }

    public void setvBCSTDest(BigDecimal vBCSTDest) {
        this.vBCSTDest = vBCSTDest;
    }

    public BigDecimal getvICMSSTDest() {
        return vICMSSTDest;
    }

    public void setvICMSSTDest(BigDecimal vICMSSTDest) {
        this.vICMSSTDest = vICMSSTDest;
    }
}
