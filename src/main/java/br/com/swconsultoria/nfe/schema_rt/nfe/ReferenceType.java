
package br.com.swconsultoria.nfe.schema_rt.nfe;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java de ReferenceType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Transforms" type="{http://www.w3.org/2000/09/xmldsig#}TransformsType"/>
 *         &lt;element name="DigestMethod">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DigestValue" type="{http://www.w3.org/2000/09/xmldsig#}DigestValueType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="URI" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyURI">
 *             &lt;minLength value="2"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
    "transforms",
    "digestMethod",
    "digestValue"
})
public class ReferenceType {

    @XmlElement(name = "Transforms", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected TransformsType transforms;
    @XmlElement(name = "DigestMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected ReferenceType.DigestMethod digestMethod;
    @XmlElement(name = "DigestValue", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected byte[] digestValue;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "URI", required = true)
    protected String uri;
    @XmlAttribute(name = "Type")
    @XmlSchemaType(name = "anyURI")
    protected String type;

    /**
     * Obtém o valor da propriedade transforms.
     * 
     * @return
     *     possible object is
     *     {@link TransformsType }
     *     
     */
    public TransformsType getTransforms() {
        return transforms;
    }

    /**
     * Define o valor da propriedade transforms.
     * 
     * @param value
     *     allowed object is
     *     {@link TransformsType }
     *     
     */
    public void setTransforms(TransformsType value) {
        this.transforms = value;
    }

    /**
     * Obtém o valor da propriedade digestMethod.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType.DigestMethod }
     *     
     */
    public ReferenceType.DigestMethod getDigestMethod() {
        return digestMethod;
    }

    /**
     * Define o valor da propriedade digestMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType.DigestMethod }
     *     
     */
    public void setDigestMethod(ReferenceType.DigestMethod value) {
        this.digestMethod = value;
    }

    /**
     * Obtém o valor da propriedade digestValue.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDigestValue() {
        return digestValue;
    }

    /**
     * Define o valor da propriedade digestValue.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDigestValue(byte[] value) {
        this.digestValue = value;
    }

    /**
     * Obtém o valor da propriedade id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade uri.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * Define o valor da propriedade uri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

    /**
     * Obtém o valor da propriedade type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Define o valor da propriedade type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DigestMethod {

        @XmlAttribute(name = "Algorithm", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String algorithm;

        /**
         * Obtém o valor da propriedade algorithm.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlgorithm() {
            if (algorithm == null) {
                return "http://www.w3.org/2000/09/xmldsig#sha1";
            } else {
                return algorithm;
            }
        }

        /**
         * Define o valor da propriedade algorithm.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlgorithm(String value) {
            this.algorithm = value;
        }

    }

}
