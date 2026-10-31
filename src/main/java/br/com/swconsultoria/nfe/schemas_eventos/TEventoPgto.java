
package br.com.swconsultoria.nfe.schemas_eventos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Dados do Pagamento
 * 
 * <p>Classe Java de TEventoPgto complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TEventoPgto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tpMeioPgto">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;pattern value="[0-9]{2}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CNPJReceb" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *         &lt;element name="CNPJBasePSP" type="{http://www.portalfiscal.inf.br/nfe}TCnpjBase"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idTransacao" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *             &lt;minLength value="2"/>
 *             &lt;maxLength value="35"/>
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
@XmlType(name = "TEventoPgto", propOrder = {
    "tpMeioPgto",
    "cnpjReceb",
    "cnpjBasePSP"
})
public class TEventoPgto {

    @XmlElement(required = true)
    protected String tpMeioPgto;
    @XmlElement(name = "CNPJReceb", required = true)
    protected String cnpjReceb;
    @XmlElement(name = "CNPJBasePSP", required = true)
    protected String cnpjBasePSP;
    @XmlAttribute(name = "idTransacao", required = true)
    protected String idTransacao;

    /**
     * Obtém o valor da propriedade tpMeioPgto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpMeioPgto() {
        return tpMeioPgto;
    }

    /**
     * Define o valor da propriedade tpMeioPgto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpMeioPgto(String value) {
        this.tpMeioPgto = value;
    }

    /**
     * Obtém o valor da propriedade cnpjReceb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJReceb() {
        return cnpjReceb;
    }

    /**
     * Define o valor da propriedade cnpjReceb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJReceb(String value) {
        this.cnpjReceb = value;
    }

    /**
     * Obtém o valor da propriedade cnpjBasePSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJBasePSP() {
        return cnpjBasePSP;
    }

    /**
     * Define o valor da propriedade cnpjBasePSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJBasePSP(String value) {
        this.cnpjBasePSP = value;
    }

    /**
     * Obtém o valor da propriedade idTransacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTransacao() {
        return idTransacao;
    }

    /**
     * Define o valor da propriedade idTransacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTransacao(String value) {
        this.idTransacao = value;
    }

}
