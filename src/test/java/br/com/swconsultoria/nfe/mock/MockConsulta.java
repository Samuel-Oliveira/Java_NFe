package br.com.swconsultoria.nfe.mock;

import br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub.NfeResultMsg;
import br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub.NfeDadosMsg;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe.InfProt;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetCancNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetCancNFe.InfCanc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.swconsultoria.nfe.schema_4.consSitNFe.TConsSitNFe;

import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class MockConsulta {

	private static TProtNFe getTProtNFe(TConsSitNFe consSitNFe, XMLGregorianCalendar dh, String protocolo, String consCodRet, String consMotRet) {
		TProtNFe prot = new TProtNFe();
		prot.setVersao("4.00");
		
		InfProt inf = new InfProt();
		inf.setId("ID"+protocolo);
		inf.setTpAmb(consSitNFe.getTpAmb());
		inf.setVerAplic("GO4.0");
		inf.setChNFe(consSitNFe.getChNFe());
		inf.setDhRecbto(dh);		
		inf.setNProt(protocolo);
		inf.setDigVal("Mockado".getBytes());
		inf.setCStat(consCodRet);
		inf.setXMotivo(consMotRet);
		
		prot.setInfProt(inf);
		return prot;
	}
	
	private static TRetCancNFe getRetCancNFe(TConsSitNFe consSitNFe, XMLGregorianCalendar dh, String protocolo) {
		TRetCancNFe retCancNFe = new TRetCancNFe();
		InfCanc infCanc = new InfCanc();
		infCanc.setId("ID"+protocolo);
		infCanc.setDhRecbto(dh);
		infCanc.setTpAmb(consSitNFe.getTpAmb());
		infCanc.setCStat("135");
		infCanc.setXMotivo("Evento registrado e vinculado a NF-e");
		//infCanc.setCUF(""); *****>>>>nao sei se vai precisar, talvez pegar daqui config.getEstado().getCodigoUF()
		infCanc.setChNFe(consSitNFe.getChNFe());
		infCanc.setNProt(protocolo);
		retCancNFe.setInfCanc(infCanc);
		return retCancNFe;
	}
	
	public static NfeResultMsg nfeConsultaNF(NfeDadosMsg dadosMsg, String consCodRet, String consMotRet) {
		try {
			TConsSitNFe consSitNFe = XmlNfeUtil.xmlToObject(dadosMsg.getExtraElement().toString(), TConsSitNFe.class);
			
			Random random = new Random();
			String protocolo = Instant.now().toEpochMilli() + String.format("%02d", new Object[] { Integer.valueOf(random.nextInt(99)) });
			XMLGregorianCalendar dh = DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDateTime.now().toString());

			TRetConsSitNFe retConsSitNFe = new TRetConsSitNFe();
			retConsSitNFe.setChNFe(consSitNFe.getChNFe());
			retConsSitNFe.setCStat(consCodRet);
			retConsSitNFe.setXMotivo(consMotRet);
			
//			retConsSitNFe.setCUF(value); *****>>>>nao sei se vai precisar, talvez pegar daqui config.getEstado().getCodigoUF()
			retConsSitNFe.setDhRecbto(XmlNfeUtil.dataNfe(LocalDateTime.now()));
//			
			retConsSitNFe.setTpAmb(consSitNFe.getTpAmb());
			retConsSitNFe.setVerAplic("GO4.0");
			retConsSitNFe.setVersao(consSitNFe.getVersao());
			
			
			switch (consCodRet) {
				case "100":
					retConsSitNFe.setProtNFe(getTProtNFe(consSitNFe, dh, protocolo, consCodRet, consMotRet));
					break;
				case "101":
					retConsSitNFe.setProtNFe(getTProtNFe(consSitNFe, dh, protocolo, "100", "Autorizado o uso da NF-e"));//cancelado traz protNfe com codigo 100
					retConsSitNFe.setRetCancNFe(getRetCancNFe(consSitNFe, dh, protocolo));
					//retConsSitNFe.setProcEventoNFe(getProcEventoNFe());//isso nao esta mapeado no objecto mas minas esta retornando na consulta. ta aqui apenas para doc
					break;
				default:
					//qualquer coisa que não seja 100 ou 101 estou tratando como "rejeição" colocando o valor de resultado conforme solicitado, 
					//porem sem "RetCancNFe" e "ProtNFe" caso tenha outros que precisem destes valores tratar acima					
					break;
			}

			String retornoStr = XmlNfeUtil.objectToXml(retConsSitNFe).replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

			OMElement om = AXIOMUtil.stringToOM("<nfeResultMsg>" + retornoStr + "</nfeResultMsg>");
			return NfeResultMsg.Factory.parse(om.getXMLStreamReaderWithoutCaching());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * exemplo de retorno que vem junto com a consulta de uma nota cancelada porem,  isso nao esta mapeado no objeto, entao não sei se e so a sefaz de minas que esta retornando.
	 * colocando aqui para documentacao de uma futura necessidade de inclusao
	 */
	private static void getProcEventoNFe() {
//		<procEventoNFe>
//		<evento versao="1.00">
//			<infEvento Id="ID1101113119100153459300021065180000000077194984403801">
//				<cOrgao>31</cOrgao>
//				<tpAmb>2</tpAmb>
//				<CNPJ>01534593000210</CNPJ>
//				<chNFe>31191001534593000210651800000000771949844038</chNFe>
//				<dhEvento>2019-10-11T09:50:00-03:00</dhEvento>
//				<tpEvento>110111</tpEvento>
//				<nSeqEvento>1</nSeqEvento>
//				<verEvento>1.00</verEvento>
//				<detEvento versao="1.00">
//					<descEvento>Cancelamento</descEvento>
//					<nProt>131190019427886</nProt>
//					<xJust>teste sssssssssssssssssssssss</xJust>
//				</detEvento>
//			</infEvento>
//			<ns0:Signature>
//				<ns0:SignedInfo>
//					<ns0:CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
//					<ns0:SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
//					<ns0:Reference URI="#ID1101113119100153459300021065180000000077194984403801">
//						<ns0:Transforms>
//							<ns0:Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
//							<ns0:Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
//						</ns0:Transforms>
//						<ns0:DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
//						<ns0:DigestValue>AYQVC8BrievU/Hoz7/304Dpf+e4=</ns0:DigestValue>
//					</ns0:Reference>
//				</ns0:SignedInfo>
//				<ns0:SignatureValue>pGjJ+KZIIEcRlmEVft7RF7u69fSCMaCsLnf4D34B/qXV0PNPTCltC8Vn9mg/GSSOtk2bURYUnnb9kuLIFBhBGaGKIsSKTlXLrpBYR2BUeaBajrUdIkQD5XzpeEb2pQYkMH3fRF686uXPBLrkbo8UmljsbmPCuRpWp4v+jIG5Jc2VFdGASnSSNtwlOQxqqiTZTEN5zhUfmBqrp3AJt8nefu9uhYOhL71+HprvAgnZ09UM61kLphkkhAfZiRoT85nLo7TLA5bIAmaWz6PTAykPIR0nGjbvNgR8C3C5oPCe8vfaC8zUt8mVTcmFe2ldkApxEzHOHV/6FNgGiAlKeHoong==</ns0:SignatureValue>
//				<ns0:KeyInfo>
//					<ns0:X509Data>
//						<ns0:X509Certificate>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</ns0:X509Certificate>
//					</ns0:X509Data>
//				</ns0:KeyInfo>
//			</ns0:Signature>
//		</evento>
//		<retEvento versao="1.00">
//			<infEvento Id="ID131190019427888">
//				<tpAmb>2</tpAmb>
//				<verAplic>W-1.4.32</verAplic>
//				<cOrgao>31</cOrgao>
//				<cStat>135</cStat>
//				<xMotivo>Evento registrado e vinculado a NF-e</xMotivo>
//				<chNFe>711910015345990007106510800000000171949844038</chNFe>
//				<tpEvento>110111</tpEvento>
//				<xEvento>CANCELAMENTO</xEvento>
//				<nSeqEvento>1</nSeqEvento>
//				<dhRegEvento>2019-10-11T09:49:11-03:00</dhRegEvento>
//				<nProt>131190019427888</nProt>
//			</infEvento>
//		</retEvento>
//	</procEventoNFe>
	}
}
