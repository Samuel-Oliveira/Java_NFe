package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class Imposto {

    @NfeCampo(tipo = BigDecimal.class
            , id = "M02", tag = "vTotTrib"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = NfeConsts.DSC_VTOTTRIB)
    private BigDecimal vTotTrib;

    private ICMS icms;

    public TNFe.InfNFe.Det.Imposto build() {
        TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
        //todo verificar como impementar esse atributo
        /*if (this.vTotTrib != null) {
            imposto.setVTotTrib(this.vTotTrib.toString());
        }*/
        /*
  Gerador.wCampo(tcDe2, 'M02', 'vTotTrib ', 01, 15, 0, nfe.Det[i].Imposto.vTotTrib, DSC_VTOTTRIB);
  if (( NFe.Det[i].Imposto.ISSQN.cSitTrib <> ISSQNcSitTribVazio ) or (( NFe.infNFe.Versao > 3 ) and ( nfe.Det[i].Imposto.ISSQN.cListServ <> '' ))) then
   begin
     if NFe.infNFe.Versao >= 3 then
      (**)GerarDetImpostoIPI(i);
     (**)GerarDetImpostoISSQN(i);
   end
  else
   begin
     (**)GerarDetImpostoICMS(i);
     (**)GerarDetImpostoIPI(i);
     (**)GerarDetImpostoII(i);
   end;
  (**)GerarDetImpostoPIS(i);
  (**)GerarDetImpostoPISST(i);
  (**)GerarDetImpostoCOFINS(i);
  (**)GerarDetImpostoCOFINSST(i);
  if nfe.Det[i].Imposto.ICMSUFDest.pICMSInterPart > 0 then
    (**)GerarDetImpostoICMSUFDest(i);
*/

        return imposto;
    }
}
