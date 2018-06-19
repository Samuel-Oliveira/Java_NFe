package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class Det {

    private String nItem;

    private Prod prod;
    private TNFe.InfNFe.Det.Imposto imposto;
    private TNFe.InfNFe.Det.ImpostoDevol impostoDevol;

    @NfeCampo(tipo = String.class
            , id = "V01", tag = "infAdProd"
            , tamanhoMinimo = 1, tamanhoMaximo = 500, ocorrencias = 0
            , descricao = DfeConsts.DSC_INFADPROD)
    private String infAdProd;


    public TNFe.InfNFe.Det build() {
        TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        det.setNItem(this.nItem);
        return det;
    }
}
