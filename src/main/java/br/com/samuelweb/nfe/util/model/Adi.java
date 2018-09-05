package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarDrawback;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

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
        adi.setNAdicao(this.getnAdicao());
        adi.setNSeqAdic(this.getnSeqAdic());
        adi.setCFabricante(this.getcFabricante());
        if (this.getvDescDI() != null) {
            adi.setVDescDI(this.getvDescDI().toString());
        }
        adi.setNDraw(this.getnDraw());
        return adi;
    }

    public String getnAdicao() {
        return nAdicao;
    }

    public String getnSeqAdic() {
        return nSeqAdic;
    }

    public String getcFabricante() {
        return cFabricante;
    }

    public BigDecimal getvDescDI() {
        return vDescDI;
    }

    public String getnDraw() {
        return nDraw;
    }

    public Adi setnAdicao(String nAdicao) {
        this.nAdicao = nAdicao;
        return this;
    }

    public Adi setnSeqAdic(String nSeqAdic) {
        this.nSeqAdic = nSeqAdic;
        return this;
    }

    public Adi setcFabricante(String cFabricante) {
        this.cFabricante = cFabricante;
        return this;
    }

    public Adi setvDescDI(BigDecimal vDescDI) {
        this.vDescDI = vDescDI;
        return this;
    }

    public Adi setnDraw(String nDraw) {
        this.nDraw = nDraw;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}