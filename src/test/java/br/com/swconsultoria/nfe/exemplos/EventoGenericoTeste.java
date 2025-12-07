/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.schema.evento112110.DetEvento;
import br.com.swconsultoria.nfe.schema.eventoGenerico.TEnvEvento;
import br.com.swconsultoria.nfe.schema.eventoGenerico.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.EventoGenericoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;

import java.time.LocalDateTime;

/**
 * @author Samuel Oliveira
 */
public class EventoGenericoTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Dados Basicos Evento
            Evento generico = new Evento();
            generico.setChave("52250810732644000128550010000927491462345823");
            generico.setCnpj("10732644000128");
            generico.setSequencia(2);
            generico.setDataEvento(LocalDateTime.now());

            //Monta Eventos da Receita, Aqui pode ser qualquer um.
            //Abaixo Exemplo do 112110
            DetEvento detEvento = new DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_GENERICO);
            detEvento.setDescEvento("Informação de efetivo pagamento integral para liberar crédito presumido do adquirente");
            detEvento.setCOrgaoAutor(config.getEstado().getCodigoUF());
            detEvento.setTpAutor("1");
            detEvento.setVerAplic("v4.00.46");
            detEvento.setIndQuitacao("1");
            generico.setDetEvento(detEvento);

            //Monta o Evento Generico
            TEnvEvento enviEvento = EventoGenericoUtil.montaEvento(generico,DetEvento.class, EventosEnum.PAGAMENTO_INTEGRAL_CREDITO_PRESUMIDO, config);

            //Envia o Evento Generico
            TRetEnvEvento retorno = Nfe.eventoGenerico(config, enviEvento, true);

            //Valida o Retorno do Cancelamento
            RetornoUtil.validaEventoGenerico(retorno);

            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach( resultado -> {
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });

            //Cria ProcEvento Generico
            String proc = EventoGenericoUtil.criaProcEventoGenerico(config, enviEvento, retorno.getRetEvento().get(0));
            System.out.println();
            System.out.println("# ProcEvento : " + proc);

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: "+e.getMessage());
        }

    }

}
