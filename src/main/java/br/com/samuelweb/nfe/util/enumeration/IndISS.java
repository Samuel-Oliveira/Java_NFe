package br.com.samuelweb.nfe.util.enumeration;

public enum IndISS implements EnumNfeValue<Integer> {

    EXIGIVEL(1),
    NAO_INCIDENCIA(2),
    ISENCAO(3),
    EXPORTACAO(4),
    IMUNIDADE(5),
    EXIGIBILIDADE_SUSPENSA_JUDICIAL(6),
    EXIGIBILIDADE_SUSPENSA_ADMINISTRATIVO(7);

    private Integer value;

    IndISS(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
