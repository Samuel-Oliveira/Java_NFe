
package br.com.swconsultoria.nfe.schema_4.consReciNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Crédito Presumido da Operação
 * 
 * <p>Classe Java de TCredPresOper complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCredPresOper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vBCCredPres" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *         &lt;element name="cCredPres" type="{http://www.portalfiscal.inf.br/nfe}TcCredPres"/>
 *         &lt;element name="gIBSCredPres" type="{http://www.portalfiscal.inf.br/nfe}TCredPres" minOccurs="0"/>
 *         &lt;element name="gCBSCredPres" type="{http://www.portalfiscal.inf.br/nfe}TCredPres" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPresOper", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "vbcCredPres",
    "cCredPres",
    "gibsCredPres",
    "gcbsCredPres"
})
public class TCredPresOper {

    @XmlElement(name = "vBCCredPres", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vbcCredPres;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cCredPres;
    @XmlElement(name = "gIBSCredPres", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCredPres gibsCredPres;
    @XmlElement(name = "gCBSCredPres", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCredPres gcbsCredPres;

    /**
     * Obtém o valor da propriedade vbcCredPres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBCCredPres() {
        return vbcCredPres;
    }

    /**
     * Define o valor da propriedade vbcCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBCCredPres(String value) {
        this.vbcCredPres = value;
    }

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
     * Obtém o valor da propriedade gibsCredPres.
     * 
     * @return
     *     possible object is
     *     {@link TCredPres }
     *     
     */
    public TCredPres getGIBSCredPres() {
        return gibsCredPres;
    }

    /**
     * Define o valor da propriedade gibsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPres }
     *     
     */
    public void setGIBSCredPres(TCredPres value) {
        this.gibsCredPres = value;
    }

    /**
     * Obtém o valor da propriedade gcbsCredPres.
     * 
     * @return
     *     possible object is
     *     {@link TCredPres }
     *     
     */
    public TCredPres getGCBSCredPres() {
        return gcbsCredPres;
    }

    /**
     * Define o valor da propriedade gcbsCredPres.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPres }
     *     
     */
    public void setGCBSCredPres(TCredPres value) {
        this.gcbsCredPres = value;
    }

}
