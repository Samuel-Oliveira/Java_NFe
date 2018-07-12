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

    public void validarRegraNegocio(InfNFe infNFe) {

    }

    public boolean isEmpty() {
        return (orig == null)
            && (CST == null)
            && (CSOSN == null)
            && (modBC == null)
            && (pRedBC == null)
            && (vBC == null)
            && (pICMS == null)
            && (vICMSOp == null)
            && (pDif == null)
            && (vICMSDif == null)
            && (vICMS == null)
            && (vBCFCP == null)
            && (pFCP == null)
            && (vFCP == null)
            && (modBCST == null)
            && (pMVAST == null)
            && (pRedBCST == null)
            && (vBCST == null)
            && (pICMSST == null)
            && (vICMSST == null)
            && (vBCFCPST == null)
            && (pFCPST == null)
            && (vFCPST == null)
            && (UFST == null || UFST.isEmpty())
            && (pBCOp == null)
            && (vBCSTRet == null)
            && (pST == null)
            && (vICMSSTRet == null)
            && (vBCFCPSTRet == null)
            && (pFCPSTRet == null)
            && (vFCPSTRet == null)
            && (motDesICMS == null)
            && (vICMSDeson == null)
            && (pCredSN == null)
            && (vCredICMSSN == null)
            && (vBCSTDest == null)
            && (vICMSSTDest == null);
    }

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

    public CSTIcms getCST() {
        return CST;
    }

    public CSOSNIcms getCSOSN() {
        return CSOSN;
    }

    public DeterminacaoBaseIcms getModBC() {
        return modBC;
    }

    public BigDecimal getpRedBC() {
        return pRedBC;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public BigDecimal getpICMS() {
        return pICMS;
    }

    public BigDecimal getvICMSOp() {
        return vICMSOp;
    }

    public BigDecimal getpDif() {
        return pDif;
    }

    public BigDecimal getvICMSDif() {
        return vICMSDif;
    }

    public BigDecimal getvICMS() {
        return vICMS;
    }

    public BigDecimal getvBCFCP() {
        return vBCFCP;
    }

    public BigDecimal getpFCP() {
        return pFCP;
    }

    public BigDecimal getvFCP() {
        return vFCP;
    }

    public DeterminacaoBaseIcmsST getModBCST() {
        return modBCST;
    }

    public BigDecimal getpMVAST() {
        return pMVAST;
    }

    public BigDecimal getpRedBCST() {
        return pRedBCST;
    }

    public BigDecimal getvBCST() {
        return vBCST;
    }

    public BigDecimal getpICMSST() {
        return pICMSST;
    }

    public BigDecimal getvICMSST() {
        return vICMSST;
    }

    public BigDecimal getvBCFCPST() {
        return vBCFCPST;
    }

    public BigDecimal getpFCPST() {
        return pFCPST;
    }

    public BigDecimal getvFCPST() {
        return vFCPST;
    }

    public String getUFST() {
        return UFST;
    }

    public BigDecimal getpBCOp() {
        return pBCOp;
    }

    public BigDecimal getvBCSTRet() {
        return vBCSTRet;
    }

    public BigDecimal getpST() {
        return pST;
    }

    public BigDecimal getvICMSSTRet() {
        return vICMSSTRet;
    }

    public BigDecimal getvBCFCPSTRet() {
        return vBCFCPSTRet;
    }

    public BigDecimal getpFCPSTRet() {
        return pFCPSTRet;
    }

    public BigDecimal getvFCPSTRet() {
        return vFCPSTRet;
    }

    public MotivoDesoneracaoICMS getMotDesICMS() {
        return motDesICMS;
    }

    public BigDecimal getvICMSDeson() {
        return vICMSDeson;
    }

    public BigDecimal getpCredSN() {
        return pCredSN;
    }

    public BigDecimal getvCredICMSSN() {
        return vCredICMSSN;
    }

    public BigDecimal getvBCSTDest() {
        return vBCSTDest;
    }

    public BigDecimal getvICMSSTDest() {
        return vICMSSTDest;
    }

    public ICMS setOrig(Integer orig) {
        this.orig = orig;
        return this;
    }

    public ICMS setCST(CSTIcms CST) {
        this.CST = CST;
        return this;
    }

    public ICMS setCSOSN(CSOSNIcms CSOSN) {
        this.CSOSN = CSOSN;
        return this;
    }

    public ICMS setModBC(DeterminacaoBaseIcms modBC) {
        this.modBC = modBC;
        return this;
    }

    public ICMS setpRedBC(BigDecimal pRedBC) {
        this.pRedBC = pRedBC;
        return this;
    }

    public ICMS setvBC(BigDecimal vBC) {
        this.vBC = vBC;
        return this;
    }

    public ICMS setpICMS(BigDecimal pICMS) {
        this.pICMS = pICMS;
        return this;
    }

    public ICMS setvICMSOp(BigDecimal vICMSOp) {
        this.vICMSOp = vICMSOp;
        return this;
    }

    public ICMS setpDif(BigDecimal pDif) {
        this.pDif = pDif;
        return this;
    }

    public ICMS setvICMSDif(BigDecimal vICMSDif) {
        this.vICMSDif = vICMSDif;
        return this;
    }

    public ICMS setvICMS(BigDecimal vICMS) {
        this.vICMS = vICMS;
        return this;
    }

    public ICMS setvBCFCP(BigDecimal vBCFCP) {
        this.vBCFCP = vBCFCP;
        return this;
    }

    public ICMS setpFCP(BigDecimal pFCP) {
        this.pFCP = pFCP;
        return this;
    }

    public ICMS setvFCP(BigDecimal vFCP) {
        this.vFCP = vFCP;
        return this;
    }

    public ICMS setModBCST(DeterminacaoBaseIcmsST modBCST) {
        this.modBCST = modBCST;
        return this;
    }

    public ICMS setpMVAST(BigDecimal pMVAST) {
        this.pMVAST = pMVAST;
        return this;
    }

    public ICMS setpRedBCST(BigDecimal pRedBCST) {
        this.pRedBCST = pRedBCST;
        return this;
    }

    public ICMS setvBCST(BigDecimal vBCST) {
        this.vBCST = vBCST;
        return this;
    }

    public ICMS setpICMSST(BigDecimal pICMSST) {
        this.pICMSST = pICMSST;
        return this;
    }

    public ICMS setvICMSST(BigDecimal vICMSST) {
        this.vICMSST = vICMSST;
        return this;
    }

    public ICMS setvBCFCPST(BigDecimal vBCFCPST) {
        this.vBCFCPST = vBCFCPST;
        return this;
    }

    public ICMS setpFCPST(BigDecimal pFCPST) {
        this.pFCPST = pFCPST;
        return this;
    }

    public ICMS setvFCPST(BigDecimal vFCPST) {
        this.vFCPST = vFCPST;
        return this;
    }

    public ICMS setUFST(String UFST) {
        this.UFST = UFST;
        return this;
    }

    public ICMS setpBCOp(BigDecimal pBCOp) {
        this.pBCOp = pBCOp;
        return this;
    }

    public ICMS setvBCSTRet(BigDecimal vBCSTRet) {
        this.vBCSTRet = vBCSTRet;
        return this;
    }

    public ICMS setpST(BigDecimal pST) {
        this.pST = pST;
        return this;
    }

    public ICMS setvICMSSTRet(BigDecimal vICMSSTRet) {
        this.vICMSSTRet = vICMSSTRet;
        return this;
    }

    public ICMS setvBCFCPSTRet(BigDecimal vBCFCPSTRet) {
        this.vBCFCPSTRet = vBCFCPSTRet;
        return this;
    }

    public ICMS setpFCPSTRet(BigDecimal pFCPSTRet) {
        this.pFCPSTRet = pFCPSTRet;
        return this;
    }

    public ICMS setvFCPSTRet(BigDecimal vFCPSTRet) {
        this.vFCPSTRet = vFCPSTRet;
        return this;
    }

    public ICMS setMotDesICMS(MotivoDesoneracaoICMS motDesICMS) {
        this.motDesICMS = motDesICMS;
        return this;
    }

    public ICMS setvICMSDeson(BigDecimal vICMSDeson) {
        this.vICMSDeson = vICMSDeson;
        return this;
    }

    public ICMS setpCredSN(BigDecimal pCredSN) {
        this.pCredSN = pCredSN;
        return this;
    }

    public ICMS setvCredICMSSN(BigDecimal vCredICMSSN) {
        this.vCredICMSSN = vCredICMSSN;
        return this;
    }

    public ICMS setvBCSTDest(BigDecimal vBCSTDest) {
        this.vBCSTDest = vBCSTDest;
        return this;
    }

    public ICMS setvICMSSTDest(BigDecimal vICMSSTDest) {
        this.vICMSSTDest = vICMSSTDest;
        return this;
    }
}