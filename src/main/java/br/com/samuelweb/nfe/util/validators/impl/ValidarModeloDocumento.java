package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.ModeloDocumento;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarModeloDocumento implements ValidadorCampo<String, Object> {

    private static Map<String, ModeloDocumento> modeloMap =  new HashMap<>();
    static {

        modeloMap.put(ModeloDocumento.NFE.getValue(), ModeloDocumento.NFE);
        modeloMap.put(ModeloDocumento.NFCE.getValue(), ModeloDocumento.NFCE);
    }
    @Override
    public RetornoValidar validar(String valor, Object parent) {
        if (modeloMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Modelo do documento inválido, modelo informado %s", valor));
    }
}
