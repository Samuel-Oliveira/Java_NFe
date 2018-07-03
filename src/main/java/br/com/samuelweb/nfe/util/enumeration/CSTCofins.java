package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.COFINS;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public enum CSTCofins implements EnumNfeValue<String> {

    CST_01("01", new MontaCOFINSAliq()),
    CST_02("02", new MontaCOFINSAliq()),
    CST_03("03", new MontaCOFINSQtde()),
    CST_04("04", new MontaCOFINSNt()),
    CST_05("05", new MontaCOFINSNt()),
    CST_06("06", new MontaCOFINSNt()),
    CST_07("07", new MontaCOFINSNt()),
    CST_08("08", new MontaCOFINSNt()),
    CST_09("09", new MontaCOFINSNt()),
    CST_49("49", new MontaCOFINSOutr()),
    CST_50("50", new MontaCOFINSOutr()),
    CST_51("51", new MontaCOFINSOutr()),
    CST_52("52", new MontaCOFINSOutr()),
    CST_53("53", new MontaCOFINSOutr()),
    CST_54("54", new MontaCOFINSOutr()),
    CST_55("55", new MontaCOFINSOutr()),
    CST_56("56", new MontaCOFINSOutr()),
    CST_60("60", new MontaCOFINSOutr()),
    CST_61("61", new MontaCOFINSOutr()),
    CST_62("62", new MontaCOFINSOutr()),
    CST_63("63", new MontaCOFINSOutr()),
    CST_64("64", new MontaCOFINSOutr()),
    CST_65("65", new MontaCOFINSOutr()),
    CST_66("66", new MontaCOFINSOutr()),
    CST_67("67", new MontaCOFINSOutr()),
    CST_70("70", new MontaCOFINSOutr()),
    CST_71("71", new MontaCOFINSOutr()),
    CST_72("72", new MontaCOFINSOutr()),
    CST_73("73", new MontaCOFINSOutr()),
    CST_74("74", new MontaCOFINSOutr()),
    CST_75("75", new MontaCOFINSOutr()),
    CST_98("98", new MontaCOFINSOutr()),
    CST_99("99", new MontaCOFINSOutr());

    private String value;
    private MontaImposto<TNFe.InfNFe.Det.Imposto.COFINS, COFINS> montaImposto;

    CSTCofins(String value, MontaImposto<TNFe.InfNFe.Det.Imposto.COFINS, COFINS> montaImposto) {
        this.value = value;
        this.montaImposto = montaImposto;
    }

    @Override
    public String getValue() {
        return value;
    }

    public MontaImposto<TNFe.InfNFe.Det.Imposto.COFINS, COFINS> getMontaImposto() {
        return montaImposto;
    }
}
