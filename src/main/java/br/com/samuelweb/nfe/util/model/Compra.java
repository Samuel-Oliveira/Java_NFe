package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Compra {

    @NfeCampo(id = "ZB02", tag = "xNEmp"
            , tamanhoMinimo = 1, tamanhoMaximo = 22
            , descricao = NfeConsts.DSC_XNEMP)
    private String xnEmp;

    @NfeCampo(id = "ZB03", tag = "xPed"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , descricao = NfeConsts.DSC_XPED)
    private String xPed;

    @NfeCampo(id = "ZB04", tag = "xCont"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , descricao = NfeConsts.DSC_XCONT)
    private String xCont;


    public TNFe.InfNFe.Compra build() {
        TNFe.InfNFe.Compra compra = new TNFe.InfNFe.Compra();
        compra.setXNEmp(this.getXnEmp());
        compra.setXPed(this.getxPed());
        compra.setXCont(this.getxCont());
        return compra;
    }

    public String getXnEmp() {
        return xnEmp;
    }

    public void setXnEmp(String xnEmp) {
        this.xnEmp = xnEmp;
    }

    public String getxPed() {
        return xPed;
    }

    public void setxPed(String xPed) {
        this.xPed = xPed;
    }

    public String getxCont() {
        return xCont;
    }

    public void setxCont(String xCont) {
        this.xCont = xCont;
    }
}
