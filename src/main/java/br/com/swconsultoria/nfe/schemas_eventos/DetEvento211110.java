
package br.com.swconsultoria.nfe.schemas_eventos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Evento 211110 - Solicitação de Apropriação de crédito presumido (NT 2025.002-RTC).
 *
 * <p>Estrutura conforme o e211110_v1.00.xsd do pacote "Schema dos eventos da NT 2025.002 v.1.40 -
 * RTC", publicado em 27/07/2026:
 *
 * <pre>
 * &lt;complexType>
 *   &lt;sequence>
 *     &lt;element name="descEvento"> (enumeration "Solicitação de Apropriação de crédito presumido")
 *     &lt;element name="cOrgaoAutor" type="TCodUfIBGE"/>
 *     &lt;element name="tpAutor"> (enumeration "1", "2")
 *     &lt;element name="verAplic" type="TVerAplic"/>
 *     &lt;element name="gCredPresOper" maxOccurs="990">
 *       &lt;sequence>
 *         &lt;element name="vBCCredPres" type="TDec_1302"/>
 *         &lt;element name="cCredPres"/>
 *         &lt;element name="gIBSCredPres" minOccurs="0">
 *           &lt;sequence>
 *             &lt;element name="pCredPres" type="TDec_0302_04"/>
 *             &lt;element name="vCredPres" type="TDec1302"/>
 *           &lt;/sequence>
 *         &lt;/element>
 *         &lt;element name="gCBSCredPres" minOccurs="0">
 *           &lt;sequence>
 *             &lt;element name="pCredPres" type="TDec_0302_04"/>
 *             &lt;element name="vCredPres" type="TDec1302"/>
 *           &lt;/sequence>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="nItem" type="TnItem" use="required"/>
 *     &lt;/element>
 *   &lt;/sequence>
 *   &lt;attribute name="versao"> (enumeration "1.00")
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "descEvento",
    "cOrgaoAutor",
    "tpAutor",
    "verAplic",
    "gCredPresOper"
})
@XmlRootElement(name = "detEvento")
public class DetEvento211110 {

    @XmlElement(required = true)
    protected String descEvento;
    @XmlElement(required = true)
    protected String cOrgaoAutor;
    @XmlElement(required = true)
    protected String tpAutor;
    @XmlElement(required = true)
    protected String verAplic;
    @XmlElement(required = true)
    protected List<DetEvento211110.GCredPresOper> gCredPresOper;
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
     * Gets the value of the gCredPresOper property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetEvento211110.GCredPresOper }
     *
     *
     */
    public List<DetEvento211110.GCredPresOper> getGCredPresOper() {
        if (gCredPresOper == null) {
            gCredPresOper = new ArrayList<DetEvento211110.GCredPresOper>();
        }
        return this.gCredPresOper;
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
     * Grupo de crédito presumido, por item da NF-e.
     *
     * <p>A base de cálculo e o código do crédito presumido são do grupo; o percentual e o valor
     * vêm separados por tributo, em gIBSCredPres e gCBSCredPres.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vBCCredPres",
        "cCredPres",
        "gIBSCredPres",
        "gCBSCredPres"
    })
    public static class GCredPresOper {

        @XmlElement(required = true)
        protected String vBCCredPres;
        @XmlElement(required = true)
        protected String cCredPres;
        protected DetEvento211110.GCredPresOper.GIBSCredPres gIBSCredPres;
        protected DetEvento211110.GCredPresOper.GCBSCredPres gCBSCredPres;
        @XmlAttribute(name = "nItem", required = true)
        protected String nItem;

        /**
         * Obtém o valor da propriedade vBCCredPres.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVBCCredPres() {
            return vBCCredPres;
        }

        /**
         * Define o valor da propriedade vBCCredPres.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVBCCredPres(String value) {
            this.vBCCredPres = value;
        }

        /**
         * Obtém o valor da propriedade cCredPres.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCCredPres() {
            return cCredPres;
        }

        /**
         * Define o valor da propriedade cCredPres.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCCredPres(String value) {
            this.cCredPres = value;
        }

        /**
         * Obtém o valor da propriedade gIBSCredPres.
         *
         * @return
         *     possible object is
         *     {@link DetEvento211110.GCredPresOper.GIBSCredPres }
         *
         */
        public DetEvento211110.GCredPresOper.GIBSCredPres getGIBSCredPres() {
            return gIBSCredPres;
        }

        /**
         * Define o valor da propriedade gIBSCredPres.
         *
         * @param value
         *     allowed object is
         *     {@link DetEvento211110.GCredPresOper.GIBSCredPres }
         *
         */
        public void setGIBSCredPres(DetEvento211110.GCredPresOper.GIBSCredPres value) {
            this.gIBSCredPres = value;
        }

        /**
         * Obtém o valor da propriedade gCBSCredPres.
         *
         * @return
         *     possible object is
         *     {@link DetEvento211110.GCredPresOper.GCBSCredPres }
         *
         */
        public DetEvento211110.GCredPresOper.GCBSCredPres getGCBSCredPres() {
            return gCBSCredPres;
        }

        /**
         * Define o valor da propriedade gCBSCredPres.
         *
         * @param value
         *     allowed object is
         *     {@link DetEvento211110.GCredPresOper.GCBSCredPres }
         *
         */
        public void setGCBSCredPres(DetEvento211110.GCredPresOper.GCBSCredPres value) {
            this.gCBSCredPres = value;
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
         * Crédito presumido do IBS.
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "pCredPres",
            "vCredPres"
        })
        public static class GIBSCredPres {

            @XmlElement(required = true)
            protected String pCredPres;
            @XmlElement(required = true)
            protected String vCredPres;

            /**
             * Obtém o valor da propriedade pCredPres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPCredPres() {
                return pCredPres;
            }

            /**
             * Define o valor da propriedade pCredPres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPCredPres(String value) {
                this.pCredPres = value;
            }

            /**
             * Obtém o valor da propriedade vCredPres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getVCredPres() {
                return vCredPres;
            }

            /**
             * Define o valor da propriedade vCredPres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setVCredPres(String value) {
                this.vCredPres = value;
            }

        }


        /**
         * Crédito presumido da CBS.
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "pCredPres",
            "vCredPres"
        })
        public static class GCBSCredPres {

            @XmlElement(required = true)
            protected String pCredPres;
            @XmlElement(required = true)
            protected String vCredPres;

            /**
             * Obtém o valor da propriedade pCredPres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPCredPres() {
                return pCredPres;
            }

            /**
             * Define o valor da propriedade pCredPres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPCredPres(String value) {
                this.pCredPres = value;
            }

            /**
             * Obtém o valor da propriedade vCredPres.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getVCredPres() {
                return vCredPres;
            }

            /**
             * Define o valor da propriedade vCredPres.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setVCredPres(String value) {
                this.vCredPres = value;
            }

        }

    }

}
