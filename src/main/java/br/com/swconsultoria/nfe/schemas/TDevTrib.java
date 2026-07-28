
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Devolução Tributo
 * 
 * <p>Classe Java de TDevTrib complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TDevTrib">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec0302a04RTC" minOccurs="0"/>
 *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDevTrib", propOrder = {
    "pDevTrib",
    "vDevTrib"
})
public class TDevTrib {

    protected String pDevTrib;
    @XmlElement(required = true)
    protected String vDevTrib;

    /**
     * Obtém o valor da propriedade pDevTrib.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPDevTrib() {
        return pDevTrib;
    }

    /**
     * Define o valor da propriedade pDevTrib.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPDevTrib(String value) {
        this.pDevTrib = value;
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

}
