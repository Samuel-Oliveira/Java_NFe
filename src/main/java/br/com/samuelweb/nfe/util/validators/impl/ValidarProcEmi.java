package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarProcEmi implements ValidadorCampo<Integer> {
    private static Map<Integer, String> procEmiMap =  new HashMap<>();
    static {
        procEmiMap.put(0, "Emissão de NF-e com aplicativo do contribuinte");
        procEmiMap.put(1, "Emissão de NF-e avulsa pelo Fisco");
        procEmiMap.put(2, "Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco");
        procEmiMap.put(3, "Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco");
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (procEmiMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Processo de emissão da NF-e, informado %d", valor));
    }
}
