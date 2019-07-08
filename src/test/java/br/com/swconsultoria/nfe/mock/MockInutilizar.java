//package br.com.swconsultoria.nfe.mock;
//
//import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
//import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
//import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
//import br.com.swconsultoria.nfe.util.XmlNfeUtil;
//import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;
//import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub.NfeResultMsg;
//import org.apache.axiom.om.OMAbstractFactory;
//import org.apache.axiom.om.OMElement;
//import org.apache.axiom.om.util.AXIOMUtil;
//import org.apache.axiom.soap.SOAPEnvelope;
//import org.apache.axiom.soap.SOAPFactory;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.util.Random;
//
//public class MockInutilizar {
//
//	public static String getRetInutilizacao(AmbienteEnum ambiente, EstadosEnum estado, DocumentoEnum modelo, String cnpj, String verAplic, String serie, String nNFIni, String nNFFin, String codStatus, String xMotivo) {
//		String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());
//		StringBuilder b = new StringBuilder();
//		Random random = new Random();
//		String protocolo = Instant.now().toEpochMilli()+ String.format("%02d", random.nextInt(99));
//		if((codStatus==null||codStatus.isEmpty())||(xMotivo==null||xMotivo.isEmpty())) {
//			codStatus = "102";
//			xMotivo = "Inutilização de número homologado";
//		}
//		b.append("    <retInutNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" xmlns:ns0=\"http://www.w3.org/2000/09/xmldsig#\" versao=\"4.00\">");
//		b.append("        <infInut Id=\"ID31196630551700010565308000000014000000014\">");
//		b.append("             <tpAmb>").append(ambiente.getCodigo()).append("</tpAmb>");
//		b.append("             <verAplic>").append(verAplic).append("</verAplic>");
//		b.append("             <cStat>").append(codStatus).append("</cStat>");
//		b.append("             <xMotivo>").append(xMotivo).append("</xMotivo>");
//		b.append("             <cUF>").append(estado.getCodigoUF()).append("</cUF>");
//		b.append("             <ano>").append(LocalDateTime.now().getYear()).append("</ano>");
//		b.append("             <CNPJ>").append(cnpj).append("</CNPJ>");
//		b.append("             <mod>").append(modelo.getModelo()).append("</mod>");
//		b.append("             <serie>").append(serie).append("</serie>");
//		b.append("             <nNFIni>").append(nNFIni).append("</nNFIni>");
//		b.append("             <nNFFin>").append(nNFFin).append("</nNFFin>");
//		b.append("             <dhRecbto>").append(dh).append("</dhRecbto>");
//		b.append("             <nProt>").append(protocolo).append("</nProt>");
//		b.append("         </infInut>");
//		b.append("    </retInutNFe>");
//        return b.toString();
//	}
//
//	public static NfeResultMsg getNfeResultMsg(String xmlMock) throws Exception{
//		SOAPFactory soapFactory = OMAbstractFactory.getSOAP12Factory();
//
//        SOAPEnvelope defaultEnvelope = soapFactory.getDefaultEnvelope();
//
//        OMElement documentElement = AXIOMUtil.stringToOM(xmlMock);
//        defaultEnvelope.getBody().addChild(documentElement);
//		return NFeInutilizacao4Stub.NfeResultMsg.Factory.parse(defaultEnvelope.getBody().getFirstElement().getXMLStreamReaderWithoutCaching());
//	}
//}
