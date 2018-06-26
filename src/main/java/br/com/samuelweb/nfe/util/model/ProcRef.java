package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.IndicadorOrigemProcesso;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIndicadorOrigemProcesso;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class ProcRef {

    @NfeCampo(id = "Z11", tag = "nProc"
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 1, descricao = NfeConsts.DSC_NPROC)
    private String nProc;

    @NfeCampo(tipo = IndicadorOrigemProcesso.class, id = "Z12", tag = "indProc"
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , validadores = {ValidarIndicadorOrigemProcesso.class}
            , descricao = NfeConsts.DSC_INDPROC)
    private String indProc;

    public TNFe.InfNFe.InfAdic.ProcRef build(){
        TNFe.InfNFe.InfAdic.ProcRef procRef = new TNFe.InfNFe.InfAdic.ProcRef();

        procRef.setNProc(this.nProc);
        procRef.setIndProc(this.indProc);

        return procRef;
    }

    public String getnProc() {
        return nProc;
    }

    public void setnProc(String nProc) {
        this.nProc = nProc;
    }

    public String getIndProc() {
        return indProc;
    }

    public void setIndProc(String indProc) {
        this.indProc = indProc;
    }
}
