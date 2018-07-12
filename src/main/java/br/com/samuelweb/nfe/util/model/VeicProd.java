package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarTpRestricao;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class VeicProd {

    @NfeCampo(tipo = String.class
            , id = "J02", tag = "tpOp"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPOP)
    private String tpOp;

    @NfeCampo(tipo = String.class
            , id = "J03", tag = "chassi"
            , tamanhoMinimo = 17, tamanhoMaximo = 17, ocorrencias = 1
            , descricao = DfeConsts.DSC_CHASSI)
    private String chassi;

    @NfeCampo(tipo = String.class
            , id = "J04", tag = "cCor"
            , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = DfeConsts.DSC_CCOR)
    private String cCor;

    @NfeCampo(tipo = String.class
            , id = "J05", tag = "xCor"
            , tamanhoMinimo = 1, tamanhoMaximo = 40, ocorrencias = 1
            , descricao = DfeConsts.DSC_XCOR)
    private String xCor;

    @NfeCampo(tipo = String.class
            , id = "J06", tag = "pot"
            , tamanhoMinimo = 1, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_POT)
    private String pot;

    @NfeCampo(tipo = String.class
            , id = "J07", tag = "cilin"
            , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_CILIN)
    private String cilin;

    @NfeCampo(tipo = String.class
            , id = "J08", tag = "pesoL"
            , tamanhoMinimo = 0, tamanhoMaximo = 9, ocorrencias = 1
            , descricao = NfeConsts.DSC_PESOL)
    private String pesoL;

    @NfeCampo(tipo = String.class
            , id = "J09", tag = "pesoB"
            , tamanhoMinimo = 0, tamanhoMaximo = 9, ocorrencias = 1
            , descricao = NfeConsts.DSC_PESOB)
    private String pesoB;

    @NfeCampo(tipo = String.class
            , id = "J10", tag = "nSerie"
            , tamanhoMinimo = 0, tamanhoMaximo = 9, ocorrencias = 1
            , descricao = DfeConsts.DSC_NSERIE)
    private String nSerie;

    @NfeCampo(tipo = String.class
            , id = "J11", tag = "tpComb"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPCOMB)
    private String tpComb;

    @NfeCampo(tipo = String.class
            , id = "J12", tag = "nMotor"
            , tamanhoMinimo = 0, tamanhoMaximo = 21, ocorrencias = 1
            , descricao = NfeConsts.DSC_NMOTOR)
    private String nMotor;

    @NfeCampo(tipo = String.class
            , id = "J13", tag = "CMT"
            , tamanhoMinimo = 9, tamanhoMaximo = 9, ocorrencias = 1
            , descricao = NfeConsts.DSC_CMT)
    private String cmt;

    @NfeCampo(tipo = String.class
            , id = "J14", tag = "dist"
            , tamanhoMinimo = 0, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_DIST)
    private String dist;

    @NfeCampo(tipo = Integer.class
            , id = "J16", tag = "anoMod"
            , tamanhoMinimo = 0, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_ANOMOD)
    private Integer anoMod;

    @NfeCampo(tipo = Integer.class
            , id = "J17", tag = "anoFab"
            , tamanhoMinimo = 0, tamanhoMaximo = 4, ocorrencias = 1
            , descricao = NfeConsts.DSC_ANOFAB)
    private Integer anoFab;

    @NfeCampo(tipo = String.class
            , id = "J18", tag = "tpPint"
            , tamanhoMinimo = 0, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPPINT)
    private String tpPint;

    @NfeCampo(tipo = Integer.class
            , id = "J19", tag = "tpVeic"
            , tamanhoMinimo = 0, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_TPVEIC)
    private Integer tpVeic;

    @NfeCampo(tipo = Integer.class
            , id = "J20", tag = "espVeic"
            , tamanhoMinimo = 0, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_ESPVEIC)
    private Integer espVeic;

    @NfeCampo(tipo = String.class
            , id = "J21", tag = "VIN"
            , tamanhoMinimo = 0, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_VIN)
    private String vin;

    @NfeCampo(tipo = Integer.class
            , id = "J22", tag = "condVeic"
            , tamanhoMinimo = 0, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_CONDVEIC)
    private Integer condVeic;

    @NfeCampo(tipo = Integer.class
            , id = "J23", tag = "cMod"
            , tamanhoMinimo = 0, tamanhoMaximo = 6, ocorrencias = 1
            , descricao = DfeConsts.DSC_CMOD)
    private Integer cMod;

    @NfeCampo(tipo = Integer.class
            , id = "J24", tag = "cCorDENATRAN"
            , tamanhoMinimo = 1, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = NfeConsts.DSC_CCORDEN)
    private Integer cCorDENATRAN;

    @NfeCampo(tipo = Integer.class
            , id = "J25", tag = "lota"
            , tamanhoMinimo = 1, tamanhoMaximo = 3, ocorrencias = 1
            , descricao = DfeConsts.DSC_LOTA)
    private Integer lota;

    @NfeCampo(tipo = Integer.class
            , id = "J26", tag = "tpRest", validadores = {ValidarTpRestricao.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPREST)
    private Integer tpRest;


    public TNFe.InfNFe.Det.Prod.VeicProd build() {
        if (this.getChassi() != null && this.getChassi().isEmpty()) {
            return null;
        }
        TNFe.InfNFe.Det.Prod.VeicProd veicProd = new TNFe.InfNFe.Det.Prod.VeicProd();
        veicProd.setTpOp(this.getTpOp());
        veicProd.setChassi(this.getChassi());
        veicProd.setCCor(this.getcCor());
        veicProd.setXCor(this.getxCor());
        veicProd.setPot(this.getPot());
        veicProd.setCilin(this.getCilin());
        veicProd.setPesoL(this.getPesoL());
        veicProd.setPesoB(this.getPesoB());
        veicProd.setNSerie(this.getnSerie());
        veicProd.setTpComb(this.getTpComb());
        veicProd.setNMotor(this.getnMotor());
        veicProd.setCMT(this.getCmt());
        veicProd.setDist(this.getDist());
        if (this.getAnoMod() != null) {
            veicProd.setAnoMod(this.getAnoMod().toString());
        }
        if (this.getAnoFab() != null) {
            veicProd.setAnoFab(this.getAnoFab().toString());
        }
        veicProd.setTpPint(this.getTpPint());
        if (this.getTpVeic() != null) {
            veicProd.setTpVeic(this.getTpVeic().toString());
        }
        if (this.getEspVeic() != null) {
            veicProd.setEspVeic(this.getEspVeic().toString());
        }
        veicProd.setVIN(this.getVin());
        if (this.getCondVeic() != null) {
            veicProd.setCondVeic(this.getCondVeic().toString());
        }
        if (this.getcMod() != null) {
            veicProd.setCMod(this.getcMod().toString());
        }
        if (this.getcCorDENATRAN() != null) {
            veicProd.setCCorDENATRAN(this.getcCorDENATRAN().toString());
        }
        if (this.getLota() != null) {
            veicProd.setLota(this.getLota().toString());
        }
        if (this.getTpRest() != null) {
            veicProd.setTpRest(this.getTpRest().toString());
        }
        return veicProd;
    }

    public String getTpOp() {
        return tpOp;
    }

    public String getChassi() {
        return chassi;
    }

    public String getcCor() {
        return cCor;
    }

    public String getxCor() {
        return xCor;
    }

    public String getPot() {
        return pot;
    }

    public String getCilin() {
        return cilin;
    }

    public String getPesoL() {
        return pesoL;
    }

    public String getPesoB() {
        return pesoB;
    }

    public String getnSerie() {
        return nSerie;
    }

    public String getTpComb() {
        return tpComb;
    }

    public String getnMotor() {
        return nMotor;
    }

    public String getCmt() {
        return cmt;
    }

    public String getDist() {
        return dist;
    }

    public Integer getAnoMod() {
        return anoMod;
    }

    public Integer getAnoFab() {
        return anoFab;
    }

    public String getTpPint() {
        return tpPint;
    }

    public Integer getTpVeic() {
        return tpVeic;
    }

    public Integer getEspVeic() {
        return espVeic;
    }

    public String getVin() {
        return vin;
    }

    public Integer getCondVeic() {
        return condVeic;
    }

    public Integer getcMod() {
        return cMod;
    }

    public Integer getcCorDENATRAN() {
        return cCorDENATRAN;
    }

    public Integer getLota() {
        return lota;
    }

    public Integer getTpRest() {
        return tpRest;
    }

    public VeicProd setTpOp(String tpOp) {
        this.tpOp = tpOp;
        return this;
    }

    public VeicProd setChassi(String chassi) {
        this.chassi = chassi;
        return this;
    }

    public VeicProd setcCor(String cCor) {
        this.cCor = cCor;
        return this;
    }

    public VeicProd setxCor(String xCor) {
        this.xCor = xCor;
        return this;
    }

    public VeicProd setPot(String pot) {
        this.pot = pot;
        return this;
    }

    public VeicProd setCilin(String cilin) {
        this.cilin = cilin;
        return this;
    }

    public VeicProd setPesoL(String pesoL) {
        this.pesoL = pesoL;
        return this;
    }

    public VeicProd setPesoB(String pesoB) {
        this.pesoB = pesoB;
        return this;
    }

    public VeicProd setnSerie(String nSerie) {
        this.nSerie = nSerie;
        return this;
    }

    public VeicProd setTpComb(String tpComb) {
        this.tpComb = tpComb;
        return this;
    }

    public VeicProd setnMotor(String nMotor) {
        this.nMotor = nMotor;
        return this;
    }

    public VeicProd setCmt(String cmt) {
        this.cmt = cmt;
        return this;
    }

    public VeicProd setDist(String dist) {
        this.dist = dist;
        return this;
    }

    public VeicProd setAnoMod(Integer anoMod) {
        this.anoMod = anoMod;
        return this;
    }

    public VeicProd setAnoFab(Integer anoFab) {
        this.anoFab = anoFab;
        return this;
    }

    public VeicProd setTpPint(String tpPint) {
        this.tpPint = tpPint;
        return this;
    }

    public VeicProd setTpVeic(Integer tpVeic) {
        this.tpVeic = tpVeic;
        return this;
    }

    public VeicProd setEspVeic(Integer espVeic) {
        this.espVeic = espVeic;
        return this;
    }

    public VeicProd setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public VeicProd setCondVeic(Integer condVeic) {
        this.condVeic = condVeic;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}
