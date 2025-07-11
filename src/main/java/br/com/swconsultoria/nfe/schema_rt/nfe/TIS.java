
package br.com.swconsultoria.nfe.schema_rt.nfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações do Imposto Seletivo
 * 
 * <p>Classe Java de TIS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TIS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CSTIS" type="{http://www.portalfiscal.inf.br/nfe}TCST"/>
 *         &lt;element name="cClassTribIS" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="vBCIS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *           &lt;element name="pIS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *           &lt;element name="pISEspec" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04" minOccurs="0"/>
 *           &lt;sequence minOccurs="0">
 *             &lt;element name="uTrib">
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TStringRTC">
 *                   &lt;minLength value="1"/>
 *                   &lt;maxLength value="6"/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *             &lt;/element>
 *             &lt;element name="qTrib" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104Op"/>
 *           &lt;/sequence>
 *           &lt;element name="vIS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TIS", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "cstis",
    "cClassTribIS",
    "vbcis",
    "pis",
    "pisEspec",
    "uTrib",
    "qTrib",
    "vis"
})
public class TIS {

    @XmlElement(name = "CSTIS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cstis;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cClassTribIS;
    @XmlElement(name = "vBCIS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String vbcis;
    @XmlElement(name = "pIS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String pis;
    @XmlElement(name = "pISEspec", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String pisEspec;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String uTrib;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String qTrib;
    @XmlElement(name = "vIS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String vis;

    /**
     * Obtém o valor da propriedade cstis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSTIS() {
        return cstis;
    }

    /**
     * Define o valor da propriedade cstis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSTIS(String value) {
        this.cstis = value;
    }

    /**
     * Obtém o valor da propriedade cClassTribIS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTribIS() {
        return cClassTribIS;
    }

    /**
     * Define o valor da propriedade cClassTribIS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTribIS(String value) {
        this.cClassTribIS = value;
    }

    /**
     * Obtém o valor da propriedade vbcis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBCIS() {
        return vbcis;
    }

    /**
     * Define o valor da propriedade vbcis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBCIS(String value) {
        this.vbcis = value;
    }

    /**
     * Obtém o valor da propriedade pis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIS() {
        return pis;
    }

    /**
     * Define o valor da propriedade pis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIS(String value) {
        this.pis = value;
    }

    /**
     * Obtém o valor da propriedade pisEspec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPISEspec() {
        return pisEspec;
    }

    /**
     * Define o valor da propriedade pisEspec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPISEspec(String value) {
        this.pisEspec = value;
    }

    /**
     * Obtém o valor da propriedade uTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUTrib() {
        return uTrib;
    }

    /**
     * Define o valor da propriedade uTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUTrib(String value) {
        this.uTrib = value;
    }

    /**
     * Obtém o valor da propriedade qTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQTrib() {
        return qTrib;
    }

    /**
     * Define o valor da propriedade qTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQTrib(String value) {
        this.qTrib = value;
    }

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
