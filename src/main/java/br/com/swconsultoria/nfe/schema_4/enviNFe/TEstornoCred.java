
package br.com.swconsultoria.nfe.schema_4.enviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Estorno de Crédito
 * 
 * <p>Classe Java de TEstornoCred complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TEstornoCred">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vIBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *         &lt;element name="vCBSEstCred" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEstornoCred", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vibsEstCred",
    "vcbsEstCred"
})
public class TEstornoCred {

    @XmlElement(name = "vIBSEstCred", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vibsEstCred;
    @XmlElement(name = "vCBSEstCred", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vcbsEstCred;

    /**
     * Obtém o valor da propriedade vibsEstCred.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBSEstCred() {
        return vibsEstCred;
    }

    /**
     * Define o valor da propriedade vibsEstCred.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBSEstCred(String value) {
        this.vibsEstCred = value;
    }

    /**
     * Obtém o valor da propriedade vcbsEstCred.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBSEstCred() {
        return vcbsEstCred;
    }

    /**
     * Define o valor da propriedade vcbsEstCred.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBSEstCred(String value) {
        this.vcbsEstCred = value;
    }

}
