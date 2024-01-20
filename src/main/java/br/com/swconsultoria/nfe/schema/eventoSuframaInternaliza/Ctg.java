
package br.com.swconsultoria.nfe.schema.eventoSuframaInternaliza;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}nFormSeg" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UFDest"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpEmis"/&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{http://www.portalfiscal.inf.br/nfe}CNPJDest"/&gt;
 *           &lt;element ref="{http://www.portalfiscal.inf.br/nfe}CPFDest"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vTotalNFe"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}indICMS"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}indICMSST"/&gt;
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}diaEmi"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nFormSeg",
    "ufDest",
    "tpEmis",
    "cnpjDest",
    "cpfDest",
    "vTotalNFe",
    "indICMS",
    "indICMSST",
    "diaEmi"
})
@XmlRootElement(name = "ctg", namespace = "http://www.portalfiscal.inf.br/nfe")
public class Ctg {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String nFormSeg;
    @XmlElement(name = "UFDest", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    @XmlSchemaType(name = "string")
    protected TUf ufDest;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpEmis;
    @XmlElement(name = "CNPJDest", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cnpjDest;
    @XmlElement(name = "CPFDest", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cpfDest;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String vTotalNFe;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String indICMS;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String indICMSST;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String diaEmi;

    /**
     * Obtém o valor da propriedade nFormSeg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNFormSeg() {
        return nFormSeg;
    }

    /**
     * Define o valor da propriedade nFormSeg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNFormSeg(String value) {
        this.nFormSeg = value;
    }

    /**
     * Obtém o valor da propriedade ufDest.
     * 
     * @return
     *     possible object is
     *     {@link TUf }
     *     
     */
    public TUf getUFDest() {
        return ufDest;
    }

    /**
     * Define o valor da propriedade ufDest.
     * 
     * @param value
     *     allowed object is
     *     {@link TUf }
     *     
     */
    public void setUFDest(TUf value) {
        this.ufDest = value;
    }

    /**
     * Obtém o valor da propriedade tpEmis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpEmis() {
        return tpEmis;
    }

    /**
     * Define o valor da propriedade tpEmis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpEmis(String value) {
        this.tpEmis = value;
    }

    /**
     * Obtém o valor da propriedade cnpjDest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJDest() {
        return cnpjDest;
    }

    /**
     * Define o valor da propriedade cnpjDest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJDest(String value) {
        this.cnpjDest = value;
    }

    /**
     * Obtém o valor da propriedade cpfDest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPFDest() {
        return cpfDest;
    }

    /**
     * Define o valor da propriedade cpfDest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPFDest(String value) {
        this.cpfDest = value;
    }

    /**
     * Obtém o valor da propriedade vTotalNFe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTotalNFe() {
        return vTotalNFe;
    }

    /**
     * Define o valor da propriedade vTotalNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTotalNFe(String value) {
        this.vTotalNFe = value;
    }

    /**
     * Obtém o valor da propriedade indICMS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndICMS() {
        return indICMS;
    }

    /**
     * Define o valor da propriedade indICMS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndICMS(String value) {
        this.indICMS = value;
    }

    /**
     * Obtém o valor da propriedade indICMSST.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndICMSST() {
        return indICMSST;
    }

    /**
     * Define o valor da propriedade indICMSST.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndICMSST(String value) {
        this.indICMSST = value;
    }

    /**
     * Obtém o valor da propriedade diaEmi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiaEmi() {
        return diaEmi;
    }

    /**
     * Define o valor da propriedade diaEmi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiaEmi(String value) {
        this.diaEmi = value;
    }

}
