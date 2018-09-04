package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Ide;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarFinalidadeNfe implements ValidadorCampo<Integer, Ide> {
    private static Map<Integer, String> finalidadeNfeMap =  new HashMap<>();
    static {
        inicializarMap(finalidadeNfeMap);
    }

    static void inicializarMap(Map<Integer, String> finalidadeNfeMap) {
        finalidadeNfeMap.put(1, "NF-e normal");
        finalidadeNfeMap.put(2, "NF-e complementar");
        finalidadeNfeMap.put(3, "NF-e de ajuste");
        finalidadeNfeMap.put(4, "Devolução de mercadoria");
    }

    @Override
    public RetornoValidar validar(Integer valor, Ide parent) {
        if (finalidadeNfeMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Finalidade Nfe, informado %d", valor));
    }
}
