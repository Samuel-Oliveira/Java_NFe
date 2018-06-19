package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.DeterminacaoBaseIcms;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarDeterminacaoBaseIcms implements ValidadorCampo<Integer> {

    private static Map<Integer, DeterminacaoBaseIcms> modBCMap =  new HashMap<>();

    static {
        modBCMap.put(0, DeterminacaoBaseIcms.MARGEM_VALOR_AGREGADO);
        modBCMap.put(1, DeterminacaoBaseIcms.PAUTA);
        modBCMap.put(2, DeterminacaoBaseIcms.PRECO_TABELADO);
        modBCMap.put(3, DeterminacaoBaseIcms.VALOR_OPERACAO);
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (modBCMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Modalidade de determinação da BC do ICMS, informado %d", valor));
    }
}
