package br.com.samuelweb.nfe.util.model.imposto;

public interface MontaImposto<T, D> {
    void build(T imposto, D objeto);
}
