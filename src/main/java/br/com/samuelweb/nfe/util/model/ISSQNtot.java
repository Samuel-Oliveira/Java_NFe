package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.RegimeTributario;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ISSQNtot {

    @NfeCampo(tipo = BigDecimal.class, id = "W18", tag = "vServ"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VSERV)
    private BigDecimal vServ;

    @NfeCampo(tipo = BigDecimal.class, id = "W19", tag = "vBC"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VBC)
    private BigDecimal vbc;

    @NfeCampo(tipo = BigDecimal.class, id = "W20", tag = "vISS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VISS)
    private BigDecimal viss;

    @NfeCampo(tipo = BigDecimal.class, id = "W21", tag = "vPIS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VPIS)
    private BigDecimal vpis;

    @NfeCampo(tipo = BigDecimal.class, id = "W22", tag = "vCOFINS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VCOFINS)
    private BigDecimal vcofins;

    @NfeCampo(tipo = LocalDate.class, id = "W22a", tag = "dCompet"
            , tamanhoMinimo = 10, tamanhoMaximo = 10, ocorrencias = 1
            , descricao = NfeConsts.DSC_DCOMPET)
    private LocalDate dCompet;

    @NfeCampo(tipo = BigDecimal.class, id = "W22b", tag = "vDeducao"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VDEDUCAO)
    private BigDecimal vDeducao;

    @NfeCampo(tipo = BigDecimal.class, id = "W22c", tag = "vOutro"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VOUTRODED)
    private BigDecimal vOutro;

    @NfeCampo(tipo = BigDecimal.class, id = "W22d", tag = "vDescIncond"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VDESCINCOND)
    private BigDecimal vDescIncond;

    @NfeCampo(tipo = BigDecimal.class, id = "W22e", tag = "vDescCond"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VDESCCOND)
    private BigDecimal vDescCond;

    @NfeCampo(tipo = BigDecimal.class, id = "W22f", tag = "vISSRet"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VISSRET)
    private BigDecimal vissRet;

    @NfeCampo(tipo = RegimeTributario.class, id = "W22g", tag = "cRegTrib"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 0
            , descricao = NfeConsts.DSC_CREGTRIB)
    private RegimeTributario cRegTrib;

    public TNFe.InfNFe.Total.ISSQNtot build() {
        if ((this.getvServ().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getVbc().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getViss().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getVpis().compareTo(BigDecimal.ZERO) > 0) ||
                (this.getVcofins().compareTo(BigDecimal.ZERO) > 0)) {

            TNFe.InfNFe.Total.ISSQNtot issqNtot = new TNFe.InfNFe.Total.ISSQNtot();

            if (this.getvServ() != null) {
                issqNtot.setVServ(this.getvServ().toString());
            }
            if (this.getVbc() != null) {
                issqNtot.setVBC(this.getVbc().toString());
            }
            if (this.getViss() != null) {
                issqNtot.setVISS(this.getViss().toString());
            }
            if (this.getVpis() != null) {
                issqNtot.setVPIS(this.getVpis().toString());
            }
            if (this.getVcofins() != null) {
                issqNtot.setVCOFINS(this.getVcofins().toString());
            }
            if (this.getvDeducao() != null) {
                issqNtot.setVDeducao(this.getvDeducao().toString());
            }
            if (this.getvOutro() != null) {
                issqNtot.setVOutro(this.getvOutro().toString());
            }
            if (this.getvDescIncond() != null) {
                issqNtot.setVDescIncond(this.getvDescIncond().toString());
            }
            if (this.getvDescCond() != null) {
                issqNtot.setVDescCond(this.getvDescCond().toString());
            }
            if (this.getVissRet() != null) {
                issqNtot.setVISSRet(this.getVissRet().toString());
            }
            if (this.getdCompet() != null) {
                issqNtot.setDCompet(this.getdCompet().format(DateTimeFormatter.ISO_DATE));
            }
            if (this.getcRegTrib() != null) {
                issqNtot.setCRegTrib(this.getcRegTrib().getValue().toString());
            }
            return issqNtot;
        }
        return null;
    }

    public BigDecimal getvServ() {
        return vServ;
    }

    public void setvServ(BigDecimal vServ) {
        this.vServ = vServ;
    }

    public BigDecimal getVbc() {
        return vbc;
    }

    public void setVbc(BigDecimal vbc) {
        this.vbc = vbc;
    }

    public BigDecimal getViss() {
        return viss;
    }

    public void setViss(BigDecimal viss) {
        this.viss = viss;
    }

    public BigDecimal getVpis() {
        return vpis;
    }

    public void setVpis(BigDecimal vpis) {
        this.vpis = vpis;
    }

    public BigDecimal getVcofins() {
        return vcofins;
    }

    public void setVcofins(BigDecimal vcofins) {
        this.vcofins = vcofins;
    }

    public LocalDate getdCompet() {
        return dCompet;
    }

    public void setdCompet(LocalDate dCompet) {
        this.dCompet = dCompet;
    }

    public BigDecimal getvDeducao() {
        return vDeducao;
    }

    public void setvDeducao(BigDecimal vDeducao) {
        this.vDeducao = vDeducao;
    }

    public BigDecimal getvOutro() {
        return vOutro;
    }

    public void setvOutro(BigDecimal vOutro) {
        this.vOutro = vOutro;
    }

    public BigDecimal getvDescIncond() {
        return vDescIncond;
    }

    public void setvDescIncond(BigDecimal vDescIncond) {
        this.vDescIncond = vDescIncond;
    }

    public BigDecimal getvDescCond() {
        return vDescCond;
    }

    public void setvDescCond(BigDecimal vDescCond) {
        this.vDescCond = vDescCond;
    }

    public BigDecimal getVissRet() {
        return vissRet;
    }

    public void setVissRet(BigDecimal vissRet) {
        this.vissRet = vissRet;
    }

    public RegimeTributario getcRegTrib() {
        return cRegTrib;
    }

    public void setcRegTrib(RegimeTributario cRegTrib) {
        this.cRegTrib = cRegTrib;
    }
}
