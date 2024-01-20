
package br.com.swconsultoria.nfe.schema.eventoSuframaInternaliza;

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
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}placaVeic" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UFVeic" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}placaCarreta" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UFCarreta" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}placaCarreta2" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UFCarreta2" minOccurs="0"/&gt;
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
    "placaVeic",
    "ufVeic",
    "placaCarreta",
    "ufCarreta",
    "placaCarreta2",
    "ufCarreta2"
})
@XmlRootElement(name = "modalRodov", namespace = "http://www.portalfiscal.inf.br/nfe")
public class ModalRodov {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String placaVeic;
    @XmlElement(name = "UFVeic", namespace = "http://www.portalfiscal.inf.br/nfe")
    @XmlSchemaType(name = "string")
    protected TUf ufVeic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String placaCarreta;
    @XmlElement(name = "UFCarreta", namespace = "http://www.portalfiscal.inf.br/nfe")
    @XmlSchemaType(name = "string")
    protected TUf ufCarreta;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String placaCarreta2;
    @XmlElement(name = "UFCarreta2", namespace = "http://www.portalfiscal.inf.br/nfe")
    @XmlSchemaType(name = "string")
    protected TUf ufCarreta2;

    /**
     * Obtém o valor da propriedade placaVeic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacaVeic() {
        return placaVeic;
    }

    /**
     * Define o valor da propriedade placaVeic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacaVeic(String value) {
        this.placaVeic = value;
    }

    /**
     * Obtém o valor da propriedade ufVeic.
     * 
     * @return
     *     possible object is
     *     {@link TUf }
     *     
     */
    public TUf getUFVeic() {
        return ufVeic;
    }

    /**
     * Define o valor da propriedade ufVeic.
     * 
     * @param value
     *     allowed object is
     *     {@link TUf }
     *     
     */
    public void setUFVeic(TUf value) {
        this.ufVeic = value;
    }

    /**
     * Obtém o valor da propriedade placaCarreta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacaCarreta() {
        return placaCarreta;
    }

    /**
     * Define o valor da propriedade placaCarreta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacaCarreta(String value) {
        this.placaCarreta = value;
    }

    /**
     * Obtém o valor da propriedade ufCarreta.
     * 
     * @return
     *     possible object is
     *     {@link TUf }
     *     
     */
    public TUf getUFCarreta() {
        return ufCarreta;
    }

    /**
     * Define o valor da propriedade ufCarreta.
     * 
     * @param value
     *     allowed object is
     *     {@link TUf }
     *     
     */
    public void setUFCarreta(TUf value) {
        this.ufCarreta = value;
    }

    /**
     * Obtém o valor da propriedade placaCarreta2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacaCarreta2() {
        return placaCarreta2;
    }

    /**
     * Define o valor da propriedade placaCarreta2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacaCarreta2(String value) {
        this.placaCarreta2 = value;
    }

    /**
     * Obtém o valor da propriedade ufCarreta2.
     * 
     * @return
     *     possible object is
     *     {@link TUf }
     *     
     */
    public TUf getUFCarreta2() {
        return ufCarreta2;
    }

    /**
     * Define o valor da propriedade ufCarreta2.
     * 
     * @param value
     *     allowed object is
     *     {@link TUf }
     *     
     */
    public void setUFCarreta2(TUf value) {
        this.ufCarreta2 = value;
    }

}
