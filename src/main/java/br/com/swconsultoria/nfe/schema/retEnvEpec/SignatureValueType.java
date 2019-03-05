
package br.com.swconsultoria.nfe.schema.retEnvEpec;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java de SignatureValueType complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="SignatureValueType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureValueType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
        "value"
})
public class SignatureValueType {

    @XmlValue
    protected byte[] value;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtm o valor da propriedade value.
     *
     * @return possible object is
     * byte[]
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Define o valor da propriedade value.
     *
     * @param value allowed object is
     *              byte[]
     */
    public void setValue(byte[] value) {
        this.value = value;
    }

    /**
     * Obtm o valor da propriedade id.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

}
