package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.COFINS;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

public class MontaCOFINSAliq implements MontaImposto<TNFe.InfNFe.Det.Imposto.COFINS, COFINS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.COFINS imposto, COFINS cofins) {
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq cofinsAliq = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
        if (cofins.getCst() != null) {
            cofinsAliq.setCST(cofins.getCst().getValue());
        }
        if (cofins.getvBC() != null) {
            cofinsAliq.setVBC(cofins.getvBC().toString());
        }
        if (cofins.getpCOFINS() != null) {
            cofinsAliq.setPCOFINS(cofins.getpCOFINS().toString());
        }
        if (cofins.getvCOFINS() != null) {
            cofinsAliq.setVCOFINS(cofins.getvCOFINS().toString());
        }
        imposto.setCOFINSAliq(cofinsAliq);
    }
}
