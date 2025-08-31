
package br.com.swconsultoria.nfe.schema_4.consReciNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Crédito Presumido
 * 
 * <p>Classe Java de TCredPres complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCredPres">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cCredPres" type="{http://www.portalfiscal.inf.br/nfe}TcCredPres"/>
 *         &lt;element name="pCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;choice>
 *           &lt;element name="vCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *           &lt;element name="vCredPresCondSus" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPres", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "cCredPres",
    "pCredPres",
    "vCredPres",
    "vCredPresCondSus"
})
public class TCredPres {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cCredPres;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pCredPres;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String vCredPres;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String vCredPresCondSus;

    /**
     * Obtém o valor da propriedade cCredPres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCredPres() {
        return cCredPres;
    }

    /**
     * Define o valor da propriedade cCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCredPres(String value) {
        this.cCredPres = value;
    }

    /**
     * Obtém o valor da propriedade pCredPres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCredPres() {
        return pCredPres;
    }

    /**
     * Define o valor da propriedade pCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCredPres(String value) {
        this.pCredPres = value;
    }

    /**
     * Obtém o valor da propriedade vCredPres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPres() {
        return vCredPres;
    }

    /**
     * Define o valor da propriedade vCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCredPres(String value) {
        this.vCredPres = value;
    }

    /**
     * Obtém o valor da propriedade vCredPresCondSus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPresCondSus() {
        return vCredPresCondSus;
    }

    /**
     * Define o valor da propriedade vCredPresCondSus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCredPresCondSus(String value) {
        this.vCredPresCondSus = value;
    }

}
