
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
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="gCredPresOper" maxOccurs="990">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vBCCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                   &lt;element name="cCredPres">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[0-9]{2}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="gIBSCredPres" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                             &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="gCBSCredPres" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
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
    "gCredPresOper"
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
    protected List<DetEvento211110.GCredPresOper> gCredPresOper;
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
     * Gets the value of the gCredPresOper property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gCredPresOper property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGCredPresOper().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento211110.GCredPresOper }
     * 
     * 
     */
    public List<DetEvento211110.GCredPresOper> getGCredPresOper() {
        if (gCredPresOper == null) {
            gCredPresOper = new ArrayList<DetEvento211110.GCredPresOper>();
        }
        return this.gCredPresOper;
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
     *         &lt;element name="vBCCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *         &lt;element name="cCredPres">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[0-9]{2}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="gIBSCredPres" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gCBSCredPres" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
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
        "vbcCredPres",
        "cCredPres",
        "gibsCredPres",
        "gcbsCredPres"
    })
    public static class GCredPresOper {

        @XmlElement(name = "vBCCredPres", required = true)
        protected String vbcCredPres;
        @XmlElement(required = true)
        protected String cCredPres;
        @XmlElement(name = "gIBSCredPres")
        protected DetEvento211110.GCredPresOper.GIBSCredPres gibsCredPres;
        @XmlElement(name = "gCBSCredPres")
        protected DetEvento211110.GCredPresOper.GCBSCredPres gcbsCredPres;
        @XmlAttribute(name = "nItem", required = true)
        protected String nItem;

        /**
         * Obtém o valor da propriedade vbcCredPres.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVBCCredPres() {
            return vbcCredPres;
        }

        /**
         * Define o valor da propriedade vbcCredPres.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVBCCredPres(String value) {
            this.vbcCredPres = value;
        }

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
         * Obtém o valor da propriedade gibsCredPres.
         * 
         * @return
         *     possible object is
         *     {@link DetEvento211110.GCredPresOper.GIBSCredPres }
         *     
         */
        public DetEvento211110.GCredPresOper.GIBSCredPres getGIBSCredPres() {
            return gibsCredPres;
        }

        /**
         * Define o valor da propriedade gibsCredPres.
         * 
         * @param value
         *     allowed object is
         *     {@link DetEvento211110.GCredPresOper.GIBSCredPres }
         *     
         */
        public void setGIBSCredPres(DetEvento211110.GCredPresOper.GIBSCredPres value) {
            this.gibsCredPres = value;
        }

        /**
         * Obtém o valor da propriedade gcbsCredPres.
         * 
         * @return
         *     possible object is
         *     {@link DetEvento211110.GCredPresOper.GCBSCredPres }
         *     
         */
        public DetEvento211110.GCredPresOper.GCBSCredPres getGCBSCredPres() {
            return gcbsCredPres;
        }

        /**
         * Define o valor da propriedade gcbsCredPres.
         * 
         * @param value
         *     allowed object is
         *     {@link DetEvento211110.GCredPresOper.GCBSCredPres }
         *     
         */
        public void setGCBSCredPres(DetEvento211110.GCredPresOper.GCBSCredPres value) {
            this.gcbsCredPres = value;
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
            "pCredPres",
            "vCredPres"
        })
        public static class GCBSCredPres {

            @XmlElement(required = true)
            protected String pCredPres;
            @XmlElement(required = true)
            protected String vCredPres;

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
            "pCredPres",
            "vCredPres"
        })
        public static class GIBSCredPres {

            @XmlElement(required = true)
            protected String pCredPres;
            @XmlElement(required = true)
            protected String vCredPres;

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
