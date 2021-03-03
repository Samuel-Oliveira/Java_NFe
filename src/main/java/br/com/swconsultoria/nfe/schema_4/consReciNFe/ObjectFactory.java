
package br.com.swconsultoria.nfe.schema_4.consReciNFe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.swconsultoria.nfe.schema_4.consReciNFe package. 
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

    private final static QName _ConsReciNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "consReciNFe");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _TNFeInfNFeDetImpostoICMS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ICMS");
    private final static QName _TNFeInfNFeDetImpostoPISST_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "PISST");
    private final static QName _TNFeInfNFeDetImpostoCOFINSST_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "COFINSST");
    private final static QName _TNFeInfNFeDetImpostoIPI_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "IPI");
    private final static QName _TNFeInfNFeDetImpostoICMSUFDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ICMSUFDest");
    private final static QName _TNFeInfNFeDetImpostoVTotTrib_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "vTotTrib");
    private final static QName _TNFeInfNFeDetImpostoII_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "II");
    private final static QName _TNFeInfNFeDetImpostoISSQN_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ISSQN");
    private final static QName _TNFeInfNFeDetImpostoCOFINS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "COFINS");
    private final static QName _TNFeInfNFeDetImpostoPIS_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "PIS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.swconsultoria.nfe.schema_4.consReciNFe
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
     * Create an instance of {@link TNFe }
     * 
     */
    public TNFe createTNFe() {
        return new TNFe();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe }
     * 
     */
    public TNFe.InfNFe createTNFeInfNFe() {
        return new TNFe.InfNFe();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cana }
     * 
     */
    public TNFe.InfNFe.Cana createTNFeInfNFeCana() {
        return new TNFe.InfNFe.Cana();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic }
     * 
     */
    public TNFe.InfNFe.InfAdic createTNFeInfNFeInfAdic() {
        return new TNFe.InfNFe.InfAdic();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Pag }
     * 
     */
    public TNFe.InfNFe.Pag createTNFeInfNFePag() {
        return new TNFe.InfNFe.Pag();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Pag.DetPag }
     * 
     */
    public TNFe.InfNFe.Pag.DetPag createTNFeInfNFePagDetPag() {
        return new TNFe.InfNFe.Pag.DetPag();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cobr }
     * 
     */
    public TNFe.InfNFe.Cobr createTNFeInfNFeCobr() {
        return new TNFe.InfNFe.Cobr();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp }
     * 
     */
    public TNFe.InfNFe.Transp createTNFeInfNFeTransp() {
        return new TNFe.InfNFe.Transp();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.Vol }
     * 
     */
    public TNFe.InfNFe.Transp.Vol createTNFeInfNFeTranspVol() {
        return new TNFe.InfNFe.Transp.Vol();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total }
     * 
     */
    public TNFe.InfNFe.Total createTNFeInfNFeTotal() {
        return new TNFe.InfNFe.Total();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det }
     * 
     */
    public TNFe.InfNFe.Det createTNFeInfNFeDet() {
        return new TNFe.InfNFe.Det();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ImpostoDevol }
     * 
     */
    public TNFe.InfNFe.Det.ImpostoDevol createTNFeInfNFeDetImpostoDevol() {
        return new TNFe.InfNFe.Det.ImpostoDevol();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto }
     * 
     */
    public TNFe.InfNFe.Det.Imposto createTNFeInfNFeDetImposto() {
        return new TNFe.InfNFe.Det.Imposto();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.COFINS createTNFeInfNFeDetImpostoCOFINS() {
        return new TNFe.InfNFe.Det.Imposto.COFINS();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.PIS createTNFeInfNFeDetImpostoPIS() {
        return new TNFe.InfNFe.Det.Imposto.PIS();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS createTNFeInfNFeDetImpostoICMS() {
        return new TNFe.InfNFe.Det.Imposto.ICMS();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod }
     * 
     */
    public TNFe.InfNFe.Det.Prod createTNFeInfNFeDetProd() {
        return new TNFe.InfNFe.Det.Prod();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb }
     * 
     */
    public TNFe.InfNFe.Det.Prod.Comb createTNFeInfNFeDetProdComb() {
        return new TNFe.InfNFe.Det.Prod.Comb();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DetExport }
     * 
     */
    public TNFe.InfNFe.Det.Prod.DetExport createTNFeInfNFeDetProdDetExport() {
        return new TNFe.InfNFe.Det.Prod.DetExport();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DI }
     * 
     */
    public TNFe.InfNFe.Det.Prod.DI createTNFeInfNFeDetProdDI() {
        return new TNFe.InfNFe.Det.Prod.DI();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide }
     * 
     */
    public TNFe.InfNFe.Ide createTNFeInfNFeIde() {
        return new TNFe.InfNFe.Ide();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref }
     * 
     */
    public TNFe.InfNFe.Ide.NFref createTNFeInfNFeIdeNFref() {
        return new TNFe.InfNFe.Ide.NFref();
    }

    /**
     * Create an instance of {@link TProtNFe }
     * 
     */
    public TProtNFe createTProtNFe() {
        return new TProtNFe();
    }

    /**
     * Create an instance of {@link TRetEnviNFe }
     * 
     */
    public TRetEnviNFe createTRetEnviNFe() {
        return new TRetEnviNFe();
    }

    /**
     * Create an instance of {@link TIpi }
     * 
     */
    public TIpi createTIpi() {
        return new TIpi();
    }

    /**
     * Create an instance of {@link TConsReciNFe }
     * 
     */
    public TConsReciNFe createTConsReciNFe() {
        return new TConsReciNFe();
    }

    /**
     * Create an instance of {@link TVeiculo }
     * 
     */
    public TVeiculo createTVeiculo() {
        return new TVeiculo();
    }

    /**
     * Create an instance of {@link TEnderEmi }
     * 
     */
    public TEnderEmi createTEnderEmi() {
        return new TEnderEmi();
    }

    /**
     * Create an instance of {@link TEndereco }
     * 
     */
    public TEndereco createTEndereco() {
        return new TEndereco();
    }

    /**
     * Create an instance of {@link TEnviNFe }
     * 
     */
    public TEnviNFe createTEnviNFe() {
        return new TEnviNFe();
    }

    /**
     * Create an instance of {@link TInfRespTec }
     * 
     */
    public TInfRespTec createTInfRespTec() {
        return new TInfRespTec();
    }

    /**
     * Create an instance of {@link TLocal }
     * 
     */
    public TLocal createTLocal() {
        return new TLocal();
    }

    /**
     * Create an instance of {@link TNfeProc }
     * 
     */
    public TNfeProc createTNfeProc() {
        return new TNfeProc();
    }

    /**
     * Create an instance of {@link TRetConsReciNFe }
     * 
     */
    public TRetConsReciNFe createTRetConsReciNFe() {
        return new TRetConsReciNFe();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
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
     * Create an instance of {@link TNFe.InfNFeSupl }
     * 
     */
    public TNFe.InfNFeSupl createTNFeInfNFeSupl() {
        return new TNFe.InfNFeSupl();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Emit }
     * 
     */
    public TNFe.InfNFe.Emit createTNFeInfNFeEmit() {
        return new TNFe.InfNFe.Emit();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Avulsa }
     * 
     */
    public TNFe.InfNFe.Avulsa createTNFeInfNFeAvulsa() {
        return new TNFe.InfNFe.Avulsa();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Dest }
     * 
     */
    public TNFe.InfNFe.Dest createTNFeInfNFeDest() {
        return new TNFe.InfNFe.Dest();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.AutXML }
     * 
     */
    public TNFe.InfNFe.AutXML createTNFeInfNFeAutXML() {
        return new TNFe.InfNFe.AutXML();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfIntermed }
     * 
     */
    public TNFe.InfNFe.InfIntermed createTNFeInfNFeInfIntermed() {
        return new TNFe.InfNFe.InfIntermed();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Exporta }
     * 
     */
    public TNFe.InfNFe.Exporta createTNFeInfNFeExporta() {
        return new TNFe.InfNFe.Exporta();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Compra }
     * 
     */
    public TNFe.InfNFe.Compra createTNFeInfNFeCompra() {
        return new TNFe.InfNFe.Compra();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cana.ForDia }
     * 
     */
    public TNFe.InfNFe.Cana.ForDia createTNFeInfNFeCanaForDia() {
        return new TNFe.InfNFe.Cana.ForDia();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cana.Deduc }
     * 
     */
    public TNFe.InfNFe.Cana.Deduc createTNFeInfNFeCanaDeduc() {
        return new TNFe.InfNFe.Cana.Deduc();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic.ObsCont }
     * 
     */
    public TNFe.InfNFe.InfAdic.ObsCont createTNFeInfNFeInfAdicObsCont() {
        return new TNFe.InfNFe.InfAdic.ObsCont();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic.ObsFisco }
     * 
     */
    public TNFe.InfNFe.InfAdic.ObsFisco createTNFeInfNFeInfAdicObsFisco() {
        return new TNFe.InfNFe.InfAdic.ObsFisco();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.InfAdic.ProcRef }
     * 
     */
    public TNFe.InfNFe.InfAdic.ProcRef createTNFeInfNFeInfAdicProcRef() {
        return new TNFe.InfNFe.InfAdic.ProcRef();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Pag.DetPag.Card }
     * 
     */
    public TNFe.InfNFe.Pag.DetPag.Card createTNFeInfNFePagDetPagCard() {
        return new TNFe.InfNFe.Pag.DetPag.Card();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cobr.Fat }
     * 
     */
    public TNFe.InfNFe.Cobr.Fat createTNFeInfNFeCobrFat() {
        return new TNFe.InfNFe.Cobr.Fat();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Cobr.Dup }
     * 
     */
    public TNFe.InfNFe.Cobr.Dup createTNFeInfNFeCobrDup() {
        return new TNFe.InfNFe.Cobr.Dup();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.Transporta }
     * 
     */
    public TNFe.InfNFe.Transp.Transporta createTNFeInfNFeTranspTransporta() {
        return new TNFe.InfNFe.Transp.Transporta();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.RetTransp }
     * 
     */
    public TNFe.InfNFe.Transp.RetTransp createTNFeInfNFeTranspRetTransp() {
        return new TNFe.InfNFe.Transp.RetTransp();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Transp.Vol.Lacres }
     * 
     */
    public TNFe.InfNFe.Transp.Vol.Lacres createTNFeInfNFeTranspVolLacres() {
        return new TNFe.InfNFe.Transp.Vol.Lacres();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total.ICMSTot }
     * 
     */
    public TNFe.InfNFe.Total.ICMSTot createTNFeInfNFeTotalICMSTot() {
        return new TNFe.InfNFe.Total.ICMSTot();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total.ISSQNtot }
     * 
     */
    public TNFe.InfNFe.Total.ISSQNtot createTNFeInfNFeTotalISSQNtot() {
        return new TNFe.InfNFe.Total.ISSQNtot();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Total.RetTrib }
     * 
     */
    public TNFe.InfNFe.Total.RetTrib createTNFeInfNFeTotalRetTrib() {
        return new TNFe.InfNFe.Total.RetTrib();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.ImpostoDevol.IPI }
     * 
     */
    public TNFe.InfNFe.Det.ImpostoDevol.IPI createTNFeInfNFeDetImpostoDevolIPI() {
        return new TNFe.InfNFe.Det.ImpostoDevol.IPI();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.II }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.II createTNFeInfNFeDetImpostoII() {
        return new TNFe.InfNFe.Det.Imposto.II();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ISSQN }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ISSQN createTNFeInfNFeDetImpostoISSQN() {
        return new TNFe.InfNFe.Det.Imposto.ISSQN();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PISST }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.PISST createTNFeInfNFeDetImpostoPISST() {
        return new TNFe.InfNFe.Det.Imposto.PISST();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINSST }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.COFINSST createTNFeInfNFeDetImpostoCOFINSST() {
        return new TNFe.InfNFe.Det.Imposto.COFINSST();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMSUFDest }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMSUFDest createTNFeInfNFeDetImpostoICMSUFDest() {
        return new TNFe.InfNFe.Det.Imposto.ICMSUFDest();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq createTNFeInfNFeDetImpostoCOFINSCOFINSAliq() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde createTNFeInfNFeDetImpostoCOFINSCOFINSQtde() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT createTNFeInfNFeDetImpostoCOFINSCOFINSNT() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr createTNFeInfNFeDetImpostoCOFINSCOFINSOutr() {
        return new TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISAliq }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISAliq createTNFeInfNFeDetImpostoPISPISAliq() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISAliq();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISQtde }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISQtde createTNFeInfNFeDetImpostoPISPISQtde() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISQtde();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISNT }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISNT createTNFeInfNFeDetImpostoPISPISNT() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISNT();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.PIS.PISOutr }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.PIS.PISOutr createTNFeInfNFeDetImpostoPISPISOutr() {
        return new TNFe.InfNFe.Det.Imposto.PIS.PISOutr();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 createTNFeInfNFeDetImpostoICMSICMS00() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 createTNFeInfNFeDetImpostoICMSICMS10() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 createTNFeInfNFeDetImpostoICMSICMS20() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS20();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 createTNFeInfNFeDetImpostoICMSICMS30() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS30();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 createTNFeInfNFeDetImpostoICMSICMS40() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 createTNFeInfNFeDetImpostoICMSICMS51() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS51();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 createTNFeInfNFeDetImpostoICMSICMS60() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 createTNFeInfNFeDetImpostoICMSICMS70() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 createTNFeInfNFeDetImpostoICMSICMS90() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart createTNFeInfNFeDetImpostoICMSICMSPart() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSST }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSST createTNFeInfNFeDetImpostoICMSICMSST() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSST();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 createTNFeInfNFeDetImpostoICMSICMSSN101() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 createTNFeInfNFeDetImpostoICMSICMSSN102() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 createTNFeInfNFeDetImpostoICMSICMSSN201() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 createTNFeInfNFeDetImpostoICMSICMSSN202() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 createTNFeInfNFeDetImpostoICMSICMSSN500() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 }
     * 
     */
    public TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 createTNFeInfNFeDetImpostoICMSICMSSN900() {
        return new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Rastro }
     * 
     */
    public TNFe.InfNFe.Det.Prod.Rastro createTNFeInfNFeDetProdRastro() {
        return new TNFe.InfNFe.Det.Prod.Rastro();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.VeicProd }
     * 
     */
    public TNFe.InfNFe.Det.Prod.VeicProd createTNFeInfNFeDetProdVeicProd() {
        return new TNFe.InfNFe.Det.Prod.VeicProd();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Med }
     * 
     */
    public TNFe.InfNFe.Det.Prod.Med createTNFeInfNFeDetProdMed() {
        return new TNFe.InfNFe.Det.Prod.Med();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Arma }
     * 
     */
    public TNFe.InfNFe.Det.Prod.Arma createTNFeInfNFeDetProdArma() {
        return new TNFe.InfNFe.Det.Prod.Arma();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb.CIDE }
     * 
     */
    public TNFe.InfNFe.Det.Prod.Comb.CIDE createTNFeInfNFeDetProdCombCIDE() {
        return new TNFe.InfNFe.Det.Prod.Comb.CIDE();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.Comb.Encerrante }
     * 
     */
    public TNFe.InfNFe.Det.Prod.Comb.Encerrante createTNFeInfNFeDetProdCombEncerrante() {
        return new TNFe.InfNFe.Det.Prod.Comb.Encerrante();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DetExport.ExportInd }
     * 
     */
    public TNFe.InfNFe.Det.Prod.DetExport.ExportInd createTNFeInfNFeDetProdDetExportExportInd() {
        return new TNFe.InfNFe.Det.Prod.DetExport.ExportInd();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Det.Prod.DI.Adi }
     * 
     */
    public TNFe.InfNFe.Det.Prod.DI.Adi createTNFeInfNFeDetProdDIAdi() {
        return new TNFe.InfNFe.Det.Prod.DI.Adi();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref.RefNF }
     * 
     */
    public TNFe.InfNFe.Ide.NFref.RefNF createTNFeInfNFeIdeNFrefRefNF() {
        return new TNFe.InfNFe.Ide.NFref.RefNF();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref.RefNFP }
     * 
     */
    public TNFe.InfNFe.Ide.NFref.RefNFP createTNFeInfNFeIdeNFrefRefNFP() {
        return new TNFe.InfNFe.Ide.NFref.RefNFP();
    }

    /**
     * Create an instance of {@link TNFe.InfNFe.Ide.NFref.RefECF }
     * 
     */
    public TNFe.InfNFe.Ide.NFref.RefECF createTNFeInfNFeIdeNFrefRefECF() {
        return new TNFe.InfNFe.Ide.NFref.RefECF();
    }

    /**
     * Create an instance of {@link TProtNFe.InfProt }
     * 
     */
    public TProtNFe.InfProt createTProtNFeInfProt() {
        return new TProtNFe.InfProt();
    }

    /**
     * Create an instance of {@link TRetEnviNFe.InfRec }
     * 
     */
    public TRetEnviNFe.InfRec createTRetEnviNFeInfRec() {
        return new TRetEnviNFe.InfRec();
    }

    /**
     * Create an instance of {@link TIpi.IPITrib }
     * 
     */
    public TIpi.IPITrib createTIpiIPITrib() {
        return new TIpi.IPITrib();
    }

    /**
     * Create an instance of {@link TIpi.IPINT }
     * 
     */
    public TIpi.IPINT createTIpiIPINT() {
        return new TIpi.IPINT();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TConsReciNFe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "consReciNFe")
    public JAXBElement<TConsReciNFe> createConsReciNFe(TConsReciNFe value) {
        return new JAXBElement<TConsReciNFe>(_ConsReciNFe_QNAME, TConsReciNFe.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ICMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ICMS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS> createTNFeInfNFeDetImpostoICMS(TNFe.InfNFe.Det.Imposto.ICMS value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.ICMS>(_TNFeInfNFeDetImpostoICMS_QNAME, TNFe.InfNFe.Det.Imposto.ICMS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.PISST }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "PISST", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.PISST> createTNFeInfNFeDetImpostoPISST(TNFe.InfNFe.Det.Imposto.PISST value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.PISST>(_TNFeInfNFeDetImpostoPISST_QNAME, TNFe.InfNFe.Det.Imposto.PISST.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.COFINSST }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "COFINSST", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.COFINSST> createTNFeInfNFeDetImpostoCOFINSST(TNFe.InfNFe.Det.Imposto.COFINSST value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.COFINSST>(_TNFeInfNFeDetImpostoCOFINSST_QNAME, TNFe.InfNFe.Det.Imposto.COFINSST.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIpi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "IPI", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TIpi> createTNFeInfNFeDetImpostoIPI(TIpi value) {
        return new JAXBElement<TIpi>(_TNFeInfNFeDetImpostoIPI_QNAME, TIpi.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ICMSUFDest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ICMSUFDest", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.ICMSUFDest> createTNFeInfNFeDetImpostoICMSUFDest(TNFe.InfNFe.Det.Imposto.ICMSUFDest value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.ICMSUFDest>(_TNFeInfNFeDetImpostoICMSUFDest_QNAME, TNFe.InfNFe.Det.Imposto.ICMSUFDest.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "vTotTrib", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<String> createTNFeInfNFeDetImpostoVTotTrib(String value) {
        return new JAXBElement<String>(_TNFeInfNFeDetImpostoVTotTrib_QNAME, String.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.II }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "II", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.II> createTNFeInfNFeDetImpostoII(TNFe.InfNFe.Det.Imposto.II value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.II>(_TNFeInfNFeDetImpostoII_QNAME, TNFe.InfNFe.Det.Imposto.II.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.ISSQN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ISSQN", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.ISSQN> createTNFeInfNFeDetImpostoISSQN(TNFe.InfNFe.Det.Imposto.ISSQN value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.ISSQN>(_TNFeInfNFeDetImpostoISSQN_QNAME, TNFe.InfNFe.Det.Imposto.ISSQN.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.COFINS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "COFINS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS> createTNFeInfNFeDetImpostoCOFINS(TNFe.InfNFe.Det.Imposto.COFINS value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.COFINS>(_TNFeInfNFeDetImpostoCOFINS_QNAME, TNFe.InfNFe.Det.Imposto.COFINS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNFe.InfNFe.Det.Imposto.PIS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "PIS", scope = TNFe.InfNFe.Det.Imposto.class)
    public JAXBElement<TNFe.InfNFe.Det.Imposto.PIS> createTNFeInfNFeDetImpostoPIS(TNFe.InfNFe.Det.Imposto.PIS value) {
        return new JAXBElement<TNFe.InfNFe.Det.Imposto.PIS>(_TNFeInfNFeDetImpostoPIS_QNAME, TNFe.InfNFe.Det.Imposto.PIS.class, TNFe.InfNFe.Det.Imposto.class, value);
    }

}
