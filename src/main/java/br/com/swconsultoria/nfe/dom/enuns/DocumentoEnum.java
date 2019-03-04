package br.com.swconsultoria.nfe.dom.enuns;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 19:55
 */
public enum  DocumentoEnum {

    NFE("NFe", "55"),
    NFCE("NFCe", "65");

    private final String tipo;
    private final String modelo;

    DocumentoEnum(String tipo, String modelo) {
        this.tipo = tipo;
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }
    public String getModelo() {
        return modelo;
    }
}
