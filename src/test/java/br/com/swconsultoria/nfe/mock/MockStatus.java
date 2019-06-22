package br.com.swconsultoria.nfe.mock;

import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TConsStatServ;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.time.LocalDateTime;

public class MockStatus {

    public static NFeStatusServico4Stub.NfeResultMsg getNfeResultMsg(NFeStatusServico4Stub.NfeDadosMsg dadosMsg) throws Exception {

        TConsStatServ consStatServ = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TConsStatServ.class);
        String dh = XmlNfeUtil.dataNfe(LocalDateTime.of(2019, 1, 1, 0, 0));

        TRetConsStatServ retorno = new TRetConsStatServ();
        retorno.setVersao(ConstantesUtil.VERSAO.NFE);
        retorno.setTpAmb(consStatServ.getTpAmb());
        retorno.setVerAplic("GO4.0");
        retorno.setCStat(StatusEnum.SERVICO_EM_OPERACAO.getCodigo());
        retorno.setXMotivo("Serviço em Operação");
        retorno.setCUF(consStatServ.getCUF());
        retorno.setDhRecbto(dh);
        retorno.setTMed("1");
        retorno.setDhRetorno(dh);
        String retornoStr = XmlNfeUtil.objectToXml(retorno).replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

        OMElement om = AXIOMUtil.stringToOM("<nfeResultMsg>" + retornoStr + "</nfeResultMsg>");
        return NFeStatusServico4Stub.NfeResultMsg.Factory.parse(om.getXMLStreamReaderWithoutCaching());
    }
}