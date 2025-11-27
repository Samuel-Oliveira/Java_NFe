/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema.envEventoCancSubst.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.CancelamentoSubstituicaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;

import java.time.LocalDateTime;

/**
 * @author Samuel Oliveira
 */
public class CancelarSubstituicaoTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
            //Para isso Foi criado o Objeto Cancela
            Evento cancela = new Evento();
            //Informe a chave da Nota a ser Cancelada
            cancela.setChave("52250810732644000128550010000927491462345823");
            //Informe a chave da Nota a Substituta
            cancela.setChaveSusbstituta("52250810732644000128550010000927501960446967");
            //Informe o protocolo da Nota a ser Cancelada
            cancela.setProtocolo("152250026070346");
            //Informe o CNPJ do emitente
            cancela.setCnpj("10732644000128");
            //Informe o Motivo do Cancelamento
            cancela.setMotivo("Teste de Cancelamento");
            //Informe a data do Cancelamento
            cancela.setDataEvento(LocalDateTime.now());

            //Monta o Evento de Cancelamento
            TEnvEvento enviEvento = CancelamentoSubstituicaoUtil.montaCancelamento(cancela, config);

            //Envia o Evento de Cancelamento
            TRetEnvEvento retorno = Nfe.cancelarSubstituicaoNfe(config, enviEvento, true);

            //Valida o Retorno do Cancelamento
            RetornoUtil.validaCancelamentoSubstituicao(retorno);

            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach( resultado -> {
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });

            //Cria ProcEvento de Cacnelamento
            String proc = CancelamentoSubstituicaoUtil.criaProcEventoCancelamento(config, enviEvento, retorno.getRetEvento().get(0));
            System.out.println();
            System.out.println("# ProcEvento : " + proc);

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: "+e.getMessage());
        }

    }

}
