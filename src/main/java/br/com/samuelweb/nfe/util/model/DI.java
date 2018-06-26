package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJ;
import br.com.samuelweb.nfe.util.validators.impl.ValidarDIDSI;
import br.com.samuelweb.nfe.util.validators.impl.ValidarDIRE;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUfEmi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DI {

    @NfeCampo(tipo = String.class
            , id = "I19", tag = "nDI", validadores = {ValidarDIRE.class, ValidarDIDSI.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 12, ocorrencias = 1
            , descricao = NfeConsts.DSC_NDI)
    private String ndi;

    @NfeCampo(tipo = LocalDate.class
            , id = "I20", tag = "dDI"
            , tamanhoMinimo = 10, tamanhoMaximo = 10, ocorrencias = 1
            , descricao = NfeConsts.DSC_DDI)
    private LocalDate ddi;

    @NfeCampo(tipo = String.class
            , id = "I21", tag = "xLocDesemb"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = NfeConsts.DSC_XLOCDESEMB)
    private String xLocDesemb;

    @NfeCampo(tipo = String.class
            , id = "I22", tag = "UFDesemb"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = NfeConsts.DSC_UFDESEMB)
    private String ufDesemb;

    @NfeCampo(tipo = LocalDate.class
            , id = "I23", tag = "dDesemb"
            , tamanhoMinimo = 10, tamanhoMaximo = 10, ocorrencias = 1
            , descricao = NfeConsts.DSC_DDESEMB)
    private LocalDate dDesemb;

    @NfeCampo(tipo = String.class
            , id = "I23a", tag = "tpViaTransp"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPVIATRANSP)
    private String tpViaTransp;

    @NfeCampo(tipo = BigDecimal.class
            , id = "I23b", tag = "vAFRMM"
            , tamanhoMinimo = 0, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = DfeConsts.DSC_VAFRMM)
    private BigDecimal vafrmm;

    @NfeCampo(tipo = String.class
            , id = "I23c", tag = "tpIntermedio"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_TPINTERMEDIO)
    private String tpIntermedio;

    @NfeCampo(tipo = String.class
            , id = "I23d", tag = "CNPJ", validadores = {ValidarCNPJ.class}
            , tamanhoMinimo = 14, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpj;

    @NfeCampo(tipo = String.class
            , id = "I23e", tag = "UFTerceiro"
            , tamanhoMinimo = 2, tamanhoMaximo = 2, ocorrencias = 0
            , descricao = DfeConsts.DSC_UF)
    private String ufTerceiro;

    @NfeCampo(tipo = String.class
            , id = "I24", tag = "cExportador"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = NfeConsts.DSC_CEXPORTADOR)
    private String cExportador;

    @NfeObjetoList(id = "I25", tag = "adi"
            , ocorrenciaMinima = 1, ocorrenciaMaxima = 100
            , descricao = NfeConsts.DSC_ADICOES)
    private List<Adi> adi;

    public DI() {
        adi = new ArrayList<>();
    }

    public TNFe.InfNFe.Det.Prod.DI build() {
        TNFe.InfNFe.Det.Prod.DI di = new TNFe.InfNFe.Det.Prod.DI();
        di.setNDI(this.ndi);
        if (this.ddi != null) {
            di.setDDI(this.ddi.format(DateTimeFormatter.ISO_DATE));
        }
        di.setXLocDesemb(this.xLocDesemb);
        di.setUFDesemb(TUfEmi.fromValue(this.ufDesemb));
        if (this.dDesemb != null) {
            di.setDDesemb(this.dDesemb.format(DateTimeFormatter.ISO_DATE));
        }
        di.setTpViaTransp(this.tpViaTransp);
        if (this.vafrmm != null) {
            di.setVAFRMM(this.vafrmm.toString());
        }
        di.setTpIntermedio(this.tpIntermedio);
        di.setCNPJ(this.cnpj);
        di.setUFTerceiro(TUfEmi.fromValue(this.ufTerceiro));
        di.setCExportador(this.cExportador);
        if (this.adi != null) {
            this.adi.forEach(a -> di.getAdi().add(a.build()));
        }
        return di;
    }

    public String getNdi() {
        return ndi;
    }

    public void setNdi(String ndi) {
        this.ndi = ndi;
    }

    public LocalDate getDdi() {
        return ddi;
    }

    public void setDdi(LocalDate ddi) {
        this.ddi = ddi;
    }

    public String getxLocDesemb() {
        return xLocDesemb;
    }

    public void setxLocDesemb(String xLocDesemb) {
        this.xLocDesemb = xLocDesemb;
    }

    public String getUfDesemb() {
        return ufDesemb;
    }

    public void setUfDesemb(String ufDesemb) {
        this.ufDesemb = ufDesemb;
    }

    public LocalDate getdDesemb() {
        return dDesemb;
    }

    public void setdDesemb(LocalDate dDesemb) {
        this.dDesemb = dDesemb;
    }

    public String getTpViaTransp() {
        return tpViaTransp;
    }

    public void setTpViaTransp(String tpViaTransp) {
        this.tpViaTransp = tpViaTransp;
    }

    public BigDecimal getVafrmm() {
        return vafrmm;
    }

    public void setVafrmm(BigDecimal vafrmm) {
        this.vafrmm = vafrmm;
    }

    public String getTpIntermedio() {
        return tpIntermedio;
    }

    public void setTpIntermedio(String tpIntermedio) {
        this.tpIntermedio = tpIntermedio;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUfTerceiro() {
        return ufTerceiro;
    }

    public void setUfTerceiro(String ufTerceiro) {
        this.ufTerceiro = ufTerceiro;
    }

    public String getcExportador() {
        return cExportador;
    }

    public void setcExportador(String cExportador) {
        this.cExportador = cExportador;
    }

    public List<Adi> getAdi() {
        return adi;
    }

    public void setAdi(List<Adi> adi) {
        this.adi = adi;
    }
}
