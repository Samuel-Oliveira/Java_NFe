package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.IndicadorOrigemProcesso;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarIndicadorOrigemProcesso implements ValidadorCampo<Integer> {

    private static Map<Integer, IndicadorOrigemProcesso> modBCMap =  new HashMap<>();

    static {
        modBCMap.put(0, IndicadorOrigemProcesso.SEFAZ);
        modBCMap.put(1, IndicadorOrigemProcesso.JUSTICA_FEDERAL);
        modBCMap.put(2, IndicadorOrigemProcesso.JUSTICA_ESTADUAL);
        modBCMap.put(3, IndicadorOrigemProcesso.SECEX_RFB);
        modBCMap.put(9, IndicadorOrigemProcesso.OUTROS);
    }


    @Override
    public RetornoValidar validar(Integer valor) {
        if (modBCMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Modalidade de determinação da BC do ICMS, informado %d", valor));
    }
}
