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
 * 
 * Responsável por iniciar as configurações das operações NF-e.
 * 
 * <p>
 * Implementa a interface ConfiguracoesNfe e é permitido criar apenas uma 
 * instância dessa classe. Caso necessite operar em ambiente Web, use a classe
 * ConfiguracoesIniciaisNfe.
 * </p>
 * 
 * Para iniciar as configurações chame o método estático iniciaConfiguracoes:<br>
 * {@code 
 * ConfiguracoesIniciaisNfe.iniciaConfiguracoes(estado, ambiente, certificado, schemas);
 * }
 * 
 * @see ConfiguracoesNfe
 * @see ConfiguracoesWebNfe
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
     * Este método recebe como parâmetro os dados necessários para iniciar a 
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma. Neste caso, o log será setado para True.
     * @param estado enumeration Estados, UF do emitente.
     * @param ambiente Produção = 1 ou Homologação = 2
     * @param certificado objeto Certificado
     * @param pastaSchemas local dos arquivo de schemas da NF-e.
     * @return ConfiguracoesIniciaisNfe
     * @see br.com.samuelweb.certificado.Certificado
     * @see Estados
     */
    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado, String ambiente, Certificado certificado,
            String pastaSchemas) {
        return iniciaConfiguracoes(estado, ambiente, certificado, pastaSchemas, true);

    }

     /**
     * Este método recebe como parâmetro os dados necessários para iniciar a 
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma.
     * @param estado enumeration Estados, UF do emitente.
     * @param ambiente Produção = "1" ou Homologação = "2"
     * @param certificado objeto Certificado
     * @param pastaSchemas local dos arquivo de schemas da NF-e.
     * @param log true ou false, imprime informações da inicialização.
     * @return ConfiguracoesIniciaisNfe
     * @see br.com.samuelweb.certificado.Certificado
     * @see Estados
     */
    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes(Estados estado, String ambiente, Certificado certificado,
            String pastaSchemas, Boolean log) {
        new ConfiguracoesIniciaisNfe(estado, ambiente, certificado, pastaSchemas, log);
        if (log) {
            System.out.println("Api Java Nfe Versão 4.00.10a - Samuel Olivera - samuk.exe@hotmail.com");
            System.out.println("Certificado: " + certificado.getTipo().toUpperCase() + " - "
                    + certificado.getNome().toUpperCase() + " - Vencimento: " + certificado.getVencimento());
            System.out.println("Ambiente: " + (ambiente.equals("1") ? "Produção" : "Homologação") + " - Estado: "
                    + estado.getNome());
        }
        return instance;
    }

    /**
     * Retorna uma instância privada de ConfiguracoesIniciaisNfe. Verifica
     * instância private ConfiguracoesIniciaisNfe é nula. Caso sim, dispara um
     * NfeException.
     * @return instance ConfiguracoesIniciaisNfe
     * @throws NfeException
     */
    public static ConfiguracoesIniciaisNfe getInstance() throws NfeException {
        if (instance == null) {
            throw new NfeException("Configurações Não Foram Inicializadas.");
        }

        return instance;
    }

     /**
     * Cria e atribui valores necessários para o objeto ProxyUtil.
     * @param ip
     * @param porta
     * @param usuario
     * @param senha 
     * @see ProxyUtil
     */
    public void setProxy(String ip, int porta, String usuario, String senha) {
        proxyUtil = new ProxyUtil(ip, porta, usuario, senha);
    }


    /**
     * Retorna o local da pasta dos schemas da NF-e(.xsd)
     * @return pastaSchemas
     */
    public String getPastaSchemas() {
        return pastaSchemas;
    }

    /**Atribui uma string que representa o local da pasta dos schemas da NF-e
     * (.xsd)
     * @param pastaSchemas
     */
    private void setPastaSchemas(String pastaSchemas) {
        this.pastaSchemas = pastaSchemas;
    }

    /**
     * Retorna a versão da NF-e.
     * @return versaoNfe
     */
    public String getVersaoNfe() {
        return versaoNfe;
    }

    /**
     * Atributo que epresenta a versão da NF-e. Por padrão, será o valor
     * ConstantesUtil.VERSAO.NFE;
     * @see ConstantesUtil
     */
    private void setVersaoNfe() {
        this.versaoNfe = ConstantesUtil.VERSAO.NFE;
    }

    /**
     * Retorna uma String que representa o ambiente de operações da NF-e.<br>
     * Ex.: PRODUÇÃO = "1" | HOMOLOGAÇÃO = "2"
     * @return ambiente
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * Atribui uma String que representa o ambiente de operação da NF-e.<br>
     * Ex.:<br>
     * {@code
     * ConfiguracoesIniciaisNfe.iniciaConfiguracoes(
                    estado,
                    ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                    certificado, 
                    schemas);
     * }
     * @param ambiente
     * @see ConstantesUtil
     */
    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * Retorna o objeto Certificado.
     * @return certificado
     * @see br.com.samuelweb.certificado
     */
    public Certificado getCertificado() {
        return certificado;
    }

    /**
     * Atribui um objeto Certificado.
     * @param certificado
     */
    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    /**
     * Retorna o objeto responsável pelas configurações do proxy.
     * @return proxyUtil
     * @see ProxyUtil
     */
    public ProxyUtil getProxy() {
        return proxyUtil;
    }

    /**
     * Retorna um valor booleano que representa se as operações de NF-e estão,
     * ou, não operando no modo de Contingência.
     * @return contigenciaSCAN
     */
    public boolean isContigenciaSCAN() {
        return contigenciaSCAN;
    }

    /**
     * Atribui um valor para contigenciaSCAN. Caso True, as 
     * operações da NF-e funcionarão no modo de Contingência.
     * <br>
     * Usar para situações em que não for possível estabelecer conexão com o 
     * WebService SEFAZ Origem.
     * @param contigenciaSCAN
     */
    public void setContigenciaSCAN(boolean contigenciaSCAN) {
        this.contigenciaSCAN = contigenciaSCAN;
    }

    /**
     * Retorna um objeto Estado que representa o UF do emissor da NF-e.
     * @return estado
     * @see Estados
     */
    public Estados getEstado() {
        return estado;
    }

    /**
     * Atribui um valor para o atribuito Estado.
     * @param estado estado
     * @see Estados
     */
    private void setEstado(Estados estado) {
        this.estado = estado;
    }

    /**
     * Retorna o valor do atributo log. Usada para exibir algumas informações
     * ao inicializar as configurações iniciais da NF-e.
     * @return log
     */
    public boolean isLog() {
        return log;
    }

    /**
     * Atribui valor para o atributo log.
     * @param log 
     */
    public void setLog(boolean log) {
        this.log = log;
    }

    /**
     * Retorna o valor do atributo proxyUtil.
     * @return proxyUtil
     * @see ProxyUtil
     */
    public ProxyUtil getProxyUtil() {
        return proxyUtil;
    }

    /**
     * Atribui um valor para o proxuUtil.
     * @param proxyUtil 
     */
    public void setProxyUtil(ProxyUtil proxyUtil) {
        this.proxyUtil = proxyUtil;
    }

    /**
     * Retorna o valor do atributo timeout.
     * @return timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Atribui o valor de timeout.<br>
     * O timeout é o limite de tempo(em milisegundos) de comunicação com 
     * WebServie. Sugerido pelo manual do contribuinte: 30000.
     * @param timeout 
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
