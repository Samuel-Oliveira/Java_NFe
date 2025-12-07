
package br.com.swconsultoria.nfe.schema.evento211124;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.swconsultoria.nfe.schema.evento211124 package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.swconsultoria.nfe.schema.evento211124
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DetEvento }
     * 
     */
    public DetEvento createDetEvento() {
        return new DetEvento();
    }

    /**
     * Create an instance of {@link DetEvento.GPerecimento }
     * 
     */
    public DetEvento.GPerecimento createDetEventoGPerecimento() {
        return new DetEvento.GPerecimento();
    }

    /**
     * Create an instance of {@link DetEvento.GPerecimento.GControleEstoque }
     * 
     */
    public DetEvento.GPerecimento.GControleEstoque createDetEventoGPerecimentoGControleEstoque() {
        return new DetEvento.GPerecimento.GControleEstoque();
    }

}
