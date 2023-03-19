
package br.com.swconsultoria.nfe.schema_4.retEnviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Grupo de informações do responsável técnico pelo sistema de emissão de DF-e
 * 
 * <p>Classe Java de TInfRespTec complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TInfRespTec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjOpc"/&gt;
 *         &lt;element name="xContato"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
 *               &lt;maxLength value="60"/&gt;
 *               &lt;minLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="email"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;minLength value="6"/&gt;
 *               &lt;maxLength value="60"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="fone"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;pattern value="[0-9]{6,14}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element name="idCSRT"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                 &lt;whiteSpace value="preserve"/&gt;
 *                 &lt;pattern value="[0-9]{2}"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="hashCSRT"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}base64Binary"&gt;
 *                 &lt;length value="20"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInfRespTec", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "cnpj",
    "xContato",
    "email",
    "fone",
    "idCSRT",
    "hashCSRT"
})
public class TInfRespTec {

    @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cnpj;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String xContato;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String email;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String fone;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String idCSRT;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected byte[] hashCSRT;

    /**
     * Obtém o valor da propriedade cnpj.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJ() {
        return cnpj;
    }

    /**
     * Define o valor da propriedade cnpj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJ(String value) {
        this.cnpj = value;
    }

    /**
     * Obtém o valor da propriedade xContato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXContato() {
        return xContato;
    }

    /**
     * Define o valor da propriedade xContato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXContato(String value) {
        this.xContato = value;
    }

    /**
     * Obtém o valor da propriedade email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o valor da propriedade email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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

    /**
     * Obtém o valor da propriedade idCSRT.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCSRT() {
        return idCSRT;
    }

    /**
     * Define o valor da propriedade idCSRT.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCSRT(String value) {
        this.idCSRT = value;
    }

    /**
     * Obtém o valor da propriedade hashCSRT.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHashCSRT() {
        return hashCSRT;
    }

    /**
     * Define o valor da propriedade hashCSRT.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setHashCSRT(byte[] value) {
        this.hashCSRT = value;
    }

}
