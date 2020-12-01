package br.com.swconsultoria.nfe.dom;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 04/03/2019 - 10:34
 */
public class EventoEpec {

    private String tipoNF;
    private String ieEmitente;
    private String ieDestinatario;
    private EstadosEnum estadoDestinatario;
    private String cnpjDestinatario;
    private String cpfDestinatario;
    private String vNF;
    private String vICMS;
    private String vST;

    public String getTipoNF() {
        return tipoNF;
    }

    public void setTipoNF(String tipoNF) {
        this.tipoNF = tipoNF;
    }

    public String getIeEmitente() {
        return ieEmitente;
    }

    public void setIeEmitente(String ieEmitente) {
        this.ieEmitente = ieEmitente;
    }

    public String getIeDestinatario() {
        return ieDestinatario;
    }

    public void setIeDestinatario(String ieDestinatario) {
        this.ieDestinatario = ieDestinatario;
    }

    public EstadosEnum getEstadoDestinatario() {
        return estadoDestinatario;
    }

    public void setEstadoDestinatario(EstadosEnum estadoDestinatario) {
        this.estadoDestinatario = estadoDestinatario;
    }

    public String getCnpjDestinatario() {
        return cnpjDestinatario;
    }

    public void setCnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
    }

    public String getvNF() {
        return vNF;
    }

    public void setvNF(String vNF) {
        this.vNF = vNF;
    }

    public String getvICMS() {
        return vICMS;
    }

    public void setvICMS(String vICMS) {
        this.vICMS = vICMS;
    }

    public String getvST() {
        return vST;
    }

    public void setvST(String vST) {
        this.vST = vST;
    }

    public String getCpfDestinatario() {
        return cpfDestinatario;
    }

    public void setCpfDestinatario(String cpfDestinatario) {
        this.cpfDestinatario = cpfDestinatario;
    }
}
