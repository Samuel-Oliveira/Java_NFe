package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas.TEnviNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import lombok.extern.java.Log;

@Log
public class EnviarA3 {

    static String montaXmlA3(ConfiguracoesNfe config, TEnviNFe enviNFe) throws NfeException {
        try {
            return XmlNfeUtil.objectToXml(enviNFe, config.getEncode());
        } catch (Exception e) {
            throw new NfeException(e.getMessage());
        }
    }

    static TEnviNFe montaNfeA3(ConfiguracoesNfe config, String xmlAssinado, boolean valida) throws NfeException {
        try {
            xmlAssinado = xmlAssinado.replaceAll(System.lineSeparator(), "");

            log.info("[XML-ASSINADO]: " + xmlAssinado);

            if (valida) {
                new Validar().validaXml(config, xmlAssinado, ServicosEnum.ENVIO);
            }

            return XmlNfeUtil.xmlToObject(xmlAssinado, TEnviNFe.class);
        } catch (Exception e) {
            throw new NfeException(e.getMessage());
        }
    }

}
