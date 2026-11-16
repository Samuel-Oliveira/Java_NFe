
package br.com.swconsultoria.nfe.schemas_eventos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Detalhamento dos Tributos por Item
 * 
 * <p>Classe Java de TTribItemSN complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTribItemSN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vRBSNitem" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="tpRBSN" type="{http://www.portalfiscal.inf.br/nfe}TRBSN"/>
 *         &lt;element name="pIBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04" minOccurs="0"/>
 *         &lt;element name="vIBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *         &lt;element name="pCBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04" minOccurs="0"/>
 *         &lt;element name="vCBSSN" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *         &lt;element name="vIBSPendSusp" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *         &lt;element name="vCBSPendSusp" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nItem" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="preserve"/>
 *             &lt;pattern value="[1-9][0-9]?|[1-8][0-9]{2}|9[0-8][0-9]|990"/>
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
@XmlType(name = "TTribItemSN", propOrder = {
    "vrbsNitem",
    "tpRBSN",
    "pibssn",
    "vibssn",
    "pcbssn",
    "vcbssn",
    "vibsPendSusp",
    "vcbsPendSusp"
})
public class TTribItemSN {

    @XmlElement(name = "vRBSNitem", required = true)
    protected String vrbsNitem;
    @XmlElement(required = true)
    protected String tpRBSN;
    @XmlElement(name = "pIBSSN")
    protected String pibssn;
    @XmlElement(name = "vIBSSN")
    protected String vibssn;
    @XmlElement(name = "pCBSSN")
    protected String pcbssn;
    @XmlElement(name = "vCBSSN")
    protected String vcbssn;
    @XmlElement(name = "vIBSPendSusp")
    protected String vibsPendSusp;
    @XmlElement(name = "vCBSPendSusp")
    protected String vcbsPendSusp;
    @XmlAttribute(name = "nItem", required = true)
    protected String nItem;

    /**
     * Obtém o valor da propriedade vrbsNitem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVRBSNitem() {
        return vrbsNitem;
    }

    /**
     * Define o valor da propriedade vrbsNitem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVRBSNitem(String value) {
        this.vrbsNitem = value;
    }

    /**
     * Obtém o valor da propriedade tpRBSN.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpRBSN() {
        return tpRBSN;
    }

    /**
     * Define o valor da propriedade tpRBSN.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpRBSN(String value) {
        this.tpRBSN = value;
    }

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

    /**
     * Obtém o valor da propriedade vibsPendSusp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSPendSusp() {
        return vibsPendSusp;
    }

    /**
     * Define o valor da propriedade vibsPendSusp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSPendSusp(String value) {
        this.vibsPendSusp = value;
    }

    /**
     * Obtém o valor da propriedade vcbsPendSusp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBSPendSusp() {
        return vcbsPendSusp;
    }

    /**
     * Define o valor da propriedade vcbsPendSusp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBSPendSusp(String value) {
        this.vcbsPendSusp = value;
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

}
