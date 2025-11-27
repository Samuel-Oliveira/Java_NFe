package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envcce.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import javax.xml.bind.JAXBException;

public class CartaCorrecaoA3 {

    static TRetEnvEvento eventoCCe(ConfiguracoesNfe config, boolean valida, String xmlAssinado)
            throws NfeException {

            String xmlRetorno = EventosA3.enviarEvento(config, xmlAssinado, ServicosEnum.CCE, valida, DocumentoEnum.NFE);

            return XmlNfeUtil.xmlToObject(xmlRetorno, TRetEnvEvento.class);
    }

    static String montaXmlCartaCorrecao(ConfiguracoesNfe config, TEnvEvento enviEvento)
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
