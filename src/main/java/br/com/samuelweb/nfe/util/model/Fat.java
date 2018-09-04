package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class Fat {

    @NfeCampo(id = "Y03", tag = "nFat", tamanhoMinimo = 1, tamanhoMaximo = 60,
            ocorrencias = 0, descricao = NfeConsts.DSC_NFAT)
    private String nFat;

    @NfeCampo(tipo = BigDecimal.class, id = "Y04", tag = "vOrig"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , decimais = 2, precisao = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_VORIG)
    private BigDecimal vOrig;

    @NfeCampo(tipo = BigDecimal.class, id = "Y05", tag = "vDesc"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , decimais = 2, precisao = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_VDESC)
    private BigDecimal vDesc;

    @NfeCampo(tipo = BigDecimal.class, id = "Y06", tag = "vLiq"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_VLIQ)
    private BigDecimal vLiq;

    public TNFe.InfNFe.Cobr.Fat build() {
        TNFe.InfNFe.Cobr.Fat fat = new TNFe.InfNFe.Cobr.Fat();

        if (StringUtils.isNotBlank(this.getnFat().trim()) ||
                (this.getvOrig() != null && this.getvOrig().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getvDesc() != null && this.getvDesc().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getvLiq() != null && this.getvLiq().compareTo(BigDecimal.ZERO) > 0)){

            fat.setNFat(this.getnFat());

            if (this.getvOrig() != null){
                fat.setVOrig(this.getvOrig().toString());
            }

            if (this.getvDesc() != null){
                fat.setVOrig(this.getvDesc().toString());
            }

            if (this.getvLiq() != null){
                fat.setVOrig(this.getvLiq().toString());
            }

            return fat;
        }
        return null;
    }

    public String getnFat() {
        return nFat;
    }

    public BigDecimal getvOrig() {
        return vOrig;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public BigDecimal getvLiq() {
        return vLiq;
    }

    public Fat setnFat(String nFat) {
        this.nFat = nFat;
        return this;
    }

    public Fat setvOrig(BigDecimal vOrig) {
        this.vOrig = vOrig;
        return this;
    }

    public Fat setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
        return this;
    }

    public Fat setvLiq(BigDecimal vLiq) {
        this.vLiq = vLiq;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}