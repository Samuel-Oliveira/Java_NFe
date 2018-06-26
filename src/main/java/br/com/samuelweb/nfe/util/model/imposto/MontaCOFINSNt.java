package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class MontaCOFINSNt implements MontaImposto<TNFe.InfNFe.Det.Imposto.COFINS, COFINS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.COFINS imposto, COFINS pis) {
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT pisnt = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT();
        if (pis.getCst() != null) {
            pisnt.setCST(pis.getCst().getValue());
        }
        imposto.setCOFINSNT(pisnt);
    }
}
