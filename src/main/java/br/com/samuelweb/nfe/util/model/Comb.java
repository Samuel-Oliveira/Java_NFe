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
        if (this.getcProdANP() != null) {
            comb.setCProdANP(this.getcProdANP().toString());
        }
        comb.setDescANP(this.getDescANP());
        if (this.getPglp() != null) {
            comb.setPGLP(this.getPglp().toString());
        }
        if (this.getPgNn() != null) {
            comb.setPGNn(this.getPgNn().toString());
        }
        if (this.getPgNi() != null) {
            comb.setPGNi(this.getPgNi().toString());
        }
        if (this.getvPart() != null) {
            comb.setVPart(this.getvPart().toString());
        }
        if (this.getCodif() != null) {
            comb.setCODIF(this.getCodif().toString());
        }
        if (this.getqTemp() != null) {
            comb.setQTemp(this.getqTemp().toString());
        }
        comb.setUFCons(TUf.fromValue(this.getUfCons()));
        if (this.getCide() != null) {
            comb.setCIDE(this.getCide().build());
        }
        if (this.getEncerrante() != null) {
            comb.setEncerrante(this.getEncerrante().build());
        }
        return comb;
    }

    public Integer getcProdANP() {
        return cProdANP;
    }

    public String getDescANP() {
        return descANP;
    }

    public BigDecimal getPglp() {
        return pglp;
    }

    public BigDecimal getPgNn() {
        return pgNn;
    }

    public BigDecimal getPgNi() {
        return pgNi;
    }

    public BigDecimal getvPart() {
        return vPart;
    }

    public Integer getCodif() {
        return codif;
    }

    public BigDecimal getqTemp() {
        return qTemp;
    }

    public String getUfCons() {
        return ufCons;
    }

    public Cide getCide() {
        return cide;
    }

    public Encerrante getEncerrante() {
        return encerrante;
    }

    public Comb setcProdANP(Integer cProdANP) {
        this.cProdANP = cProdANP;
        return this;
    }

    public Comb setDescANP(String descANP) {
        this.descANP = descANP;
        return this;
    }

    public Comb setPglp(BigDecimal pglp) {
        this.pglp = pglp;
        return this;
    }

    public Comb setPgNn(BigDecimal pgNn) {
        this.pgNn = pgNn;
        return this;
    }

    public Comb setPgNi(BigDecimal pgNi) {
        this.pgNi = pgNi;
        return this;
    }

    public Comb setvPart(BigDecimal vPart) {
        this.vPart = vPart;
        return this;
    }

    public Comb setCodif(Integer codif) {
        this.codif = codif;
        return this;
    }

    public Comb setqTemp(BigDecimal qTemp) {
        this.qTemp = qTemp;
        return this;
    }

    public Comb setUfCons(String ufCons) {
        this.ufCons = ufCons;
        return this;
    }

    public Comb setCide(Cide cide) {
        this.cide = cide;
        return this;
    }

    public Comb setEncerrante(Encerrante encerrante) {
        this.encerrante = encerrante;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {
        if (this.getCide() != null) {
            this.getCide().validarRegraNegocio(infNFe);
        }
        if (this.getEncerrante() != null) {
            this.getEncerrante().validarRegraNegocio(infNFe);
        }
    }
}