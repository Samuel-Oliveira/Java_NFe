package br.com.swconsultoria.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.LoggerUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;

public class InutilizarA3 {


	/**
	 * Inutilizar numeração de nota fiscal com cerificado A3
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param tipo
	 * @param validar
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	
	static TRetInutNFe inutiliza(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, boolean validar, String xmlAssinado)
			throws NfeException {

		try {

			LoggerUtil.log(Inutilizar.class, "[XML-ENVIO]: " + xmlAssinado);

			if (validar) {
				new Validar().validaXml(config, xmlAssinado, ServicosEnum.INUTILIZACAO);
			}

			OMElement ome = AXIOMUtil.stringToOM(xmlAssinado);

			NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = new NFeInutilizacao4Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			NFeInutilizacao4Stub stub = new NFeInutilizacao4Stub(
					WebServiceUtil.getUrl(config, tipoDocumento, ServicosEnum.INUTILIZACAO));

			// Timeout
			if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
						config.getTimeout());
			}

			NFeInutilizacao4Stub.NfeResultMsg result = stub.nfeInutilizacaoNF(dadosMsg);

			LoggerUtil.log(Inutilizar.class, "[XML-RETORNO]: " + result.getExtraElement().toString());
			return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetInutNFe.class);
		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}


	/**
	 * Metodo retorna o XML da inutilizacao sem assinatura
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * 
	 * @param config
	 * @param id
	 * @param motivo
	 * @param tipo
	 * @return
	 * @throws JAXBException
	 * @throws NfeException
	 */	
	static String montaXmlInutlizacao(ConfiguracoesNfe config, TInutNFe inutNFe, DocumentoEnum tipoDocument)
			throws NfeException {

		try {

			String xml = XmlNfeUtil.objectToXml(inutNFe);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
			return xml;
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}

}
