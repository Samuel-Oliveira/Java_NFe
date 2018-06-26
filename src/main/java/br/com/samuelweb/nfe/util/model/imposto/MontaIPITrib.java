package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.IPI;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi;

import java.math.BigDecimal;

public class MontaIPITrib implements MontaImposto<TIpi, IPI> {
    @Override
    public void build(TIpi imposto, IPI ipi) {
        br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi.IPITrib ipiTrib = new br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi.IPITrib();
        if (ipi.getCst() != null) {
            ipiTrib.setCST(ipi.getCst().getValue());
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
