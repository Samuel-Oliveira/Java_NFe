package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.exception.NfeValidacaoException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.consCad.TConsCad;
import br.inf.portalfiscal.nfe.schema.retConsCad.TRetConsCad;
import br.inf.portalfiscal.www.nfe.wsdl.CadConsultaCadastro2.CadConsultaCadastro2Stub;
import br.inf.portalfiscal.www.nfe.wsdl.CadConsultaCadastro2.CadConsultaCadastro2Stub.CadConsultaCadastro2Result;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */

public class ConsultaCadastro {

	private static CadConsultaCadastro2Result result;
	private static CertificadoUtil certUtil;

	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
	 * 
	 * @param consCad
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	
	public static TRetConsCad consultaCadastro(TConsCad consCad, boolean valida) throws NfeException {

		certUtil = new CertificadoUtil();

		try {

			certUtil.iniciaConfiguracoes();

			String xml = XmlUtil.objectToXml(consCad);

			if (valida) {
				String erros = Validar.validaXml(xml, Validar.CONSULTA_CADASTRO);
				if (!ObjetoUtil.isEmpty(erros)) {
					throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
				}
			}

			System.out.println("Xml Consulta: " + xml);
			OMElement ome = AXIOMUtil.stringToOM(xml);

			CadConsultaCadastro2Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro2Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			CadConsultaCadastro2Stub.NfeCabecMsg nfeCabecMsg = new CadConsultaCadastro2Stub.NfeCabecMsg();
			nfeCabecMsg.setCUF(Estados.valueOf(consCad.getInfCons().getUF().toString()).getCodigoIbge());
			nfeCabecMsg.setVersaoDados("2.00");

			CadConsultaCadastro2Stub.NfeCabecMsgE nfeCabecMsgE = new CadConsultaCadastro2Stub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

			CadConsultaCadastro2Stub stub = new CadConsultaCadastro2Stub(WebServiceUtil.getUrlConsultaCadastro(consCad.getInfCons().getUF().toString()));
			result = stub.cadConsultaCadastro2(dadosMsg, nfeCabecMsgE);
			
			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}
