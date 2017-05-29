package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.inutnfe.TInutNFe;
import br.inf.portalfiscal.nfe.schema.retinutnfe.TRetInutNFe;
import br.inf.portalfiscal.www.nfe.wsdl.NfeInutilizacao.NfeInutilizacaoStub;
import br.inf.portalfiscal.www.nfe.wsdl.NfeInutilizacao2.NfeInutilizacao2Stub;

/**
 * Classe Responsavel por inutilizar uma Faixa de numeracao da Nfe.
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */
public class Inutilizar {

	public static NfeInutilizacao2Stub.NfeInutilizacaoNF2Result result;
	public static NfeInutilizacaoStub.NfeInutilizacaoNFResult resultBA;
	
	private static ConfiguracoesIniciaisNfe configuracoesNfe;
	private static CertificadoUtil certUtil;

	public static TRetInutNFe inutiliza(TInutNFe inutNFe, boolean valida, String tipo) throws NfeException {

		certUtil = new CertificadoUtil();
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
		boolean nfce = tipo.equals(ConstantesUtil.NFCE);
		boolean BA = configuracoesNfe.getEstado().equals(Estados.BA);

		try {

			certUtil.iniciaConfiguracoes();

			String xml = XmlUtil.objectToXml(inutNFe);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");

			xml = Assinar.assinaNfe(xml, Assinar.INFINUT);

			if (valida) {
				String erros = Validar.validaXml(xml, Validar.INUTILIZACAO);
				if (!ObjetoUtil.isEmpty(erros)) {
					throw new NfeException("Erro Na Validação do Xml: " + erros);
				}
			}

			System.out.println("Xml Inutilizar: " + xml);
			OMElement ome = AXIOMUtil.stringToOM(xml);

			if (BA && !nfce) {
				NfeInutilizacaoStub.NfeDadosMsg dadosMsgBA = new NfeInutilizacaoStub.NfeDadosMsg();
				dadosMsgBA.setExtraElement(ome);

				NfeInutilizacaoStub.NfeCabecMsg nfeCabecMsgBA = new NfeInutilizacaoStub.NfeCabecMsg();
				nfeCabecMsgBA.setCUF(String.valueOf(configuracoesNfe.getEstado().getCodigoIbge()));
				nfeCabecMsgBA.setVersaoDados(configuracoesNfe.getVersaoNfe());

				NfeInutilizacaoStub.NfeCabecMsgE nfeCabecMsgEBA = new NfeInutilizacaoStub.NfeCabecMsgE();
				nfeCabecMsgEBA.setNfeCabecMsg(nfeCabecMsgBA);

				NfeInutilizacaoStub stub = new NfeInutilizacaoStub(
						nfce ? WebServiceUtil.getUrl(ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.INUTILIZACAO)
								: WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.INUTILIZACAO));
				resultBA = stub.nfeInutilizacaoNF(dadosMsgBA, nfeCabecMsgEBA);

				return XmlUtil.xmlToObject(resultBA.getExtraElement().toString(), TRetInutNFe.class);
			} else {
				NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = new NfeInutilizacao2Stub.NfeDadosMsg();
				dadosMsg.setExtraElement(ome);

				NfeInutilizacao2Stub.NfeCabecMsg nfeCabecMsg = new NfeInutilizacao2Stub.NfeCabecMsg();
				nfeCabecMsg.setCUF(String.valueOf(configuracoesNfe.getEstado().getCodigoIbge()));
				nfeCabecMsg.setVersaoDados(configuracoesNfe.getVersaoNfe());

				NfeInutilizacao2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeInutilizacao2Stub.NfeCabecMsgE();
				nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

				NfeInutilizacao2Stub stub = new NfeInutilizacao2Stub(
						nfce ? WebServiceUtil.getUrl(ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.INUTILIZACAO)
								: WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.INUTILIZACAO));
				result = stub.nfeInutilizacaoNF2(dadosMsg, nfeCabecMsgE);

				return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetInutNFe.class);
			}
		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}