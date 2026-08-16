
package br.com.swconsultoria.nfe.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Informado para abater as parcelas de antecipação de pagamento, conforme art. 10 §4
 * 
 * <p>Classe Java de TPagRef complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TPagRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="refDFe" type="{http://www.portalfiscal.inf.br/nfe}TChDFeRTC" maxOccurs="99"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPagRef", propOrder = {
    "refDFe"
})
public class TPagRef {

    @XmlElement(required = true)
    protected List<String> refDFe;

    /**
     * Gets the value of the refDFe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refDFe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefDFe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRefDFe() {
        if (refDFe == null) {
            refDFe = new ArrayList<String>();
        }
        return this.refDFe;
    }

}
