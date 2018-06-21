package br.com.samuelweb.nfe.util.enumeration;

public enum ModalidadeFrete implements EnumNfeValue<Integer>{

    CONTRATACAO_DO_FRETE_POR_CONTA_DO_REMETENTE_CIF(0),
    CONTRATACAO_DO_FRETE_POR_CONTA_DO_DESTINATARIO_FOB(1),
    CONTRATACAO_DO_FRETE_POR_CONTA_DE_TERCEIROS(2),
    TRANSPORTE_PROPRIO_POR_CONTA_DO_REMETENTE(3),
    TRANSPORTE_PROPRIO_POR_CONTA_DO_DESTINATARIO(4),
    SEM_OCORRENCIA_DE_TRANSPORTE(9);

    private Integer value;

    ModalidadeFrete(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
