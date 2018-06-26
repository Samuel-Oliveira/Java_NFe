package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;

import java.math.BigDecimal;

public class Comb {

    @NfeCampo(tipo = Integer.class
            , id = "L102", tag = "cProdANP"
            , tamanhoMinimo = 9, tamanhoMaximo = 9, ocorrencias = 1
            , descricao = NfeConsts.DSC_CPRODANP)
    private Integer cProdANP;

    @NfeCampo(tipo = String.class
            , id = "LA03", tag = "descANP"
            , tamanhoMinimo = 2, tamanhoMaximo = 95, ocorrencias = 1
            , descricao = NfeConsts.DSC_DESCANP)
    private String descANP;

    @NfeCampo(tipo = BigDecimal.class
            , id = "LA03a", tag = "pGLP", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PGLP)
    private BigDecimal pglp;

    @NfeCampo(tipo = BigDecimal.class
            , id = "LA03b", tag = "pGNn", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PGNN)
    private BigDecimal pgNn;

    @NfeCampo(tipo = BigDecimal.class
            , id = "LA03c", tag = "pGNi", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = NfeConsts.DSC_PGNI)
    private BigDecimal pgNi;

    @NfeCampo(tipo = BigDecimal.class
            , id = "LA03d", tag = "vPart"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VPART)
    private BigDecimal vPart;

    @NfeCampo(tipo = Integer.class
            , id = "L103", tag = "CODIF"
            , tamanhoMinimo = 1, tamanhoMaximo = 21, ocorrencias = 0
            , descricao = NfeConsts.DSC_CODIF)
    private Integer codif;

    @NfeCampo(tipo = BigDecimal.class
            , id = "L104", tag = "qTemp", decimais = 4, precisao = 16
            , tamanhoMinimo = 1, tamanhoMaximo = 16, ocorrencias = 0
            , descricao = NfeConsts.DSC_QTEMP)
    private BigDecimal qTemp;

    @NfeCampo(tipo = String.class
            , id = "LA06", tag = "UFCons"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = NfeConsts.DSC_UFCONS)
    private String ufCons;

    private Cide cide;
    
    private Encerrante encerrante;
    
    public TNFe.InfNFe.Det.Prod.Comb build() {
        TNFe.InfNFe.Det.Prod.Comb comb = new TNFe.InfNFe.Det.Prod.Comb();
        if (this.cProdANP != null) {
            comb.setCProdANP(this.cProdANP.toString());
        }
        comb.setDescANP(this.descANP);
        if (this.pglp != null) {
            comb.setPGLP(this.pglp.toString());
        }
        if (this.pgNn != null) {
            comb.setPGNn(this.pgNn.toString());
        }
        if (this.pgNi != null) {
            comb.setPGNi(this.pgNi.toString());
        }
        if (this.vPart != null) {
            comb.setVPart(this.vPart.toString());
        }
        if (this.codif != null) {
            comb.setCODIF(this.codif.toString());
        }
        if (this.qTemp != null) {
            comb.setQTemp(this.qTemp.toString());
        }
        comb.setUFCons(TUf.fromValue(this.ufCons));
        if (this.cide != null) {
            comb.setCIDE(this.cide.build());
        }
        if (this.encerrante != null) {
            comb.setEncerrante(this.encerrante.build());
        }
        return comb;
    }

    public Integer getcProdANP() {
        return cProdANP;
    }

    public void setcProdANP(Integer cProdANP) {
        this.cProdANP = cProdANP;
    }

    public String getDescANP() {
        return descANP;
    }

    public void setDescANP(String descANP) {
        this.descANP = descANP;
    }

    public BigDecimal getPglp() {
        return pglp;
    }

    public void setPglp(BigDecimal pglp) {
        this.pglp = pglp;
    }

    public BigDecimal getPgNn() {
        return pgNn;
    }

    public void setPgNn(BigDecimal pgNn) {
        this.pgNn = pgNn;
    }

    public BigDecimal getPgNi() {
        return pgNi;
    }

    public void setPgNi(BigDecimal pgNi) {
        this.pgNi = pgNi;
    }

    public BigDecimal getvPart() {
        return vPart;
    }

    public void setvPart(BigDecimal vPart) {
        this.vPart = vPart;
    }

    public Integer getCodif() {
        return codif;
    }

    public void setCodif(Integer codif) {
        this.codif = codif;
    }

    public BigDecimal getqTemp() {
        return qTemp;
    }

    public void setqTemp(BigDecimal qTemp) {
        this.qTemp = qTemp;
    }

    public String getUfCons() {
        return ufCons;
    }

    public void setUfCons(String ufCons) {
        this.ufCons = ufCons;
    }

    public Cide getCide() {
        return cide;
    }

    public void setCide(Cide cide) {
        this.cide = cide;
    }

    public Encerrante getEncerrante() {
        return encerrante;
    }

    public void setEncerrante(Encerrante encerrante) {
        this.encerrante = encerrante;
    }
}