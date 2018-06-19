package br.com.samuelweb.nfe.util.validators.impl;

public class ErrosValidacao {

    private String id;
    private String tag;
    private String descricao;
    private String mensagem;

    public ErrosValidacao(String id, String tag, String descricao, String mensagem) {
        this.id = id;
        this.tag = tag;
        this.descricao = descricao;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "ErrosValidacao{" +
                "id='" + id + '\'' +
                ", tag='" + tag + '\'' +
                ", descricao='" + descricao + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
