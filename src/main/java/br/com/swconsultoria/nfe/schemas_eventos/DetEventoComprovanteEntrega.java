
package br.com.swconsultoria.nfe.schemas_eventos;

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
 *               &lt;enumeration value="Comprovante de Entrega da NF-e"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCOrgaoIBGE"/>
 *         &lt;element name="tpAutor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="dhEntrega" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/>
 *         &lt;element name="nDoc">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="2"/>
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="xNome">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="latGPS" type="{http://www.portalfiscal.inf.br/nfe}TLatitude" minOccurs="0"/>
 *         &lt;element name="longGPS" type="{http://www.portalfiscal.inf.br/nfe}TLongitude" minOccurs="0"/>
 *         &lt;element name="hashComprovante">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}base64Binary">
 *               &lt;length value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dhHashComprovante" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required">
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
    "dhEntrega",
    "nDoc",
    "xNome",
    "latGPS",
    "longGPS",
    "hashComprovante",
    "dhHashComprovante"
})
public class DetEventoComprovanteEntrega {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cOrgaoAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String dhEntrega;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String nDoc;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String xNome;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String latGPS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String longGPS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected byte[] hashComprovante;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String dhHashComprovante;
    @XmlAttribute(name = "versao", required = true)
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
     * Obtém o valor da propriedade dhEntrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhEntrega() {
        return dhEntrega;
    }

    /**
     * Define o valor da propriedade dhEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDhEntrega(String value) {
        this.dhEntrega = value;
    }

    /**
     * Obtém o valor da propriedade nDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNDoc() {
        return nDoc;
    }

    /**
     * Define o valor da propriedade nDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNDoc(String value) {
        this.nDoc = value;
    }

    /**
     * Obtém o valor da propriedade xNome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXNome() {
        return xNome;
    }

    /**
     * Define o valor da propriedade xNome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXNome(String value) {
        this.xNome = value;
    }

    /**
     * Obtém o valor da propriedade latGPS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatGPS() {
        return latGPS;
    }

    /**
     * Define o valor da propriedade latGPS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatGPS(String value) {
        this.latGPS = value;
    }

    /**
     * Obtém o valor da propriedade longGPS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongGPS() {
        return longGPS;
    }

    /**
     * Define o valor da propriedade longGPS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongGPS(String value) {
        this.longGPS = value;
    }

    /**
     * Obtém o valor da propriedade hashComprovante.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHashComprovante() {
        return hashComprovante;
    }

    /**
     * Define o valor da propriedade hashComprovante.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setHashComprovante(byte[] value) {
        this.hashComprovante = value;
    }

    /**
     * Obtém o valor da propriedade dhHashComprovante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhHashComprovante() {
        return dhHashComprovante;
    }

    /**
     * Define o valor da propriedade dhHashComprovante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDhHashComprovante(String value) {
        this.dhHashComprovante = value;
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
