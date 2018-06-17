package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.consCad.TConsCad;
import br.inf.portalfiscal.nfe.schema.consCad.TUfCons;
import br.inf.portalfiscal.nfe.schema.retConsCad.TRetConsCad;
import br.inf.portalfiscal.www.nfe_400.wsdl.CadConsultaCadastro.CadConsultaCadastro4Stub;
import br.inf.portalfiscal.www.nfe_400.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4StubRs;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */

class ConsultaCadastro {

	static final String CNPJ = "CNPJ";
	static final String CPF = "CPF";

	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
	 *
	 * @param consCad
	 * @param valida
	 * @return
	 * @throws NfeException
	 */

	static TRetConsCad consultaCadastro(ConfiguracoesNfe config, String tipo, String cnpjCpf, Estados estado)
			throws NfeException {

		try {

			TConsCad consCad = new TConsCad();
			consCad.setVersao("2.00");

			TConsCad.InfCons infCons = new TConsCad.InfCons();
			if (CNPJ.equals(tipo)) {
				infCons.setCNPJ(cnpjCpf);
			} else {
				infCons.setCPF(cnpjCpf);
			}
			infCons.setXServ("CONS-CAD");
			infCons.setUF(TUfCons.valueOf(estado.toString()));

			consCad.setInfCons(infCons);

			String xml = XmlUtil.objectToXml(consCad);

			if (config.isLog()) {
				System.out.println("Xml Consulta: " + xml);
			}
			OMElement ome = AXIOMUtil.stringToOM(xml);

			if (estado.equals(Estados.RS)) {
				CadConsultaCadastro4StubRs.NfeDadosMsg_type0 dadosMsgRS = new CadConsultaCadastro4StubRs.NfeDadosMsg_type0();
				dadosMsgRS.setExtraElement(ome);

				CadConsultaCadastro4StubRs stubRS = new CadConsultaCadastro4StubRs(
						WebServiceUtil.getUrlConsultaCadastro(config, estado.toString()));

				// Timeout
				if (!ObjetoUtil.isEmpty(config.getTimeout())) {
					stubRS._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
					stubRS._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
							config.getTimeout());
				}

				CadConsultaCadastro4StubRs.ConsultaCadastro consultaCadastro = new CadConsultaCadastro4StubRs.ConsultaCadastro();
				consultaCadastro.setNfeDadosMsg(dadosMsgRS);

				CadConsultaCadastro4StubRs.NfeResultMsg resultRS = stubRS.consultaCadastro(consultaCadastro);

				return XmlUtil.xmlToObject(resultRS.getConsultaCadastroResult().getExtraElement().toString(),
						TRetConsCad.class);

			} else {
				CadConsultaCadastro4Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro4Stub.NfeDadosMsg();
				dadosMsg.setExtraElement(ome);

				CadConsultaCadastro4Stub stub = new CadConsultaCadastro4Stub(
						WebServiceUtil.getUrlConsultaCadastro(config, estado.toString()));

				// Timeout
				if (!ObjetoUtil.isEmpty(config.getTimeout())) {
					stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
					stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
							config.getTimeout());
				}

				CadConsultaCadastro4Stub.NfeResultMsg result = stub.consultaCadastro(dadosMsg);

				return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);
			}

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}