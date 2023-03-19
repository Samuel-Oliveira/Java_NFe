
package br.com.swconsultoria.nfe.schema.retConsCad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Tipo Pedido de Consulta de cadastro de contribuintes
 * 
 * <p>Classe Java de TConsCad complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TConsCad"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="infCons"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="xServ"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TServ"&gt;
 *                         &lt;enumeration value="CONS-CAD"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUfCons"/&gt;
 *                   &lt;choice&gt;
 *                     &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/&gt;
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/&gt;
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/&gt;
 *                   &lt;/choice&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerConsCad" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConsCad", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "infCons"
})
public class TConsCad {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TConsCad.InfCons infCons;
    @XmlAttribute(name = "versao", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versao;

    /**
     * Obtém o valor da propriedade infCons.
     * 
     * @return
     *     possible object is
     *     {@link TConsCad.InfCons }
     *     
     */
    public TConsCad.InfCons getInfCons() {
        return infCons;
    }

    /**
     * Define o valor da propriedade infCons.
     * 
     * @param value
     *     allowed object is
     *     {@link TConsCad.InfCons }
     *     
     */
    public void setInfCons(TConsCad.InfCons value) {
        this.infCons = value;
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
     *         &lt;element name="xServ"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TServ"&gt;
     *               &lt;enumeration value="CONS-CAD"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUfCons"/&gt;
     *         &lt;choice&gt;
     *           &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/&gt;
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/&gt;
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/&gt;
     *         &lt;/choice&gt;
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
        "xServ",
        "uf",
        "ie",
        "cnpj",
        "cpf"
    })
    public static class InfCons {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String xServ;
        @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        @XmlSchemaType(name = "token")
        protected TUfCons uf;
        @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String ie;
        @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpj;
        @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cpf;

        /**
         * Obtém o valor da propriedade xServ.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXServ() {
            return xServ;
        }

        /**
         * Define o valor da propriedade xServ.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXServ(String value) {
            this.xServ = value;
        }

        /**
         * Obtém o valor da propriedade uf.
         * 
         * @return
         *     possible object is
         *     {@link TUfCons }
         *     
         */
        public TUfCons getUF() {
            return uf;
        }

        /**
         * Define o valor da propriedade uf.
         * 
         * @param value
         *     allowed object is
         *     {@link TUfCons }
         *     
         */
        public void setUF(TUfCons value) {
            this.uf = value;
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

    }

}
