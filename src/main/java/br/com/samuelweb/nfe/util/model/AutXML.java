package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJCPF;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class AutXML {

    @NfeCampo(tipo = String.class
            , id = "GA02", tag = "CNPJ/CPF", validadores = {ValidarCNPJCPF.class}
            , tamanhoMinimo = 11, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpjCpf;

    public TNFe.InfNFe.AutXML build() {
        TNFe.InfNFe.AutXML autXML = new TNFe.InfNFe.AutXML();
        if (cnpjCpf != null && cnpjCpf.length() > 11) {
            autXML.setCNPJ(cnpjCpf);
        } else {
            autXML.setCPF(cnpjCpf);
        }
        return autXML;
    }

}
