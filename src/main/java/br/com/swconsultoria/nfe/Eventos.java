package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.ws.RetryParameter;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import lombok.extern.java.Log;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

@Log
class Eventos {

    static String enviarEvento(ConfiguracoesNfe config, String xml, ServicosEnum tipoEvento, boolean valida, boolean assina, DocumentoEnum tipoDocumento)
            throws NfeException {

        try {

            if (assina) {
                xml = Assinar.assinaNfe(config, xml, AssinaturaEnum.EVENTO);
            }

            log.info("[XML-ENVIO-" + tipoEvento + "]: " + xml);

            if (valida) {
                new Validar().validaXml(config, xml, tipoEvento);
            }

            OMElement ome = AXIOMUtil.stringToOM(xml);

            NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg = new NFeRecepcaoEvento4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            String url = WebServiceUtil.getUrl(config, tipoDocumento, tipoEvento);

            NFeRecepcaoEvento4Stub stub = new NFeRecepcaoEvento4Stub(url);
            // Timeout
            if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
            }

            if (ObjetoUtil.verifica(config.getRetry()).isPresent()) {
                RetryParameter.populateRetry(stub, config.getRetry());
            }

            NFeRecepcaoEvento4Stub.NfeResultMsg result = stub.nfeRecepcaoEvento(dadosMsg);

            log.info("[XML-RETORNO-" + tipoEvento + "]: " + result.getExtraElement().toString());
            return result.getExtraElement().toString();
        } catch (RemoteException | XMLStreamException e) {
            throw new NfeException(e.getMessage(),e);
        }

    }
}
