package br.com.samuelweb.nfe.util.enumeration;

public enum DeterminacaoBaseIcms implements EnumNfeValue<Integer> {

    MARGEM_VALOR_AGREGADO(0),
    PAUTA(1),
    PRECO_TABELADO(2),
    VALOR_OPERACAO(3);

  /*0=Margem Valor Agregado (%);
	1=Pauta (Valor);
	2=Preço Tabelado Máx. (valor);
	3=Valor da operação.*/

    private Integer value;

    DeterminacaoBaseIcms(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
