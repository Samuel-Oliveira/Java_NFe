/**
 * 
 */
package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.exception.NfeException;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 *
 * Configurações Nfe. 
 */
public final class ConfiguracoesIniciaisNfe {
	
	private static ConfiguracoesIniciaisNfe instance;
	
	private String uf;
	private String ambiente;
	private String certificado;
	private String pastaSchemas;
	private String versaoNfe;
	
	//Construtor Singleton
	private ConfiguracoesIniciaisNfe(){}
	
	//Construtor Privado
	private ConfiguracoesIniciaisNfe(String uf,String ambiente, String certificado, String pastaSchemas, String versaoNfe){
		
		instance = new ConfiguracoesIniciaisNfe();
		instance.setUf(uf);
		instance.setAmbiente(ambiente);
		instance.setCertificado(certificado);
		instance.setPastaSchemas(pastaSchemas);
		instance.setVersaoNfe(versaoNfe);
		
	}
	
	/**
	 *
	 * Inicia Configurações Nfe. 
	 * 
	 * - Codigo da UF 
	 * - Ambiente (1 - Produção, 2 - Homologação) 
     * - Alias do certificado 
     * - Versão da NFE.
     * 
	 */
	public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(String uf,String ambiente, String certificado, String pastaSchemas, String versaoNfe) throws NfeException{
		new ConfiguracoesIniciaisNfe(uf,ambiente,certificado,pastaSchemas,versaoNfe);
		return instance;
	}
	
	public static ConfiguracoesIniciaisNfe getInstance() throws NfeException{
		if(instance == null){
			throw new NfeException("Configurações Não Foram Inicializadas.");
		}
		
		return instance;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return the certificado
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * @param certificado the certificado to set
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
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

}
