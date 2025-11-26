
package br.com.swconsultoria.nfe.schema_4.enviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações de totais da CBS/IBS com monofasia
 * 
 * <p>Classe Java de TIBSCBSMonoTot complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TIBSCBSMonoTot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vBCIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="gIBS" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="gIBSUF">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                             &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                             &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
 *                             &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                             &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                             &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
 *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gMono" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
@XmlType(name = "TIBSCBSMonoTot", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vbcibscbs",
    "gibs",
    "gcbs",
    "gMono"
})
public class TIBSCBSMonoTot {

    @XmlElement(name = "vBCIBSCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vbcibscbs;
    @XmlElement(name = "gIBS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TIBSCBSMonoTot.GIBS gibs;
    @XmlElement(name = "gCBS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TIBSCBSMonoTot.GCBS gcbs;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TIBSCBSMonoTot.GMono gMono;

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
     *     {@link TIBSCBSMonoTot.GIBS }
     *     
     */
    public TIBSCBSMonoTot.GIBS getGIBS() {
        return gibs;
    }

    /**
     * Define o valor da propriedade gibs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GIBS }
     *     
     */
    public void setGIBS(TIBSCBSMonoTot.GIBS value) {
        this.gibs = value;
    }

    /**
     * Obtém o valor da propriedade gcbs.
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSMonoTot.GCBS }
     *     
     */
    public TIBSCBSMonoTot.GCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GCBS }
     *     
     */
    public void setGCBS(TIBSCBSMonoTot.GCBS value) {
        this.gcbs = value;
    }

    /**
     * Obtém o valor da propriedade gMono.
     * 
     * @return
     *     possible object is
     *     {@link TIBSCBSMonoTot.GMono }
     *     
     */
    public TIBSCBSMonoTot.GMono getGMono() {
        return gMono;
    }

    /**
     * Define o valor da propriedade gMono.
     * 
     * @param value
     *     allowed object is
     *     {@link TIBSCBSMonoTot.GMono }
     *     
     */
    public void setGMono(TIBSCBSMonoTot.GMono value) {
        this.gMono = value;
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
     *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "vcbs",
        "vCredPres",
        "vCredPresCondSus"
    })
    public static class GCBS {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vDif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vDevTrib;
        @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbs;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vCredPres;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vCredPresCondSus;

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

        /**
         * Obtém o valor da propriedade vCredPresCondSus.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredPresCondSus() {
            return vCredPresCondSus;
        }

        /**
         * Define o valor da propriedade vCredPresCondSus.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCredPresCondSus(String value) {
            this.vCredPresCondSus = value;
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
     *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                   &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
     *                   &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                   &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                   &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "vibs",
        "vCredPres",
        "vCredPresCondSus"
    })
    public static class GIBS {

        @XmlElement(name = "gIBSUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected TIBSCBSMonoTot.GIBS.GIBSUF gibsuf;
        @XmlElement(name = "gIBSMun", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected TIBSCBSMonoTot.GIBS.GIBSMun gibsMun;
        @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibs;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vCredPres;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vCredPresCondSus;

        /**
         * Obtém o valor da propriedade gibsuf.
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSUF }
         *     
         */
        public TIBSCBSMonoTot.GIBS.GIBSUF getGIBSUF() {
            return gibsuf;
        }

        /**
         * Define o valor da propriedade gibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSUF }
         *     
         */
        public void setGIBSUF(TIBSCBSMonoTot.GIBS.GIBSUF value) {
            this.gibsuf = value;
        }

        /**
         * Obtém o valor da propriedade gibsMun.
         * 
         * @return
         *     possible object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSMun }
         *     
         */
        public TIBSCBSMonoTot.GIBS.GIBSMun getGIBSMun() {
            return gibsMun;
        }

        /**
         * Define o valor da propriedade gibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link TIBSCBSMonoTot.GIBS.GIBSMun }
         *     
         */
        public void setGIBSMun(TIBSCBSMonoTot.GIBS.GIBSMun value) {
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

        /**
         * Obtém o valor da propriedade vCredPresCondSus.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredPresCondSus() {
            return vCredPresCondSus;
        }

        /**
         * Define o valor da propriedade vCredPresCondSus.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCredPresCondSus(String value) {
            this.vCredPresCondSus = value;
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
         *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
         *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
         *         &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
         *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
         *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
         *         &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
     *         &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "vibsMono",
        "vcbsMono",
        "vibsMonoReten",
        "vcbsMonoReten",
        "vibsMonoRet",
        "vcbsMonoRet"
    })
    public static class GMono {

        @XmlElement(name = "vIBSMono", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMono;
        @XmlElement(name = "vCBSMono", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMono;
        @XmlElement(name = "vIBSMonoReten", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMonoReten;
        @XmlElement(name = "vCBSMonoReten", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMonoReten;
        @XmlElement(name = "vIBSMonoRet", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMonoRet;
        @XmlElement(name = "vCBSMonoRet", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMonoRet;

        /**
         * Obtém o valor da propriedade vibsMono.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMono() {
            return vibsMono;
        }

        /**
         * Define o valor da propriedade vibsMono.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBSMono(String value) {
            this.vibsMono = value;
        }

        /**
         * Obtém o valor da propriedade vcbsMono.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMono() {
            return vcbsMono;
        }

        /**
         * Define o valor da propriedade vcbsMono.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBSMono(String value) {
            this.vcbsMono = value;
        }

        /**
         * Obtém o valor da propriedade vibsMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMonoReten() {
            return vibsMonoReten;
        }

        /**
         * Define o valor da propriedade vibsMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBSMonoReten(String value) {
            this.vibsMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade vcbsMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMonoReten() {
            return vcbsMonoReten;
        }

        /**
         * Define o valor da propriedade vcbsMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBSMonoReten(String value) {
            this.vcbsMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade vibsMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMonoRet() {
            return vibsMonoRet;
        }

        /**
         * Define o valor da propriedade vibsMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBSMonoRet(String value) {
            this.vibsMonoRet = value;
        }

        /**
         * Obtém o valor da propriedade vcbsMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMonoRet() {
            return vcbsMonoRet;
        }

        /**
         * Define o valor da propriedade vcbsMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBSMonoRet(String value) {
            this.vcbsMonoRet = value;
        }

    }

}
