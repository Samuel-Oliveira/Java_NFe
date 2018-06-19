package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.consReciNFe.TConsReciNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeRetAutorizacao.NFeRetAutorizacao4Stub;

/**
 * Classe Responsavel Por pegar o Retorno da NFE, apos o Envio.
 *
 * @author Samuel Oliveira
 */
class ConsultaRecibo {

	/**
	 * Metodo Responsavel Por Pegar o Xml De Retorno.
	 *
	 * @param tConsReciNFe
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */

	static TRetConsReciNFe reciboNfe(ConfiguracoesNfe config, String recibo, String tipo) throws NfeException {

		try {

			/**
			 * Informaçoes do Certificado Digital.
			 */

			TConsReciNFe consReciNFe = new TConsReciNFe();
			consReciNFe.setVersao(config.getVersaoNfe());
			consReciNFe.setTpAmb(config.getAmbiente());
			consReciNFe.setNRec(recibo);

			String xml = XmlUtil.objectToXml(consReciNFe);

			OMElement ome = AXIOMUtil.stringToOM(xml);
			NFeRetAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeRetAutorizacao4Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			NFeRetAutorizacao4Stub stub = new NFeRetAutorizacao4Stub(tipo.equals(ConstantesUtil.NFCE)
					? WebServiceUtil.getUrl(config, ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.CONSULTA_RECIBO)
					: WebServiceUtil.getUrl(config, ConstantesUtil.NFE, ConstantesUtil.SERVICOS.CONSULTA_RECIBO));
			// Timeout
			if (!ObjetoUtil.isEmpty(config.getTimeout())) {
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
						config.getTimeout());
			}
			NFeRetAutorizacao4Stub.NfeResultMsg result = stub.nfeRetAutorizacaoLote(dadosMsg);

			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsReciNFe.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}
}
