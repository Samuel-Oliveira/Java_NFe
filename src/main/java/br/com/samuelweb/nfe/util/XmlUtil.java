/**
 * 
 */
package br.com.samuelweb.nfe.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.Normalizer;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.samuelweb.nfe.exception.NfeException;
import br.inf.portalfiscal.nfe.schema.conssitnfe.TConsSitNFe;
import br.inf.portalfiscal.nfe.schema.consstatserv.TConsStatServ;
import br.inf.portalfiscal.nfe.schema.distdfeint.DistDFeInt;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envinfe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema.envinfe.TNfeProc;
import br.inf.portalfiscal.nfe.schema.envinfe.TProtNFe;
import br.inf.portalfiscal.nfe.schema.inutnfe.TInutNFe;

/**
 * Classe Responsavel por Metodos referentes ao XML
 * 
 * @author Samuel Oliveira
 *
 */
public class XmlUtil {

	private static final String STATUS = "TConsStatServ";
	private static final String SITUACAO_NFE = "TConsSitNFe";
	private static final String ENVIO_NFE = "TEnviNFe";
	private static final String DIST_DFE = "DistDFeInt";
	private static final String INUTILIZACAO = "TInutNFe";
	private static final String NFEPROC = "TNfeProc";
	private static final String EVENTO = "TEnvEvento";
	private static final String TPROCEVENTO = "TProcEvento";
	
	private static final String TPROCCANCELAR = "br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento";
	private static final String TPROCCCE = "br.inf.portalfiscal.nfe.schema.envcce.TProcEvento";
	
	private static final String TProtNFe = "TProtNFe";
	private static final String TProtEnvi = "br.inf.portalfiscal.nfe.schema.envinfe.TProtNFe";
	private static final String TProtCons = "br.inf.portalfiscal.nfe.schema.retconssitnfe.TProtNFe";
	
	private static final String CANCELAR = "br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento";
	private static final String CCE = "br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento";
	private static final String MANIFESTAR = "br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento";
	

	/**
	 * Transforma o String do XML em Objeto
	 * 
	 * @param String
	 * @param Class<T>
	 * @return <T> T
	 */
	public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(classe);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
	}

	/**
	 * Transforma o Objeto em XML(String)
	 * 
	 * @return String
	 * @throws NfeException
	 */
	public static <T> String objectToXml(Object obj) throws JAXBException, NfeException {

		JAXBContext context = null;
		JAXBElement<?> element = null;

		switch (obj.getClass().getSimpleName()) {

		case STATUS:
			context = JAXBContext.newInstance(TConsStatServ.class);
			element = new br.inf.portalfiscal.nfe.schema.consstatserv.ObjectFactory()
					.createConsStatServ((TConsStatServ) obj);
			break;

		case ENVIO_NFE:
			context = JAXBContext.newInstance(TEnviNFe.class);
			element = new br.inf.portalfiscal.nfe.schema.envinfe.ObjectFactory().createEnviNFe((TEnviNFe) obj);
			break;

		case SITUACAO_NFE:
			context = JAXBContext.newInstance(TConsSitNFe.class);
			element = new br.inf.portalfiscal.nfe.schema.conssitnfe.ObjectFactory().createConsSitNFe((TConsSitNFe) obj);
			break;

		case DIST_DFE:
			context = JAXBContext.newInstance(DistDFeInt.class);
			element = new br.inf.portalfiscal.nfe.schema.distdfeint.ObjectFactory().createDistDFeInt((DistDFeInt) obj);
			break;

		case INUTILIZACAO:
			context = JAXBContext.newInstance(TInutNFe.class);
			element = new br.inf.portalfiscal.nfe.schema.inutnfe.ObjectFactory().createInutNFe((TInutNFe) obj);
			break;
			
		case TPROCEVENTO:
			if(obj.getClass().getName().equals(TPROCCANCELAR)){
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento.class);
				element = new br.inf.portalfiscal.nfe.schema.envEventoCancNFe.ObjectFactory().createTProcEvento((br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento) obj);
			}else if(obj.getClass().getName().equals(TPROCCCE)){
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envcce.TProcEvento.class);
				element = new br.inf.portalfiscal.nfe.schema.envcce.ObjectFactory().createTProcEvento((br.inf.portalfiscal.nfe.schema.envcce.TProcEvento) obj);
			}
			
			break;
			
		case NFEPROC:
			context = JAXBContext.newInstance(TNfeProc.class);
			element = new br.inf.portalfiscal.nfe.schema.envinfe.ObjectFactory().createNfeProc((TNfeProc) obj);
			break;

		case EVENTO:
			if(obj.getClass().getName().equals(CANCELAR)){
				context = JAXBContext.newInstance(TEnvEvento.class);
				element = new br.inf.portalfiscal.nfe.schema.envEventoCancNFe.ObjectFactory().createEnvEvento((TEnvEvento) obj);
			}else if(obj.getClass().getName().equals(CCE)){
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento.class);
				element = new br.inf.portalfiscal.nfe.schema.envcce.ObjectFactory().createEnvEvento((br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento) obj);
			} else if (obj.getClass().getName().equals(MANIFESTAR)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento.class);
				element = new br.inf.portalfiscal.nfe.schema.envConfRecebto.ObjectFactory()
						.createEnvEvento((br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento) obj);
			}
			break;
			
		case TProtNFe:
			if(obj.getClass().getName().equals(TProtEnvi)){
				context = JAXBContext.newInstance(TProtNFe.class);
				element = new br.inf.portalfiscal.nfe.schema.envinfe.ObjectFactory().createTProtNFe((br.inf.portalfiscal.nfe.schema.envinfe.TProtNFe) obj);
			}else if(obj.getClass().getName().equals(TProtCons)){
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.retconssitnfe.TProtNFe.class);
				element = new br.inf.portalfiscal.nfe.schema.retconssitnfe.ObjectFactory().createProtNFe((br.inf.portalfiscal.nfe.schema.retconssitnfe.TProtNFe) obj);
			}
			break;
			
		default:
			throw new NfeException("Objeto não mapeado no XmlUtil:" + obj.getClass().getSimpleName());
		}
		Marshaller marshaller = context.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);

		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(sw.toString());
		
		if((obj.getClass().getSimpleName().equals(TPROCEVENTO))){
			return replacesNfe(xml.toString().replaceAll("procEvento", "procEventoNFe"));
		}else{
			return replacesNfe(xml.toString());
		}

	}

	public static String gZipToXml(byte[] conteudo) throws NfeException, IOException {
		if (conteudo == null || conteudo.length == 0) {
			return "";
		}
		GZIPInputStream gis;
		gis = new GZIPInputStream(new ByteArrayInputStream(conteudo));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null) {
			outStr += line;
		}

		return outStr;
	}
	
	public static String criaNfeProc(TEnviNFe enviNfe,Object retorno) throws JAXBException, NfeException {
		
		TNfeProc nfeProc = new TNfeProc();
		nfeProc.setVersao("3.10");
		nfeProc.setNFe(enviNfe.getNFe().get(0));
		String xml = XmlUtil.objectToXml(retorno);
		nfeProc.setProtNFe(XmlUtil.xmlToObject(xml, TProtNFe.class));
		
		String xmlFinal = XmlUtil.objectToXml(nfeProc);
				
		return xmlFinal;
	}
	
	public static String removeAcentos(String str){
		
		str = str.replaceAll("\r", "");
        str = str.replaceAll("\t", "");
        str = str.replaceAll("\n", "");
        str = str.replaceAll(">\\s+<", "><");
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		
	}
	
	public static String replacesNfe(String xml){
		
		xml = xml.replaceAll("ns2:", "");
		xml = xml.replaceAll("ns3:", "");
		xml = xml.replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll(" xmlns=\"\" xmlns:ns3=\"http://www.portalfiscal.inf.br/nfe\"", "");
		
        return xml;
		
	}
	
}
