package br.com.swconsultoria.nfe.mock;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.swconsultoria.nfe.Mock;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe.InfInut;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.InfProt;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TConsStatServ;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub.NfeDadosMsg;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub.NfeResultMsg;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;

public class MockImp implements Mock {
	private static String	CAN_COD_RET		= "135";
	private static String	CAN_MOT_RET		= "Evento registrado e vinculado a NF-e";
	private static String	ENV_COD_RET		= "100";
	private static String	ENV_MOT_RET		= "Autorizado o uso da NF-e";
	private static String	INUT_COD_RET		= "102";
	private static String	INUT_MOT_RET		= "Inutilização de número homologado";
	private static String	STATUS_COD_RET	= "107";
	private static String	STATUS_MOT_RET	= "Serviço em Operação";
	private static String	CONS_COD_RET		= "100";
	private static String	CONS_MOT_RET		= "Autorizado o uso da NF-e";
	
	public static void setRetEnvio(String codigo, String motivo) {
		ENV_COD_RET = codigo;
		ENV_MOT_RET = motivo;
	}

	public static void setRetCancelamento(String codigo, String motivo) {
		CAN_COD_RET = codigo;
		CAN_MOT_RET = motivo;
	}

	public static String getInutCodRet() {
		return INUT_COD_RET;
	}

	public static void setRetInutilizacao(String codigo, String motivo) {
		INUT_COD_RET = codigo;
		INUT_MOT_RET = motivo;
	}

	public static void setRetStatus(String codigo, String motivo) {
		STATUS_COD_RET = codigo;
		STATUS_MOT_RET = motivo;
	}

	public static void setRetConsulta(String codigo, String motivo) {
		CONS_COD_RET = codigo;
		CONS_MOT_RET = motivo;
	}
	
	public static String getConsultaCodRet() {
		return CONS_COD_RET;
	}
	
	public static String getStatusCodRet() {
		return STATUS_COD_RET;
	}

	public static String getCancelamentoCodRet() {
		return CAN_COD_RET;
	}
	
	public static String getEnvioCodRet() {
		return ENV_COD_RET;
	}
	
	@Override
	public NFeStatusServico4Stub.NfeResultMsg nfeStatusServicoNF(NFeStatusServico4Stub.NfeDadosMsg dadosMsg) {
		try {
			TConsStatServ consStatServ = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TConsStatServ.class);
			String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());

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

	public NFeRecepcaoEvento4Stub.NfeResultMsg nfeRecepcaoEvento(NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg, ServicosEnum tipoEvento) {
		try {
			TEnvEvento envEvento = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TEnvEvento.class);

			String protocolo = Instant.now().toEpochMilli() + String.format("%02d", new Random().nextInt(99));
			String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());

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

	@Override
	public NfeResultMsg nfeInutilizacaoNF(NfeDadosMsg dadosMsg) {
		try {
			String protocolo = Instant.now().toEpochMilli() + String.format("%02d", new Random().nextInt(99));

			TInutNFe inutNFe = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TInutNFe.class);
			String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());

			TRetInutNFe ret = new TRetInutNFe();
			TRetInutNFe.InfInut infInut = new TRetInutNFe.InfInut();
			infInut.setId("ID" + protocolo);
			infInut.setTpAmb(inutNFe.getInfInut().getTpAmb());
			infInut.setVerAplic("GO4.0");
			infInut.setCStat("102");
			infInut.setXMotivo("Inutilização de número homologado");
			infInut.setCUF(inutNFe.getInfInut().getCUF());

			infInut.setAno(inutNFe.getInfInut().getAno());
			infInut.setCNPJ(inutNFe.getInfInut().getCNPJ());
			infInut.setSerie(inutNFe.getInfInut().getSerie());
			infInut.setMod(inutNFe.getInfInut().getMod());
			infInut.setNNFIni(inutNFe.getInfInut().getNNFIni());
			infInut.setNNFFin(inutNFe.getInfInut().getNNFFin());
			if (infInut.getCStat().equals("102")) {
				infInut.setDhRecbto(dh);
				infInut.setNProt(protocolo);
			}

			ret.setInfInut(infInut);

			String retornoStr = XmlNfeUtil.objectToXml(ret).replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

			OMElement om = AXIOMUtil.stringToOM("<nfeResultMsg>" + retornoStr + "</nfeResultMsg>");
			return NFeInutilizacao4Stub.NfeResultMsg.Factory.parse(om.getXMLStreamReaderWithoutCaching());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public br.com.swconsultoria.nfe.wsdl.NFeAutorizacao.NFeAutorizacao4Stub.NfeResultMsg nfeAutorizacaoLote(br.com.swconsultoria.nfe.wsdl.NFeAutorizacao.NFeAutorizacao4Stub.NfeDadosMsg dadosMsg) {
		try {
			TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TEnviNFe.class);

			Random random = new Random();
			String protocolo = Instant.now().toEpochMilli() + String.format("%02d", new Object[] { Integer.valueOf(random.nextInt(99)) });
			String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());

			TRetEnviNFe retEnviNFe = new TRetEnviNFe();
			retEnviNFe.setTpAmb(enviNFe.getNFe().get(0).getInfNFe().getIde().getTpAmb());
			retEnviNFe.setCStat("104");
			retEnviNFe.setXMotivo("Lote processado");
			retEnviNFe.setCUF(enviNFe.getNFe().get(0).getInfNFe().getIde().getCUF());
			retEnviNFe.setDhRecbto(dh);
			retEnviNFe.setVersao(enviNFe.getNFe().get(0).getInfNFe().getVersao());
			retEnviNFe.setVerAplic("GO4.0");
			TProtNFe prot = new TProtNFe();
			prot.setVersao("4.00");
			InfProt inf = new InfProt();
			inf.setTpAmb(enviNFe.getNFe().get(0).getInfNFe().getIde().getTpAmb());
			inf.setVerAplic("GO4.0");
			inf.setChNFe(enviNFe.getNFe().get(0).getInfNFe().getId().substring(3));
			inf.setDhRecbto(dh);
			inf.setNProt(protocolo);
			inf.setDigVal("Mockado".getBytes());
			inf.setCStat(ENV_COD_RET);
			inf.setXMotivo(ENV_MOT_RET);
			prot.setInfProt(inf);
			retEnviNFe.setProtNFe(prot);

			String retornoStr = XmlNfeUtil.objectToXml(retEnviNFe).replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

			OMElement om = AXIOMUtil.stringToOM("<nfeResultMsg>" + retornoStr + "</nfeResultMsg>");
			return br.com.swconsultoria.nfe.wsdl.NFeAutorizacao.NFeAutorizacao4Stub.NfeResultMsg.Factory.parse(om.getXMLStreamReaderWithoutCaching());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub.NfeResultMsg nfeConsultaNF(br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub.NfeDadosMsg dadosMsg) {
		return MockConsulta.nfeConsultaNF(dadosMsg, CONS_COD_RET, CONS_MOT_RET);
	}

}
