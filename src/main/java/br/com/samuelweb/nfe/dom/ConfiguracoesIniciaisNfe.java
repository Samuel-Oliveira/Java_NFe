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
public final class ConfiguracoesIniciaisNfe {

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


    //Construtor Singleton
    private ConfiguracoesIniciaisNfe() {
    }

    //Construtor Privado
    private ConfiguracoesIniciaisNfe(Estados estado, String ambiente, Certificado certificado, String pastaSchemas) {

        instance = new ConfiguracoesIniciaisNfe();
        instance.setEstado(estado);
        instance.setAmbiente(ambiente);
        instance.setCertificado(certificado);
        instance.setPastaSchemas(pastaSchemas);
        instance.setVersaoNfe(ConstantesUtil.VERSAO.NFE);

    }

    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado, String ambiente, Certificado certificado, String pastaSchemas) {
        new ConfiguracoesIniciaisNfe(estado, ambiente, certificado, pastaSchemas);
        System.out.println("Api Java Nfe Versão 4.00.3 - Samuel Olivera - samuk.exe@hotmail.com");
        System.out.println("Certificado: " + certificado.getTipo().toUpperCase() + " - " + certificado.getNome().toUpperCase() + " - Vencimento: " + certificado.getVencimento());
        System.out.println("Ambiente: " + (ambiente.equals("1") ? "Produção" : "Homologação") + " - Estado: " + estado.getNome());
        return instance;
    }

    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado, String ambiente, Certificado certificado, String pastaSchemas, Boolean log) {
        new ConfiguracoesIniciaisNfe(estado, ambiente, certificado, pastaSchemas);
        if (log) {
            System.out.println("Api Java Nfe Versão 4.00.3 - Samuel Olivera - samuk.exe@hotmail.com");
            System.out.println("Certificado: " + certificado.getTipo().toUpperCase() + " - " + certificado.getNome().toUpperCase() + " - Vencimento: " + certificado.getVencimento());
            System.out.println("Ambiente: " + (ambiente.equals("1") ? "Produção" : "Homologação") + " - Estado: " + estado.getNome());
        }
        return instance;
    }

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
     * @param versaoNfe the versaoNfe to set
     */
    private void setVersaoNfe(String versaoNfe) {
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
