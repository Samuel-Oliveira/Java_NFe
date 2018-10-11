package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.exception.NfeValidacaoException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TInutNFe;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

/**
 * Classe Responsavel por inutilizar uma Faixa de numeracao da Nfe.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */
class Inutilizar {

    static TInutNFe criaObjetoInutiliza(ConfiguracoesNfe config, String id, String motivo, String tipo) {

		TInutNFe inutNFe = new TInutNFe();
		inutNFe.setVersao(ConstantesUtil.VERSAO.INUTILIZACAO);

		TInutNFe.InfInut infInut = new TInutNFe.InfInut();
		infInut.setId(id);
		infInut.setTpAmb(config.getAmbiente());
		infInut.setXServ("INUTILIZAR");
		infInut.setCUF(id.substring(2, 4));
		infInut.setAno(id.substring(4, 6));

		infInut.setCNPJ(id.substring(6, 20));
		infInut.setMod(tipo.equals(ConstantesUtil.TipoDoc_e.NFE.name()) ? "55" : "65");
		infInut.setSerie(Integer.valueOf(id.substring(22, 25)).toString());

		infInut.setNNFIni(Integer.valueOf(id.substring(25, 34)).toString());
		infInut.setNNFFin(Integer.valueOf(id.substring(34, 43)).toString());

		infInut.setXJust(motivo);
		inutNFe.setInfInut(infInut);

		return inutNFe;
	}

	static TRetInutNFe inutiliza(ConfiguracoesNfe config, String id, String motivo, String tipo, boolean validar)
			throws NfeException {

		try {

			TInutNFe inutNFe = criaObjetoInutiliza(config, id, motivo, tipo);

			String xml = XmlUtil.objectToXml(inutNFe);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");

			xml = Assinar.assinaNfe(config, xml, Assinar.INFINUT);

			if (validar) {
				String erros = Validar.validaXml(config, xml, Validar.INUTILIZACAO);
				if (!ObjetoUtil.isEmpty(erros)) {
					throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
				}
			}

			if (config.isLog()) {
				System.out.println("Xml Inutilizar: " + xml);
			}
			OMElement ome = AXIOMUtil.stringToOM(xml);

			NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = new NFeInutilizacao4Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			ConstantesUtil.TipoDoc_e doc_e = ConstantesUtil.TipoDoc_e.valueOf(tipo.toUpperCase()); 
			NFeInutilizacao4Stub stub = new NFeInutilizacao4Stub(
					WebServiceUtil.getUrl(config, doc_e, ConstantesUtil.SERVICOS.INUTILIZACAO));

			// Timeout
			if (!ObjetoUtil.isEmpty(config.getTimeout())) {
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
						config.getTimeout());
			}

			NFeInutilizacao4Stub.NfeResultMsg result = stub.nfeInutilizacaoNF(dadosMsg);

			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetInutNFe.class);
		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}