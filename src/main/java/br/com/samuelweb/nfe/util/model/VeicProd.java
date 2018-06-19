package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarTpRestricao;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

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

    
    public TNFe.InfNFe.Det.Prod.VeicProd build(){
        if (this.chassi != null && this.chassi.isEmpty()) {
            return null;
        }
        TNFe.InfNFe.Det.Prod.VeicProd veicProd = new TNFe.InfNFe.Det.Prod.VeicProd();
        veicProd.setTpOp(this.tpOp);
        veicProd.setChassi(this.chassi);
        veicProd.setCCor(this.cCor);
        veicProd.setXCor(this.xCor);
        veicProd.setPot(this.pot);
        veicProd.setCilin(this.cilin);
        veicProd.setPesoL(this.pesoL);
        veicProd.setPesoB(this.pesoB);
        veicProd.setNSerie(this.nSerie);
        veicProd.setTpComb(this.tpComb);
        veicProd.setNMotor(this.nMotor);
        veicProd.setCMT(this.cmt);
        veicProd.setDist(this.dist);
        if (this.anoMod != null) {
            veicProd.setAnoMod(this.anoMod.toString());
        }
        if (this.anoFab != null) {
            veicProd.setAnoFab(this.anoFab.toString());
        }
        veicProd.setTpPint(this.tpPint);
        if (this.tpVeic != null) {
            veicProd.setTpVeic(this.tpVeic.toString());
        }
        if (this.espVeic != null) {
            veicProd.setEspVeic(this.espVeic.toString());
        }
        veicProd.setVIN(this.vin);
        if (this.condVeic != null) {
            veicProd.setCondVeic(this.condVeic.toString());
        }
        if (this.cMod != null) {
            veicProd.setCMod(this.cMod.toString());
        }
        if (this.cCorDENATRAN != null) {
            veicProd.setCCorDENATRAN(this.cCorDENATRAN.toString());
        }
        if (this.lota != null) {
            veicProd.setLota(this.lota.toString());
        }
        if (this.tpRest != null) {
            veicProd.setTpRest(this.tpRest.toString());
        }
        return veicProd;
    }

    public String getTpOp() {
        return tpOp;
    }

    public void setTpOp(String tpOp) {
        this.tpOp = tpOp;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getcCor() {
        return cCor;
    }

    public void setcCor(String cCor) {
        this.cCor = cCor;
    }

    public String getxCor() {
        return xCor;
    }

    public void setxCor(String xCor) {
        this.xCor = xCor;
    }

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }

    public String getCilin() {
        return cilin;
    }

    public void setCilin(String cilin) {
        this.cilin = cilin;
    }

    public String getPesoL() {
        return pesoL;
    }

    public void setPesoL(String pesoL) {
        this.pesoL = pesoL;
    }

    public String getPesoB() {
        return pesoB;
    }

    public void setPesoB(String pesoB) {
        this.pesoB = pesoB;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getTpComb() {
        return tpComb;
    }

    public void setTpComb(String tpComb) {
        this.tpComb = tpComb;
    }

    public String getnMotor() {
        return nMotor;
    }

    public void setnMotor(String nMotor) {
        this.nMotor = nMotor;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public Integer getAnoMod() {
        return anoMod;
    }

    public void setAnoMod(Integer anoMod) {
        this.anoMod = anoMod;
    }

    public Integer getAnoFab() {
        return anoFab;
    }

    public void setAnoFab(Integer anoFab) {
        this.anoFab = anoFab;
    }

    public String getTpPint() {
        return tpPint;
    }

    public void setTpPint(String tpPint) {
        this.tpPint = tpPint;
    }

    public Integer getTpVeic() {
        return tpVeic;
    }

    public void setTpVeic(Integer tpVeic) {
        this.tpVeic = tpVeic;
    }

    public Integer getEspVeic() {
        return espVeic;
    }

    public void setEspVeic(Integer espVeic) {
        this.espVeic = espVeic;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getCondVeic() {
        return condVeic;
    }

    public void setCondVeic(Integer condVeic) {
        this.condVeic = condVeic;
    }
}
