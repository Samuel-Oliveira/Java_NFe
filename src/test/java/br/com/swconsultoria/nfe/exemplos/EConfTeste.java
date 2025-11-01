package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoEConf.DetEvento;
import br.com.swconsultoria.nfe.schema.envEventoEConf.TEvento;
import br.com.swconsultoria.nfe.schema.envEventoEConf.TUfEmi;
import br.com.swconsultoria.nfe.schema.eventoEConf.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.time.LocalDateTime;

/**
 * @author Samuel Oliveira
 *
 */
public class EConfTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            // Monta o Evento
            br.com.swconsultoria.nfe.schema.envEventoEConf.TEnvEvento envEvento = new br.com.swconsultoria.nfe.schema.envEventoEConf.TEnvEvento();
            envEvento.setVersao("1.00");
            envEvento.setIdLote("1");

            TEvento evento = new TEvento();
            evento.setVersao("1.00");
            TEvento.InfEvento infEvento = new TEvento.InfEvento();
            infEvento.setId("ID" + "110750" + "52251010732644000128650030000004409296182274" + "01");
            infEvento.setCOrgao("52");
            infEvento.setTpAmb("2");
            infEvento.setCNPJ("10732644000128");
            infEvento.setChNFe("52251010732644000128650030000004409296182274");
            infEvento.setDhEvento(XmlNfeUtil.dataNfe(LocalDateTime.now()));
            infEvento.setTpEvento("110750");
            infEvento.setNSeqEvento("1");
            infEvento.setVerEvento("1.00");

            DetEvento detEvento = new DetEvento();
            detEvento.setVersao("1.00");
            detEvento.setDescEvento("ECONF");
            detEvento.setVerAplic("1.00");
            infEvento.setDetEvento(detEvento);
            evento.setInfEvento(infEvento);
            envEvento.getEvento().add(evento);

            DetEvento.DetPag detPag = new DetEvento.DetPag();
            detPag.setIndPag("1");
            detPag.setTPag("04");
            detPag.setVPag("500.00");
            detPag.setDPag("2025-09-21");
            detPag.setCNPJPag("10440482000154");
            detPag.setUFPag(TUfEmi.GO);
            detPag.setTBand("02");
            detPag.setCNPJIF("10440482000154");
            detPag.setCAut("JFMfVe");
            envEvento.getEvento().get(0).getInfEvento().getDetEvento().getDetPag().add(detPag);

            System.out.println(XmlNfeUtil.objectToXml(envEvento));

            //Envia a ECONF
            TRetEnvEvento retorno = Nfe.econf(config, envEvento, DocumentoEnum.NFCE, true);

            if (!retorno.getCStat().equals("128")) {
                throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
            }

            if (!retorno.getRetEvento().get(0).getInfEvento().getCStat().equals("135")) {
                throw new NfeException(retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - " + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            }

            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach(resultado -> {
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });

        } catch (Exception e) {
            System.err.println();
            System.err.println(e.getMessage());
        }

    }

}
