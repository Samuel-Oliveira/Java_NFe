package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 28/09/2017 - 11:11
 */
 class Cancelar {

    static TEnvEvento criaEventoCancelamento(String chave, String protocolo, String cnpj, String data,String motivo) throws NfeException {

        ConfiguracoesIniciaisNfe configuracoesNfe = CertificadoUtil.iniciaConfiguracoes();

        String id = "ID"+ ConstantesUtil.EVENTO.CANCELAR + chave + "01";

        TEnvEvento enviEvento = new TEnvEvento();
        enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        enviEvento.setIdLote("1");

        TEvento eventoCancela = new TEvento();
        eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

        TEvento.InfEvento infoEvento = new TEvento.InfEvento();
        infoEvento.setId(id);
        infoEvento.setChNFe(chave);
        infoEvento.setCOrgao(String.valueOf(configuracoesNfe.getEstado().getCodigoIbge()));
        infoEvento.setTpAmb(configuracoesNfe.getAmbiente());
        infoEvento.setCNPJ(cnpj);

        infoEvento.setDhEvento(data);
        infoEvento.setTpEvento(ConstantesUtil.EVENTO.CANCELAR);
        infoEvento.setNSeqEvento("1");
        infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

        TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
        detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        detEvento.setDescEvento("Cancelamento");
        detEvento.setNProt(protocolo);
        detEvento.setXJust(motivo);
        infoEvento.setDetEvento(detEvento);
        eventoCancela.setInfEvento(infoEvento);
        enviEvento.getEvento().add(eventoCancela);

        return enviEvento;
    }

    static TRetEnvEvento eventoCancelamento(TEnvEvento enviEvento, boolean valida, String tipo) throws NfeException {

        try {

            String xml = XmlUtil.objectToXml(enviEvento);
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            xml = Eventos.enviarEvento(xml,ConstantesUtil.EVENTO.CANCELAR,valida, tipo);

            return XmlUtil.xmlToObject(xml, TRetEnvEvento.class);

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }

}
