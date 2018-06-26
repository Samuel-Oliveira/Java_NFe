package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Lacres {

    @NfeCampo(id = "X34", tag = "nLacre"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 1, descricao = NfeConsts.DSC_NLACRE)
    private String nLacre;

    public TNFe.InfNFe.Transp.Vol.Lacres build(){
        TNFe.InfNFe.Transp.Vol.Lacres lacre = new TNFe.InfNFe.Transp.Vol.Lacres();
        lacre.setNLacre(this.nLacre);
        return lacre;
    }

    public String getnLacre() {
        return nLacre;
    }

    public void setnLacre(String nLacre) {
        this.nLacre = nLacre;
    }
}
