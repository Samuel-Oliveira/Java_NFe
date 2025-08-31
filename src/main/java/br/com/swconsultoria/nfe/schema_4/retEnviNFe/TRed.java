
package br.com.swconsultoria.nfe.schema_4.retEnviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Redução Base de Cálculo
 * 
 * <p>Classe Java de TRed complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TRed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pRedAliq" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="pAliqEfet" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRed", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "pRedAliq",
    "pAliqEfet"
})
public class TRed {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pRedAliq;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqEfet;

    /**
     * Obtém o valor da propriedade pRedAliq.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedAliq() {
        return pRedAliq;
    }

    /**
     * Define o valor da propriedade pRedAliq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRedAliq(String value) {
        this.pRedAliq = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfet.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfet() {
        return pAliqEfet;
    }

    /**
     * Define o valor da propriedade pAliqEfet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfet(String value) {
        this.pAliqEfet = value;
    }

}
