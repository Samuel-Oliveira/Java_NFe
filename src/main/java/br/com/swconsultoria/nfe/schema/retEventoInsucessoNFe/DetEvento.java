
package br.com.swconsultoria.nfe.schema.retEventoInsucessoNFe;

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
 *               &lt;enumeration value="Insucesso na Entrega da NF-e"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCOrgaoIBGE"/>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="dhTentativaEntrega" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/>
 *         &lt;element name="nTentativa" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{1,3}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tpMotivo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
 *               &lt;enumeration value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="xJustMotivo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="250"/>
 *               &lt;minLength value="25"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="latGPS" type="{http://www.portalfiscal.inf.br/nfe}TLatitude" minOccurs="0"/>
 *         &lt;element name="longGPS" type="{http://www.portalfiscal.inf.br/nfe}TLongitude" minOccurs="0"/>
 *         &lt;element name="hashTentativaEntrega">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}base64Binary">
 *               &lt;length value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dhHashTentativaEntrega" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC" minOccurs="0"/>
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
    "verAplic",
    "dhTentativaEntrega",
    "nTentativa",
    "tpMotivo",
    "xJustMotivo",
    "latGPS",
    "longGPS",
    "hashTentativaEntrega",
    "dhHashTentativaEntrega"
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
    protected String dhTentativaEntrega;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String nTentativa;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpMotivo;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String xJustMotivo;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String latGPS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String longGPS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected byte[] hashTentativaEntrega;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String dhHashTentativaEntrega;
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
     * Obtém o valor da propriedade dhTentativaEntrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhTentativaEntrega() {
        return dhTentativaEntrega;
    }

    /**
     * Define o valor da propriedade dhTentativaEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDhTentativaEntrega(String value) {
        this.dhTentativaEntrega = value;
    }

    /**
     * Obtém o valor da propriedade nTentativa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNTentativa() {
        return nTentativa;
    }

    /**
     * Define o valor da propriedade nTentativa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNTentativa(String value) {
        this.nTentativa = value;
    }

    /**
     * Obtém o valor da propriedade tpMotivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpMotivo() {
        return tpMotivo;
    }

    /**
     * Define o valor da propriedade tpMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpMotivo(String value) {
        this.tpMotivo = value;
    }

    /**
     * Obtém o valor da propriedade xJustMotivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXJustMotivo() {
        return xJustMotivo;
    }

    /**
     * Define o valor da propriedade xJustMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXJustMotivo(String value) {
        this.xJustMotivo = value;
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
     * Obtém o valor da propriedade hashTentativaEntrega.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHashTentativaEntrega() {
        return hashTentativaEntrega;
    }

    /**
     * Define o valor da propriedade hashTentativaEntrega.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setHashTentativaEntrega(byte[] value) {
        this.hashTentativaEntrega = value;
    }

    /**
     * Obtém o valor da propriedade dhHashTentativaEntrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhHashTentativaEntrega() {
        return dhHashTentativaEntrega;
    }

    /**
     * Define o valor da propriedade dhHashTentativaEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDhHashTentativaEntrega(String value) {
        this.dhHashTentativaEntrega = value;
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
