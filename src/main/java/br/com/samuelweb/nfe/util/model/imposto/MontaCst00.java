package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaCst00 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
        icms00.setOrig(icms.getOrig().getValue().toString());
        icms00.setCST(icms.getCST().getValue());
        icms00.setModBC(icms.getModBC().getValue().toString());
        icms00.setVBC(icms.getvBC().toString());
        icms00.setPICMS(icms.getpICMS().toString());
        icms00.setVICMS(icms.getvICMS().toString());
        icms00.setPFCP(icms.getpFCP().toString());
        icms00.setVFCP(icms.getvFCP().toString());
        imposto.setICMS00(icms00);
    }

}
