
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Detalhamento dos Tributos por Item
 * 
 * <p>Classe Java de TTribSN complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTribSN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gIBSSN" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pIBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                   &lt;element name="vIBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gCBSSN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pCBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                   &lt;element name="vCBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribSN", propOrder = {
    "gibssn",
    "gcbssn"
})
public class TTribSN {

    @XmlElement(name = "gIBSSN")
    protected TTribSN.GIBSSN gibssn;
    @XmlElement(name = "gCBSSN", required = true)
    protected TTribSN.GCBSSN gcbssn;

    /**
     * Obtém o valor da propriedade gibssn.
     * 
     * @return
     *     possible object is
     *     {@link TTribSN.GIBSSN }
     *     
     */
    public TTribSN.GIBSSN getGIBSSN() {
        return gibssn;
    }

    /**
     * Define o valor da propriedade gibssn.
     * 
     * @param value
     *     allowed object is
     *     {@link TTribSN.GIBSSN }
     *     
     */
    public void setGIBSSN(TTribSN.GIBSSN value) {
        this.gibssn = value;
    }

    /**
     * Obtém o valor da propriedade gcbssn.
     * 
     * @return
     *     possible object is
     *     {@link TTribSN.GCBSSN }
     *     
     */
    public TTribSN.GCBSSN getGCBSSN() {
        return gcbssn;
    }

    /**
     * Define o valor da propriedade gcbssn.
     * 
     * @param value
     *     allowed object is
     *     {@link TTribSN.GCBSSN }
     *     
     */
    public void setGCBSSN(TTribSN.GCBSSN value) {
        this.gcbssn = value;
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
     *         &lt;element name="pCBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *         &lt;element name="vCBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
        "pcbssn",
        "vcbssn"
    })
    public static class GCBSSN {

        @XmlElement(name = "pCBSSN", required = true)
        protected String pcbssn;
        @XmlElement(name = "vCBSSN", required = true)
        protected String vcbssn;

        /**
         * Obtém o valor da propriedade pcbssn.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPCBSSN() {
            return pcbssn;
        }

        /**
         * Define o valor da propriedade pcbssn.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPCBSSN(String value) {
            this.pcbssn = value;
        }

        /**
         * Obtém o valor da propriedade vcbssn.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSSN() {
            return vcbssn;
        }

        /**
         * Define o valor da propriedade vcbssn.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBSSN(String value) {
            this.vcbssn = value;
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
     *         &lt;element name="pIBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *         &lt;element name="vIBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
        "pibssn",
        "vibssn"
    })
    public static class GIBSSN {

        @XmlElement(name = "pIBSSN", required = true)
        protected String pibssn;
        @XmlElement(name = "vIBSSN", required = true)
        protected String vibssn;

        /**
         * Obtém o valor da propriedade pibssn.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBSSN() {
            return pibssn;
        }

        /**
         * Define o valor da propriedade pibssn.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPIBSSN(String value) {
            this.pibssn = value;
        }

        /**
         * Obtém o valor da propriedade vibssn.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSSN() {
            return vibssn;
        }

        /**
         * Define o valor da propriedade vibssn.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBSSN(String value) {
            this.vibssn = value;
        }

    }

}
