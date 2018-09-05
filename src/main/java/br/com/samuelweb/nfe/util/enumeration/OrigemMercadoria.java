package br.com.samuelweb.nfe.util.enumeration;

public enum OrigemMercadoria implements EnumNfeValue<Integer> {
    NACIONAL(0),
    ESTRANGEIRA_IMPORTACAO_DIRETA(1),
    ESTRANGEIRA_ADQUIRIDA_BRASIL(2),
    NACIONAL_CONTEUDO_IMPORTACAO_SUPERIOR40(3),
    NACIONAL_PROCESSOS_BASICOS(4),
    NACIONAL_CONTEUDO_IMPORTACAO_INFERIOR_IGUAL40(5),
    ESTRANGEIRA_IMPORTACAO_DIRETA_SEM_SIMILAR(6),
    ESTRANGEIRA_ADQUIRIDA_BRASIL_SEM_SIMILAR(7),
    NACIONAL_CONTEUDO_IMPORTACAO_SUPERIOR70(8);
/*
0 - Nacional, exceto as indicadas nos códigos 3, 4, 5 e 8;
1 - Estrangeira - Importação direta, exceto a indicada no código 6;
2 - Estrangeira - Adquirida no mercado interno, exceto a indicada no código 7;
3 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40% e inferior ou igual a 70%;
4 - Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam as legislações citadas nos Ajustes;
5 - Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%;
6 - Estrangeira - Importação direta, sem similar nacional, constante em lista da CAMEX e gás natural;
7 - Estrangeira - Adquirida no mercado interno, sem similar nacional, constante lista CAMEX e gás natural.
8 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 70%;
*/

    private Integer value;

    OrigemMercadoria(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
