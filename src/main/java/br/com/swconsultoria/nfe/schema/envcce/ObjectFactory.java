
package br.com.swconsultoria.nfe.schema.envcce;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.nfe.schema.envcce package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _EnvEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "envEvento");
    private final static QName _ProcEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "procEvento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.nfe.schema.envcce
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SignedInfoType }
     * 
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link TretEvento }
     * 
     */
    public TretEvento createTretEvento() {
        return new TretEvento();
    }

    /**
     * Create an instance of {@link TEvento }
     * 
     */
    public TEvento createTEvento() {
        return new TEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento }
     * 
     */
    public TEvento.InfEvento createTEventoInfEvento() {
        return new TEvento.InfEvento();
    }

    /**
     * Create an instance of {@link TEnvEvento }
     * 
     */
    public TEnvEvento createTEnvEvento() {
        return new TEnvEvento();
    }

    /**
     * Create an instance of {@link TRetEnvEvento }
     * 
     */
    public TRetEnvEvento createTRetEnvEvento() {
        return new TRetEnvEvento();
    }

    /**
     * Create an instance of {@link TProcEvento }
     * 
     */
    public TProcEvento createTProcEvento() {
        return new TProcEvento();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     * 
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link SignatureValueType }
     * 
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link TransformType }
     * 
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link SignedInfoType.CanonicalizationMethod }
     * 
     */
    public SignedInfoType.CanonicalizationMethod createSignedInfoTypeCanonicalizationMethod() {
        return new SignedInfoType.CanonicalizationMethod();
    }

    /**
     * Create an instance of {@link SignedInfoType.SignatureMethod }
     * 
     */
    public SignedInfoType.SignatureMethod createSignedInfoTypeSignatureMethod() {
        return new SignedInfoType.SignatureMethod();
    }

    /**
     * Create an instance of {@link ReferenceType.DigestMethod }
     * 
     */
    public ReferenceType.DigestMethod createReferenceTypeDigestMethod() {
        return new ReferenceType.DigestMethod();
    }

    /**
     * Create an instance of {@link TretEvento.InfEvento }
     * 
     */
    public TretEvento.InfEvento createTretEventoInfEvento() {
        return new TretEvento.InfEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento }
     * 
     */
    public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
        return new TEvento.InfEvento.DetEvento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TEnvEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "envEvento")
    public JAXBElement<TEnvEvento> createEnvEvento(TEnvEvento value) {
        return new JAXBElement<TEnvEvento>(_EnvEvento_QNAME, TEnvEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TEnvEvento }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "procEvento")
    public JAXBElement<TProcEvento> createTProcEvento(TProcEvento value) {
        return new JAXBElement<TProcEvento>(_ProcEvento_QNAME, TProcEvento.class, null, value);
    }

}
