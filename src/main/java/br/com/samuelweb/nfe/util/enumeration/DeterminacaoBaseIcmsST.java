package br.com.samuelweb.nfe.util.enumeration;

public enum DeterminacaoBaseIcmsST implements EnumNfeValue<Integer> {
    PRECO_TABELADO(0),
    LISTA_NEGATIVA(1),
    LISTA_POSITIVA(2),
    LISTA_NEUTRA(4),
    MARGEM_VALOR_AGREGADO(4),
    PAUTA(5);

    /*
    0=Preço tabelado ou máximo sugerido;
    1=Lista Negativa (valor);
    2=Lista Positiva (valor);
    3=Lista Neutra (valor);
    4=Margem Valor Agregado (%);
    5=Pauta (valor);
    */

    private Integer value;

    DeterminacaoBaseIcmsST(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
