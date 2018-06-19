package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.com.samuelweb.nfe.util.model.imposto.MontaCst00;
import br.com.samuelweb.nfe.util.model.imposto.MontaCst10;
import br.com.samuelweb.nfe.util.model.imposto.MontaImposto;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public enum CSTIcms implements EnumNfeValue<String> {

    CST_00("00", new MontaCst00()),
    CST_10("10", new MontaCst10())/*,
    CST_20("20", new MontaCst20()),
    CST_30("30", new MontaCst30()),
    CST_40("40", new MontaCst40()),
    CST_41("41", new MontaCst41()),
    CST_45("45", new MontaCst45()),
    CST_50("50", new MontaCst50()),
    CST_51("51", new MontaCst51()),
    CST_60("60", new MontaCst60()),
    CST_70("70", new MontaCst70()),
    CST_80("80", new MontaCst80()),
    CST_81("81", new MontaCst81()),
    CST_90("90", new MontaCst90())*/;

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
