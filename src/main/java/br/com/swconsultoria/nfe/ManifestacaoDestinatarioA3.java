package br.com.swconsultoria.nfe;

import javax.xml.bind.JAXBException;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class ManifestacaoDestinatarioA3 {
	
	/**
	 * Manifesta o recebimento da NFe
	 * 
	 * @author Hugo
	 * @since 21/06/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	static TRetEnvEvento eventoManifestacao(ConfiguracoesNfe config, boolean valida,  String xmlAssinado) throws NfeException {
		try {
			xmlAssinado = Eventos.enviarEvento(config, xmlAssinado, ServicosEnum.MANIFESTACAO, valida, DocumentoEnum.NFE);
            return XmlNfeUtil.xmlToObject(xmlAssinado, TRetEnvEvento.class);
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}

	/**
	 * Retorna o Xml para assinatura com certificado A3
	 * 
	 * @author Hugo
	 * @since 21/06/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param chave
	 * @param manifestacao
	 * @param cnpj
	 * @param data
	 * @param motivo
	 * @return
	 * @throws NfeException
	 */
	static String montarXML(ConfiguracoesNfe config, TEnvEvento envEvento ) throws NfeException {
		try {
            String xml = XmlNfeUtil.objectToXml(envEvento);
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
            return xml;
		} catch (JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}
	

}
