package br.com.samuelweb.nfe;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.exception.NfeValidacaoException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeAutorizacao.NFeAutorizacao4Stub;

/**
 * Classe Responsavel por Enviar o XML.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
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
			String xml = XmlUtil.objectToXml(enviNFe);

			/**
			 * Assina o Xml
			 */
			xml = Assinar.assinaNfe(config, xml, "NFe");

			if (config.isLog()) {
				System.out.println("Xml Assinado: " + xml);
			}

			/**
			 * Valida o Xml caso sejá selecionado True
			 */
			if (valida) {
				String erros = Validar.validaXml(config, xml, Validar.ENVIO);
				if (!ObjetoUtil.isEmpty(erros)) {
					throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
				}
			}

			return XmlUtil.xmlToObject(xml, TEnviNFe.class);

		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

	/**
	 * Metodo para Enviar a NFE.
	 *
	 * @param enviNFe
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	static TRetEnviNFe enviaNfe(ConfiguracoesNfe config, TEnviNFe enviNFe, String tipo) throws NfeException {

		boolean nfce = tipo.equals(ConstantesUtil.TipoDoc_e.NFCE.name());
		String qrCode = "";

		try {
			if (nfce) {
				qrCode = enviNFe.getNFe().get(0).getInfNFeSupl().getQrCode();
				enviNFe.getNFe().get(0).getInfNFeSupl().setQrCode("");
			}

			String xml = XmlUtil.objectToXml(enviNFe);

			if (nfce) {
				enviNFe.getNFe().get(0).getInfNFeSupl().setQrCode(qrCode);
			}

			OMElement ome = AXIOMUtil.stringToOM(xml);

			Iterator<?> children = ome.getChildrenWithLocalName("NFe");
			while (children.hasNext()) {
				OMElement omElementNFe = (OMElement) children.next();
				if ((omElementNFe != null) && ("NFe".equals(omElementNFe.getLocalName()))) {
					omElementNFe.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe", null);
					if (nfce) {

						OMFactory f = OMAbstractFactory.getOMFactory();
						OMText omt = f.createOMText(qrCode, OMElement.CDATA_SECTION_NODE);

						Iterator<?> itInfSupl = omElementNFe.getChildrenWithLocalName("infNFeSupl");
						while (itInfSupl.hasNext()) {
							Object elementInfSupl = itInfSupl.next();
							if (elementInfSupl instanceof OMElement) {
								OMElement omElementInfSupl = (OMElement) elementInfSupl;
								Iterator<?> itqrCode = omElementInfSupl.getChildrenWithLocalName("qrCode");
								while (itqrCode.hasNext()) {
									Object elementQrCode = itqrCode.next();
									if (elementQrCode instanceof OMElement) {
										OMElement omElementQrCode = (OMElement) elementQrCode;
										omElementQrCode.addChild(omt);
									}
								}
							}
						}
					}
				}
			}

			// Adicionado CDATA após OM
			if (nfce) {
				enviNFe.getNFe().get(0).getInfNFeSupl().setQrCode("<![CDATA[" + qrCode + "]]>");
			}

			if (config.isLog()) {
				System.out.println("Xml para Envio: " + ome.toString());
			}

			NFeAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			NFeAutorizacao4Stub stub = new NFeAutorizacao4Stub(
					nfce ? WebServiceUtil.getUrl(config, ConstantesUtil.TipoDoc_e.NFCE, ConstantesUtil.SERVICOS.ENVIO)
							: WebServiceUtil.getUrl(config, ConstantesUtil.TipoDoc_e.NFE, ConstantesUtil.SERVICOS.ENVIO));
			// Timeout
			if (!ObjetoUtil.isEmpty(config.getTimeout())) {
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
						config.getTimeout());
			}
			NFeAutorizacao4Stub.NfeResultMsg result = stub.nfeAutorizacaoLote(dadosMsg);

			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetEnviNFe.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}