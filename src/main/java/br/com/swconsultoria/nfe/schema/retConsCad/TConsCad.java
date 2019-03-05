
package br.com.swconsultoria.nfe.schema.retConsCad;

import javax.xml.bind.annotation.*;
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
 * &lt;complexType name="TConsCad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infCons">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="xServ">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TServ">
 *                         &lt;enumeration value="CONS-CAD"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUfCons"/>
 *                   &lt;choice>
 *                     &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerConsCad" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConsCad", propOrder = {
    "infCons"
})
public class TConsCad {

    @XmlElement(required = true)
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
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="xServ">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TServ">
     *               &lt;enumeration value="CONS-CAD"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUfCons"/>
     *         &lt;choice>
     *           &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
     *         &lt;/choice>
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
        "xServ",
        "uf",
        "ie",
        "cnpj",
        "cpf"
    })
    public static class InfCons {

        @XmlElement(required = true)
        protected String xServ;
        @XmlElement(name = "UF", required = true)
        protected TUfCons uf;
        @XmlElement(name = "IE")
        protected String ie;
        @XmlElement(name = "CNPJ")
        protected String cnpj;
        @XmlElement(name = "CPF")
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
