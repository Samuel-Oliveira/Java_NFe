package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCsosn900 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 icmsSn900 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900();
        if (icms.getOrig() != null) {
            icmsSn900.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCSOSN() != null) {
            icmsSn900.setCSOSN(icms.getCSOSN().getValue());
        }
        if (icms.getModBC() != null) {
            icmsSn900.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getvBC() != null) {
            icmsSn900.setVBC(icms.getvBC().toString());
        }
        if (icms.getpRedBC() != null) {
            icmsSn900.setPRedBC(icms.getpRedBC().toString());
        }
        if (icms.getpICMS() != null) {
            icmsSn900.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icmsSn900.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getModBCST() != null) {
            icmsSn900.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icmsSn900.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icmsSn900.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icmsSn900.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icmsSn900.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icmsSn900.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icmsSn900.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icmsSn900.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icmsSn900.setVFCPST(icms.getvFCPST().toString());
        }
        if (icms.getpCredSN() != null) {
            icmsSn900.setPCredSN(icms.getpCredSN().toString());
        }
        if (icms.getvCredICMSSN() != null) {
            icmsSn900.setVCredICMSSN(icms.getvCredICMSSN().toString());
        }
        imposto.setICMSSN900(icmsSn900);
    }
}
