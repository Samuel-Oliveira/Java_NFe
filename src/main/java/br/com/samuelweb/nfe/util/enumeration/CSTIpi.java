package br.com.samuelweb.nfe.util.enumeration;

import br.com.samuelweb.nfe.util.model.IPI;
import br.com.samuelweb.nfe.util.model.imposto.*;
import br.inf.portalfiscal.nfe.schema_4.nfe.TIpi;

public enum CSTIpi implements EnumNfeValue<String> {

    CST00("00", new MontaIPITrib()),
    CST49("49", new MontaIPITrib()),
    CST50("50", new MontaIPITrib()),
    CST99("99", new MontaIPITrib()),
    CST01("01", new MontaIPINt()),
    CST02("02", new MontaIPINt()),
    CST03("03", new MontaIPINt()),
    CST04("04", new MontaIPINt()),
    CST05("05", new MontaIPINt()),
    CST51("51", new MontaIPINt()),
    CST52("52", new MontaIPINt()),
    CST53("53", new MontaIPINt()),
    CST54("54", new MontaIPINt()),
    CST55("55", new MontaIPINt());


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
