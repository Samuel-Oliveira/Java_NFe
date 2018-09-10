package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Ide;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarTipoOperacao implements ValidadorCampo<Integer, Ide> {

    private static Map<Integer, String> tipoOperacaoMap =  new HashMap<>();
    static {
        tipoOperacaoMap.put(0, "Entrada");
        tipoOperacaoMap.put(1, "Saída");
    }

    @Override
    public RetornoValidar validar(Integer valor, Ide ide) {
        if (tipoOperacaoMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Tipo Operação, somente é aceito 0=Entrada 1=Saída informado %d", valor));
    }
}
