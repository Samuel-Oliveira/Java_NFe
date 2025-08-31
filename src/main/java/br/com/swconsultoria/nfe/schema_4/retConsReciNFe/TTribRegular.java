
package br.com.swconsultoria.nfe.schema_4.retConsReciNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Tributação Regular
 * 
 * <p>Classe Java de TTribRegular complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTribRegular">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CSTReg" type="{http://www.portalfiscal.inf.br/nfe}TCST"/>
 *         &lt;element name="cClassTribReg" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/>
 *         &lt;element name="pAliqEfetRegIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vTribRegIBSUF" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="pAliqEfetRegIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vTribRegIBSMun" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *         &lt;element name="pAliqEfetRegCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_0302_04"/>
 *         &lt;element name="vTribRegCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec1302"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribRegular", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "cstReg",
    "cClassTribReg",
    "pAliqEfetRegIBSUF",
    "vTribRegIBSUF",
    "pAliqEfetRegIBSMun",
    "vTribRegIBSMun",
    "pAliqEfetRegCBS",
    "vTribRegCBS"
})
public class TTribRegular {

    @XmlElement(name = "CSTReg", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cstReg;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cClassTribReg;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqEfetRegIBSUF;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTribRegIBSUF;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqEfetRegIBSMun;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTribRegIBSMun;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String pAliqEfetRegCBS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTribRegCBS;

    /**
     * Obtém o valor da propriedade cstReg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSTReg() {
        return cstReg;
    }

    /**
     * Define o valor da propriedade cstReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSTReg(String value) {
        this.cstReg = value;
    }

    /**
     * Obtém o valor da propriedade cClassTribReg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTribReg() {
        return cClassTribReg;
    }

    /**
     * Define o valor da propriedade cClassTribReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTribReg(String value) {
        this.cClassTribReg = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfetRegIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetRegIBSUF() {
        return pAliqEfetRegIBSUF;
    }

    /**
     * Define o valor da propriedade pAliqEfetRegIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfetRegIBSUF(String value) {
        this.pAliqEfetRegIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade vTribRegIBSUF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegIBSUF() {
        return vTribRegIBSUF;
    }

    /**
     * Define o valor da propriedade vTribRegIBSUF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribRegIBSUF(String value) {
        this.vTribRegIBSUF = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfetRegIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetRegIBSMun() {
        return pAliqEfetRegIBSMun;
    }

    /**
     * Define o valor da propriedade pAliqEfetRegIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfetRegIBSMun(String value) {
        this.pAliqEfetRegIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade vTribRegIBSMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegIBSMun() {
        return vTribRegIBSMun;
    }

    /**
     * Define o valor da propriedade vTribRegIBSMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribRegIBSMun(String value) {
        this.vTribRegIBSMun = value;
    }

    /**
     * Obtém o valor da propriedade pAliqEfetRegCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAliqEfetRegCBS() {
        return pAliqEfetRegCBS;
    }

    /**
     * Define o valor da propriedade pAliqEfetRegCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAliqEfetRegCBS(String value) {
        this.pAliqEfetRegCBS = value;
    }

    /**
     * Obtém o valor da propriedade vTribRegCBS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTribRegCBS() {
        return vTribRegCBS;
    }

    /**
     * Define o valor da propriedade vTribRegCBS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTribRegCBS(String value) {
        this.vTribRegCBS = value;
    }

}
