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
        enderEmi.setXLgr(this.xLgr);
        enderEmi.setNro(this.nro);
        enderEmi.setXCpl(this.xCpl);
        enderEmi.setXBairro(this.xBairro);
        if (this.cMun != null) {
            enderEmi.setCMun(this.cMun.toString());
        }
        enderEmi.setXMun(this.xMun);
        if (this.uf != null) {
            enderEmi.setUF(TUfEmi.fromValue(this.uf));
        }
        if (this.cep != null) {
            enderEmi.setCEP(this.cep.toString());
        }
        if (this.cPais != null) {
            enderEmi.setCPais(this.cPais.toString());
        }
        enderEmi.setXPais(this.xPais);
        if (this.fone != null) {
            enderEmi.setFone(this.fone.toString());
        }
        return enderEmi;
    }

    public String getxLgr() {
        return xLgr;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getxCpl() {
        return xCpl;
    }

    public void setxCpl(String xCpl) {
        this.xCpl = xCpl;
    }

    public String getxBairro() {
        return xBairro;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public Integer getcMun() {
        return cMun;
    }

    public void setcMun(Integer cMun) {
        this.cMun = cMun;
    }

    public String getxMun() {
        return xMun;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Integer getcPais() {
        return cPais;
    }

    public void setcPais(Integer cPais) {
        this.cPais = cPais;
    }

    public String getxPais() {
        return xPais;
    }

    public void setxPais(String xPais) {
        this.xPais = xPais;
    }

    public Integer getFone() {
        return fone;
    }

    public void setFone(Integer fone) {
        this.fone = fone;
    }
}
