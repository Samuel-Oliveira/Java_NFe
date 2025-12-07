package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.eventoGenerico.TEnvEvento;
import br.com.swconsultoria.nfe.schema.eventoGenerico.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br Data: 28/09/2017 - 11:11
 */
class EventoGenerico {

	private EventoGenerico(){}

	static TRetEnvEvento evento(ConfiguracoesNfe config, TEnvEvento enviEvento, boolean valida)
			throws NfeException {

		try {

			String xml = XmlNfeUtil.objectToXml(enviEvento, config. getEncode());
			xml = xml.replace(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "")
					.replace("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v")
					.replace("<detEvento v", "<detEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

			xml = Eventos.enviarEvento(config, xml, ServicosEnum.EVENTO_GENERICO, valida, true, DocumentoEnum.NFE);

			return XmlNfeUtil.xmlToObject(xml, TRetEnvEvento.class);

		} catch (JAXBException e) {
			throw new NfeException(e.getMessage(),e);
		}

	}

}
