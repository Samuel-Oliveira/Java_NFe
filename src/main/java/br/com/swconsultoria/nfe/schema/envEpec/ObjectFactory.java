
package br.com.swconsultoria.nfe.schema.envEpec;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the br.inf.portalfiscal.nfe.schema.envEpec package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VerAplic_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "verAplic");
    private final static QName _TpNF_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "tpNF");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _VNF_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "vNF");
    private final static QName _EnvEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "envEvento");
    private final static QName _DhEmi_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "dhEmi");
    private final static QName _DescEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "descEvento");
    private final static QName _COrgaoAutor_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "cOrgaoAutor");
    private final static QName _VST_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "vST");
    private final static QName _VICMS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "vICMS");
    private final static QName _UF_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "UF");
    private final static QName _IE_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "IE");
    private final static QName _TpAutor_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "tpAutor");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.nfe.schema.envEpec
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReferenceType }
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link SignedInfoType }
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
    }

    /**
     * Create an instance of {@link TEvento }
     */
    public TEvento createTEvento() {
        return new TEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento }
     */
    public TEvento.InfEvento createTEventoInfEvento() {
        return new TEvento.InfEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento }
     */
    public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
        return new TEvento.InfEvento.DetEvento();
    }

    /**
     * Create an instance of {@link TRetEvento }
     */
    public TRetEvento createTRetEvento() {
        return new TRetEvento();
    }

    /**
     * Create an instance of {@link TEnvEvento }
     */
    public TEnvEvento createTEnvEvento() {
        return new TEnvEvento();
    }

    /**
     * Create an instance of {@link TRetEnvEvento }
     */
    public TRetEnvEvento createTRetEnvEvento() {
        return new TRetEnvEvento();
    }

    /**
     * Create an instance of {@link TProcEvento }
     */
    public TProcEvento createTProcEvento() {
        return new TProcEvento();
    }

    /**
     * Create an instance of {@link SignatureType }
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link X509DataType }
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link SignatureValueType }
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link TransformsType }
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link TransformType }
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link ReferenceType.DigestMethod }
     */
    public ReferenceType.DigestMethod createReferenceTypeDigestMethod() {
        return new ReferenceType.DigestMethod();
    }

    /**
     * Create an instance of {@link SignedInfoType.CanonicalizationMethod }
     */
    public SignedInfoType.CanonicalizationMethod createSignedInfoTypeCanonicalizationMethod() {
        return new SignedInfoType.CanonicalizationMethod();
    }

    /**
     * Create an instance of {@link SignedInfoType.SignatureMethod }
     */
    public SignedInfoType.SignatureMethod createSignedInfoTypeSignatureMethod() {
        return new SignedInfoType.SignatureMethod();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento.Dest }
     */
    public TEvento.InfEvento.DetEvento.Dest createTEventoInfEventoDetEventoDest() {
        return new TEvento.InfEvento.DetEvento.Dest();
    }

    /**
     * Create an instance of {@link TRetEvento.InfEvento }
     */
    public TRetEvento.InfEvento createTRetEventoInfEvento() {
        return new TRetEvento.InfEvento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "verAplic")
    public JAXBElement<String> createVerAplic(String value) {
        return new JAXBElement<String>(_VerAplic_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "tpNF")
    public JAXBElement<String> createTpNF(String value) {
        return new JAXBElement<String>(_TpNF_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "vNF")
    public JAXBElement<String> createVNF(String value) {
        return new JAXBElement<String>(_VNF_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TEnvEvento }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "envEvento")
    public JAXBElement<TEnvEvento> createEnvEvento(TEnvEvento value) {
        return new JAXBElement<TEnvEvento>(_EnvEvento_QNAME, TEnvEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "dhEmi")
    public JAXBElement<String> createDhEmi(String value) {
        return new JAXBElement<String>(_DhEmi_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "descEvento")
    public JAXBElement<String> createDescEvento(String value) {
        return new JAXBElement<String>(_DescEvento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "cOrgaoAutor")
    public JAXBElement<String> createCOrgaoAutor(String value) {
        return new JAXBElement<String>(_COrgaoAutor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "vST")
    public JAXBElement<String> createVST(String value) {
        return new JAXBElement<String>(_VST_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "vICMS")
    public JAXBElement<String> createVICMS(String value) {
        return new JAXBElement<String>(_VICMS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "UF")
    public JAXBElement<TUf> createUF(TUf value) {
        return new JAXBElement<TUf>(_UF_QNAME, TUf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "IE")
    public JAXBElement<String> createIE(String value) {
        return new JAXBElement<String>(_IE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "tpAutor")
    public JAXBElement<String> createTpAutor(String value) {
        return new JAXBElement<String>(_TpAutor_QNAME, String.class, null, value);
    }

}
