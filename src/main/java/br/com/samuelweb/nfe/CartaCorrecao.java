package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 28/09/2017 - 11:11
 */
class CartaCorrecao {

    static TEnvEvento criaEventoCartaCorrecao(String chave, String cnpj, String data,String motivo, String nSeqEvento) throws NfeException {

        ConfiguracoesIniciaisNfe configuracoesNfe = CertificadoUtil.iniciaConfiguracoes();

        String id = "ID" + ConstantesUtil.EVENTO.CCE + chave + (nSeqEvento.length() < 2 ? "0" + nSeqEvento : nSeqEvento);

        br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento envEvento = new br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento();
        envEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);
        envEvento.setIdLote("1");

        br.inf.portalfiscal.nfe.schema.envcce.TEvento evento = new br.inf.portalfiscal.nfe.schema.envcce.TEvento();
        evento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);

        br.inf.portalfiscal.nfe.schema.envcce.TEvento.InfEvento infEvento = new br.inf.portalfiscal.nfe.schema.envcce.TEvento.InfEvento();
        infEvento.setId(id);
        infEvento.setCOrgao(configuracoesNfe.getEstado().getCodigoIbge());
        infEvento.setTpAmb(configuracoesNfe.getAmbiente());

        infEvento.setCNPJ(cnpj);
        infEvento.setChNFe(chave);

        // Altere a Data
        infEvento.setDhEvento(data);
        infEvento.setTpEvento(ConstantesUtil.EVENTO.CCE);
        infEvento.setNSeqEvento(nSeqEvento);
        infEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CCE);

        br.inf.portalfiscal.nfe.schema.envcce.TEvento.InfEvento.DetEvento detEvento = new br.inf.portalfiscal.nfe.schema.envcce.TEvento.InfEvento.DetEvento();
        detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CCE);
        detEvento.setDescEvento("Carta de Correcao");

        // Informe a Correção
        detEvento.setXCorrecao(motivo);
        detEvento.setXCondUso("A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.");
        infEvento.setDetEvento(detEvento);
        evento.setInfEvento(infEvento);
        envEvento.getEvento().add(evento);

        return envEvento;

    }

    static TRetEnvEvento eventoCCe(TEnvEvento enviEvento, boolean valida, String tipo) throws NfeException {

        try {

            String xml = XmlUtil.objectToXml(enviEvento);
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            xml = Eventos.enviarEvento(xml,ConstantesUtil.EVENTO.CCE,valida, tipo);

            return XmlUtil.xmlToObject(xml, TRetEnvEvento.class);

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }

}
