package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class MontaCOFINSOutr implements MontaImposto<TNFe.InfNFe.Det.Imposto.COFINS, COFINS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.COFINS imposto, COFINS cofins) {
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr cofinsOutr = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr();
        if (cofins.getCst() != null) {
            cofinsOutr.setCST(cofins.getCst().getValue());
        }
        if (cofins.getqBCProd() != null && cofins.getvAliqProd() != null
                && cofins.getqBCProd().add(cofins.getvAliqProd()).compareTo(BigDecimal.ZERO) > 0) {
            if (cofins.getqBCProd() != null) {
                cofinsOutr.setQBCProd(cofins.getqBCProd().toString());
            }
            if (cofins.getvAliqProd() != null) {
                cofinsOutr.setVAliqProd(cofins.getvAliqProd().toString());
            }
        } else {
            if (cofins.getvBC() != null) {
                cofinsOutr.setVBC(cofins.getvBC().toString());
            }
            if (cofins.getpCOFINS() != null) {
                cofinsOutr.setPCOFINS(cofins.getpCOFINS().toString());
            }
        }
        if (cofins.getvCOFINS() != null) {
            cofinsOutr.setVCOFINS(cofins.getvCOFINS().toString());
        }
        imposto.setCOFINSOutr(cofinsOutr);
    }
}
