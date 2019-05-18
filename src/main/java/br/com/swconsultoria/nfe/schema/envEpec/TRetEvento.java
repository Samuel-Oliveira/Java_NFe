
package br.com.swconsultoria.nfe.schema.envEpec;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * Tipo retorno do Evento
 *
 * <p>Classe Java de TRetEvento complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TRetEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infEvento">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
 *                   &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *                   &lt;element name="cOrgao" type="{http://www.portalfiscal.inf.br/nfe}TCOrgaoIBGE"/>
 *                   &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
 *                   &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
 *                   &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe" minOccurs="0"/>
 *                   &lt;element name="tpEvento" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[0-9]{6}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="xEvento" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                         &lt;minLength value="5"/>
 *                         &lt;maxLength value="60"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="nSeqEvento" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[1-9][0-9]{0,1}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element ref="{http://www.portalfiscal.inf.br/nfe}cOrgaoAutor"/>
 *                   &lt;element name="dhRegEvento">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="(((20(([02468][048])|([13579][26]))-02-29))|(20[0-9][0-9])-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))T(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d[\-,\+](0[0-9]|10|11|12):00"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="nProt" type="{http://www.portalfiscal.inf.br/nfe}TProt" minOccurs="0"/>
 *                   &lt;element name="chNFePend" type="{http://www.portalfiscal.inf.br/nfe}TChNFe" maxOccurs="50" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Id">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *                       &lt;pattern value="ID[0-9]{15}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerEvento" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRetEvento", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
        "infEvento",
        "signature"
})
public class TRetEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TRetEvento.InfEvento infEvento;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtm o valor da propriedade infEvento.
     *
     * @return possible object is
     * {@link TRetEvento.InfEvento }
     */
    public TRetEvento.InfEvento getInfEvento() {
        return infEvento;
    }

    /**
     * Define o valor da propriedade infEvento.
     *
     * @param value allowed object is
     *              {@link TRetEvento.InfEvento }
     */
    public void setInfEvento(TRetEvento.InfEvento value) {
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
     *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
     *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
     *         &lt;element name="cOrgao" type="{http://www.portalfiscal.inf.br/nfe}TCOrgaoIBGE"/>
     *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
     *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
     *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe" minOccurs="0"/>
     *         &lt;element name="tpEvento" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[0-9]{6}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="xEvento" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *               &lt;minLength value="5"/>
     *               &lt;maxLength value="60"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="nSeqEvento" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="[1-9][0-9]{0,1}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element ref="{http://www.portalfiscal.inf.br/nfe}cOrgaoAutor"/>
     *         &lt;element name="dhRegEvento">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="(((20(([02468][048])|([13579][26]))-02-29))|(20[0-9][0-9])-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))T(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d[\-,\+](0[0-9]|10|11|12):00"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="nProt" type="{http://www.portalfiscal.inf.br/nfe}TProt" minOccurs="0"/>
     *         &lt;element name="chNFePend" type="{http://www.portalfiscal.inf.br/nfe}TChNFe" maxOccurs="50" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Id">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
     *             &lt;pattern value="ID[0-9]{15}"/>
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
            "tpAmb",
            "verAplic",
            "cOrgao",
            "cStat",
            "xMotivo",
            "chNFe",
            "tpEvento",
            "xEvento",
            "nSeqEvento",
            "cOrgaoAutor",
            "dhRegEvento",
            "nProt",
            "chNFePend"
    })
    public static class InfEvento {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tpAmb;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String verAplic;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cOrgao;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cStat;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String xMotivo;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String chNFe;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String tpEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String xEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String nSeqEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cOrgaoAutor;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String dhRegEvento;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String nProt;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected List<String> chNFePend;
        @XmlAttribute(name = "Id")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;

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
         * Obtm o valor da propriedade cStat.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCStat() {
            return cStat;
        }

        /**
         * Define o valor da propriedade cStat.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCStat(String value) {
            this.cStat = value;
        }

        /**
         * Obtm o valor da propriedade xMotivo.
         *
         * @return possible object is
         * {@link String }
         */
        public String getXMotivo() {
            return xMotivo;
        }

        /**
         * Define o valor da propriedade xMotivo.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setXMotivo(String value) {
            this.xMotivo = value;
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
         * Obtm o valor da propriedade xEvento.
         *
         * @return possible object is
         * {@link String }
         */
        public String getXEvento() {
            return xEvento;
        }

        /**
         * Define o valor da propriedade xEvento.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setXEvento(String value) {
            this.xEvento = value;
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
         * Obtm o valor da propriedade dhRegEvento.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDhRegEvento() {
            return dhRegEvento;
        }

        /**
         * Define o valor da propriedade dhRegEvento.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDhRegEvento(String value) {
            this.dhRegEvento = value;
        }

        /**
         * Obtm o valor da propriedade nProt.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNProt() {
            return nProt;
        }

        /**
         * Define o valor da propriedade nProt.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNProt(String value) {
            this.nProt = value;
        }

        /**
         * Gets the value of the chNFePend property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chNFePend property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChNFePend().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         */
        public List<String> getChNFePend() {
            if (chNFePend == null) {
                chNFePend = new ArrayList<String>();
            }
            return this.chNFePend;
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

    }

}
