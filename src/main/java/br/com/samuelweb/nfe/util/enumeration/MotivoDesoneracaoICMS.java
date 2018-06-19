package br.com.samuelweb.nfe.util.enumeration;

public enum MotivoDesoneracaoICMS implements EnumNfeValue<Integer> {
    TAXI(1),
    PRODUTOR_AGROPECUARIO(3),
    FROTISTA_LOCADORA(4),
    DIPLOMATICO_CONSULAR(5),
    AMAZONIA_LIVRE_COMERCIO(6),
    SUFRAMA(7),
    VENDA_ORGAOS_PUBLICOS(8),
    OUTROS(9),
    DEFICIENTE_CONDUTOR(10),
    DEFICIENTE_NAO_CONDUTOR(11),
    ORGAO_FOMENTO(12),
    OLIMPIADA_RIO_2016(16),
    SOLICITADO_FISCO(90);

    private Integer value;

    MotivoDesoneracaoICMS(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
