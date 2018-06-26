package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Det {

    //esse campo é controle interno, pois isso não tem validação
    private String nItem;

    @NfeObjeto(id = "I01", tag = "prod"
            , ocorrencias = 1, descricao = NfeConsts.DSC_PROD)
    private Prod prod;

    @NfeObjeto(id = "M01", tag = "imposto"
            , ocorrencias = 1, descricao = NfeConsts.DSC_IMPOSTO)
    private Imposto imposto;

    @NfeObjeto(id = "UA01", tag = "impostoDevol"
            , ocorrencias = 0, descricao = NfeConsts.DSC_IMPOSTO_DEVOL)
    private ImpostoDevol impostoDevol;

    @NfeCampo(tipo = String.class
            , id = "V01", tag = "infAdProd"
            , tamanhoMinimo = 1, tamanhoMaximo = 500, ocorrencias = 0
            , descricao = DfeConsts.DSC_INFADPROD)
    private String infAdProd;

    public TNFe.InfNFe.Det build() {
        TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        det.setNItem(this.getnItem());
        if (this.getProd() != null) {
            det.setProd(getProd().build());
        }
        if (this.getImposto() != null) {
            det.setImposto(this.getImposto().build());
        }
        if (this.getImpostoDevol() != null) {
            det.setImpostoDevol(this.getImpostoDevol().build());
        }
        det.setInfAdProd(this.getInfAdProd());
        return det;
    }

    public String getnItem() {
        return nItem;
    }

    public void setnItem(String nItem) {
        this.nItem = nItem;
    }

    public Prod getProd() {
        if (this.prod == null) {
            prod = new Prod();
        }
        return prod;
    }

    public void setProd(Prod prod) {
        this.prod = prod;
    }

    public Imposto getImposto() {
        if (imposto == null) {
            imposto = new Imposto();
        }
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public ImpostoDevol getImpostoDevol() {
        return impostoDevol;
    }

    public void setImpostoDevol(ImpostoDevol impostoDevol) {
        this.impostoDevol = impostoDevol;
    }

    public String getInfAdProd() {
        return infAdProd;
    }

    public void setInfAdProd(String infAdProd) {
        this.infAdProd = infAdProd;
    }
}
