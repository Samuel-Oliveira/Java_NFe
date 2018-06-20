package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class Deduc {

    @NfeCampo(id = "ZC11", tag = "xDed"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 1, descricao = NfeConsts.DSC_XDED)
    private String xDed;

    @NfeCampo(tipo = BigDecimal.class, id = "ZC12",tag = "vDed"
            , tamanhoMinimo = 1,tamanhoMaximo = 15,ocorrencias = 1
            , decimais = 13, precisao = 15
            , descricao = NfeConsts.DSC_VDED)
    private BigDecimal vDed;

    public TNFe.InfNFe.Cana.Deduc build(){
        TNFe.InfNFe.Cana.Deduc deduc = new TNFe.InfNFe.Cana.Deduc();

        deduc.setXDed(this.xDed);

        if (this.vDed != null){
            deduc.setVDed(this.vDed.toString());
        }

        return deduc;
    }

}
