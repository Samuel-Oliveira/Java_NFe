/**
 *
 */
package br.com.samuelweb.nfe.dom;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.ProxyUtil;

/**
 * @author Samuel Oliveira
 *         <p>
 *         Inicia Configurações Nfe.
 */
public interface ConfiguracoesNfe {

	/**
	 * @return the pastaSchemas
	 */
	public String getPastaSchemas();

	/**
	 * @return the versaoNfe
	 */
	public String getVersaoNfe();

	/**
	 * @return the ambiente
	 */
	public String getAmbiente();

	/**
	 * @return the certificado
	 */
	public Certificado getCertificado();

	/**
	 * @return configuracao do proxy
	 */
	public ProxyUtil getProxy();

	/**
	 * @return the contigenciaSCAN
	 */
	public boolean isContigenciaSCAN();

	/**
	 * @return the estado
	 */
	public Estados getEstado();

	public boolean isLog();

	public ProxyUtil getProxyUtil();

	public Integer getTimeout();
}
