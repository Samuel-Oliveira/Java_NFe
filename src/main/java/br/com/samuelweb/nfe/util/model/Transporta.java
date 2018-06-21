package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIE;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.nfe.TUf;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

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

        if (StringUtils.isNotBlank(this.cnpjCpf.trim()) ||
                StringUtils.isNotBlank(this.xNome.trim()) ||
                StringUtils.isNotBlank(this.ie.trim()) ||
                StringUtils.isNotBlank(this.xEnder.trim()) ||
                StringUtils.isNotBlank(this.xMun.trim()) ||
                StringUtils.isNotBlank(this.uf.trim())) {

            if (StringUtils.isNotBlank(this.cnpjCpf.trim())) {
                if (this.cnpjCpf.length() > 11) {
                    transporta.setCNPJ(this.cnpjCpf);
                } else {
                    transporta.setCPF(this.cnpjCpf);
                }
            }

            transporta.setXNome(this.xNome);
            transporta.setIE(this.ie);
            transporta.setXEnder(this.xEnder);
            transporta.setXMun(this.xMun);
            transporta.setUF(TUf.fromValue(this.uf));
        }


        return transporta;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getxEnder() {
        return xEnder;
    }

    public void setxEnder(String xEnder) {
        this.xEnder = xEnder;
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
}
