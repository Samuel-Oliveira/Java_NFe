package br.com.samuelweb.nfe.util.enumeration;


public enum NfeVersao {
    NFE_VERSAO_400("4.00");

    private String value;

    NfeVersao(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}