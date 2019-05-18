
package br.com.swconsultoria.nfe.schema_4.inutNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Tipo Pedido de Inutilizao de Numerao da Nota Fiscal Eletrnica
 * 
 * <p>Classe Java de TInutNFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TInutNFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infInut">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
 *                   &lt;element name="xServ">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TServ">
 *                         &lt;enumeration value="INUTILIZAR"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *                   &lt;element name="ano" type="{http://www.portalfiscal.inf.br/nfe}Tano"/>
 *                   &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *                   &lt;element name="mod" type="{http://www.portalfiscal.inf.br/nfe}TMod"/>
 *                   &lt;element name="serie" type="{http://www.portalfiscal.inf.br/nfe}TSerie"/>
 *                   &lt;element name="nNFIni" type="{http://www.portalfiscal.inf.br/nfe}TNF"/>
 *                   &lt;element name="nNFFin" type="{http://www.portalfiscal.inf.br/nfe}TNF"/>
 *                   &lt;element name="xJust" type="{http://www.portalfiscal.inf.br/nfe}TJust"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Id" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *                       &lt;pattern value="ID[0-9]{41}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerInutNFe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInutNFe", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "infInut",
    "signature"
})
public class TInutNFe {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TInutNFe.InfInut infInut;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType signature;
    @XmlAttribute(name = "versao", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versao;

    /**
     * Obtm o valor da propriedade infInut.
     * 
     * @return
     *     possible object is
     *     {@link TInutNFe.InfInut }
     *     
     */
    public TInutNFe.InfInut getInfInut() {
        return infInut;
    }

    /**
     * Define o valor da propriedade infInut.
     * 
     * @param value
     *     allowed object is
     *     {@link TInutNFe.InfInut }
     *     
     */
    public void setInfInut(TInutNFe.InfInut value) {
        this.infInut = value;
    }

    /**
     * Obtm o valor da propriedade signature.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Define o valor da propriedade signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Obtm o valor da propriedade versao.
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
     * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
     *         &lt;element name="xServ">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TServ">
     *               &lt;enumeration value="INUTILIZAR"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
     *         &lt;element name="ano" type="{http://www.portalfiscal.inf.br/nfe}Tano"/>
     *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
     *         &lt;element name="mod" type="{http://www.portalfiscal.inf.br/nfe}TMod"/>
     *         &lt;element name="serie" type="{http://www.portalfiscal.inf.br/nfe}TSerie"/>
     *         &lt;element name="nNFIni" type="{http://www.portalfiscal.inf.br/nfe}TNF"/>
     *         &lt;element name="nNFFin" type="{http://www.portalfiscal.inf.br/nfe}TNF"/>
     *         &lt;element name="xJust" type="{http://www.portalfiscal.inf.br/nfe}TJust"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Id" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
     *             &lt;pattern value="ID[0-9]{41}"/>
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
        "tpAmb",
        "xServ",
        "cuf",
        "ano",
        "cnpj",
        "mod",
        "serie",
        "nnfIni",
        "nnfFin",
        "xJust"
    })
    public static class InfInut {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tpAmb;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String xServ;
        @XmlElement(name = "cUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cuf;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String ano;
        @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cnpj;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String mod;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String serie;
        @XmlElement(name = "nNFIni", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String nnfIni;
        @XmlElement(name = "nNFFin", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String nnfFin;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String xJust;
        @XmlAttribute(name = "Id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;

        /**
         * Obtm o valor da propriedade tpAmb.
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
         * Obtm o valor da propriedade xServ.
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
         * Obtm o valor da propriedade cuf.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCUF() {
            return cuf;
        }

        /**
         * Define o valor da propriedade cuf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCUF(String value) {
            this.cuf = value;
        }

        /**
         * Obtm o valor da propriedade ano.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAno() {
            return ano;
        }

        /**
         * Define o valor da propriedade ano.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAno(String value) {
            this.ano = value;
        }

        /**
         * Obtm o valor da propriedade cnpj.
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
         * Obtm o valor da propriedade mod.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMod() {
            return mod;
        }

        /**
         * Define o valor da propriedade mod.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMod(String value) {
            this.mod = value;
        }

        /**
         * Obtm o valor da propriedade serie.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSerie() {
            return serie;
        }

        /**
         * Define o valor da propriedade serie.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSerie(String value) {
            this.serie = value;
        }

        /**
         * Obtm o valor da propriedade nnfIni.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNNFIni() {
            return nnfIni;
        }

        /**
         * Define o valor da propriedade nnfIni.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNNFIni(String value) {
            this.nnfIni = value;
        }

        /**
         * Obtm o valor da propriedade nnfFin.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNNFFin() {
            return nnfFin;
        }

        /**
         * Define o valor da propriedade nnfFin.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNNFFin(String value) {
            this.nnfFin = value;
        }

        /**
         * Obtm o valor da propriedade xJust.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXJust() {
            return xJust;
        }

        /**
         * Define o valor da propriedade xJust.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXJust(String value) {
            this.xJust = value;
        }

        /**
         * Obtm o valor da propriedade id.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Define o valor da propriedade id.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

    }

}
