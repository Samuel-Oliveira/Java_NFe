package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TLocal;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;

public class Entrega {

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
    private String cMun;

    @NfeCampo(tipo = String.class
            , id = "F08", tag = "xMun"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XMUN)
    private String xMun;

    public TLocal build() {
        TLocal entrega = new TLocal();
        if (this.getCnpjCpf() != null && this.getCnpjCpf().length() > 11) {
            entrega.setCNPJ(this.getCnpjCpf());
        } else {
            entrega.setCPF(this.getCnpjCpf());
        }
        entrega.setXLgr(this.getxLgr());
        entrega.setNro(this.getNro());
        entrega.setXCpl(this.getxCpl());
        entrega.setXBairro(this.getxBairro());
        entrega.setCMun(this.getcMun());
        entrega.setXMun(this.getxMun());
        entrega.setUF(TUf.fromValue(this.getUf()));
        if (entrega.getXLgr() != null && !entrega.getXLgr().isEmpty()) {
            return entrega;
        }
        return null;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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

    public String getcMun() {
        return cMun;
    }

    public void setcMun(String cMun) {
        this.cMun = cMun;
    }

    public String getxMun() {
        return xMun;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }
}
