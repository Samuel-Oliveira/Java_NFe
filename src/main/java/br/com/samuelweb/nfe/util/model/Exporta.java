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

        if (StringUtils.isNotBlank(this.ufSaidaPais.trim() + this.xLocExporta.trim())){
            exporta.setUFSaidaPais(TUfEmi.fromValue(this.ufSaidaPais));
            exporta.setXLocExporta(this.xLocExporta);
            exporta.setXLocDespacho(this.xLocDespacho);
        }
        return exporta;
    }

    public String getUfSaidaPais() {
        return ufSaidaPais;
    }

    public void setUfSaidaPais(String ufSaidaPais) {
        this.ufSaidaPais = ufSaidaPais;
    }

    public String getxLocExporta() {
        return xLocExporta;
    }

    public void setxLocExporta(String xLocExporta) {
        this.xLocExporta = xLocExporta;
    }

    public String getxLocDespacho() {
        return xLocDespacho;
    }

    public void setxLocDespacho(String xLocDespacho) {
        this.xLocDespacho = xLocDespacho;
    }
}
