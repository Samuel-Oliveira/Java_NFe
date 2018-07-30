package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Ide;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarIndFinal implements ValidadorCampo<Integer, Ide> {
    private static Map<Integer, String> inficadorConsumidorFinalMap =  new HashMap<>();
    static {
        inficadorConsumidorFinalMap.put(0, "Normal");
        inficadorConsumidorFinalMap.put(1, "Consumidor final");
    }

    @Override
    public RetornoValidar validar(Integer valor, Ide parent) {
        if (inficadorConsumidorFinalMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format(
                "Valor inválido para o campo Indicador operação com Consumidor final, informado %d", valor));
    }
}
