package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarDrawback;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class Adi {

    @NfeCampo(tipo = Integer.class
            , id = "I26", tag = "nAdicao"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = NfeConsts.DSC_NADICAO)
    private String nAdicao;

    @NfeCampo(tipo = Integer.class
            , id = "I27", tag = "nSeqAdic"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = NfeConsts.DSC_NSEQADIC)
    private String nSeqAdic;
    
    @NfeCampo(tipo = String.class
            , id = "I28", tag = "cFabricante"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = NfeConsts.DSC_CFABRICANTE)
    private String cFabricante;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I29", tag = "vDescDI", decimais = 2, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VDESCDI)
    private BigDecimal vDescDI;
    
    @NfeCampo(tipo = String.class
            , id = "I29a", tag = "nDraw", validadores = {ValidarDrawback.class}
            , tamanhoMinimo = 9, tamanhoMaximo = 11, ocorrencias = 0
            , descricao = NfeConsts.DSC_NDRAW)
    private String nDraw;

    public TNFe.InfNFe.Det.Prod.DI.Adi build() {
        TNFe.InfNFe.Det.Prod.DI.Adi adi = new TNFe.InfNFe.Det.Prod.DI.Adi();
        adi.setNAdicao(this.nAdicao);
        adi.setNSeqAdic(this.nSeqAdic);
        adi.setCFabricante(this.cFabricante);
        if (this.vDescDI != null) {
            adi.setVDescDI(this.vDescDI.toString());
        }
        adi.setNDraw(this.nDraw);
        return adi;
    }

    public String getnAdicao() {
        return nAdicao;
    }

    public void setnAdicao(String nAdicao) {
        this.nAdicao = nAdicao;
    }

    public String getnSeqAdic() {
        return nSeqAdic;
    }

    public void setnSeqAdic(String nSeqAdic) {
        this.nSeqAdic = nSeqAdic;
    }

    public String getcFabricante() {
        return cFabricante;
    }

    public void setcFabricante(String cFabricante) {
        this.cFabricante = cFabricante;
    }

    public BigDecimal getvDescDI() {
        return vDescDI;
    }

    public void setvDescDI(BigDecimal vDescDI) {
        this.vDescDI = vDescDI;
    }

    public String getnDraw() {
        return nDraw;
    }

    public void setnDraw(String nDraw) {
        this.nDraw = nDraw;
    }
}
