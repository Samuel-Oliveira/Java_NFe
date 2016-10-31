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

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.UrlWebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envinfe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema.envinfe.TRetEnviNFe;
import br.inf.portalfiscal.www.nfe.wsdl.NfeAutorizacao.NfeAutorizacaoStub;
import br.inf.portalfiscal.www.nfe.wsdl.NfeAutorizacao.NfeAutorizacaoStub.NfeAutorizacaoLoteResult;

/**
 * Classe Responsavel por Enviar o XML.
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */
public class Enviar {

	private static NfeAutorizacaoLoteResult result;
	private static ConfiguracoesIniciaisNfe configuracoesNfe;
	private static CertificadoUtil certUtil;

	/**
	 * Metodo para Montar a NFE..
	 * 
	 * @param TEnviNFe
	 * @return String
	 * @throws NfeException
	 */
	public static TEnviNFe montaNfe(TEnviNFe enviNFe, boolean valida) throws NfeException {

		certUtil = new CertificadoUtil();

		try {

			/**
			 * Informacoes do Certificado Digital.
			 */
			certUtil.iniciaConfiguracoes();

			/**
			 * Cria o xml
			 */
			String xml = XmlUtil.objectToXml(enviNFe);

			/**
			 * Assina o Xml
			 */
			xml = Assinar.assinaNfe(xml, Assinar.NFE);

			/**
			 * Valida o Xml caso sejá selecionado True
			 */
			if(valida){
				String erros = Validar.validaXml(xml, Validar.ENVIO);
				if (!ObjetoUtil.isEmpty(erros)) {
					throw new NfeException("Erro Na Validação do Xml: " + erros);
				}
			}

			System.out.println("Xml Assinado: " + xml);

			return XmlUtil.xmlToObject(xml, TEnviNFe.class);

		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

	/**
	 * Metodo para Enviar a NFE.
	 * 
	 * @param nfe
	 * @return Nfe
	 * @throws NfeException
	 */
	public static TRetEnviNFe enviaNfe(TEnviNFe enviNFe) throws NfeException {

		certUtil = new CertificadoUtil();
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
		String qrCode = "";

		try {
			if (enviNFe.getNFe().get(0).getInfNFe().getIde().getMod().equals("65")) {
				qrCode = enviNFe.getNFe().get(0).getInfNFeSupl().getQrCode();
				enviNFe.getNFe().get(0).getInfNFeSupl().setQrCode("");
			}

			String xml = XmlUtil.objectToXml(enviNFe);
			
			if (enviNFe.getNFe().get(0).getInfNFe().getIde().getMod().equals("65")) {
				enviNFe.getNFe().get(0).getInfNFeSupl().setQrCode(qrCode);
			}
			
			OMElement ome = AXIOMUtil.stringToOM(xml);

			Iterator<?> children = ome.getChildrenWithLocalName("NFe");
			while (children.hasNext()) {
				OMElement omElementNFe = (OMElement) children.next();
				if ((omElementNFe != null) && ("NFe".equals(omElementNFe.getLocalName()))) {
					omElementNFe.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe", null);
					if (enviNFe.getNFe().get(0).getInfNFe().getIde().getMod().equals("65")) {

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
			
			System.out.println("Xml para Envio: " + ome.toString()); 
			
			NfeAutorizacaoStub.NfeDadosMsg dadosMsg = new NfeAutorizacaoStub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			NfeAutorizacaoStub.NfeCabecMsg nfeCabecMsg = new NfeAutorizacaoStub.NfeCabecMsg();

			/**
			 * Codigo do Estado.
			 */
			nfeCabecMsg.setCUF(String.valueOf(configuracoesNfe.getUf()));

			/**
			 * Versao do XML
			 */
			nfeCabecMsg.setVersaoDados(configuracoesNfe.getVersaoNfe());

			NfeAutorizacaoStub.NfeCabecMsgE nfeCabecMsgE = new NfeAutorizacaoStub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

			NfeAutorizacaoStub stub = new NfeAutorizacaoStub(UrlWebServiceUtil.enviarSincrono().toString());
			result = stub.nfeAutorizacaoLote(dadosMsg, nfeCabecMsgE);

			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetEnviNFe.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}
