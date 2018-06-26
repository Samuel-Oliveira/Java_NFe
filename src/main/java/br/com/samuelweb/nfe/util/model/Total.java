package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Total {

    @NfeObjeto(id = "W02", tag = "ICMSTot", ocorrencias = 0, descricao = NfeConsts.DSC_ICMSTOT)
    private ICMSTot icmsTot;

    @NfeObjeto(id = "W17", tag = "ISSQNtot", ocorrencias = 0, descricao = NfeConsts.DSC_ISSQNTOT)
    private ISSQNtot issqNtot;

    @NfeObjeto(id = "W23", tag = "retTrib", ocorrencias = 0, descricao = NfeConsts.DSC_RETTRIB)
    private RetTrib retTrib;

    public TNFe.InfNFe.Total build() {
        TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();

        if (this.getIcmsTot() != null) {
            total.setICMSTot(this.getIcmsTot().build());
        }
        if (this.getIssqNtot() != null) {
            total.setISSQNtot(this.getIssqNtot().build());
        }
        if (this.getRetTrib() != null) {
            total.setRetTrib(this.getRetTrib().build());
        }
        return total;

    }

    public ICMSTot getIcmsTot() {
        return icmsTot;
    }

    public void setIcmsTot(ICMSTot icmsTot) {
        this.icmsTot = icmsTot;
    }

    public ISSQNtot getIssqNtot() {
        return issqNtot;
    }

    public void setIssqNtot(ISSQNtot issqNtot) {
        this.issqNtot = issqNtot;
    }

    public RetTrib getRetTrib() {
        return retTrib;
    }

    public void setRetTrib(RetTrib retTrib) {
        this.retTrib = retTrib;
    }
}
