package br.com.samuelweb.nfe.util.model;

public class InutilizacaoDados {

    private Long idEmpresa;
    private Integer codigoUf;
    private Integer ano;
    private String cnpj;
    private String modelo;
    private String serie;
    private Long numeroInicial;
    private Long numeroFinal;
    private String justificativa;

    public String getId(){
        //ID + UF + Ano (2 posições) + CNPJ + modelo + série + número inicial e número final
        return "ID"
                + codigoUf.toString()
                + cnpj
                + modelo
                + serie
                + numeroInicial.toString()
                + numeroFinal.toString();
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(Integer codigoUf) {
        this.codigoUf = codigoUf;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(Long numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public Long getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(Long numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}
