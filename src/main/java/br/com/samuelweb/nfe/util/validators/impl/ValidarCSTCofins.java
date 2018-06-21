package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTCofins;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTCofins implements ValidadorCampo<String> {

    private static Map<String, CSTCofins> cstCofinsMap =  new HashMap<>();
    static {
        cstCofinsMap.put("01", CSTCofins.CST01);
        cstCofinsMap.put("02", CSTCofins.CST02);
        cstCofinsMap.put("03", CSTCofins.CST03);
        cstCofinsMap.put("04", CSTCofins.CST04);
        cstCofinsMap.put("05", CSTCofins.CST05);
        cstCofinsMap.put("06", CSTCofins.CST06);
        cstCofinsMap.put("07", CSTCofins.CST07);
        cstCofinsMap.put("08", CSTCofins.CST08);
        cstCofinsMap.put("09", CSTCofins.CST09);
        cstCofinsMap.put("49", CSTCofins.CST49);
        cstCofinsMap.put("50", CSTCofins.CST50);
        cstCofinsMap.put("51", CSTCofins.CST51);
        cstCofinsMap.put("52", CSTCofins.CST52);
        cstCofinsMap.put("53", CSTCofins.CST53);
        cstCofinsMap.put("54", CSTCofins.CST54);
        cstCofinsMap.put("55", CSTCofins.CST55);
        cstCofinsMap.put("56", CSTCofins.CST56);
        cstCofinsMap.put("60", CSTCofins.CST60);
        cstCofinsMap.put("61", CSTCofins.CST61);
        cstCofinsMap.put("62", CSTCofins.CST62);
        cstCofinsMap.put("63", CSTCofins.CST63);
        cstCofinsMap.put("64", CSTCofins.CST64);
        cstCofinsMap.put("65", CSTCofins.CST65);
        cstCofinsMap.put("66", CSTCofins.CST66);
        cstCofinsMap.put("67", CSTCofins.CST67);
        cstCofinsMap.put("70", CSTCofins.CST70);
        cstCofinsMap.put("71", CSTCofins.CST71);
        cstCofinsMap.put("72", CSTCofins.CST72);
        cstCofinsMap.put("73", CSTCofins.CST73);
        cstCofinsMap.put("74", CSTCofins.CST74);
        cstCofinsMap.put("75", CSTCofins.CST75);
        cstCofinsMap.put("98", CSTCofins.CST98);
        cstCofinsMap.put("99", CSTCofins.CST99);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstCofinsMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST Cofins, informado %s", valor));
    }
}
