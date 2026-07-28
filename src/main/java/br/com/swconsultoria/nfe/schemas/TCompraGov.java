
package br.com.swconsultoria.nfe.schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Cada DFe que utilizar deverá utilizar esses tipo no grupo ide
 * 
 * <p>Classe Java de TCompraGov complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCompraGov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tpEnteGov" type="{http://www.portalfiscal.inf.br/nfe}TEnteGov"/>
 *         &lt;element name="pRedutor" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04RTC"/>
 *         &lt;element name="tpOperGov" type="{http://www.portalfiscal.inf.br/nfe}TOperCompraGov"/>
 *         &lt;element name="refDFeAnt" type="{http://www.portalfiscal.inf.br/nfe}TChNFe" maxOccurs="99" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCompraGov", propOrder = {
    "tpEnteGov",
    "pRedutor",
    "tpOperGov",
    "refDFeAnt"
})
public class TCompraGov {

    @XmlElement(required = true)
    protected String tpEnteGov;
    @XmlElement(required = true)
    protected String pRedutor;
    @XmlElement(required = true)
    protected String tpOperGov;
    protected List<String> refDFeAnt;

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

    /**
     * Obtém o valor da propriedade tpOperGov.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpOperGov() {
        return tpOperGov;
    }

    /**
     * Define o valor da propriedade tpOperGov.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpOperGov(String value) {
        this.tpOperGov = value;
    }

    /**
     * Gets the value of the refDFeAnt property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getRefDFeAnt() {
        if (refDFeAnt == null) {
            refDFeAnt = new ArrayList<String>();
        }
        return this.refDFeAnt;
    }

}
