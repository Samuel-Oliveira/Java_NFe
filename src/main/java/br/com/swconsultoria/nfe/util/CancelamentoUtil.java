package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.Assinar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;

import javax.xml.bind.JAXBException;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 22:51
 */
public class CancelamentoUtil {

    /**
     * MOnta o Evento de cancelamento unico
     * @param cancela
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCancelamento(Evento cancela, ConfiguracoesNfe configuracao) throws NfeException {
        return montaCancelamento(Collections.singletonList(cancela), configuracao);
    }
    /**
     * MOnta o Evento de cancelamento unico
     * @param cancela
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCancelamento(Evento cancela, ConfiguracoesNfe configuracao, ZoneId zoneId) throws NfeException {
        return montaCancelamento(Collections.singletonList(cancela), configuracao, zoneId);
    }

    /**
     * MOnta o Evento de cancelamento Lote
     * @param listaCancela
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCancelamento(List<Evento> listaCancela, ConfiguracoesNfe configuracao) throws NfeException {
        return montaCancelamento(listaCancela,configuracao, null);
    }
    /**
     * MOnta o Evento de cancelamento Lote
     * @param listaCancela
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCancelamento(List<Evento> listaCancela, ConfiguracoesNfe configuracao, ZoneId zoneId) throws NfeException {

        if(listaCancela.size() > 20){
            throw new NfeException("Podem ser enviados no máximo 20 eventos no Lote.");
        }

        TEnvEvento enviEvento = new TEnvEvento();
        enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        enviEvento.setIdLote("1");

        listaCancela.forEach(evento -> {
            String id = "ID" + EventosEnum.CANCELAMENTO.getCodigo() + evento.getChave() + "01";

            TEvento eventoCancela = new TEvento();
            eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento infoEvento = new TEvento.InfEvento();
            infoEvento.setId(id);
            //MOdificao
            infoEvento.setChNFe(evento.getChave());
            infoEvento.setCOrgao(String.valueOf(configuracao.getEstado().getCodigoUF()));
            infoEvento.setTpAmb(configuracao.getAmbiente().getCodigo());
            infoEvento.setCNPJ(evento.getCnpj());

            infoEvento.setDhEvento(XmlNfeUtil.dataNfe(evento.getDataEvento(),zoneId));
            infoEvento.setTpEvento(EventosEnum.CANCELAMENTO.getCodigo());
            infoEvento.setNSeqEvento("1");
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
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
     * @param config
     * @param enviEvento
     * @param retorno
     * @return
     * @throws JAXBException
     * @throws NfeException
     */
    public static String criaProcEventoCancelamento(ConfiguracoesNfe config, TEnvEvento enviEvento, TRetEvento retorno) throws JAXBException, NfeException {

        String xml = XmlNfeUtil.objectToXml(enviEvento);
        xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

        String assinado = Assinar.assinaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xml, AssinaturaEnum.EVENTO);

        TProcEvento procEvento = new TProcEvento();
        procEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        procEvento.setEvento(XmlNfeUtil.xmlToObject(assinado, TEnvEvento.class).getEvento().get(0));
        procEvento.setRetEvento(retorno);

        return XmlNfeUtil.objectToXml(procEvento);
    }

}
