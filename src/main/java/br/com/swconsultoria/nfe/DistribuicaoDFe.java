package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.util.*;
import br.com.swconsultoria.nfe.wsdl.NFeDistribuicaoDFe.NFeDistribuicaoDFeStub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 *
 */
class DistribuicaoDFe {

	/**
	 *
	 * Classe Reponsavel Por Consultar as NFE na SEFAZ
	 *
	 * @param config Configuração
	 * @param tipoPessoa Informe {@link PessoaEnum}
	 * @param cpfCnpj Informe o Cpf ou Cnpj
	 * @param tipoConsulta Informe {@link ConsultaDFeEnum}
	 * @param nsuChave Informe a Chave ou o Nsu
	 * @return
	 * @throws NfeException
	 */
	static RetDistDFeInt consultaNfe(ConfiguracoesNfe config, PessoaEnum tipoPessoa, String cpfCnpj, ConsultaDFeEnum tipoConsulta,
                                     String nsuChave) throws NfeException {

		try {

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

}
