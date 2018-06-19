package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaCst10 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 icms10 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
        icms10.setOrig(icms.getOrig().getValue().toString());
        icms10.setCST(icms.getCST().getValue());
        icms10.setModBC(icms.getModBC().getValue().toString());
        icms10.setVBC(icms.getvBC().toString());
        icms10.setPICMS(icms.getpICMS().toString());
        icms10.setVICMS(icms.getvICMS().toString());
        icms10.setPFCP(icms.getpFCP().toString());
        icms10.setVFCP(icms.getvFCP().toString());
        icms10.setModBCST(icms.getModBCST().getValue().toString());
        icms10.setPMVAST(icms.getpMVAST().toString());
        icms10.setPRedBCST(icms.getpRedBCST().toString());
        icms10.setVBCST(icms.getvBCST().toString());
        icms10.setPICMSST(icms.getpICMSST().toString());
        icms10.setVICMSST(icms.getvICMSST().toString());
        icms10.setVBCFCPST(icms.getvBCFCPST().toString());
        icms10.setPFCPST(icms.getpFCPST().toString());
        icms10.setVFCPST(icms.getvFCPST().toString());
        imposto.setICMS10(icms10);
    }
}
