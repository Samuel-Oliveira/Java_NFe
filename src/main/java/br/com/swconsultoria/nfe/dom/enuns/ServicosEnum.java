package br.com.swconsultoria.nfe.dom.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 20:03
 */
@Getter
@RequiredArgsConstructor
public enum ServicosEnum {

    STATUS_SERVICO("nfestatusservico_4.00", "consStatServ_v4.00.xsd"),
    ENVIO("nfeautorizacao_4.00", "enviNFe_v4.00.xsd"),
    CONSULTA_RECIBO("nferetautorizacao_4.00", "consReciNFe_v4.00.xsd"),
    CONSULTA_CADASTRO("nfeconsultacadastro_4.00", "ConsCad_v4.00.xsd"),
    CCE("recepcaoevento_4.00", "envCCe_v1.00.xsd"),
    EVENTO_GENERICO("recepcaoevento_4.00", "envEvento_v1.00.xsd"),
    EPEC("recepcaoevento_4.00", "envEPEC_v1.00.xsd"),
    MANIFESTACAO("recepcaoevento_4.00", "envConfRecebto_v1.00.xsd"),
    CANCELAMENTO("recepcaoevento_4.00", "envEventoCancNFe_v1.00.xsd"),
    ATOR_INTERESSADO("recepcaoevento_4.00", "envEventoAtorInteressado_v1.00.xsd"),
    INSUCESSO_ENTREGA("recepcaoevento_4.00", "envEventoInsucessoNFe_v1.00.xsd"),
    CANC_INSUCESSO_ENTREGA("recepcaoevento_4.00", "envEventoCancInsucessoNFe_v1.00.xsd"),
    ECONF("recepcaoevento_4.00", "envEventoEConf_v1.00.xsd"),
    CANC_ECONF("recepcaoevento_4.00", "envEventoCancEConf_v1.00.xsd"),
    CANCELAMENTO_SUBSTITUICAO("recepcaoevento_4.00", "envEventoCancSubst_v1.00.xsd"),
    INUTILIZACAO("nfeinutilizacao_4.00", "inutNFe_v4.00.xsd"),
    CONSULTA_XML("nfeconsultaprotocolo_4.00", "consSitNFe_v4.00.xsd"),
    DISTRIBUICAO_DFE("nfedistribuicaodfe_1.01", "distDFeInt_v1.01.xsd"),
    URL_QRCODE("url-qrcode", null),
    URL_CONSULTANFCE("url-consultanfce", null),
    PROC("procNFe_v4.00", "procNFe_v4.00.xsd");

    private final String servico;
    private final String xsd;
}
