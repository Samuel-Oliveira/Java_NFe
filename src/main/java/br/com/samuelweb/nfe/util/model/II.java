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

    public TNFe.InfNFe.Det.Imposto.II build() {
        if ((this.vbc.compareTo(BigDecimal.ZERO) > 0)
                || (this.vDespAdu.compareTo(BigDecimal.ZERO) > 0)
                || (this.vii.compareTo(BigDecimal.ZERO) > 0)
                || (this.viof.compareTo(BigDecimal.ZERO) > 0)) {
            TNFe.InfNFe.Det.Imposto.II ii = new TNFe.InfNFe.Det.Imposto.II();
            if (this.vbc != null) {
                ii.setVBC(this.vbc.toString());
            }
            if (this.vDespAdu != null) {
                ii.setVDespAdu(this.vDespAdu.toString());
            }
            if (this.vii != null) {
                ii.setVII(this.vii.toString());
            }
            if (this.viof != null) {
                ii.setVIOF(this.viof.toString());
            }
            return ii;
        }
        return null;
    }
}