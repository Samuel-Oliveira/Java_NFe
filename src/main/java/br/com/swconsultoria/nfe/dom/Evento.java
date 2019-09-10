package br.com.swconsultoria.nfe.dom;

import br.com.swconsultoria.nfe.dom.enuns.ManifestacaoEnum;

import java.time.LocalDateTime;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 04/03/2019 - 10:34
 */
public class Evento {

    private String chave;
    private String protocolo;
    private String motivo;
    private String cpf;
    private String cnpj;
    private String chaveSusbstituta;
    private LocalDateTime dataEvento;
    private int sequencia;
    private ManifestacaoEnum tipoManifestacao;
    private EventoEpec eventoEpec;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getSequencia() {
        return sequencia;
    }

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }

    public ManifestacaoEnum getTipoManifestacao() {
        return tipoManifestacao;
    }

    public void setTipoManifestacao(ManifestacaoEnum tipoManifestacao) {
        this.tipoManifestacao = tipoManifestacao;
    }

    public EventoEpec getEventoEpec() {
        return eventoEpec;
    }

    public void setEventoEpec(EventoEpec eventoEpec) {
        this.eventoEpec = eventoEpec;
    }

    public String getChaveSusbstituta() {
        return chaveSusbstituta;
    }

    public void setChaveSusbstituta(String chaveSusbstituta) {
        this.chaveSusbstituta = chaveSusbstituta;
    }
}
