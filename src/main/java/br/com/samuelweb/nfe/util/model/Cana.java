package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;
import java.util.List;

public class Cana {

    @NfeCampo(tipo = String.class
            , id = "ZC02", tag = "safra"
            , tamanhoMinimo = 4, tamanhoMaximo = 9, ocorrencias = 0
            , descricao = NfeConsts.DSC_SAFRA)
    private String safra;

    @NfeCampo(tipo = String.class
            , id = "ZC03", tag = "ref"
            , tamanhoMinimo = 04, tamanhoMaximo = 09, ocorrencias = 0
            , descricao = NfeConsts.DSC_REF)
    private String vLiqFor;
    private String ref;

    private List<TNFe.InfNFe.Cana.ForDia> forDia;

    private String qTotMes;

    private String qTotAnt;

    private String qTotGer;
    //private List<TNFe.InfNFe.Cana.Deduc> deduc;

    private String vFor;

    @NfeCampo(tipo = BigDecimal.class
            , id = "ZC14", tag = "vTotDed", decimais = 2, precisao = 15
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , descricao = NfeConsts.DSC_VTOTDED)
    private BigDecimal vTotDed;




     (**)GerarforDia;
    Gerador.wCampo(tcDe10,"ZC07","qTotMes", 01, 21, 1, nfe.cana.qTotMes, DSC_QTOTMES);
    Gerador.wCampo(tcDe10,"ZC08","qTotAnt", 01, 21, 1, nfe.cana.qTotAnt, DSC_QTOTANT);
    Gerador.wCampo(tcDe10,"ZC09","qTotGer", 01, 21, 1, nfe.cana.qTotGer, DSC_TOTGER);
     (**)GerarDeduc;
    Gerador.wCampo(tcDe2,"ZC13","vFor   ", 01, 15, 1, nfe.cana.vFor, DSC_VFOR);
    Gerador.wCampo(tcDe2,"ZC15","vLiqFor", 01, 15, 1, nfe.cana.vLiqFor, DSC_VLIQFOR);


    public TNFe.InfNFe.Cana build() {
        if not(Trim(nfe.cana.safra) = '') or not(Trim(nfe.cana.ref) = '') or
                (nfe.cana.fordia.Count > 0) or (nfe.cana.deduc.Count > 0) {

            TNFe.InfNFe.Cana cana = new TNFe.InfNFe.Cana();

            cana.setSafra(this.safra);

            if (this.forDia != null) {
                this.forDia.forEach( dia - > cana.getForDia().add(dia.build()));
            }
            if (this.vTotDed != null) {
                cana.setVTotDed(this.vTotDed.toString());
            }
            return cana;
        }
        return null;
    }
}
