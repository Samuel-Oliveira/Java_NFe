package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Vol {

    @NfeCampo(id = "X27", tag = "qVol"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , ocorrencias = 1, descricao = NfeConsts.DSC_QVOL)
    private String qVol;

    @NfeCampo(id = "X28", tag ="esp"
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

    @NfeCampo(id = "X31", tag = "pesoL"
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_PESOL)
    private String pesoL;

    @NfeCampo(id = "X32", tag = "pesoB"
            , tamanhoMaximo = 1, tamanhoMinimo = 15
            , ocorrencias = 0, descricao = NfeConsts.DSC_PESOB)
    private String pesoB;

    private List<Lacres> lacres;

    public TNFe.InfNFe.Transp.Vol build(){
        TNFe.InfNFe.Transp.Vol vol = new TNFe.InfNFe.Transp.Vol();

        vol.setQVol(this.qVol);
        vol.setEsp(this.esp);
        vol.setMarca(this.marca);
        vol.setNVol(this.nVol);
        vol.setPesoL(this.pesoL);
        vol.setPesoB(this.pesoB);

        lacres.forEach(e -> vol.getLacres().add(e.build()));

        return vol;
    }

    public String getqVol() {
        return qVol;
    }

    public void setqVol(String qVol) {
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

    public List<Lacres> getLacres() {
        return lacres;
    }

    public void setLacres(List<Lacres> lacres) {
        this.lacres = lacres;
    }
}
