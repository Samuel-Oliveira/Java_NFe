package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst10 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 icms10 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
        if (icms.getOrig() != null) {
            icms10.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms10.setCST(icms.getCST().getValue());
        }
        if (icms.getModBC() != null) {
            icms10.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getvBC() != null) {
            icms10.setVBC(icms.getvBC().toString());
        }
        if (icms.getpICMS() != null) {
            icms10.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icms10.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getpFCP() != null) {
            icms10.setPFCP(icms.getpFCP().toString());
        }
        if (icms.getvFCP() != null) {
            icms10.setVFCP(icms.getvFCP().toString());
        }
        if (icms.getModBCST() != null) {
            icms10.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icms10.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icms10.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icms10.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icms10.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icms10.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icms10.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icms10.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icms10.setVFCPST(icms.getvFCPST().toString());
        }
        imposto.setICMS10(icms10);
    }
}
