package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

public class ValidarPais implements ValidadorCampo<Integer> {
    @Override
    public RetornoValidar validar(Integer valor) {
        //todo implementar
        return new RetornoValidarImpl(true);
    }
}
