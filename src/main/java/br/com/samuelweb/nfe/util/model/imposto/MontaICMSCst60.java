package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class MontaICMSCst60 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        if ((icms.getvBCSTDest().compareTo(BigDecimal.ZERO) != 0)
                || (icms.getvICMSSTDest().compareTo(BigDecimal.ZERO) != 0)) {
            buildIcmsSt(imposto, icms);
        } else {
            buildIcms(imposto, icms);
        }
    }

    private void buildIcms(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 icms60 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
        if (icms.getOrig() != null) {
            icms60.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icms60.setCST(icms.getCST().getValue());
        }
        if (icms.getvBCSTRet() != null) {
            icms60.setVBCSTRet(icms.getvBCSTRet().toString());
        }
        if (icms.getpST() != null) {
            icms60.setPST(icms.getpST().toString());
        }
        if (icms.getvICMSSTRet() != null) {
            icms60.setVICMSSTRet(icms.getvICMSSTRet().toString());
        }
        if (icms.getvBCFCPSTRet() != null) {
            icms60.setVBCFCPSTRet(icms.getvBCFCPSTRet().toString());
        }
        if (icms.getpFCPSTRet() != null) {
            icms60.setPFCPSTRet(icms.getpFCPSTRet().toString());
        }
        if (icms.getvFCPSTRet() != null) {
            icms60.setVFCPSTRet(icms.getvFCPSTRet().toString());
        }
        imposto.setICMS60(icms60);
    }

    private void buildIcmsSt(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSST icmsSt = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSST();
        if (icms.getOrig() != null) {
            icmsSt.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icmsSt.setCST(icms.getCST().getValue());
        }
        if (icms.getvBCSTRet() != null) {
            icmsSt.setVBCSTRet(icms.getvBCSTRet().toString());
        }
        if (icms.getvICMSSTRet() != null) {
            icmsSt.setVICMSSTRet(icms.getvICMSSTRet().toString());
        }
        if (icms.getvBCSTDest() != null) {
            icmsSt.setVBCSTDest(icms.getvBCSTDest().toString());
        }
        if (icms.getvICMSSTDest() != null) {
            icmsSt.setVICMSSTDest(icms.getvICMSSTDest().toString());
        }
        imposto.setICMSST(icmsSt);
    }
}
