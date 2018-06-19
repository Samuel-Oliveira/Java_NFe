package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSOSNIcms;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSOSNIcms implements ValidadorCampo<String> {
    private static Map<String, CSOSNIcms> csosnMap =  new HashMap<>();
    static {
        csosnMap.put("101", CSOSNIcms.CSOSN_101);
        csosnMap.put("102", CSOSNIcms.CSOSN_102);
        csosnMap.put("103", CSOSNIcms.CSOSN_103);
        csosnMap.put("201", CSOSNIcms.CSOSN_201);
        csosnMap.put("202", CSOSNIcms.CSOSN_202);
        csosnMap.put("203", CSOSNIcms.CSOSN_203);
        csosnMap.put("300", CSOSNIcms.CSOSN_300);
        csosnMap.put("400", CSOSNIcms.CSOSN_400);
        csosnMap.put("500", CSOSNIcms.CSOSN_500);
        csosnMap.put("900", CSOSNIcms.CSOSN_900);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (csosnMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CSOSN, informado %s", valor));
    }
}
