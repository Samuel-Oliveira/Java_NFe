
package br.com.swconsultoria.nfe.schema.retEnvEpec;

import javax.xml.bind.annotation.*;


/**
 * Tipo procEvento
 *
 * <p>Classe Java de TProcEvento complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contedo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TProcEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="evento" type="{http://www.portalfiscal.inf.br/nfe}TEvento"/>
 *         &lt;element name="retEvento" type="{http://www.portalfiscal.inf.br/nfe}TRetEvento"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerEvento" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TProcEvento", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
        "evento",
        "retEvento"
})
public class TProcEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TEvento evento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TRetEvento retEvento;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtm o valor da propriedade evento.
     *
     * @return possible object is
     * {@link TEvento }
     */
    public TEvento getEvento() {
        return evento;
    }

    /**
     * Define o valor da propriedade evento.
     *
     * @param value allowed object is
     *              {@link TEvento }
     */
    public void setEvento(TEvento value) {
        this.evento = value;
    }

    /**
     * Obtm o valor da propriedade retEvento.
     *
     * @return possible object is
     * {@link TRetEvento }
     */
    public TRetEvento getRetEvento() {
        return retEvento;
    }

    /**
     * Define o valor da propriedade retEvento.
     *
     * @param value allowed object is
     *              {@link TRetEvento }
     */
    public void setRetEvento(TRetEvento value) {
        this.retEvento = value;
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

}
