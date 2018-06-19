package br.com.samuelweb.nfe.util.enumeration;

public enum CSOSNIcms implements EnumNfeValue<String> {
    CSOSN_101("101"),
    CSOSN_102("102"),
    CSOSN_103("103"),
    CSOSN_201("201"),
    CSOSN_202("202"),
    CSOSN_203("203"),
    CSOSN_300("300"),
    CSOSN_400("400"),
    CSOSN_500("500"),
    CSOSN_900("900");

    private String value;

    CSOSNIcms(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
