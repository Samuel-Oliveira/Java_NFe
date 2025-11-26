package br.com.swconsultoria.nfe;

import javax.xml.bind.JAXBException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import lombok.extern.java.Log;

@Log
public class ManifestacaoDestinatarioA3 {

    static TRetEnvEvento eventoManifestacao(ConfiguracoesNfe config, boolean valida,  String xmlAssinado) throws NfeException {
        log.info("[XML-ENVIO]: " + xmlAssinado);
        xmlAssinado = EventosA3.enviarEvento(config, xmlAssinado, ServicosEnum.MANIFESTACAO, valida, DocumentoEnum.NFE);
        return XmlNfeUtil.xmlToObject(xmlAssinado, TRetEnvEvento.class);
    }

    static String montarXML(ConfiguracoesNfe config, TEnvEvento envEvento) throws NfeException {
        try {
            String xml = XmlNfeUtil.objectToXml(envEvento, config.getEncode());
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
            return xml;
        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }
    }


}
