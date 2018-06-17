package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.exception.NfeValidacaoException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

class Eventos {

    static String enviarEvento(ConfiguracoesNfe config, String xml, String tipoEvento, boolean valida, String tipo)
            throws NfeException {

        try {

            xml = Assinar.assinaNfe(config, xml, Assinar.EVENTO);

            if (valida) {
                String erros = "";
                switch (tipoEvento) {
                    case ConstantesUtil.EVENTO.CANCELAR:
                        erros = Validar.validaXml(config, xml, Validar.CANCELAR);
                        break;
                    case ConstantesUtil.EVENTO.CCE:
                        erros = Validar.validaXml(config, xml, Validar.CCE);
                        break;
                    case ConstantesUtil.EVENTO.EPEC:
                        erros = Validar.validaXml(config, xml, Validar.CCE);
                        break;
                    case ConstantesUtil.EVENTO.MANIFESTACAO:
                        erros = Validar.validaXml(config, xml, Validar.MANIFESTAR);
                        break;
                    default:
                        break;
                }

                if (!ObjetoUtil.isEmpty(erros)) {
                    throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
                }
            }

            if (config.isLog()) {
                System.out.println("Xml Evento: " + xml);
            }

            OMElement ome = AXIOMUtil.stringToOM(xml);

            NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg = new NFeRecepcaoEvento4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            String url;
            if (tipoEvento.equals(ConstantesUtil.EVENTO.MANIFESTACAO)) {
                url = WebServiceUtil.getUrl(config, ConstantesUtil.NFE, ConstantesUtil.SERVICOS.MANIFESTACAO);
            } else {
                url = WebServiceUtil.getUrl(config, tipo, ConstantesUtil.SERVICOS.EVENTO);
            }

            NFeRecepcaoEvento4Stub stub = new NFeRecepcaoEvento4Stub(url);
            // Timeout
            if (!ObjetoUtil.isEmpty(config.getTimeout())) {
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                        config.getTimeout());
            }
            NFeRecepcaoEvento4Stub.NfeResultMsg result = stub.nfeRecepcaoEvento(dadosMsg);

            return result.getExtraElement().toString();
        } catch (RemoteException | XMLStreamException e) {
            throw new NfeException(e.getMessage());
        }

    }
}