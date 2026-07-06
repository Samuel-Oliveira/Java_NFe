
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Tipo Informações do crédito presumido de IBS para fornecimentos a partir da ZFM
 * 
 * <p>Classe Java de TCredPresIBSZFM complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TCredPresIBSZFM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="competApur" type="{http://www.portalfiscal.inf.br/nfe}TCompetApur"/>
 *         &lt;element name="tpCredPresIBSZFM" type="{http://www.portalfiscal.inf.br/nfe}TTpCredPresIBSZFM"/>
 *         &lt;element name="vCredPresIBSZFM" type="{http://www.portalfiscal.inf.br/nfe}TDec1302RTC"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCredPresIBSZFM", propOrder = {
    "competApur",
    "tpCredPresIBSZFM",
    "vCredPresIBSZFM"
})
public class TCredPresIBSZFM {

    @XmlElement(required = true)
    @XmlSchemaType(name = "gYearMonth")
    protected XMLGregorianCalendar competApur;
    @XmlElement(required = true)
    protected String tpCredPresIBSZFM;
    @XmlElement(required = true)
    protected String vCredPresIBSZFM;

    /**
     * Obtém o valor da propriedade competApur.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompetApur() {
        return competApur;
    }

    /**
     * Define o valor da propriedade competApur.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompetApur(XMLGregorianCalendar value) {
        this.competApur = value;
    }

    /**
     * Obtém o valor da propriedade tpCredPresIBSZFM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpCredPresIBSZFM() {
        return tpCredPresIBSZFM;
    }

    /**
     * Define o valor da propriedade tpCredPresIBSZFM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpCredPresIBSZFM(String value) {
        this.tpCredPresIBSZFM = value;
    }

    /**
     * Obtém o valor da propriedade vCredPresIBSZFM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCredPresIBSZFM() {
        return vCredPresIBSZFM;
    }

    /**
     * Define o valor da propriedade vCredPresIBSZFM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCredPresIBSZFM(String value) {
        this.vCredPresIBSZFM = value;
    }

}
