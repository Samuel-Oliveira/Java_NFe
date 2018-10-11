package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.OrigemMercadoria;
import br.com.samuelweb.nfe.util.model.ICMS;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarOrigemMercadora implements ValidadorCampo<Integer, Object> {
    private static Map<Integer, OrigemMercadoria> origemMercadoriaMap =  new HashMap<>();
    static {
        origemMercadoriaMap.put(0, OrigemMercadoria.NACIONAL);
        origemMercadoriaMap.put(1, OrigemMercadoria.ESTRANGEIRA_IMPORTACAO_DIRETA);
        origemMercadoriaMap.put(2, OrigemMercadoria.ESTRANGEIRA_ADQUIRIDA_BRASIL);
        origemMercadoriaMap.put(3, OrigemMercadoria.NACIONAL_CONTEUDO_IMPORTACAO_SUPERIOR40);
        origemMercadoriaMap.put(4, OrigemMercadoria.NACIONAL_PROCESSOS_BASICOS);
        origemMercadoriaMap.put(5, OrigemMercadoria.NACIONAL_CONTEUDO_IMPORTACAO_INFERIOR_IGUAL40);
        origemMercadoriaMap.put(6, OrigemMercadoria.ESTRANGEIRA_IMPORTACAO_DIRETA_SEM_SIMILAR);
        origemMercadoriaMap.put(7, OrigemMercadoria.ESTRANGEIRA_ADQUIRIDA_BRASIL_SEM_SIMILAR);
        origemMercadoriaMap.put(8, OrigemMercadoria.NACIONAL_CONTEUDO_IMPORTACAO_SUPERIOR70);
    }

    @Override
    public RetornoValidar validar(Integer valor, Object parent) {
        if (origemMercadoriaMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Origem Mercadoria, informado %d", valor));
    }
}
