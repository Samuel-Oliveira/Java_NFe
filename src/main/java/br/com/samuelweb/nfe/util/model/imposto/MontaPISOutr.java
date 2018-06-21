package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.PIS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class MontaPISOutr implements MontaImposto<TNFe.InfNFe.Det.Imposto.PIS, PIS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.PIS imposto, PIS pis) {
        TNFe.InfNFe.Det.Imposto.PIS.PISOutr pisOutr = new TNFe.InfNFe.Det.Imposto.PIS.PISOutr();
        if (pis.getCST() != null) {
            pisOutr.setCST(pis.getCST().getValue());
        }
        if (pis.getqBCProd() != null && pis.getvAliqProd() != null
                && pis.getqBCProd().add(pis.getvAliqProd()).compareTo(BigDecimal.ZERO) > 0) {
            if (pis.getqBCProd() != null) {
                pisOutr.setQBCProd(pis.getqBCProd().toString());
            }
            if (pis.getvAliqProd() != null) {
                pisOutr.setVAliqProd(pis.getvAliqProd().toString());
            }
        } else {
            if (pis.getvBC() != null) {
                pisOutr.setVBC(pis.getvBC().toString());
            }
            if (pis.getpPIS() != null) {
                pisOutr.setPPIS(pis.getpPIS().toString());
            }
        }
        if (pis.getvPIS() != null) {
            pisOutr.setVPIS(pis.getvPIS().toString());
        }
        imposto.setPISOutr(pisOutr);
    }
}
