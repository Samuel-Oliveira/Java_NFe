package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.Assinar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.ManifestacaoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TretEvento;

import javax.xml.bind.JAXBException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 22:51
 */
public class ManifestacaoUtil {

    private ManifestacaoUtil() {}

    /**
     * MOnta o Evento de Manifestacao Unico
     *
     * @param manifesta
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaManifestacao(Evento manifesta, ConfiguracoesNfe configuracao) throws NfeException {
        return montaManifestacao(Collections.singletonList(manifesta), configuracao);
    }

    /**
     * MOnta o Evento de Manifestacao Lote
     *
     * @param listaManifestacao
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaManifestacao(List<Evento> listaManifestacao, ConfiguracoesNfe configuracao) throws NfeException {

        if (listaManifestacao.size() > 20) {
            throw new NfeException("Podem ser enviados no máximo 20 eventos no Lote.");
        }

        TEnvEvento enviEvento = new TEnvEvento();
        enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);
        enviEvento.setIdLote("1");

        listaManifestacao.forEach(manifestacao -> {

            String id = "ID" + manifestacao.getTipoManifestacao().getCodigo() + manifestacao.getChave() + "01";

            TEvento evento = new TEvento();
            evento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);

            TEvento.InfEvento infEvento = new TEvento.InfEvento();
            infEvento.setId(id);
            infEvento.setCOrgao("91");
            infEvento.setTpAmb(configuracao.getAmbiente().getCodigo());

            infEvento.setCPF(manifestacao.getCpf());
            infEvento.setCNPJ(manifestacao.getCnpj());

            infEvento.setChNFe(manifestacao.getChave());
            infEvento.setDhEvento(XmlNfeUtil.dataNfe(manifestacao.getDataEvento(), configuracao.getZoneId()));
            infEvento.setTpEvento(manifestacao.getTipoManifestacao().getCodigo());
            infEvento.setNSeqEvento("1");
            infEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);
            detEvento.setDescEvento(manifestacao.getTipoManifestacao().getValor());
            if (ManifestacaoEnum.OPERACAO_NAO_REALIZADA.equals(manifestacao.getTipoManifestacao())) {
                detEvento.setXJust(manifestacao.getMotivo());
            }
            infEvento.setDetEvento(detEvento);
            evento.setInfEvento(infEvento);
            enviEvento.getEvento().add(evento);
        });

        return enviEvento;
    }

    /**
     * Cria e assina o tag procEventoNFe
     *
     * @param config Um {@link ConfiguracoesNfe}, interface de configuração da NF-e ou NFC-e.
     * @param enviEvento Um {@link TEnvEvento} com a estrutura com a mensagem enviada para o sistema de distribuição.
     * @param retorno Um {@link TretEvento} com os dadps do resultado do Envio do Evento.
     * @return Uma {@link String} retornando um XML de evento assinado.
     * @throws JAXBException
     * @throws NfeException
     */
    public static String criaProcEventoManifestacao(ConfiguracoesNfe config, TEnvEvento enviEvento, TretEvento retorno) throws JAXBException, NfeException {

        String xml = XmlNfeUtil.objectToXml(enviEvento, config.getEncode());
        xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replace("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

        String assinado = Assinar.assinaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xml, AssinaturaEnum.EVENTO);

        TProcEvento procEvento = new TProcEvento();
        procEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_MANIFESTAR);

        Optional<TEvento> optEvento = XmlNfeUtil.xmlToObject(assinado, TEnvEvento.class)
                        .getEvento()
                        .stream()
                        .filter(e -> e.getInfEvento().getChNFe().equalsIgnoreCase(retorno.getInfEvento().getChNFe()))
                        .findFirst();

        if (optEvento.isPresent()) {
            procEvento.setEvento(optEvento.get());
        }

        procEvento.setRetEvento(retorno);

        return XmlNfeUtil.objectToXml(procEvento, config.getEncode());
    }

}
