
package br.com.swconsultoria.nfe.schema.retdistdfeint;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.nfe.schema.retdistdfeint package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.nfe.schema.retdistdfeint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetDistDFeInt }
     * 
     */
    public RetDistDFeInt createRetDistDFeInt() {
        return new RetDistDFeInt();
    }

    /**
     * Create an instance of {@link RetDistDFeInt.LoteDistDFeInt }
     * 
     */
    public RetDistDFeInt.LoteDistDFeInt createRetDistDFeIntLoteDistDFeInt() {
        return new RetDistDFeInt.LoteDistDFeInt();
    }

    /**
     * Create an instance of {@link RetDistDFeInt.LoteDistDFeInt.DocZip }
     * 
     */
    public RetDistDFeInt.LoteDistDFeInt.DocZip createRetDistDFeIntLoteDistDFeIntDocZip() {
        return new RetDistDFeInt.LoteDistDFeInt.DocZip();
    }

}
