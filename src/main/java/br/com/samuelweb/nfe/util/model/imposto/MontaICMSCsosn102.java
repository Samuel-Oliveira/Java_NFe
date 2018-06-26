package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class MontaICMSCsosn102 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 icmsSn102 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();
        if (icms.getOrig() != null) {
            icmsSn102.setOrig(icms.getOrig().toString());
        }
        if (icms.getCSOSN() != null) {
            icmsSn102.setCSOSN(icms.getCSOSN().getValue());
        }
        imposto.setICMSSN102(icmsSn102);
    }
}
