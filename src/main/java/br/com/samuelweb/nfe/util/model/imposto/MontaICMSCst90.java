package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst90 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 icms90 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
        if (icms.getOrig() != null) {
            icms90.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms90.setCST(icms.getCST().getValue());
        }
        if (icms.getModBC() != null) {
            icms90.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getvBC() != null) {
            icms90.setVBC(icms.getvBC().toString());
        }
        if (icms.getpRedBC() != null) {
            icms90.setPRedBC(icms.getpRedBC().toString());
        }
        if (icms.getpICMS() != null) {
            icms90.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icms90.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getvBCFCP() != null) {
            icms90.setVBCFCP(icms.getvBCFCP().toString());
        }
        if (icms.getpFCP() != null) {
            icms90.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms90.setVFCP(icms.getvFCP().toString());
        }
        if (icms.getModBCST() != null) {
            icms90.setModBCST(icms.getModBCST().toString());
        }
        if (icms.getpMVAST() != null) {
            icms90.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icms90.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icms90.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icms90.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icms90.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icms90.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icms90.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icms90.setVFCPST(icms.getvFCPST().toString());
        }
        if (icms.getvICMSDeson() != null) {
            icms90.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms90.setMotDesICMS(icms.getMotDesICMS().toString());
        }
        imposto.setICMS90(icms90);
    }
}
