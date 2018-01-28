package br.com.samuelweb.nfe.util;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * Classe Responsavel Por Carregar as informações do Certificado Digital
 * 
 * @author Samuel Oliveira
 * 
 */
public class CertificadoUtil {

	public static ConfiguracoesIniciaisNfe iniciaConfiguracoes() throws NfeException {

		try {
			Certificado certificado = ConfiguracoesIniciaisNfe.getInstance().getCertificado();
			if(!certificado.isValido()){
			    throw new CertificadoException("Certificado vencido.");
            }
			CertificadoService.inicializaCertificado(certificado, CertificadoUtil.class.getResourceAsStream("/Cacert"));
		} catch (CertificadoException e) {
			throw new NfeException(e.getMessage());
		}

		return ConfiguracoesIniciaisNfe.getInstance();
	}

}