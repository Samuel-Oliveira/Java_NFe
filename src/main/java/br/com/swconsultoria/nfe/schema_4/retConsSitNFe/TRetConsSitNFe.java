
package br.com.swconsultoria.nfe.schema_4.retConsSitNFe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Retorno de Pedido de Consulta da Situao Atual da Nota Fiscal Eletrnica
 * 
 * <p>Classe Java de TRetConsSitNFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TRetConsSitNFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
 *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
 *         &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *         &lt;element name="dhRecbto" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/>
 *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
 *         &lt;element name="protNFe" type="{http://www.portalfiscal.inf.br/nfe}TProtNFe" minOccurs="0"/>
 *         &lt;element name="retCancNFe" type="{http://www.portalfiscal.inf.br/nfe}TRetCancNFe" minOccurs="0"/>
 *         &lt;element name="procEventoNFe" type="{http://www.portalfiscal.inf.br/nfe}TProcEvento" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerConsSitNFe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRetConsSitNFe", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
    "tpAmb",
    "verAplic",
    "cStat",
    "xMotivo",
    "cuf",
    "dhRecbto",
    "chNFe",
    "protNFe",
    "retCancNFe",
    "procEventoNFe"
})
public class TRetConsSitNFe {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String tpAmb;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cStat;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String xMotivo;
    @XmlElement(name = "cUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String cuf;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String dhRecbto;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String chNFe;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TProtNFe protNFe;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected TRetCancNFe retCancNFe;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected List<TProcEvento> procEventoNFe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

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
     * Obtm o valor da propriedade verAplic.
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
     * Obtm o valor da propriedade cStat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCStat() {
        return cStat;
    }

    /**
     * Define o valor da propriedade cStat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCStat(String value) {
        this.cStat = value;
    }

    /**
     * Obtm o valor da propriedade xMotivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMotivo() {
        return xMotivo;
    }

    /**
     * Define o valor da propriedade xMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMotivo(String value) {
        this.xMotivo = value;
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
     * Obtm o valor da propriedade dhRecbto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDhRecbto() {
        return dhRecbto;
    }

    /**
     * Define o valor da propriedade dhRecbto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDhRecbto(String value) {
        this.dhRecbto = value;
    }

    /**
     * Obtm o valor da propriedade chNFe.
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

    /**
     * Obtm o valor da propriedade protNFe.
     * 
     * @return
     *     possible object is
     *     {@link TProtNFe }
     *     
     */
    public TProtNFe getProtNFe() {
        return protNFe;
    }

    /**
     * Define o valor da propriedade protNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link TProtNFe }
     *     
     */
    public void setProtNFe(TProtNFe value) {
        this.protNFe = value;
    }

    /**
     * Obtm o valor da propriedade retCancNFe.
     * 
     * @return
     *     possible object is
     *     {@link TRetCancNFe }
     *     
     */
    public TRetCancNFe getRetCancNFe() {
        return retCancNFe;
    }

    /**
     * Define o valor da propriedade retCancNFe.
     * 
     * @param value
     *     allowed object is
     *     {@link TRetCancNFe }
     *     
     */
    public void setRetCancNFe(TRetCancNFe value) {
        this.retCancNFe = value;
    }

    /**
     * Gets the value of the procEventoNFe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the procEventoNFe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcEventoNFe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TProcEvento }
     * 
     * 
     */
    public List<TProcEvento> getProcEventoNFe() {
        if (procEventoNFe == null) {
            procEventoNFe = new ArrayList<TProcEvento>();
        }
        return this.procEventoNFe;
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

}
