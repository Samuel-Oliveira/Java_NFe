
package br.com.swconsultoria.nfe.schemas_eventos;

import javax.xml.bind.annotation.*;

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
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}descEvento"/>
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}cOrgaoAutor"/>
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpAutor"/>
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}verAplic"/>
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}dhEmi"/>
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpNF"/>
 *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE"/>
 *         &lt;element name="dest">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UF"/>
 *                   &lt;choice>
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
 *                     &lt;element name="idEstrangeiro">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;whiteSpace value="preserve"/>
 *                           &lt;pattern value="([!-ÿ]{0}|[!-ÿ]{5,20})?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                   &lt;/choice>
 *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE" minOccurs="0"/>
 *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vNF"/>
 *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vICMS"/>
 *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vST"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required">
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
    "dhEmi",
    "tpNF",
    "ie",
    "dest"
})
@XmlRootElement(name = "detEvento")
public class DetEvento110140 {

    @XmlElement(required = true)
    protected String descEvento;
    @XmlElement(required = true)
    protected String cOrgaoAutor;
    @XmlElement(required = true)
    protected String tpAutor;
    @XmlElement(required = true)
    protected String verAplic;
    @XmlElement(required = true)
    protected String dhEmi;
    @XmlElement(required = true)
    protected String tpNF;
    @XmlElement(name = "IE", required = true)
    protected String ie;
    @XmlElement(required = true)
    protected DetEvento110140.Dest dest;
    @XmlAttribute(name = "versao", required = true)
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
     * Obtém o valor da propriedade dhEmi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhEmi() {
        return dhEmi;
    }

    /**
     * Define o valor da propriedade dhEmi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDhEmi(String value) {
        this.dhEmi = value;
    }

    /**
     * Obtém o valor da propriedade tpNF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpNF() {
        return tpNF;
    }

    /**
     * Define o valor da propriedade tpNF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpNF(String value) {
        this.tpNF = value;
    }

    /**
     * Obtém o valor da propriedade ie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIE() {
        return ie;
    }

    /**
     * Define o valor da propriedade ie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIE(String value) {
        this.ie = value;
    }

    /**
     * Obtém o valor da propriedade dest.
     * 
     * @return
     *     possible object is
     *     {@link DetEvento110140.Dest }
     *     
     */
    public DetEvento110140.Dest getDest() {
        return dest;
    }

    /**
     * Define o valor da propriedade dest.
     * 
     * @param value
     *     allowed object is
     *     {@link DetEvento110140.Dest }
     *     
     */
    public void setDest(DetEvento110140.Dest value) {
        this.dest = value;
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
     *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UF"/>
     *         &lt;choice>
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
     *           &lt;element name="idEstrangeiro">
     *             &lt;simpleType>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                 &lt;whiteSpace value="preserve"/>
     *                 &lt;pattern value="([!-ÿ]{0}|[!-ÿ]{5,20})?"/>
     *               &lt;/restriction>
     *             &lt;/simpleType>
     *           &lt;/element>
     *         &lt;/choice>
     *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE" minOccurs="0"/>
     *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vNF"/>
     *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vICMS"/>
     *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vST"/>
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
        "uf",
        "cnpj",
        "cpf",
        "idEstrangeiro",
        "ie",
        "vnf",
        "vicms",
        "vst"
    })
    public static class Dest {

        @XmlElement(name = "UF", required = true)
        @XmlSchemaType(name = "string")
        protected TUf uf;
        @XmlElement(name = "CNPJ")
        protected String cnpj;
        @XmlElement(name = "CPF")
        protected String cpf;
        protected String idEstrangeiro;
        @XmlElement(name = "IE")
        protected String ie;
        @XmlElement(name = "vNF", required = true)
        protected String vnf;
        @XmlElement(name = "vICMS", required = true)
        protected String vicms;
        @XmlElement(name = "vST", required = true)
        protected String vst;

        /**
         * Obtém o valor da propriedade uf.
         * 
         * @return
         *     possible object is
         *     {@link TUf }
         *     
         */
        public TUf getUF() {
            return uf;
        }

        /**
         * Define o valor da propriedade uf.
         * 
         * @param value
         *     allowed object is
         *     {@link TUf }
         *     
         */
        public void setUF(TUf value) {
            this.uf = value;
        }

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
         * Obtém o valor da propriedade cpf.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCPF() {
            return cpf;
        }

        /**
         * Define o valor da propriedade cpf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCPF(String value) {
            this.cpf = value;
        }

        /**
         * Obtém o valor da propriedade idEstrangeiro.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdEstrangeiro() {
            return idEstrangeiro;
        }

        /**
         * Define o valor da propriedade idEstrangeiro.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdEstrangeiro(String value) {
            this.idEstrangeiro = value;
        }

        /**
         * Obtém o valor da propriedade ie.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIE() {
            return ie;
        }

        /**
         * Define o valor da propriedade ie.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIE(String value) {
            this.ie = value;
        }

        /**
         * Obtém o valor da propriedade vnf.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVNF() {
            return vnf;
        }

        /**
         * Define o valor da propriedade vnf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVNF(String value) {
            this.vnf = value;
        }

        /**
         * Obtém o valor da propriedade vicms.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVICMS() {
            return vicms;
        }

        /**
         * Define o valor da propriedade vicms.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVICMS(String value) {
            this.vicms = value;
        }

        /**
         * Obtém o valor da propriedade vst.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVST() {
            return vst;
        }

        /**
         * Define o valor da propriedade vst.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVST(String value) {
            this.vst = value;
        }

    }

}
