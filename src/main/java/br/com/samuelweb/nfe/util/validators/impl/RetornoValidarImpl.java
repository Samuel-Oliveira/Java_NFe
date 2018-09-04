package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.validators.RetornoValidar;

public class RetornoValidarImpl implements RetornoValidar {

    private boolean valido;
    private String mensagem;

    public RetornoValidarImpl(boolean valido) {
        this.valido = valido;
    }

    public RetornoValidarImpl(boolean valido, String mensagem) {
        this.valido = valido;
        this.mensagem = mensagem;
    }

    @Override
    public boolean getValido() {
        return valido;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }
}
