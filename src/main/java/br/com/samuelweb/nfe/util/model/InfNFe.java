package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.NfeUtil;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.util.ArrayList;
import java.util.List;

import static br.com.samuelweb.nfe.util.enumeration.NfeVersao.NFE_VERSAO_400;

public class InfNFe {

    @NfeObjeto(id = "B01", tag = "ide"
            , ocorrencias = 1, descricao = NfeConsts.DSC_IDE)
    private Ide ide;

    @NfeObjeto(id = "C01", tag = "emit"
            , ocorrencias = 1, descricao = NfeConsts.DSC_EMIT)
    private Emit emit;

    @NfeObjeto(id = "D01", tag = "avulsa"
            , ocorrencias = 0, descricao = NfeConsts.DSC_AVULSA)
    private Avulsa avulsa;

    @NfeObjeto(id = "E01", tag = "dest"
            , ocorrencias = 0, descricao = NfeConsts.DSC_DEST)
    private Dest dest;

    @NfeObjeto(id = "F01", tag = "retirada"
            , ocorrencias = 0, descricao = NfeConsts.DSC_RETIRADA)
    private Retirada retirada;

    @NfeObjeto(id = "G01", tag = "entrega"
            , ocorrencias = 0, descricao = NfeConsts.DSC_ENTREGA)
    private Entrega entrega;

    @NfeObjetoList(id = "GA01", tag = "autXML"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 10
            , descricao = NfeConsts.DSC_AUTXML)
    private List<AutXML> autXMLList;

    @NfeObjetoList(id = "H01", tag = "det"
            , ocorrenciaMinima = 1, ocorrenciaMaxima = 999
            , descricao = NfeConsts.DSC_DET)
    private List<Det> detList;

    @NfeObjeto(id = "W01", tag = "total"
            , ocorrencias = 1, descricao = NfeConsts.DSC_TOTAL)
    private Total total;

    @NfeObjeto(id = "X01", tag = "transp"
            , ocorrencias = 1, descricao = NfeConsts.DSC_TRANSP)
    private Transp transp;

    @NfeObjeto(id = "Y01", tag = "cobr"
            , ocorrencias = 0, descricao = NfeConsts.DSC_COBR)
    private Cobr cobr;

    @NfeObjeto(id = "YA01", tag = "pag", ocorrencias = 1, descricao = NfeConsts.DSC_PAG)
    private Pag pag;

    @NfeObjeto(id = "Z01", tag = "infAdic"
            , ocorrencias = 0, descricao = NfeConsts.DSC_INFADIC)
    private InfAdic infAdic;

    @NfeObjeto(id = "ZA01", tag = "exporta"
            , ocorrencias = 0, descricao = NfeConsts.DSC_EXPORTA)
    private Exporta exporta;

    @NfeObjeto(id = "ZB01", tag = "compra"
            , ocorrencias = 0, descricao = NfeConsts.DSC_COMPRA)
    private Compra compra;

    @NfeObjeto(id = "ZC01", tag = "cana"
            , ocorrencias = 0, descricao = NfeConsts.DSC_CANA)
    private Cana cana;

    public TNFe.InfNFe build() {
        TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.setVersao(NFE_VERSAO_400.getValue());
        infNFe.setId("NFe" + NfeUtil.gerarChaveAcesso(this.getIde(), this.getEmit()));
        if (this.getIde() != null) {
            infNFe.setIde(this.getIde().build());
        }
        if (this.getEmit() != null) {
            infNFe.setEmit(this.getEmit().build());
        }
        if (this.getAvulsa() != null) {
            infNFe.setAvulsa(this.getAvulsa().build());
        }
        if (this.getDest() != null) {
            infNFe.setDest(this.getDest().build());
        }
        if (this.getRetirada() != null) {
            infNFe.setRetirada(this.getRetirada().build());
        }
        if (this.getEntrega() != null) {
            infNFe.setEntrega(this.getEntrega().build());
        }
        if (this.getAutXMLList() != null) {
            this.getAutXMLList().forEach(autXML -> infNFe.getAutXML().add(autXML.build()));
        }
        if (this.getDetList() != null) {
            Integer i = 0;
            for (Det det : this.getDetList()) {
                i++;
                det.setnItem(i.toString());
                infNFe.getDet().add(det.build());
            }
        }
        if (this.getTotal() != null) {
            infNFe.setTotal(this.getTotal().build());
        }
        if (this.getTransp() != null) {
            infNFe.setTransp(this.getTransp().build());
        }
        if (this.getCobr() != null) {
            infNFe.setCobr(this.getCobr().build());
        }
        if (this.getPag() != null) {
            infNFe.setPag(this.getPag().build());
        }
        if (this.getInfAdic() != null) {
            infNFe.setInfAdic(this.getInfAdic().build());
        }
        if (this.getExporta() != null) {
            infNFe.setExporta(this.getExporta().build());
        }
        if (this.getCompra() != null) {
            infNFe.setCompra(this.getCompra().build());
        }
        if (this.getCana() != null) {
            infNFe.setCana(this.getCana().build());
        }
        return infNFe;
    }

    public Ide getIde() {
        if (this.getIde() == null) {
            this.setIde(new Ide());
        }
        return this.getIde();
    }

    public void setIde(Ide ide) {
        this.ide = ide;
    }

    public Emit getEmit() {
        if (this.getEmit() == null) {
            this.setEmit(new Emit());
        }
        return this.getEmit();
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
        if (total == null) {
            total = new Total();
        }
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Transp getTransp() {
        if (transp == null) {
            transp = new Transp();
        }
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

    public Pag getPag() {
        return pag;
    }

    public void setPag(Pag pag) {
        this.pag = pag;
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
