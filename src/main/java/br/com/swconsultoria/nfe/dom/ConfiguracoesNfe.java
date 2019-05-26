/**
 *
 */
package br.com.swconsultoria.nfe.dom;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.util.ConstantesUtil;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Samuel Oliveira
 *
 * Responsável por iniciar as configurações das operações NF-e.
 *
 * Para iniciar as configurações chame o método estático iniciaConfiguracoes:<br>
 * {@code
 * ConfiguracoesIniciaisNfe.iniciaConfiguracoes(estado, ambiente, certificado, schemas);
 * }
 *
 * @see ConfiguracoesNfe
 * @see ConfiguracoesWebNfe
 */
public class ConfiguracoesNfe {

    private EstadosEnum estado;
    private AmbienteEnum ambiente;
    private Certificado certificado;
    private String pastaSchemas;
    private Proxy proxy;
    private Integer timeout;
    private boolean contigenciaSCAN;
    private boolean validacaoDocumento = true;
    private String arquivoWebService;

    /**
     * Este método recebe como parâmetro os dados necessários para iniciar a 
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma.
     * @param estado enumeration Estados, UF do emitente.
     * @param ambiente Enumeration AmbienteEnum
     * @param certificado objeto Certificado
     * @param pastaSchemas local dos arquivo de schemas da NF-e.
     * @return ConfiguracoesIniciaisNfe
     * @see br.com.swconsultoria.certificado.Certificado
     * @see EstadosEnum
     */
    public static ConfiguracoesNfe criarConfiguracoes(EstadosEnum estado, AmbienteEnum ambiente, Certificado certificado,
                                                      String pastaSchemas) throws CertificadoException {

        ConfiguracoesNfe configuracoesNfe = new ConfiguracoesNfe();
        configuracoesNfe.setEstado(estado);
        configuracoesNfe.setAmbiente(ambiente);
        configuracoesNfe.setCertificado(certificado);
        configuracoesNfe.setPastaSchemas(pastaSchemas);

        try {
            //Setando Encoding.
            System.setProperty("file.encoding", "UTF-8");
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new CertificadoException("Erro ao setar Encoding.");
        }

        if(Logger.getLogger("").isLoggable(Level.SEVERE)) {
            System.err.println();
            System.err.println("#########################################################");
            System.err.println("    Api Java Nfe - Versão 4.00.11    ");
            if(Logger.getLogger("").isLoggable(Level.WARNING)) {
                System.err.println(" Samuel Olivera - samuel@swconsultoria.com.br ");
            }
            System.err.println("            Tipo Certificado: " + certificado.getTipoCertificado().toString());
            System.err.println(" Alias Certificado: " + certificado.getNome().toUpperCase());
            System.err.println(" Vencimento Certificado: " + certificado.getVencimento());
            System.err.println(" Cnpj/Cpf Certificado: " + certificado.getCnpjCpf());
            System.err.println(" Ambiente: " + (ambiente.equals(AmbienteEnum.PRODUCAO) ? "Produção" : "Homologação") + " - Estado: "
                    + estado.getNome());
            System.err.println("#########################################################");
            System.err.println();
        }
        if (!certificado.isValido()) {
            throw new CertificadoException("Certificado Vencido!");
        }
        return configuracoesNfe;
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
     * Retorna um enuns que representa o ambiente de operações da NF-e.<br>
     * @return ambiente
     */
    public AmbienteEnum getAmbiente() {
        return ambiente;
    }

    /**
     * Atribui uma String que representa o ambiente de operação da NF-e.<br>
     * Ex.:<br>
     * {@code
     * ConfiguracoesIniciaisNfe.iniciaConfiguracoes(
    estado,
    AmbienteEnum.HOMOLOGACAO,
    certificado,
    schemas);
     * }
     * @param ambiente
     * @see ConstantesUtil
     */
    public void setAmbiente(AmbienteEnum ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * Retorna o objeto Certificado.
     * @return certificado
     * @see br.com.swconsultoria.certificado
     */
    public Certificado getCertificado() {
        return certificado;
    }

    /**
     * Atribui um objeto Certificado.
     * @param certificado
     */
    private void setCertificado(Certificado certificado) {
        this.certificado = certificado;
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
     * @see EstadosEnum
     */
    public EstadosEnum getEstado() {
        return estado;
    }

    /**
     * Atribui um valor para o atribuito Estado.
     * @param estado estado
     * @see EstadosEnum
     */
    public void setEstado(EstadosEnum estado) {
        this.estado = estado;
    }

    /**
     * Retorna o valor do atributo proxyUtil.
     * @return proxyUtil
     * @see Proxy
     */
    public Proxy getProxy() {
        return proxy;
    }

    /**
     * Atribui um valor para o proxuUtil.
     * @param proxy
     */
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
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

    /**
     * Retorna o valor da validacaoDocumento.
     * @return validacaoDocumento
     */
    public boolean isValidacaoDocumento() {
        return validacaoDocumento;
    }

    /**
     * Atribui um valor para validacaoDocumento. Caso True, irá
     * validar o documento do emitente com o documento do certificado.
     * <br>
     * @param validacaoDocumento
     */
    public void setValidacaoDocumento(boolean validacaoDocumento) {
        this.validacaoDocumento = validacaoDocumento;
    }

    public String getArquivoWebService() {
        return arquivoWebService;
    }

    public void setArquivoWebService(String arquivoWebService) {
        this.arquivoWebService = arquivoWebService;
    }
}
