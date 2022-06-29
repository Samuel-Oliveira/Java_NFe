# Envio Epec

### Envio
```java title="EpecTeste.java"
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.EventoEpec;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema.envEpec.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.EpecUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;

import java.time.LocalDateTime;

/**
 * @author Samuel Oliveira
 */
public class EpecTeste {
    public static void main(String[] args) {
        try {
            // Inicia As Configurações (1)
            ConfiguracoesNfe config = Config.iniciaConfiguracoes();

            //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
            //Para isso Foi criado o Objeto Epec
            Evento epec = new Evento();
            //Informe a chave da Epec
            epec.setChave("52190310732644000128550010000125491000125491");
            //Informe o CNPJ do emitente
            epec.setCnpj("10732644000128");
            //Informe a data do EPEC
            epec.setDataEvento(LocalDateTime.now());
            //Preenche os Dados do Evento EPEC
            EventoEpec eventoEpec = new EventoEpec();
            eventoEpec.setCnpjDestinatario("X");
            eventoEpec.setvST("X");
            eventoEpec.setvNF("X");
            eventoEpec.setvICMS("X");
            eventoEpec.setTipoNF("X");
            eventoEpec.setIeEmitente("X");
            eventoEpec.setIeDestinatario("X");
            eventoEpec.setEstadoDestinatario(EstadosEnum.GO);
            epec.setEventoEpec(eventoEpec);

            //Monta o Evento de Cancelamento
            TEnvEvento enviEvento = EpecUtil.montaEpec(epec,config);

            //Envia Evento EPEC
            TRetEnvEvento retorno = Nfe.enviarEpec(config, enviEvento, true);

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
```