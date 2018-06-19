package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarTpRestricao implements ValidadorCampo<Integer> {
    private static Map<Integer, String> tipoResticaoMap =  new HashMap<>();
    static {
        tipoResticaoMap.put(0, "Não há");
        tipoResticaoMap.put(1, "Alienação Fiduciária");
        tipoResticaoMap.put(2, "Arrendamento Mercantil");
        tipoResticaoMap.put(3, "Reserva de Domínio");
        tipoResticaoMap.put(4, "Penhor de Veículos");
        tipoResticaoMap.put(9, "Outras");
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (tipoResticaoMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Tipo Restição, informado %d", valor));
    }
}
