package br.com.samuelweb.nfe.util.enumeration;

public enum TipoPagamento implements EnumNfeValue<Integer>{

    DINHEIRO(1),
    CHEQUE(2),
    CARTAO_DE_CREDITO(3),
    CARTAO_DE_DEBITO(4),
    CREDITO_LOJA(5),
    VALE_ALIMENTACAO(10),
    VALE_REFEICAO(11),
    VALE_PRESENTE(12),
    VALE_COMBUSTIVEL(13),
    DUPLICATA_MERCANTIL(14),
    BOLETO_BANCARIO(15),
    SEM_PAGAMENTO(90),
    OUTROS(99);

    private Integer value;

    TipoPagamento(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }


}
