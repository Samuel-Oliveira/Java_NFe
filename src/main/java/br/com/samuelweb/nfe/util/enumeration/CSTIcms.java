package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public enum CSTIcms implements EnumNfeValue<String> {

    CST_00("00", new MontaICMSCst00()),
    CST_10("10", new MontaICMSCst10()),
    CST_20("20", new MontaICMSCst20()),
    CST_30("30", new MontaICMSCst30()),
    CST_40("40", new MontaICMSCst40()),
    CST_41("41", new MontaICMSCst40()),
    CST_50("50", new MontaICMSCst40()),
    CST_51("51", new MontaICMSCst51()),
    CST_60("60", new MontaICMSCst60()),
    CST_70("70", new MontaICMSCst70()),
    CST_90("90", new MontaICMSCst90());

    private String value;
    private MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> montaImposto;

    CSTIcms(String value, MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> montaImposto) {
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
