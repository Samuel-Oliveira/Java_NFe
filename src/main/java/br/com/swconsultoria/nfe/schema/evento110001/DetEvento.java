
package br.com.swconsultoria.nfe.schema.evento110001;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descEvento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Cancelamento de Evento"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="tpEventoAut">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="112110"/>
 *               &lt;enumeration value="112120"/>
 *               &lt;enumeration value="112130"/>
 *               &lt;enumeration value="112140"/>
 *               &lt;enumeration value="112150"/>
 *               &lt;enumeration value="211110"/>
 *               &lt;enumeration value="211120"/>
 *               &lt;enumeration value="211124"/>
 *               &lt;enumeration value="211128"/>
 *               &lt;enumeration value="211130"/>
 *               &lt;enumeration value="211140"/>
 *               &lt;enumeration value="211150"/>
 *               &lt;enumeration value="212110"/>
 *               &lt;enumeration value="212120"/>
 *               &lt;enumeration value="412120"/>
 *               &lt;enumeration value="412130"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nProtEvento" type="{http://www.portalfiscal.inf.br/nfe}TProt"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="preserve"/>
 *             &lt;enumeration value="1.00"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "descEvento",
    "cOrgaoAutor",
    "verAplic",
    "tpEventoAut",
    "nProtEvento"
})
@XmlRootElement(name = "detEvento", namespace = "http://www.portalfiscal.inf.br/nfe")
public class DetEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cOrgaoAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpEventoAut;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String nProtEvento;
    @XmlAttribute(name = "versao")
    protected String versao;

    /**
     * Obtém o valor da propriedade descEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEvento() {
        return descEvento;
    }

    /**
     * Define o valor da propriedade descEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEvento(String value) {
        this.descEvento = value;
    }

    /**
     * Obtém o valor da propriedade cOrgaoAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOrgaoAutor() {
        return cOrgaoAutor;
    }

    /**
     * Define o valor da propriedade cOrgaoAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOrgaoAutor(String value) {
        this.cOrgaoAutor = value;
    }

    /**
     * Obtém o valor da propriedade verAplic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerAplic() {
        return verAplic;
    }

    /**
     * Define o valor da propriedade verAplic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerAplic(String value) {
        this.verAplic = value;
    }

    /**
     * Obtém o valor da propriedade tpEventoAut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpEventoAut() {
        return tpEventoAut;
    }

    /**
     * Define o valor da propriedade tpEventoAut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpEventoAut(String value) {
        this.tpEventoAut = value;
    }

    /**
     * Obtém o valor da propriedade nProtEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNProtEvento() {
        return nProtEvento;
    }

    /**
     * Define o valor da propriedade nProtEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNProtEvento(String value) {
        this.nProtEvento = value;
    }

    /**
     * Obtém o valor da propriedade versao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
    }

}
