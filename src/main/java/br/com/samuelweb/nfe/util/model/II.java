package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class II {

    @NfeCampo(tipo = BigDecimal.class
            , id = "P02", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vbc;

    @NfeCampo(tipo = BigDecimal.class
            , id = "P03", tag = "vDespAdu", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VDESPADU)
    private BigDecimal vDespAdu;

    @NfeCampo(tipo = BigDecimal.class
            , id = "P04", tag = "vII", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VII)
    private BigDecimal vii;

    @NfeCampo(tipo = BigDecimal.class
            , id = "P05", tag = "vIOF", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VIOF)
    private BigDecimal viof;

    public boolean isEmpty() {
        return (vbc == null)
            && (vDespAdu == null)
            && (vii == null)
            && (viof == null);
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
    public TNFe.InfNFe.Det.Imposto.II build() {
        if ((this.getVbc().compareTo(BigDecimal.ZERO) > 0)
                || (this.getvDespAdu().compareTo(BigDecimal.ZERO) > 0)
                || (this.getVii().compareTo(BigDecimal.ZERO) > 0)
                || (this.getViof().compareTo(BigDecimal.ZERO) > 0)) {
            TNFe.InfNFe.Det.Imposto.II ii = new TNFe.InfNFe.Det.Imposto.II();
            if (this.getVbc() != null) {
                ii.setVBC(this.getVbc().toString());
            }
            if (this.getvDespAdu() != null) {
                ii.setVDespAdu(this.getvDespAdu().toString());
            }
            if (this.getVii() != null) {
                ii.setVII(this.getVii().toString());
            }
            if (this.getViof() != null) {
                ii.setVIOF(this.getViof().toString());
            }
            return ii;
        }
        return null;
    }

    public BigDecimal getVbc() {
        return vbc;
    }

    public BigDecimal getvDespAdu() {
        return vDespAdu;
    }

    public BigDecimal getVii() {
        return vii;
    }

    public BigDecimal getViof() {
        return viof;
    }

    public II setVbc(BigDecimal vbc) {
        this.vbc = vbc;
        return this;
    }

    public II setvDespAdu(BigDecimal vDespAdu) {
        this.vDespAdu = vDespAdu;
        return this;
    }

    public II setVii(BigDecimal vii) {
        this.vii = vii;
        return this;
    }

    public II setViof(BigDecimal viof) {
        this.viof = viof;
        return this;
    }
}