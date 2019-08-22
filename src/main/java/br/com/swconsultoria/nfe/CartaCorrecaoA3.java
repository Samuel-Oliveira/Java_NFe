package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envcce.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBException;
public class CartaCorrecaoA3 {

	

	/**
	 * Envia a carta de correção com certificado A3
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param valida
	 * @param tipo
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	static TRetEnvEvento eventoCCe(ConfiguracoesNfe config, boolean valida, String xmlAssinado)
			throws NfeException {
		try {
			String xmlRetorno = EventosA3.enviarEvento(config, xmlAssinado, ServicosEnum.CCE, valida, DocumentoEnum.NFE);
			return XmlNfeUtil.xmlToObject(xmlRetorno, TRetEnvEvento.class);

		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}
	
	/**
	 * Metodo retorna o XML da carta de correção sem assinatura
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * @param envEvento
	 * @return
	 * @throws NfeException
	 */
	static String montaXmlCartaCorrecao(TEnvEvento enviEvento)
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
