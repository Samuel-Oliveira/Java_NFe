package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJCPF;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIE;
import br.com.samuelweb.nfe.util.validators.impl.ValidarISUF;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIndIeDestinatario;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Dest {

    @NfeCampo(tipo = String.class
            , id = "E02", tag = "CNPJ/CPF", validadores = {ValidarCNPJCPF.class}
            , tamanhoMinimo = 11, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpjCpf;

    @NfeCampo(tipo = String.class
            , id = "E03a", tag = "idEstrangeiro"
            , tamanhoMinimo = 0, tamanhoMaximo = 20, ocorrencias = 1
            , descricao = NfeConsts.DSC_IDESTR)
    private String idEstrangeiro;

    @NfeCampo(tipo = String.class
            , id = "E04", tag = "xNome"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XNOME)
    private String xNome;

    @NfeObjeto(id = "E05", tag = "enderDest"
            , ocorrencias = 0, descricao = NfeConsts.DSC_ENDERDEST)
    private EnderDest enderDest;

    @NfeCampo(tipo = Integer.class
            , id = "E16a", tag = "indIEDest", validadores = {ValidarIndIeDestinatario.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_INDIEDEST)
    private Integer indIEDest;

    @NfeCampo(tipo = String.class
            , id = "E17", tag = "IE", validadores = {ValidarIE.class}
            , tamanhoMinimo = 0, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_IE)
    private String ie;

    @NfeCampo(tipo = String.class
            , id = "E18", tag = "ISUF", validadores = {ValidarISUF.class}
            , tamanhoMinimo = 8, tamanhoMaximo = 9, ocorrencias = 0
            , descricao = DfeConsts.DSC_ISUF)
    private String isuf;

    @NfeCampo(tipo = String.class
            , id = "E18a", tag = "IM"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_IM)
    private String im;

    @NfeCampo(tipo = String.class
            , id = "E19", tag = "email"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_EMAIL)
    private String email;


    public TNFe.InfNFe.Dest build() {
        TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
        if ((this.idEstrangeiro != null && !this.idEstrangeiro.isEmpty()) ||
                (this.enderDest != null && this.enderDest.getcPais() != 1058)) {
            dest.setIdEstrangeiro(this.idEstrangeiro);
        } else if (this.cnpjCpf.length() > 11) {
            dest.setCNPJ(this.cnpjCpf);
        } else {
            dest.setCPF(this.cnpjCpf);
        }
        dest.setXNome(this.xNome);
        dest.setEnderDest(enderDest.build());
        if (this.indIEDest != null) {
            dest.setIndIEDest(this.indIEDest.toString());
        }
        dest.setIE(this.ie);
        dest.setISUF(this.isuf);
        dest.setIM(this.im);
        dest.setEmail(this.email);
        return dest;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getIdEstrangeiro() {
        return idEstrangeiro;
    }

    public void setIdEstrangeiro(String idEstrangeiro) {
        this.idEstrangeiro = idEstrangeiro;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public EnderDest getEnderDest() {
        if (this.enderDest == null) {
            this.enderDest = new EnderDest();
        }
        return enderDest;
    }

    public void setEnderDest(EnderDest enderDest) {
        this.enderDest = enderDest;
    }

    public Integer getIndIEDest() {
        return indIEDest;
    }

    public void setIndIEDest(Integer indIEDest) {
        this.indIEDest = indIEDest;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIsuf() {
        return isuf;
    }

    public void setIsuf(String isuf) {
        this.isuf = isuf;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
