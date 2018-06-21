package br.com.samuelweb.nfe.util.enumeration;

public enum SimNao implements EnumNfeValue<Integer> {

    SIM(1),
    NAO(2);

    private Integer value;

    SimNao(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
