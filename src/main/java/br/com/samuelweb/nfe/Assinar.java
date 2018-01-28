package br.com.samuelweb.nfe;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Responsavel Por Assinar O Xml.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */
class Assinar {

    static final String INFINUT = "infInut";
    static final String EVENTO = "evento";

    private static ConfiguracoesIniciaisNfe configuracoesNfe;

    private static PrivateKey privateKey;

    private static KeyInfo keyInfo;

    Assinar assinarXMLsCertfificadoA1;

    /**
     * @param stringXml
     * @param tipo      ('NFe' para nfe normal , 'infInut' para inutilizacao, 'evento' para eventos)
     * @return String do Xml Assinado
     * @throws NfeException
     */
    static String assinaNfe(String stringXml, String tipo) throws NfeException {

        configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();

        stringXml = XmlUtil.removeAcentos(stringXml);
        stringXml = assinaDocNFe(stringXml, tipo);

        return stringXml;
    }

    /**
     * Assinatura do XML de Envio de Lote da NF-e utilizando Certificado
     * Digital.
     *
     * @param Conteudo do Xml
     * @param Nome     do Certificado Digital
     * @return String do XMl Assinado
     * @throws Exception
     */
    private static String assinaDocNFe(String xml, String tipo) throws NfeException {

        try {
            Document document = documentFactory(xml);
            XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
            ArrayList<Transform> transformList = signatureFactory(signatureFactory);
            loadCertificates(signatureFactory);

            for (int i = 0; i < document.getDocumentElement().getElementsByTagName(tipo).getLength(); i++) {
                assinarNFe(tipo, signatureFactory, transformList, privateKey, keyInfo, document, i);
            }

            return outputXML(document);
        } catch (SAXException | IOException | ParserConfigurationException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | KeyStoreException | UnrecoverableEntryException | NoSuchProviderException | CertificateException | CertificadoException | MarshalException | XMLSignatureException e) {
            throw new NfeException("Erro ao Assinar Nfe" + e.getMessage());
        }
    }

    private static void assinarNFe(String tipo, XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey, KeyInfo ki, Document document, int indexNFe) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException {

        NodeList elements;
        switch (tipo) {
            case EVENTO:
                elements = document.getElementsByTagName("infEvento");
                break;
            case INFINUT:
                elements = document.getElementsByTagName("infInut");
                break;
            default:
                elements = document.getElementsByTagName("infNFe");
        }

        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexNFe);
        String id = el.getAttribute("Id");

        el.setIdAttribute("Id", true);

        Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        XMLSignature signature = fac.newXMLSignature(si, ki);

        DOMSignContext dsc;

        if (tipo.equals(INFINUT)) {
            dsc = new DOMSignContext(privateKey, document.getFirstChild());
        } else {
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
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
    }

    private static void loadCertificates(XMLSignatureFactory signatureFactory) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, NoSuchProviderException, CertificateException, IOException, CertificadoException {

        Certificado certificado = configuracoesNfe.getCertificado();
        KeyStore keyStore = CertificadoService.getKeyStore(certificado);

        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(certificado.getNome(), new KeyStore.PasswordProtection(certificado.getSenha().toCharArray()));
        privateKey = pkEntry.getPrivateKey();

        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
        List<X509Certificate> x509Content = new ArrayList<X509Certificate>();

        x509Content.add(CertificadoService.getCertificate(certificado, keyStore));
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
        keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
    }

    private static String outputXML(Document doc) throws NfeException {

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
            String xml = os.toString();
            xml = xml.replaceAll("\\r\\n", "");
            xml = xml.replaceAll(" standalone=\"no\"", "");
            return xml;
        } catch (TransformerException e) {
            throw new NfeException("Erro ao Transformar Documento:" + e.getMessage());
        }

    }

}