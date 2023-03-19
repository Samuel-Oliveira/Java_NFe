
package br.com.swconsultoria.nfe.schema.distdfeint;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/&gt;
 *         &lt;element name="cUFAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/&gt;
 *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="distNSU"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="ultNSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="consNSU"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="NSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="consChNFe"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerDistDFe" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tpAmb",
    "cufAutor",
    "cnpj",
    "cpf",
    "distNSU",
    "consNSU",
    "consChNFe"
})
@XmlRootElement(name = "distDFeInt", namespace = "http://www.portalfiscal.inf.br/nfe")
public class DistDFeInt {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpAmb;
    @XmlElement(name = "cUFAutor", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cufAutor;
    @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cnpj;
    @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cpf;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected DistDFeInt.DistNSU distNSU;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected DistDFeInt.ConsNSU consNSU;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected DistDFeInt.ConsChNFe consChNFe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtém o valor da propriedade tpAmb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpAmb() {
        return tpAmb;
    }

    /**
     * Define o valor da propriedade tpAmb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpAmb(String value) {
        this.tpAmb = value;
    }

    /**
     * Obtém o valor da propriedade cufAutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUFAutor() {
        return cufAutor;
    }

    /**
     * Define o valor da propriedade cufAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUFAutor(String value) {
        this.cufAutor = value;
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
     * Obtém o valor da propriedade distNSU.
     * 
     * @return
     *     possible object is
     *     {@link DistDFeInt.DistNSU }
     *     
     */
    public DistDFeInt.DistNSU getDistNSU() {
        return distNSU;
    }

    /**
     * Define o valor da propriedade distNSU.
     * 
     * @param value
     *     allowed object is
     *     {@link DistDFeInt.DistNSU }
     *     
     */
    public void setDistNSU(DistDFeInt.DistNSU value) {
        this.distNSU = value;
    }

    /**
     * Obtém o valor da propriedade consNSU.
     * 
     * @return
     *     possible object is
     *     {@link DistDFeInt.ConsNSU }
     *     
     */
    public DistDFeInt.ConsNSU getConsNSU() {
        return consNSU;
    }

    /**
     * Define o valor da propriedade consNSU.
     * 
     * @param value
     *     allowed object is
     *     {@link DistDFeInt.ConsNSU }
     *     
     */
    public void setConsNSU(DistDFeInt.ConsNSU value) {
        this.consNSU = value;
    }

    /**
     * Obtém o valor da propriedade consChNFe.
     * 
     * @return
     *     possible object is
     *     {@link DistDFeInt.ConsChNFe }
     *     
     */
    public DistDFeInt.ConsChNFe getConsChNFe() {
        return consChNFe;
    }

    /**
     * Define o valor da propriedade consChNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link DistDFeInt.ConsChNFe }
     *     
     */
    public void setConsChNFe(DistDFeInt.ConsChNFe value) {
        this.consChNFe = value;
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
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/&gt;
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
        "chNFe"
    })
    public static class ConsChNFe {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String chNFe;

        /**
         * Obtém o valor da propriedade chNFe.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChNFe() {
            return chNFe;
        }

        /**
         * Define o valor da propriedade chNFe.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChNFe(String value) {
            this.chNFe = value;
        }

    }


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
     *         &lt;element name="NSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/&gt;
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
        "nsu"
    })
    public static class ConsNSU {

        @XmlElement(name = "NSU", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "token")
        protected String nsu;

        /**
         * Obtém o valor da propriedade nsu.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNSU() {
            return nsu;
        }

        /**
         * Define o valor da propriedade nsu.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNSU(String value) {
            this.nsu = value;
        }

    }


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
     *         &lt;element name="ultNSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/&gt;
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
        "ultNSU"
    })
    public static class DistNSU {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "token")
        protected String ultNSU;

        /**
         * Obtém o valor da propriedade ultNSU.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUltNSU() {
            return ultNSU;
        }

        /**
         * Define o valor da propriedade ultNSU.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUltNSU(String value) {
            this.ultNSU = value;
        }

    }

}
