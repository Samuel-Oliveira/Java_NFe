/**
 *
 */
package br.com.samuelweb.nfe.dom;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.ProxyUtil;

/**
 * @author Samuel Oliveira
 * <p>
 * Inicia Configurações Nfe.
 */
public class ConfiguracoesIniciaisNfe implements ConfiguracoesNfe {
	private static ConfiguracoesIniciaisNfe instance;

	private Estados estado;
	private String ambiente;
	private Certificado certificado;
	private String pastaSchemas;
	private String versaoNfe;
	private ProxyUtil proxyUtil;
	private Integer timeout;
	private boolean contigenciaSCAN;
	private boolean log = true;

    // Construtor Singleton
    private ConfiguracoesIniciaisNfe() {
    }

    // Construtor Privado
    private ConfiguracoesIniciaisNfe(Estados estado, String ambiente, Certificado certificado, String pastaSchemas, Boolean log) {

        instance = new ConfiguracoesIniciaisNfe();
        instance.setEstado(estado);
        instance.setAmbiente(ambiente);
        instance.setCertificado(certificado);
        instance.setPastaSchemas(pastaSchemas);
        instance.setVersaoNfe();
        instance.setLog(log);

    }

    /**
     * Este método recebe como parâmetro os daddos necessários para iniciar a 
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma.
     * @param estado - enumeration Estados, UF do emitente.
     * @param ambiente - Produção = 1 ou Homologação = 2
     * @param certificado - objeto Certificado
     * @param pastaSchemas - local dos arquivo de schemas da NF-e.
     * @return ConfiguracoesIniciaisNfe
     * @see Certificado
     * @see Estados
     */
    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado, String ambiente, Certificado certificado,
            String pastaSchemas) {
        return iniciaConfiguracoes(estado, ambiente, certificado, pastaSchemas, true);

    }

    /**
     * Este método recebe como parâmetro os daddos necessários para iniciar a 
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma.
     * @param estado - enumeration Estados, UF do emitente.
     * @param ambiente - Produção = "1" ou Homologação = "2"
     * @param certificado - objeto Certificado
     * @param pastaSchemas - local dos arquivo de schemas da NF-e.
     * @param log - true ou false
     * @return ConfiguracoesIniciaisNfe
     * @see Certificado
     * @see Estados
     */
    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado, String ambiente, Certificado certificado,
            String pastaSchemas, Boolean log) {
        new ConfiguracoesIniciaisNfe(estado, ambiente, certificado, pastaSchemas, log);
        if (log) {
            System.out.println("Api Java Nfe Versão 4.00.9 - Samuel Olivera - samuk.exe@hotmail.com");
            System.out.println("Certificado: " + certificado.getTipo().toUpperCase() + " - "
                    + certificado.getNome().toUpperCase() + " - Vencimento: " + certificado.getVencimento());
            System.out.println("Ambiente: " + (ambiente.equals("1") ? "Produção" : "Homologação") + " - Estado: "
                    + estado.getNome());
        }
        return instance;
    }

    /**
     * Retorna uma instância privada de ConfiguracoesIniciaisNfe.
     * Verifica instância private ConfiguracoesIniciaisNfe é nula. 
     * Caso sim, dispara um NfeException
     * @return instance - ConfiguracoesIniciaisNfe
     * @throws NfeException 
     */
    public static ConfiguracoesIniciaisNfe getInstance() throws NfeException {
        if (instance == null) {
            throw new NfeException("Configurações Não Foram Inicializadas.");
        }

        return instance;
    }

    public void setProxy(String ip, int porta, String usuario, String senha) {
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
    private void setPastaSchemas(String pastaSchemas) {
        this.pastaSchemas = pastaSchemas;
    }

    /**
     * @return the versaoNfe
     */
    public String getVersaoNfe() {
        return versaoNfe;
    }

    /**
     */
    private void setVersaoNfe() {
        this.versaoNfe = ConstantesUtil.VERSAO.NFE;
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
     * @return configuracao do proxy
     */
    public ProxyUtil getProxy() {
        return proxyUtil;
    }

    /**
     * @return the contigenciaSCAN
     */
    public boolean isContigenciaSCAN() {
        return contigenciaSCAN;
    }

    /**
     * @param contigenciaSCAN the contigencia to set
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
    private void setEstado(Estados estado) {
        this.estado = estado;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public ProxyUtil getProxyUtil() {
        return proxyUtil;
    }

    public void setProxyUtil(ProxyUtil proxyUtil) {
        this.proxyUtil = proxyUtil;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
