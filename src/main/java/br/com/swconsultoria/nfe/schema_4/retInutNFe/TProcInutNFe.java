
package br.com.swconsultoria.nfe.schema_4.retInutNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Tipo Pedido de inutilzação de númeração de  NF-e processado
 * 
 * <p>Classe Java de TProcInutNFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TProcInutNFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inutNFe" type="{http://www.portalfiscal.inf.br/nfe}TInutNFe"/>
 *         &lt;element name="retInutNFe" type="{http://www.portalfiscal.inf.br/nfe}TRetInutNFe"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerInutNFe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TProcInutNFe", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "inutNFe",
    "retInutNFe"
})
public class TProcInutNFe {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TInutNFe inutNFe;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TRetInutNFe retInutNFe;
    @XmlAttribute(name = "versao", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versao;

    /**
     * Obtém o valor da propriedade inutNFe.
     * 
     * @return
     *     possible object is
     *     {@link TInutNFe }
     *     
     */
    public TInutNFe getInutNFe() {
        return inutNFe;
    }

    /**
     * Define o valor da propriedade inutNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link TInutNFe }
     *     
     */
    public void setInutNFe(TInutNFe value) {
        this.inutNFe = value;
    }

    /**
     * Obtém o valor da propriedade retInutNFe.
     * 
     * @return
     *     possible object is
     *     {@link TRetInutNFe }
     *     
     */
    public TRetInutNFe getRetInutNFe() {
        return retInutNFe;
    }

    /**
     * Define o valor da propriedade retInutNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link TRetInutNFe }
     *     
     */
    public void setRetInutNFe(TRetInutNFe value) {
        this.retInutNFe = value;
    }

    /**
     * Obtém o valor da propriedade versao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
    }

}
