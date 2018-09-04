package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public enum CSOSNIcms implements EnumNfeValue<String> {
    CSOSN_101("101", new MontaICMSCsosn101()),
    CSOSN_102("102", new MontaICMSCsosn102()),
    CSOSN_103("103", new MontaICMSCsosn102()),
    CSOSN_201("201", new MontaICMSCsosn201()),
    CSOSN_202("202", new MontaICMSCsosn202()),
    CSOSN_203("203", new MontaICMSCsosn202()),
    CSOSN_300("300", new MontaICMSCsosn102()),
    CSOSN_400("400", new MontaICMSCsosn102()),
    CSOSN_500("500", new MontaICMSCsosn500()),
    CSOSN_900("900", new MontaICMSCsosn900());

    private String value;
    private MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> montaImposto;

    CSOSNIcms(String value, MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> montaImposto) {
        this.value = value;
        this.montaImposto = montaImposto;
    }

    @Override
    public String getValue() {
        return value;
    }

    public MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> getMontaImposto() {
        return montaImposto;
    }
}
