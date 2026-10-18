
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de Informações do IBS e CBS em operações com imposto monofásico  (CST 620)
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
 *         &lt;sequence minOccurs="0">
 *           &lt;choice>
 *             &lt;element name="gIBSMonoAdRem" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;sequence>
 *                       &lt;element name="gMonoPadrao" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoReten" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoRet" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gpBioDiferenca" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="vIBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                     &lt;/sequence>
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *             &lt;element name="gIBSMonoAdValorem" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;sequence>
 *                       &lt;element name="gMonoPadrao" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                                 &lt;element name="pAliqMonoUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vIBSMonoUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                                 &lt;element name="pAliqMonoMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vIBSMonoMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                                 &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoReten" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                                 &lt;element name="pAliqMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoRet" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gpBioDiferenca" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="vIBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                     &lt;/sequence>
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *           &lt;/choice>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;choice>
 *             &lt;element name="gCBSMonoAdRem" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;sequence>
 *                       &lt;element name="gMonoPadrao" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoReten" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoRet" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gpBioDiferenca" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="vCBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                     &lt;/sequence>
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *             &lt;element name="gCBSMonoAdValorem" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;sequence>
 *                       &lt;element name="gMonoPadrao" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                                 &lt;element name="pAliqMonoCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoReten" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                                 &lt;element name="pAliqMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *                                 &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gMonoRet" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                       &lt;element name="gpBioDiferenca" minOccurs="0">
 *                         &lt;complexType>
 *                           &lt;complexContent>
 *                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                               &lt;sequence>
 *                                 &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
 *                                 &lt;element name="vCBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *                               &lt;/sequence>
 *                             &lt;/restriction>
 *                           &lt;/complexContent>
 *                         &lt;/complexType>
 *                       &lt;/element>
 *                     &lt;/sequence>
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *           &lt;/choice>
 *         &lt;/sequence>
 *         &lt;element name="vTotIBSMonoItem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *         &lt;element name="vTotCBSMonoItem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMonofasia", propOrder = {
    "gibsMonoAdRem",
    "gibsMonoAdValorem",
    "gcbsMonoAdRem",
    "gcbsMonoAdValorem",
    "vTotIBSMonoItem",
    "vTotCBSMonoItem"
})
public class TMonofasia {

    @XmlElement(name = "gIBSMonoAdRem")
    protected TMonofasia.GIBSMonoAdRem gibsMonoAdRem;
    @XmlElement(name = "gIBSMonoAdValorem")
    protected TMonofasia.GIBSMonoAdValorem gibsMonoAdValorem;
    @XmlElement(name = "gCBSMonoAdRem")
    protected TMonofasia.GCBSMonoAdRem gcbsMonoAdRem;
    @XmlElement(name = "gCBSMonoAdValorem")
    protected TMonofasia.GCBSMonoAdValorem gcbsMonoAdValorem;
    @XmlElement(required = true)
    protected String vTotIBSMonoItem;
    @XmlElement(required = true)
    protected String vTotCBSMonoItem;

    /**
     * Obtém o valor da propriedade gibsMonoAdRem.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GIBSMonoAdRem }
     *     
     */
    public TMonofasia.GIBSMonoAdRem getGIBSMonoAdRem() {
        return gibsMonoAdRem;
    }

    /**
     * Define o valor da propriedade gibsMonoAdRem.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GIBSMonoAdRem }
     *     
     */
    public void setGIBSMonoAdRem(TMonofasia.GIBSMonoAdRem value) {
        this.gibsMonoAdRem = value;
    }

    /**
     * Obtém o valor da propriedade gibsMonoAdValorem.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GIBSMonoAdValorem }
     *     
     */
    public TMonofasia.GIBSMonoAdValorem getGIBSMonoAdValorem() {
        return gibsMonoAdValorem;
    }

    /**
     * Define o valor da propriedade gibsMonoAdValorem.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GIBSMonoAdValorem }
     *     
     */
    public void setGIBSMonoAdValorem(TMonofasia.GIBSMonoAdValorem value) {
        this.gibsMonoAdValorem = value;
    }

    /**
     * Obtém o valor da propriedade gcbsMonoAdRem.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GCBSMonoAdRem }
     *     
     */
    public TMonofasia.GCBSMonoAdRem getGCBSMonoAdRem() {
        return gcbsMonoAdRem;
    }

    /**
     * Define o valor da propriedade gcbsMonoAdRem.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GCBSMonoAdRem }
     *     
     */
    public void setGCBSMonoAdRem(TMonofasia.GCBSMonoAdRem value) {
        this.gcbsMonoAdRem = value;
    }

    /**
     * Obtém o valor da propriedade gcbsMonoAdValorem.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia.GCBSMonoAdValorem }
     *     
     */
    public TMonofasia.GCBSMonoAdValorem getGCBSMonoAdValorem() {
        return gcbsMonoAdValorem;
    }

    /**
     * Define o valor da propriedade gcbsMonoAdValorem.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia.GCBSMonoAdValorem }
     *     
     */
    public void setGCBSMonoAdValorem(TMonofasia.GCBSMonoAdValorem value) {
        this.gcbsMonoAdValorem = value;
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
     *         &lt;element name="gMonoPadrao" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gpBioDiferenca" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="vCBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
    @XmlType(name = "", propOrder = {
        "gMonoPadrao",
        "gMonoReten",
        "gMonoRet",
        "gpBioDiferenca"
    })
    public static class GCBSMonoAdRem {

        protected TMonofasia.GCBSMonoAdRem.GMonoPadrao gMonoPadrao;
        protected TMonofasia.GCBSMonoAdRem.GMonoReten gMonoReten;
        protected TMonofasia.GCBSMonoAdRem.GMonoRet gMonoRet;
        protected TMonofasia.GCBSMonoAdRem.GpBioDiferenca gpBioDiferenca;

        /**
         * Obtém o valor da propriedade gMonoPadrao.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdRem.GMonoPadrao }
         *     
         */
        public TMonofasia.GCBSMonoAdRem.GMonoPadrao getGMonoPadrao() {
            return gMonoPadrao;
        }

        /**
         * Define o valor da propriedade gMonoPadrao.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdRem.GMonoPadrao }
         *     
         */
        public void setGMonoPadrao(TMonofasia.GCBSMonoAdRem.GMonoPadrao value) {
            this.gMonoPadrao = value;
        }

        /**
         * Obtém o valor da propriedade gMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdRem.GMonoReten }
         *     
         */
        public TMonofasia.GCBSMonoAdRem.GMonoReten getGMonoReten() {
            return gMonoReten;
        }

        /**
         * Define o valor da propriedade gMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdRem.GMonoReten }
         *     
         */
        public void setGMonoReten(TMonofasia.GCBSMonoAdRem.GMonoReten value) {
            this.gMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade gMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdRem.GMonoRet }
         *     
         */
        public TMonofasia.GCBSMonoAdRem.GMonoRet getGMonoRet() {
            return gMonoRet;
        }

        /**
         * Define o valor da propriedade gMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdRem.GMonoRet }
         *     
         */
        public void setGMonoRet(TMonofasia.GCBSMonoAdRem.GMonoRet value) {
            this.gMonoRet = value;
        }

        /**
         * Obtém o valor da propriedade gpBioDiferenca.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdRem.GpBioDiferenca }
         *     
         */
        public TMonofasia.GCBSMonoAdRem.GpBioDiferenca getGpBioDiferenca() {
            return gpBioDiferenca;
        }

        /**
         * Define o valor da propriedade gpBioDiferenca.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdRem.GpBioDiferenca }
         *     
         */
        public void setGpBioDiferenca(TMonofasia.GCBSMonoAdRem.GpBioDiferenca value) {
            this.gpBioDiferenca = value;
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
         *         &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="adRemCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "adRemCBS",
            "vcbsMono"
        })
        public static class GMonoPadrao {

            @XmlElement(name = "qBCMono", required = true)
            protected String qbcMono;
            @XmlElement(required = true)
            protected String adRemCBS;
            @XmlElement(name = "vCBSMono", required = true)
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
         *         &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vcbsMonoRet"
        })
        public static class GMonoRet {

            @XmlElement(name = "vCBSMonoRet", required = true)
            protected String vcbsMonoRet;

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
         *         &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="adRemCBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "adRemCBSReten",
            "vcbsMonoReten"
        })
        public static class GMonoReten {

            @XmlElement(name = "qBCMonoReten", required = true)
            protected String qbcMonoReten;
            @XmlElement(required = true)
            protected String adRemCBSReten;
            @XmlElement(name = "vCBSMonoReten", required = true)
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
         *         &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="vCBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "qbcBioComb",
            "vcbsDiferenca"
        })
        public static class GpBioDiferenca {

            @XmlElement(name = "qBCBioComb", required = true)
            protected String qbcBioComb;
            @XmlElement(name = "vCBSDiferenca", required = true)
            protected String vcbsDiferenca;

            /**
             * Obtém o valor da propriedade qbcBioComb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQBCBioComb() {
                return qbcBioComb;
            }

            /**
             * Define o valor da propriedade qbcBioComb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQBCBioComb(String value) {
                this.qbcBioComb = value;
            }

            /**
             * Obtém o valor da propriedade vcbsDiferenca.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVCBSDiferenca() {
                return vcbsDiferenca;
            }

            /**
             * Define o valor da propriedade vcbsDiferenca.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVCBSDiferenca(String value) {
                this.vcbsDiferenca = value;
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
     *         &lt;element name="gMonoPadrao" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="vBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="pAliqMonoCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="vBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="pAliqMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gpBioDiferenca" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="vCBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
    @XmlType(name = "", propOrder = {
        "gMonoPadrao",
        "gMonoReten",
        "gMonoRet",
        "gpBioDiferenca"
    })
    public static class GCBSMonoAdValorem {

        protected TMonofasia.GCBSMonoAdValorem.GMonoPadrao gMonoPadrao;
        protected TMonofasia.GCBSMonoAdValorem.GMonoReten gMonoReten;
        protected TMonofasia.GCBSMonoAdValorem.GMonoRet gMonoRet;
        protected TMonofasia.GCBSMonoAdValorem.GpBioDiferenca gpBioDiferenca;

        /**
         * Obtém o valor da propriedade gMonoPadrao.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GMonoPadrao }
         *     
         */
        public TMonofasia.GCBSMonoAdValorem.GMonoPadrao getGMonoPadrao() {
            return gMonoPadrao;
        }

        /**
         * Define o valor da propriedade gMonoPadrao.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GMonoPadrao }
         *     
         */
        public void setGMonoPadrao(TMonofasia.GCBSMonoAdValorem.GMonoPadrao value) {
            this.gMonoPadrao = value;
        }

        /**
         * Obtém o valor da propriedade gMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GMonoReten }
         *     
         */
        public TMonofasia.GCBSMonoAdValorem.GMonoReten getGMonoReten() {
            return gMonoReten;
        }

        /**
         * Define o valor da propriedade gMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GMonoReten }
         *     
         */
        public void setGMonoReten(TMonofasia.GCBSMonoAdValorem.GMonoReten value) {
            this.gMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade gMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GMonoRet }
         *     
         */
        public TMonofasia.GCBSMonoAdValorem.GMonoRet getGMonoRet() {
            return gMonoRet;
        }

        /**
         * Define o valor da propriedade gMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GMonoRet }
         *     
         */
        public void setGMonoRet(TMonofasia.GCBSMonoAdValorem.GMonoRet value) {
            this.gMonoRet = value;
        }

        /**
         * Obtém o valor da propriedade gpBioDiferenca.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GpBioDiferenca }
         *     
         */
        public TMonofasia.GCBSMonoAdValorem.GpBioDiferenca getGpBioDiferenca() {
            return gpBioDiferenca;
        }

        /**
         * Define o valor da propriedade gpBioDiferenca.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GCBSMonoAdValorem.GpBioDiferenca }
         *     
         */
        public void setGpBioDiferenca(TMonofasia.GCBSMonoAdValorem.GpBioDiferenca value) {
            this.gpBioDiferenca = value;
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
         *         &lt;element name="vBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="pAliqMonoCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vbcMono",
            "pAliqMonoCBS",
            "vcbsMono"
        })
        public static class GMonoPadrao {

            @XmlElement(name = "vBCMono", required = true)
            protected String vbcMono;
            @XmlElement(required = true)
            protected String pAliqMonoCBS;
            @XmlElement(name = "vCBSMono", required = true)
            protected String vcbsMono;

            /**
             * Obtém o valor da propriedade vbcMono.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVBCMono() {
                return vbcMono;
            }

            /**
             * Define o valor da propriedade vbcMono.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVBCMono(String value) {
                this.vbcMono = value;
            }

            /**
             * Obtém o valor da propriedade pAliqMonoCBS.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPAliqMonoCBS() {
                return pAliqMonoCBS;
            }

            /**
             * Define o valor da propriedade pAliqMonoCBS.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPAliqMonoCBS(String value) {
                this.pAliqMonoCBS = value;
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
         *         &lt;element name="vCBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vcbsMonoRet"
        })
        public static class GMonoRet {

            @XmlElement(name = "vCBSMonoRet", required = true)
            protected String vcbsMonoRet;

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
         *         &lt;element name="vBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="pAliqMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vCBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vbcMonoReten",
            "pAliqMonoReten",
            "vcbsMonoReten"
        })
        public static class GMonoReten {

            @XmlElement(name = "vBCMonoReten", required = true)
            protected String vbcMonoReten;
            @XmlElement(required = true)
            protected String pAliqMonoReten;
            @XmlElement(name = "vCBSMonoReten", required = true)
            protected String vcbsMonoReten;

            /**
             * Obtém o valor da propriedade vbcMonoReten.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVBCMonoReten() {
                return vbcMonoReten;
            }

            /**
             * Define o valor da propriedade vbcMonoReten.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVBCMonoReten(String value) {
                this.vbcMonoReten = value;
            }

            /**
             * Obtém o valor da propriedade pAliqMonoReten.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPAliqMonoReten() {
                return pAliqMonoReten;
            }

            /**
             * Define o valor da propriedade pAliqMonoReten.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPAliqMonoReten(String value) {
                this.pAliqMonoReten = value;
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
         *         &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="vCBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "qbcBioComb",
            "vcbsDiferenca"
        })
        public static class GpBioDiferenca {

            @XmlElement(name = "qBCBioComb", required = true)
            protected String qbcBioComb;
            @XmlElement(name = "vCBSDiferenca", required = true)
            protected String vcbsDiferenca;

            /**
             * Obtém o valor da propriedade qbcBioComb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQBCBioComb() {
                return qbcBioComb;
            }

            /**
             * Define o valor da propriedade qbcBioComb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQBCBioComb(String value) {
                this.qbcBioComb = value;
            }

            /**
             * Obtém o valor da propriedade vcbsDiferenca.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVCBSDiferenca() {
                return vcbsDiferenca;
            }

            /**
             * Define o valor da propriedade vcbsDiferenca.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVCBSDiferenca(String value) {
                this.vcbsDiferenca = value;
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
     *         &lt;element name="gMonoPadrao" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gpBioDiferenca" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="vIBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
    @XmlType(name = "", propOrder = {
        "gMonoPadrao",
        "gMonoReten",
        "gMonoRet",
        "gpBioDiferenca"
    })
    public static class GIBSMonoAdRem {

        protected TMonofasia.GIBSMonoAdRem.GMonoPadrao gMonoPadrao;
        protected TMonofasia.GIBSMonoAdRem.GMonoReten gMonoReten;
        protected TMonofasia.GIBSMonoAdRem.GMonoRet gMonoRet;
        protected TMonofasia.GIBSMonoAdRem.GpBioDiferenca gpBioDiferenca;

        /**
         * Obtém o valor da propriedade gMonoPadrao.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdRem.GMonoPadrao }
         *     
         */
        public TMonofasia.GIBSMonoAdRem.GMonoPadrao getGMonoPadrao() {
            return gMonoPadrao;
        }

        /**
         * Define o valor da propriedade gMonoPadrao.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdRem.GMonoPadrao }
         *     
         */
        public void setGMonoPadrao(TMonofasia.GIBSMonoAdRem.GMonoPadrao value) {
            this.gMonoPadrao = value;
        }

        /**
         * Obtém o valor da propriedade gMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdRem.GMonoReten }
         *     
         */
        public TMonofasia.GIBSMonoAdRem.GMonoReten getGMonoReten() {
            return gMonoReten;
        }

        /**
         * Define o valor da propriedade gMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdRem.GMonoReten }
         *     
         */
        public void setGMonoReten(TMonofasia.GIBSMonoAdRem.GMonoReten value) {
            this.gMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade gMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdRem.GMonoRet }
         *     
         */
        public TMonofasia.GIBSMonoAdRem.GMonoRet getGMonoRet() {
            return gMonoRet;
        }

        /**
         * Define o valor da propriedade gMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdRem.GMonoRet }
         *     
         */
        public void setGMonoRet(TMonofasia.GIBSMonoAdRem.GMonoRet value) {
            this.gMonoRet = value;
        }

        /**
         * Obtém o valor da propriedade gpBioDiferenca.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdRem.GpBioDiferenca }
         *     
         */
        public TMonofasia.GIBSMonoAdRem.GpBioDiferenca getGpBioDiferenca() {
            return gpBioDiferenca;
        }

        /**
         * Define o valor da propriedade gpBioDiferenca.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdRem.GpBioDiferenca }
         *     
         */
        public void setGpBioDiferenca(TMonofasia.GIBSMonoAdRem.GpBioDiferenca value) {
            this.gpBioDiferenca = value;
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
         *         &lt;element name="qBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="adRemIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vibsMono"
        })
        public static class GMonoPadrao {

            @XmlElement(name = "qBCMono", required = true)
            protected String qbcMono;
            @XmlElement(required = true)
            protected String adRemIBS;
            @XmlElement(name = "vIBSMono", required = true)
            protected String vibsMono;

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
         *         &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vibsMonoRet"
        })
        public static class GMonoRet {

            @XmlElement(name = "vIBSMonoRet", required = true)
            protected String vibsMonoRet;

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
         *         &lt;element name="qBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="adRemIBSReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vibsMonoReten"
        })
        public static class GMonoReten {

            @XmlElement(name = "qBCMonoReten", required = true)
            protected String qbcMonoReten;
            @XmlElement(required = true)
            protected String adRemIBSReten;
            @XmlElement(name = "vIBSMonoReten", required = true)
            protected String vibsMonoReten;

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
         *         &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="vIBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "qbcBioComb",
            "vibsDiferenca"
        })
        public static class GpBioDiferenca {

            @XmlElement(name = "qBCBioComb", required = true)
            protected String qbcBioComb;
            @XmlElement(name = "vIBSDiferenca", required = true)
            protected String vibsDiferenca;

            /**
             * Obtém o valor da propriedade qbcBioComb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQBCBioComb() {
                return qbcBioComb;
            }

            /**
             * Define o valor da propriedade qbcBioComb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQBCBioComb(String value) {
                this.qbcBioComb = value;
            }

            /**
             * Obtém o valor da propriedade vibsDiferenca.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBSDiferenca() {
                return vibsDiferenca;
            }

            /**
             * Define o valor da propriedade vibsDiferenca.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBSDiferenca(String value) {
                this.vibsDiferenca = value;
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
     *         &lt;element name="gMonoPadrao" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="vBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="pAliqMonoUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vIBSMonoUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="pAliqMonoMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vIBSMonoMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="vBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                   &lt;element name="pAliqMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
     *                   &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
     *                   &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="gpBioDiferenca" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
     *                   &lt;element name="vIBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
    @XmlType(name = "", propOrder = {
        "gMonoPadrao",
        "gMonoReten",
        "gMonoRet",
        "gpBioDiferenca"
    })
    public static class GIBSMonoAdValorem {

        protected TMonofasia.GIBSMonoAdValorem.GMonoPadrao gMonoPadrao;
        protected TMonofasia.GIBSMonoAdValorem.GMonoReten gMonoReten;
        protected TMonofasia.GIBSMonoAdValorem.GMonoRet gMonoRet;
        protected TMonofasia.GIBSMonoAdValorem.GpBioDiferenca gpBioDiferenca;

        /**
         * Obtém o valor da propriedade gMonoPadrao.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GMonoPadrao }
         *     
         */
        public TMonofasia.GIBSMonoAdValorem.GMonoPadrao getGMonoPadrao() {
            return gMonoPadrao;
        }

        /**
         * Define o valor da propriedade gMonoPadrao.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GMonoPadrao }
         *     
         */
        public void setGMonoPadrao(TMonofasia.GIBSMonoAdValorem.GMonoPadrao value) {
            this.gMonoPadrao = value;
        }

        /**
         * Obtém o valor da propriedade gMonoReten.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GMonoReten }
         *     
         */
        public TMonofasia.GIBSMonoAdValorem.GMonoReten getGMonoReten() {
            return gMonoReten;
        }

        /**
         * Define o valor da propriedade gMonoReten.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GMonoReten }
         *     
         */
        public void setGMonoReten(TMonofasia.GIBSMonoAdValorem.GMonoReten value) {
            this.gMonoReten = value;
        }

        /**
         * Obtém o valor da propriedade gMonoRet.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GMonoRet }
         *     
         */
        public TMonofasia.GIBSMonoAdValorem.GMonoRet getGMonoRet() {
            return gMonoRet;
        }

        /**
         * Define o valor da propriedade gMonoRet.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GMonoRet }
         *     
         */
        public void setGMonoRet(TMonofasia.GIBSMonoAdValorem.GMonoRet value) {
            this.gMonoRet = value;
        }

        /**
         * Obtém o valor da propriedade gpBioDiferenca.
         * 
         * @return
         *     possible object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GpBioDiferenca }
         *     
         */
        public TMonofasia.GIBSMonoAdValorem.GpBioDiferenca getGpBioDiferenca() {
            return gpBioDiferenca;
        }

        /**
         * Define o valor da propriedade gpBioDiferenca.
         * 
         * @param value
         *     allowed object is
         *     {@link TMonofasia.GIBSMonoAdValorem.GpBioDiferenca }
         *     
         */
        public void setGpBioDiferenca(TMonofasia.GIBSMonoAdValorem.GpBioDiferenca value) {
            this.gpBioDiferenca = value;
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
         *         &lt;element name="vBCMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="pAliqMonoUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vIBSMonoUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="pAliqMonoMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vIBSMonoMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="vIBSMono" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vbcMono",
            "pAliqMonoUF",
            "vibsMonoUF",
            "pAliqMonoMun",
            "vibsMonoMun",
            "vibsMono"
        })
        public static class GMonoPadrao {

            @XmlElement(name = "vBCMono", required = true)
            protected String vbcMono;
            @XmlElement(required = true)
            protected String pAliqMonoUF;
            @XmlElement(name = "vIBSMonoUF", required = true)
            protected String vibsMonoUF;
            @XmlElement(required = true)
            protected String pAliqMonoMun;
            @XmlElement(name = "vIBSMonoMun", required = true)
            protected String vibsMonoMun;
            @XmlElement(name = "vIBSMono", required = true)
            protected String vibsMono;

            /**
             * Obtém o valor da propriedade vbcMono.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVBCMono() {
                return vbcMono;
            }

            /**
             * Define o valor da propriedade vbcMono.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVBCMono(String value) {
                this.vbcMono = value;
            }

            /**
             * Obtém o valor da propriedade pAliqMonoUF.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPAliqMonoUF() {
                return pAliqMonoUF;
            }

            /**
             * Define o valor da propriedade pAliqMonoUF.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPAliqMonoUF(String value) {
                this.pAliqMonoUF = value;
            }

            /**
             * Obtém o valor da propriedade vibsMonoUF.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBSMonoUF() {
                return vibsMonoUF;
            }

            /**
             * Define o valor da propriedade vibsMonoUF.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBSMonoUF(String value) {
                this.vibsMonoUF = value;
            }

            /**
             * Obtém o valor da propriedade pAliqMonoMun.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPAliqMonoMun() {
                return pAliqMonoMun;
            }

            /**
             * Define o valor da propriedade pAliqMonoMun.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPAliqMonoMun(String value) {
                this.pAliqMonoMun = value;
            }

            /**
             * Obtém o valor da propriedade vibsMonoMun.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBSMonoMun() {
                return vibsMonoMun;
            }

            /**
             * Define o valor da propriedade vibsMonoMun.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBSMonoMun(String value) {
                this.vibsMonoMun = value;
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
         *         &lt;element name="vIBSMonoRet" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vibsMonoRet"
        })
        public static class GMonoRet {

            @XmlElement(name = "vIBSMonoRet", required = true)
            protected String vibsMonoRet;

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
         *         &lt;element name="vBCMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
         *         &lt;element name="pAliqMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
         *         &lt;element name="vIBSMonoReten" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "vbcMonoReten",
            "pAliqMonoReten",
            "vibsMonoReten"
        })
        public static class GMonoReten {

            @XmlElement(name = "vBCMonoReten", required = true)
            protected String vbcMonoReten;
            @XmlElement(required = true)
            protected String pAliqMonoReten;
            @XmlElement(name = "vIBSMonoReten", required = true)
            protected String vibsMonoReten;

            /**
             * Obtém o valor da propriedade vbcMonoReten.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVBCMonoReten() {
                return vbcMonoReten;
            }

            /**
             * Define o valor da propriedade vbcMonoReten.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVBCMonoReten(String value) {
                this.vbcMonoReten = value;
            }

            /**
             * Obtém o valor da propriedade pAliqMonoReten.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPAliqMonoReten() {
                return pAliqMonoReten;
            }

            /**
             * Define o valor da propriedade pAliqMonoReten.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPAliqMonoReten(String value) {
                this.pAliqMonoReten = value;
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
         *         &lt;element name="qBCBioComb" type="{http://www.portalfiscal.inf.br/nfe}TDec1104RTC"/>
         *         &lt;element name="vIBSDiferenca" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
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
            "qbcBioComb",
            "vibsDiferenca"
        })
        public static class GpBioDiferenca {

            @XmlElement(name = "qBCBioComb", required = true)
            protected String qbcBioComb;
            @XmlElement(name = "vIBSDiferenca", required = true)
            protected String vibsDiferenca;

            /**
             * Obtém o valor da propriedade qbcBioComb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQBCBioComb() {
                return qbcBioComb;
            }

            /**
             * Define o valor da propriedade qbcBioComb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQBCBioComb(String value) {
                this.qbcBioComb = value;
            }

            /**
             * Obtém o valor da propriedade vibsDiferenca.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBSDiferenca() {
                return vibsDiferenca;
            }

            /**
             * Define o valor da propriedade vibsDiferenca.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBSDiferenca(String value) {
                this.vibsDiferenca = value;
            }

        }

    }

}
