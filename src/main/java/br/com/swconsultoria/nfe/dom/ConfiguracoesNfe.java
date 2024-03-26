/**
 *
 */
package br.com.swconsultoria.nfe.dom;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;

/**
 * @author Samuel Oliveira
 * <p>
 * Responsável por iniciar as configurações das operações NF-e.
 * <p>
 * Para iniciar as configurações chame o método estático
 * iniciaConfiguracoes:<br>
 * {@code
 * ConfiguracoesIniciaisNfe.iniciaConfiguracoes(estado, ambiente, certificado, schemas);
 * }
 * @see ConfiguracoesNfe
 * @see ConfiguracoesWebNfe
 */
@Log
public class ConfiguracoesNfe {

    private EstadosEnum estado;
    private AmbienteEnum ambiente;
    private Certificado certificado;
    private String pastaSchemas;
    private Proxy proxy;
    private Integer timeout;
    private boolean contigenciaSVC;
    private boolean validacaoDocumento = true;
    private String arquivoWebService;
    private Integer retry;
    private InputStream cacert;
    private Charset encode;
    private ZoneId zoneId;

    /**
     * Este método recebe como parâmetro os dados necessários para iniciar a
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma.
     * Nessa inicializacao é usado o ZoneId padrao America/Sao_Paulo
     *
     * @param estado       enumeration Estados, UF do emitente.
     * @param ambiente     Enumeration AmbienteEnum
     * @param certificado  objeto Certificado
     * @param pastaSchemas local dos arquivo de schemas da NF-e.
     * @return ConfiguracoesIniciaisNfe
     * @see br.com.swconsultoria.certificado.Certificado
     * @see EstadosEnum
     */
    public static ConfiguracoesNfe criarConfiguracoes(EstadosEnum estado, AmbienteEnum ambiente, Certificado certificado, String pastaSchemas) throws CertificadoException {
        return criarConfiguracoes(estado,ambiente,certificado,pastaSchemas, ZoneId.of("America/Sao_Paulo"));
    }

    /**
     * Este método recebe como parâmetro os dados necessários para iniciar a
     * comunicação de operações dos eventos da NF-e. Retorna uma instância dela
     * mesma.
     *
     * @param estado       enumeration Estados, UF do emitente.
     * @param ambiente     Enumeration AmbienteEnum
     * @param certificado  objeto Certificado
     * @param pastaSchemas local dos arquivo de schemas da NF-e.
     * @param zoneId       Zona para configuracoes de data
     * @return ConfiguracoesIniciaisNfe
     * @see br.com.swconsultoria.certificado.Certificado
     * @see EstadosEnum
     */
    public static ConfiguracoesNfe criarConfiguracoes(EstadosEnum estado, AmbienteEnum ambiente, Certificado certificado, String pastaSchemas, ZoneId zoneId) throws CertificadoException {

        ConfiguracoesNfe configuracoesNfe = new ConfiguracoesNfe();
        configuracoesNfe.setEstado(ObjetoUtil.verifica(estado).orElseThrow(() -> new IllegalArgumentException("Estado não pode ser Nulo.")));
        configuracoesNfe.setAmbiente(ObjetoUtil.verifica(ambiente).orElseThrow(() -> new IllegalArgumentException("Ambiente não pode ser Nulo.")));
        configuracoesNfe.setCertificado(ObjetoUtil.verifica(certificado).orElseThrow(() -> new IllegalArgumentException("Certificado não pode ser Nulo.")));
        configuracoesNfe.setPastaSchemas(pastaSchemas);
        configuracoesNfe.setZoneId(ObjetoUtil.verifica(zoneId).orElseThrow(() -> new IllegalArgumentException("Zone ID não pode ser Nulo.")));

        /**
         * Para as versões Java até 11, Eu ainda seto o Encoding por que é permitido.
         * Para quem trabalha com Java 12+, Aconselhasse setar o Encoding :
         * -Dfile.encoding="UTF-8"
         * -Dsun.jnu.encoding="UTF-8"
         *
         */
        if (Integer.parseInt(System.getProperty("java.class.version").substring(0, 2)) < 56) {
            try {
                //Setando Encoding.
                System.setProperty("file.encoding", "UTF-8");
                Field charset = Charset.class.getDeclaredField("defaultCharset");
                charset.setAccessible(true);
                charset.set(null, null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new CertificadoException("Erro ao setar Encoding.");
            }
        }

        log.info(String.format("JAVA-NFE | Samuel Oliveira | samuel@swconsultoria.com.br " +
                        "| VERSAO=%s | DATA_VERSAO=%s | PASTA_SCHEMAS=%s | AMBIENTE=%s | ESTADO=%s",
                "4.00.31",
                "25/03/2024",
                pastaSchemas,
                ambiente,
                estado.getNome().toUpperCase()));

        if (!certificado.isValido()) {
            throw new CertificadoException("Certificado Vencido/Inválido");
        }
        return configuracoesNfe;
    }

    /**
     * Retorna o local da pasta dos schemas da NF-e(.xsd)
     *
     * @return pastaSchemas
     */
    public String getPastaSchemas() {
        return pastaSchemas;
    }

    /**
     * Atribui uma string que representa o local da pasta dos schemas da NF-e
     * (.xsd)
     *
     * @param pastaSchemas
     */
    private void setPastaSchemas(String pastaSchemas) {
        this.pastaSchemas = pastaSchemas;
    }

    /**
     * Retorna um enuns que representa o ambiente de operações da NF-e.<br>
     *
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
     * estado,
     * AmbienteEnum.HOMOLOGACAO,
     * certificado,
     * schemas);
     * }
     *
     * @param ambiente
     * @see ConstantesUtil
     */
    public void setAmbiente(AmbienteEnum ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * Retorna o objeto Certificado.
     *
     * @return certificado
     * @see br.com.swconsultoria.certificado
     */
    public Certificado getCertificado() {
        return certificado;
    }

    /**
     * Atribui um objeto Certificado.
     *
     * @param certificado
     */
    private void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    /**
     * Retorna um valor booleano que representa se as operações de NF-e estão,
     * ou, não operando no modo de Contingência.
     *
     * @return contigenciaSCAN
     */
    public boolean isContigenciaSVC() {
        return contigenciaSVC;
    }

    /**
     * Atribui um valor para contigenciaSVC. Caso True, as operações da NF-e
     * funcionarão no modo de Contingência. <br>
     * Usar para situações em que não for possível estabelecer conexão com o
     * WebService SEFAZ Origem.
     *
     * @param contigenciaSVC
     */
    public void setContigenciaSVC(boolean contigenciaSVC) {
        this.contigenciaSVC = contigenciaSVC;
    }

    /**
     * Retorna um objeto Estado que representa o UF do emissor da NF-e.
     *
     * @return estado
     * @see EstadosEnum
     */
    public EstadosEnum getEstado() {
        return estado;
    }

    /**
     * Atribui um valor para o atribuito Estado.
     *
     * @param estado estado
     * @see EstadosEnum
     */
    public void setEstado(EstadosEnum estado) {
        this.estado = estado;
    }

    /**
     * Retorna o valor do atributo proxyUtil.
     *
     * @return proxyUtil
     * @see Proxy
     */
    public Proxy getProxy() {
        return proxy;
    }

    /**
     * Atribui um valor para o proxuUtil.
     *
     * @param proxy
     */
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    /**
     * Retorna o valor do atributo timeout.
     *
     * @return timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Atribui o valor de timeout.<br>
     * O timeout é o limite de tempo(em milisegundos) de comunicação com
     * WebService. Sugerido pelo manual do contribuinte: 30000.
     *
     * @param timeout
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * Retorna o valor da validacaoDocumento.
     *
     * @return validacaoDocumento
     */
    public boolean isValidacaoDocumento() {
        return validacaoDocumento;
    }

    /**
     * Atribui um valor para validacaoDocumento. Caso True, irá validar o
     * documento do emitente com o documento do certificado. <br>
     *
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

    /**
     * Retorna o valor do atributo retry.
     *
     * @return
     */
    public Integer getRetry() {
        return retry;
    }

    /**
     * Permite informar um retry. O padrão é de 3.<br>
     * Ao definir um retry indicamos o valor a ser usado como um número de
     * tentativas para a conexão com WebService.
     *
     * @param retry
     */
    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public InputStream getCacert() {
        return cacert;
    }

    public void setCacert(InputStream cacert) {
        this.cacert = cacert;
    }

    /**
     * Recupera encode que será utilizado na geração do arquivo XML.
     *
     * @return
     */
    public Charset getEncode() {return encode;}

    /**
     * Altera o encode utilizado para criar o arquivo xml.<br>
     * Por padrão é utilizado o UTF-8 em caso de erro ou não ser
     * informado nada.
     *
     * @param encode
     */
    public void setEncode(Charset encode) {this.encode = encode;}

    /**
     * Passar encode via String para o xml.
     *
     * @param nomeEncode
     */
    public void setEncode(String nomeEncode) {
        if (nomeEncode != null && !nomeEncode.equals("")) {
            try {
                this.encode = Charset.forName(nomeEncode);
            } catch (Exception ex) {
                this.encode = StandardCharsets.UTF_8;
            }
        }
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }
}
