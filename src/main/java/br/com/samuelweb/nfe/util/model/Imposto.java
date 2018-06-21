package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TIpi;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigDecimal;

public class Imposto {

    @NfeCampo(tipo = BigDecimal.class
            , id = "M02", tag = "vTotTrib"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VTOTTRIB)
    private BigDecimal vTotTrib;

    private ICMS icms;
    private IPI ipi;
    private II ii;
    private PIS pis;
    private PISST pisst;
    private COFINS cofins;
    private COFINSST cofinsst;
    private ISSQN issqn;
    private ICMSUFDest icmsUfDest;

    public TNFe.InfNFe.Det.Imposto build() {
        TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
        if (this.vTotTrib != null) {
            imposto.getContent().add(
                    new JAXBElement<>(new QName("vTotTrib"), String.class, this.vTotTrib.toString()));
        }
        if (this.issqn != null
                && !this.issqn.getcListServ().isEmpty()) {
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
}
