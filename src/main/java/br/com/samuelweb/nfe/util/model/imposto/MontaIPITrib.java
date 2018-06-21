package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.IPI;
import br.inf.portalfiscal.nfe.schema_4.nfe.TIpi;

public class MontaIPITrib implements MontaImposto<TIpi, IPI> {
    @Override
    public void build(TIpi imposto, IPI ipi) {
        TIpi.IPINT ipiNt = new TIpi.IPINT();
        if (ipi.getCST() != null) {
            ipiNt.setCST(ipi.getCST().getValue());
        }
        imposto.setIPINT(ipiNt);
    }
}
