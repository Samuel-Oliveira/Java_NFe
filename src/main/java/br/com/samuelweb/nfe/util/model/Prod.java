package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidaIndTotal;
import br.com.samuelweb.nfe.util.validators.impl.ValidarGTIN;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class Prod {

    @NfeCampo(tipo = String.class
            , id = "I02", tag = "cProd"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_CPROD)
    private String cProd;

    @NfeCampo(tipo = String.class
            , id = "I02", tag = "cEAN", validadores = {ValidarGTIN.class}
            , tamanhoMinimo = 0, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_CEAN)
    private String cean;

    @NfeCampo(tipo = String.class
            , id = "I04", tag = "xProd"
            , tamanhoMinimo = 1, tamanhoMaximo = 120, ocorrencias = 1
            , descricao = DfeConsts.DSC_XPROD)
    private String xProd;

    @NfeCampo(tipo = String.class
            , id = "I05", tag = "NCM"
            , tamanhoMinimo = 2, tamanhoMaximo = 8, ocorrencias = 1
            , descricao = DfeConsts.DSC_NCM)
    private String ncm;

    @NfeCampo(tipo = String.class
            , id = "I05a", tag = "NVE"
            , tamanhoMinimo = 6, tamanhoMaximo = 6, ocorrencias = 0
            , descricao = NfeConsts.DSC_NVE)
    private List<String> nve;

    @NfeCampo(tipo = String.class
            , id = "I05c", tag = "CEST"
            , tamanhoMinimo = 7, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_CEST)
    private String cest;

    @NfeCampo(tipo = String.class
            , id = "I05d", tag = "indEscala"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 0
            , descricao = NfeConsts.DSC_INDESCALA)
    private String indEscala;

    @NfeCampo(tipo = String.class
            , id = "I05e", tag = "CNPJFab"
            , tamanhoMinimo = 14, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = NfeConsts.DSC_CNPJFAB)
    private String cnpjFab;

    @NfeCampo(tipo = String.class
            , id = "I05f", tag = "cBenef"
            , tamanhoMinimo = 10, tamanhoMaximo = 10, ocorrencias = 0
            , descricao = NfeConsts.DSC_CBENEF)
    private String cBenef;

    @NfeCampo(tipo = String.class
            , id = "I06", tag = "EXTIPI"
            , tamanhoMinimo = 2, tamanhoMaximo = 3, ocorrencias = 0
            , descricao = NfeConsts.DSC_EXTIPI)
    private String extipi;

    @NfeCampo(tipo = String.class
            , id = "I08 ", tag = "CFOP"
            , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = DfeConsts.DSC_CFOP)
    private String cfop;

    @NfeCampo(tipo = String.class
            , id = "I09", tag = "uCom"
            , tamanhoMinimo = 1, tamanhoMaximo = 6, ocorrencias = 1
            , descricao = DfeConsts.DSC_UCOM)
    private String uCom;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I10 ", tag = "qCom", decimais = 4, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_QCOM)
    private BigDecimal qCom;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I10a", tag = "vUnCom", decimais = 10, precisao = 21
            , tamanhoMinimo = 0, tamanhoMaximo = 21, ocorrencias = 1
            , descricao = DfeConsts.DSC_VUNCOM)
    private BigDecimal vUnCom;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I11", tag = "vProd", decimais = 2, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VPROD)
    private BigDecimal vProd;

    @NfeCampo(tipo = String.class
            , id = "I12", tag = "cEANTrib", validadores = {ValidarGTIN.class}
            , tamanhoMinimo = 0, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = NfeConsts.DSC_CEANTRIB)
    private String ceanTrib;

    @NfeCampo(tipo = String.class
            , id = "I13", tag = "uTrib"
            , tamanhoMinimo = 1, tamanhoMaximo = 6, ocorrencias = 1
            , descricao = NfeConsts.DSC_UTRIB)
    private String uTrib;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I14", tag = "qTrib", decimais = 4, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_QTRIB)
    private BigDecimal qTrib;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I14a", tag = "vUnTrib", decimais = 10, precisao = 21
            , tamanhoMinimo = 0, tamanhoMaximo = 21, ocorrencias = 1
            , descricao = NfeConsts.DSC_VUNTRIB)
    private BigDecimal vUnTrib;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I15", tag = "vFrete", decimais = 2, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VFRETE)
    private BigDecimal vFrete;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I16", tag = "vSeg", decimais = 2, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VSEG)
    private BigDecimal vSeg;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I17", tag = "vDesc", decimais = 2, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VDESC)
    private BigDecimal vDesc;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I17a", tag = "vOutro", decimais = 2, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VOUTRO)
    private BigDecimal vOutro;

    @NfeCampo(tipo = Integer.class
            , id = "I17b", tag = "indTot", validadores = {ValidaIndTotal.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_INDTOT)
    private Integer indTot;

    @NfeObjetoList(id = "I18", tag = "DI"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 100
            , descricao = NfeConsts.DSC_DI)
    private List<DI> di;

    @NfeObjetoList(id = "I50", tag = "detExport"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 500
            , descricao = NfeConsts.DSC_DETEXPORT)
    private List<DetExport> detExport;

    @NfeCampo(tipo = String.class
            , id = "I30", tag = "xPed"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_XPED)
    private String xPed;

    @NfeCampo(tipo = String.class
            , id = "I31", tag = "nItemPed"
            , tamanhoMinimo = 6, tamanhoMaximo = 6, ocorrencias = 0
            , descricao = DfeConsts.DSC_NITEMPED)
    private String nItemPed;

    @NfeCampo(tipo = String.class
            , id = "I70", tag = "nFCI"
            , tamanhoMinimo = 36, tamanhoMaximo = 36, ocorrencias = 0
            , descricao = NfeConsts.DSC_NFCI)
    private String nfci;

    @NfeObjetoList(id = "I80", tag = "rastro"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 500
            , descricao = NfeConsts.DSC_RASTRO)
    private List<Rastro> rastro;

    @NfeObjeto(id = "J01", tag = "veicProd"
            , ocorrencias = 0, descricao = NfeConsts.DSC_VEICPROD)
    private VeicProd veicProd;

    @NfeObjeto(id = "K01", tag = "med"
            , ocorrencias = 0, descricao = NfeConsts.DSC_MED)
    private Med med;

    @NfeObjetoList(id = "L01", tag = "arma"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 500
            , descricao = NfeConsts.DSC_ARMA)
    private List<Arma> arma;

    @NfeObjeto(id = "LA01", tag = "comb"
            , ocorrencias = 0, descricao = NfeConsts.DSC_COMB)
    private Comb comb;

    @NfeCampo(tipo = String.class
            , id = "LB01", tag = "nRECOPI"
            , tamanhoMinimo = 20, tamanhoMaximo = 20, ocorrencias = 0
            , descricao = NfeConsts.DSC_NRECOPI)
    private String nrecopi;

    public TNFe.InfNFe.Det.Prod build() {
        TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
        prod.setCProd(this.getcProd());
        prod.setCEAN(this.getCean());
        prod.setXProd(this.getxProd());
        prod.setNCM(this.getNcm());
        if (this.getNve() != null) {
            this.getNve().forEach(n -> prod.getNVE().add(n));
        }
        if (StringUtils.isNumeric(this.getCest())) {
            prod.setCEST(this.getCest());
            prod.setIndEscala(this.getIndEscala());
            prod.setCNPJFab(this.getCnpjFab());
        }
        prod.setCBenef(this.getcBenef());
        prod.setEXTIPI(this.getExtipi());
        prod.setCFOP(this.getCfop());
        prod.setUCom(this.getuCom());
        if (this.getqCom() != null) {
            prod.setQCom(this.getqCom().toString());
        }
        if (this.getvUnCom() != null) {
            prod.setVUnCom(this.getvUnCom().toString());
        }
        if (this.getvProd() != null) {
            prod.setVProd(this.getvProd().toString());
        }
        prod.setCEANTrib(this.getCeanTrib());
        prod.setUTrib(this.getuTrib());
        if (this.getqTrib() != null) {
            prod.setQTrib(this.getqTrib().toString());
        }
        if (this.getvUnTrib() != null) {
            prod.setVUnTrib(this.getvUnTrib().toString());
        }
        if (this.getvFrete() != null) {
            prod.setVFrete(this.getvFrete().toString());
        }
        if (this.getvSeg() != null) {
            prod.setVSeg(this.getvSeg().toString());
        }
        if (this.getvDesc() != null) {
            prod.setVDesc(this.getvDesc().toString());
        }
        if (this.getvOutro() != null) {
            prod.setVOutro(this.getvOutro().toString());
        }
        if (this.getIndTot() != null) {
            prod.setIndTot(this.getIndTot().toString());
        }
        if (this.getDi() != null) {
            this.getDi().forEach(i -> prod.getDI().add(i.build()));
        }
        if (this.getDetExport() != null) {
            this.getDetExport().forEach(export -> prod.getDetExport().add(export.build()));
        }
        prod.setXPed(this.getxPed());
        prod.setNItemPed(this.getnItemPed());
        prod.setNFCI(this.getNfci());
        if (this.getRastro() != null) {
            this.getRastro().forEach(ras -> prod.getRastro().add(ras.build()));
        }
        if (this.getVeicProd() != null) {
            prod.setVeicProd(this.getVeicProd().build());
        }
        if (this.getMed() != null) {
            prod.setMed(this.getMed().build());
        }
        if (this.getArma() != null) {
            this.getArma().forEach(ar -> prod.getArma().add(ar.build()));
        }
        if (this.getComb() != null) {
            prod.setComb(this.getComb().build());
        }
        prod.setNRECOPI(this.getNrecopi());
        return prod;
    }

    public String getcProd() {
        return cProd;
    }

    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    public String getCean() {
        return cean;
    }

    public void setCean(String cean) {
        this.cean = cean;
    }

    public String getxProd() {
        return xProd;
    }

    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public List<String> getNve() {
        return nve;
    }

    public void setNve(List<String> nve) {
        this.nve = nve;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getIndEscala() {
        return indEscala;
    }

    public void setIndEscala(String indEscala) {
        this.indEscala = indEscala;
    }

    public String getCnpjFab() {
        return cnpjFab;
    }

    public void setCnpjFab(String cnpjFab) {
        this.cnpjFab = cnpjFab;
    }

    public String getcBenef() {
        return cBenef;
    }

    public void setcBenef(String cBenef) {
        this.cBenef = cBenef;
    }

    public String getExtipi() {
        return extipi;
    }

    public void setExtipi(String extipi) {
        this.extipi = extipi;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getuCom() {
        return uCom;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    public BigDecimal getqCom() {
        return qCom;
    }

    public void setqCom(BigDecimal qCom) {
        this.qCom = qCom;
    }

    public BigDecimal getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(BigDecimal vUnCom) {
        this.vUnCom = vUnCom;
    }

    public BigDecimal getvProd() {
        return vProd;
    }

    public void setvProd(BigDecimal vProd) {
        this.vProd = vProd;
    }

    public String getCeanTrib() {
        return ceanTrib;
    }

    public void setCeanTrib(String ceanTrib) {
        this.ceanTrib = ceanTrib;
    }

    public String getuTrib() {
        return uTrib;
    }

    public void setuTrib(String uTrib) {
        this.uTrib = uTrib;
    }

    public BigDecimal getqTrib() {
        return qTrib;
    }

    public void setqTrib(BigDecimal qTrib) {
        this.qTrib = qTrib;
    }

    public BigDecimal getvUnTrib() {
        return vUnTrib;
    }

    public void setvUnTrib(BigDecimal vUnTrib) {
        this.vUnTrib = vUnTrib;
    }

    public BigDecimal getvFrete() {
        return vFrete;
    }

    public void setvFrete(BigDecimal vFrete) {
        this.vFrete = vFrete;
    }

    public BigDecimal getvSeg() {
        return vSeg;
    }

    public void setvSeg(BigDecimal vSeg) {
        this.vSeg = vSeg;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public void setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
    }

    public BigDecimal getvOutro() {
        return vOutro;
    }

    public void setvOutro(BigDecimal vOutro) {
        this.vOutro = vOutro;
    }

    public Integer getIndTot() {
        return indTot;
    }

    public void setIndTot(Integer indTot) {
        this.indTot = indTot;
    }

    public List<DI> getDi() {
        return di;
    }

    public void setDi(List<DI> di) {
        this.di = di;
    }

    public List<DetExport> getDetExport() {
        return detExport;
    }

    public void setDetExport(List<DetExport> detExport) {
        this.detExport = detExport;
    }

    public String getxPed() {
        return xPed;
    }

    public void setxPed(String xPed) {
        this.xPed = xPed;
    }

    public String getnItemPed() {
        return nItemPed;
    }

    public void setnItemPed(String nItemPed) {
        this.nItemPed = nItemPed;
    }

    public String getNfci() {
        return nfci;
    }

    public void setNfci(String nfci) {
        this.nfci = nfci;
    }

    public List<Rastro> getRastro() {
        return rastro;
    }

    public void setRastro(List<Rastro> rastro) {
        this.rastro = rastro;
    }

    public VeicProd getVeicProd() {
        return veicProd;
    }

    public void setVeicProd(VeicProd veicProd) {
        this.veicProd = veicProd;
    }

    public Med getMed() {
        return med;
    }

    public void setMed(Med med) {
        this.med = med;
    }

    public List<Arma> getArma() {
        return arma;
    }

    public void setArma(List<Arma> arma) {
        this.arma = arma;
    }

    public Comb getComb() {
        return comb;
    }

    public void setComb(Comb comb) {
        this.comb = comb;
    }

    public String getNrecopi() {
        return nrecopi;
    }

    public void setNrecopi(String nrecopi) {
        this.nrecopi = nrecopi;
    }
}
