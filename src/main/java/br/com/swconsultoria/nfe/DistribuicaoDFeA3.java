package br.com.swconsultoria.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.LoggerUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeDistribuicaoDFe.NFeDistribuicaoDFeStub;

public class DistribuicaoDFeA3 {
	
	/**
	 * Envia o a consulta para retornar as NFs que estão na receita.
	 * 
	 * @author Hugo
	 * @since 21/06/2018	-- NFE 4.0
	 * 	 
	 * @param config
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	static RetDistDFeInt consultaNfe(ConfiguracoesNfe config, String xmlAssinado) throws NfeException {

		try {

			String xml = xmlAssinado;

            LoggerUtil.log(DistribuicaoDFe.class,"[XML-ENVIO]: "  + xml);

			OMElement ome = AXIOMUtil.stringToOM(xml);

			NFeDistribuicaoDFeStub.NfeDadosMsg_type0 dadosMsgType0 = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();
			dadosMsgType0.setExtraElement(ome);

			NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();
			distDFeInteresse.setNfeDadosMsg(dadosMsgType0);

			NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(
					WebServiceUtil.getUrl(config, DocumentoEnum.NFE, ServicosEnum.DISTRIBUICAO_DFE));

			// Timeout
			if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
				stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
						config.getTimeout());
			}
			NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);

            LoggerUtil.log(DistribuicaoDFe.class,"[XML-RETORNO]: "  + result.getNfeDistDFeInteresseResult().getExtraElement().toString());
			return XmlNfeUtil.xmlToObject(result.getNfeDistDFeInteresseResult().getExtraElement().toString(),
					RetDistDFeInt.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}
	
	
	/**
	 * Monta o xml para ser assinado pelo certificado A3
	 * 
	 * @author Hugo
	 * @since 21/06/2018	-- NFE 4.0
	 * 
	 * @param distDFeInt
	 * @param valida
	 * @return
	 * @throws NfeException
	 */	
	static String montarXML(ConfiguracoesNfe config, PessoaEnum tipoPessoa, String cpfCnpj, ConsultaDFeEnum tipoConsulta,
            String nsuChave) throws JAXBException, NfeException {
		DistDFeInt distDFeInt = new DistDFeInt();
		distDFeInt.setVersao(ConstantesUtil.VERSAO.DIST_DFE);
		distDFeInt.setTpAmb(config.getAmbiente().getCodigo());
        distDFeInt.setCUFAutor(config.getEstado().getCodigoUF());

		if (PessoaEnum.JURIDICA.equals(tipoPessoa)) {
			distDFeInt.setCNPJ(cpfCnpj);
		} else {
			distDFeInt.setCPF(cpfCnpj);
		}

		if (ConsultaDFeEnum.NSU.equals(tipoConsulta)) {
			DistDFeInt.DistNSU distNSU = new DistDFeInt.DistNSU();
			distNSU.setUltNSU(nsuChave);
			distDFeInt.setDistNSU(distNSU);
		} else {
			DistDFeInt.ConsChNFe chNFe = new DistDFeInt.ConsChNFe();
			chNFe.setChNFe(nsuChave);
			distDFeInt.setConsChNFe(chNFe);
		}

		String xml = XmlNfeUtil.objectToXml(distDFeInt);


        LoggerUtil.log(DistribuicaoDFe.class,"[XML-ENVIO]: "  + xml);
        return xml;
	}
	
}
