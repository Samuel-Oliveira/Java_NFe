package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TLocal;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;

public class Retirada {

    @NfeCampo(tipo = String.class
            , id = "F02", tag = "CNPJ/CPF"
            , tamanhoMinimo = 0, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpjCpf;

    @NfeCampo(tipo = String.class
            , id = "F03", tag = "xLgr"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XLGR)
    private String xLgr;

    @NfeCampo(tipo = String.class
            , id = "F04", tag = "nro", valorDefault = "SN"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_NRO)
    private String nro;

    @NfeCampo(tipo = String.class
            , id = "F09", tag = "UF"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_UF)
    private String uf;

    @NfeCampo(tipo = String.class
            , id = "F05", tag = "xCpl"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XCPL)
    private String xCpl;

    @NfeCampo(tipo = String.class
            , id = "F06", tag = "xBairro"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XBAIRRO)
    private String xBairro;

    @NfeCampo(tipo = Integer.class
            , id = "F07", tag = "cMun", validadores = {ValidarMunicipio.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = DfeConsts.DSC_CMUN)
    private Integer cMun;

    @NfeCampo(tipo = String.class
            , id = "F08", tag = "xMun"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XMUN)
    private String xMun;

    public TLocal build() {
        TLocal retirada = new TLocal();
        if (this.getCnpjCpf() != null && this.getCnpjCpf().length() > 11) {
            retirada.setCNPJ(this.getCnpjCpf());
        } else {
            retirada.setCPF(this.getCnpjCpf());
        }
        retirada.setXLgr(this.getxLgr());
        retirada.setNro(this.getNro());
        retirada.setXCpl(this.getxCpl());
        retirada.setXBairro(this.getxBairro());
        if (this.getcMun() != null) {
            retirada.setCMun(this.getcMun().toString());
        }
        retirada.setXMun(this.getxMun());
        if (this.getUf() != null) {
            retirada.setUF(TUf.fromValue(this.getUf()));
        }
        if (retirada.getXLgr() != null && !retirada.getXLgr().isEmpty()) {
            return retirada;
        }
        return null;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public String getxLgr() {
        return xLgr;
    }

    public String getNro() {
        return nro;
    }

    public String getUf() {
        return uf;
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

    public Retirada setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
        return this;
    }

    public Retirada setxLgr(String xLgr) {
        this.xLgr = xLgr;
        return this;
    }

    public Retirada setNro(String nro) {
        this.nro = nro;
        return this;
    }

    public Retirada setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public Retirada setxCpl(String xCpl) {
        this.xCpl = xCpl;
        return this;
    }

    public Retirada setxBairro(String xBairro) {
        this.xBairro = xBairro;
        return this;
    }

    public Retirada setcMun(Integer cMun) {
        this.cMun = cMun;
        return this;
    }

    public Retirada setxMun(String xMun) {
        this.xMun = xMun;
        return this;
    }
}