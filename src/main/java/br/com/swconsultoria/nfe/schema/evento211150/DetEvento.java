
package br.com.swconsultoria.nfe.schema.evento211150;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descEvento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Solicitação de Apropriação de Crédito para bens e serviços que dependem de atividade do adquirente"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *         &lt;element name="tpAutor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="gCredito" maxOccurs="990">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vCredIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                   &lt;element name="vCredCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="nItem" use="required" type="{http://www.portalfiscal.inf.br/nfe}TnItem" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="preserve"/>
 *             &lt;enumeration value="1.00"/>
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
@XmlType(name = "", propOrder = {
    "descEvento",
    "cOrgaoAutor",
    "tpAutor",
    "verAplic",
    "gCredito"
})
@XmlRootElement(name = "detEvento", namespace = "http://www.portalfiscal.inf.br/nfe")
public class DetEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cOrgaoAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected List<DetEvento.GCredito> gCredito;
    @XmlAttribute(name = "versao")
    protected String versao;

    /**
     * Obtém o valor da propriedade descEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEvento() {
        return descEvento;
    }

    /**
     * Define o valor da propriedade descEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEvento(String value) {
        this.descEvento = value;
    }

    /**
     * Obtém o valor da propriedade cOrgaoAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOrgaoAutor() {
        return cOrgaoAutor;
    }

    /**
     * Define o valor da propriedade cOrgaoAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOrgaoAutor(String value) {
        this.cOrgaoAutor = value;
    }

    /**
     * Obtém o valor da propriedade tpAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpAutor() {
        return tpAutor;
    }

    /**
     * Define o valor da propriedade tpAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpAutor(String value) {
        this.tpAutor = value;
    }

    /**
     * Obtém o valor da propriedade verAplic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerAplic() {
        return verAplic;
    }

    /**
     * Define o valor da propriedade verAplic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerAplic(String value) {
        this.verAplic = value;
    }

    /**
     * Gets the value of the gCredito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gCredito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGCredito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento.GCredito }
     * 
     * 
     */
    public List<DetEvento.GCredito> getGCredito() {
        if (gCredito == null) {
            gCredito = new ArrayList<DetEvento.GCredito>();
        }
        return this.gCredito;
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


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="vCredIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *         &lt;element name="vCredCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *       &lt;/sequence>
     *       &lt;attribute name="nItem" use="required" type="{http://www.portalfiscal.inf.br/nfe}TnItem" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vCredIBS",
        "vCredCBS"
    })
    public static class GCredito {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vCredIBS;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vCredCBS;
        @XmlAttribute(name = "nItem", required = true)
        protected String nItem;

        /**
         * Obtém o valor da propriedade vCredIBS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredIBS() {
            return vCredIBS;
        }

        /**
         * Define o valor da propriedade vCredIBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCredIBS(String value) {
            this.vCredIBS = value;
        }

        /**
         * Obtém o valor da propriedade vCredCBS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCredCBS() {
            return vCredCBS;
        }

        /**
         * Define o valor da propriedade vCredCBS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCredCBS(String value) {
            this.vCredCBS = value;
        }

        /**
         * Obtém o valor da propriedade nItem.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNItem() {
            return nItem;
        }

        /**
         * Define o valor da propriedade nItem.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNItem(String value) {
            this.nItem = value;
        }

    }

}
