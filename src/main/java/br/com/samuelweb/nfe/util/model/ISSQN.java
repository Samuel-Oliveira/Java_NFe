package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.enumeration.IndISS;
import br.com.samuelweb.nfe.util.enumeration.SimNao;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIndISS;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class ISSQN  {
    
    @NfeCampo(tipo = BigDecimal.class
            , id = "U02", tag = "vBC", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VBC)
    private BigDecimal vBC;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U03", tag = "vAliq", decimais = 4, precisao = 7
            , tamanhoMinimo = 1, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = DfeConsts.DSC_VALIQ)
    private BigDecimal vAliq;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U04", tag = "vISSQN", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VISSQN)
    private BigDecimal vISSQN;

    @NfeCampo(tipo = Integer.class
            , id = "U05", tag = "cMunFG", validadores = {ValidarMunicipio.class}
            , tamanhoMinimo = 7, tamanhoMaximo = 7, ocorrencias = 1
            , descricao = DfeConsts.DSC_CMUNFG)
    private Integer cMunFG;

    @NfeCampo(tipo = String.class
            , id = "u06", tag = "cListServ"
            , tamanhoMinimo = 5, tamanhoMaximo = 5, ocorrencias = 1
            , descricao = DfeConsts.DSC_CLISTSERV)
    private String cListServ;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U07", tag = "vDeducao", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VDEDUCISS)
    private BigDecimal vDeducao;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U08", tag = "vOutro", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VOUTRO)
    private BigDecimal vOutro;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U09", tag = "vDescIncond", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VDESCINCOND)
    private BigDecimal vDescIncond;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U10", tag = "vDescCond", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VDESCCOND)
    private BigDecimal vDescCond;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U10", tag = "vISSRet", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_VISSRET)
    private BigDecimal vISSRet;

    @NfeCampo(tipo = IndISS.class
            , id = "U12", tag = "indISS", validadores = {ValidarIndISS.class}
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = DfeConsts.DSC_INDISS)
    private IndISS indISS;

    @NfeCampo(tipo = BigDecimal.class
            , id = "U13", tag = "cServico"
            , tamanhoMinimo = 1, tamanhoMaximo = 20, ocorrencias = 0
            , descricao = DfeConsts.DSC_CSERVTRIBMUN)
    private String cServico;

    @NfeCampo(tipo = Integer.class
            , id = "U14", tag = "cMun", validadores = {ValidarMunicipio.class}
            , tamanhoMinimo = 7, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_CMUN)
    private Integer cMun;

    @NfeCampo(tipo = Integer.class
            , id = "U15", tag = "cPais"
            , tamanhoMinimo = 4, tamanhoMaximo = 4, ocorrencias = 0
            , descricao = DfeConsts.DSC_CPAIS)
    private Integer cPais;

    @NfeCampo(tipo = String.class
            , id = "U16", tag = "nProcesso"
            , tamanhoMinimo = 1, tamanhoMaximo = 30, ocorrencias = 0
            , descricao = DfeConsts.DSC_NPROCESSO)
    private String nProcesso;

    @NfeCampo(tipo = SimNao.class
            , id = "U17", tag = "indIncentivo"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = DfeConsts.DSC_INDINCENTIVO)
    private SimNao indIncentivo;
    
    public TNFe.InfNFe.Det.Imposto.ISSQN build() {
        TNFe.InfNFe.Det.Imposto.ISSQN issqn = new TNFe.InfNFe.Det.Imposto.ISSQN();
        return issqn;
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    public BigDecimal getvAliq() {
        return vAliq;
    }

    public void setvAliq(BigDecimal vAliq) {
        this.vAliq = vAliq;
    }

    public BigDecimal getvISSQN() {
        return vISSQN;
    }

    public void setvISSQN(BigDecimal vISSQN) {
        this.vISSQN = vISSQN;
    }

    public Integer getcMunFG() {
        return cMunFG;
    }

    public void setcMunFG(Integer cMunFG) {
        this.cMunFG = cMunFG;
    }

    public String getcListServ() {
        return cListServ;
    }

    public void setcListServ(String cListServ) {
        this.cListServ = cListServ;
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

    public BigDecimal getvISSRet() {
        return vISSRet;
    }

    public void setvISSRet(BigDecimal vISSRet) {
        this.vISSRet = vISSRet;
    }

    public IndISS getIndISS() {
        return indISS;
    }

    public void setIndISS(IndISS indISS) {
        this.indISS = indISS;
    }

    public String getcServico() {
        return cServico;
    }

    public void setcServico(String cServico) {
        this.cServico = cServico;
    }

    public Integer getcMun() {
        return cMun;
    }

    public void setcMun(Integer cMun) {
        this.cMun = cMun;
    }

    public Integer getcPais() {
        return cPais;
    }

    public void setcPais(Integer cPais) {
        this.cPais = cPais;
    }

    public String getnProcesso() {
        return nProcesso;
    }

    public void setnProcesso(String nProcesso) {
        this.nProcesso = nProcesso;
    }

    public SimNao getIndIncentivo() {
        return indIncentivo;
    }

    public void setIndIncentivo(SimNao indIncentivo) {
        this.indIncentivo = indIncentivo;
    }
}
