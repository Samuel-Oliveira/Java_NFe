package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.com.samuelweb.nfe.util.validators.impl.ValidarPais;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEndereco;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;

public class EnderDest {

    @NfeCampo(tipo = String.class
            , id = "E06", tag = "xLgr"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XLGR)
    private String xLgr;

    @NfeCampo(tipo = String.class
            , id = "E07", tag = "nro", valorDefault = "SN"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_NRO)
    private String nro;

    @NfeCampo(tipo = String.class
            , id = "E08", tag = "xCpl"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XCPL)
    private String xCpl;

    @NfeCampo(tipo = String.class
            , id = "E09", tag = "xBairro"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XBAIRRO)
    private String xBairro;

    @NfeCampo(tipo = Integer.class
            , id = "E10", tag = "cMun", validadores = {ValidarMunicipio.class}
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_CMUN)
    private Integer cMun;

    @NfeCampo(tipo = String.class
            , id = "E11", tag = "xMun"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XMUN)
    private String xMun;

    @NfeCampo(tipo = String.class
            , id = "E12", tag = "UF"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_UF)
    private String uf;

    @NfeCampo(tipo = Integer.class
            , id = "E13", tag = "CEP"
            , tamanhoMinimo = 8, tamanhoMaximo = 8, ocorrencias = 0
            , descricao = DfeConsts.DSC_CEP)
    private Integer cep;

    @NfeCampo(tipo = Integer.class
            , id = "E14", tag = "cPais", validadores = {ValidarPais.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 4, ocorrencias = 0
            , descricao = DfeConsts.DSC_CPAIS)
    private Integer cPais;

    @NfeCampo(tipo = String.class
            , id = "E15", tag = "xPais"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XPAIS)
    private String xPais;

    @NfeCampo(tipo = String.class
            , id = "E16", tag = "fone"
            , tamanhoMinimo = 6, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_FONE)
    private String fone;

    public TEndereco build() {
        TEndereco endereco = new TEndereco();
        endereco.setXLgr(this.xLgr);
        endereco.setNro(this.nro);
        endereco.setXCpl(this.xCpl);
        endereco.setXBairro(this.xBairro);
        if (this.cMun != null) {
            endereco.setCMun(this.cMun.toString());
        }
        endereco.setXMun(this.xMun);
        endereco.setUF(TUf.fromValue(this.uf));
        if (this.cep != null) {
            endereco.setCEP(this.cep.toString());
        }
        if (this.cPais != null) {
            endereco.setCPais(this.cPais.toString());
        }
        endereco.setXPais(this.xPais);
        endereco.setFone(this.fone);
        if (endereco.getXLgr() != null && !endereco.getXLgr().isEmpty()) {
            return endereco;
        }
        return null;
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

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}
