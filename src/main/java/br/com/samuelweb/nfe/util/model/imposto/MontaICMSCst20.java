package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst20 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 icms20 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS20();
        if (icms.getOrig() != null) {
            icms20.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms20.setCST(icms.getCST().getValue());
        }
        if (icms.getModBC() != null) {
            icms20.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getvBC() != null) {
            icms20.setVBC(icms.getvBC().toString());
        }
        if (icms.getpICMS() != null) {
            icms20.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icms20.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getpRedBC() != null) {
            icms20.setPRedBC(icms.getpRedBC().toString());
        }
        if (icms.getvBCFCP() != null) {
            icms20.setVBCFCP(icms.getvBCFCP().toString());
        }
        if (icms.getpFCP() != null) {
            icms20.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms20.setVFCP(icms.getvFCP().toString());
        }
        if (icms.getvICMSDeson() != null) {
            icms20.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms20.setMotDesICMS(icms.getMotDesICMS().getValue().toString());
        }
        imposto.setICMS20(icms20);
    }
}
