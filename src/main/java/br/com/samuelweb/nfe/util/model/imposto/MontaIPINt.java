package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.IPI;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi;

public class MontaIPINt implements MontaImposto<TIpi, IPI> {
    @Override
    public void build(TIpi imposto, IPI ipi) {

        TIpi.IPINT ipiNt = new TIpi.IPINT();
        if (ipi.getCst() != null) {
            ipiNt.setCST(ipi.getCst().getValue());
        }
        imposto.setIPINT(ipiNt);
    }
}
