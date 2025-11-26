package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import javax.xml.bind.JAXBException;

public class CancelarA3 {

    static TRetEnvEvento eventoCancelamento(ConfiguracoesNfe config, boolean valida, DocumentoEnum tipoDocumento, String xmlAssinado)
            throws NfeException {

            String xmlRetorno = EventosA3.enviarEvento(config, xmlAssinado, ServicosEnum.CANCELAMENTO, valida, tipoDocumento);
            return XmlNfeUtil.xmlToObject(xmlRetorno, TRetEnvEvento.class);
    }

    static  br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento eventoCancelamentoSubstituicao(ConfiguracoesNfe config, boolean valida, DocumentoEnum tipoDocumento, String xmlAssinado)
            throws NfeException {

            String xmlRetorno = EventosA3.enviarEvento(config, xmlAssinado, ServicosEnum.CANCELAMENTO_SUBSTITUICAO, valida, tipoDocumento);
            return XmlNfeUtil.xmlToObject(xmlRetorno, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento.class);
    }

    static String montaXmleventoCancelamento(ConfiguracoesNfe config, TEnvEvento enviEvento)
            throws NfeException {
        try {
            String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());

            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            return xml;

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }
    }

    static String montaXmleventoCancelamentoSubstituicao(ConfiguracoesNfe config, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TEnvEvento enviEvento)
            throws NfeException {
        try {
            String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());

            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            return xml;

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }
    }

}
