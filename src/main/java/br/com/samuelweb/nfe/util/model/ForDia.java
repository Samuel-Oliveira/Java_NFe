package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;

public class ForDia {

    @NfeCampo(tipo = BigDecimal.class, id = "ZC06", tag = "qtde"
            , tamanhoMinimo = 11, tamanhoMaximo = 21, ocorrencias = 1
            , decimais = 10, precisao = 21
            , descricao = NfeConsts.DSC_QTDE)
    private String qtde;


    @NfeCampo(id = "ZC05", tag = "dia"
    , tamanhoMinimo = 1, tamanhoMaximo = 2, ocorrencias = 1
    , descricao = NfeConsts.DSC_DIA)
    private String dia;

    public TNFe.InfNFe.Cana.ForDia build(){
        TNFe.InfNFe.Cana.ForDia forDia = new TNFe.InfNFe.Cana.ForDia();

        forDia.setDia(this.getDia());
        forDia.setQtde(this.getQtde());

        return forDia;
    }

    public String getQtde() {
        return qtde;
    }

    public String getDia() {
        return dia;
    }

    public ForDia setQtde(String qtde) {
        this.qtde = qtde;
        return this;
    }

    public ForDia setDia(String dia) {
        this.dia = dia;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}