package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TIpi;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigDecimal;

public class Imposto {

    @NfeCampo(tipo = BigDecimal.class
            , id = "M02", tag = "vTotTrib"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VTOTTRIB)
    private BigDecimal vTotTrib;

    @NfeObjeto(id = "N01", tag = "ICMS", ocorrencias = 0, descricao = NfeConsts.DSC_ICMS)
    private ICMS icms;

    @NfeObjeto(id = "NA01", tag = "ICMSUFDest", ocorrencias = 0, descricao = NfeConsts.DSC_ICMSUFDEST)
    private ICMSUFDest icmsUfDest;

    @NfeObjeto(id = "O01", tag = "IPI", ocorrencias = 0, descricao = NfeConsts.DSC_IPI)
    private IPI ipi;

    @NfeObjeto(id = "P01", tag = "II", ocorrencias = 0, descricao = NfeConsts.DSC_II)
    private II ii;

    @NfeObjeto(id = "Q01", tag = "PIS", ocorrencias = 0, descricao = NfeConsts.DSC_PIS)
    private PIS pis;

    @NfeObjeto(id = "R01", tag = "R01", ocorrencias = 0, descricao = NfeConsts.DSC_PISST)
    private PISST pisst;

    @NfeObjeto(id = "S01", tag = "COFINS", ocorrencias = 0, descricao = NfeConsts.DSC_COFINS)
    private COFINS cofins;

    @NfeObjeto(id = "T01", tag = "COFINSST", ocorrencias = 0, descricao = NfeConsts.DSC_COFINSST)
    private COFINSST cofinsst;

    @NfeObjeto(id = "U01", tag = "ISSQN", ocorrencias = 0, descricao = NfeConsts.DSC_ISSQN)
    private ISSQN issqn;

    public void validarRegraNegocio(InfNFe infNFe) {
        if (getIcms() != null && getIcms().isEmpty()) {
            setIcms(null);
        }
        if (infNFe.getEmit().getEnderEmit().getcMun().equals(infNFe.getDest().getEnderDest().getcMun())) {
            this.setIcmsUfDest(null);
        }
        if (getIpi() != null && getIpi().isEmpty()) {
            setIpi(null);
        }
        if (getIi() != null && getIi().isEmpty()) {
            setIi(null);
        }
        if (getPis() != null && getPis().isEmpty()) {
            setPis(null);
        }
        if (getPisst() != null && getPisst().isEmpty()) {
            setPisst(null);
        }
        if (getCofins() != null && getCofins().isEmpty()) {
            setCofins(null);
        }
        if (getCofinsst() != null && getCofinsst().isEmpty()) {
            setCofinsst(null);
        }
        if (getIssqn() != null && getIssqn().isEmpty()) {
            setIssqn(null);
        }

        if (this.getIcms() != null) {
            this.getIcms().validarRegraNegocio(infNFe);
        }
        if (this.getIi() != null) {
            this.getIi().validarRegraNegocio(infNFe);
        }
        if (this.getPis() != null) {
            this.getPis().validarRegraNegocio(infNFe);
        }
        if (this.getPisst() != null) {
            this.getPisst().validarRegraNegocio(infNFe);
        }
        if (this.getCofins() != null) {
            this.getCofins().validarRegraNegocio(infNFe);
        }
        if (this.getCofinsst() != null) {
            this.getCofinsst().validarRegraNegocio(infNFe);
        }
        if (this.getIcmsUfDest() != null) {
            this.getIcmsUfDest().validarRegraNegocio(infNFe);
        }
        if (this.getIpi() != null) {
            this.getIpi().validarRegraNegocio(infNFe);
        }
        if (this.getIssqn() != null) {
            this.getIssqn().validarRegraNegocio(infNFe);
        }
    }

    public TNFe.InfNFe.Det.Imposto build() {
        TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
        if (this.getvTotTrib() != null) {
            imposto.getContent().add(
                    new JAXBElement<>(new QName("vTotTrib"), String.class, this.getvTotTrib().toString()));
        }
        if (this.getIssqn() != null
                && this.getIssqn().getcListServ() != null
                && !this.getIssqn().getcListServ().isEmpty()) {
            gerarImpostoIPI(imposto);
            gerarImpostoISSQN(imposto);
        } else {
            gerarImpostoICMS(imposto);
            gerarImpostoIPI(imposto);
            gerarImpostoII(imposto);
        }
        gerarImpostoPIS(imposto);
        gerarImpostoPISST(imposto);
        gerarImpostoCOFINS(imposto);
        gerarImpostoCOFINSST(imposto);
        gerarImpostoICMSUFDest(imposto);
        return imposto;
    }

    private void gerarImpostoICMS(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.icms != null) {
            TNFe.InfNFe.Det.Imposto.ICMS icms1 = this.icms.build();
            if (icms1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("ICMS"), TNFe.InfNFe.Det.Imposto.ICMS.class, icms1));
            }
        }
    }

    private void gerarImpostoII(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.ii != null) {
            TNFe.InfNFe.Det.Imposto.II ii1 = this.ii.build();
            if (ii1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("II"), TNFe.InfNFe.Det.Imposto.II.class, ii1));
            }
        }
    }

    private void gerarImpostoPIS(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.pis != null) {
            TNFe.InfNFe.Det.Imposto.PIS pis1 = this.pis.build();
            if (pis1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("PIS"), TNFe.InfNFe.Det.Imposto.PIS.class, pis1));
            }
        }
    }

    private void gerarImpostoPISST(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.pisst != null) {
            TNFe.InfNFe.Det.Imposto.PISST pisst1 = this.pisst.build();
            if (pisst1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("PISST"), TNFe.InfNFe.Det.Imposto.PISST.class, pisst1));
            }
        }
    }

    private void gerarImpostoCOFINS(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.cofins != null) {
            TNFe.InfNFe.Det.Imposto.COFINS cofins1 = this.cofins.build();
            if (cofins1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("COFINS"), TNFe.InfNFe.Det.Imposto.COFINS.class, cofins1)
                );
            }
        }
    }
    private void gerarImpostoCOFINSST(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.cofinsst != null) {
            TNFe.InfNFe.Det.Imposto.COFINSST cofinsst1 = this.cofinsst.build();
            if (cofinsst1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("COFINSST"), TNFe.InfNFe.Det.Imposto.COFINSST.class, cofinsst1)
                );
            }
        }
    }
    private void gerarImpostoICMSUFDest(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.icmsUfDest != null
                && this.icmsUfDest.getpICMSInterPart().compareTo(BigDecimal.ZERO) > 0) {
            TNFe.InfNFe.Det.Imposto.ICMSUFDest icmsUfDest1 = this.icmsUfDest.build();
            if (icmsUfDest1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("ICMSUFDest"), TNFe.InfNFe.Det.Imposto.ICMSUFDest.class, icmsUfDest1)
                );
            }
        }
    }

    private void gerarImpostoIPI(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.ipi != null) {
            TIpi ipi1 = ipi.build();
            if (ipi1 != null){
                imposto.getContent().add(
                        new JAXBElement<>(new QName("IPI"), TIpi.class, ipi1)
                );
            }
        }
    }

    private void gerarImpostoISSQN(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.issqn != null) {
            TNFe.InfNFe.Det.Imposto.ISSQN issqn1 = issqn.build();
            if (issqn1 != null){
                imposto.getContent().add(
                        new JAXBElement<>(new QName("IPI"), TNFe.InfNFe.Det.Imposto.ISSQN.class, issqn1)
                );
            }
        }
    }

    public BigDecimal getvTotTrib() {
        return vTotTrib;
    }

    public ICMS getIcms() {
        return icms;
    }

    public ICMSUFDest getIcmsUfDest() {
        return icmsUfDest;
    }

    public IPI getIpi() {
        return ipi;
    }

    public II getIi() {
        return ii;
    }

    public PIS getPis() {
        return pis;
    }

    public PISST getPisst() {
        return pisst;
    }

    public COFINS getCofins() {
        return cofins;
    }

    public COFINSST getCofinsst() {
        return cofinsst;
    }

    public ISSQN getIssqn() {
        return issqn;
    }

    public Imposto setvTotTrib(BigDecimal vTotTrib) {
        this.vTotTrib = vTotTrib;
        return this;
    }

    public Imposto setIcms(ICMS icms) {
        this.icms = icms;
        return this;
    }

    public Imposto setIcmsUfDest(ICMSUFDest icmsUfDest) {
        this.icmsUfDest = icmsUfDest;
        return this;
    }

    public Imposto setIpi(IPI ipi) {
        this.ipi = ipi;
        return this;
    }

    public Imposto setIi(II ii) {
        this.ii = ii;
        return this;
    }

    public Imposto setPis(PIS pis) {
        this.pis = pis;
        return this;
    }

    public Imposto setPisst(PISST pisst) {
        this.pisst = pisst;
        return this;
    }

    public Imposto setCofins(COFINS cofins) {
        this.cofins = cofins;
        return this;
    }

    public Imposto setCofinsst(COFINSST cofinsst) {
        this.cofinsst = cofinsst;
        return this;
    }

    public Imposto setIssqn(ISSQN issqn) {
        this.issqn = issqn;
        return this;
    }
}