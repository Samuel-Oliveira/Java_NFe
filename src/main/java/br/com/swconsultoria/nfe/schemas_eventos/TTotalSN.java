
package br.com.swconsultoria.nfe.schemas_eventos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Dados de Totais do SN
 * 
 * <p>Classe Java de TTotalSN complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTotalSN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vRBSNtot" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="vIBSSNtot" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *         &lt;element name="vIBSSNtotPendSusp" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *         &lt;element name="vCBSSNtot" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *         &lt;element name="vCBSSNtotPendSusp" type="{http://www.portalfiscal.inf.br/nfe}TDec1302" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTotalSN", propOrder = {
    "vrbsNtot",
    "vibssNtot",
    "vibssNtotPendSusp",
    "vcbssNtot",
    "vcbssNtotPendSusp"
})
public class TTotalSN {

    @XmlElement(name = "vRBSNtot", required = true)
    protected String vrbsNtot;
    @XmlElement(name = "vIBSSNtot")
    protected String vibssNtot;
    @XmlElement(name = "vIBSSNtotPendSusp")
    protected String vibssNtotPendSusp;
    @XmlElement(name = "vCBSSNtot")
    protected String vcbssNtot;
    @XmlElement(name = "vCBSSNtotPendSusp")
    protected String vcbssNtotPendSusp;

    /**
     * Obtém o valor da propriedade vrbsNtot.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVRBSNtot() {
        return vrbsNtot;
    }

    /**
     * Define o valor da propriedade vrbsNtot.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVRBSNtot(String value) {
        this.vrbsNtot = value;
    }

    /**
     * Obtém o valor da propriedade vibssNtot.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSSNtot() {
        return vibssNtot;
    }

    /**
     * Define o valor da propriedade vibssNtot.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSSNtot(String value) {
        this.vibssNtot = value;
    }

    /**
     * Obtém o valor da propriedade vibssNtotPendSusp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSSNtotPendSusp() {
        return vibssNtotPendSusp;
    }

    /**
     * Define o valor da propriedade vibssNtotPendSusp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSSNtotPendSusp(String value) {
        this.vibssNtotPendSusp = value;
    }

    /**
     * Obtém o valor da propriedade vcbssNtot.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBSSNtot() {
        return vcbssNtot;
    }

    /**
     * Define o valor da propriedade vcbssNtot.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBSSNtot(String value) {
        this.vcbssNtot = value;
    }

    /**
     * Obtém o valor da propriedade vcbssNtotPendSusp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBSSNtotPendSusp() {
        return vcbssNtotPendSusp;
    }

    /**
     * Define o valor da propriedade vcbssNtotPendSusp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBSSNtotPendSusp(String value) {
        this.vcbssNtotPendSusp = value;
    }

}
