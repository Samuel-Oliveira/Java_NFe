package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

public class Pag {

    @NfeCampo(id = "detPag", tag = "YA01a", descricao = NfeConsts.DSC_DETPAG)
    private List<DetPag> detPag;

    @NfeCampo(tipo = BigDecimal.class, id = "YA09", tag = "vTroco"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VTROCO)
    private BigDecimal vTroco;


    TNFe.InfNFe.Pag build(){
        TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();
        detPag.forEach(detPag -> pag.getDetPag().add(detPag.build()));
        if (this.vTroco != null){
            pag.setVTroco(this.vTroco.toString());
        }
        return pag;
    }

}
