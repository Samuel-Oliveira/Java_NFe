
package br.com.swconsultoria.nfe.schema_4.enviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Cada DFe que utilizar deverá utilizar esses tipo no grupo ide
 * 
 * <p>Classe Java de TCompraGovReduzido complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCompraGovReduzido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tpEnteGov" type="{http://www.portalfiscal.inf.br/nfe}TEnteGov"/>
 *         &lt;element name="pRedutor" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCompraGovReduzido", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "tpEnteGov",
    "pRedutor"
})
public class TCompraGovReduzido {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpEnteGov;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pRedutor;

    /**
     * Obtém o valor da propriedade tpEnteGov.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpEnteGov() {
        return tpEnteGov;
    }

    /**
     * Define o valor da propriedade tpEnteGov.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpEnteGov(String value) {
        this.tpEnteGov = value;
    }

    /**
     * Obtém o valor da propriedade pRedutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRedutor() {
        return pRedutor;
    }

    /**
     * Define o valor da propriedade pRedutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRedutor(String value) {
        this.pRedutor = value;
    }

}
