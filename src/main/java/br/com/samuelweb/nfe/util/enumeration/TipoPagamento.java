package br.com.samuelweb.nfe.util.enumeration;

public enum TipoPagamento implements EnumNfeValue<String>{

    DINHEIRO("01"),
    CHEQUE("02"),
    CARTAO_DE_CREDITO("03"),
    CARTAO_DE_DEBITO("04"),
    CREDITO_LOJA("05"),
    VALE_ALIMENTACAO("10"),
    VALE_REFEICAO("11"),
    VALE_PRESENTE("12"),
    VALE_COMBUSTIVEL("13"),
    DUPLICATA_MERCANTIL("14"),
    BOLETO_BANCARIO("15"),
    SEM_PAGAMENTO("90"),
    OUTROS("99");

    private String value;

    TipoPagamento(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }


}
