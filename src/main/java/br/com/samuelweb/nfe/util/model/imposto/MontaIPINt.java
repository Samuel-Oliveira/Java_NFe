package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.IPI;
import br.inf.portalfiscal.nfe.schema_4.nfe.TIpi;

import java.math.BigDecimal;

public class MontaIPINt implements MontaImposto<TIpi, IPI> {
    @Override
    public void build(TIpi imposto, IPI ipi) {
        TIpi.IPITrib ipiTrib = new TIpi.IPITrib();
        if (ipi.getCST() != null) {
            ipiTrib.setCST(ipi.getCST().getValue());
        }
        if (ipi.getvBC() != null) {
            ipiTrib.setVBC(ipi.getvBC().toString());
        }
        if (ipi.getqUnid() != null && ipi.getvUnid() != null
                && ipi.getqUnid().add(ipi.getvUnid()).compareTo(BigDecimal.ZERO) > 0) {
            ipiTrib.setQUnid(ipi.getqUnid().toString());
            ipiTrib.setVUnid(ipi.getvUnid().toString());
        } else {
            if (ipi.getvBC() != null) {
                ipiTrib.setVBC(ipi.getvBC().toString());
            }
            if (ipi.getpIPI() != null) {
                ipiTrib.setPIPI(ipi.getpIPI().toString());
            }
        }
        if (ipi.getvIPI() != null) {
            ipiTrib.setVIPI(ipi.getvIPI().toString());
        }
        imposto.setIPITrib(ipiTrib);
    }
}
