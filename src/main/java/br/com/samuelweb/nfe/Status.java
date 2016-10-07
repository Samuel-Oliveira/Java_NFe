package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.UrlWebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.consstatserv.TConsStatServ;
import br.inf.portalfiscal.nfe.schema.retconsstatserv.TRetConsStatServ;
import br.inf.portalfiscal.www.nfe.wsdl.NfeStatusServico2.NfeStatusServico2Stub;


/**
 * Classe responsavel por fazer a Verificacao do Status Do Webservice
 * 
 * @autor Samuel Oliveira
 */
public class Status {

	static NfeStatusServico2Stub.NfeStatusServicoNF2Result result;
	private static ConfiguracoesIniciaisNfe configuracoesNfe;

	public static TRetConsStatServ statusServico(TConsStatServ consStatServ, boolean valida) throws NfeException {
		
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
		CertificadoUtil certificadoUtil = new CertificadoUtil();
		certificadoUtil.iniciaConfiguracoes();

		try {

			String xml = XmlUtil.objectToXml(consStatServ);
	
			if(valida){
				String erros = Validar.validaXml(xml, Validar.STATUS);
				if(!ObjetoUtil.isEmpty(erros)){
					throw new NfeException("Erro Na Validação do Xml: "+erros);
				}
			}
			

			OMElement ome = AXIOMUtil.stringToOM(xml);
			NfeStatusServico2Stub.NfeDadosMsg dadosMsg = new NfeStatusServico2Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			NfeStatusServico2Stub.NfeCabecMsg nfeCabecMsg = new NfeStatusServico2Stub.NfeCabecMsg();
			/**
			 * Codigo do Estado.
			 */
			nfeCabecMsg.setCUF(configuracoesNfe.getUf());

			/**
			 * Versao do XML
			 */
			nfeCabecMsg.setVersaoDados(configuracoesNfe.getVersaoNfe());
			NfeStatusServico2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeStatusServico2Stub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

			NfeStatusServico2Stub stub = new NfeStatusServico2Stub(UrlWebServiceUtil.status().toString());
			result = stub.nfeStatusServicoNF2(dadosMsg, nfeCabecMsgE);
		
			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsStatServ.class);
			
		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}
		
	}
	
}