package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.IndISS;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarIndISS implements ValidadorCampo<Integer, Object> {

    private static Map<Integer, IndISS> indISSMap = new HashMap<>();
    static {
        indISSMap.put(1, IndISS.EXIGIVEL);
        indISSMap.put(2, IndISS.NAO_INCIDENCIA);
        indISSMap.put(3, IndISS.ISENCAO);
        indISSMap.put(4, IndISS.EXPORTACAO);
        indISSMap.put(5, IndISS.IMUNIDADE);
        indISSMap.put(6, IndISS.EXIGIBILIDADE_SUSPENSA_JUDICIAL);
        indISSMap.put(7, IndISS.EXIGIBILIDADE_SUSPENSA_ADMINISTRATIVO);
    }

    @Override
    public RetornoValidar validar(Integer valor, Object parent) {
        if (indISSMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Indicador da exigibilidade do ISS, informado %d", valor));
    }
}
