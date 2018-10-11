package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.enumeration.CSTIcms;
import br.com.samuelweb.nfe.util.model.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;

import java.math.BigDecimal;

public class MontaICMSCst40 implements MontaImposto<TNFe.InfNFe.Det.Imposto.ICMS, ICMS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        if (icms.getCST().equals(CSTIcms.CST_41)
                && ((icms.getvBCSTRet().compareTo(BigDecimal.ZERO)) != 0)
                    || (icms.getvICMSSTRet().compareTo(BigDecimal.ZERO) != 0)
                    || (icms.getvBCSTDest().compareTo(BigDecimal.ZERO) != 0)
                    || (icms.getvICMSSTDest().compareTo(BigDecimal.ZERO) != 0)) {
            buildIcmsPartilha(imposto, icms);
        } else {
            buildIcms(imposto, icms);
        }
    }

    private void buildIcms(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 icms40 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
        if (icms.getOrig() != null) {
            icms40.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icms40.setCST(icms.getCST().getValue());
        }
        if (icms.getvICMSDeson() != null) {
            icms40.setVICMSDeson(icms.getvICMSDeson().toString());
        }
        if (icms.getMotDesICMS() != null) {
            icms40.setMotDesICMS(icms.getMotDesICMS().getValue().toString());
        }
        imposto.setICMS40(icms40);
    }

    private void buildIcmsPartilha(TNFe.InfNFe.Det.Imposto.ICMS imposto, ICMS icms) {
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart icmsPart = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart();
        if (icms.getOrig() != null) {
            icmsPart.setOrig(icms.getOrig().toString());
        }
        if (icms.getCST() != null) {
            icmsPart.setCST(icms.getCST().getValue().toString());
        }
        if (icms.getModBC() != null) {
            icmsPart.setModBC(icms.getModBC().getValue().toString());
        }
        if (icms.getvBC() != null) {
            icmsPart.setVBC(icms.getvBC().toString());
        }
        if (icms.getpRedBC() != null) {
            icmsPart.setPRedBC(icms.getpRedBC().toString());
        }
        if (icms.getpICMS() != null) {
            icmsPart.setPICMS(icms.getpICMS().toString());
        }
        if (icms.getvICMS() != null) {
            icmsPart.setVICMS(icms.getvICMS().toString());
        }
        if (icms.getModBCST() != null) {
            icmsPart.setModBCST(icms.getModBCST().getValue().toString());
        }
        if (icms.getpMVAST() != null) {
            icmsPart.setPMVAST(icms.getpMVAST().toString());
        }
        if (icms.getpRedBCST() != null) {
            icmsPart.setPRedBCST(icms.getpRedBCST().toString());
        }
        if (icms.getvBCST() != null) {
            icmsPart.setVBCST(icms.getvBCST().toString());
        }
        if (icms.getpICMSST() != null) {
            icmsPart.setPICMSST(icms.getpICMSST().toString());
        }
        if (icms.getvICMSST() != null) {
            icmsPart.setVICMSST(icms.getvICMSST().toString());
        }
        if (icms.getpBCOp() != null) {
            icmsPart.setPBCOp(icms.getpBCOp().toString());
        }
        if (icms.getUFST() != null) {
            icmsPart.setUFST(TUf.fromValue(icms.getUFST()));
        }
        imposto.setICMSPart(icmsPart);
    }
}
