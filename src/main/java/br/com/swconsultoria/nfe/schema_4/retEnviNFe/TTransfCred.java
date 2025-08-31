
package br.com.swconsultoria.nfe.schema_4.retEnviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Transferência de Crédito
 * 
 * <p>Classe Java de TTransfCred complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTransfCred">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTransfCred", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vibs",
    "vcbs"
})
public class TTransfCred {

    @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vibs;
    @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vcbs;

    /**
     * Obtém o valor da propriedade vibs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIBS() {
        return vibs;
    }

    /**
     * Define o valor da propriedade vibs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIBS(String value) {
        this.vibs = value;
    }

    /**
     * Obtém o valor da propriedade vcbs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCBS() {
        return vcbs;
    }

    /**
     * Define o valor da propriedade vcbs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCBS(String value) {
        this.vcbs = value;
    }

}
