package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

public class ValidarGTIN implements ValidadorCampo<String> {
    @Override
    public RetornoValidar validar(String valor) {
        //todo implementar => ValidarCEAN.class} Preencher com o código GTIN-8, GTIN-12, GTIN-13 ou GTIN14 (antigos códigos EAN, UPC e DUN-14), não informar o conteúdo da TAG em caso de o produto não possuir este código.
        return new RetornoValidarImpl(true);
    }
}
