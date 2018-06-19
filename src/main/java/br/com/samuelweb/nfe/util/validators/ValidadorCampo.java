package br.com.samuelweb.nfe.util.validators;

public interface ValidadorCampo<T> {
    RetornoValidar validar(T valor);
}
