package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class MontaICMSCsosn201 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 icmsSn201 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201();
        if (icms.getOrig() != null) {
            icmsSn201.setOrig(icms.getOrig().toString());
        }
        if (icms.getCSOSN() != null) {
            icmsSn201.setCSOSN(icms.getCSOSN().getValue());
        }
        if (icms.getModBCST() != null) {
            icmsSn201.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icmsSn201.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icmsSn201.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icmsSn201.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icmsSn201.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icmsSn201.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icmsSn201.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icmsSn201.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icmsSn201.setVFCPST(icms.getvFCPST().toString());
        }
        if (icms.getpCredSN() != null) {
            icmsSn201.setPCredSN(icms.getpCredSN().toString());
        }
        if (icms.getvCredICMSSN() != null) {
            icmsSn201.setVCredICMSSN(icms.getvCredICMSSN().toString());
        }
        imposto.setICMSSN201(icmsSn201);
    }
}
