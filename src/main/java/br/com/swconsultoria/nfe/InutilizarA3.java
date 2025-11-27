package br.com.swconsultoria.nfe;

import java.rmi.RemoteException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import lombok.extern.java.Log;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;

@Log
public class InutilizarA3 {

    static TRetInutNFe inutiliza(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, boolean validar, String xmlAssinado)
            throws NfeException {
        try {

            log.info("[XML-ENVIO]: " + xmlAssinado);

            if (validar) {
                new Validar().validaXml(config, xmlAssinado, ServicosEnum.INUTILIZACAO);
            }

            OMElement ome = AXIOMUtil.stringToOM(xmlAssinado);

            NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = new NFeInutilizacao4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NFeInutilizacao4Stub stub = new NFeInutilizacao4Stub(
                    WebServiceUtil.getUrl(config, tipoDocumento, ServicosEnum.INUTILIZACAO));

            if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
            }
            NFeInutilizacao4Stub.NfeResultMsg result = stub.nfeInutilizacaoNF(dadosMsg);

            log.info("[XML-RETORNO]: " + result.getExtraElement().toString());
            return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetInutNFe.class);

        } catch (RemoteException | XMLStreamException e) {
            throw new NfeException(e.getMessage());
        }

    }

    static String montaXmlInutlizacao(ConfiguracoesNfe config, TInutNFe inutNFe)
            throws NfeException {
        try {

            String xml = XmlNfeUtil.objectToXml(inutNFe, config.getEncode());
            return xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }
    }
}
