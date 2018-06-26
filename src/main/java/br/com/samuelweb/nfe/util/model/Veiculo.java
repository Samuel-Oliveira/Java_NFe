package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCodigoUf;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TUf;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TVeiculo;
import org.apache.commons.lang3.StringUtils;

public class Veiculo {

    @NfeCampo(id = "X19", tag = "placa"
            , tamanhoMinimo = 6, tamanhoMaximo = 7
            , ocorrencias = 1, descricao = NfeConsts.DSC_PLACA)
    private String placa;

    @NfeCampo(id = "X20", tag = "UF"
            , validadores = {ValidarCodigoUf.class}
            , tamanhoMinimo = 2, tamanhoMaximo = 2
            , ocorrencias = 1, descricao = NfeConsts.DSC_UF)
    private String uf;

    @NfeCampo(id = "X21", tag = "RNTC"
            , tamanhoMinimo = 1, tamanhoMaximo = 20
            , ocorrencias = 0, descricao = NfeConsts.DSC_RNTC)
    private String rntc;

    public TVeiculo build() {
        TVeiculo veiculo = new TVeiculo();

        if (StringUtils.isNotBlank(this.placa.trim()) ||
                (StringUtils.isNotBlank(this.uf)) ||
                (StringUtils.isNotBlank(this.rntc))) {

            veiculo.setPlaca(this.placa);
            veiculo.setUF(TUf.fromValue(this.uf));
            veiculo.setRNTC(this.rntc);
            return veiculo;
        }
        return null;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRntc() {
        return rntc;
    }

    public void setRntc(String rntc) {
        this.rntc = rntc;
    }
}
