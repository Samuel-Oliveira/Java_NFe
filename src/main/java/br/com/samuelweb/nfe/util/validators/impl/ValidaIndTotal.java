package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidaIndTotal implements ValidadorCampo<Integer> {

    private static Map<Integer, String> indicaTotal =  new HashMap<>();
    static {
        indicaTotal.put(0, "Valor do item (vProd) não compõe o valor total da NF-e");
        indicaTotal.put(1, "Valor do item (vProd) compõe o valor total da NF-e (vProd)");
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (indicaTotal.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format(
                "Valor inválido para o campo Indica se valor do Item (vProd) entra no valor total da NF-e (vProd), informado %d", valor));
    }
}
