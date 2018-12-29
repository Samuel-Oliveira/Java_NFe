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