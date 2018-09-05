package br.com.samuelweb.nfe.util.model.imposto;

import br.com.samuelweb.nfe.util.model.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class MontaPISQtde implements MontaImposto<TNFe.InfNFe.Det.Imposto.PIS, PIS> {
    @Override
    public void build(TNFe.InfNFe.Det.Imposto.PIS imposto, PIS pis) {
        TNFe.InfNFe.Det.Imposto.PIS.PISQtde pisQtde = new TNFe.InfNFe.Det.Imposto.PIS.PISQtde();
        if (pis.getCST() != null) {
            pisQtde.setCST(pis.getCST().getValue());
        }
        if (pis.getqBCProd() != null) {
            pisQtde.setQBCProd(pis.getqBCProd().toString());
        }
        if (pis.getvAliqProd() != null) {
            pisQtde.setVAliqProd(pis.getvAliqProd().toString());
        }
        if (pis.getvPIS() != null) {
            pisQtde.setVPIS(pis.getvPIS().toString());
        }
        imposto.setPISQtde(pisQtde);
    }
}
