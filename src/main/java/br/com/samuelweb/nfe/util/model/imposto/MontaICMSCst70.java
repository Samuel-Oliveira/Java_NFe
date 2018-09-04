package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class MontaICMSCst70 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 icms70 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
        if (icms.getOrig() != null) {
            icms70.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icms70.setCST(icms.getCST().getValue());
        }
        if (icms.getModBC() != null) {
            icms70.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getpRedBC() != null) {
            icms70.setPRedBC(icms.getpRedBC().toString());
        }
        if (icms.getvBC() != null) {
            icms70.setVBC(icms.getvBC().toString());
        }
        if (icms.getpICMS() != null) {
            icms70.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icms70.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getvBCFCP() != null) {
            icms70.setVBCFCP(icms.getvBCFCP().toString());
        }
        if (icms.getpFCP() != null) {
            icms70.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms70.setVFCP(icms.getvFCP().toString());
        }
        if (icms.getModBCST() != null) {
            icms70.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icms70.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icms70.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icms70.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icms70.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icms70.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icms70.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icms70.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icms70.setVFCPST(icms.getvFCPST().toString());
        }
        if (icms.getvICMSDeson() != null) {
            icms70.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms70.setMotDesICMS(icms.getMotDesICMS().toString());
        }
        imposto.setICMS70(icms70); 
    }
}
