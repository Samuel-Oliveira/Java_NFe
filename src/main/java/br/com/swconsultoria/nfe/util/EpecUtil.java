package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.Assinar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoEpec;
import br.com.swconsultoria.nfe.schemas_eventos.TEventoEpec;
import br.com.swconsultoria.nfe.schemas_eventos.TUf;
import br.com.swconsultoria.nfe.schemas_eventos.TProcEventoEpec;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoEpec;

import javax.xml.bind.JAXBException;
import java.util.Collections;
import java.util.List;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 22:51
 */
public class EpecUtil {

    private EpecUtil() {}

    /**
     * MOnta o Evento de epec Lote
     *
     * @param epec
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEventoEpec montaEpec(Evento epec, ConfiguracoesNfe configuracao) throws NfeException {
        return montaEpec(Collections.singletonList(epec), configuracao);
    }

    /**
     * MOnta o Evento de epec Lote
     *
     * @param listaEpec
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEventoEpec montaEpec(List<Evento> listaEpec, ConfiguracoesNfe configuracao) throws NfeException {

        if (listaEpec.size() > 20) {
            throw new NfeException("Podem ser enviados no máximo 20 eventos no Lote.");
        }

        TEnvEventoEpec enviEvento = new TEnvEventoEpec();
        enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);
        enviEvento.setIdLote("1");

        listaEpec.forEach(epec -> {

            String id = "ID" + EventosEnum.EPEC.getCodigo() + epec.getChave() + "01";

            TEventoEpec eventoEpec = new TEventoEpec();
            eventoEpec.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);

            TEventoEpec.InfEvento infoEvento = new TEventoEpec.InfEvento();
            infoEvento.setId(id);
            infoEvento.setCOrgao("91");
            infoEvento.setTpAmb(configuracao.getAmbiente().getCodigo());

            infoEvento.setCPF(epec.getCpf());
            infoEvento.setCNPJ(epec.getCnpj());

            infoEvento.setChNFe(epec.getChave());
            infoEvento.setDhEvento(XmlNfeUtil.dataNfe(epec.getDataEvento(), configuracao.getZoneId()));
            infoEvento.setTpEvento(EventosEnum.EPEC.getCodigo());
            infoEvento.setNSeqEvento("1");
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_EPEC);

            TEventoEpec.InfEvento.DetEventoEpec detEvento = new TEventoEpec.InfEvento.DetEventoEpec();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);
            detEvento.setDescEvento("EPEC");
            detEvento.setCOrgaoAutor(configuracao.getEstado().getCodigoUF());
            detEvento.setTpAutor("1");
            detEvento.setVerAplic("1.0.0");
            detEvento.setDhEmi(XmlNfeUtil.dataNfe(epec.getDataEvento(), configuracao.getZoneId()));
            detEvento.setTpNF(epec.getEventoEpec().getTipoNF());
            detEvento.setIE(epec.getEventoEpec().getIeEmitente());

            TEventoEpec.InfEvento.DetEventoEpec.Dest dest = new TEventoEpec.InfEvento.DetEventoEpec.Dest();
            dest.setUF(TUf.valueOf(epec.getEventoEpec().getEstadoDestinatario().toString()));
            dest.setCNPJ(epec.getEventoEpec().getCnpjDestinatario());
            dest.setCPF(epec.getEventoEpec().getCpfDestinatario());
            dest.setIE(epec.getEventoEpec().getIeDestinatario());
            dest.setVNF(epec.getEventoEpec().getvNF());
            dest.setVICMS(epec.getEventoEpec().getvICMS());
            dest.setVST(epec.getEventoEpec().getvST());
            detEvento.setDest(dest);

            infoEvento.setDetEvento(detEvento);
            eventoEpec.setInfEvento(infoEvento);

            enviEvento.getEvento().add(eventoEpec);

        });

        return enviEvento;
    }

    /**
     * Cria o ProcEvento de CCe
     *
     * @param config
     * @param enviEvento
     * @param retorno
     * @return
     * @throws JAXBException
     * @throws NfeException
     */
    public static String criaProcEventoEpec(ConfiguracoesNfe config, TEnvEventoEpec enviEvento, TRetEnvEventoEpec retorno) throws JAXBException, NfeException {

        String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
        xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replace("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

        String assinado = Assinar.assinaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xml, AssinaturaEnum.EVENTO);

        TProcEventoEpec procEvento = new TProcEventoEpec();
        procEvento.setEvento(XmlNfeUtil.xmlToObject(assinado, TEnvEventoEpec.class).getEvento().get(0));
        procEvento.setRetEvento(retorno.getRetEvento().get(0));
        procEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_EPEC);

        return XmlNfeUtil.objectToXml(procEvento, config.getEncode());
    }

}
