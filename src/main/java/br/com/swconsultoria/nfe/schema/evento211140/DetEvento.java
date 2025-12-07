
package br.com.swconsultoria.nfe.schema.evento211140;

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
 *               &lt;enumeration value="Solicitação de Apropriação de Crédito de Combustível"/>
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
 *         &lt;element name="gConsumoComb" maxOccurs="990">
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
 *                             &lt;element name="qComb" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/>
 *                             &lt;element name="uComb">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                                   &lt;maxLength value="6"/>
 *                                   &lt;minLength value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
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
    "gConsumoComb"
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
    protected List<DetEvento.GConsumoComb> gConsumoComb;
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
     * Gets the value of the gConsumoComb property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gConsumoComb property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGConsumoComb().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento.GConsumoComb }
     * 
     * 
     */
    public List<DetEvento.GConsumoComb> getGConsumoComb() {
        if (gConsumoComb == null) {
            gConsumoComb = new ArrayList<DetEvento.GConsumoComb>();
        }
        return this.gConsumoComb;
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
     *                   &lt;element name="qComb" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/>
     *                   &lt;element name="uComb">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *                         &lt;maxLength value="6"/>
     *                         &lt;minLength value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
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
    public static class GConsumoComb {

        @XmlElement(name = "vIBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vibs;
        @XmlElement(name = "vCBS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vcbs;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected DetEvento.GConsumoComb.GControleEstoque gControleEstoque;
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
         *     {@link DetEvento.GConsumoComb.GControleEstoque }
         *     
         */
        public DetEvento.GConsumoComb.GControleEstoque getGControleEstoque() {
            return gControleEstoque;
        }

        /**
         * Define o valor da propriedade gControleEstoque.
         * 
         * @param value
         *     allowed object is
         *     {@link DetEvento.GConsumoComb.GControleEstoque }
         *     
         */
        public void setGControleEstoque(DetEvento.GConsumoComb.GControleEstoque value) {
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
         *         &lt;element name="qComb" type="{http://www.portalfiscal.inf.br/nfe}TDec_1104"/>
         *         &lt;element name="uComb">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
         *               &lt;maxLength value="6"/>
         *               &lt;minLength value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
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
        @XmlType(name = "", propOrder = {
            "qComb",
            "uComb"
        })
        public static class GControleEstoque {

            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String qComb;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String uComb;

            /**
             * Obtém o valor da propriedade qComb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQComb() {
                return qComb;
            }

            /**
             * Define o valor da propriedade qComb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQComb(String value) {
                this.qComb = value;
            }

            /**
             * Obtém o valor da propriedade uComb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUComb() {
                return uComb;
            }

            /**
             * Define o valor da propriedade uComb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUComb(String value) {
                this.uComb = value;
            }

        }

    }

}
