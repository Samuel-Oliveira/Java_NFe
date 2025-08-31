
package br.com.swconsultoria.nfe.schema_4.retConsReciNFe;

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
 *         &lt;element name="vDevTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDevTrib", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vDevTrib"
})
public class TDevTrib {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vDevTrib;

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
