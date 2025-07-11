
package br.com.swconsultoria.nfe.schema_rt.nfe;

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
 *         &lt;element name="pDif" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vDif" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDif", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "pDif",
    "vDif"
})
public class TDif {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pDif;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
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
