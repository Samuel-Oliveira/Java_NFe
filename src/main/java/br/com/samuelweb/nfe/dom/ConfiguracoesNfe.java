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
    String getPastaSchemas();

	/**
	 * @return the versaoNfe
	 */
    String getVersaoNfe();

	/**
	 * @return the ambiente
	 */
    String getAmbiente();

	/**
	 * @return the certificado
	 */
    Certificado getCertificado();

	/**
	 * @return configuracao do proxy
	 */
    ProxyUtil getProxy();

	/**
	 * @return the contigenciaSCAN
	 */
    boolean isContigenciaSCAN();

	/**
	 * @return the estado
	 */
    Estados getEstado();

    boolean isLog();

    ProxyUtil getProxyUtil();

    Integer getTimeout();

}
