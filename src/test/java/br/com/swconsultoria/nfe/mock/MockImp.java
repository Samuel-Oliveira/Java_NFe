package br.com.swconsultoria.nfe.mock;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.swconsultoria.nfe.Mock;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TConsStatServ;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;

public class MockImp implements Mock {

	@Override
	public NFeStatusServico4Stub.NfeResultMsg nfeStatusServicoNF(NFeStatusServico4Stub.NfeDadosMsg dadosMsg) {
		try {
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
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public NFeRecepcaoEvento4Stub.NfeResultMsg nfeRecepcaoEvento(NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg) {
		try {
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
			infEvento.setCStat(StatusEnum.EVENTO_VINCULADO.getCodigo());
			infEvento.setXMotivo("Evento registrado e vinculado a NF-e");
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

			String retornoStr = XmlNfeUtil.objectToXml(retorno).replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

			OMElement om = AXIOMUtil.stringToOM("<nfeResultMsg>" + retornoStr + "</nfeResultMsg>");
			return NFeRecepcaoEvento4Stub.NfeResultMsg.Factory.parse(om.getXMLStreamReaderWithoutCaching());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
