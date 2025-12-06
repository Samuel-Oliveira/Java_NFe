
package br.com.swconsultoria.nfe.schema_4.enviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações da Tributação da NFGas
 * 
 * <p>Classe Java de TTribNFGas complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTribNFGas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CST" type="{http://www.portalfiscal.inf.br/nfe}TCST"/>
 *         &lt;element name="cClassTrib" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/>
 *         &lt;element name="indDoacao" type="{http://www.portalfiscal.inf.br/nfe}TIndDoacao" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="gIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TCIBS"/>
 *           &lt;element name="gIBSCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TMonofasia"/>
 *         &lt;/choice>
 *         &lt;element name="gEstornoCred" type="{http://www.portalfiscal.inf.br/nfe}TEstornoCred" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribNFGas", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "cst",
    "cClassTrib",
    "indDoacao",
    "gibscbs",
    "gibscbsMono",
    "gEstornoCred"
})
public class TTribNFGas {

    @XmlElement(name = "CST", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cst;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cClassTrib;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String indDoacao;
    @XmlElement(name = "gIBSCBS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCIBS gibscbs;
    @XmlElement(name = "gIBSCBSMono", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TMonofasia gibscbsMono;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TEstornoCred gEstornoCred;

    /**
     * Obtém o valor da propriedade cst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCST() {
        return cst;
    }

    /**
     * Define o valor da propriedade cst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCST(String value) {
        this.cst = value;
    }

    /**
     * Obtém o valor da propriedade cClassTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCClassTrib() {
        return cClassTrib;
    }

    /**
     * Define o valor da propriedade cClassTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCClassTrib(String value) {
        this.cClassTrib = value;
    }

    /**
     * Obtém o valor da propriedade indDoacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndDoacao() {
        return indDoacao;
    }

    /**
     * Define o valor da propriedade indDoacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndDoacao(String value) {
        this.indDoacao = value;
    }

    /**
     * Obtém o valor da propriedade gibscbs.
     * 
     * @return
     *     possible object is
     *     {@link TCIBS }
     *     
     */
    public TCIBS getGIBSCBS() {
        return gibscbs;
    }

    /**
     * Define o valor da propriedade gibscbs.
     * 
     * @param value
     *     allowed object is
     *     {@link TCIBS }
     *     
     */
    public void setGIBSCBS(TCIBS value) {
        this.gibscbs = value;
    }

    /**
     * Obtém o valor da propriedade gibscbsMono.
     * 
     * @return
     *     possible object is
     *     {@link TMonofasia }
     *     
     */
    public TMonofasia getGIBSCBSMono() {
        return gibscbsMono;
    }

    /**
     * Define o valor da propriedade gibscbsMono.
     * 
     * @param value
     *     allowed object is
     *     {@link TMonofasia }
     *     
     */
    public void setGIBSCBSMono(TMonofasia value) {
        this.gibscbsMono = value;
    }

    /**
     * Obtém o valor da propriedade gEstornoCred.
     * 
     * @return
     *     possible object is
     *     {@link TEstornoCred }
     *     
     */
    public TEstornoCred getGEstornoCred() {
        return gEstornoCred;
    }

    /**
     * Define o valor da propriedade gEstornoCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TEstornoCred }
     *     
     */
    public void setGEstornoCred(TEstornoCred value) {
        this.gEstornoCred = value;
    }

}
