package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

public class ValidarPais implements ValidadorCampo<Integer, Object> {
    @Override
    public RetornoValidar validar(Integer valor, Object parent) {
        //todo implementar
        return new RetornoValidarImpl(true);
    }
}
