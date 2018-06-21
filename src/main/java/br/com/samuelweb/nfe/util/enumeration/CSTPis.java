package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.PIS;
import br.com.samuelweb.nfe.util.model.imposto.MontaImposto;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public enum CSTPis implements EnumNfeValue<String> {
    PIS01("01", new MontaPISAliq()),
    PIS02("02", new MontaPISAliq()),
    PIS03("03", new MontaPISQtde()),
    PIS04("04", new MontaPISNt()),
    PIS05("05", new MontaPISNt()),
    PIS06("06", new MontaPISNt()),
    PIS07("07", new MontaPISNt()),
    PIS08("08", new MontaPISNt()),
    PIS09("09", new MontaPISNt()),
    PIS49("49", new MontaPISOutr()),
    PIS50("50", new MontaPISOutr()),
    PIS51("51", new MontaPISOutr()),
    PIS52("52", new MontaPISOutr()),
    PIS53("53", new MontaPISOutr()),
    PIS54("54", new MontaPISOutr()),
    PIS55("55", new MontaPISOutr()),
    PIS56("56", new MontaPISOutr()),
    PIS60("60", new MontaPISOutr()),
    PIS61("61", new MontaPISOutr()),
    PIS62("62", new MontaPISOutr()),
    PIS63("63", new MontaPISOutr()),
    PIS64("64", new MontaPISOutr()),
    PIS65("65", new MontaPISOutr()),
    PIS66("66", new MontaPISOutr()),
    PIS67("67", new MontaPISOutr()),
    PIS70("70", new MontaPISOutr()),
    PIS71("71", new MontaPISOutr()),
    PIS72("72", new MontaPISOutr()),
    PIS73("73", new MontaPISOutr()),
    PIS74("74", new MontaPISOutr()),
    PIS75("75", new MontaPISOutr()),
    PIS98("98", new MontaPISOutr()),
    PIS99("99", new MontaPISOutr());

    private String value;
    private MontaImposto<TNFe.InfNFe.Det.Imposto.PIS, PIS> montaImposto;

    CSTPis(String value, MontaImposto<TNFe.InfNFe.Det.Imposto.PIS, PIS> montaImposto) {
        this.value = value;
        this.montaImposto = montaImposto;
    }

    @Override
    public String getValue() {
        return value;
    }

    public MontaImposto<TNFe.InfNFe.Det.Imposto.PIS, PIS> getMontaImposto() {
        return montaImposto;
    }
}
