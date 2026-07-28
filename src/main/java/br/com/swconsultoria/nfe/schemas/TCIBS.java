
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo CBS IBS Completo
 * 
 * <p>Classe Java de TCIBS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCIBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *         &lt;sequence>
 *           &lt;element name="gIBSUF">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="pIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                     &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
 *                     &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
 *                     &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
 *                     &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="gIBSMun">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="pIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                     &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
 *                     &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
 *                     &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
 *                     &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *         &lt;/sequence>
 *         &lt;element name="gCBS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                   &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
 *                   &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
 *                   &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gTribRegular" type="{http://www.portalfiscal.inf.br/nfe}TTribRegular" minOccurs="0"/>
 *         &lt;element name="gTribCompraGov" type="{http://www.portalfiscal.inf.br/nfe}TTribCompraGov" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCIBS", propOrder = {
    "vbc",
    "gibsuf",
    "gibsMun",
    "vibs",
    "gcbs",
    "gTribRegular",
    "gTribCompraGov"
})
public class TCIBS {

    @XmlElement(name = "vBC", required = true)
    protected String vbc;
    @XmlElement(name = "gIBSUF", required = true)
    protected TCIBS.GIBSUF gibsuf;
    @XmlElement(name = "gIBSMun", required = true)
    protected TCIBS.GIBSMun gibsMun;
    @XmlElement(name = "vIBS", required = true)
    protected String vibs;
    @XmlElement(name = "gCBS", required = true)
    protected TCIBS.GCBS gcbs;
    protected TTribRegular gTribRegular;
    protected TTribCompraGov gTribCompraGov;

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
     * Obtém o valor da propriedade gibsuf.
     * 
     * @return
     *     possible object is
     *     {@link TCIBS.GIBSUF }
     *     
     */
    public TCIBS.GIBSUF getGIBSUF() {
        return gibsuf;
    }

    /**
     * Define o valor da propriedade gibsuf.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS.GIBSUF }
     *     
     */
    public void setGIBSUF(TCIBS.GIBSUF value) {
        this.gibsuf = value;
    }

    /**
     * Obtém o valor da propriedade gibsMun.
     * 
     * @return
     *     possible object is
     *     {@link TCIBS.GIBSMun }
     *     
     */
    public TCIBS.GIBSMun getGIBSMun() {
        return gibsMun;
    }

    /**
     * Define o valor da propriedade gibsMun.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS.GIBSMun }
     *     
     */
    public void setGIBSMun(TCIBS.GIBSMun value) {
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
     * Obtém o valor da propriedade gcbs.
     * 
     * @return
     *     possible object is
     *     {@link TCIBS.GCBS }
     *     
     */
    public TCIBS.GCBS getGCBS() {
        return gcbs;
    }

    /**
     * Define o valor da propriedade gcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS.GCBS }
     *     
     */
    public void setGCBS(TCIBS.GCBS value) {
        this.gcbs = value;
    }

    /**
     * Obtém o valor da propriedade gTribRegular.
     * 
     * @return
     *     possible object is
     *     {@link TTribRegular }
     *     
     */
    public TTribRegular getGTribRegular() {
        return gTribRegular;
    }

    /**
     * Define o valor da propriedade gTribRegular.
     * 
     * @param value
     *     allowed object is
     *     {@link TTribRegular }
     *     
     */
    public void setGTribRegular(TTribRegular value) {
        this.gTribRegular = value;
    }

    /**
     * Obtém o valor da propriedade gTribCompraGov.
     * 
     * @return
     *     possible object is
     *     {@link TTribCompraGov }
     *     
     */
    public TTribCompraGov getGTribCompraGov() {
        return gTribCompraGov;
    }

    /**
     * Define o valor da propriedade gTribCompraGov.
     * 
     * @param value
     *     allowed object is
     *     {@link TTribCompraGov }
     *     
     */
    public void setGTribCompraGov(TTribCompraGov value) {
        this.gTribCompraGov = value;
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
     *         &lt;element name="pCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
     *         &lt;element name="gALCZFMCBS" minOccurs="0"/>
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
        "pcbs",
        "gDif",
        "gDevTrib",
        "gRed",
        "galczfmcbs",
        "vcbs"
    })
    public static class GCBS {

        @XmlElement(name = "pCBS", required = true)
        protected String pcbs;
        protected TDif gDif;
        protected TDevTrib gDevTrib;
        protected TRed gRed;
        @XmlElement(name = "gALCZFMCBS")
        protected TCIBS.GCBS.GALCZFMCBS galczfmcbs;
        @XmlElement(name = "vCBS", required = true)
        protected String vcbs;

        /**
         * Obtém o valor da propriedade pcbs.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPCBS() {
            return pcbs;
        }

        /**
         * Define o valor da propriedade pcbs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPCBS(String value) {
            this.pcbs = value;
        }

        /**
         * Obtém o valor da propriedade gDif.
         * 
         * @return
         *     possible object is
         *     {@link TDif }
         *     
         */
        public TDif getGDif() {
            return gDif;
        }

        /**
         * Define o valor da propriedade gDif.
         * 
         * @param value
         *     allowed object is
         *     {@link TDif }
         *     
         */
        public void setGDif(TDif value) {
            this.gDif = value;
        }

        /**
         * Obtém o valor da propriedade gDevTrib.
         * 
         * @return
         *     possible object is
         *     {@link TDevTrib }
         *     
         */
        public TDevTrib getGDevTrib() {
            return gDevTrib;
        }

        /**
         * Define o valor da propriedade gDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link TDevTrib }
         *     
         */
        public void setGDevTrib(TDevTrib value) {
            this.gDevTrib = value;
        }

        /**
         * Obtém o valor da propriedade gRed.
         * 
         * @return
         *     possible object is
         *     {@link TRed }
         *     
         */
        public TRed getGRed() {
            return gRed;
        }

        /**
         * Define o valor da propriedade gRed.
         * 
         * @param value
         *     allowed object is
         *     {@link TRed }
         *     
         */
        public void setGRed(TRed value) {
            this.gRed = value;
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
         * Obtém o valor da propriedade galczfmcbs.
         *
         * @return
         *     possible object is
         *     {@link TCIBS.GCBS.GALCZFMCBS }
         *
         */
        public TCIBS.GCBS.GALCZFMCBS getGALCZFMCBS() {
            return galczfmcbs;
        }

        /**
         * Define o valor da propriedade galczfmcbs.
         *
         * @param value
         *     allowed object is
         *     {@link TCIBS.GCBS.GALCZFMCBS }
         *
         */
        public void setGALCZFMCBS(TCIBS.GCBS.GALCZFMCBS value) {
            this.galczfmcbs = value;
        }


        /**
         * Grupo de operações em áreas incentivadas (ALC/ZFM) - CBS (alíquota zero).
         * Criado pela NT 2025.002 v1.40 (UB66a), arts. 451 e 466 da LC 214/2025.
         *
         * <p>Classe Java de anonymous complex type.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="tpALCZFMCBS" type="{http://www.portalfiscal.inf.br/nfe}TtpALCZFMCBS"/>
         *         &lt;element name="nProcSuframa" minOccurs="0"/>
         *         &lt;element name="pAliqEfetRegCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vTribRegCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "tpALCZFMCBS",
            "nProcSuframa",
            "pAliqEfetRegCBS",
            "vTribRegCBS"
        })
        public static class GALCZFMCBS {

            @XmlElement(required = true)
            protected String tpALCZFMCBS;
            protected String nProcSuframa;
            @XmlElement(required = true)
            protected String pAliqEfetRegCBS;
            @XmlElement(required = true)
            protected String vTribRegCBS;

            /**
             * Obtém o valor da propriedade tpALCZFMCBS.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTpALCZFMCBS() {
                return tpALCZFMCBS;
            }

            /**
             * Define o valor da propriedade tpALCZFMCBS.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTpALCZFMCBS(String value) {
                this.tpALCZFMCBS = value;
            }

            /**
             * Obtém o valor da propriedade nProcSuframa.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNProcSuframa() {
                return nProcSuframa;
            }

            /**
             * Define o valor da propriedade nProcSuframa.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNProcSuframa(String value) {
                this.nProcSuframa = value;
            }

            /**
             * Obtém o valor da propriedade pAliqEfetRegCBS.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPAliqEfetRegCBS() {
                return pAliqEfetRegCBS;
            }

            /**
             * Define o valor da propriedade pAliqEfetRegCBS.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPAliqEfetRegCBS(String value) {
                this.pAliqEfetRegCBS = value;
            }

            /**
             * Obtém o valor da propriedade vTribRegCBS.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getVTribRegCBS() {
                return vTribRegCBS;
            }

            /**
             * Define o valor da propriedade vTribRegCBS.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setVTribRegCBS(String value) {
                this.vTribRegCBS = value;
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
     *         &lt;element name="pIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
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
        "pibsMun",
        "gDif",
        "gDevTrib",
        "gRed",
        "vibsMun"
    })
    public static class GIBSMun {

        @XmlElement(name = "pIBSMun", required = true)
        protected String pibsMun;
        protected TDif gDif;
        protected TDevTrib gDevTrib;
        protected TRed gRed;
        @XmlElement(name = "vIBSMun", required = true)
        protected String vibsMun;

        /**
         * Obtém o valor da propriedade pibsMun.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBSMun() {
            return pibsMun;
        }

        /**
         * Define o valor da propriedade pibsMun.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPIBSMun(String value) {
            this.pibsMun = value;
        }

        /**
         * Obtém o valor da propriedade gDif.
         * 
         * @return
         *     possible object is
         *     {@link TDif }
         *     
         */
        public TDif getGDif() {
            return gDif;
        }

        /**
         * Define o valor da propriedade gDif.
         * 
         * @param value
         *     allowed object is
         *     {@link TDif }
         *     
         */
        public void setGDif(TDif value) {
            this.gDif = value;
        }

        /**
         * Obtém o valor da propriedade gDevTrib.
         * 
         * @return
         *     possible object is
         *     {@link TDevTrib }
         *     
         */
        public TDevTrib getGDevTrib() {
            return gDevTrib;
        }

        /**
         * Define o valor da propriedade gDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link TDevTrib }
         *     
         */
        public void setGDevTrib(TDevTrib value) {
            this.gDevTrib = value;
        }

        /**
         * Obtém o valor da propriedade gRed.
         * 
         * @return
         *     possible object is
         *     {@link TRed }
         *     
         */
        public TRed getGRed() {
            return gRed;
        }

        /**
         * Define o valor da propriedade gRed.
         * 
         * @param value
         *     allowed object is
         *     {@link TRed }
         *     
         */
        public void setGRed(TRed value) {
            this.gRed = value;
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
     *         &lt;element name="pIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
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
        "pibsuf",
        "gDif",
        "gDevTrib",
        "gRed",
        "vibsuf"
    })
    public static class GIBSUF {

        @XmlElement(name = "pIBSUF", required = true)
        protected String pibsuf;
        protected TDif gDif;
        protected TDevTrib gDevTrib;
        protected TRed gRed;
        @XmlElement(name = "vIBSUF", required = true)
        protected String vibsuf;

        /**
         * Obtém o valor da propriedade pibsuf.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBSUF() {
            return pibsuf;
        }

        /**
         * Define o valor da propriedade pibsuf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPIBSUF(String value) {
            this.pibsuf = value;
        }

        /**
         * Obtém o valor da propriedade gDif.
         * 
         * @return
         *     possible object is
         *     {@link TDif }
         *     
         */
        public TDif getGDif() {
            return gDif;
        }

        /**
         * Define o valor da propriedade gDif.
         * 
         * @param value
         *     allowed object is
         *     {@link TDif }
         *     
         */
        public void setGDif(TDif value) {
            this.gDif = value;
        }

        /**
         * Obtém o valor da propriedade gDevTrib.
         * 
         * @return
         *     possible object is
         *     {@link TDevTrib }
         *     
         */
        public TDevTrib getGDevTrib() {
            return gDevTrib;
        }

        /**
         * Define o valor da propriedade gDevTrib.
         * 
         * @param value
         *     allowed object is
         *     {@link TDevTrib }
         *     
         */
        public void setGDevTrib(TDevTrib value) {
            this.gDevTrib = value;
        }

        /**
         * Obtém o valor da propriedade gRed.
         * 
         * @return
         *     possible object is
         *     {@link TRed }
         *     
         */
        public TRed getGRed() {
            return gRed;
        }

        /**
         * Define o valor da propriedade gRed.
         * 
         * @param value
         *     allowed object is
         *     {@link TRed }
         *     
         */
        public void setGRed(TRed value) {
            this.gRed = value;
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
