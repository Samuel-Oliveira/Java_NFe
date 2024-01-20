
package br.com.swconsultoria.nfe.schema.eventoSuframaVistoria;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.swconsultoria.nfe.schema.eventoSuframaVistoria package. 
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

    private final static QName _DescEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "descEvento");
    private final static QName _COrgaoAutor_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "cOrgaoAutor");
    private final static QName _CPostoUF_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "cPostoUF");
    private final static QName _XPostoUF_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "xPostoUF");
    private final static QName _LatGPS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "latGPS");
    private final static QName _LongGPS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "longGPS");
    private final static QName _CPFOper_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "CPFOper");
    private final static QName _XNomeOper_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "xNomeOper");
    private final static QName _IndOffline_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "indOffline");
    private final static QName _DhPas_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "dhPas");
    private final static QName _SentidoVia_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "sentidoVia");
    private final static QName _IndRet_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "indRet");
    private final static QName _UFDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "UFDest");
    private final static QName _XObs_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "xObs");
    private final static QName _ChMDFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "chMDFe");
    private final static QName _ChCTe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "chCTe");
    private final static QName _PlacaVeic_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "placaVeic");
    private final static QName _UFVeic_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "UFVeic");
    private final static QName _PlacaCarreta_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "placaCarreta");
    private final static QName _UFCarreta_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "UFCarreta");
    private final static QName _PlacaCarreta2_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "placaCarreta2");
    private final static QName _UFCarreta2_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "UFCarreta2");
    private final static QName _TpModal_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "tpModal");
    private final static QName _XIdent_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "xIdent");
    private final static QName _NFormSeg_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "nFormSeg");
    private final static QName _TpEmis_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "tpEmis");
    private final static QName _CNPJDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "CNPJDest");
    private final static QName _CPFDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "CPFDest");
    private final static QName _VTotalNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "vTotalNFe");
    private final static QName _IndICMS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "indICMS");
    private final static QName _IndICMSST_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "indICMSST");
    private final static QName _DiaEmi_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "diaEmi");
    private final static QName _Evento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "evento");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.swconsultoria.nfe.schema.eventoSuframaVistoria
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link SignedInfoType }
     * 
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
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
     * Create an instance of {@link ModalRodov }
     * 
     */
    public ModalRodov createModalRodov() {
        return new ModalRodov();
    }

    /**
     * Create an instance of {@link ModalOutro }
     * 
     */
    public ModalOutro createModalOutro() {
        return new ModalOutro();
    }

    /**
     * Create an instance of {@link Ctg }
     * 
     */
    public Ctg createCtg() {
        return new Ctg();
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
     * Create an instance of {@link SignatureValueType }
     * 
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link TransformType }
     * 
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     * 
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link ReferenceType.DigestMethod }
     * 
     */
    public ReferenceType.DigestMethod createReferenceTypeDigestMethod() {
        return new ReferenceType.DigestMethod();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "descEvento")
    public JAXBElement<String> createDescEvento(String value) {
        return new JAXBElement<String>(_DescEvento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "cOrgaoAutor")
    public JAXBElement<String> createCOrgaoAutor(String value) {
        return new JAXBElement<String>(_COrgaoAutor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "cPostoUF")
    public JAXBElement<String> createCPostoUF(String value) {
        return new JAXBElement<String>(_CPostoUF_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "xPostoUF")
    public JAXBElement<String> createXPostoUF(String value) {
        return new JAXBElement<String>(_XPostoUF_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "latGPS")
    public JAXBElement<String> createLatGPS(String value) {
        return new JAXBElement<String>(_LatGPS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "longGPS")
    public JAXBElement<String> createLongGPS(String value) {
        return new JAXBElement<String>(_LongGPS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "CPFOper")
    public JAXBElement<String> createCPFOper(String value) {
        return new JAXBElement<String>(_CPFOper_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "xNomeOper")
    public JAXBElement<String> createXNomeOper(String value) {
        return new JAXBElement<String>(_XNomeOper_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "indOffline")
    public JAXBElement<String> createIndOffline(String value) {
        return new JAXBElement<String>(_IndOffline_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "dhPas")
    public JAXBElement<String> createDhPas(String value) {
        return new JAXBElement<String>(_DhPas_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "sentidoVia")
    public JAXBElement<String> createSentidoVia(String value) {
        return new JAXBElement<String>(_SentidoVia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "indRet")
    public JAXBElement<String> createIndRet(String value) {
        return new JAXBElement<String>(_IndRet_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "UFDest")
    public JAXBElement<TUf> createUFDest(TUf value) {
        return new JAXBElement<TUf>(_UFDest_QNAME, TUf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "xObs")
    public JAXBElement<String> createXObs(String value) {
        return new JAXBElement<String>(_XObs_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "chMDFe")
    public JAXBElement<String> createChMDFe(String value) {
        return new JAXBElement<String>(_ChMDFe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "chCTe")
    public JAXBElement<String> createChCTe(String value) {
        return new JAXBElement<String>(_ChCTe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "placaVeic")
    public JAXBElement<String> createPlacaVeic(String value) {
        return new JAXBElement<String>(_PlacaVeic_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "UFVeic")
    public JAXBElement<TUf> createUFVeic(TUf value) {
        return new JAXBElement<TUf>(_UFVeic_QNAME, TUf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "placaCarreta")
    public JAXBElement<String> createPlacaCarreta(String value) {
        return new JAXBElement<String>(_PlacaCarreta_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "UFCarreta")
    public JAXBElement<TUf> createUFCarreta(TUf value) {
        return new JAXBElement<TUf>(_UFCarreta_QNAME, TUf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "placaCarreta2")
    public JAXBElement<String> createPlacaCarreta2(String value) {
        return new JAXBElement<String>(_PlacaCarreta2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TUf }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "UFCarreta2")
    public JAXBElement<TUf> createUFCarreta2(TUf value) {
        return new JAXBElement<TUf>(_UFCarreta2_QNAME, TUf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "tpModal")
    public JAXBElement<String> createTpModal(String value) {
        return new JAXBElement<String>(_TpModal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "xIdent")
    public JAXBElement<String> createXIdent(String value) {
        return new JAXBElement<String>(_XIdent_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "nFormSeg")
    public JAXBElement<String> createNFormSeg(String value) {
        return new JAXBElement<String>(_NFormSeg_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "tpEmis")
    public JAXBElement<String> createTpEmis(String value) {
        return new JAXBElement<String>(_TpEmis_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "CNPJDest")
    public JAXBElement<String> createCNPJDest(String value) {
        return new JAXBElement<String>(_CNPJDest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "CPFDest")
    public JAXBElement<String> createCPFDest(String value) {
        return new JAXBElement<String>(_CPFDest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "vTotalNFe")
    public JAXBElement<String> createVTotalNFe(String value) {
        return new JAXBElement<String>(_VTotalNFe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "indICMS")
    public JAXBElement<String> createIndICMS(String value) {
        return new JAXBElement<String>(_IndICMS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "indICMSST")
    public JAXBElement<String> createIndICMSST(String value) {
        return new JAXBElement<String>(_IndICMSST_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "diaEmi")
    public JAXBElement<String> createDiaEmi(String value) {
        return new JAXBElement<String>(_DiaEmi_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TEvento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TEvento }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "evento")
    public JAXBElement<TEvento> createEvento(TEvento value) {
        return new JAXBElement<TEvento>(_Evento_QNAME, TEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

}
