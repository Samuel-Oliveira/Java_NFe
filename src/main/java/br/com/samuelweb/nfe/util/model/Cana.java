package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class Cana {

    @NfeCampo(tipo = String.class
            , id = "ZC02", tag = "safra"
            , tamanhoMinimo = 4, tamanhoMaximo = 9, ocorrencias = 0
            , descricao = NfeConsts.DSC_SAFRA)
    private String safra;

    @NfeCampo(tipo = String.class
            , id = "ZC03", tag = "ref"
            , tamanhoMinimo = 4, tamanhoMaximo = 9, ocorrencias = 0
            , descricao = NfeConsts.DSC_REF)
    private String ref;

    @NfeCampo(tipo = BigDecimal.class
            , id = "ZC14", tag = "vTotDed", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VTOTDED)
    private BigDecimal vTotDed;

    @NfeCampo(tipo = BigDecimal.class
            , id = "ZC15", tag = "vLiqFor"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , precisao = 15, decimais = 2, ocorrencias = 1,
            descricao = NfeConsts.DSC_VLIQFOR)
    private BigDecimal vLiqFor;

    @NfeCampo(tipo = BigDecimal.class
            , id = "ZC07", tag = "qTotMes"
            , tamanhoMinimo = 1, tamanhoMaximo = 21, ocorrencias = 1
            , decimais = 10, precisao = 21
            , descricao = NfeConsts.DSC_QTOTMES)
    private BigDecimal qTotMes;

    @NfeCampo(tipo = BigDecimal.class, id = "ZC08", tag = "qTotAnt"
            , tamanhoMinimo = 1, tamanhoMaximo = 21, ocorrencias = 1
            , decimais = 10, precisao = 21
            , descricao = NfeConsts.DSC_QTOTANT)
    private BigDecimal qTotAnt;

    @NfeCampo(tipo = BigDecimal.class, id = "ZC09", tag = "qTotGer"
            , tamanhoMinimo = 1, tamanhoMaximo = 21, ocorrencias = 1
            , decimais = 10, precisao = 21
            , descricao = NfeConsts.DSC_TOTGER)
    private BigDecimal qTotGer;

    @NfeCampo(tipo = BigDecimal.class, id = "ZC13", tag = "vFor"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VFOR)
    private String vFor;

    @NfeObjetoList(id = "ZC04", tag = "forDia", ocorrenciaMinima = 1, ocorrenciaMaxima = 31, descricao = NfeConsts.DSC_FORDIA)
    private List<ForDia> forDia;

    @NfeObjetoList(id = "ZC10", tag = "deduc", ocorrenciaMinima = 0, ocorrenciaMaxima = 10, descricao = NfeConsts.DSC_DEDUC)
    private List<Deduc> deduc;

    public TNFe.InfNFe.Cana build() {
        if ((StringUtils.isNotBlank(this.getSafra()) || StringUtils.isNotBlank(this.getRef())) ||
                ((this.getForDia() != null && this.getForDia().size() > 0) || (this.getDeduc() != null && this.getDeduc().size() > 0))) {

            TNFe.InfNFe.Cana cana = new TNFe.InfNFe.Cana();

            cana.setSafra(this.getSafra());
            cana.setRef(this.getRef());

            if (this.getForDia() != null) {
                this.getForDia().forEach(dia -> cana.getForDia().add(dia.build()));
            }

            if (this.getDeduc() != null){
                this.getDeduc().forEach(deduc -> cana.getDeduc().add(deduc.build()));
            }
            if (this.getvTotDed() != null) {
                cana.setVTotDed(this.getvTotDed().toString());
            }

            if (this.getvLiqFor() != null) {
                cana.setVLiqFor(this.getvLiqFor().toString());
            }

            if (this.getqTotMes() != null) {
                cana.setQTotMes(this.getqTotMes().toString());
            }

            if (this.getqTotAnt() != null) {
                cana.setQTotAnt(this.getqTotAnt().toString());
            }

            if (this.getqTotGer() != null) {
                cana.setQTotGer(this.getqTotGer().toString());
            }
            return cana;
        }
        return null;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public BigDecimal getvTotDed() {
        return vTotDed;
    }

    public void setvTotDed(BigDecimal vTotDed) {
        this.vTotDed = vTotDed;
    }

    public BigDecimal getvLiqFor() {
        return vLiqFor;
    }

    public void setvLiqFor(BigDecimal vLiqFor) {
        this.vLiqFor = vLiqFor;
    }

    public BigDecimal getqTotMes() {
        return qTotMes;
    }

    public void setqTotMes(BigDecimal qTotMes) {
        this.qTotMes = qTotMes;
    }

    public BigDecimal getqTotAnt() {
        return qTotAnt;
    }

    public void setqTotAnt(BigDecimal qTotAnt) {
        this.qTotAnt = qTotAnt;
    }

    public BigDecimal getqTotGer() {
        return qTotGer;
    }

    public void setqTotGer(BigDecimal qTotGer) {
        this.qTotGer = qTotGer;
    }

    public String getvFor() {
        return vFor;
    }

    public void setvFor(String vFor) {
        this.vFor = vFor;
    }

    public List<ForDia> getForDia() {
        return forDia;
    }

    public void setForDia(List<ForDia> forDia) {
        this.forDia = forDia;
    }

    public List<Deduc> getDeduc() {
        return deduc;
    }

    public void setDeduc(List<Deduc> deduc) {
        this.deduc = deduc;
    }
}
