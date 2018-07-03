package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTCofins;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTCofins implements ValidadorCampo<String> {

    private static Map<String, CSTCofins> cstCofinsMap =  new HashMap<>();
    static {
        cstCofinsMap.put("01", CSTCofins.CST_01);
        cstCofinsMap.put("02", CSTCofins.CST_02);
        cstCofinsMap.put("03", CSTCofins.CST_03);
        cstCofinsMap.put("04", CSTCofins.CST_04);
        cstCofinsMap.put("05", CSTCofins.CST_05);
        cstCofinsMap.put("06", CSTCofins.CST_06);
        cstCofinsMap.put("07", CSTCofins.CST_07);
        cstCofinsMap.put("08", CSTCofins.CST_08);
        cstCofinsMap.put("09", CSTCofins.CST_09);
        cstCofinsMap.put("49", CSTCofins.CST_49);
        cstCofinsMap.put("50", CSTCofins.CST_50);
        cstCofinsMap.put("51", CSTCofins.CST_51);
        cstCofinsMap.put("52", CSTCofins.CST_52);
        cstCofinsMap.put("53", CSTCofins.CST_53);
        cstCofinsMap.put("54", CSTCofins.CST_54);
        cstCofinsMap.put("55", CSTCofins.CST_55);
        cstCofinsMap.put("56", CSTCofins.CST_56);
        cstCofinsMap.put("60", CSTCofins.CST_60);
        cstCofinsMap.put("61", CSTCofins.CST_61);
        cstCofinsMap.put("62", CSTCofins.CST_62);
        cstCofinsMap.put("63", CSTCofins.CST_63);
        cstCofinsMap.put("64", CSTCofins.CST_64);
        cstCofinsMap.put("65", CSTCofins.CST_65);
        cstCofinsMap.put("66", CSTCofins.CST_66);
        cstCofinsMap.put("67", CSTCofins.CST_67);
        cstCofinsMap.put("70", CSTCofins.CST_70);
        cstCofinsMap.put("71", CSTCofins.CST_71);
        cstCofinsMap.put("72", CSTCofins.CST_72);
        cstCofinsMap.put("73", CSTCofins.CST_73);
        cstCofinsMap.put("74", CSTCofins.CST_74);
        cstCofinsMap.put("75", CSTCofins.CST_75);
        cstCofinsMap.put("98", CSTCofins.CST_98);
        cstCofinsMap.put("99", CSTCofins.CST_99);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstCofinsMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST Cofins, informado %s", valor));
    }
}
