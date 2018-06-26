package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJCPF;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCRT;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIE;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Emit {

    @NfeCampo(tipo = String.class
            , id = "C02", tag = "CNPJ", validadores = {ValidarCNPJCPF.class}
            , tamanhoMinimo = 11, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpjCpf;

    @NfeCampo(tipo = String.class
            , id = "C03", tag = "xNome"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XNOME)
    private String xNome;

    @NfeCampo(tipo = String.class
            , id = "C04", tag = "xFant"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XFANT)
    private String xFant;

    @NfeObjeto(id = "C05", tag = "enderEmit"
            , ocorrencias = 1, descricao = NfeConsts.DSC_ENDEREMIT)
    private EnderEmi enderEmit;

    @NfeCampo(tipo = String.class
            , id = "C17", tag = "IE", validadores = {ValidarIE.class}
            , tamanhoMinimo = 0, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_IE)
    private String ie;

    @NfeCampo(tipo = String.class
            , id = "C18", tag = "IEST"
            , tamanhoMinimo = 2, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_IEST)
    private String iest;

    @NfeCampo(tipo = String.class
            , id = "C19", tag = "IM"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_IM)
    private String im;

    @NfeCampo(tipo = String.class
            , id = "C20", tag = "CNAE"
            , tamanhoMinimo = 7, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_CNAE)
    private String cnae;

    @NfeCampo(tipo = String.class
            , id = "C21", tag = "CRT", validadores = {ValidarCRT.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_CRT)
    private String crt;

    public Emit() {
        this.enderEmit = new EnderEmi();
    }

    public TNFe.InfNFe.Emit build(){
        TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
        if (cnpjCpf.length() > 11) {
            emit.setCNPJ(cnpjCpf);
        } else {
            emit.setCPF(cnpjCpf);
        }
        emit.setXNome(this.xNome);
        emit.setXFant(this.xFant);
        emit.setEnderEmit(this.enderEmit.build());
        if (!this.ie.equals("ISENTO")) {
            this.ie = this.ie.replaceAll("\\D", "");
        }
        emit.setIE(this.ie);
        emit.setIEST(this.iest);
        emit.setIM(this.im);
        // NT 2013/005 versão 1.03
        // o CNAE passa ser opcional mesmo quando informado o IM, mas o CNAE s� pode
        // ser informado se o IM for informado.
        if (this.im != null && !this.im.isEmpty()) {
            emit.setCNAE(this.cnae);
        }
        emit.setCRT(this.crt);
        return emit;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getxFant() {
        return xFant;
    }

    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    public EnderEmi getEnderEmit() {
        return enderEmit;
    }

    public void setEnderEmit(EnderEmi enderEmit) {
        this.enderEmit = enderEmit;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIest() {
        return iest;
    }

    public void setIest(String iest) {
        this.iest = iest;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }
}
