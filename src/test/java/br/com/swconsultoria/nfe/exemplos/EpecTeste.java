package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.EventoEpec;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoEpec;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoEpec;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.EpecUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author Samuel Oliveira
 */
public class EpecTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
            //Para isso Foi criado o Objeto Epec
            Evento epec = new Evento();
            //Informe a chave da Epec
            // O EPEC so aceita chave com tpEmis=4 (rejeicao 484): ela e montada aqui,
            // e nao fixada, para refletir a nota que sera emitida em contingencia.
            String cnpjEmit = "10732644000128";
            int numeroNfe = Integer.parseInt(System.getProperty("numeroNfeEpec", "94000"));
            String cnf = String.format("%08d", new Random().nextInt(99999999));
            ChaveUtil chaveUtil = new ChaveUtil(EstadosEnum.GO, cnpjEmit, "55", 1,
                    numeroNfe, "4", cnf, LocalDateTime.now());
            epec.setChave(chaveUtil.getChaveNF().replace("NFe", ""));
            //Informe o CNPJ do emitente
            epec.setCnpj("10732644000128");
            //Informe a data do EPEC
            epec.setDataEvento(LocalDateTime.now());
            //Preenche os Dados do Evento EPEC
            EventoEpec eventoEpec = new EventoEpec();
            eventoEpec.setCnpjDestinatario("10732644000128");
            eventoEpec.setvST("0.00");
            eventoEpec.setvNF("13.00");
            eventoEpec.setvICMS("0.00");
            eventoEpec.setTipoNF("1");
            eventoEpec.setIeEmitente("104519304");
            eventoEpec.setIeDestinatario("104519304");
            eventoEpec.setEstadoDestinatario(EstadosEnum.GO);
            epec.setEventoEpec(eventoEpec);

            //Monta o Evento de Cancelamento
            TEnvEventoEpec enviEvento = EpecUtil.montaEpec(epec,config);

            //Envia Evento EPEC
            TRetEnvEventoEpec retorno = Nfe.enviarEpec(config, enviEvento, true);

            //Valida o Retorno do Cancelamento
            RetornoUtil.validaEpec(retorno);

            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach( resultado -> {
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });
            //Cria ProcEvento de Cacnelamento
            String proc = EpecUtil.criaProcEventoEpec(config, enviEvento, retorno);
            System.out.println();
            System.out.println("# ProcEvento : " + proc);

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: "+e.getMessage());
        }

    }

}
