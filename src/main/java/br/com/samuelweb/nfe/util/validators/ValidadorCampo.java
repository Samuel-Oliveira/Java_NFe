package br.com.samuelweb.nfe.util.validators;

public interface ValidadorCampo<T, P> {
    RetornoValidar validar(T valor, P parent);
}
