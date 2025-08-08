package br.com.swconsultoria.nfe.impressao;

import net.sf.jasperreports.engine.JasperReport;

public enum JasperNFeEnum {

    NFE("/nfe/danfe"),
    NFE_FATURA("/nfe/danfe_fatura"),
    NFCE("/nfce/danfce"),
    CCE("/cce/cce");

    private final String caminho;

    JasperNFeEnum(String caminho) {
        this.caminho = caminho;
    }

    public JasperReport getJasper() {
        String caminhoJasper = "/jasper" + caminho + ".jasper";
        return ImpressaoNfeUtil.carregaJasperResources(caminhoJasper);
    }
}
