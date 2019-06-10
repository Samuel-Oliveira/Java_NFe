package br.com.swconsultoria.nfe.mock;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;

import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub.NfeResultMsg;

public class MockCancelar {
	
	public static String getRetCancelamento(AmbienteEnum ambiente, EstadosEnum estado, String verAplic, String chaveNFe, String codStatus, String xMotivo) {
		String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());
		StringBuilder b = new StringBuilder();
		Random random = new Random();
		String protocolo = Instant.now().toEpochMilli()+ String.format("%04d", random.nextInt(1000));
		if((codStatus==null||codStatus.isEmpty())||(xMotivo==null||xMotivo.isEmpty())) {
			codStatus = "135";
			xMotivo = "Evento registrado e vinculado a NF-e";
		}
		b.append("    <retEnvEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\" xmlns:ns0=\"http://www.w3.org/2000/09/xmldsig#\" versao=\"1.00\">");
		b.append("       <idLote>1</idLote>");
		b.append("       <tpAmb>").append(ambiente.getCodigo()).append("</tpAmb>");
		b.append("       <verAplic>").append(verAplic).append("</verAplic>");
		b.append("       <cOrgao").append(estado.getCodigoUF()).append("cOrgao>");
		b.append("       <cStat>128</cStat>");
		b.append("       <xMotivo>Lote de Evento Processado</xMotivo>");
		b.append("       <retEvento versao=\"1.00\">");
		b.append("          <infEvento Id=\"ID"+protocolo+"\">");
        b.append("             <tpAmb>").append(ambiente.getCodigo()).append("</tpAmb>");
        b.append("             <verAplic>").append(verAplic).append("</verAplic>");
		b.append("             <cOrgao").append(estado.getCodigoUF()).append("cOrgao>");
        b.append("             <cStat>").append(codStatus).append("</cStat>");
        b.append("             <xMotivo>").append(xMotivo).append("</xMotivo>");		
        b.append("             <chNFe>").append(chaveNFe).append("</chNFe>");
		b.append("             <tpEvento>110111</tpEvento>");
		b.append("             <xEvento>CANCELAMENTO</xEvento>");
		b.append("             <nSeqEvento>1</nSeqEvento>");
		b.append("             <dhRegEvento>").append(dh).append("</dhRegEvento>");
		b.append("             <nProt>").append(protocolo).append("</nProt>");
		b.append("          </infEvento>");
		b.append("       </retEvento>");
		b.append("</retEnvEvento>");
        return b.toString();
	}
	
	
	public static NfeResultMsg getNfeResultMsg(String xmlMock) throws Exception{
		SOAPFactory soapFactory = OMAbstractFactory.getSOAP12Factory();

        SOAPEnvelope defaultEnvelope = soapFactory.getDefaultEnvelope();

        OMElement documentElement = AXIOMUtil.stringToOM(xmlMock);
        defaultEnvelope.getBody().addChild(documentElement);
		return NFeRecepcaoEvento4Stub.NfeResultMsg.Factory.parse(defaultEnvelope.getBody().getFirstElement().getXMLStreamReaderWithoutCaching());
	}

}
