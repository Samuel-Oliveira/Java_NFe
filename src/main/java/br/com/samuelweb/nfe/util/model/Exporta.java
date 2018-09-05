package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUfEmi;
import org.apache.commons.lang3.StringUtils;

public class Exporta {


    @NfeCampo(id = "ZA02", tag = "UFSaidaPais", tamanhoMinimo = 2, tamanhoMaximo = 2
            , ocorrencias = 1,  descricao = NfeConsts.DSC_UFEMBARQ)
    private String ufSaidaPais;

    @NfeCampo(id = "ZA03", tag = "xLocExporta ", tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 1, descricao = NfeConsts.DSC_XLOCEMBARQ)
    private String xLocExporta;

    @NfeCampo(id = "ZA04", tag = "xLocDespacho",tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_XLOCDESP)
    private String xLocDespacho;

    public TNFe.InfNFe.Exporta build() {
        TNFe.InfNFe.Exporta exporta = new TNFe.InfNFe.Exporta();

        if (StringUtils.isNotBlank(this.getUfSaidaPais().trim() + this.getxLocExporta().trim())){
            exporta.setUFSaidaPais(TUfEmi.fromValue(this.getUfSaidaPais()));
            exporta.setXLocExporta(this.getxLocExporta());
            exporta.setXLocDespacho(this.getxLocDespacho());
        }
        return exporta;
    }

    public String getUfSaidaPais() {
        return ufSaidaPais;
    }

    public String getxLocExporta() {
        return xLocExporta;
    }

    public String getxLocDespacho() {
        return xLocDespacho;
    }

    public Exporta setUfSaidaPais(String ufSaidaPais) {
        this.ufSaidaPais = ufSaidaPais;
        return this;
    }

    public Exporta setxLocExporta(String xLocExporta) {
        this.xLocExporta = xLocExporta;
        return this;
    }

    public Exporta setxLocDespacho(String xLocDespacho) {
        this.xLocDespacho = xLocDespacho;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}