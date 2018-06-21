package br.com.samuelweb.nfe.util.model;

import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InfNFe {

    private Ide ide;
    private Emit emit;
    private Avulsa avulsa;
    private Dest dest;
    private Retirada retirada;
    private Entrega entrega;
    private List<AutXML> autXMLList;
    private List<Det> detList;
    private Total total;
    private Transp transp;
    private Cobr cobr;
    //private Pag pag;
    private InfAdic infAdic;
    private Exporta exporta;
    private Compra compra;
    private Cana cana;

    public TNFe.InfNFe build() {
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        if (this.ide != null) {
            infNFe.setIde(this.ide.build());
        }
        if (this.emit != null) {
            infNFe.setEmit (this.emit.build());
        }
        if (this.avulsa != null) {
            infNFe.setAvulsa(this.avulsa.build());
        }
        if (this.dest != null) {
            infNFe.setDest (this.dest.build());
        }
        if (this.retirada != null) {
            infNFe.setRetirada (this.retirada.build());
        }
        if (this.entrega != null) {
            infNFe.setEntrega (this.entrega.build());
        }
        if (this.autXMLList != null) {
            this.autXMLList.forEach(autXML ->  infNFe.getAutXML().add(autXML.build()));
        }
        if (this.detList != null) {
            Integer i = 0;
            this.detList.forEach(det -> {
                i++;
                det.setnItem(i.toString());
                infNFe.getDet().add(det.build());
            });
        }
        if (this.total != null) {
            infNFe.setTotal (this.total.build());
        }
        if (this.transp != null) {
            infNFe.setTransp (this.transp.build());
        }
        if (this.cobr != null) {
            infNFe.setCobr (this.cobr.build());
        }
        /*if (this.pag != null) {
            infNFe.setPag(this.pag.build());
        }*/
        if (this.infAdic != null) {
            infNFe.setInfAdic (this.infAdic.build());
        }
        if (this.exporta != null) {
            infNFe.setExporta (this.exporta.build());
        }
        if (this.compra != null) {
            infNFe.setCompra (this.compra.build());
        }
        if (this.cana != null) {
            infNFe.setCana (this.cana.build());
        }
        infNFe.setId(gerarChaveAcesso());
        return infNFe;
    }

    private String gerarChaveAcesso() {
        /*
        // Se o usuario informar um c�digo inferior a -2 a chave n�o ser� gerada //
        if ACodigo < -2 then
        raise EACBrDFeException.Create('C�digo Num�rico inv�lido, Chave n�o Gerada');

        // Se o usuario informar 0 ou -1; o c�digo numerico sera gerado de maneira aleat�ria //
        if ACodigo = -1 then
        ACodigo := 0;

        while ACodigo = 0 do
            begin
        ACodigo := Random(99999999);
        end;

        // Se o usuario informar -2; o c�digo numerico sera ZERO //
        if ACodigo = -2 then
        ACodigo := 0;*/


        StringUtils.leftPad()
        //vUF          := Poem_Zeros(AUF, 2);
        this.ide.getCuf();

        //vDataEmissao := FormatDateTime('YYMM', ADataEmissao);
        this.ide.getDhEmi().format(DateTimeFormatter.ofPattern("yyMM"));

        //vCNPJ        := copy(OnlyNumber(ACNPJ) + '00000000000000', 1, 14);
        (this.emit.getCnpjCpf() + "00000000000000").substring(0, 13);

        //vModelo      := Poem_Zeros(AModelo, 2);
        this.ide.getMod();

        //vSerie       := Poem_Zeros(ASerie, 3);
        this.ide.getSerie();

        //vNumero      := Poem_Zeros(ANumero, 9);
        this.ide.getNnf();

        //vtpEmi       := Poem_Zeros(AtpEmi, 1);
        this.ide.getTpEmis();

        //vCodigo      := Poem_Zeros(ACodigo, 8);
        this.ide.getCnf();

        //Calcular Digito Verificador.

        Result := vUF + vDataEmissao + vCNPJ + vModelo + vSerie + vNumero + vtpEmi + vCodigo;
        Result := Result + Modulo11(Result);
    }

    public Ide getIde() {
        return ide;
    }

    public void setIde(Ide ide) {
        this.ide = ide;
    }

    public Emit getEmit() {
        return emit;
    }

    public void setEmit(Emit emit) {
        this.emit = emit;
    }

    public Avulsa getAvulsa() {
        return avulsa;
    }

    public void setAvulsa(Avulsa avulsa) {
        this.avulsa = avulsa;
    }

    public Dest getDest() {
        return dest;
    }

    public void setDest(Dest dest) {
        this.dest = dest;
    }

    public Retirada getRetirada() {
        return retirada;
    }

    public void setRetirada(Retirada retirada) {
        this.retirada = retirada;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Transp getTransp() {
        return transp;
    }

    public void setTransp(Transp transp) {
        this.transp = transp;
    }

    public Cobr getCobr() {
        return cobr;
    }

    public void setCobr(Cobr cobr) {
        this.cobr = cobr;
    }

    public InfAdic getInfAdic() {
        return infAdic;
    }

    public void setInfAdic(InfAdic infAdic) {
        this.infAdic = infAdic;
    }

    public Exporta getExporta() {
        return exporta;
    }

    public void setExporta(Exporta exporta) {
        this.exporta = exporta;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Cana getCana() {
        return cana;
    }

    public void setCana(Cana cana) {
        this.cana = cana;
    }

    public List<AutXML> getAutXMLList() {
        if (this.autXMLList == null) {
            autXMLList = new ArrayList<>();
        }
        return autXMLList;
    }

    public List<Det> getDetList() {
        if (this.detList == null) {
            detList = new ArrayList<>();
        }
        return detList;
    }
}
