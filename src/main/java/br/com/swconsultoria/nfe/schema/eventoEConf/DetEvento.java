
package br.com.swconsultoria.nfe.schema.eventoEConf;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descEvento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="ECONF"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="detPag" maxOccurs="100">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="indPag" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;enumeration value="0"/>
 *                         &lt;enumeration value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tPag">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[0-9]{2}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="xPag" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                         &lt;minLength value="2"/>
 *                         &lt;maxLength value="60"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="vPag" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                   &lt;element name="dPag" type="{http://www.portalfiscal.inf.br/nfe}TData"/>
 *                   &lt;sequence minOccurs="0">
 *                     &lt;element name="CNPJPag" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *                     &lt;element name="UFPag" type="{http://www.portalfiscal.inf.br/nfe}TUfEmi"/>
 *                     &lt;element name="CNPJIF" type="{http://www.portalfiscal.inf.br/nfe}TCnpj" minOccurs="0"/>
 *                     &lt;element name="tBand" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;whiteSpace value="preserve"/>
 *                           &lt;pattern value="[0-9]{2}"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="cAut" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                           &lt;minLength value="1"/>
 *                           &lt;maxLength value="128"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                   &lt;sequence minOccurs="0">
 *                     &lt;element name="CNPJReceb" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *                     &lt;element name="UFReceb" type="{http://www.portalfiscal.inf.br/nfe}TUfEmi"/>
 *                   &lt;/sequence>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="preserve"/>
 *             &lt;enumeration value="1.00"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "descEvento",
    "verAplic",
    "detPag"
})
@XmlRootElement(name = "detEvento", namespace = "http://www.portalfiscal.inf.br/nfe")
public class DetEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected List<DetEvento.DetPag> detPag;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtém o valor da propriedade descEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEvento() {
        return descEvento;
    }

    /**
     * Define o valor da propriedade descEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEvento(String value) {
        this.descEvento = value;
    }

    /**
     * Obtém o valor da propriedade verAplic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerAplic() {
        return verAplic;
    }

    /**
     * Define o valor da propriedade verAplic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerAplic(String value) {
        this.verAplic = value;
    }

    /**
     * Gets the value of the detPag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detPag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetPag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento.DetPag }
     * 
     * 
     */
    public List<DetEvento.DetPag> getDetPag() {
        if (detPag == null) {
            detPag = new ArrayList<DetEvento.DetPag>();
        }
        return this.detPag;
    }

    /**
     * Obtém o valor da propriedade versao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
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
     *       &lt;sequence>
     *         &lt;element name="indPag" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;enumeration value="0"/>
     *               &lt;enumeration value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tPag">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[0-9]{2}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="xPag" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *               &lt;minLength value="2"/>
     *               &lt;maxLength value="60"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="vPag" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *         &lt;element name="dPag" type="{http://www.portalfiscal.inf.br/nfe}TData"/>
     *         &lt;sequence minOccurs="0">
     *           &lt;element name="CNPJPag" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
     *           &lt;element name="UFPag" type="{http://www.portalfiscal.inf.br/nfe}TUfEmi"/>
     *           &lt;element name="CNPJIF" type="{http://www.portalfiscal.inf.br/nfe}TCnpj" minOccurs="0"/>
     *           &lt;element name="tBand" minOccurs="0">
     *             &lt;simpleType>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                 &lt;whiteSpace value="preserve"/>
     *                 &lt;pattern value="[0-9]{2}"/>
     *               &lt;/restriction>
     *             &lt;/simpleType>
     *           &lt;/element>
     *           &lt;element name="cAut" minOccurs="0">
     *             &lt;simpleType>
     *               &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *                 &lt;minLength value="1"/>
     *                 &lt;maxLength value="128"/>
     *               &lt;/restriction>
     *             &lt;/simpleType>
     *           &lt;/element>
     *         &lt;/sequence>
     *         &lt;sequence minOccurs="0">
     *           &lt;element name="CNPJReceb" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
     *           &lt;element name="UFReceb" type="{http://www.portalfiscal.inf.br/nfe}TUfEmi"/>
     *         &lt;/sequence>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "indPag",
        "tPag",
        "xPag",
        "vPag",
        "dPag",
        "cnpjPag",
        "ufPag",
        "cnpjif",
        "tBand",
        "cAut",
        "cnpjReceb",
        "ufReceb"
    })
    public static class DetPag {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String indPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String xPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String dPag;
        @XmlElement(name = "CNPJPag", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpjPag;
        @XmlElement(name = "UFPag", namespace = "http://www.portalfiscal.inf.br/nfe")
        @XmlSchemaType(name = "string")
        protected TUfEmi ufPag;
        @XmlElement(name = "CNPJIF", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpjif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String tBand;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cAut;
        @XmlElement(name = "CNPJReceb", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpjReceb;
        @XmlElement(name = "UFReceb", namespace = "http://www.portalfiscal.inf.br/nfe")
        @XmlSchemaType(name = "string")
        protected TUfEmi ufReceb;

        /**
         * Obtém o valor da propriedade indPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIndPag() {
            return indPag;
        }

        /**
         * Define o valor da propriedade indPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIndPag(String value) {
            this.indPag = value;
        }

        /**
         * Obtém o valor da propriedade tPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTPag() {
            return tPag;
        }

        /**
         * Define o valor da propriedade tPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTPag(String value) {
            this.tPag = value;
        }

        /**
         * Obtém o valor da propriedade xPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXPag() {
            return xPag;
        }

        /**
         * Define o valor da propriedade xPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXPag(String value) {
            this.xPag = value;
        }

        /**
         * Obtém o valor da propriedade vPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVPag() {
            return vPag;
        }

        /**
         * Define o valor da propriedade vPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVPag(String value) {
            this.vPag = value;
        }

        /**
         * Obtém o valor da propriedade dPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDPag() {
            return dPag;
        }

        /**
         * Define o valor da propriedade dPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDPag(String value) {
            this.dPag = value;
        }

        /**
         * Obtém o valor da propriedade cnpjPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCNPJPag() {
            return cnpjPag;
        }

        /**
         * Define o valor da propriedade cnpjPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCNPJPag(String value) {
            this.cnpjPag = value;
        }

        /**
         * Obtém o valor da propriedade ufPag.
         * 
         * @return
         *     possible object is
         *     {@link TUfEmi }
         *     
         */
        public TUfEmi getUFPag() {
            return ufPag;
        }

        /**
         * Define o valor da propriedade ufPag.
         * 
         * @param value
         *     allowed object is
         *     {@link TUfEmi }
         *     
         */
        public void setUFPag(TUfEmi value) {
            this.ufPag = value;
        }

        /**
         * Obtém o valor da propriedade cnpjif.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCNPJIF() {
            return cnpjif;
        }

        /**
         * Define o valor da propriedade cnpjif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCNPJIF(String value) {
            this.cnpjif = value;
        }

        /**
         * Obtém o valor da propriedade tBand.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTBand() {
            return tBand;
        }

        /**
         * Define o valor da propriedade tBand.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTBand(String value) {
            this.tBand = value;
        }

        /**
         * Obtém o valor da propriedade cAut.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCAut() {
            return cAut;
        }

        /**
         * Define o valor da propriedade cAut.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCAut(String value) {
            this.cAut = value;
        }

        /**
         * Obtém o valor da propriedade cnpjReceb.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCNPJReceb() {
            return cnpjReceb;
        }

        /**
         * Define o valor da propriedade cnpjReceb.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCNPJReceb(String value) {
            this.cnpjReceb = value;
        }

        /**
         * Obtém o valor da propriedade ufReceb.
         * 
         * @return
         *     possible object is
         *     {@link TUfEmi }
         *     
         */
        public TUfEmi getUFReceb() {
            return ufReceb;
        }

        /**
         * Define o valor da propriedade ufReceb.
         * 
         * @param value
         *     allowed object is
         *     {@link TUfEmi }
         *     
         */
        public void setUFReceb(TUfEmi value) {
            this.ufReceb = value;
        }

    }

}
