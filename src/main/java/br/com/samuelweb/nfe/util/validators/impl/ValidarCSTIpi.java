package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTIpi;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTIpi implements ValidadorCampo<String> {

    private static Map<String, CSTIpi> cstIpiMap =  new HashMap<>();
    static {
        cstIpiMap.put("00", CSTIpi.CST_00);
        cstIpiMap.put("49", CSTIpi.CST_49);
        cstIpiMap.put("50", CSTIpi.CST_50);
        cstIpiMap.put("99", CSTIpi.CST_99);
        cstIpiMap.put("01", CSTIpi.CST_01);
        cstIpiMap.put("02", CSTIpi.CST_02);
        cstIpiMap.put("03", CSTIpi.CST_03);
        cstIpiMap.put("04", CSTIpi.CST_04);
        cstIpiMap.put("05", CSTIpi.CST_05);
        cstIpiMap.put("51", CSTIpi.CST_51);
        cstIpiMap.put("52", CSTIpi.CST_52);
        cstIpiMap.put("53", CSTIpi.CST_53);
        cstIpiMap.put("54", CSTIpi.CST_54);
        cstIpiMap.put("55", CSTIpi.CST_55);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstIpiMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST Ipi, informado %s", valor));
    }
}
