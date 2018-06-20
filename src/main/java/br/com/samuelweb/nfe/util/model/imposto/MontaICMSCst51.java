package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst51 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 icms51 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS51();
        if (icms.getOrig() != null) {
            icms51.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms51.setCST(icms.getCST().getValue());
        }
        if (icms.getModBC() != null) {
            icms51.setModBC(icms.getModBC().toString());
        }
        if (icms.getpRedBC() != null) {
            icms51.setPRedBC(icms.getpRedBC().toString());
        }
        if (icms.getvBC() != null) {
            icms51.setVBC(icms.getvBC().toString());
        }
        if (icms.getpICMS() != null) {
            icms51.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMSOp() != null) {
            icms51.setVICMSOp(icms.getvICMSOp().toString());
        }
        if (icms.getpDif() != null) {
            icms51.setPDif(icms.getpDif().toString());
        }
        if (icms.getvICMSDif() != null) {
            icms51.setVICMSDif(icms.getvICMSDif().toString());
        }
        if (icms.getvICMS() != null) {
            icms51.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getvBCFCP() != null) {
            icms51.setVBCFCP(icms.getvBCFCP().toString());
        }
        if (icms.getpFCP() != null) {
            icms51.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms51.setVFCP(icms.getvFCP().toString());
        }
        imposto.setICMS51(icms51);
    }
}
