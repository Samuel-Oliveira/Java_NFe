package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.LoggerUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.ws.RetryParameter;
import br.com.swconsultoria.nfe.wsdl.NFeAutorizacao.NFeAutorizacao4Stub;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.om.util.StAXParserConfiguration;
import org.apache.axis2.transport.http.HTTPConstants;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Classe Responsavel por Enviar o XML.
 *
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
class Enviar {

	/**
	 * Metodo para Montar a NFE
	 *
	 * @param enviNFe
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	static TEnviNFe montaNfe(ConfiguracoesNfe config, TEnviNFe enviNFe, boolean valida) throws NfeException {

		try {

			/**
			 * Cria o xml
			 */
			String xml = XmlNfeUtil.objectToXml(enviNFe);

			/**
			 * Assina o Xml
			 */
			xml = Assinar.assinaNfe(config, xml, AssinaturaEnum.NFE);

			//Retira Quebra de Linha
			xml = xml.replaceAll(System.lineSeparator(), "");

			LoggerUtil.log(Enviar.class, "[XML-ASSINADO]: " + xml);

			/**
			 * Valida o Xml caso sejá selecionado True
			 */
			if (valida) {
				new Validar().validaXml(config, xml, ServicosEnum.ENVIO);
			}

			return XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);

		} catch (Exception e) {
			throw new NfeException(e.getMessage());
		}

	}

	/**
	 * Metodo para Enviar a NFE.
	 *
	 * @param enviNFe
	 * @param tipoDocumento
	 * @return
	 * @throws NfeException
	 */
    static TRetEnviNFe enviaNfe(ConfiguracoesNfe config, TEnviNFe enviNFe, DocumentoEnum tipoDocumento) throws NfeException {

		try {

			String xml = XmlNfeUtil.objectToXml(enviNFe);

			OMElement ome;
			if (tipoDocumento.equals(DocumentoEnum.NFE)) {
				ome = AXIOMUtil.stringToOM(xml);
			} else {
				OMFactory factory = OMAbstractFactory.getOMFactory();
				ome = factory.getMetaFactory().createOMBuilder(factory, StAXParserConfiguration.NON_COALESCING, new InputSource(new StringReader(xml))).getDocumentElement();
			}

			Iterator<?> children = ome.getChildrenWithLocalName("NFe");
			while (children.hasNext()) {
				OMElement omElementNFe = (OMElement) children.next();
				if ((omElementNFe != null) && ("NFe".equals(omElementNFe.getLocalName()))) {
					omElementNFe.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe", null);
				}
			}

			LoggerUtil.log(Enviar.class, "[XML-ENVIO]: " + xml);

			NFeAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

            NFeAutorizacao4Stub stub = new NFeAutorizacao4Stub(WebServiceUtil.getUrl(config, tipoDocumento, ServicosEnum.ENVIO));

				// Timeout
				if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
					stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
					stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
				}

				//Erro 411 MG
				if (tipoDocumento.equals(DocumentoEnum.NFCE) && config.getEstado().equals(EstadosEnum.MG)) {
					stub._getServiceClient().getOptions().setProperty(HTTPConstants.CHUNKED, false);
				}
				
				if (ObjetoUtil.verifica(config.getRetry()).isPresent()) {
				    RetryParameter.populateRetry(stub, config.getRetry());
				}
				
				
			NFeAutorizacao4Stub.NfeResultMsg result = stub.nfeAutorizacaoLote(dadosMsg);
			LoggerUtil.log(Enviar.class, "[XML-RETORNO]: " + result.getExtraElement().toString());
			return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetEnviNFe.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}