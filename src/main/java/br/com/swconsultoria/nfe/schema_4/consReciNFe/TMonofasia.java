
package br.com.swconsultoria.nfe.schema_4.consReciNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Monofasia
 * 
 * <p>Classe Java de TMonofasia complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TMonofasia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gMonoPadrao" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
 *                   &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gMonoReten" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
 *                   &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gMonoRet" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qBCMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
 *                   &lt;element name="adRemIBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="adRemCBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="gMonoDif" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pDifIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *                   &lt;element name="vIBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="pDifCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                   &lt;element name="vCBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="vTotIBSMonoItem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="vTotCBSMonoItem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMonofasia", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "gMonoPadrao",
    "gMonoReten",
    "gMonoRet",
    "gMonoDif",
    "vTotIBSMonoItem",
    "vTotCBSMonoItem"
})
public class TMonofasia {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TMonofasia.GMonoPadrao gMonoPadrao;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TMonofasia.GMonoReten gMonoReten;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TMonofasia.GMonoRet gMonoRet;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TMonofasia.GMonoDif gMonoDif;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTotIBSMonoItem;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTotCBSMonoItem;

    /**
     * Obtém o valor da propriedade gMonoPadrao.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoPadrao }
     *     
     */
    public TMonofasia.GMonoPadrao getGMonoPadrao() {
        return gMonoPadrao;
    }

    /**
     * Define o valor da propriedade gMonoPadrao.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoPadrao }
     *     
     */
    public void setGMonoPadrao(TMonofasia.GMonoPadrao value) {
        this.gMonoPadrao = value;
    }

    /**
     * Obtém o valor da propriedade gMonoReten.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoReten }
     *     
     */
    public TMonofasia.GMonoReten getGMonoReten() {
        return gMonoReten;
    }

    /**
     * Define o valor da propriedade gMonoReten.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoReten }
     *     
     */
    public void setGMonoReten(TMonofasia.GMonoReten value) {
        this.gMonoReten = value;
    }

    /**
     * Obtém o valor da propriedade gMonoRet.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoRet }
     *     
     */
    public TMonofasia.GMonoRet getGMonoRet() {
        return gMonoRet;
    }

    /**
     * Define o valor da propriedade gMonoRet.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoRet }
     *     
     */
    public void setGMonoRet(TMonofasia.GMonoRet value) {
        this.gMonoRet = value;
    }

    /**
     * Obtém o valor da propriedade gMonoDif.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GMonoDif }
     *     
     */
    public TMonofasia.GMonoDif getGMonoDif() {
        return gMonoDif;
    }

    /**
     * Define o valor da propriedade gMonoDif.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GMonoDif }
     *     
     */
    public void setGMonoDif(TMonofasia.GMonoDif value) {
        this.gMonoDif = value;
    }

    /**
     * Obtém o valor da propriedade vTotIBSMonoItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTotIBSMonoItem() {
        return vTotIBSMonoItem;
    }

    /**
     * Define o valor da propriedade vTotIBSMonoItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTotIBSMonoItem(String value) {
        this.vTotIBSMonoItem = value;
    }

    /**
     * Obtém o valor da propriedade vTotCBSMonoItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTotCBSMonoItem() {
        return vTotCBSMonoItem;
    }

    /**
     * Define o valor da propriedade vTotCBSMonoItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTotCBSMonoItem(String value) {
        this.vTotCBSMonoItem = value;
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
     *         &lt;element name="pDifIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="vIBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="pDifCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCBSMonoDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "pDifIBS",
        "vibsMonoDif",
        "pDifCBS",
        "vcbsMonoDif"
    })
    public static class GMonoDif {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String pDifIBS;
        @XmlElement(name = "vIBSMonoDif", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMonoDif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String pDifCBS;
        @XmlElement(name = "vCBSMonoDif", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMonoDif;

        /**
         * Obtém o valor da propriedade pDifIBS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPDifIBS() {
            return pDifIBS;
        }

        /**
         * Define o valor da propriedade pDifIBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPDifIBS(String value) {
            this.pDifIBS = value;
        }

        /**
         * Obtém o valor da propriedade vibsMonoDif.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBSMonoDif() {
            return vibsMonoDif;
        }

        /**
         * Define o valor da propriedade vibsMonoDif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBSMonoDif(String value) {
            this.vibsMonoDif = value;
        }

        /**
         * Obtém o valor da propriedade pDifCBS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPDifCBS() {
            return pDifCBS;
        }

        /**
         * Define o valor da propriedade pDifCBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPDifCBS(String value) {
            this.pDifCBS = value;
        }

        /**
         * Obtém o valor da propriedade vcbsMonoDif.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBSMonoDif() {
            return vcbsMonoDif;
        }

        /**
         * Define o valor da propriedade vcbsMonoDif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBSMonoDif(String value) {
            this.vcbsMonoDif = value;
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
     *         &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
     *         &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "qbcMono",
        "adRemIBS",
        "adRemCBS",
        "vibsMono",
        "vcbsMono"
    })
    public static class GMonoPadrao {

        @XmlElement(name = "qBCMono", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String qbcMono;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String adRemIBS;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String adRemCBS;
        @XmlElement(name = "vIBSMono", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMono;
        @XmlElement(name = "vCBSMono", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMono;

        /**
         * Obtém o valor da propriedade qbcMono.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQBCMono() {
            return qbcMono;
        }

        /**
         * Define o valor da propriedade qbcMono.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQBCMono(String value) {
            this.qbcMono = value;
        }

        /**
         * Obtém o valor da propriedade adRemIBS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemIBS() {
            return adRemIBS;
        }

        /**
         * Define o valor da propriedade adRemIBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdRemIBS(String value) {
            this.adRemIBS = value;
        }

        /**
         * Obtém o valor da propriedade adRemCBS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemCBS() {
            return adRemCBS;
        }

        /**
         * Define o valor da propriedade adRemCBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdRemCBS(String value) {
            this.adRemCBS = value;
        }

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
     *         &lt;element name="qBCMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
     *         &lt;element name="adRemIBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="adRemCBSRet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
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
        "qbcMonoRet",
        "adRemIBSRet",
        "vibsMonoRet",
        "adRemCBSRet",
        "vcbsMonoRet"
    })
    public static class GMonoRet {

        @XmlElement(name = "qBCMonoRet", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String qbcMonoRet;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String adRemIBSRet;
        @XmlElement(name = "vIBSMonoRet", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMonoRet;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String adRemCBSRet;
        @XmlElement(name = "vCBSMonoRet", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMonoRet;

        /**
         * Obtém o valor da propriedade qbcMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQBCMonoRet() {
            return qbcMonoRet;
        }

        /**
         * Define o valor da propriedade qbcMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQBCMonoRet(String value) {
            this.qbcMonoRet = value;
        }

        /**
         * Obtém o valor da propriedade adRemIBSRet.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemIBSRet() {
            return adRemIBSRet;
        }

        /**
         * Define o valor da propriedade adRemIBSRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdRemIBSRet(String value) {
            this.adRemIBSRet = value;
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
         * Obtém o valor da propriedade adRemCBSRet.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemCBSRet() {
            return adRemCBSRet;
        }

        /**
         * Define o valor da propriedade adRemCBSRet.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdRemCBSRet(String value) {
            this.adRemCBSRet = value;
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
     *         &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
     *         &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
     *         &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
     *         &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
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
        "qbcMonoReten",
        "adRemIBSReten",
        "vibsMonoReten",
        "adRemCBSReten",
        "vcbsMonoReten"
    })
    public static class GMonoReten {

        @XmlElement(name = "qBCMonoReten", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String qbcMonoReten;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String adRemIBSReten;
        @XmlElement(name = "vIBSMonoReten", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibsMonoReten;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String adRemCBSReten;
        @XmlElement(name = "vCBSMonoReten", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbsMonoReten;

        /**
         * Obtém o valor da propriedade qbcMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQBCMonoReten() {
            return qbcMonoReten;
        }

        /**
         * Define o valor da propriedade qbcMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQBCMonoReten(String value) {
            this.qbcMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade adRemIBSReten.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemIBSReten() {
            return adRemIBSReten;
        }

        /**
         * Define o valor da propriedade adRemIBSReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdRemIBSReten(String value) {
            this.adRemIBSReten = value;
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
         * Obtém o valor da propriedade adRemCBSReten.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdRemCBSReten() {
            return adRemCBSReten;
        }

        /**
         * Define o valor da propriedade adRemCBSReten.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdRemCBSReten(String value) {
            this.adRemCBSReten = value;
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

    }

}
