
package br.com.swconsultoria.nfe.schema.distdfeint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
 *         &lt;element name="cUFAutor" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="distNSU">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="ultNSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="consNSU">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="NSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="consChNFe">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerDistDFe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
@XmlRootElement(name = "distDFeInt")
public class DistDFeInt {

    @XmlElement(required = true)
    protected String tpAmb;
    @XmlElement(name = "cUFAutor")
    protected String cufAutor;
    @XmlElement(name = "CNPJ")
    protected String cnpj;
    @XmlElement(name = "CPF")
    protected String cpf;
    protected DistDFeInt.DistNSU distNSU;
    protected DistDFeInt.ConsNSU consNSU;
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
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
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
        "chNFe"
    })
    public static class ConsChNFe {

        @XmlElement(required = true)
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
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="NSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/>
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
        "nsu"
    })
    public static class ConsNSU {

        @XmlElement(name = "NSU", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
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
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ultNSU" type="{http://www.portalfiscal.inf.br/nfe}TNSU"/>
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
        "ultNSU"
    })
    public static class DistNSU {

        @XmlElement(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
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
