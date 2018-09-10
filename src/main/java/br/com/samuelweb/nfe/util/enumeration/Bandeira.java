package br.com.samuelweb.nfe.util.enumeration;

public enum Bandeira implements EnumNfeValue<Integer>{

    VISA(1),
    MASTERCARD(2),
    AMERICAN_EXPRESS(3),
    SOROCRED(4),
    DINERS_CLUB(5),
    ELO(6),
    HIPERCARD(7),
    AURA(8),
    CABAL(9),
    OUTROS(99);


    private Integer value;

    Bandeira(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }


}
