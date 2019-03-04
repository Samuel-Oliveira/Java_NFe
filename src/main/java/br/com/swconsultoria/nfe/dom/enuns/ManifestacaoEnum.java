package br.com.swconsultoria.nfe.dom.enuns;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 20:02
 */
public enum ManifestacaoEnum {

    CONFIRMACAO_DA_OPERACAO     ("210200",  "Confirmação da Operação",      "Confirmacao da Operacao"),
    CIENCIA_DA_OPERACAO         ("210210",  "Ciência da Emissão",      "Ciencia da Operacao"),
    DESCONHECIMENTO_DA_OPERACAO ("210220",  "Desconhecimento da Operação",  "Desconhecimento da Operacao"),
    OPERACAO_NAO_REALIZADA      ("210240",  "Operação não Realizada",       "Operacao nao Realizada");

    private final String codigo;
    private final String descricao;
    private final String valor;

    ManifestacaoEnum(String codigo, String descricao, String valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @return TipoManifestacao
     */
    public static ManifestacaoEnum getTipo(String codigo){
        ManifestacaoEnum tipo = null;
        switch (codigo) {

            case "210200":
                tipo = CONFIRMACAO_DA_OPERACAO;
                break;
            case "210210":
                tipo = CIENCIA_DA_OPERACAO;
                break;
            case "210220":
                tipo = DESCONHECIMENTO_DA_OPERACAO;
                break;
            case "210240":
                tipo = OPERACAO_NAO_REALIZADA;
                break;
            default:
                break;
        }

        return tipo;
    }
}
