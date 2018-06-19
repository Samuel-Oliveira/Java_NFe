package br.com.samuelweb.nfe.util.enumeration;

public enum ModeloDocumento implements EnumNfeValue<String> {

    NFE("55"), //NF-e emitida em substituição ao modelo 1 ou 1A;
    NFCE("65");//NFC-e, utilizada nas operações de venda no varejo (a critério da UF aceitar este modelo de documento).

    private String value;

    ModeloDocumento(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
