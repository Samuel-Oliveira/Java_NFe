
package br.com.swconsultoria.nfe.schema.distdfeint;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.swconsultoria.nfe.schema.distdfeint package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.swconsultoria.nfe.schema.distdfeint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DistDFeInt }
     * 
     */
    public DistDFeInt createDistDFeInt() {
        return new DistDFeInt();
    }

    /**
     * Create an instance of {@link DistDFeInt.DistNSU }
     * 
     */
    public DistDFeInt.DistNSU createDistDFeIntDistNSU() {
        return new DistDFeInt.DistNSU();
    }

    /**
     * Create an instance of {@link DistDFeInt.ConsNSU }
     * 
     */
    public DistDFeInt.ConsNSU createDistDFeIntConsNSU() {
        return new DistDFeInt.ConsNSU();
    }

    /**
     * Create an instance of {@link DistDFeInt.ConsChNFe }
     * 
     */
    public DistDFeInt.ConsChNFe createDistDFeIntConsChNFe() {
        return new DistDFeInt.ConsChNFe();
    }

}
