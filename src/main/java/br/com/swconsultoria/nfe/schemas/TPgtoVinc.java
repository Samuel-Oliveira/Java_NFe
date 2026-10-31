
package br.com.swconsultoria.nfe.schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Informações da Vinculação com a Transação de Pagamento
 * 
 * <p>Classe Java de TPgtoVinc complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TPgtoVinc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gPgto" maxOccurs="99">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tpMeioPgto">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[0-9]{2}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="CNPJReceb" type="{http://www.portalfiscal.inf.br/nfe}TCnpjRTC"/>
 *                   &lt;element name="CNPJBasePSP" type="{http://www.portalfiscal.inf.br/nfe}TCnpjBaseRTC"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="nPag" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;whiteSpace value="preserve"/>
 *                       &lt;pattern value="[1-9]{1}[0-9]{0,1}|[1-8]{1}[0-9]{2}|[9]{1}[0-8]{1}[0-9]{1}|[9]{1}[9]{1}[0]{1}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="idTransacao" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TStringRTC">
 *                       &lt;minLength value="2"/>
 *                       &lt;maxLength value="35"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPgtoVinc", propOrder = {
    "gPgto"
})
public class TPgtoVinc {

    @XmlElement(required = true)
    protected List<TPgtoVinc.GPgto> gPgto;

    /**
     * Gets the value of the gPgto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gPgto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGPgto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPgtoVinc.GPgto }
     * 
     * 
     */
    public List<TPgtoVinc.GPgto> getGPgto() {
        if (gPgto == null) {
            gPgto = new ArrayList<TPgtoVinc.GPgto>();
        }
        return this.gPgto;
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
     *         &lt;element name="tpMeioPgto">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[0-9]{2}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="CNPJReceb" type="{http://www.portalfiscal.inf.br/nfe}TCnpjRTC"/>
     *         &lt;element name="CNPJBasePSP" type="{http://www.portalfiscal.inf.br/nfe}TCnpjBaseRTC"/>
     *       &lt;/sequence>
     *       &lt;attribute name="nPag" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;whiteSpace value="preserve"/>
     *             &lt;pattern value="[1-9]{1}[0-9]{0,1}|[1-8]{1}[0-9]{2}|[9]{1}[0-8]{1}[0-9]{1}|[9]{1}[9]{1}[0]{1}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="idTransacao" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TStringRTC">
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
    @XmlType(name = "", propOrder = {
        "tpMeioPgto",
        "cnpjReceb",
        "cnpjBasePSP"
    })
    public static class GPgto {

        @XmlElement(required = true)
        protected String tpMeioPgto;
        @XmlElement(name = "CNPJReceb", required = true)
        protected String cnpjReceb;
        @XmlElement(name = "CNPJBasePSP", required = true)
        protected String cnpjBasePSP;
        @XmlAttribute(name = "nPag", required = true)
        protected String nPag;
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
         * Obtém o valor da propriedade nPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNPag() {
            return nPag;
        }

        /**
         * Define o valor da propriedade nPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNPag(String value) {
            this.nPag = value;
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

}
