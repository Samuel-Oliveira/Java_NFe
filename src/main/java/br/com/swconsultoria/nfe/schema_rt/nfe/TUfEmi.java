
package br.com.swconsultoria.nfe.schema_rt.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de TUfEmi.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TUfEmi">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;whiteSpace value="preserve"/>
 *     &lt;enumeration value="AC"/>
 *     &lt;enumeration value="AL"/>
 *     &lt;enumeration value="AM"/>
 *     &lt;enumeration value="AP"/>
 *     &lt;enumeration value="BA"/>
 *     &lt;enumeration value="CE"/>
 *     &lt;enumeration value="DF"/>
 *     &lt;enumeration value="ES"/>
 *     &lt;enumeration value="GO"/>
 *     &lt;enumeration value="MA"/>
 *     &lt;enumeration value="MG"/>
 *     &lt;enumeration value="MS"/>
 *     &lt;enumeration value="MT"/>
 *     &lt;enumeration value="PA"/>
 *     &lt;enumeration value="PB"/>
 *     &lt;enumeration value="PE"/>
 *     &lt;enumeration value="PI"/>
 *     &lt;enumeration value="PR"/>
 *     &lt;enumeration value="RJ"/>
 *     &lt;enumeration value="RN"/>
 *     &lt;enumeration value="RO"/>
 *     &lt;enumeration value="RR"/>
 *     &lt;enumeration value="RS"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="SE"/>
 *     &lt;enumeration value="SP"/>
 *     &lt;enumeration value="TO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TUfEmi", namespace = "http://www.portalfiscal.inf.br/nfe")
@XmlEnum
public enum TUfEmi {

    AC,
    AL,
    AM,
    AP,
    BA,
    CE,
    DF,
    ES,
    GO,
    MA,
    MG,
    MS,
    MT,
    PA,
    PB,
    PE,
    PI,
    PR,
    RJ,
    RN,
    RO,
    RR,
    RS,
    SC,
    SE,
    SP,
    TO;

    public String value() {
        return name();
    }

    public static TUfEmi fromValue(String v) {
        return valueOf(v);
    }

}
