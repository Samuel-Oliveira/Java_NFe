//package br.com.swconsultoria.nfe.mock;
//
//import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
//import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
//import br.com.swconsultoria.nfe.util.XmlNfeUtil;
//import br.com.swconsultoria.nfe.wsdl.NFeAutorizacao.NFeAutorizacao4Stub;
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
//public class MockEnvio {
//
//	public static String getRetEnv(AmbienteEnum ambiente, EstadosEnum estado, String verAplic, String chaveNFe, String codStatus, String xMotivo) {
//		String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());
//		StringBuilder b = new StringBuilder();
//		Random random = new Random();
//		String protocolo = Instant.now().toEpochMilli()+ String.format("%02d", random.nextInt(99));
//		if((codStatus==null||codStatus.isEmpty())||(xMotivo==null||xMotivo.isEmpty())) {
//			codStatus = "100";
//			xMotivo = "Autorizado";
//		}
//		b.append("<nfeResultMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4\">");
//        b.append("    <retEnviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" xmlns:ns0=\"http://www.w3.org/2000/09/xmldsig#\" versao=\"4.00\">");
//        b.append("        <tpAmb>").append(ambiente.getCodigo()).append("</tpAmb>");
//        b.append("        <verAplic>").append(verAplic).append("</verAplic>");
//        b.append("        <cStat>104</cStat>");
//        b.append("		  <xMotivo>Lote processado</xMotivo>");
//        b.append("        <cUF>").append(estado.getCodigoUF()).append("</cUF>");
//        b.append("        <dhRecbto>").append(dh).append("</dhRecbto>");
//        b.append("        <protNFe versao=\"4.00\">");
//        b.append("            <infProt ").append("Id=\"ID131190017042866\">");
//        b.append("                <tpAmb>").append(ambiente.getCodigo()).append("</tpAmb>");
//        b.append("                <verAplic>").append(verAplic).append("</verAplic>");
//        b.append("                <chNFe>").append(chaveNFe).append("</chNFe>");
//        b.append("                <dhRecbto>").append(dh).append("</dhRecbto>");
//        b.append("                <nProt>").append(protocolo).append("</nProt>");
//        b.append("                <digVal>FakeMockado</digVal>");
//        b.append("                <cStat>").append(codStatus).append("</cStat>");
//        b.append("                <xMotivo>").append(xMotivo).append("</xMotivo>");
//        b.append("            </infProt>");
//        b.append("        </protNFe>");
//        b.append("    </retEnviNFe>");
//        b.append("</nfeResultMsg>");
//
//        return b.toString();
//	}
//
//	public static NFeAutorizacao4Stub.NfeResultMsg getNfeResultMsg(String mockXml) throws Exception {
//
//		SOAPFactory soapFactory = OMAbstractFactory.getSOAP12Factory();
//
//        SOAPEnvelope defaultEnvelope = soapFactory.getDefaultEnvelope();
//
//        OMElement documentElement = AXIOMUtil.stringToOM(mockXml);
//        defaultEnvelope.getBody().addChild(documentElement);
//		return NFeAutorizacao4Stub.NfeResultMsg.Factory.parse(defaultEnvelope.getBody().getFirstElement().getXMLStreamReaderWithoutCaching());
//
//	}
//}
