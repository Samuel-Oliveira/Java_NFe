package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBException;



public class CancelarA3 {
	/**
	 * Metodo para Cancelar a NFE. com certificado A3 No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @author Hugo
	 * @since 26/06/2018	-- NFE 4.0
	 * 
	 * @param envEvento
	 * @return
	 * @throws NfeException
	 */
	static TRetEnvEvento eventoCancelamento(ConfiguracoesNfe config, boolean valida, DocumentoEnum tipoDocumento, String xmlAssinado)
			throws NfeException {

		try {
			String xmlRetorno = EventosA3.enviarEvento(config, xmlAssinado, ServicosEnum.CANCELAMENTO, valida, tipoDocumento);
			return XmlNfeUtil.xmlToObject(xmlRetorno, TRetEnvEvento.class);

		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

	/**
	 * Metodo retorno o XML de cancelamento sem assinatura
	 * 
	 * @author Hugo
	 * @since 26/06/2018	-- NFE 4.0
	 * 
	 * @param envEvento
	 * @return
	 * @throws NfeException
	 */
	static String montaXmleventoCancelamento(TEnvEvento enviEvento)
			throws NfeException {

		try {
			String xml = XmlNfeUtil.objectToXml(enviEvento);
			xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
			xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
			return xml;
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}
}
