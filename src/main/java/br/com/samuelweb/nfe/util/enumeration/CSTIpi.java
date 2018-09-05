package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.IPI;
import br.com.samuelweb.nfe.util.model.imposto.MontaIPINt;
import br.com.samuelweb.nfe.util.model.imposto.MontaIPITrib;
import br.com.samuelweb.nfe.util.model.imposto.MontaImposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi;

public enum CSTIpi implements EnumNfeValue<String> {

    CST_00("00", new MontaIPITrib()),
    CST_49("49", new MontaIPITrib()),
    CST_50("50", new MontaIPITrib()),
    CST_99("99", new MontaIPITrib()),
    CST_01("01", new MontaIPINt()),
    CST_02("02", new MontaIPINt()),
    CST_03("03", new MontaIPINt()),
    CST_04("04", new MontaIPINt()),
    CST_05("05", new MontaIPINt()),
    CST_51("51", new MontaIPINt()),
    CST_52("52", new MontaIPINt()),
    CST_53("53", new MontaIPINt()),
    CST_54("54", new MontaIPINt()),
    CST_55("55", new MontaIPINt());


    private String value;
    private MontaImposto<TIpi, IPI> montaImposto;

    CSTIpi(String value, MontaImposto<TIpi, IPI> montaImposto) {
        this.value = value;
        this.montaImposto = montaImposto;
    }

    @Override
    public String getValue() {
        return value;
    }

    public MontaImposto<TIpi, IPI> getMontaImposto() {
        return montaImposto;
    }
}
