
package br.com.swconsultoria.nfe.schema_4.enviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações de totais da CBS/IBS
 * 
 * <p>Classe Java de TIBSCBSTot complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TIBSCBSTot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vBCIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *         &lt;element name="gIBS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="gIBSUF">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                             &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                             &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="gIBSMun">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                             &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                             &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gCBS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gEstornoCred" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vIBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                   &lt;element name="vCBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
@XmlType(name = "TIBSCBSTot", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vbcibscbs",
    "gibs",
    "gcbs",
    "gEstornoCred"
})
public class TIBSCBSTot {

    @XmlElement(name = "vBCIBSCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vbcibscbs;
    @XmlElement(name = "gIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TIBSCBSTot.GIBS gibs;
    @XmlElement(name = "gCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TIBSCBSTot.GCBS gcbs;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TIBSCBSTot.GEstornoCred gEstornoCred;

    /**
     * Obtém o valor da propriedade vbcibscbs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBCIBSCBS() {
        return vbcibscbs;
    }

    /**
     * Define o valor da propriedade vbcibscbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBCIBSCBS(String value) {
        this.vbcibscbs = value;
    }

    /**
     * Obtém o valor da propriedade gibs.
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSTot.GIBS }
     *     
     */
    public TIBSCBSTot.GIBS getGIBS() {
        return gibs;
    }

    /**
     * Define o valor da propriedade gibs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSTot.GIBS }
     *     
     */
    public void setGIBS(TIBSCBSTot.GIBS value) {
        this.gibs = value;
    }

    /**
     * Obtém o valor da propriedade gcbs.
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSTot.GCBS }
     *     
     */
    public TIBSCBSTot.GCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSTot.GCBS }
     *     
     */
    public void setGCBS(TIBSCBSTot.GCBS value) {
        this.gcbs = value;
    }

    /**
     * Obtém o valor da propriedade gEstornoCred.
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSTot.GEstornoCred }
     *     
     */
    public TIBSCBSTot.GEstornoCred getGEstornoCred() {
        return gEstornoCred;
    }

    /**
     * Define o valor da propriedade gEstornoCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSTot.GEstornoCred }
     *     
     */
    public void setGEstornoCred(TIBSCBSTot.GEstornoCred value) {
        this.gEstornoCred = value;
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
     *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
        "vDif",
        "vDevTrib",
        "vcbs"
    })
    public static class GCBS {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vDif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vDevTrib;
        @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbs;

        /**
         * Obtém o valor da propriedade vDif.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVDif() {
            return vDif;
        }

        /**
         * Define o valor da propriedade vDif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVDif(String value) {
            this.vDif = value;
        }

        /**
         * Obtém o valor da propriedade vDevTrib.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVDevTrib() {
            return vDevTrib;
        }

        /**
         * Define o valor da propriedade vDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVDevTrib(String value) {
            this.vDevTrib = value;
        }

        /**
         * Obtém o valor da propriedade vcbs.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBS() {
            return vcbs;
        }

        /**
         * Define o valor da propriedade vcbs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBS(String value) {
            this.vcbs = value;
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
     *         &lt;element name="vIBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *         &lt;element name="vCBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
        "vibsEstCred",
        "vcbsEstCred"
    })
    public static class GEstornoCred {

        @XmlElement(name = "vIBSEstCred", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsEstCred;
        @XmlElement(name = "vCBSEstCred", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsEstCred;

        /**
         * Obtém o valor da propriedade vibsEstCred.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSEstCred() {
            return vibsEstCred;
        }

        /**
         * Define o valor da propriedade vibsEstCred.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBSEstCred(String value) {
            this.vibsEstCred = value;
        }

        /**
         * Obtém o valor da propriedade vcbsEstCred.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSEstCred() {
            return vcbsEstCred;
        }

        /**
         * Define o valor da propriedade vcbsEstCred.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBSEstCred(String value) {
            this.vcbsEstCred = value;
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
     *         &lt;element name="gIBSUF">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gIBSMun">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
        "gibsuf",
        "gibsMun",
        "vibs"
    })
    public static class GIBS {

        @XmlElement(name = "gIBSUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected TIBSCBSTot.GIBS.GIBSUF gibsuf;
        @XmlElement(name = "gIBSMun", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected TIBSCBSTot.GIBS.GIBSMun gibsMun;
        @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibs;

        /**
         * Obtém o valor da propriedade gibsuf.
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSTot.GIBS.GIBSUF }
         *     
         */
        public TIBSCBSTot.GIBS.GIBSUF getGIBSUF() {
            return gibsuf;
        }

        /**
         * Define o valor da propriedade gibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSTot.GIBS.GIBSUF }
         *     
         */
        public void setGIBSUF(TIBSCBSTot.GIBS.GIBSUF value) {
            this.gibsuf = value;
        }

        /**
         * Obtém o valor da propriedade gibsMun.
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSTot.GIBS.GIBSMun }
         *     
         */
        public TIBSCBSTot.GIBS.GIBSMun getGIBSMun() {
            return gibsMun;
        }

        /**
         * Define o valor da propriedade gibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSTot.GIBS.GIBSMun }
         *     
         */
        public void setGIBSMun(TIBSCBSTot.GIBS.GIBSMun value) {
            this.gibsMun = value;
        }

        /**
         * Obtém o valor da propriedade vibs.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBS() {
            return vibs;
        }

        /**
         * Define o valor da propriedade vibs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBS(String value) {
            this.vibs = value;
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
         *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vDif",
            "vDevTrib",
            "vibsMun"
        })
        public static class GIBSMun {

            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vDif;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vDevTrib;
            @XmlElement(name = "vIBSMun", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vibsMun;

            /**
             * Obtém o valor da propriedade vDif.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVDif() {
                return vDif;
            }

            /**
             * Define o valor da propriedade vDif.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVDif(String value) {
                this.vDif = value;
            }

            /**
             * Obtém o valor da propriedade vDevTrib.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVDevTrib() {
                return vDevTrib;
            }

            /**
             * Define o valor da propriedade vDevTrib.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVDevTrib(String value) {
                this.vDevTrib = value;
            }

            /**
             * Obtém o valor da propriedade vibsMun.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBSMun() {
                return vibsMun;
            }

            /**
             * Define o valor da propriedade vibsMun.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBSMun(String value) {
                this.vibsMun = value;
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
         *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vDif",
            "vDevTrib",
            "vibsuf"
        })
        public static class GIBSUF {

            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vDif;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vDevTrib;
            @XmlElement(name = "vIBSUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vibsuf;

            /**
             * Obtém o valor da propriedade vDif.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVDif() {
                return vDif;
            }

            /**
             * Define o valor da propriedade vDif.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVDif(String value) {
                this.vDif = value;
            }

            /**
             * Obtém o valor da propriedade vDevTrib.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVDevTrib() {
                return vDevTrib;
            }

            /**
             * Define o valor da propriedade vDevTrib.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVDevTrib(String value) {
                this.vDevTrib = value;
            }

            /**
             * Obtém o valor da propriedade vibsuf.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBSUF() {
                return vibsuf;
            }

            /**
             * Define o valor da propriedade vibsuf.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBSUF(String value) {
                this.vibsuf = value;
            }

        }

    }

}
