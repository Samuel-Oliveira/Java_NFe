/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.consStatServ.TRetConsStatServ;

/**
 * @author Samuel Oliveira
 */
public class StatusServicoTeste {

    public static void main(String[] args) {

        try {
            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            try {
                //Efetua Consulta
                TRetConsStatServ retorno = Nfe.statusServico(config, DocumentoEnum.NFCE);

                //Resultado
                System.out.println();
                System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("# Erro: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("# Erro: " + e.getMessage());
        }
    }
}
