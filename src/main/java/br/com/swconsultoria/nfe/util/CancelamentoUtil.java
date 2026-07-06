package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.Assinar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamento;
import br.com.swconsultoria.nfe.schemas_eventos.TEventoCancelamento;
import br.com.swconsultoria.nfe.schemas_eventos.TProcEventoCancelamento;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEventoCancelamento;

import javax.xml.bind.JAXBException;
import java.util.Collections;
import java.util.List;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 22:51
 */
public class CancelamentoUtil {

    private CancelamentoUtil() {}

    /**
     * MOnta o Evento de cancelamento unico
     *
     * @param cancela
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEventoCancelamento montaCancelamento(Evento cancela, ConfiguracoesNfe configuracao) throws NfeException {
        return montaCancelamento(Collections.singletonList(cancela), configuracao);
    }

    /**
     * MOnta o Evento de cancelamento Lote
     *
     * @param listaCancela
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEventoCancelamento montaCancelamento(List<Evento> listaCancela, ConfiguracoesNfe configuracao) throws NfeException {

        if (listaCancela.size() > 20) {
            throw new NfeException("Podem ser enviados no máximo 20 eventos no Lote.");
        }

        TEnvEventoCancelamento enviEvento = new TEnvEventoCancelamento();
        enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        enviEvento.setIdLote("1");

        listaCancela.forEach(evento -> {
            String id = "ID" + EventosEnum.CANCELAMENTO.getCodigo() + evento.getChave() + "01";

            TEventoCancelamento eventoCancela = new TEventoCancelamento();
            eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEventoCancelamento.InfEvento infoEvento = new TEventoCancelamento.InfEvento();
            infoEvento.setId(id);
            //MOdificao
            infoEvento.setChNFe(evento.getChave());
            infoEvento.setCOrgao(String.valueOf(configuracao.getEstado().getCodigoUF()));
            infoEvento.setTpAmb(configuracao.getAmbiente().getCodigo());

            infoEvento.setCPF(evento.getCpf());
            infoEvento.setCNPJ(evento.getCnpj());

            infoEvento.setDhEvento(XmlNfeUtil.dataNfe(evento.getDataEvento(), configuracao.getZoneId()));
            infoEvento.setTpEvento(EventosEnum.CANCELAMENTO.getCodigo());
            infoEvento.setNSeqEvento("1");
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEventoCancelamento.InfEvento.DetEventoCancelamento detEvento = new TEventoCancelamento.InfEvento.DetEventoCancelamento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
            detEvento.setDescEvento("Cancelamento");
            detEvento.setNProt(evento.getProtocolo());
            detEvento.setXJust(evento.getMotivo());
            infoEvento.setDetEvento(detEvento);
            eventoCancela.setInfEvento(infoEvento);
            enviEvento.getEvento().add(eventoCancela);
        });

        return enviEvento;
    }

    /**
     * Cria o ProcEvento de Cancelamento
     *
     * @param config
     * @param enviEvento
     * @param retorno
     * @return
     * @throws JAXBException
     * @throws NfeException
     */
    public static String criaProcEventoCancelamento(ConfiguracoesNfe config, TEnvEventoCancelamento enviEvento, TRetEventoCancelamento retorno) throws JAXBException, NfeException {

        String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
        xml = xml.replace(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "")
                .replace("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

        String assinado = Assinar.assinaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xml, AssinaturaEnum.EVENTO);

        TProcEventoCancelamento procEvento = new TProcEventoCancelamento();
        procEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        procEvento.setEvento(XmlNfeUtil.xmlToObject(assinado, TEnvEventoCancelamento.class).getEvento().get(0));
        procEvento.setRetEvento(retorno);

        return XmlNfeUtil.objectToXml(procEvento, config.getEncode());
    }

}
