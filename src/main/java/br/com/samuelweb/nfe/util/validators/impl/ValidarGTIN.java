package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.model.Prod;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class ValidarGTIN implements ValidadorCampo<String, Prod> {
    @Override
    public RetornoValidar validar(String valor, Prod prod) {
        if (valor.equals("SEM GTIN")) {
            return new RetornoValidarImpl(true);
        }
        if (!StringUtils.isNumeric(valor)) {
            return new RetornoValidarImpl(false, String.format(
                    "Código GTIN inválido, para o produto %s - %s o código GTIN deve conter somente números, valor informado %s."
                    , prod.getcProd(), prod.getxProd(), valor));
        }
        if (!Arrays.asList(8, 12, 13, 14).contains(valor.length())){
            return new RetornoValidarImpl(false, String.format(
                    "Código GTIN inválido, para o produto %s - %s o código GTIN deve ter 8, 12, 13 ou 14 caracteres, valor informado %s."
                    , prod.getcProd(), prod.getxProd(), valor));
        }

        Integer soma = 0;
        int resultado;
        Integer base10;
        for (int i = 0; i <= valor.length() - 2; i++) {
            //Se for GTIN-13 multiplica todas as posições pares menos a última por 1 e as ímpares por 3. Nos outros tamanhos, faz o inverso
            if (valor.length() == 13) {
                soma += Integer.parseInt(String.valueOf(valor.charAt(i))) * ((i % 2 == 0) ? 1 : 3);
            } else {
                soma += Integer.parseInt(String.valueOf(valor.charAt(i))) * ((i % 2 == 0) ? 3 : 1);
            }
        }

        //Procura pelo número de base 10 mais próximo do total somado (arredondando sempre para cima, se necessário)
        base10 = soma;
        if (base10 % 10 != 0) {
            while (base10 % 10 != 0) {
                base10 += 1;
            }
        }
        //Diminui o total do número de base 10. O resultado deve ser o último digito do código de barras
        resultado = base10 - soma;
        if (resultado != Integer.parseInt(String.valueOf(valor.charAt(valor.length() - 1)))) {
            return new RetornoValidarImpl(false, String.format(
                    "Código GTIN inválido, para o produto %s - %s o dígito verificador não confere, valor informado %s."
                    , prod.getcProd(), prod.getxProd(), valor));

        }
        return new RetornoValidarImpl(true);
    }
}
