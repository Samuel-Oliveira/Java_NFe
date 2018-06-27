package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIE;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;
import org.apache.commons.lang3.StringUtils;

public class Transporta {

    @NfeCampo(id = "X04", tag = "CNPJ", ocorrencias = 0, descricao = NfeConsts.DSC_CNPJFAB)
    private String cnpjCpf;

    @NfeCampo(id = "X06", tag = "xNome"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_XNOME)
    private String xNome;

    @NfeCampo(id = "X07", tag = "IE"
            , tamanhoMinimo = 2, tamanhoMaximo = 14
            , validadores = {ValidarIE.class}
            , ocorrencias = 0, descricao = NfeConsts.DSC_IE)
    private String ie;

    @NfeCampo(id = "X08", tag = "xEnder"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_XENDER)
    private String xEnder;

    @NfeCampo(id = "X09", tag = "xMun"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_XMUN)
    private String xMun;

    @NfeCampo(id = "X10", tag = "UF"
            , tamanhoMinimo = 1, tamanhoMaximo = 2
            , ocorrencias = 0, descricao = NfeConsts.DSC_UF)
    private String uf;


    public TNFe.InfNFe.Transp.Transporta build() {
        TNFe.InfNFe.Transp.Transporta transporta = new TNFe.InfNFe.Transp.Transporta();

        if (StringUtils.isNotBlank(this.getCnpjCpf().trim()) ||
                StringUtils.isNotBlank(this.getxNome().trim()) ||
                StringUtils.isNotBlank(this.getIe().trim()) ||
                StringUtils.isNotBlank(this.getxEnder().trim()) ||
                StringUtils.isNotBlank(this.getxMun().trim()) ||
                StringUtils.isNotBlank(this.getUf().trim())) {

            if (StringUtils.isNotBlank(this.getCnpjCpf().trim())) {
                if (this.getCnpjCpf().length() > 11) {
                    transporta.setCNPJ(this.getCnpjCpf());
                } else {
                    transporta.setCPF(this.getCnpjCpf());
                }
            }

            transporta.setXNome(this.getxNome());
            transporta.setIE(this.getIe());
            transporta.setXEnder(this.getxEnder());
            transporta.setXMun(this.getxMun());
            transporta.setUF(TUf.fromValue(this.getUf()));
        }


        return transporta;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public String getxNome() {
        return xNome;
    }

    public String getIe() {
        return ie;
    }

    public String getxEnder() {
        return xEnder;
    }

    public String getxMun() {
        return xMun;
    }

    public String getUf() {
        return uf;
    }

    public Transporta setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
        return this;
    }

    public Transporta setxNome(String xNome) {
        this.xNome = xNome;
        return this;
    }

    public Transporta setIe(String ie) {
        this.ie = ie;
        return this;
    }

    public Transporta setxEnder(String xEnder) {
        this.xEnder = xEnder;
        return this;
    }

    public Transporta setxMun(String xMun) {
        this.xMun = xMun;
        return this;
    }

    public Transporta setUf(String uf) {
        this.uf = uf;
        return this;
    }
}