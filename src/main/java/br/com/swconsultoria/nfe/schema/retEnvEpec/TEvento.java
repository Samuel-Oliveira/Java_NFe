
package br.com.swconsultoria.nfe.schema.retEnvEpec;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Tipo Evento
 *
 * <p>Classe Java de TEvento complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infEvento">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cOrgao" type="{http://www.portalfiscal.inf.br/nfe}TCOrgaoIBGE"/>
 *                   &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
 *                   &lt;choice>
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjOpc"/>
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
 *                   &lt;/choice>
 *                   &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
 *                   &lt;element name="dhEvento" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/>
 *                   &lt;element name="tpEvento">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[0-9]{6}"/>
 *                         &lt;enumeration value="110140"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="nSeqEvento">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[1-9]|[1][0-9]{0,1}|20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="verEvento">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;enumeration value="1.00"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="detEvento">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}descEvento"/>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}cOrgaoAutor"/>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpAutor"/>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}verAplic"/>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}dhEmi"/>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpNF"/>
 *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE"/>
 *                             &lt;element name="dest">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UF"/>
 *                                       &lt;choice>
 *                                         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
 *                                         &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
 *                                         &lt;element name="idEstrangeiro">
 *                                           &lt;simpleType>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                               &lt;whiteSpace value="preserve"/>
 *                                               &lt;pattern value="([!-]{0}|[!-]{5,20})?"/>
 *                                             &lt;/restriction>
 *                                           &lt;/simpleType>
 *                                         &lt;/element>
 *                                       &lt;/choice>
 *                                       &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE" minOccurs="0"/>
 *                                       &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vNF"/>
 *                                       &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vICMS"/>
 *                                       &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vST"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="versao" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="preserve"/>
 *                                 &lt;enumeration value="1.00"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Id" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *                       &lt;pattern value="ID[0-9]{52}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerEvento" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEvento", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
        "infEvento",
        "signature"
})
public class TEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TEvento.InfEvento infEvento;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType signature;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtm o valor da propriedade infEvento.
     *
     * @return possible object is
     * {@link TEvento.InfEvento }
     */
    public TEvento.InfEvento getInfEvento() {
        return infEvento;
    }

    /**
     * Define o valor da propriedade infEvento.
     *
     * @param value allowed object is
     *              {@link TEvento.InfEvento }
     */
    public void setInfEvento(TEvento.InfEvento value) {
        this.infEvento = value;
    }

    /**
     * Obtm o valor da propriedade signature.
     *
     * @return possible object is
     * {@link SignatureType }
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Define o valor da propriedade signature.
     *
     * @param value allowed object is
     *              {@link SignatureType }
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Obtm o valor da propriedade versao.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     *
     * @param value allowed object is
     *              {@link String }
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
     *         &lt;element name="cOrgao" type="{http://www.portalfiscal.inf.br/nfe}TCOrgaoIBGE"/>
     *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
     *         &lt;choice>
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjOpc"/>
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
     *         &lt;/choice>
     *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
     *         &lt;element name="dhEvento" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/>
     *         &lt;element name="tpEvento">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[0-9]{6}"/>
     *               &lt;enumeration value="110140"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="nSeqEvento">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[1-9]|[1][0-9]{0,1}|20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="verEvento">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;enumeration value="1.00"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="detEvento">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}descEvento"/>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}cOrgaoAutor"/>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpAutor"/>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}verAplic"/>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}dhEmi"/>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}tpNF"/>
     *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE"/>
     *                   &lt;element name="dest">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UF"/>
     *                             &lt;choice>
     *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
     *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
     *                               &lt;element name="idEstrangeiro">
     *                                 &lt;simpleType>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                     &lt;whiteSpace value="preserve"/>
     *                                     &lt;pattern value="([!-]{0}|[!-]{5,20})?"/>
     *                                   &lt;/restriction>
     *                                 &lt;/simpleType>
     *                               &lt;/element>
     *                             &lt;/choice>
     *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}IE" minOccurs="0"/>
     *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vNF"/>
     *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vICMS"/>
     *                             &lt;element ref="{http://www.portalfiscal.inf.br/nfe}vST"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="versao" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;whiteSpace value="preserve"/>
     *                       &lt;enumeration value="1.00"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Id" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
     *             &lt;pattern value="ID[0-9]{52}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "cOrgao",
            "tpAmb",
            "cnpj",
            "cpf",
            "chNFe",
            "dhEvento",
            "tpEvento",
            "nSeqEvento",
            "verEvento",
            "detEvento"
    })
    public static class InfEvento {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cOrgao;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tpAmb;
        @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpj;
        @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cpf;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String chNFe;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String dhEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tpEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String nSeqEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String verEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected TEvento.InfEvento.DetEvento detEvento;
        @XmlAttribute(name = "Id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;

        /**
         * Obtm o valor da propriedade cOrgao.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCOrgao() {
            return cOrgao;
        }

        /**
         * Define o valor da propriedade cOrgao.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCOrgao(String value) {
            this.cOrgao = value;
        }

        /**
         * Obtm o valor da propriedade tpAmb.
         *
         * @return possible object is
         * {@link String }
         */
        public String getTpAmb() {
            return tpAmb;
        }

        /**
         * Define o valor da propriedade tpAmb.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTpAmb(String value) {
            this.tpAmb = value;
        }

        /**
         * Obtm o valor da propriedade cnpj.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCNPJ() {
            return cnpj;
        }

        /**
         * Define o valor da propriedade cnpj.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCNPJ(String value) {
            this.cnpj = value;
        }

        /**
         * Obtm o valor da propriedade cpf.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCPF() {
            return cpf;
        }

        /**
         * Define o valor da propriedade cpf.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCPF(String value) {
            this.cpf = value;
        }

        /**
         * Obtm o valor da propriedade chNFe.
         *
         * @return possible object is
         * {@link String }
         */
        public String getChNFe() {
            return chNFe;
        }

        /**
         * Define o valor da propriedade chNFe.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChNFe(String value) {
            this.chNFe = value;
        }

        /**
         * Obtm o valor da propriedade dhEvento.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDhEvento() {
            return dhEvento;
        }

        /**
         * Define o valor da propriedade dhEvento.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDhEvento(String value) {
            this.dhEvento = value;
        }

        /**
         * Obtm o valor da propriedade tpEvento.
         *
         * @return possible object is
         * {@link String }
         */
        public String getTpEvento() {
            return tpEvento;
        }

        /**
         * Define o valor da propriedade tpEvento.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTpEvento(String value) {
            this.tpEvento = value;
        }

        /**
         * Obtm o valor da propriedade nSeqEvento.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNSeqEvento() {
            return nSeqEvento;
        }

        /**
         * Define o valor da propriedade nSeqEvento.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNSeqEvento(String value) {
            this.nSeqEvento = value;
        }

        /**
         * Obtm o valor da propriedade verEvento.
         *
         * @return possible object is
         * {@link String }
         */
        public String getVerEvento() {
            return verEvento;
        }

        /**
         * Define o valor da propriedade verEvento.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setVerEvento(String value) {
            this.verEvento = value;
        }

        /**
         * Obtm o valor da propriedade detEvento.
         *
         * @return possible object is
         * {@link TEvento.InfEvento.DetEvento }
         */
        public TEvento.InfEvento.DetEvento getDetEvento() {
            return detEvento;
        }

        /**
         * Define o valor da propriedade detEvento.
         *
         * @param value allowed object is
         *              {@link TEvento.InfEvento.DetEvento }
         */
        public void setDetEvento(TEvento.InfEvento.DetEvento value) {
            this.detEvento = value;
        }

        /**
         * Obtm o valor da propriedade id.
         *
         * @return possible object is
         * {@link String }
         */
        public String getId() {
            return id;
        }

        /**
         * Define o valor da propriedade id.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setId(String value) {
            this.id = value;
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
         *                           &lt;pattern value="([!-]{0}|[!-]{5,20})?"/>
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
        public static class DetEvento {

            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String descEvento;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String cOrgaoAutor;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String tpAutor;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String verAplic;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String dhEmi;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String tpNF;
            @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected String ie;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
            protected TEvento.InfEvento.DetEvento.Dest dest;
            @XmlAttribute(name = "versao", required = true)
            protected String versao;

            /**
             * Obtm o valor da propriedade descEvento.
             *
             * @return possible object is
             * {@link String }
             */
            public String getDescEvento() {
                return descEvento;
            }

            /**
             * Define o valor da propriedade descEvento.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setDescEvento(String value) {
                this.descEvento = value;
            }

            /**
             * Obtm o valor da propriedade cOrgaoAutor.
             *
             * @return possible object is
             * {@link String }
             */
            public String getCOrgaoAutor() {
                return cOrgaoAutor;
            }

            /**
             * Define o valor da propriedade cOrgaoAutor.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setCOrgaoAutor(String value) {
                this.cOrgaoAutor = value;
            }

            /**
             * Obtm o valor da propriedade tpAutor.
             *
             * @return possible object is
             * {@link String }
             */
            public String getTpAutor() {
                return tpAutor;
            }

            /**
             * Define o valor da propriedade tpAutor.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setTpAutor(String value) {
                this.tpAutor = value;
            }

            /**
             * Obtm o valor da propriedade verAplic.
             *
             * @return possible object is
             * {@link String }
             */
            public String getVerAplic() {
                return verAplic;
            }

            /**
             * Define o valor da propriedade verAplic.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setVerAplic(String value) {
                this.verAplic = value;
            }

            /**
             * Obtm o valor da propriedade dhEmi.
             *
             * @return possible object is
             * {@link String }
             */
            public String getDhEmi() {
                return dhEmi;
            }

            /**
             * Define o valor da propriedade dhEmi.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setDhEmi(String value) {
                this.dhEmi = value;
            }

            /**
             * Obtm o valor da propriedade tpNF.
             *
             * @return possible object is
             * {@link String }
             */
            public String getTpNF() {
                return tpNF;
            }

            /**
             * Define o valor da propriedade tpNF.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setTpNF(String value) {
                this.tpNF = value;
            }

            /**
             * Obtm o valor da propriedade ie.
             *
             * @return possible object is
             * {@link String }
             */
            public String getIE() {
                return ie;
            }

            /**
             * Define o valor da propriedade ie.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setIE(String value) {
                this.ie = value;
            }

            /**
             * Obtm o valor da propriedade dest.
             *
             * @return possible object is
             * {@link TEvento.InfEvento.DetEvento.Dest }
             */
            public TEvento.InfEvento.DetEvento.Dest getDest() {
                return dest;
            }

            /**
             * Define o valor da propriedade dest.
             *
             * @param value allowed object is
             *              {@link TEvento.InfEvento.DetEvento.Dest }
             */
            public void setDest(TEvento.InfEvento.DetEvento.Dest value) {
                this.dest = value;
            }

            /**
             * Obtm o valor da propriedade versao.
             *
             * @return possible object is
             * {@link String }
             */
            public String getVersao() {
                return versao;
            }

            /**
             * Define o valor da propriedade versao.
             *
             * @param value allowed object is
             *              {@link String }
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
             *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}UF"/>
             *         &lt;choice>
             *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj"/>
             *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpf"/>
             *           &lt;element name="idEstrangeiro">
             *             &lt;simpleType>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                 &lt;whiteSpace value="preserve"/>
             *                 &lt;pattern value="([!-]{0}|[!-]{5,20})?"/>
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

                @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
                @XmlSchemaType(name = "string")
                protected TUf uf;
                @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe")
                protected String cnpj;
                @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/nfe")
                protected String cpf;
                @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
                protected String idEstrangeiro;
                @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/nfe")
                protected String ie;
                @XmlElement(name = "vNF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
                protected String vnf;
                @XmlElement(name = "vICMS", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
                protected String vicms;
                @XmlElement(name = "vST", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
                protected String vst;

                /**
                 * Obtm o valor da propriedade uf.
                 *
                 * @return possible object is
                 * {@link TUf }
                 */
                public TUf getUF() {
                    return uf;
                }

                /**
                 * Define o valor da propriedade uf.
                 *
                 * @param value allowed object is
                 *              {@link TUf }
                 */
                public void setUF(TUf value) {
                    this.uf = value;
                }

                /**
                 * Obtm o valor da propriedade cnpj.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getCNPJ() {
                    return cnpj;
                }

                /**
                 * Define o valor da propriedade cnpj.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setCNPJ(String value) {
                    this.cnpj = value;
                }

                /**
                 * Obtm o valor da propriedade cpf.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getCPF() {
                    return cpf;
                }

                /**
                 * Define o valor da propriedade cpf.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setCPF(String value) {
                    this.cpf = value;
                }

                /**
                 * Obtm o valor da propriedade idEstrangeiro.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getIdEstrangeiro() {
                    return idEstrangeiro;
                }

                /**
                 * Define o valor da propriedade idEstrangeiro.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setIdEstrangeiro(String value) {
                    this.idEstrangeiro = value;
                }

                /**
                 * Obtm o valor da propriedade ie.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getIE() {
                    return ie;
                }

                /**
                 * Define o valor da propriedade ie.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setIE(String value) {
                    this.ie = value;
                }

                /**
                 * Obtm o valor da propriedade vnf.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVNF() {
                    return vnf;
                }

                /**
                 * Define o valor da propriedade vnf.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVNF(String value) {
                    this.vnf = value;
                }

                /**
                 * Obtm o valor da propriedade vicms.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVICMS() {
                    return vicms;
                }

                /**
                 * Define o valor da propriedade vicms.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVICMS(String value) {
                    this.vicms = value;
                }

                /**
                 * Obtm o valor da propriedade vst.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getVST() {
                    return vst;
                }

                /**
                 * Define o valor da propriedade vst.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setVST(String value) {
                    this.vst = value;
                }

            }

        }

    }

}
