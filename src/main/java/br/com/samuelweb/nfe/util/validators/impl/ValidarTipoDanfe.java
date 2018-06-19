package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarTipoDanfe implements ValidadorCampo<Integer> {
    private static Map<Integer, String> tipoDanfeMap =  new HashMap<>();
    static {
        ValidarFinalidadeNfe.inicializarMap(tipoDanfeMap);
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (tipoDanfeMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Tipo Danfe, informado %d", valor));
    }
}
