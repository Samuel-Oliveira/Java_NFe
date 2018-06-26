package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.Bandeira;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Card {

    @NfeCampo(tipo = Integer.class, id = "YA04a", tag = "tpIntegra"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPINTEGRA)
    private Integer tpIntegra;

    @NfeCampo(id = "YA05", tag = "CNPJ "
            , tamanhoMinimo = 14, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = NfeConsts.DSC_CNPJ)
    private String cnpj;

    @NfeCampo(tipo = Bandeira.class, id = "YA06", tag = "tBand"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 0
            , descricao = NfeConsts.DSC_TBAND)
    private Bandeira tBand;

    @NfeCampo(id = "YA07", tag = "cAut"
            , tamanhoMinimo = 1, tamanhoMaximo = 20, ocorrencias = 0
            , descricao = NfeConsts.DSC_CAUT)
    private String cAut;

    TNFe.InfNFe.Pag.DetPag.Card build() {
        TNFe.InfNFe.Pag.DetPag.Card card = new TNFe.InfNFe.Pag.DetPag.Card();

        if (this.getTpIntegra() != null) {
            card.setTpIntegra(this.getTpIntegra().toString());
        }
        card.setCNPJ(this.getCnpj());
        card.setTpIntegra(this.gettBand().getValue().toString());
        card.setCAut(this.getcAut());

        return card;
    }

    public Integer getTpIntegra() {
        return tpIntegra;
    }

    public void setTpIntegra(Integer tpIntegra) {
        this.tpIntegra = tpIntegra;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Bandeira gettBand() {
        return tBand;
    }

    public void settBand(Bandeira tBand) {
        this.tBand = tBand;
    }

    public String getcAut() {
        return cAut;
    }

    public void setcAut(String cAut) {
        this.cAut = cAut;
    }
}
