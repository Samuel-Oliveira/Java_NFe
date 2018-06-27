package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnderEmi;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUfEmi;

public class EnderEmi {

    @NfeCampo(tipo = String.class
            , id = "C06", tag = "xLgr"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XLGR)
    private String xLgr;

    @NfeCampo(tipo = String.class
            , id = "C07", tag = "nro"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_NRO)
    private String nro;

    @NfeCampo(tipo = String.class
            , id = "C08", tag = "xCpl"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XCPL)
    private String xCpl;

    @NfeCampo(tipo = String.class
            , id = "C09", tag = "xBairro"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XBAIRRO)
    private String xBairro;

    @NfeCampo(tipo = Integer.class
            , id = "C10", tag = "cMun", validadores = {ValidarMunicipio.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = DfeConsts.DSC_CMUN)
    private Integer cMun;

    @NfeCampo(tipo = String.class
            , id = "C11", tag = "xMun"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XMUN)
    private String xMun;

    @NfeCampo(tipo = String.class
            , id = "C12", tag = "UF"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_UF)
    private String uf;

    @NfeCampo(tipo = Integer.class
            , id = "C13", tag = "CEP", valorDefault = "00000000"
            , tamanhoMinimo = 8, tamanhoMaximo = 8, ocorrencias = 1
            , descricao = DfeConsts.DSC_CEP)
    private Integer cep;

    @NfeCampo(tipo = Integer.class
            , id = "C14", tag = "cPais", valorDefault = "1058"
            , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 0
            , descricao = DfeConsts.DSC_CPAIS)
    private Integer cPais;

    @NfeCampo(tipo = String.class
            , id = "C15", tag = "xPais", valorDefault = "BRASIL"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XPAIS)
    private String xPais;

    @NfeCampo(tipo = Integer.class
            , id = "C16", tag = "fone"
            , tamanhoMinimo = 6, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_FONE)
    private Integer fone;

    public TEnderEmi build() {
        TEnderEmi enderEmi = new TEnderEmi();
        enderEmi.setXLgr(this.getxLgr());
        enderEmi.setNro(this.getNro());
        enderEmi.setXCpl(this.getxCpl());
        enderEmi.setXBairro(this.getxBairro());
        if (this.getcMun() != null) {
            enderEmi.setCMun(this.getcMun().toString());
        }
        enderEmi.setXMun(this.getxMun());
        if (this.getUf() != null) {
            enderEmi.setUF(TUfEmi.fromValue(this.getUf()));
        }
        if (this.getCep() != null) {
            enderEmi.setCEP(this.getCep().toString());
        }
        if (this.getcPais() != null) {
            enderEmi.setCPais(this.getcPais().toString());
        }
        enderEmi.setXPais(this.getxPais());
        if (this.getFone() != null) {
            enderEmi.setFone(this.getFone().toString());
        }
        return enderEmi;
    }

    public String getxLgr() {
        return xLgr;
    }

    public String getNro() {
        return nro;
    }

    public String getxCpl() {
        return xCpl;
    }

    public String getxBairro() {
        return xBairro;
    }

    public Integer getcMun() {
        return cMun;
    }

    public String getxMun() {
        return xMun;
    }

    public String getUf() {
        return uf;
    }

    public Integer getCep() {
        return cep;
    }

    public Integer getcPais() {
        return cPais;
    }

    public String getxPais() {
        return xPais;
    }

    public EnderEmi setxLgr(String xLgr) {
        this.xLgr = xLgr;
        return this;
    }

    public EnderEmi setNro(String nro) {
        this.nro = nro;
        return this;
    }

    public EnderEmi setxCpl(String xCpl) {
        this.xCpl = xCpl;
        return this;
    }

    public EnderEmi setxBairro(String xBairro) {
        this.xBairro = xBairro;
        return this;
    }

    public EnderEmi setcMun(Integer cMun) {
        this.cMun = cMun;
        return this;
    }

    public EnderEmi setxMun(String xMun) {
        this.xMun = xMun;
        return this;
    }

    public EnderEmi setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public EnderEmi setCep(Integer cep) {
        this.cep = cep;
        return this;
    }

    public EnderEmi setcPais(Integer cPais) {
        this.cPais = cPais;
        return this;
    }

    public EnderEmi setxPais(String xPais) {
        this.xPais = xPais;
        return this;
    }

    public EnderEmi setFone(Integer fone) {
        this.fone = fone;
        return this;
    }

    public Integer getFone() {
        return fone;
    }
}