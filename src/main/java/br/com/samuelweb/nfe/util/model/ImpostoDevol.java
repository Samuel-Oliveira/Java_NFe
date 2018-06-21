package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class ImpostoDevol {

    @NfeCampo(tipo = BigDecimal.class
            , id = "U51", tag = "pDevol", decimais = 2, precisao = 5
            , tamanhoMinimo = 1, tamanhoMaximo = 5, ocorrencias = 1
            , descricao = NfeConsts.DSC_PDEVOL)
    private BigDecimal pDevol;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U61", tag = "vIPIDevol", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VIPIDEVOL)
    private BigDecimal vIPIDevol;

    public TNFe.InfNFe.Det.ImpostoDevol build() {
        TNFe.InfNFe.Det.ImpostoDevol impostoDevol = new TNFe.InfNFe.Det.ImpostoDevol();
        if (this.pDevol != null) {
            impostoDevol.setPDevol(this.pDevol.toString());
        }
        if (this.vIPIDevol != null) {
            TNFe.InfNFe.Det.ImpostoDevol.IPI ipi = new TNFe.InfNFe.Det.ImpostoDevol.IPI();
            ipi.setVIPIDevol(this.vIPIDevol.toString());
            impostoDevol.setIPI(ipi);
        }
        return impostoDevol;
    }

    public BigDecimal getpDevol() {
        return pDevol;
    }

    public void setpDevol(BigDecimal pDevol) {
        this.pDevol = pDevol;
    }

    public BigDecimal getvIPIDevol() {
        return vIPIDevol;
    }

    public void setvIPIDevol(BigDecimal vIPIDevol) {
        this.vIPIDevol = vIPIDevol;
    }
}
