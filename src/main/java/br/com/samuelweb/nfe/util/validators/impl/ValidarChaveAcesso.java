package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;
import org.apache.commons.lang3.StringUtils;

public class ValidarChaveAcesso implements ValidadorCampo<String, Object> {
    @Override
    public RetornoValidar validar(String valor, Object parent) {
        if (StringUtils.isNotBlank(valor)) {
            if (!StringUtils.isNumeric(valor) || valor.length() != 44) {
                return new RetornoValidarImpl(false, String.format(
                        "Chave de acesso referênciada informada inválida, informado %s", valor));
            }
            ValidarCodigoUf validarCodigoUf = new ValidarCodigoUf();
            RetornoValidar retornoValidar = validarCodigoUf.validar(Integer.parseInt(valor.substring(0, 2)), parent);
            if (!retornoValidar.getValido()) {
                return new RetornoValidarImpl(false, String.format(
                        "Chave de acesso referênciada informada inválida, informado %s", valor));
            }
        }
        //todo validar "CNPJ da chave de acesso" "AAMM da chave de acesso" "digito verificado da chave de acesso"
        return new RetornoValidarImpl(true);
    }
}
