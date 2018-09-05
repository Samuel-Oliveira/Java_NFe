package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarIndIeDestinatario implements ValidadorCampo<Integer, Object> {
    private static Map<Integer, String> tipoDanfeMap =  new HashMap<>();
    static {
        tipoDanfeMap.put(1, "Contribuinte ICMS (informar a IE do destinatário)");
        tipoDanfeMap.put(2, "Contribuinte isento de Inscrição no cadastro de Contribuintes do ICMS");
        tipoDanfeMap.put(9, "Não Contribuinte, que pode ou não possuir Inscrição Estadual no Cadastro de Contribuintes do ICMS. Nota 1: No caso de NFC-e informar indIEDest=9 e não informar a tag IE do destinatário; Nota 2: No caso de operação com o Exterior informar indIEDest=9 e não informar a tag IE do destinatário; Nota 3: No caso de Contribuinte Isento de Inscrição (indIEDest=2), não informar a tag IE do destinatário");
    }

    @Override
    public RetornoValidar validar(Integer valor, Object parent) {
        if (tipoDanfeMap.get(valor) != null) {
            return new RetornoValidarImpl(true);
        }
        return new RetornoValidarImpl(false, String.format("Valor inválido para o campo Indicador da IE do Destinatário, informado %d", valor));
    }
}
