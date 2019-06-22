package br.com.swconsultoria.nfe.mock;

import java.time.LocalDateTime;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;

import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;

public class MockStatus {
	
	public static String getXmlStatus(AmbienteEnum ambiente, EstadosEnum estado, String verAplic, String codStatus, String xMotivo) {
		String dh = XmlNfeUtil.dataNfe(LocalDateTime.now());
		StringBuilder b = new StringBuilder();
		if((codStatus==null||codStatus.isEmpty())||(xMotivo==null||xMotivo.isEmpty())) {
			codStatus = "107";
			xMotivo = "Serviço em Operação";
		}
        b.append("<nfeResultMsg>")
          .append("		<retConsStatServ xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\">")
          .append("				<tpAmb>").append(ambiente.getCodigo()).append("</tpAmb>")
          .append("				<verAplic>").append(verAplic).append("</verAplic>")
          .append("				<cStat>").append(codStatus).append("</cStat>")
          .append("				<xMotivo>").append(xMotivo).append("</xMotivo>")
          .append("				<cUF>").append(estado.getCodigoUF()).append("</cUF>")
          .append("				<dhRecbto>").append(dh).append("</dhRecbto>")
          .append("				<tMed>0</tMed>")
          .append("				<dhRetorno>").append(dh).append("</dhRetorno>")
          .append("		</retConsStatServ>")
          .append("</nfeResultMsg>");
        return b.toString();
        
	}
	
	public static NFeStatusServico4Stub.NfeResultMsg getNfeResultMsg(String mockXml) throws Exception {

		SOAPFactory soapFactory = OMAbstractFactory.getSOAP12Factory();

        SOAPEnvelope defaultEnvelope = soapFactory.getDefaultEnvelope();

        OMElement documentElement = AXIOMUtil.stringToOM(mockXml);
        defaultEnvelope.getBody().addChild(documentElement);
		return NFeStatusServico4Stub.NfeResultMsg.Factory.parse(defaultEnvelope.getBody().getFirstElement().getXMLStreamReaderWithoutCaching());
		
	}
}
