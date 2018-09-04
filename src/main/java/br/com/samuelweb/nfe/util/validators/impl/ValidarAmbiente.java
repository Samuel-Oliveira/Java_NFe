package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Ide;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarAmbiente implements ValidadorCampo<Integer, Ide> {
    private static Map<Integer, String> tipoDanfeMap =  new HashMap<>();
    static {
        tipoDanfeMap.put(1, "Produção");
        tipoDanfeMap.put(2, "Homologação");
    }

    @Override
    public RetornoValidar validar(Integer valor, Ide ide) {
        if (tipoDanfeMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format(
                "Valor inválido para o campo Identificação do Ambiente, informado %d", valor));
    }
}
