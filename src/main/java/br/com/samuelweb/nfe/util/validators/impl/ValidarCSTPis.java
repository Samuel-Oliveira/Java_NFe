package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTPis;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTPis implements ValidadorCampo<String> {

    private static Map<String, CSTPis> cstPisMap =  new HashMap<>();
    static {
        cstPisMap.put("01", CSTPis.CST_01);
        cstPisMap.put("02", CSTPis.CST_02);
        cstPisMap.put("03", CSTPis.CST_03);
        cstPisMap.put("04", CSTPis.CST_04);
        cstPisMap.put("05", CSTPis.CST_05);
        cstPisMap.put("06", CSTPis.CST_06);
        cstPisMap.put("07", CSTPis.CST_07);
        cstPisMap.put("08", CSTPis.CST_08);
        cstPisMap.put("09", CSTPis.CST_09);
        cstPisMap.put("49", CSTPis.CST_49);
        cstPisMap.put("50", CSTPis.CST_50);
        cstPisMap.put("51", CSTPis.CST_51);
        cstPisMap.put("52", CSTPis.CST_52);
        cstPisMap.put("53", CSTPis.CST_53);
        cstPisMap.put("54", CSTPis.CST_54);
        cstPisMap.put("55", CSTPis.CST_55);
        cstPisMap.put("56", CSTPis.CST_56);
        cstPisMap.put("60", CSTPis.CST_60);
        cstPisMap.put("61", CSTPis.CST_61);
        cstPisMap.put("62", CSTPis.CST_62);
        cstPisMap.put("63", CSTPis.CST_63);
        cstPisMap.put("64", CSTPis.CST_64);
        cstPisMap.put("65", CSTPis.CST_65);
        cstPisMap.put("66", CSTPis.CST_66);
        cstPisMap.put("67", CSTPis.CST_67);
        cstPisMap.put("70", CSTPis.CST_70);
        cstPisMap.put("71", CSTPis.CST_71);
        cstPisMap.put("72", CSTPis.CST_72);
        cstPisMap.put("73", CSTPis.CST_73);
        cstPisMap.put("74", CSTPis.CST_74);
        cstPisMap.put("75", CSTPis.CST_75);
        cstPisMap.put("98", CSTPis.CST_98);
        cstPisMap.put("99", CSTPis.CST_99);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstPisMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST Pis, informado %s", valor));
    }
}
