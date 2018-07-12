package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarChaveAcesso;
import br.com.samuelweb.nfe.util.validators.impl.ValidarDrawback;
import br.com.samuelweb.nfe.util.validators.impl.ValidarRE;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class DetExport {

    @NfeCampo(tipo = String.class
            , id = "I51", tag = "nDraw", validadores = {ValidarDrawback.class}
            , tamanhoMinimo = 9, tamanhoMaximo = 11, ocorrencias = 0
            , descricao = NfeConsts.DSC_NDRAW)
    private String nDraw;
    
    @NfeCampo(tipo = String.class
            , id = "I53", tag = "nRE", validadores = {ValidarRE.class}
            , tamanhoMinimo = 12, tamanhoMaximo = 12, ocorrencias = 1
            , descricao = NfeConsts.DSC_NRE)
    private String nre;

    @NfeCampo(tipo = Integer.class
            , id = "I54", tag = "chNFe", validadores = {ValidarChaveAcesso.class}
            , tamanhoMinimo = 44, tamanhoMaximo = 44, ocorrencias = 1
            , descricao = DfeConsts.DSC_REFNFE)
    private String chNFe;
    
    @NfeCampo(tipo = BigDecimal.class
            , id = "I55", tag = "qExport", decimais = 4, precisao = 15
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_QEXPORT)
    private BigDecimal qExport;

    public TNFe.InfNFe.Det.Prod.DetExport build() {
        TNFe.InfNFe.Det.Prod.DetExport detExport = new TNFe.InfNFe.Det.Prod.DetExport();
        TNFe.InfNFe.Det.Prod.DetExport.ExportInd exportInd = new TNFe.InfNFe.Det.Prod.DetExport.ExportInd();
        detExport.setNDraw(this.getnDraw());
        if ((this.getNre() != null && !this.getNre().isEmpty()) || (this.getChNFe() != null && !this.getChNFe().isEmpty())) {
            exportInd.setNRE(this.getNre());
            exportInd.setChNFe(this.getChNFe());
            if (this.getqExport() != null) {
                exportInd.setQExport(this.getqExport().toString());
            }
            detExport.setExportInd(exportInd);
        }
        return detExport;
    }

    public String getnDraw() {
        return nDraw;
    }

    public String getNre() {
        return nre;
    }

    public String getChNFe() {
        return chNFe;
    }

    public BigDecimal getqExport() {
        return qExport;
    }

    public DetExport setnDraw(String nDraw) {
        this.nDraw = nDraw;
        return this;
    }

    public DetExport setNre(String nre) {
        this.nre = nre;
        return this;
    }

    public DetExport setChNFe(String chNFe) {
        this.chNFe = chNFe;
        return this;
    }

    public DetExport setqExport(BigDecimal qExport) {
        this.qExport = qExport;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}