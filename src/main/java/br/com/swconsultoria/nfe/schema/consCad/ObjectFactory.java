
package br.com.swconsultoria.nfe.schema.consCad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.nfe.schema.consCad package. 
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

    private final static QName _ConsCad_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "ConsCad");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.nfe.schema.consCad
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TRetConsCad }
     * 
     */
    public TRetConsCad createTRetConsCad() {
        return new TRetConsCad();
    }

    /**
     * Create an instance of {@link TRetConsCad.InfCons }
     * 
     */
    public TRetConsCad.InfCons createTRetConsCadInfCons() {
        return new TRetConsCad.InfCons();
    }

    /**
     * Create an instance of {@link TConsCad }
     * 
     */
    public TConsCad createTConsCad() {
        return new TConsCad();
    }

    /**
     * Create an instance of {@link TEndereco }
     * 
     */
    public TEndereco createTEndereco() {
        return new TEndereco();
    }

    /**
     * Create an instance of {@link TRetConsCad.InfCons.InfCad }
     * 
     */
    public TRetConsCad.InfCons.InfCad createTRetConsCadInfConsInfCad() {
        return new TRetConsCad.InfCons.InfCad();
    }

    /**
     * Create an instance of {@link TConsCad.InfCons }
     * 
     */
    public TConsCad.InfCons createTConsCadInfCons() {
        return new TConsCad.InfCons();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TConsCad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "ConsCad")
    public JAXBElement<TConsCad> createConsCad(TConsCad value) {
        return new JAXBElement<TConsCad>(_ConsCad_QNAME, TConsCad.class, null, value);
    }

}
