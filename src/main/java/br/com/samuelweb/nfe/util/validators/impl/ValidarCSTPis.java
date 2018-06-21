package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.enumeration.CSTPis;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCSTPis implements ValidadorCampo<String> {

    private static Map<String, CSTPis> cstPisMap =  new HashMap<>();
    static {
        cstPisMap.put("01", CSTPis.PIS01);
        cstPisMap.put("02", CSTPis.PIS02);
        cstPisMap.put("03", CSTPis.PIS03);
        cstPisMap.put("04", CSTPis.PIS04);
        cstPisMap.put("05", CSTPis.PIS05);
        cstPisMap.put("06", CSTPis.PIS06);
        cstPisMap.put("07", CSTPis.PIS07);
        cstPisMap.put("08", CSTPis.PIS08);
        cstPisMap.put("09", CSTPis.PIS09);
        cstPisMap.put("49", CSTPis.PIS49);
        cstPisMap.put("50", CSTPis.PIS50);
        cstPisMap.put("51", CSTPis.PIS51);
        cstPisMap.put("52", CSTPis.PIS52);
        cstPisMap.put("53", CSTPis.PIS53);
        cstPisMap.put("54", CSTPis.PIS54);
        cstPisMap.put("55", CSTPis.PIS55);
        cstPisMap.put("56", CSTPis.PIS56);
        cstPisMap.put("60", CSTPis.PIS60);
        cstPisMap.put("61", CSTPis.PIS61);
        cstPisMap.put("62", CSTPis.PIS62);
        cstPisMap.put("63", CSTPis.PIS63);
        cstPisMap.put("64", CSTPis.PIS64);
        cstPisMap.put("65", CSTPis.PIS65);
        cstPisMap.put("66", CSTPis.PIS66);
        cstPisMap.put("67", CSTPis.PIS67);
        cstPisMap.put("70", CSTPis.PIS70);
        cstPisMap.put("71", CSTPis.PIS71);
        cstPisMap.put("72", CSTPis.PIS72);
        cstPisMap.put("73", CSTPis.PIS73);
        cstPisMap.put("74", CSTPis.PIS74);
        cstPisMap.put("75", CSTPis.PIS75);
        cstPisMap.put("98", CSTPis.PIS98);
        cstPisMap.put("99", CSTPis.PIS99);
    }

    @Override
    public RetornoValidar validar(String valor) {
        if (cstPisMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo CST Pis, informado %s", valor));
    }
}
