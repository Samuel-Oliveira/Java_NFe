package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Emit;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCRT implements ValidadorCampo<String, Emit> {

    private static Map<String, String> crtMap =  new HashMap<>();
    static {
        inicializarMap(crtMap);
    }

    static void inicializarMap(Map<String, String> finalidadeNfeMap) {
        finalidadeNfeMap.put("1", "Simples Nacional");
        finalidadeNfeMap.put("2", "Simples Nacional, excesso sublimite de receita bruta");
        finalidadeNfeMap.put("3", "Regime Normal");
    }

    @Override
    public RetornoValidar validar(String valor, Emit emit) {
        if (crtMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format(
                "Valor inválido para o campo CRT (Código de Regime Tributário) do emitente da NFe, informado %s", valor));
    }
}
