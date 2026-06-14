
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Diferimento
 * 
 * <p>Classe Java de TDif complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TDif">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pDif" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDif", propOrder = {
    "pDif",
    "vDif"
})
public class TDif {

    @XmlElement(required = true)
    protected String pDif;
    @XmlElement(required = true)
    protected String vDif;

    /**
     * Obtém o valor da propriedade pDif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDif() {
        return pDif;
    }

    /**
     * Define o valor da propriedade pDif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDif(String value) {
        this.pDif = value;
    }

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

}
