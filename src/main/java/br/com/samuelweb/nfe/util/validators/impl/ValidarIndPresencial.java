package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Ide;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarIndPresencial implements ValidadorCampo<Integer, Ide> {

    private static Map<Integer, String> indicadorPresencialMap =  new HashMap<>();
    static {
        indicadorPresencialMap.put(0, "Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste);");
        indicadorPresencialMap.put(1, "Operação presencial;");
        indicadorPresencialMap.put(2, "Operação não presencial, pela Internet;");
        indicadorPresencialMap.put(3, "Operação não presencial, Teleatendimento;");
        indicadorPresencialMap.put(4, "NFC-e em operação com entrega a domicílio;");
        indicadorPresencialMap.put(5, "Operação presencial, fora do estabelecimento");
        indicadorPresencialMap.put(9, "Operação não presencial, outros");
    }

    @Override
    public RetornoValidar validar(Integer valor, Ide parent) {
        if (indicadorPresencialMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Indicador de presença do comprador, informado %d", valor));
    }
}
