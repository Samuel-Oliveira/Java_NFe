package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.TipoPagamento;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class DetPag {

     @NfeCampo(tipo = Integer.class, id = "YA01b", tag = "indPag "
             , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 0
             , descricao = NfeConsts.DSC_INDPAG)
    private Integer indPag;

    @NfeCampo(tipo = TipoPagamento.class, id = "YA02", tag = "tPag"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPAG)
    private TipoPagamento tPag;

    @NfeCampo(tipo = BigDecimal.class, id = "YA03", tag = "vPag"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VPAG)
    private BigDecimal vPag;

    @NfeCampo(tag = "card", id = "YA04", descricao = NfeConsts.DSC_CARD)
    private Card card;

    TNFe.InfNFe.Pag.DetPag build(){
        TNFe.InfNFe.Pag.DetPag detPag = new TNFe.InfNFe.Pag.DetPag();

        if (this.indPag != null){
            detPag.setIndPag(this.indPag.toString());
        }

        detPag.setTPag(tPag.getValue().toString());
        if (this.vPag != null){
            detPag.setVPag(this.vPag.toString());
        }

        detPag.setCard(this.card.build());

        return detPag;
    }

}
