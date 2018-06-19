package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

public class ValidarMunicipio implements ValidadorCampo<Integer> {

    private static final Integer TAMANHO = 7;
    private static final String PESO = "1212120";
    private static final String NAO_VALIDAR = "|2201919|2202251|2201988|2611533|3117836|3152131|4305871|5203939|5203962|";

    @Override
    public RetornoValidar validar(Integer valor) {
        String codigoStr = String.valueOf(valor == null ? "" : valor);
        if ((valor != null && valor == 9999999) || NAO_VALIDAR.indexOf("|" + codigoStr + "|") > 0) {
            return new RetornoValidarImpl(true);
        }
        if (codigoStr.length() != TAMANHO || codigoStr.substring(2, 6).equals("0000")) {
            return new RetornoValidarImpl(false, String.format("Código do IBGE do município informado não é valido. Código informado %d", valor));
        }
        ValidarCodigoUf validarCodigoUf = new ValidarCodigoUf();
        RetornoValidar retornoValidarUf = validarCodigoUf.validar(Integer.parseInt(codigoStr.substring(0, 2)));
        if (!retornoValidarUf.getValido()) {
            return retornoValidarUf;
        }
        if (validarCodigoMunicipio(codigoStr)) {
            return new RetornoValidarImpl(true);
        } else {
            return new RetornoValidarImpl(false, String.format("Código do IBGE do município informado não é valido. Código informado %d", valor));
        }
    }

    private boolean validarCodigoMunicipio(String codigoStr) {
        Integer soma = 0;
        Integer valorCalc;
        String digito;
        for (int i = 0; i < codigoStr.length(); i++) {
            valorCalc = Integer.parseInt(codigoStr.substring(i, i + 1)) * Integer.parseInt(PESO.substring(i, i + 1));
            if (valorCalc > 9) {
                soma = soma + Integer.parseInt(String.valueOf(valorCalc).substring(0, 1)) + Integer.parseInt(String.valueOf(valorCalc).substring(1, 2));
            } else {
                soma = soma + valorCalc;
            }
        }
        digito = String.valueOf((10 - (soma % 10)));
        if ((soma % 10) == 0) {
            digito = "0";
        }
        return digito.equals(String.valueOf(codigoStr.charAt(TAMANHO-1)));
    }
}
