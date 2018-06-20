package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTIcms;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTIcms implements ValidadorCampo<String> {
    private static Map<String, CSTIcms> cstIcmsMap =  new HashMap<>();
    static {
        cstIcmsMap.put("00", CSTIcms.CST_00);
        cstIcmsMap.put("10", CSTIcms.CST_10);
        cstIcmsMap.put("20", CSTIcms.CST_20);
        cstIcmsMap.put("30", CSTIcms.CST_30);
        cstIcmsMap.put("40", CSTIcms.CST_40);
        cstIcmsMap.put("41", CSTIcms.CST_41);
        cstIcmsMap.put("50", CSTIcms.CST_50);
        cstIcmsMap.put("51", CSTIcms.CST_51);
        cstIcmsMap.put("60", CSTIcms.CST_60);
        cstIcmsMap.put("70", CSTIcms.CST_70);
        cstIcmsMap.put("90", CSTIcms.CST_90);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstIcmsMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST, informado %s", valor));
    }
}
