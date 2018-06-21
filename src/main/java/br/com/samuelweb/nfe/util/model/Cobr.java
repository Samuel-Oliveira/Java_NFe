package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

public class Cobr {

    @NfeCampo(tipo = Fat.class, id = "Y02", tag = "fat", ocorrencias = 0, descricao = NfeConsts.DSC_GFAT)
    private Fat fat;

    @NfeCampo(tipo = Fat.class, id = "Y07", tag = "dup", ocorrencias = 0, descricao = NfeConsts.DSC_GDUP)
    private List<Dup> dup;

    public TNFe.InfNFe.Cobr build() {
        TNFe.InfNFe.Cobr cobr = new TNFe.InfNFe.Cobr();

        if (this.fat != null && ((StringUtils.isNotBlank(this.fat.getnFat().trim())) ||
                (this.fat.getvOrig().compareTo(BigDecimal.ZERO) > 0) ||
                (this.fat.getvDesc().compareTo(BigDecimal.ZERO) > 0) ||
                (this.fat.getvLiq().compareTo(BigDecimal.ZERO) > 0))) {

            cobr.setFat(this.fat.build());

            if (this.dup != null && this.dup.size() > 0) {
                this.dup.forEach(e -> cobr.getDup().add(e.build()));
            }

            return cobr;
        }
        return null;
    }

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public List<Dup> getDup() {
        return dup;
    }

    public void setDup(List<Dup> dup) {
        this.dup = dup;
    }
}
