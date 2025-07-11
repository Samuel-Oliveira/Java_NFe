
package br.com.swconsultoria.nfe.schema_rt.nfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações da Tributação da NFe
 * 
 * <p>Classe Java de TTribNFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TTribNFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CST" type="{http://www.portalfiscal.inf.br/nfe}TCST"/>
 *         &lt;element name="cClassTrib" type="{http://www.portalfiscal.inf.br/nfe}TcClassTrib"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="gIBSCBS" type="{http://www.portalfiscal.inf.br/nfe}TCIBS"/>
 *           &lt;element name="gIBSCBSMono" type="{http://www.portalfiscal.inf.br/nfe}TMonofasia"/>
 *           &lt;element name="gTransfCred" type="{http://www.portalfiscal.inf.br/nfe}TTransfCred"/>
 *         &lt;/choice>
 *         &lt;element name="gCredPresIBSZFM" type="{http://www.portalfiscal.inf.br/nfe}TCredPresIBSZFM" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTribNFe", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "cst",
    "cClassTrib",
    "gibscbs",
    "gibscbsMono",
    "gTransfCred",
    "gCredPresIBSZFM"
})
public class TTribNFe {

    @XmlElement(name = "CST", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cst;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cClassTrib;
    @XmlElement(name = "gIBSCBS", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCIBS gibscbs;
    @XmlElement(name = "gIBSCBSMono", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TMonofasia gibscbsMono;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TTransfCred gTransfCred;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TCredPresIBSZFM gCredPresIBSZFM;

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
     * Obtém o valor da propriedade gTransfCred.
     * 
     * @return
     *     possible object is
     *     {@link TTransfCred }
     *     
     */
    public TTransfCred getGTransfCred() {
        return gTransfCred;
    }

    /**
     * Define o valor da propriedade gTransfCred.
     * 
     * @param value
     *     allowed object is
     *     {@link TTransfCred }
     *     
     */
    public void setGTransfCred(TTransfCred value) {
        this.gTransfCred = value;
    }

    /**
     * Obtém o valor da propriedade gCredPresIBSZFM.
     * 
     * @return
     *     possible object is
     *     {@link TCredPresIBSZFM }
     *     
     */
    public TCredPresIBSZFM getGCredPresIBSZFM() {
        return gCredPresIBSZFM;
    }

    /**
     * Define o valor da propriedade gCredPresIBSZFM.
     * 
     * @param value
     *     allowed object is
     *     {@link TCredPresIBSZFM }
     *     
     */
    public void setGCredPresIBSZFM(TCredPresIBSZFM value) {
        this.gCredPresIBSZFM = value;
    }

}
