package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.PIS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaPISNt implements MontaImposto<TNFe.InfNFe.Det.Imposto.PIS, PIS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.PIS imposto, PIS pis) {
        TNFe.InfNFe.Det.Imposto.PIS.PISNT pisnt = new TNFe.InfNFe.Det.Imposto.PIS.PISNT();
        if (pis.getCST() != null) {
            pisnt.setCST(pis.getCST().getValue());
        }
        imposto.setPISNT(pisnt);
    }
}
