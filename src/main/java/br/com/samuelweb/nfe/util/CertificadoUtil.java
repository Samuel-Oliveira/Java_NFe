package br.com.samuelweb.nfe.util;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * Classe Responsavel Por Carregar as informações do Certificado Digital.
 * 
 * @author Samuel Oliveira
 * 
 */
public class CertificadoUtil {

    /**
     * Retorna uma instância do objeto ConfiguracoesIniciaisNfe.<p>
     * O método faz a leitura dos dados do Certificado Digital, se ocorrer
     * algum problema, disparará um NfeException. Se o Certificado Digital for<p> 
     * inválido disparará um CertificadoException
     * @return ConfiguracoesIniciaisNfe
     * @throws NfeException 
     * @see CertificadoException
     * @see ConfiguracoesIniciaisNfe
     */
    public static ConfiguracoesIniciaisNfe iniciaConfiguracoes() throws NfeException {

        try {
            Certificado certificado = ConfiguracoesIniciaisNfe.getInstance().getCertificado();
            if (!certificado.isValido()) {
                throw new CertificadoException("Certificado vencido.");
            }
            CertificadoService.inicializaCertificado(certificado, CertificadoUtil.class.getResourceAsStream("/Cacert"));
        } catch (CertificadoException e) {
            throw new NfeException(e.getMessage());
        }

        return ConfiguracoesIniciaisNfe.getInstance();
    }

    /**
     * Recebe como parâmetro um objeto ConfiguracoesWebNfe e retorna um objeto 
     * ConfiguracoesWebNfe.<p>
     * Semelhante ao método iniciaConfiguracoes(), o Certificado Digital será 
     * validado e inicializado.Caso ocorrá algum prolema será disparado um 
     * NfeException
     * @param config
     * @return ConfiguracoesWebNfe
     * @throws NfeException 
     * @see CertificadoException
     * @see ConfiguracoesWebNfe
     */
    public static ConfiguracoesWebNfe iniciaConfiguracoes(ConfiguracoesWebNfe config) throws NfeException {

        try {
            Certificado certificado = config.getCertificado();
            if (!certificado.isValido()) {
                throw new CertificadoException("Certificado vencido.");
            }
            CertificadoService.inicializaCertificado(certificado, CertificadoUtil.class.getResourceAsStream("/Cacert"));
        } catch (CertificadoException e) {
            throw new NfeException(e.getMessage());
        }

        return config;
    }



}