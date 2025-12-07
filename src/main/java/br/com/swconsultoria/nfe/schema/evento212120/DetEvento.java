
package br.com.swconsultoria.nfe.schema.evento212120;

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
 *               &lt;enumeration value="Manifestação sobre Pedido de Transferência de Crédito de CBS em Operação de Sucessão"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *         &lt;element name="tpAutor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="indAceitacao">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "tpAutor",
    "verAplic",
    "indAceitacao"
})
@XmlRootElement(name = "detEvento", namespace = "http://www.portalfiscal.inf.br/nfe")
public class DetEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cOrgaoAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String indAceitacao;
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
     * Obtém o valor da propriedade tpAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpAutor() {
        return tpAutor;
    }

    /**
     * Define o valor da propriedade tpAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpAutor(String value) {
        this.tpAutor = value;
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
     * Obtém o valor da propriedade indAceitacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndAceitacao() {
        return indAceitacao;
    }

    /**
     * Define o valor da propriedade indAceitacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndAceitacao(String value) {
        this.indAceitacao = value;
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
