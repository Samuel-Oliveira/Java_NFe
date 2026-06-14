
package br.com.swconsultoria.nfe.schemas_eventos;

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
 *               &lt;enumeration value="Solicitação de Apropriação de crédito presumido"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *         &lt;element name="tpAutor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="gCredPres" maxOccurs="990">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                   &lt;element name="gIBS" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="cCredPres">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{2}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                             &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="gCBS" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="cCredPres">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{2}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                             &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="nItem" use="required" type="{http://www.portalfiscal.inf.br/nfe}TnItem" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao">
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
    "cOrgaoAutor",
    "tpAutor",
    "verAplic",
    "gCredPres"
})
@XmlRootElement(name = "detEvento")
public class DetEvento211110 {

    @XmlElement(required = true)
    protected String descEvento;
    @XmlElement(required = true)
    protected String cOrgaoAutor;
    @XmlElement(required = true)
    protected String tpAutor;
    @XmlElement(required = true)
    protected String verAplic;
    @XmlElement(required = true)
    protected List<DetEvento211110.GCredPres> gCredPres;
    @XmlAttribute(name = "versao")
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
     * Obtém o valor da propriedade cOrgaoAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOrgaoAutor() {
        return cOrgaoAutor;
    }

    /**
     * Define o valor da propriedade cOrgaoAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOrgaoAutor(String value) {
        this.cOrgaoAutor = value;
    }

    /**
     * Obtém o valor da propriedade tpAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpAutor() {
        return tpAutor;
    }

    /**
     * Define o valor da propriedade tpAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpAutor(String value) {
        this.tpAutor = value;
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
     * Gets the value of the gCredPres property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gCredPres property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGCredPres().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento211110.GCredPres }
     * 
     * 
     */
    public List<DetEvento211110.GCredPres> getGCredPres() {
        if (gCredPres == null) {
            gCredPres = new ArrayList<DetEvento211110.GCredPres>();
        }
        return this.gCredPres;
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
     *         &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *         &lt;element name="gIBS" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="cCredPres">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{2}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gCBS" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="cCredPres">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{2}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="nItem" use="required" type="{http://www.portalfiscal.inf.br/nfe}TnItem" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vbc",
        "gibs",
        "gcbs"
    })
    public static class GCredPres {

        @XmlElement(name = "vBC", required = true)
        protected String vbc;
        @XmlElement(name = "gIBS")
        protected DetEvento211110.GCredPres.GIBS gibs;
        @XmlElement(name = "gCBS")
        protected DetEvento211110.GCredPres.GCBS gcbs;
        @XmlAttribute(name = "nItem", required = true)
        protected String nItem;

        /**
         * Obtém o valor da propriedade vbc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVBC() {
            return vbc;
        }

        /**
         * Define o valor da propriedade vbc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVBC(String value) {
            this.vbc = value;
        }

        /**
         * Obtém o valor da propriedade gibs.
         * 
         * @return
         *     possible object is
         *     {@link DetEvento211110.GCredPres.GIBS }
         *     
         */
        public DetEvento211110.GCredPres.GIBS getGIBS() {
            return gibs;
        }

        /**
         * Define o valor da propriedade gibs.
         * 
         * @param value
         *     allowed object is
         *     {@link DetEvento211110.GCredPres.GIBS }
         *     
         */
        public void setGIBS(DetEvento211110.GCredPres.GIBS value) {
            this.gibs = value;
        }

        /**
         * Obtém o valor da propriedade gcbs.
         * 
         * @return
         *     possible object is
         *     {@link DetEvento211110.GCredPres.GCBS }
         *     
         */
        public DetEvento211110.GCredPres.GCBS getGCBS() {
            return gcbs;
        }

        /**
         * Define o valor da propriedade gcbs.
         * 
         * @param value
         *     allowed object is
         *     {@link DetEvento211110.GCredPres.GCBS }
         *     
         */
        public void setGCBS(DetEvento211110.GCredPres.GCBS value) {
            this.gcbs = value;
        }

        /**
         * Obtém o valor da propriedade nItem.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNItem() {
            return nItem;
        }

        /**
         * Define o valor da propriedade nItem.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNItem(String value) {
            this.nItem = value;
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
         *         &lt;element name="cCredPres">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{2}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
         *         &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
            "cCredPres",
            "pCredPres",
            "vCredPres"
        })
        public static class GCBS {

            @XmlElement(required = true)
            protected String cCredPres;
            @XmlElement(required = true)
            protected String pCredPres;
            @XmlElement(required = true)
            protected String vCredPres;

            /**
             * Obtém o valor da propriedade cCredPres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCCredPres() {
                return cCredPres;
            }

            /**
             * Define o valor da propriedade cCredPres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCCredPres(String value) {
                this.cCredPres = value;
            }

            /**
             * Obtém o valor da propriedade pCredPres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPCredPres() {
                return pCredPres;
            }

            /**
             * Define o valor da propriedade pCredPres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPCredPres(String value) {
                this.pCredPres = value;
            }

            /**
             * Obtém o valor da propriedade vCredPres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVCredPres() {
                return vCredPres;
            }

            /**
             * Define o valor da propriedade vCredPres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVCredPres(String value) {
                this.vCredPres = value;
            }

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
         *         &lt;element name="cCredPres">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{2}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
         *         &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
            "cCredPres",
            "pCredPres",
            "vCredPres"
        })
        public static class GIBS {

            @XmlElement(required = true)
            protected String cCredPres;
            @XmlElement(required = true)
            protected String pCredPres;
            @XmlElement(required = true)
            protected String vCredPres;

            /**
             * Obtém o valor da propriedade cCredPres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCCredPres() {
                return cCredPres;
            }

            /**
             * Define o valor da propriedade cCredPres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCCredPres(String value) {
                this.cCredPres = value;
            }

            /**
             * Obtém o valor da propriedade pCredPres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPCredPres() {
                return pCredPres;
            }

            /**
             * Define o valor da propriedade pCredPres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPCredPres(String value) {
                this.pCredPres = value;
            }

            /**
             * Obtém o valor da propriedade vCredPres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVCredPres() {
                return vCredPres;
            }

            /**
             * Define o valor da propriedade vCredPres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVCredPres(String value) {
                this.vCredPres = value;
            }

        }

    }

}
