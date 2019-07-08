package br.com.swconsultoria.nfe.dom.enuns;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 19:55
 */
public enum AssinaturaEnum {

    NFE("NFe","infNFe"),
    INUTILIZACAO("infInut","infInut"),
    EVENTO("evento","infEvento");

    private final String tipo;
    private final String tag;

    AssinaturaEnum(String tipo,String tag) {
        this.tipo = tipo;
        this.tag = tag;
    }

    public String getTipo() {
        return tipo;
    }
    public String getTag() {
        return tag;
    }
}
