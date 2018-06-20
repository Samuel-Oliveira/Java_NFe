package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst00 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
        if (icms.getOrig() != null) {
            icms00.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms00.setCST(icms.getCST().getValue());
        }
        if (icms.getModBC() != null) {
            icms00.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getvBC() != null) {
            icms00.setVBC(icms.getvBC().toString());
        }
        if (icms.getpICMS() != null) {
            icms00.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icms00.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getpFCP() != null) {
            icms00.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms00.setVFCP(icms.getvFCP().toString());
        }
        imposto.setICMS00(icms00);
    }

}
