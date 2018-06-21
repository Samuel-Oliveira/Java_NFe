package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
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
    private Object icmsUfDest;


    public TNFe.InfNFe.Det.Imposto build() {
        TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
        //todo verificar como impementar esse atributo
        /*if (this.vTotTrib != null) {
            imposto.setVTotTrib(this.vTotTrib.toString());
            Gerador.wCampo(tcDe2, 'M02', 'vTotTrib ', 01, 15, 0, nfe.Det[i].Imposto.vTotTrib, DSC_VTOTTRIB);
        }*/
        if (this.issqn != null
                /*&& ((this.issqn.cSitTrib <> ISSQNcSitTribVazio)
                 || (this.issqn.cListServ <> ''))*/) {
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
        //if nfe.Det[i].Imposto.ICMSUFDest.pICMSInterPart > 0 then
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
        if (this.icmsUfDest != null) {
            //todo impelmentar
            /*TNFe.InfNFe.Det.Imposto.ICMSUFDest icmsUfDest1 = this.icmsUfDest.build();
            if (icmsUfDest1 != null) {
                imposto.getContent().add(
                        new JAXBElement<>(new QName("ICMSUFDest"), TNFe.InfNFe.Det.Imposto.ICMSUFDest.class, icmsUfDest1)
                );
            }*/
        }
    }

    private void gerarImpostoIPI(TNFe.InfNFe.Det.Imposto imposto) {
        if (this.ipi != null) {
            //todo implementar
            /*TNFe.InfNFe.Det.Imposto.IPI ipi1 = ipi.build();
            if (ipi1 != null){
                imposto.getContent().add(
                        new JAXBElement<>(new QName("IPI"), TNFe.InfNFe.Det.Imposto.IPI.class, ipi1)
                );
            }*/
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
