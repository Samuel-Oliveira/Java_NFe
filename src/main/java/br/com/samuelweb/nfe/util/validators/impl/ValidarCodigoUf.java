package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCodigoUf implements ValidadorCampo<Integer> {

    private static Map<String, Estados> estadosMap =  new HashMap<>();
    static {
        for (Estados estado : Estados.values()) {
            estadosMap.put(estado.getCodigoIbge(), estado);
        }
    }

    public RetornoValidar validar(Integer valor) {
        String codigoStr = String.valueOf(valor == null? "": valor);
        if (estadosMap.get(codigoStr) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Código do IBGE da UF informado não é valido. Código informado %d", valor));
    }
}
