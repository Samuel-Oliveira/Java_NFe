package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.COFINS;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public enum CSTCofins implements EnumNfeValue<String> {

    CST01("01", new MontaCOFINSAliq()),
    CST02("02", new MontaCOFINSAliq()),
    CST03("03", new MontaCOFINSQtde()),
    CST04("04", new MontaCOFINSNt()),
    CST05("05", new MontaCOFINSNt()),
    CST06("06", new MontaCOFINSNt()),
    CST07("07", new MontaCOFINSNt()),
    CST08("08", new MontaCOFINSNt()),
    CST09("09", new MontaCOFINSNt()),
    CST49("49", new MontaCOFINSOutr()),
    CST50("50", new MontaCOFINSOutr()),
    CST51("51", new MontaCOFINSOutr()),
    CST52("52", new MontaCOFINSOutr()),
    CST53("53", new MontaCOFINSOutr()),
    CST54("54", new MontaCOFINSOutr()),
    CST55("55", new MontaCOFINSOutr()),
    CST56("56", new MontaCOFINSOutr()),
    CST60("60", new MontaCOFINSOutr()),
    CST61("61", new MontaCOFINSOutr()),
    CST62("62", new MontaCOFINSOutr()),
    CST63("63", new MontaCOFINSOutr()),
    CST64("64", new MontaCOFINSOutr()),
    CST65("65", new MontaCOFINSOutr()),
    CST66("66", new MontaCOFINSOutr()),
    CST67("67", new MontaCOFINSOutr()),
    CST70("70", new MontaCOFINSOutr()),
    CST71("71", new MontaCOFINSOutr()),
    CST72("72", new MontaCOFINSOutr()),
    CST73("73", new MontaCOFINSOutr()),
    CST74("74", new MontaCOFINSOutr()),
    CST75("75", new MontaCOFINSOutr()),
    CST98("98", new MontaCOFINSOutr()),
    CST99("99", new MontaCOFINSOutr());

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
