
package br.com.swconsultoria.nfe.schema_rt.nfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações de totais do Imposto Seletivo
 * 
 * <p>Classe Java de TISTot complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TISTot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vIS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TISTot", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vis"
})
public class TISTot {

    @XmlElement(name = "vIS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vis;

    /**
     * Obtém o valor da propriedade vis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIS() {
        return vis;
    }

    /**
     * Define o valor da propriedade vis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIS(String value) {
        this.vis = value;
    }

}
