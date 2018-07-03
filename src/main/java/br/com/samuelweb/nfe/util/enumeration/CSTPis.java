package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.PIS;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public enum CSTPis implements EnumNfeValue<String> {
    CST_01("01", new MontaPISAliq()),
    CST_02("02", new MontaPISAliq()),
    CST_03("03", new MontaPISQtde()),
    CST_04("04", new MontaPISNt()),
    CST_05("05", new MontaPISNt()),
    CST_06("06", new MontaPISNt()),
    CST_07("07", new MontaPISNt()),
    CST_08("08", new MontaPISNt()),
    CST_09("09", new MontaPISNt()),
    CST_49("49", new MontaPISOutr()),
    CST_50("50", new MontaPISOutr()),
    CST_51("51", new MontaPISOutr()),
    CST_52("52", new MontaPISOutr()),
    CST_53("53", new MontaPISOutr()),
    CST_54("54", new MontaPISOutr()),
    CST_55("55", new MontaPISOutr()),
    CST_56("56", new MontaPISOutr()),
    CST_60("60", new MontaPISOutr()),
    CST_61("61", new MontaPISOutr()),
    CST_62("62", new MontaPISOutr()),
    CST_63("63", new MontaPISOutr()),
    CST_64("64", new MontaPISOutr()),
    CST_65("65", new MontaPISOutr()),
    CST_66("66", new MontaPISOutr()),
    CST_67("67", new MontaPISOutr()),
    CST_70("70", new MontaPISOutr()),
    CST_71("71", new MontaPISOutr()),
    CST_72("72", new MontaPISOutr()),
    CST_73("73", new MontaPISOutr()),
    CST_74("74", new MontaPISOutr()),
    CST_75("75", new MontaPISOutr()),
    CST_98("98", new MontaPISOutr()),
    CST_99("99", new MontaPISOutr());

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
