package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pag {

    @NfeObjetoList(id = "detPag", tag = "YA01a", ocorrenciaMinima = 1, ocorrenciaMaxima = 100, descricao = NfeConsts.DSC_DETPAG)
    private List<DetPag> detPag;

    @NfeCampo(tipo = BigDecimal.class, id = "YA09", tag = "vTroco"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VTROCO)
    private BigDecimal vTroco;


    TNFe.InfNFe.Pag build(){
        TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();
        getDetPag().forEach(detPag -> pag.getDetPag().add(detPag.build()));
        if (this.getvTroco()!= null){
            pag.setVTroco(this.getvTroco().toString());
        }
        return pag;
    }

    public List<DetPag> getDetPag() {
        if (detPag == null) {
            detPag = new ArrayList<>();
        }
        return detPag;
    }

    public BigDecimal getvTroco() {
        return vTroco;
    }

    public Pag setDetPag(List<DetPag> detPag) {
        this.detPag = detPag;
        return this;
    }

    public Pag setvTroco(BigDecimal vTroco) {
        this.vTroco = vTroco;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {
        if (getDetPag() != null) {
            getDetPag().forEach(detPag -> detPag.validarRegraNegocio(infNFe));
        }
    }
}
