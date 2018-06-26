package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.MotivoDesoneracaoICMS;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarMotivoDesoneracaoICMS implements ValidadorCampo<Integer> {
    private static Map<Integer, MotivoDesoneracaoICMS> motDesonICMSMap =  new HashMap<>();

    static {
        motDesonICMSMap.put(1, MotivoDesoneracaoICMS.TAXI);
        motDesonICMSMap.put(3, MotivoDesoneracaoICMS.PRODUTOR_AGROPECUARIO);
        motDesonICMSMap.put(4, MotivoDesoneracaoICMS.FROTISTA_LOCADORA);
        motDesonICMSMap.put(5, MotivoDesoneracaoICMS.DIPLOMATICO_CONSULAR);
        motDesonICMSMap.put(6, MotivoDesoneracaoICMS.AMAZONIA_LIVRE_COMERCIO);
        motDesonICMSMap.put(7, MotivoDesoneracaoICMS.SUFRAMA);
        motDesonICMSMap.put(8, MotivoDesoneracaoICMS.VENDA_ORGAOS_PUBLICOS);
        motDesonICMSMap.put(9, MotivoDesoneracaoICMS.OUTROS);
        motDesonICMSMap.put(10, MotivoDesoneracaoICMS.DEFICIENTE_CONDUTOR);
        motDesonICMSMap.put(11, MotivoDesoneracaoICMS.DEFICIENTE_NAO_CONDUTOR);
        motDesonICMSMap.put(12, MotivoDesoneracaoICMS.ORGAO_FOMENTO);
        motDesonICMSMap.put(16, MotivoDesoneracaoICMS.OLIMPIADA_RIO_2016);
        motDesonICMSMap.put(90, MotivoDesoneracaoICMS.SOLICITADO_FISCO);
    }

    @Override
    public RetornoValidar validar(Integer valor) {
        if (motDesonICMSMap.get(valor) != null || valor == null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Modalidade de determinação da BC do ICMS ST, informado %d", valor));
    }
}
