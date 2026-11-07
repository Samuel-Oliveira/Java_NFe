/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Samuel Oliveira
 *
 */
public class ConfiguracaoTeste {

    public static ConfiguracoesNfe iniciaConfiguracoes(EstadosEnum estado, AmbienteEnum ambiente) throws Exception {

        boolean habilitaLog = true;
        if (habilitaLog) {
            Logger.getLogger("").setLevel(Level.ALL);
        } else {
            Logger.getLogger("").setLevel(Level.WARNING);
        }

        // Caminho e senha podem vir por -Dcertificado e -DsenhaCertificado, para
        // rodar os exemplos sem editar o fonte. Os valores abaixo sao o padrao.
        Certificado certificado = CertificadoService.certificadoPfx(
                System.getProperty("certificado", "d:/teste/certificado.pfx"),
                System.getProperty("senhaCertificado", "123456"));

        return ConfiguracoesNfe.criarConfiguracoes(estado, ambiente, certificado, "./schemas");
    }

}
