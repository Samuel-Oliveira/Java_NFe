package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.Assinar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envcce.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TEvento;
import br.com.swconsultoria.nfe.schema.envcce.TProcEvento;
import br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento;

import javax.xml.bind.JAXBException;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 22:51
 */
public class CartaCorrecaoUtil {

    /**
     * MOnta o Evento de CCe unico
     * @param cce
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCCe(Evento cce, ConfiguracoesNfe configuracao, ZoneId zoneId) throws NfeException {
        return montaCCe(Collections.singletonList(cce), configuracao,zoneId);
    }
    /**
     * MOnta o Evento de CCe unico
     * @param cce
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCCe(Evento cce, ConfiguracoesNfe configuracao) throws NfeException {
        return montaCCe(Collections.singletonList(cce), configuracao);
    }

    /**
     * MOnta o Evento de CCe em Lote
     * @param listaCCe
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCCe(List<Evento> listaCCe, ConfiguracoesNfe configuracao) throws NfeException {
        return montaCCe(listaCCe,configuracao,null);
    }
    /**
     * MOnta o Evento de CCe em Lote
     * @param listaCCe
     * @param configuracao
     * @return
     * @throws NfeException
     */
    public static TEnvEvento montaCCe(List<Evento> listaCCe, ConfiguracoesNfe configuracao, ZoneId zoneId) throws NfeException {

        if (listaCCe.size() > 20) {
            throw new NfeException("Podem ser enviados no máximo 20 eventos no Lote.");
        }

        TEnvEvento envEvento = new TEnvEvento();
        envEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);
        envEvento.setIdLote("1");

        listaCCe.forEach(cce -> {
            String id = "ID" + EventosEnum.CCE.getCodigo() + cce.getChave() + ChaveUtil.completarComZerosAEsquerda(String.valueOf(cce.getSequencia()), 2);


            TEvento evento = new TEvento();
            evento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);

            TEvento.InfEvento infEvento = new TEvento.InfEvento();
            infEvento.setId(id);
            infEvento.setCOrgao(configuracao.getEstado().getCodigoUF());
            infEvento.setTpAmb(configuracao.getAmbiente().getCodigo());

            infEvento.setCNPJ(cce.getCnpj());
            infEvento.setChNFe(cce.getChave());

            // Altere a Data
            infEvento.setDhEvento(XmlNfeUtil.dataNfe(cce.getDataEvento(),zoneId));
            infEvento.setTpEvento(EventosEnum.CCE.getCodigo());
            infEvento.setNSeqEvento(String.valueOf(cce.getSequencia()));
            infEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CCE);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);
            detEvento.setDescEvento("Carta de Correcao");

            // Informe a Correção
            detEvento.setXCorrecao(cce.getMotivo());
            detEvento.setXCondUso("A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.");
            infEvento.setDetEvento(detEvento);
            evento.setInfEvento(infEvento);
            envEvento.getEvento().add(evento);
        });

        return envEvento;
    }

    /**
     * Cria o ProcEvento de CCe
     *
     * @param configuracoesNfe
     * @param enviEvento
     * @param retorno
     * @return
     * @throws JAXBException
     * @throws NfeException
     */
    public static String criaProcEventoCCe(ConfiguracoesNfe configuracoesNfe, TEnvEvento enviEvento, TRetEnvEvento retorno) throws JAXBException, NfeException {

        String xml = XmlNfeUtil.objectToXml(enviEvento);
        xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

        String assinado = Assinar.assinaNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe), xml, AssinaturaEnum.EVENTO);

        TProcEvento procEvento = new TProcEvento();
        procEvento.setEvento(XmlNfeUtil.xmlToObject(assinado, TEnvEvento.class).getEvento().get(0));
        procEvento.setRetEvento(retorno.getRetEvento().get(0));
        procEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);

        return XmlNfeUtil.objectToXml(procEvento);
    }
}
