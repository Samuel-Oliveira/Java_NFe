package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class InfAdic {

    @NfeCampo(id = "Z02", tag = "infAdFisco", tamanhoMinimo = 1, tamanhoMaximo = 2000
            , ocorrencias = 0, descricao = NfeConsts.DSC_INFADFISCO)
    private String infAdFisco;

    @NfeCampo(id = "Z03", tag = "infCpl", tamanhoMinimo = 1, tamanhoMaximo = 5000
            , ocorrencias = 0, descricao = NfeConsts.DSC_INFCPL)
    private String infCpl;

    @NfeObjetoList(id = "Z04", tag = "obsCont"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 10, descricao = NfeConsts.DSC_OBSCONT)
    private List<ObsCont> obsCont;

    @NfeObjetoList(id = "Z07", tag = "obsFisco"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 10, descricao = NfeConsts.DSC_OBSFISCO)
    private List<ObsFisco> obsFisco;

    @NfeObjetoList(id = "Z10", tag = "procRef"
            , ocorrenciaMinima = 0, ocorrenciaMaxima = 100, descricao = NfeConsts.DSC_PROCREF)
    private List<ProcRef> procRef;

    public TNFe.InfNFe.InfAdic build() {
        TNFe.InfNFe.InfAdic infAdic = new TNFe.InfNFe.InfAdic();

        infAdic.setInfAdFisco(this.getInfAdFisco());
        infAdic.setInfCpl(this.getInfCpl());

        if (this.getObsCont() != null) {
            if (this.getObsCont().size() <= 20) {
                this.getObsCont().forEach(e -> infAdic.getObsCont().add(e.build()));
            }
        }

        if (this.getObsFisco() != null) {
            if (this.getObsFisco().size() <= 20) {
                this.getObsFisco().forEach(e -> infAdic.getObsFisco().add(e.build()));
            }
        }
        if (this.getProcRef() != null) {
            this.getProcRef().forEach(e -> infAdic.getProcRef().add(e.build()));
        }

        return infAdic;
    }

    public String getInfAdFisco() {
        return infAdFisco;
    }

    public void setInfAdFisco(String infAdFisco) {
        this.infAdFisco = infAdFisco;
    }

    public String getInfCpl() {
        return infCpl;
    }

    public void setInfCpl(String infCpl) {
        this.infCpl = infCpl;
    }

    public List<ObsCont> getObsCont() {
        return obsCont;
    }

    public void setObsCont(List<ObsCont> obsCont) {
        this.obsCont = obsCont;
    }

    public List<ObsFisco> getObsFisco() {
        return obsFisco;
    }

    public void setObsFisco(List<ObsFisco> obsFisco) {
        this.obsFisco = obsFisco;
    }

    public List<ProcRef> getProcRef() {
        return procRef;
    }

    public void setProcRef(List<ProcRef> procRef) {
        this.procRef = procRef;
    }


}
