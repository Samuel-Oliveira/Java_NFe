package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

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

        deduc.setXDed(this.getxDed());

        if (this.getvDed() != null){
            deduc.setVDed(this.getvDed().toString());
        }

        return deduc;
    }

    public String getxDed() {
        return xDed;
    }

    public BigDecimal getvDed() {
        return vDed;
    }

    public Deduc setxDed(String xDed) {
        this.xDed = xDed;
        return this;
    }

    public Deduc setvDed(BigDecimal vDed) {
        this.vDed = vDed;
        return this;
    }
}