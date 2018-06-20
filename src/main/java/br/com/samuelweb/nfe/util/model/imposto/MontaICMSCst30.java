package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaICMSCst30 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 icms30 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS30();
        if (icms.getOrig() != null) {
            icms30.setOrig(icms.getOrig().getValue().toString());
        }
        if (icms.getCST() != null) {
            icms30.setCST(icms.getCST().getValue());
        }
        if (icms.getModBCST() != null) {
            icms30.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icms30.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icms30.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icms30.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icms30.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icms30.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getvBCFCPST() != null) {
            icms30.setVBCFCPST(icms.getvBCFCPST().toString());
        }
        if (icms.getpFCPST() != null) {
            icms30.setPFCPST(icms.getpFCPST().toString());
        }
        if (icms.getvFCPST() != null) {
            icms30.setVFCPST(icms.getvFCPST().toString());
        }
        if (icms.getvICMSDeson() != null) {
            icms30.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms30.setMotDesICMS(icms.getMotDesICMS().getValue().toString());
        }
        imposto.setICMS30(icms30);
    }
}
