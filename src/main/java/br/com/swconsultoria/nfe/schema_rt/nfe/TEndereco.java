
package br.com.swconsultoria.nfe.schema_rt.nfe;

import javax.xml.bind.annotation.*;


/**
 * Tipo Dados do Endereço  // 24/10/08 - tamanho mínimo
 * 
 * <p>Classe Java de TEndereco complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TEndereco">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xLgr">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nro">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="xCpl" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="xBairro">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cMun" type="{http://www.portalfiscal.inf.br/nfe}TCodMunIBGE"/>
 *         &lt;element name="xMun">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUf"/>
 *         &lt;element name="CEP" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;pattern value="[0-9]{8}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cPais" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;pattern value="[0-9]{1,4}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="xPais" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fone" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;pattern value="[0-9]{6,14}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEndereco", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "xLgr",
    "nro",
    "xCpl",
    "xBairro",
    "cMun",
    "xMun",
    "uf",
    "cep",
    "cPais",
    "xPais",
    "fone"
})
public class TEndereco {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String xLgr;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String nro;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String xCpl;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String xBairro;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cMun;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String xMun;
    @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    @XmlSchemaType(name = "string")
    protected TUf uf;
    @XmlElement(name = "CEP", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cep;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cPais;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String xPais;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String fone;

    /**
     * Obtém o valor da propriedade xLgr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXLgr() {
        return xLgr;
    }

    /**
     * Define o valor da propriedade xLgr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXLgr(String value) {
        this.xLgr = value;
    }

    /**
     * Obtém o valor da propriedade nro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNro() {
        return nro;
    }

    /**
     * Define o valor da propriedade nro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNro(String value) {
        this.nro = value;
    }

    /**
     * Obtém o valor da propriedade xCpl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXCpl() {
        return xCpl;
    }

    /**
     * Define o valor da propriedade xCpl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXCpl(String value) {
        this.xCpl = value;
    }

    /**
     * Obtém o valor da propriedade xBairro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXBairro() {
        return xBairro;
    }

    /**
     * Define o valor da propriedade xBairro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXBairro(String value) {
        this.xBairro = value;
    }

    /**
     * Obtém o valor da propriedade cMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMun() {
        return cMun;
    }

    /**
     * Define o valor da propriedade cMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMun(String value) {
        this.cMun = value;
    }

    /**
     * Obtém o valor da propriedade xMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMun() {
        return xMun;
    }

    /**
     * Define o valor da propriedade xMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMun(String value) {
        this.xMun = value;
    }

    /**
     * Obtém o valor da propriedade uf.
     * 
     * @return
     *     possible object is
     *     {@link TUf }
     *     
     */
    public TUf getUF() {
        return uf;
    }

    /**
     * Define o valor da propriedade uf.
     * 
     * @param value
     *     allowed object is
     *     {@link TUf }
     *     
     */
    public void setUF(TUf value) {
        this.uf = value;
    }

    /**
     * Obtém o valor da propriedade cep.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCEP() {
        return cep;
    }

    /**
     * Define o valor da propriedade cep.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCEP(String value) {
        this.cep = value;
    }

    /**
     * Obtém o valor da propriedade cPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPais() {
        return cPais;
    }

    /**
     * Define o valor da propriedade cPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPais(String value) {
        this.cPais = value;
    }

    /**
     * Obtém o valor da propriedade xPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPais() {
        return xPais;
    }

    /**
     * Define o valor da propriedade xPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPais(String value) {
        this.xPais = value;
    }

    /**
     * Obtém o valor da propriedade fone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFone() {
        return fone;
    }

    /**
     * Define o valor da propriedade fone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFone(String value) {
        this.fone = value;
    }

}
