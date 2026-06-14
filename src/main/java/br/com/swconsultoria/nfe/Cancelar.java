package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamento;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br Data: 28/09/2017 - 11:11
 */
class Cancelar {

    static TRetEnvEventoCancelamento eventoCancelamento(ConfiguracoesNfe config, TEnvEventoCancelamento enviEvento, boolean valida, DocumentoEnum tipoDocumento)
            throws NfeException {

        try {

            String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            xml = Eventos.enviarEvento(config, xml, ServicosEnum.CANCELAMENTO, valida, true, tipoDocumento);

            return XmlNfeUtil.xmlToObject(xml, TRetEnvEventoCancelamento.class);

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage(), e);
        }

    }

    static br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamentoSubstituicao eventoCancelamentoSubstituicao(ConfiguracoesNfe config, br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamentoSubstituicao enviEvento, boolean valida)
            throws NfeException {

        try {

            String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            xml = Eventos.enviarEvento(config, xml, ServicosEnum.CANCELAMENTO_SUBSTITUICAO, valida, true, DocumentoEnum.NFCE);

            return XmlNfeUtil.xmlToObject(xml, br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamentoSubstituicao.class);

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage(), e);
        }

    }

}
