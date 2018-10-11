package br.com.samuelweb.nfe.util.enumeration;

public enum RegimeTributario implements EnumNfeValue<Integer> {

    MICROEMPRESA_MUNICIPAL(1),
    ESTIMATIVA(2),
    SOCIEDADE_DE_PROFISSIONAIS(3),
    COOPERATIVA(4),
    MICROEMPRESARIO_INDIVIDUAL_MEI(5),
    MICROEMPRESARIO_E_EMPRESA_PEQUENO_PORTE_ME_EPP(6);

    private Integer value;

    RegimeTributario(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
