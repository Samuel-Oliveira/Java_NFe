package br.com.samuelweb.nfe.util.validators.impl;

public class ErrosValidacao {
    public static final String ERRO_VALIDACAO_PATTERN = "{mensagem} ao campo {descricao} para {descricaoGrupo} (ID='{id}' Tag='{tag}')";
    private String id;
    private String tag;
    private String descricao;
    private String mensagem;
    private String descricaoGrupo;

    public ErrosValidacao(String id, String tag, String descricao, String mensagem, String descricaoGrupo) {
        this.id = id;
        this.tag = tag;
        this.descricao = descricao;
        this.mensagem = mensagem;
        this.descricaoGrupo = descricaoGrupo;
    }

    @Override
    public String toString() {
        return this.format(ERRO_VALIDACAO_PATTERN);
    }
    public String format(String pattern) {
        return pattern
                .replace("{id}", id)
                .replace("{tag}",  tag)
                .replace("{descricao}",  descricao)
                .replace("{mensagem}",  mensagem)
                .replace("{descricaoGrupo}",  descricaoGrupo);
    }
}
