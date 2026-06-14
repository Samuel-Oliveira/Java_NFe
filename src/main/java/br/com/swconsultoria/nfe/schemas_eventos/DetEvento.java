
package br.com.swconsultoria.nfe.schemas_eventos;

import br.com.swconsultoria.nfe.schemas.TUfEmi;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe superset para o elemento global &lt;detEvento&gt; da NF-e.
 *
 * <p>Cobre os campos comuns a todos os schemas de detEvento utilizados
 * pelos eventos da SEFAZ, incluindo os campos especificos de ECONF (e110750)
 * e do evento 112110, que nao podem ser consolidados via xjc por conflito
 * de tipos entre os schemas.</p>
 *
 * <p>Campos opcionais: todos os elementos sao marcados como nao-required
 * para permitir uso por diferentes tipos de evento.</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "descEvento",
    "cOrgaoAutor",
    "tpAutor",
    "verAplic",
    "nProtEvento",
    "indQuitacao",
    "detPag"
})
@XmlRootElement(name = "detEvento", namespace = "http://www.portalfiscal.inf.br/nfe")
public class DetEvento {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String cOrgaoAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String tpAutor;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String verAplic;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String nProtEvento;
    /** Indicador de quitacao — evento 112110. */
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
    protected String indQuitacao;
    /** Detalhamento de pagamento — evento ECONF (110750). */
    @XmlElement(name = "detPag", namespace = "http://www.portalfiscal.inf.br/nfe")
    protected List<DetPag> detPag;
    @XmlAttribute(name = "versao")
    protected String versao;

    public String getDescEvento() { return descEvento; }
    public void setDescEvento(String value) { this.descEvento = value; }

    public String getCOrgaoAutor() { return cOrgaoAutor; }
    public void setCOrgaoAutor(String value) { this.cOrgaoAutor = value; }

    public String getTpAutor() { return tpAutor; }
    public void setTpAutor(String value) { this.tpAutor = value; }

    public String getVerAplic() { return verAplic; }
    public void setVerAplic(String value) { this.verAplic = value; }

    public String getNProtEvento() { return nProtEvento; }
    public void setNProtEvento(String value) { this.nProtEvento = value; }

    public String getIndQuitacao() { return indQuitacao; }
    public void setIndQuitacao(String value) { this.indQuitacao = value; }

    public List<DetPag> getDetPag() {
        if (detPag == null) {
            detPag = new ArrayList<DetPag>();
        }
        return detPag;
    }

    public String getVersao() { return versao; }
    public void setVersao(String value) { this.versao = value; }

    /**
     * Detalhamento de pagamento para o evento ECONF (110750).
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "indPag", "tPag", "xPag", "vPag", "dPag",
        "cnpjPag", "ufPag", "cnpjif", "tBand", "cAut",
        "cnpjReceb", "ufReceb"
    })
    public static class DetPag {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String indPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String xPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String vPag;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String dPag;
        @XmlElement(name = "CNPJPag", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpjPag;
        @XmlElement(name = "UFPag", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TUfEmi ufPag;
        @XmlElement(name = "CNPJIF", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpjif;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String tBand;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cAut;
        @XmlElement(name = "CNPJReceb", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpjReceb;
        @XmlElement(name = "UFReceb", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected TUfEmi ufReceb;

        public String getIndPag() { return indPag; }
        public void setIndPag(String value) { this.indPag = value; }

        public String getTPag() { return tPag; }
        public void setTPag(String value) { this.tPag = value; }

        public String getXPag() { return xPag; }
        public void setXPag(String value) { this.xPag = value; }

        public String getVPag() { return vPag; }
        public void setVPag(String value) { this.vPag = value; }

        public String getDPag() { return dPag; }
        public void setDPag(String value) { this.dPag = value; }

        public String getCNPJPag() { return cnpjPag; }
        public void setCNPJPag(String value) { this.cnpjPag = value; }

        public TUfEmi getUFPag() { return ufPag; }
        public void setUFPag(TUfEmi value) { this.ufPag = value; }

        public String getCNPJIF() { return cnpjif; }
        public void setCNPJIF(String value) { this.cnpjif = value; }

        public String getTBand() { return tBand; }
        public void setTBand(String value) { this.tBand = value; }

        public String getCAut() { return cAut; }
        public void setCAut(String value) { this.cAut = value; }

        public String getCNPJReceb() { return cnpjReceb; }
        public void setCNPJReceb(String value) { this.cnpjReceb = value; }

        public TUfEmi getUFReceb() { return ufReceb; }
        public void setUFReceb(TUfEmi value) { this.ufReceb = value; }
    }

}
