package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
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

        if (StringUtils.isNotBlank(this.nFat.trim()) ||
                (this.vOrig != null && this.vOrig.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vDesc != null && this.vDesc.compareTo(BigDecimal.ZERO) > 0) ||
                (this.vLiq != null && this.vLiq.compareTo(BigDecimal.ZERO) > 0)){

            fat.setNFat(this.nFat);

            if (this.vOrig != null){
                fat.setVOrig(this.vOrig.toString());
            }

            if (this.vDesc != null){
                fat.setVOrig(this.vDesc.toString());
            }

            if (this.vLiq != null){
                fat.setVOrig(this.vLiq.toString());
            }

            return fat;
        }
        return null;
    }

    public String getnFat() {
        return nFat;
    }

    public void setnFat(String nFat) {
        this.nFat = nFat;
    }

    public BigDecimal getvOrig() {
        return vOrig;
    }

    public void setvOrig(BigDecimal vOrig) {
        this.vOrig = vOrig;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public void setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
    }

    public BigDecimal getvLiq() {
        return vLiq;
    }

    public void setvLiq(BigDecimal vLiq) {
        this.vLiq = vLiq;
    }
}
