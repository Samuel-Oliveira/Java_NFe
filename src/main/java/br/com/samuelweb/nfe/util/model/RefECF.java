package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class RefECF {

    @NfeCampo(tipo = String.class
            , id = "B20k", tag = "mod"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_MOD)
    private String mod;

    @NfeCampo(tipo = Integer.class
            , id = "B20l", tag = "nECF"
            , tamanhoMinimo = 3, tamanhoMaximo = 3, ocorrencias = 1
            , descricao =  NfeConsts.DSC_NECF)
    private Integer necf;

    @NfeCampo(tipo = Integer.class
            , id = "B20m", tag = "nCOO"
            , tamanhoMinimo = 6, tamanhoMaximo = 6, ocorrencias = 1
            , descricao =  NfeConsts.DSC_NCOO)
    private Integer ncoo;


    public TNFe.InfNFe.Ide.NFref.RefECF build() {
        TNFe.InfNFe.Ide.NFref.RefECF refECF = new TNFe.InfNFe.Ide.NFref.RefECF();
        refECF.setMod(this.mod);
        refECF.setNECF(this.necf.toString());
        refECF.setNCOO(this.ncoo.toString());
        return refECF;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public Integer getNecf() {
        return necf;
    }

    public void setNecf(Integer necf) {
        this.necf = necf;
    }

    public Integer getNcoo() {
        return ncoo;
    }

    public void setNcoo(Integer ncoo) {
        this.ncoo = ncoo;
    }
}
