package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.DeterminacaoBaseIcmsST;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarDeterminacaoBaseIcmsST implements ValidadorCampo<Integer> {
    private static Map<Integer, DeterminacaoBaseIcmsST> modBCSTMap =  new HashMap<>();

    static {
        modBCSTMap.put(0, DeterminacaoBaseIcmsST.PRECO_TABELADO);
        modBCSTMap.put(1, DeterminacaoBaseIcmsST.LISTA_NEGATIVA);
        modBCSTMap.put(2, DeterminacaoBaseIcmsST.LISTA_POSITIVA);
        modBCSTMap.put(4, DeterminacaoBaseIcmsST.LISTA_NEUTRA);
        modBCSTMap.put(4, DeterminacaoBaseIcmsST.MARGEM_VALOR_AGREGADO);
        modBCSTMap.put(5, DeterminacaoBaseIcmsST.PAUTA);
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (modBCSTMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Modalidade de determinação da BC do ICMS ST, informado %d", valor));
    }
}
