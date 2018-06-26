package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class Encerrante {

    @NfeCampo(tipo = Integer.class
            , id = "LA12", tag = "nBico"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = NfeConsts.DSC_NBICO)
    private Integer nBico;

    @NfeCampo(tipo = Integer.class
            , id = "LA13", tag = "nBomba"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 0
            , descricao = NfeConsts.DSC_NBOMBA)
    private Integer nBomba;

    @NfeCampo(tipo = Integer.class
            , id = "LA14", tag = "nTanque"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = NfeConsts.DSC_NTANQUE)
    private Integer nTanque;

    @NfeCampo(tipo = BigDecimal.class
            , id = "LA15", tag = "vEncIni", decimais = 3, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VENCINI)
    private BigDecimal vEncIni;

    @NfeCampo(tipo = BigDecimal.class
            , id = "LA16", tag = "vEncFin", decimais = 3, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VENCFIN)
    private BigDecimal vEncFin;

    public TNFe.InfNFe.Det.Prod.Comb.Encerrante build() {
        TNFe.InfNFe.Det.Prod.Comb.Encerrante encerrante = new TNFe.InfNFe.Det.Prod.Comb.Encerrante();
        if (this.nBico != null) {
            encerrante.setNBico(this.nBico.toString());
        }
        if (this.nBomba != null) {
            encerrante.setNBomba(this.nBomba.toString());
        }
        if (this.nTanque != null) {
            encerrante.setNTanque(this.nTanque.toString());
        }
        if (this.vEncIni != null) {
            encerrante.setVEncIni(this.vEncIni.toString());
        }
        if (this.vEncFin != null) {
            encerrante.setVEncFin(this.vEncFin.toString());
        }
        return encerrante;
    }
}
