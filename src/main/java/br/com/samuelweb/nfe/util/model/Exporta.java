package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCodigoUf;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.nfe.TUfEmi;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

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
}
