/**
 * 
 */
package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.ProxyUtil;

/**
 * @author Samuel Oliveira
 *
 * Inicia Configurações Nfe. 
 */
public final class ConfiguracoesIniciaisNfe {
	
	private static ConfiguracoesIniciaisNfe instance;
	
	private Estados estado;
	private String ambiente;
	private Certificado certificado;
	private String pastaSchemas;
	private String versaoNfe;
	private ProxyUtil proxyUtil; 
	private boolean contigenciaSCAN;
	private boolean protocol;
	
	//Construtor Singleton
	private ConfiguracoesIniciaisNfe(){}
	
	//Construtor Privado
	private ConfiguracoesIniciaisNfe(Estados estado,String ambiente, Certificado certificado, String pastaSchemas, String versaoNfe){
		
		instance = new ConfiguracoesIniciaisNfe();
		instance.setEstado(estado);
		instance.setAmbiente(ambiente);
		instance.setCertificado(certificado);
		instance.setPastaSchemas(pastaSchemas);
		instance.setVersaoNfe(versaoNfe);
		
	}
	
	public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado,String ambiente, Certificado certificado, String pastaSchemas, String versaoNfe){
		new ConfiguracoesIniciaisNfe(estado,ambiente,certificado,pastaSchemas,versaoNfe);
		return instance;
	}
	
	public static ConfiguracoesIniciaisNfe getInstance() throws NfeException{
		if(instance == null){
			throw new NfeException("Configurações Não Foram Inicializadas.");
		}
		
		return instance;
	}
	
	public void setProxy(String ip, int porta, String usuario,String senha){
		proxyUtil = new ProxyUtil(ip, porta, usuario, senha);
	}

	/**
	 * @return the pastaSchemas
	 */
	public String getPastaSchemas() {
		return pastaSchemas;
	}

	/**
	 * @param pastaSchemas the pastaSchemas to set
	 */
	public void setPastaSchemas(String pastaSchemas) {
		this.pastaSchemas = pastaSchemas;
	}

	/**
	 * @return the versaoNfe
	 */
	public String getVersaoNfe() {
		return versaoNfe;
	}

	/**
	 * @param versaoNfe the versaoNfe to set
	 */
	public void setVersaoNfe(String versaoNfe) {
		this.versaoNfe = versaoNfe;
	}

	/**
	 * @return the ambiente
	 */
	public String getAmbiente() {
		return ambiente;
	}

	/**
	 * @param ambiente the ambiente to set
	 */
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	/**
	 * @return the certificado
	 */
	public Certificado getCertificado() {
		return certificado;
	}

	/**
	 * @param certificado the certificado to set
	 */
	public void setCertificado(Certificado certificado) {
		this.certificado = certificado;
	}
	/**
	 * 
	 * @return configuracao do proxy
	 */
	public ProxyUtil getProxy() {
		return proxyUtil;
	}

	/**
	 * @return the contigencia
	 */
	public boolean isContigenciaSCAN() {
		return contigenciaSCAN;
	}

	/**
	 * @param contigencia the contigencia to set
	 */
	public void setContigenciaSCAN(boolean contigenciaSCAN) {
		this.contigenciaSCAN = contigenciaSCAN;
	}

	/**
	 * @return the estado
	 */
	public Estados getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	/**
	 * @return the protocol
	 */
	public boolean isProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(boolean protocol) {
		this.protocol = protocol;
	}

}
