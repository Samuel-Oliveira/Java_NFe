package br.com.swconsultoria.nfe.dom.enuns;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 20:03
 */
public enum ServicosEnum {

    STATUS_SERVICO("nfestatusservico_4.00", "consStatServ_v4.00.xsd"),
    ENVIO("nfeautorizacao_4.00","enviNFe_v4.00.xsd"),
    CONSULTA_RECIBO("nferetautorizacao_4.00","consReciNFe_v4.00.xsd"),
    CONSULTA_CADASTRO("nfeconsultacadastro_4.00", "ConsCad_v4.00.xsd"),
    CCE("recepcaoevento_4.00", "envCCe_v1.00.xsd"),
    EPEC("recepcaoevento_4.00", "envEPEC_v1.00.xsd"),
    MANIFESTACAO("recepcaoevento_4.00", "envConfRecebto_v1.00.xsd"),
    CANCELAMENTO("recepcaoevento_4.00", "envEventoCancNFe_v1.00.xsd"),
    CANCELAMENTO_SUBSTITUICAO("recepcaoevento_4.00", "envEventoCancSubst_v1.00.xsd"),
    INUTILIZACAO("nfeinutilizacao_4.00", "inutNFe_v4.00.xsd"),
    CONSULTA_XML("nfeconsultaprotocolo_4.00", "consSitNFe_v4.00.xsd"),
    DISTRIBUICAO_DFE("nfedistribuicaodfe_1.01", "distDFeInt_v1.01.xsd"),
    URL_QRCODE("url-qrcode",null),
    URL_CONSULTANFCE("url-consultanfce",null);

    private final String servico;
    private final String xsd;

    ServicosEnum(String servico, String xsd) {
        this.servico = servico;
        this.xsd = xsd;
    }

    public String getServico() {
        return servico;
    }

    public String getXsd() {
        return xsd;
    }
}
