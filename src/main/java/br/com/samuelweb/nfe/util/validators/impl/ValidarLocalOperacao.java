package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarLocalOperacao implements ValidadorCampo<Integer> {

    private static Map<Integer, String> localOperacaoMap =  new HashMap<>();
    static {
        localOperacaoMap.put(1, "Operação interna");
        localOperacaoMap.put(2, "Operação interestadual");
        localOperacaoMap.put(3, "Operação com exterior");
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (localOperacaoMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Local da Operação, informado %d", valor));
    }
}
