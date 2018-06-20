package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst40 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 icms40 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
        if (icms.getOrig() != null) {
            icms40.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms40.setCST(icms.getCST().getValue());
        }
        if (icms.getvICMSDeson() != null) {
            icms40.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms40.setMotDesICMS(icms.getMotDesICMS().getValue().toString());
        }
        imposto.setICMS40(icms40);
    }
}
