package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.ConstantesUtil;
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
        if ((this.getIdEstrangeiro() != null && !this.getIdEstrangeiro().isEmpty()) ||
                (this.getEnderDest() != null && this.getEnderDest().getcPais() != 1058)) {
            dest.setIdEstrangeiro(this.getIdEstrangeiro());
        } else if (this.getCnpjCpf().length() > 11) {
            dest.setCNPJ(this.getCnpjCpf());
        } else {
            dest.setCPF(this.getCnpjCpf());
        }
        dest.setXNome(this.getxNome());
        dest.setEnderDest(getEnderDest().build());
        if (this.getIndIEDest() != null) {
            dest.setIndIEDest(this.getIndIEDest().toString());
        }
        if (this.getIe() != null && !this.getIe().isEmpty()) {
            dest.setIE(this.getIe());
        }
        if (this.getIsuf() != null && !this.getIsuf().isEmpty()) {
            dest.setISUF(this.getIsuf());
        }
        if (this.getIm() != null && !this.getIm().isEmpty()) {
            dest.setIM(this.getIm());
        }
        dest.setEmail(this.getEmail());
        return dest;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public String getIdEstrangeiro() {
        return idEstrangeiro;
    }

    public String getxNome() {
        return xNome;
    }

    public EnderDest getEnderDest() {
        if (this.enderDest == null) {
            this.enderDest = new EnderDest();
        }
        return enderDest;
    }

    public Integer getIndIEDest() {
        return indIEDest;
    }

    public String getIe() {
        return ie;
    }

    public String getIsuf() {
        return isuf;
    }

    public String getIm() {
        return im;
    }

    public String getEmail() {
        return email;
    }

    public Dest setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
        return this;
    }

    public Dest setIdEstrangeiro(String idEstrangeiro) {
        this.idEstrangeiro = idEstrangeiro;
        return this;
    }

    public Dest setxNome(String xNome) {
        this.xNome = xNome;
        return this;
    }

    public Dest setEnderDest(EnderDest enderDest) {
        this.enderDest = enderDest;
        return this;
    }

    public Dest setIndIEDest(Integer indIEDest) {
        this.indIEDest = indIEDest;
        return this;
    }

    public Dest setIe(String ie) {
        this.ie = ie;
        return this;
    }

    public Dest setIsuf(String isuf) {
        this.isuf = isuf;
        return this;
    }

    public Dest setIm(String im) {
        this.im = im;
        return this;
    }

    public Dest setEmail(String email) {
        this.email = email;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {
        if (infNFe.getIde() != null
                && infNFe.getIde().getTpAmb() != null
                && infNFe.getIde().getTpAmb().toString().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO)) {
            infNFe.getDest().setxNome(ConstantesUtil.DEST.XNOME_HOMOLOGACAO);
        }
        getEnderDest().validarRegraNegocio(infNFe);
    }
}