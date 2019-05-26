package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;

/**
 * Classe Responsavel Por Carregar as informações do Certificado Digital
 * 
 * @author Samuel Oliveira
 * 
 */
public class ConfiguracoesUtil {
    
     /**
     * Recebe como parâmetro um objeto ConfiguracoesNfe e Inicializa as COnfigurações e retorna um objeto
     * ConfiguracoesNfe.
     * 
     * <p>
     * Semelhante ao método iniciaConfiguracoes(), o Certificado Digital será 
     * validado e inicializado.Caso ocorrá algum prolema será disparado um 
     * NfeException
     * </p>
     * 
     * @param configuracoesNfe
     * @return ConfiguracoesWebNfe
     * @throws NfeException 
     * @see CertificadoException
     * @see ConfiguracoesWebNfe
     */
    public static ConfiguracoesNfe iniciaConfiguracoes(ConfiguracoesNfe configuracoesNfe) throws NfeException {


        return iniciaConfiguracoes(configuracoesNfe, null);
    }

     /**
     * Recebe como parâmetro um objeto ConfiguracoesNfe e Inicializa as COnfigurações e retorna um objeto
     * ConfiguracoesNfe.
     *
     * <p>
     * Semelhante ao método iniciaConfiguracoes(), o Certificado Digital será
     * validado e inicializado.Caso ocorrá algum prolema será disparado um
     * NfeException
     * </p>
     *
     * @param configuracoesNfe
     * @param cpfCnpj
     * @return ConfiguracoesWebNfe
     * @throws NfeException
     * @see CertificadoException
     * @see ConfiguracoesWebNfe
     */
    public static ConfiguracoesNfe iniciaConfiguracoes(ConfiguracoesNfe configuracoesNfe, String cpfCnpj) throws NfeException {

        ObjetoUtil.verifica(configuracoesNfe).orElseThrow( () -> new NfeException("Configurações não foram criadas"));

        try {
            if (!configuracoesNfe.getCertificado().isValido()) {
                throw new CertificadoException("Certificado vencido ou inválido.");
            }

            if (configuracoesNfe.isValidacaoDocumento() && cpfCnpj != null && !configuracoesNfe.getCertificado().getCnpjCpf().substring(0,8).equals(cpfCnpj.substring(0,8))) {
                throw new CertificadoException("Documento do Certificado("+configuracoesNfe.getCertificado().getCnpjCpf()+") não equivale ao Documento do Emissor("+cpfCnpj+")");
            }
            CertificadoService.inicializaCertificado(configuracoesNfe.getCertificado(), ConfiguracoesUtil.class.getResourceAsStream("/Cacert"));
        } catch (CertificadoException e) {
            throw new NfeException(e.getMessage());
        }

        return configuracoesNfe;
    }

}