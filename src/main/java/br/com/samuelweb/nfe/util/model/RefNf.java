package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarAAMM;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJ;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCodigoUf;
import br.com.samuelweb.nfe.util.validators.impl.ValidarModeloDocumentoRef;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class RefNf {

    @NfeCampo(tipo = Integer.class
        , id = "B15", tag = "cUF"
        , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
        , descricao = DfeConsts.DSC_UF, validadores = {ValidarCodigoUf.class})
    private Integer cuf;

    @NfeCampo(tipo = String.class
        , id = "B16", tag = "AAMM"
        , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 1
        , descricao = NfeConsts.DSC_AAMM, validadores = {ValidarAAMM.class}
    )
    private String aamm;

    @NfeCampo(tipo = String.class
        , id = "B17", tag = "CNPJ"
        , tamanhoMinimo = 11, tamanhoMaximo = 14, ocorrencias = 1
        , descricao = DfeConsts.DSC_CNPJ, validadores = {ValidarCNPJ.class}
    )
    private String cnpj;

    @NfeCampo(tipo = String.class
        , id = "B18", tag = "mod"
        , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
        , descricao = DfeConsts.DSC_MOD, validadores = {ValidarModeloDocumentoRef.class}
    )
    private String mod;

    @NfeCampo(tipo = Integer.class
        , id = "B19", tag = "serie"
        , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
        , descricao = DfeConsts.DSC_SERIE
    )
    private Integer serie;

    @NfeCampo(tipo = Integer.class
        , id = "B20", tag = "nNF"
        , tamanhoMinimo = 1, tamanhoMaximo = 9, ocorrencias = 1
        , descricao = DfeConsts.DSC_NNF)
    private Integer nnf;

    public TNFe.InfNFe.Ide.NFref.RefNF build(){
        TNFe.InfNFe.Ide.NFref.RefNF refNF = new TNFe.InfNFe.Ide.NFref.RefNF();
        refNF.setCUF(this.getCuf().toString());
        refNF.setAAMM(this.getAamm());
        refNF.setCNPJ(this.getCnpj());
        refNF.setMod(this.getMod());
        refNF.setSerie(this.getSerie().toString());
        refNF.setNNF(this.getNnf().toString());
        return refNF;
    }

    public Integer getCuf() {
        return cuf;
    }

    public void setCuf(Integer cuf) {
        this.cuf = cuf;
    }

    public String getAamm() {
        return aamm;
    }

    public void setAamm(String aamm) {
        this.aamm = aamm;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getNnf() {
        return nnf;
    }

    public void setNnf(Integer nnf) {
        this.nnf = nnf;
    }

    public void validarRegraNegocio(InfNFe infNFe) {
    }
}
