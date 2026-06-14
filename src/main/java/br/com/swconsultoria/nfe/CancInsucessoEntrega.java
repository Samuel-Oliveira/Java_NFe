package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamentoInsucessoEntrega;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamentoInsucessoEntrega;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br
 */
class CancInsucessoEntrega {

    private CancInsucessoEntrega() {
    }

    static TRetEnvEventoCancelamentoInsucessoEntrega eventoCancInsuccessoEntrega(ConfiguracoesNfe config, TEnvEventoCancelamentoInsucessoEntrega enviEvento, boolean valida)
            throws NfeException {

        try {

            String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            xml = Eventos.enviarEvento(config, xml, ServicosEnum.CANC_INSUCESSO_ENTREGA, valida, true, DocumentoEnum.NFE);

            return XmlNfeUtil.xmlToObject(xml, TRetEnvEventoCancelamentoInsucessoEntrega.class);

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage(), e);
        }

    }

}
