package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.ModalidadeFrete;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.nfe.TVeiculo;

import java.util.List;

public class Transp {

    @NfeCampo(tipo = ModalidadeFrete.class, id = "X02", tag = "modFrete"
            , tamanhoMinimo = 1, tamanhoMaximo = 1
            , ocorrencias = 1, descricao = NfeConsts.DSC_MODFRETE)
    private ModalidadeFrete modFrete;

    @NfeCampo(id  = "X25a", tag = "vagao"
            ,tamanhoMinimo = 1, tamanhoMaximo = 20,
            ocorrencias = 0, descricao = NfeConsts.DSC_VAGAO)
    private String vagao;

    @NfeCampo(id = "X25b", tag = "balsa"
            , tamanhoMinimo = 1, tamanhoMaximo = 20
            , ocorrencias = 0, descricao = NfeConsts.DSC_BALSA)
    private String balsa;

    @NfeCampo(id = "X03", tag = "transporta", descricao = NfeConsts.DSC_TRANSP, ocorrencias = 0)
    private Transporta transporta;

    @NfeCampo(id = "X11", tag = "retTransp", descricao = NfeConsts.DSC_RETTRANSP, ocorrencias = 0)
    private RetTransp retTransp;

    @NfeCampo(id = "X18", tag = "veicTransp", descricao = NfeConsts.DSC_VEICTRANSP, ocorrencias = 0)
    private Veiculo veicTransp;

    @NfeCampo(id = "X22", tag = "reboque", descricao = NfeConsts.DSC_GREBOQUE, ocorrencias = 0)
    private List<Veiculo> reboque;

    @NfeCampo(id = "X26", tag = "vol", descricao = NfeConsts.DSC_GVOLUMES, ocorrencias = 0)
    private List<Vol> vol;

    public TNFe.InfNFe.Transp build() {
        TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();

        transp.setModFrete(this.modFrete.getValue().toString());
        transp.setTransporta(this.transporta.build());
        transp.setRetTransp(this.retTransp.build());
        transp.setVeicTransp(this.veicTransp.build());

        reboque.forEach(e -> transp.getReboque().add(e.build()));
        vol.forEach(e -> transp.getVol().add(e.build()));

        return transp;
    }

    public ModalidadeFrete getModFrete() {
        return modFrete;
    }

    public void setModFrete(ModalidadeFrete modFrete) {
        this.modFrete = modFrete;
    }

    public String getVagao() {
        return vagao;
    }

    public void setVagao(String vagao) {
        this.vagao = vagao;
    }

    public String getBalsa() {
        return balsa;
    }

    public void setBalsa(String balsa) {
        this.balsa = balsa;
    }

    public Transporta getTransporta() {
        return transporta;
    }

    public void setTransporta(Transporta transporta) {
        this.transporta = transporta;
    }

    public RetTransp getRetTransp() {
        return retTransp;
    }

    public void setRetTransp(RetTransp retTransp) {
        this.retTransp = retTransp;
    }

    public Veiculo getVeicTransp() {
        return veicTransp;
    }

    public void setVeicTransp(Veiculo veicTransp) {
        this.veicTransp = veicTransp;
    }

    public List<Veiculo> getReboque() {
        return reboque;
    }

    public void setReboque(List<Veiculo> reboque) {
        this.reboque = reboque;
    }

    public List<Vol> getVol() {
        return vol;
    }

    public void setVol(List<Vol> vol) {
        this.vol = vol;
    }
}
