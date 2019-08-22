package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.util.LoggerUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class EnviarA3 {


	/**
	 * Metodo para Montar o XML da NFe (Utilizado para certificado A3 - Web)
	 * 
	 * @author Hugo
	 * @since 21/06/2018	-- NFE 4.0	 
	 *
	 * @param enviNFe
	 * @return
	 * @throws NfeException
	 * 
	 */
	static String montaXmlA3(TEnviNFe enviNFe) throws NfeException {
		try {
			/**
			 * Cria o xml
			 */
			String xml = XmlNfeUtil.objectToXml(enviNFe);
			return xml;
		} catch (Exception e) {
			throw new NfeException(e.getMessage());
		}		
	}

	
    /**
     * Metodo para enviar a NFe com certificado A3
     *
     * @param enviNFe
     * @param valida
     * @return
     * @throws NfeException
     */
	
    static TEnviNFe montaNfeA3(ConfiguracoesNfe config, String xmlAssinado, boolean valida) throws NfeException {

        try {

            /**
             * Valida o Xml caso sejá selecionado True
             */
            if (valida) {
                new Validar().validaXml(config, xmlAssinado, ServicosEnum.ENVIO);
            }

            LoggerUtil.log(Enviar.class, "[XML-ASSINADO]: " + xmlAssinado);
            
            return XmlNfeUtil.xmlToObject(xmlAssinado, TEnviNFe.class);

        } catch (Exception e) {
            throw new NfeException(e.getMessage());
        }

    }	
	
}
