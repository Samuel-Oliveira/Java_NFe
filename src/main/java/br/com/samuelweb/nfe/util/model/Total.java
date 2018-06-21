package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class Total {

    @NfeCampo(tipo = ICMSTot.class, id = "W02", tag = "ICMSTot"
            , ocorrencias = 1, descricao = NfeConsts.DSC_ICMSTOT)
    private ICMSTot icmsTot;

    @NfeCampo(tipo = ISSQNtot.class, id = "W17", tag = "ISSQNtot"
            , ocorrencias = 1, descricao = NfeConsts.DSC_ISSQNTOT)
    private ISSQNtot issqNtot;

    @NfeCampo(tipo = ISSQNtot.class, id = "W23", tag = "retTrib"
            , ocorrencias = 1, descricao = NfeConsts.DSC_RETTRIB)
    private RetTrib retTrib;


    public TNFe.InfNFe.Total build() {
        TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();

        if (this.icmsTot != null) {
            total.setICMSTot(this.icmsTot.build());
        }
        if (this.issqNtot != null){
            total.setISSQNtot(this.issqNtot.build());
        }
        if (this.retTrib != null){
            total.setRetTrib(this.retTrib.build());
        }
        return total;

    }

}
