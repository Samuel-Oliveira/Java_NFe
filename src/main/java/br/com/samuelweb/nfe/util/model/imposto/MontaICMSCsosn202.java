package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCsosn202 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {

    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 icmsSn202 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202();
        if (icms.getOrig() != null) {
            icmsSn202.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCSOSN() != null) {
            icmsSn202.setCSOSN(icms.getCSOSN().getValue());
        }
        if (icms.getModBCST() != null) {
            icmsSn202.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icmsSn202.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icmsSn202.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icmsSn202.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icmsSn202.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icmsSn202.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icmsSn202.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icmsSn202.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icmsSn202.setVFCPST(icms.getvFCPST().toString());
        }
        imposto.setICMSSN202(icmsSn202);
    }
}
