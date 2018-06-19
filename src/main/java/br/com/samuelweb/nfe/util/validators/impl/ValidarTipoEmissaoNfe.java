package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarTipoEmissaoNfe implements ValidadorCampo<Integer> {
    private static Map<Integer, String> tipoEmissaoMap =  new HashMap<>();
    static {
        tipoEmissaoMap.put(1, "Emissão normal (não em contingência)");
        tipoEmissaoMap.put(2, "Contingência FS-IA, com impressão do DANFE em formulário de segurança");
        tipoEmissaoMap.put(3, "Contingência SCAN (Sistema de Contingência do Ambiente Nacional)");
        tipoEmissaoMap.put(4, "Contingência DPEC (Declaração Prévia da Emissão em Contingência)");
        tipoEmissaoMap.put(5, "Contingência FS-DA, com impressão do DANFE em formulário de segurança");
        tipoEmissaoMap.put(6, "Contingência SVC-AN (SEFAZ Virtual de Contingência do AN)");
        tipoEmissaoMap.put(7, "Contingência SVC-RS (SEFAZ Virtual de Contingência do RS)");
        tipoEmissaoMap.put(9, "Contingência off-line da NFC-e (as demais opções de contingência são válidas também para a NFC-e). Para a NFC-e somente estão disponíveis e são válidas as opções de contingência 5 e 9");
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (tipoEmissaoMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Tipo Emissão NFe, informado %d", valor));
    }
}
