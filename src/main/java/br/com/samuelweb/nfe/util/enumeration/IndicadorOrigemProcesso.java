package br.com.samuelweb.nfe.util.enumeration;

public enum IndicadorOrigemProcesso implements EnumNfeValue<Integer> {

    SEFAZ(0),
    JUSTICA_FEDERAL(1),
    JUSTICA_ESTADUAL(2),
    SECEX_RFB(3),
    OUTROS(9);

    private Integer value;

    IndicadorOrigemProcesso(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
