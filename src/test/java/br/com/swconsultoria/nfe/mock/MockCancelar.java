package br.com.swconsultoria.nfe.mock;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

public class MockCancelar {

    public static NFeRecepcaoEvento4Stub.NfeResultMsg getNfeResultMsg(NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg, String cStat, String xMotivo) throws Exception {

        TEnvEvento envEvento = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TEnvEvento.class);

        String protocolo = Instant.now().toEpochMilli() + String.format("%02d", new Random().nextInt(99));
        String dh = XmlNfeUtil.dataNfe(LocalDateTime.of(2019, 1, 1, 0, 0));

        TRetEnvEvento retorno = new TRetEnvEvento();
        retorno.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        retorno.setIdLote(envEvento.getIdLote());
        retorno.setTpAmb(envEvento.getEvento().get(0).getInfEvento().getTpAmb());
        retorno.setVerAplic("GO4.0");
        retorno.setCOrgao(envEvento.getEvento().get(0).getInfEvento().getCOrgao());
        retorno.setCStat(StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo());
        retorno.setXMotivo("Lote de Evento Processado");

        TRetEvento.InfEvento infEvento = new TRetEvento.InfEvento();
        infEvento.setId(envEvento.getEvento().get(0).getInfEvento().getId());
        infEvento.setTpAmb(envEvento.getEvento().get(0).getInfEvento().getTpAmb());
        infEvento.setVerAplic("GO4.0");
        infEvento.setCOrgao(envEvento.getEvento().get(0).getInfEvento().getCOrgao());
        infEvento.setCStat(cStat);
        infEvento.setXMotivo(xMotivo);
        infEvento.setChNFe(envEvento.getEvento().get(0).getInfEvento().getChNFe());
        infEvento.setTpEvento(envEvento.getEvento().get(0).getInfEvento().getTpEvento());
        infEvento.setXEvento(envEvento.getEvento().get(0).getInfEvento().getDetEvento().getDescEvento());
        infEvento.setNSeqEvento(envEvento.getEvento().get(0).getInfEvento().getNSeqEvento());
        infEvento.setDhRegEvento(dh);
        infEvento.setNProt(protocolo);

        TRetEvento tRetEvento = new TRetEvento();
        tRetEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
        tRetEvento.setInfEvento(infEvento);
        retorno.getRetEvento().add(tRetEvento);

        String retornoStr = XmlNfeUtil.objectToXml(retorno).replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>","");

        OMElement om = AXIOMUtil.stringToOM("<nfeResultMsg>" + retornoStr + "</nfeResultMsg>");
        return NFeRecepcaoEvento4Stub.NfeResultMsg.Factory.parse(om.getXMLStreamReaderWithoutCaching());
    }

    public static TEnvEvento criaEventoCancelamento(ConfiguracoesNfe configuracoesNfe) throws NfeException {

        Evento cancela = new Evento();
        cancela.setChave("99999999999999999999999999999999999999999999");
        cancela.setProtocolo("000000000000000");
        cancela.setCnpj(configuracoesNfe.getCertificado().getCnpjCpf());
        cancela.setMotivo("Teste de Cancelamento");
        cancela.setDataEvento(LocalDateTime.now());
        return CancelamentoUtil.montaCancelamento(cancela, configuracoesNfe, ZoneId.systemDefault());
    }
}
