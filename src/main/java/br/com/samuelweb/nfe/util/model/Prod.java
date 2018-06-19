package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidaIndTotal;
import br.com.samuelweb.nfe.util.validators.impl.ValidarGTIN;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
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
            , tamanhoMinimo = 11, tamanhoMaximo = 15, ocorrencias = 1
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

    @NfeCampo(tipo = String.class
            , id = "I17b", tag = "indTot", validadores = {ValidaIndTotal.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_INDTOT)
    private String indTot;

    private List<DI> di;
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

    private List<Rastro> rastro;
    private VeicProd veicProd;
    private Med med;
    private List<Arma> arma;
    private Comb comb;

    @NfeCampo(tipo = String.class
            , id = "LB01", tag = "nRECOPI"
            , tamanhoMinimo = 20, tamanhoMaximo = 20, ocorrencias = 1
            , descricao = NfeConsts.DSC_NRECOPI)
    private String nrecopi;

    public TNFe.InfNFe.Det.Prod build() {
        TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
        prod.setCProd(this.cProd);
        prod.setCEAN(this.cean);
        prod.setXProd(this.xProd);
        prod.setNCM(this.ncm);
        if (this.nve != null) {
            this.nve.forEach( n -> prod.getNVE().add(n) );
        }
        if (StringUtils.isNumeric(this.cest)) {
            prod.setCEST(this.cest);
            prod.setIndEscala(this.indEscala);
            prod.setCNPJFab(this.cnpjFab);
        }
        prod.setCBenef(this.cBenef);
        prod.setEXTIPI(this.extipi);
        prod.setCFOP(this.cfop);
        prod.setUCom(this.uCom);
        if (this.qCom != null) {
            prod.setQCom(this.qCom.toString());
        }
        if (this.vUnCom != null) {
            prod.setVUnCom(this.vUnCom.toString());
        }
        if (this.vProd != null) {
            prod.setVProd(this.vProd.toString());
        }
        prod.setCEANTrib(this.ceanTrib);
        prod.setUTrib(this.uTrib);
        if (this.qTrib != null) {
            prod.setQTrib(this.qTrib.toString());
        }
        if (this.vUnTrib != null) {
            prod.setVUnTrib(this.vUnTrib.toString());
        }
        if (this.vFrete != null) {
            prod.setVFrete(this.vFrete.toString());
        }
        if (this.vSeg != null) {
            prod.setVSeg(this.vSeg.toString());
        }
        if (this.vDesc != null) {
            prod.setVDesc(this.vDesc.toString());
        }
        if (this.vOutro != null) {
            prod.setVOutro(this.vOutro.toString());
        }
        prod.setIndTot(this.indTot);
        if (this.di != null) {
            this.di.forEach( i -> prod.getDI().add(i.build()));
        }
        if (this.detExport != null) {
            this.detExport.forEach( export -> prod.getDetExport().add(export.build()));
        }
        prod.setXPed(this.xPed);
        prod.setNItemPed(this.nItemPed);
        prod.setNFCI(this.nfci);
        if (this.rastro != null) {
            this.rastro.forEach(ras -> prod.getRastro().add(ras.build()));
        }
        if (this.veicProd != null) {
            prod.setVeicProd(this.veicProd.build());
        }
        if (this.med != null) {
            prod.setMed(this.med.build());
        }
        if (this.arma != null) {
            this.arma.forEach(ar -> prod.getArma().add(ar.build()));
        }
        if (this.comb != null) {
            prod.setComb(this.comb.build());
        }
        prod.setNRECOPI(this.nrecopi);
        return prod;
    }
}
