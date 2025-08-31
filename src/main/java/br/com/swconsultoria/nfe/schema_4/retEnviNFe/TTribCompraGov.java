
package br.com.swconsultoria.nfe.schema_4.retEnviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Tributação Compra Governamental
 * 
 * <p>Classe Java de TTribCompraGov complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTribCompraGov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pAliqIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vTribIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="pAliqIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vTribIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="pAliqCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vTribCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribCompraGov", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "pAliqIBSUF",
    "vTribIBSUF",
    "pAliqIBSMun",
    "vTribIBSMun",
    "pAliqCBS",
    "vTribCBS"
})
public class TTribCompraGov {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqIBSUF;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTribIBSUF;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqIBSMun;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTribIBSMun;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqCBS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTribCBS;

    /**
     * Obtém o valor da propriedade pAliqIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqIBSUF() {
        return pAliqIBSUF;
    }

    /**
     * Define o valor da propriedade pAliqIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqIBSUF(String value) {
        this.pAliqIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade vTribIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribIBSUF() {
        return vTribIBSUF;
    }

    /**
     * Define o valor da propriedade vTribIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribIBSUF(String value) {
        this.vTribIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade pAliqIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqIBSMun() {
        return pAliqIBSMun;
    }

    /**
     * Define o valor da propriedade pAliqIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqIBSMun(String value) {
        this.pAliqIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade vTribIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribIBSMun() {
        return vTribIBSMun;
    }

    /**
     * Define o valor da propriedade vTribIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribIBSMun(String value) {
        this.vTribIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade pAliqCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqCBS() {
        return pAliqCBS;
    }

    /**
     * Define o valor da propriedade pAliqCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqCBS(String value) {
        this.pAliqCBS = value;
    }

    /**
     * Obtém o valor da propriedade vTribCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribCBS() {
        return vTribCBS;
    }

    /**
     * Define o valor da propriedade vTribCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribCBS(String value) {
        this.vTribCBS = value;
    }

}
