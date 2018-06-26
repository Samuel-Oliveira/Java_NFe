package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTIpi;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTIpi implements ValidadorCampo<String> {

    private static Map<String, CSTIpi> cstIpiMap =  new HashMap<>();
    static {
        cstIpiMap.put("00", CSTIpi.CST00);
        cstIpiMap.put("49", CSTIpi.CST49);
        cstIpiMap.put("50", CSTIpi.CST50);
        cstIpiMap.put("99", CSTIpi.CST99);
        cstIpiMap.put("01", CSTIpi.CST01);
        cstIpiMap.put("02", CSTIpi.CST02);
        cstIpiMap.put("03", CSTIpi.CST03);
        cstIpiMap.put("04", CSTIpi.CST04);
        cstIpiMap.put("05", CSTIpi.CST05);
        cstIpiMap.put("51", CSTIpi.CST51);
        cstIpiMap.put("52", CSTIpi.CST52);
        cstIpiMap.put("53", CSTIpi.CST53);
        cstIpiMap.put("54", CSTIpi.CST54);
        cstIpiMap.put("55", CSTIpi.CST55);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstIpiMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST Ipi, informado %s", valor));
    }
}
