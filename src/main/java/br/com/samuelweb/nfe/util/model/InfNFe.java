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
        if (this.ide == null) {
            this.ide = new Ide();
        }
        return this.ide;
    }

    public Emit getEmit() {
        if (this.emit == null) {
            this.emit = new Emit();
        }
        return this.emit;
    }

    public Avulsa getAvulsa() {
        return avulsa;
    }

    public Dest getDest() {
        return dest;
    }

    public Retirada getRetirada() {
        return retirada;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public Total getTotal() {
        if (total == null) {
            total = new Total();
        }
        return total;
    }

    public Transp getTransp() {
        if (transp == null) {
            transp = new Transp();
        }
        return transp;
    }

    public Cobr getCobr() {
        return cobr;
    }

    public InfAdic getInfAdic() {
        return infAdic;
    }

    public Exporta getExporta() {
        return exporta;
    }

    public Compra getCompra() {
        return compra;
    }

    public Cana getCana() {
        return cana;
    }

    public Pag getPag() {
        return pag;
    }

    public InfNFe setPag(Pag pag) {
        this.pag = pag;
        return this;
    }

    public List<AutXML> getAutXMLList() {
        if (this.autXMLList == null) {
            autXMLList = new ArrayList<>();
        }
        return autXMLList;
    }

    public InfNFe addAllAutXMLList(List<AutXML> autList){
        this.getAutXMLList().addAll(autList);
        return this;
    }

    public List<Det> getDetList() {
        if (this.detList == null) {
            detList = new ArrayList<>();
        }
        return detList;
    }

    public InfNFe addAllDetList(List<Det> detList){
        this.getDetList().addAll(detList);
        return this;
    }

    public InfNFe setIde(Ide ide) {
        this.ide = ide;
        return this;
    }

    public InfNFe setEmit(Emit emit) {
        this.emit = emit;
        return this;
    }

    public InfNFe setAvulsa(Avulsa avulsa) {
        this.avulsa = avulsa;
        return this;
    }

    public InfNFe setDest(Dest dest) {
        this.dest = dest;
        return this;
    }

    public InfNFe setRetirada(Retirada retirada) {
        this.retirada = retirada;
        return this;
    }

    public InfNFe setEntrega(Entrega entrega) {
        this.entrega = entrega;
        return this;
    }

    public InfNFe setTotal(Total total) {
        this.total = total;
        return this;
    }

    public InfNFe setTransp(Transp transp) {
        this.transp = transp;
        return this;
    }

    public InfNFe setCobr(Cobr cobr) {
        this.cobr = cobr;
        return this;
    }

    public InfNFe setInfAdic(InfAdic infAdic) {
        this.infAdic = infAdic;
        return this;
    }

    public InfNFe setExporta(Exporta exporta) {
        this.exporta = exporta;
        return this;
    }

    public InfNFe setCompra(Compra compra) {
        this.compra = compra;
        return this;
    }

    public InfNFe setCana(Cana cana) {
        this.cana = cana;
        return this;
    }

    public void validarRegraNegocio() {
        if (this.getIde() != null) {
            this.getIde().validarRegraNegocio(this);
        }
        if (this.getEmit() != null) {
            this.getEmit().validarRegraNegocio(this);
        }
        if (this.getAvulsa() != null) {
            this.getAvulsa().validarRegraNegocio(this);
        }
        if (this.getDest() != null) {
            this.getDest().validarRegraNegocio(this);
        }
        if (this.getRetirada() != null) {
            this.getRetirada().validarRegraNegocio(this);
        }
        if (this.getEntrega() != null) {
            this.getEntrega().validarRegraNegocio(this);
        }
        if (this.getAutXMLList() != null) {
            this.getAutXMLList().forEach(autXML -> autXML.validarRegraNegocio(this));
        }
        if (this.getDetList() != null) {
            this.getDetList().forEach(det -> det.validarRegraNegocio(this));
        }
        if (this.getTotal() != null) {
            this.getTotal().validarRegraNegocio(this);
        }
        if (this.getTransp() != null) {
            this.getTransp().validarRegraNegocio(this);
        }
        if (this.getCobr() != null) {
            this.getCobr().validarRegraNegocio(this);
        }
        if (this.getPag() != null) {
            this.getPag().validarRegraNegocio(this);
        }
        if (this.getInfAdic() != null) {
            this.getInfAdic().validarRegraNegocio(this);
        }
        if (this.getExporta() != null) {
            this.getExporta().validarRegraNegocio(this);
        }
        if (this.getCompra() != null) {
            this.getCompra().validarRegraNegocio(this);
        }
        if (this.getCana() != null) {
            this.getCana().validarRegraNegocio(this);
        }
    }
}