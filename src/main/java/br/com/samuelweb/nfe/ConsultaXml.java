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
import br.inf.portalfiscal.nfe.schema.conssitnfe.TConsSitNFe;
import br.inf.portalfiscal.nfe.schema.retconssitnfe.TRetConsSitNFe;
import br.inf.portalfiscal.www.nfe.wsdl.NfeConsulta2.NfeConsulta2Stub;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */

public class ConsultaXml {

	private static NfeConsulta2Stub.NfeConsultaNF2Result result;
	private static ConfiguracoesIniciaisNfe configuracoesNfe;
	private static CertificadoUtil certUtil;

	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
	 * 
	 * @param Chave
	 * @return Resposta da Sefaz
	 * @throws NfeException 
	 */
	public static TRetConsSitNFe consultaXml(TConsSitNFe consSitNFe) throws NfeException {
		
		certUtil = new CertificadoUtil();
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {

			/**
			 * Informaçoes do Certificado Digital.
			 */
			certUtil.iniciaConfiguracoes();
			
			String xml = XmlUtil.objectToXml(consSitNFe);
			
			// Validação
			String erros = Validar.validaXml(xml, Validar.CONSULTA_XML);
			if(!ObjetoUtil.isEmpty(erros)){
				throw new NfeException("Erro Na Validação do Xml: "+erros);
			}

			OMElement ome = AXIOMUtil.stringToOM(xml);

			NfeConsulta2Stub.NfeDadosMsg dadosMsg = new NfeConsulta2Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			NfeConsulta2Stub.NfeCabecMsg nfeCabecMsg = new NfeConsulta2Stub.NfeCabecMsg();
			/**
			 * Codigo do Estado.
			 */
			nfeCabecMsg.setCUF(String.valueOf(configuracoesNfe.getUf()));

			/**
			 * Versao do XML
			 */
			nfeCabecMsg.setVersaoDados(configuracoesNfe.getVersaoNfe());
			NfeConsulta2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeConsulta2Stub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

			NfeConsulta2Stub stub = new NfeConsulta2Stub(UrlWebServiceUtil.consultaXml().toString());
			result = stub.nfeConsultaNF2(dadosMsg, nfeCabecMsgE);

			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsSitNFe.class);
			
		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}
		
	}

}
