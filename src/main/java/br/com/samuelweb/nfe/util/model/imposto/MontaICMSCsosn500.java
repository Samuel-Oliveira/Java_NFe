package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCsosn500 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 icmsSn500 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500();
        if (icms.getOrig() != null) {
            icmsSn500.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCSOSN() != null) {
            icmsSn500.setCSOSN(icms.getCSOSN().getValue());
        }
        if (icms.getvBCSTRet() != null) {
            icmsSn500.setVBCSTRet(icms.getvBCSTRet().toString());
        }
        if (icms.getpST() != null) {
            icmsSn500.setPST(icms.getpST().toString());
        }
        if (icms.getvICMSSTRet() != null) {
            icmsSn500.setVICMSSTRet(icms.getvICMSSTRet().toString());
        }
        if (icms.getvBCFCPSTRet() != null) {
            icmsSn500.setVBCFCPSTRet(icms.getvBCFCPSTRet().toString());
        }
        if (icms.getpFCPSTRet() != null) {
            icmsSn500.setPFCPSTRet(icms.getpFCPSTRet().toString());
        }
        if (icms.getvFCPSTRet() != null) {
            icmsSn500.setVFCPSTRet(icms.getvFCPSTRet().toString());
        }
        imposto.setICMSSN500(icmsSn500);
    }
}
