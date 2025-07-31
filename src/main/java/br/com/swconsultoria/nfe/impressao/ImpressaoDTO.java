package br.com.swconsultoria.nfe.impressao;

import net.sf.jasperreports.engine.JasperReport;

import java.util.HashMap;
import java.util.Map;

/**
 * Model resposnsavel por armazenar as informações da Impressao
 */
public class ImpressaoDTO {

    private String xml;
    private JasperReport jasper;
    private String pathExpression;
    private Map<String, Object> parametros;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public JasperReport getJasper() {
        return jasper;
    }

    public void setJasper(JasperReport jasper) {
        this.jasper = jasper;
    }

    public String getPathExpression() {
        return pathExpression;
    }

    public void setPathExpression(String pathExpression) {
        this.pathExpression = pathExpression;
    }

    public Map<String, Object> getParametros() {
        if (this.parametros == null) {
            this.parametros = new HashMap<>();
        }
        return parametros;
    }
}
