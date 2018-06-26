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
        if (this.getnBico() != null) {
            encerrante.setNBico(this.getnBico().toString());
        }
        if (this.getnBomba() != null) {
            encerrante.setNBomba(this.getnBomba().toString());
        }
        if (this.getnTanque() != null) {
            encerrante.setNTanque(this.getnTanque().toString());
        }
        if (this.getvEncIni() != null) {
            encerrante.setVEncIni(this.getvEncIni().toString());
        }
        if (this.getvEncFin() != null) {
            encerrante.setVEncFin(this.getvEncFin().toString());
        }
        return encerrante;
    }

    public Integer getnBico() {
        return nBico;
    }

    public void setnBico(Integer nBico) {
        this.nBico = nBico;
    }

    public Integer getnBomba() {
        return nBomba;
    }

    public void setnBomba(Integer nBomba) {
        this.nBomba = nBomba;
    }

    public Integer getnTanque() {
        return nTanque;
    }

    public void setnTanque(Integer nTanque) {
        this.nTanque = nTanque;
    }

    public BigDecimal getvEncIni() {
        return vEncIni;
    }

    public void setvEncIni(BigDecimal vEncIni) {
        this.vEncIni = vEncIni;
    }

    public BigDecimal getvEncFin() {
        return vEncFin;
    }

    public void setvEncFin(BigDecimal vEncFin) {
        this.vEncFin = vEncFin;
    }
}
