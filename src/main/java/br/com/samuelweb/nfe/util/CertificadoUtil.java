package br.com.samuelweb.nfe.util;

import java.util.List;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * Classe Responsavel Por Carregar as informações do Certificado Digital
 * 
 * @author SaMuK
 * 
 */
public class CertificadoUtil {

	private ConfiguracoesIniciaisNfe configuracoesNfe;

	// Construtor
	public CertificadoUtil() throws NfeException {
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
	}

	public void iniciaConfiguracoes() throws NfeException {

		try {

			Certificado certificado = configuracoesNfe.getCertificado();
			CertificadoService.inicializaCertificado(certificado, getClass().getResourceAsStream("/Cacert"));

		} catch (CertificadoException e) {
			throw new NfeException(e.getMessage());
		}

	}
	
	public static List<Certificado> listaCertificadosWindows() throws NfeException {
		try {
			return CertificadoService.listaCertificadosWindows();
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}

	public static Certificado certificadoPfxBytes(byte[] certificadoBytes, String senha) throws NfeException {
		try {
			return CertificadoService.certificadoPfxBytes(certificadoBytes, senha);
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}
	
	public static Certificado certificadoPfx(String caminhoCertificado, String senha) throws NfeException {
		try {
			return CertificadoService.certificadoPfx(caminhoCertificado, senha);
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}
	
	public static Certificado certificadoA3(String marca, String dll, String senha) throws NfeException {
		try {
			return CertificadoService.certificadoA3(marca, dll, senha);
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}
	
}