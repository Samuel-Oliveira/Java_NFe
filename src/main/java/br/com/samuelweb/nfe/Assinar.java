package br.com.samuelweb.nfe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.XmlUtil;

/**
 * Classe Responsavel Por Assinar O Xml.
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */
public class Assinar {

	public static final String INFINUT = "infInut";

	public static final String EVENTO = "evento";

	public static final String NFE = "NFe";
	
	private static ConfiguracoesIniciaisNfe configuracoesNfe;

	private static NodeList elements;

	private static PrivateKey privateKey;

	private static KeyInfo keyInfo;

	Assinar assinarXMLsCertfificadoA1;

	
	/**
	 * @param stringXml
	 * @param tipo ('Nfe' para nfe normal , 'infInut' para inutilizacao, 'evento' para eventos)
	 * @return String do Xml Assinado
	 * @throws NfeException
	 */
	public static String assinaNfe(String stringXml ,String tipo) throws NfeException{
		
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();

		stringXml = XmlUtil.removeAcentos(stringXml);
		stringXml = assinaDocNFe(stringXml , tipo);

		return stringXml;
	}

	/**
	 * Assinatura do XML de Envio de Lote da NF-e utilizando Certificado
	 * Digital.
	 * 
	 * @param Conteudo do Xml
	 * @param Nome do Certificado Digital
	 * @return String do XMl Assinado
	 * @throws Exception
	 */
	private static String assinaDocNFe(String xml, String tipo) throws NfeException{

		Document document = null;
		
		try {
			document = documentFactory(xml);
			XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
			ArrayList<Transform> transformList = signatureFactory(signatureFactory);
			loadCertificates(signatureFactory);
	
			for (int i = 0; i < document.getDocumentElement().getElementsByTagName(tipo).getLength(); i++) {
				assinarNFe(tipo, signatureFactory, transformList, privateKey, keyInfo, document, i);
			}
		
		} catch (SAXException | IOException | ParserConfigurationException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | KeyStoreException | UnrecoverableEntryException | NoSuchProviderException | CertificateException | CertificadoException | MarshalException | XMLSignatureException e) {
			throw new NfeException("Erro ao Assinar Nfe"+e.getMessage());
		}

		return outputXML(document);
	
	}

	private static void assinarNFe(String tipo, XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey, KeyInfo ki, Document document, int indexNFe) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException{

		if (tipo.equals(EVENTO)) {
			elements = document.getElementsByTagName("infEvento");
		} else if(tipo.equals(INFINUT)){
			elements = document.getElementsByTagName("infInut");
		}else{
			elements = document.getElementsByTagName("infNFe");
		}
		
		org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexNFe);
		String id = el.getAttribute("Id");

		el.setIdAttribute("Id", true);

		Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

		SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

		XMLSignature signature = fac.newXMLSignature(si, ki);

		DOMSignContext dsc = null ;

		if (tipo.equals(INFINUT)) {
			dsc = new DOMSignContext(privateKey, document.getFirstChild());
		}else{
			dsc = new DOMSignContext(privateKey, document.getDocumentElement().getElementsByTagName(tipo).item(indexNFe));
		}
		
		dsc.setBaseURI("ok");

		signature.sign(dsc);
	}

	private static ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {

		ArrayList<Transform> transformList = new ArrayList<Transform>();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = signatureFactory.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);

		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);
		return transformList;
	}

	private static Document documentFactory(String xml) throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
		return document;
	}

	private static void loadCertificates(XMLSignatureFactory signatureFactory) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, NoSuchProviderException, CertificateException, IOException, CertificadoException {

		Certificado certificado = configuracoesNfe.getCertificado();
		KeyStore.PrivateKeyEntry pkEntry = null;
		KeyStore keyStore = CertificadoService.getKeyStore(certificado);

		pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(certificado.getNome(), new KeyStore.PasswordProtection(certificado.getSenha().toCharArray()));
		privateKey = pkEntry.getPrivateKey();
		
		KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
		List<X509Certificate> x509Content = new ArrayList<X509Certificate>();

		x509Content.add(CertificadoService.getCertificate(certificado, keyStore));
		X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
		keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
	}

	private static String outputXML(Document doc) throws NfeException {
		
		String xml = "";

		try{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));
		xml = os.toString();
		xml = xml.replaceAll("\\r\\n", "");
		xml = xml.replaceAll(" standalone=\"no\"", "");
		}catch(TransformerException e){
			throw new NfeException("Erro ao Transformar Documento:"+e.getMessage());
		}
		
		return xml;
	}

}
