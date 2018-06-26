package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;
import java.util.List;

public class Vol {

    @NfeCampo(tipo = Integer.class
            , id = "X27", tag = "qVol"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , ocorrencias = 1, descricao = NfeConsts.DSC_QVOL)
    private Integer qVol;

    @NfeCampo(id = "X28", tag = "esp"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_ESP)
    private String esp;

    @NfeCampo(id = "X29", tag = "marca"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_MARCA)
    private String marca;

    @NfeCampo(id = "X30", tag = "nVol"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 0, descricao = NfeConsts.DSC_NVOL)
    private String nVol;

    @NfeCampo(tipo = BigDecimal.class
            , id = "X31", tag = "pesoL", decimais = 3, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_PESOL)
    private BigDecimal pesoL;

    @NfeCampo(tipo = BigDecimal.class
            , id = "X32", tag = "pesoB"
            , tamanhoMaximo = 1, tamanhoMinimo = 15, decimais = 3, precisao = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_PESOB)
    private BigDecimal pesoB;

    @NfeObjetoList(id = "X33", tag = "lacres", ocorrenciaMinima = 0, ocorrenciaMaxima = 5000, descricao = NfeConsts.DSC_LACRES)
    private List<Lacres> lacres;

    public TNFe.InfNFe.Transp.Vol build() {
        TNFe.InfNFe.Transp.Vol vol = new TNFe.InfNFe.Transp.Vol();

        if (this.getqVol() != null) {
            vol.setQVol(this.getqVol().toString());
        }
        vol.setEsp(this.getEsp());
        vol.setMarca(this.getMarca());
        vol.setNVol(this.getnVol());
        if (this.getPesoL() != null) {
            vol.setPesoL(this.getPesoL().toString());
        }
        if (this.getPesoB() != null) {
            vol.setPesoB(this.getPesoB().toString());
        }
        if (this.getLacres() != null) {
            getLacres().forEach(e -> vol.getLacres().add(e.build()));
        }
        return vol;
    }

    public Integer getqVol() {
        return qVol;
    }

    public void setqVol(Integer qVol) {
        this.qVol = qVol;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getnVol() {
        return nVol;
    }

    public void setnVol(String nVol) {
        this.nVol = nVol;
    }

    public BigDecimal getPesoL() {
        return pesoL;
    }

    public void setPesoL(BigDecimal pesoL) {
        this.pesoL = pesoL;
    }

    public BigDecimal getPesoB() {
        return pesoB;
    }

    public void setPesoB(BigDecimal pesoB) {
        this.pesoB = pesoB;
    }

    public List<Lacres> getLacres() {
        return lacres;
    }

    public void setLacres(List<Lacres> lacres) {
        this.lacres = lacres;
    }
}
