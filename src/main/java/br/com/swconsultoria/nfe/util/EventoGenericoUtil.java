package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.Assinar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoGenerico;
import br.com.swconsultoria.nfe.schemas_eventos.TEventoGenerico;
import br.com.swconsultoria.nfe.schemas_eventos.TProcEventoGenerico;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEventoGenerico;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

public class EventoGenericoUtil {

    private EventoGenericoUtil() {}

    /**
     * MOnta o Evento Generico
     *
     * @param evento
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEventoGenerico montaEvento(Evento evento, Class<?> clazz, EventosEnum eventosEnum, ConfiguracoesNfe configuracao) throws NfeException {

        TEnvEventoGenerico enviEvento = new TEnvEventoGenerico();
        enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_GENERICO);
        enviEvento.setIdLote("1");

        String id = "ID" + eventosEnum.getCodigo() + evento.getChave()+ ChaveUtil.completarComZerosAEsquerda(String.valueOf(evento.getSequencia()), 2);

        TEventoGenerico eventoGenerico = new TEventoGenerico();
        eventoGenerico.setVersao(ConstantesUtil.VERSAO.EVENTO_GENERICO);

        TEventoGenerico.InfEvento infoEvento = new TEventoGenerico.InfEvento();
        infoEvento.setId(id);
        infoEvento.setChNFe(evento.getChave());
        infoEvento.setCOrgao(String.valueOf(configuracao.getEstado().getCodigoUF()));
        infoEvento.setTpAmb(configuracao.getAmbiente().getCodigo());

        infoEvento.setCPF(evento.getCpf());
        infoEvento.setCNPJ(evento.getCnpj());

        infoEvento.setDhEvento(XmlNfeUtil.dataNfe(evento.getDataEvento(), configuracao.getZoneId()));
        infoEvento.setTpEvento(eventosEnum.getCodigo());
        infoEvento.setNSeqEvento(String.valueOf(evento.getSequencia()));
        infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_GENERICO);

        TEventoGenerico. InfEvento.DetEvento detEvento = new TEventoGenerico.InfEvento.DetEvento();
        Element element = XmlNfeUtil.objectToElement(evento. getDetEvento(), clazz);
        detEvento.getOtherAttributes().put(new QName("versao"), element.getAttribute("versao"));

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                detEvento.getAny().add((Element) child);
            }
        }

        infoEvento.setDetEvento(detEvento);

        eventoGenerico.setInfEvento(infoEvento);
        enviEvento.getEvento().add(eventoGenerico);

        return enviEvento;
    }

    /**
     * Cria o ProcEvento de Generico
     *
     * @param config
     * @param enviEvento
     * @param retorno
     * @return
     * @throws JAXBException
     * @throws NfeException
     */
    public static String criaProcEventoGenerico(ConfiguracoesNfe config, TEnvEventoGenerico enviEvento, TRetEventoGenerico retorno) throws JAXBException, NfeException {

        String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
        xml = xml.replace(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "")
                .replace("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v")
                .replace("<detEvento v", "<detEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

        String assinado = Assinar.assinaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xml, AssinaturaEnum.EVENTO);

        TProcEventoGenerico procEvento = new TProcEventoGenerico();
        procEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_GENERICO);
        procEvento.setEvento(XmlNfeUtil.xmlToObject(assinado, TEnvEventoGenerico.class).getEvento().get(0));
        procEvento.setRetEvento(retorno);

        return XmlNfeUtil.objectToXml(procEvento, config.getEncode());
    }

}
