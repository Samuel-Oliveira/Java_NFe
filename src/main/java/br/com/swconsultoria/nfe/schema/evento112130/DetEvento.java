
package br.com.swconsultoria.nfe.schema.evento112130;

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
 *               &lt;enumeration value="Perecimento, perda, roubo ou furto durante o transporte contratado pelo fornecedor"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cOrgaoAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *         &lt;element name="tpAutor">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="gPerecimento" maxOccurs="990">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                   &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                   &lt;element name="gControleEstoque">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="qPerecimento" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/>
 *                             &lt;element name="uPerecimento">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                                   &lt;maxLength value="6"/>
 *                                   &lt;minLength value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;sequence>
 *                               &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                               &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
 *                             &lt;/sequence>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "gPerecimento"
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
    protected List<DetEvento.GPerecimento> gPerecimento;
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
     * Gets the value of the gPerecimento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gPerecimento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGPerecimento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento.GPerecimento }
     * 
     * 
     */
    public List<DetEvento.GPerecimento> getGPerecimento() {
        if (gPerecimento == null) {
            gPerecimento = new ArrayList<DetEvento.GPerecimento>();
        }
        return this.gPerecimento;
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
     *         &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *         &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *         &lt;element name="gControleEstoque">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qPerecimento" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/>
     *                   &lt;element name="uPerecimento">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *                         &lt;maxLength value="6"/>
     *                         &lt;minLength value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;sequence>
     *                     &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *                     &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
     *                   &lt;/sequence>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "vibs",
        "vcbs",
        "gControleEstoque"
    })
    public static class GPerecimento {

        @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibs;
        @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbs;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected DetEvento.GPerecimento.GControleEstoque gControleEstoque;
        @XmlAttribute(name = "nItem", required = true)
        protected String nItem;

        /**
         * Obtém o valor da propriedade vibs.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVIBS() {
            return vibs;
        }

        /**
         * Define o valor da propriedade vibs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVIBS(String value) {
            this.vibs = value;
        }

        /**
         * Obtém o valor da propriedade vcbs.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVCBS() {
            return vcbs;
        }

        /**
         * Define o valor da propriedade vcbs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVCBS(String value) {
            this.vcbs = value;
        }

        /**
         * Obtém o valor da propriedade gControleEstoque.
         * 
         * @return
         *     possible object is
         *     {@link DetEvento.GPerecimento.GControleEstoque }
         *     
         */
        public DetEvento.GPerecimento.GControleEstoque getGControleEstoque() {
            return gControleEstoque;
        }

        /**
         * Define o valor da propriedade gControleEstoque.
         * 
         * @param value
         *     allowed object is
         *     {@link DetEvento.GPerecimento.GControleEstoque }
         *     
         */
        public void setGControleEstoque(DetEvento.GPerecimento.GControleEstoque value) {
            this.gControleEstoque = value;
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
         *         &lt;element name="qPerecimento" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/>
         *         &lt;element name="uPerecimento">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
         *               &lt;maxLength value="6"/>
         *               &lt;minLength value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;sequence>
         *           &lt;element name="vIBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
         *           &lt;element name="vCBS" type="{http://www.portalfiscal.inf.br/nfe}TDec_1302"/>
         *         &lt;/sequence>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "qPerecimento",
            "uPerecimento",
            "vibs",
            "vcbs"
        })
        public static class GControleEstoque {

            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String qPerecimento;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String uPerecimento;
            @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vibs;
            @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String vcbs;

            /**
             * Obtém o valor da propriedade qPerecimento.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQPerecimento() {
                return qPerecimento;
            }

            /**
             * Define o valor da propriedade qPerecimento.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQPerecimento(String value) {
                this.qPerecimento = value;
            }

            /**
             * Obtém o valor da propriedade uPerecimento.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPerecimento() {
                return uPerecimento;
            }

            /**
             * Define o valor da propriedade uPerecimento.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPerecimento(String value) {
                this.uPerecimento = value;
            }

            /**
             * Obtém o valor da propriedade vibs.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVIBS() {
                return vibs;
            }

            /**
             * Define o valor da propriedade vibs.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVIBS(String value) {
                this.vibs = value;
            }

            /**
             * Obtém o valor da propriedade vcbs.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVCBS() {
                return vcbs;
            }

            /**
             * Define o valor da propriedade vcbs.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVCBS(String value) {
                this.vcbs = value;
            }

        }

    }

}
