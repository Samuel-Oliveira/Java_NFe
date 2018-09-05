package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dup {

    @NfeCampo(id = "Y08", tag = "nDup "
            , tamanhoMinimo = 1, tamanhoMaximo = 60
            , ocorrencias = 1, descricao = NfeConsts.DSC_NDUP)
    private String nDup;

    @NfeCampo(id = "Y09", tag = "dVenc", tipo = LocalDate.class
            , tamanhoMinimo = 10, tamanhoMaximo = 10
            , ocorrencias = 1, descricao = NfeConsts.DSC_DVENC)
    private LocalDate dVenc;

    @NfeCampo(id = "Y10", tag = "vDup ", tipo = BigDecimal.class
            , tamanhoMinimo = 1, tamanhoMaximo = 15
            , decimais = 2, precisao = 15
            , ocorrencias = 1, descricao = NfeConsts.DSC_VDUP)
    private BigDecimal vDup;


    public TNFe.InfNFe.Cobr.Dup build(){
        TNFe.InfNFe.Cobr.Dup dup = new TNFe.InfNFe.Cobr.Dup();

        dup.setNDup(this.getnDup());

        if (this.getdVenc() != null){
            dup.setDVenc(this.getdVenc().format(DateTimeFormatter.ISO_DATE));
        }

        if (this.getvDup() != null){
            dup.setVDup(this.getvDup().toString());
        }

        return dup;
    }

    public String getnDup() {
        return nDup;
    }

    public LocalDate getdVenc() {
        return dVenc;
    }

    public BigDecimal getvDup() {
        return vDup;
    }

    public Dup setnDup(String nDup) {
        this.nDup = nDup;
        return this;
    }

    public Dup setdVenc(LocalDate dVenc) {
        this.dVenc = dVenc;
        return this;
    }

    public Dup setvDup(BigDecimal vDup) {
        this.vDup = vDup;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {

    }
}