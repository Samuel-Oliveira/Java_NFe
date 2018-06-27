package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class Cobr {

    @NfeObjeto(id = "Y02", tag = "fat", ocorrencias = 0, descricao = NfeConsts.DSC_GFAT)
    private Fat fat;

    @NfeObjetoList(id = "Y07", tag = "dup", ocorrenciaMinima = 0, ocorrenciaMaxima = 120, descricao = NfeConsts.DSC_GDUP)
    private List<Dup> dup;

    public TNFe.InfNFe.Cobr build() {
        TNFe.InfNFe.Cobr cobr = new TNFe.InfNFe.Cobr();

        if (this.getFat() != null && ((StringUtils.isNotBlank(this.getFat().getnFat().trim())) ||
                (this.getFat().getvOrig().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getFat().getvDesc().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getFat().getvLiq().compareTo(BigDecimal.ZERO) > 0))) {

            cobr.setFat(this.getFat().build());

            if (this.getDup() != null && this.getDup().size() > 0) {
                this.getDup().forEach(e -> cobr.getDup().add(e.build()));
            }

            return cobr;
        }
        return null;
    }

    public Fat getFat() {
        return fat;
    }

    public List<Dup> getDup() {
        return dup;
    }

    public Cobr setFat(Fat fat) {
        this.fat = fat;
        return this;
    }

    public Cobr setDup(List<Dup> dup) {
        this.dup = dup;
        return this;
    }
}