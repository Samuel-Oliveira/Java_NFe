
package br.com.swconsultoria.nfe.schema.eventoSuframaVistoria;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpModal"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}xIdent" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tpModal",
    "xIdent"
})
@XmlRootElement(name = "modalOutro", namespace = "http://www.portalfiscal.inf.br/nfe")
public class ModalOutro {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpModal;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String xIdent;

    /**
     * Obtém o valor da propriedade tpModal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpModal() {
        return tpModal;
    }

    /**
     * Define o valor da propriedade tpModal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpModal(String value) {
        this.tpModal = value;
    }

    /**
     * Obtém o valor da propriedade xIdent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXIdent() {
        return xIdent;
    }

    /**
     * Define o valor da propriedade xIdent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXIdent(String value) {
        this.xIdent = value;
    }

}
