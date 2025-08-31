
package br.com.swconsultoria.nfe.schema_4.enviNFe;

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
 *         &lt;element name="vBC" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;sequence>
 *           &lt;element name="gIBSUF">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="pIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                     &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
 *                     &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
 *                     &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
 *                     &lt;element name="vIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
 *                     &lt;element name="pIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                     &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
 *                     &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
 *                     &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
 *                     &lt;element name="vIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;/sequence>
 *         &lt;element name="gCBS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
 *                   &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
 *                   &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gTribRegular" type="{http://www.portalfiscal.inf.br/nfe}TTribRegular" minOccurs="0"/>
 *         &lt;element name="gIBSCredPres" type="{http://www.portalfiscal.inf.br/nfe}TCredPres" minOccurs="0"/>
 *         &lt;element name="gCBSCredPres" type="{http://www.portalfiscal.inf.br/nfe}TCredPres" minOccurs="0"/>
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
@XmlType(name = "TCIBS", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vbc",
    "gibsuf",
    "gibsMun",
    "vibs",
    "gcbs",
    "gTribRegular",
    "gibsCredPres",
    "gcbsCredPres",
    "gTribCompraGov"
})
public class TCIBS {

    @XmlElement(name = "vBC", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vbc;
    @XmlElement(name = "gIBSUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TCIBS.GIBSUF gibsuf;
    @XmlElement(name = "gIBSMun", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TCIBS.GIBSMun gibsMun;
    @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vibs;
    @XmlElement(name = "gCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TCIBS.GCBS gcbs;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TTribRegular gTribRegular;
    @XmlElement(name = "gIBSCredPres", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCredPres gibsCredPres;
    @XmlElement(name = "gCBSCredPres", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCredPres gcbsCredPres;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
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
     * Obtém o valor da propriedade gibsCredPres.
     * 
     * @return
     *     possible object is
     *     {@link TCredPres }
     *     
     */
    public TCredPres getGIBSCredPres() {
        return gibsCredPres;
    }

    /**
     * Define o valor da propriedade gibsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPres }
     *     
     */
    public void setGIBSCredPres(TCredPres value) {
        this.gibsCredPres = value;
    }

    /**
     * Obtém o valor da propriedade gcbsCredPres.
     * 
     * @return
     *     possible object is
     *     {@link TCredPres }
     *     
     */
    public TCredPres getGCBSCredPres() {
        return gcbsCredPres;
    }

    /**
     * Define o valor da propriedade gcbsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPres }
     *     
     */
    public void setGCBSCredPres(TCredPres value) {
        this.gcbsCredPres = value;
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
     *         &lt;element name="pCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
     *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "vcbs"
    })
    public static class GCBS {

        @XmlElement(name = "pCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String pcbs;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TDif gDif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TDevTrib gDevTrib;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TRed gRed;
        @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
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
     *         &lt;element name="pIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
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
        "pibsMun",
        "gDif",
        "gDevTrib",
        "gRed",
        "vibsMun"
    })
    public static class GIBSMun {

        @XmlElement(name = "pIBSMun", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String pibsMun;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TDif gDif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TDevTrib gDevTrib;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TRed gRed;
        @XmlElement(name = "vIBSMun", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
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
     *         &lt;element name="pIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="gDif" type="{http://www.portalfiscal.inf.br/nfe}TDif" minOccurs="0"/>
     *         &lt;element name="gDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDevTrib" minOccurs="0"/>
     *         &lt;element name="gRed" type="{http://www.portalfiscal.inf.br/nfe}TRed" minOccurs="0"/>
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
        "pibsuf",
        "gDif",
        "gDevTrib",
        "gRed",
        "vibsuf"
    })
    public static class GIBSUF {

        @XmlElement(name = "pIBSUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String pibsuf;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TDif gDif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TDevTrib gDevTrib;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TRed gRed;
        @XmlElement(name = "vIBSUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
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
