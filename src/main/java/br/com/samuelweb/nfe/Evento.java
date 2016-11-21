package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.inf.portalfiscal.www.nfe.wsdl.RecepcaoEvento.RecepcaoEventoStub;

public class Evento {

	public static RecepcaoEventoStub.NfeRecepcaoEventoResult result;
	private static ConfiguracoesIniciaisNfe configuracoesNfe;
	private static CertificadoUtil certUtil;
	
	
	public static TRetEnvEvento eventoCancelamento(TEnvEvento evento, boolean valida, String tipo) throws NfeException{
		
		try {
			
			String xml = XmlUtil.objectToXml(evento);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
			xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
			
			xml = evento(xml,"cancelar",valida,tipo);
			
			return XmlUtil.xmlToObject(xml, TRetEnvEvento.class);
			
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
		
	}
	
	public static br.inf.portalfiscal.nfe.schema.retEnvConfRecebto.TRetEnvEvento eventoManifestacao(br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento evento, boolean valida) throws NfeException{
		try {
			
			String xml = XmlUtil.objectToXml(evento);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
			xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
			
			xml = evento(xml,"manifestar",valida, ConstantesUtil.NFE);
			
			return XmlUtil.xmlToObject(xml, br.inf.portalfiscal.nfe.schema.retEnvConfRecebto.TRetEnvEvento.class);
			
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
		
	}
	
	public static br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento eventoCce(br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento evento, boolean valida, String tipo) throws NfeException{
		
		try {
			
			String xml = XmlUtil.objectToXml(evento);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
			xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
			
			xml = evento(xml,"cce",valida , tipo);
			
			return XmlUtil.xmlToObject(xml, br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento.class);
			
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
		
	}
	

	private static String evento(String xml, String tipoEvento , boolean valida, String tipo) throws NfeException {
		
		certUtil = new CertificadoUtil();
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
		String estado = String.valueOf(configuracoesNfe.getEstado().getCodigoIbge());
		boolean nfce = tipo.equals(ConstantesUtil.NFCE);

		try {
			
			/**
			 * Informaes do Certificado Digital.
			 */
			
			certUtil.iniciaConfiguracoes();
			
			xml = Assinar.assinaNfe(xml, Assinar.EVENTO);
			
			if(valida){
				String erros ="";
				switch (tipoEvento) {
				case "cancelar":
					erros = Validar.validaXml(xml, Validar.CANCELAR);
					break;
				case "cce":
					erros = Validar.validaXml(xml, Validar.CCE);
					break;
				case "manifestar":
					erros = Validar.validaXml(xml, Validar.MANIFESTAR);
					estado = "91";
					break;
				default:
					break;
				}
				
				if(!ObjetoUtil.isEmpty(erros)){
					throw new NfeException("Erro Na Validação do Xml: "+erros);
				}
			}else{
				if(tipoEvento.equals("manifestar")){
					estado = "91";
				}
			}
			
			System.out.println("Xml Evento: "+ xml);

			OMElement ome = AXIOMUtil.stringToOM(xml);

			RecepcaoEventoStub.NfeDadosMsg dadosMsg = new RecepcaoEventoStub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			RecepcaoEventoStub.NfeCabecMsg nfeCabecMsg = new RecepcaoEventoStub.NfeCabecMsg();

			nfeCabecMsg.setCUF(estado);

			/**
			 * Versao do XML
			 */
			nfeCabecMsg.setVersaoDados("1.00");

			RecepcaoEventoStub.NfeCabecMsgE nfeCabecMsgE = new RecepcaoEventoStub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

			String url = nfce ? WebServiceUtil.getUrl(ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.EVENTO) : WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.EVENTO);
			if(tipoEvento.equals("manifestar")){
				url =  WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.MANIFESTACAO);
			}
			
			RecepcaoEventoStub stub = new RecepcaoEventoStub(url);
			result = stub.nfeRecepcaoEvento(dadosMsg, nfeCabecMsgE);

		} catch (RemoteException | XMLStreamException e) {
			throw new NfeException(e.getMessage());
		}
		
		return result.getExtraElement().toString();
	}

}
