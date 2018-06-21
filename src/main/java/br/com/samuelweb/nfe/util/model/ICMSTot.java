package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.inf.portalfiscal.nfe.schema_4.nfe.TNFe;

import java.math.BigDecimal;

public class ICMSTot {

    @NfeCampo(tipo = BigDecimal.class, id = "W03", tag = "vBC  "
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VBC)
    private BigDecimal vbc;

    @NfeCampo(tipo = BigDecimal.class, id = "W04", tag = "vICMS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VICMS)
    private BigDecimal vicms;

    @NfeCampo(tipo = BigDecimal.class, id = "W04a", tag = "vICMSDeson"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VICMSDESON)
    private BigDecimal vicmsDeson;

    @NfeCampo(tipo = BigDecimal.class, id = "W04c", tag = "vFCPUFDest"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VICMS)
    private BigDecimal vfcpufDest;

    @NfeCampo(tipo = BigDecimal.class, id = "W04e", tag = "vICMSUFDest"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VICMS)
    private BigDecimal vicmsufDest;

    @NfeCampo(tipo = BigDecimal.class, id = "W04g", tag = "vICMSUFRemet"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VICMS)
    private BigDecimal vicmsufRemet;

    @NfeCampo(tipo = BigDecimal.class, id = "W04h", tag = "vFCP"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VFCP)
    private BigDecimal vfcp;

    @NfeCampo(tipo = BigDecimal.class, id = "W05", tag = "vBCST"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VBCST)
    private BigDecimal vbcst;

    @NfeCampo(tipo = BigDecimal.class, id = "W06", tag = "vST"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VST)
    private BigDecimal vst;

    @NfeCampo(tipo = BigDecimal.class, id = "W06a", tag = "vFCPST"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VFCPST)
    private BigDecimal vfcpst;

    @NfeCampo(tipo = BigDecimal.class, id = "W06b", tag = "vFCPSTRet"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VFCPSTRET)
    private BigDecimal vfcpstRet;

    @NfeCampo(tipo = BigDecimal.class, id = "W07", tag = "vProd"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VPROD)
    private BigDecimal vProd;

    @NfeCampo(tipo = BigDecimal.class, id = "W08", tag = "vFrete"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VFRETE)
    private BigDecimal vFrete;

    @NfeCampo(tipo = BigDecimal.class, id = "W09", tag = "vSeg"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VSEG)
    private BigDecimal vSeg;

    @NfeCampo(tipo = BigDecimal.class, id = "W10", tag = "vDesc"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VDESC)
    private BigDecimal vDesc;

    @NfeCampo(tipo = BigDecimal.class, id = "W11", tag = "vII"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VII)
    private BigDecimal vii;

    @NfeCampo(tipo = BigDecimal.class, id = "W12", tag = "vIPI"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VIPI)
    private BigDecimal vipi;

    @NfeCampo(tipo = BigDecimal.class, id = "W12a", tag = "vIPIDevol"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VIPIDEVOL)
    private BigDecimal vipiDevol;

    @NfeCampo(tipo = BigDecimal.class, id = "W13", tag = "vPIS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VPIS)
    private BigDecimal vpis;

    @NfeCampo(tipo = BigDecimal.class, id = "W14", tag = "vCOFINS"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VCOFINS)
    private BigDecimal vcofins;

    @NfeCampo(tipo = BigDecimal.class, id = "W15", tag = "vOutro"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VOUTRO)
    private BigDecimal vOutro;

    @NfeCampo(tipo = BigDecimal.class, id = "W16", tag = "vNF"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 1
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VNF)
    private BigDecimal vnf;

    @NfeCampo(tipo = BigDecimal.class, id = "W16a", tag = "vTotTrib"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , decimais = 2, precisao = 15
            , descricao = NfeConsts.DSC_VTOTTRIB)
    private BigDecimal vTotTrib;

    public TNFe.InfNFe.Total.ICMSTot build() {
        TNFe.InfNFe.Total.ICMSTot icmsTot = new TNFe.InfNFe.Total.ICMSTot();

        if (this.vbc != null) {
            icmsTot.setVBC(this.vbc.toString());
        }
        if (this.vicms != null) {
            icmsTot.setVICMS(this.vicms.toString());
        }
        if (this.vicmsDeson != null) {
            icmsTot.setVICMSDeson(this.vicmsDeson.toString());
        }
        if (this.vfcpufDest != null) {
            icmsTot.setVFCPUFDest(this.vfcpufDest.toString());
        }
        if (this.vicmsufDest != null) {
            icmsTot.setVICMSUFDest(this.vicmsufDest.toString());
        }
        if (this.vicmsufRemet != null) {
            icmsTot.setVICMSUFRemet(this.vicmsufRemet.toString());
        }
        if (this.vfcp != null) {
            icmsTot.setVFCP(this.vfcp.toString());
        }
        if (this.vbcst != null) {
            icmsTot.setVBCST(this.vbcst.toString());
        }
        if (this.vst != null) {
            icmsTot.setVST(this.vst.toString());
        }
        if (this.vfcpst != null) {
            icmsTot.setVFCPST(this.vfcpst.toString());
        }
        if (this.vfcpstRet != null) {
            icmsTot.setVFCPSTRet(this.vfcpstRet.toString());
        }
        if (this.vProd != null) {
            icmsTot.setVProd(this.vProd.toString());
        }
        if (this.vFrete != null) {
            icmsTot.setVFrete(this.vFrete.toString());
        }
        if (this.vSeg != null) {
            icmsTot.setVSeg(this.vSeg.toString());
        }
        if (this.vDesc != null) {
            icmsTot.setVDesc(this.vDesc.toString());
        }
        if (this.vii != null) {
            icmsTot.setVII(this.vii.toString());
        }
        if (this.vipi != null) {
            icmsTot.setVIPI(this.vipi.toString());
        }
        if (this.vipiDevol != null) {
            icmsTot.setVIPIDevol(this.vipiDevol.toString());
        }
        if (this.vpis != null) {
            icmsTot.setVPIS(this.vpis.toString());
        }
        if (this.vcofins != null) {
            icmsTot.setVCOFINS(this.vcofins.toString());
        }
        if (this.vOutro != null) {
            icmsTot.setVOutro(this.vOutro.toString());
        }
        if (this.vnf != null) {
            icmsTot.setVNF(this.vnf.toString());
        }
        if (this.vTotTrib != null) {
            icmsTot.setVTotTrib(this.vTotTrib.toString());
        }

        return icmsTot;
    }

    public BigDecimal getVbc() {
        return vbc;
    }

    public void setVbc(BigDecimal vbc) {
        this.vbc = vbc;
    }

    public BigDecimal getVicms() {
        return vicms;
    }

    public void setVicms(BigDecimal vicms) {
        this.vicms = vicms;
    }

    public BigDecimal getVicmsDeson() {
        return vicmsDeson;
    }

    public void setVicmsDeson(BigDecimal vicmsDeson) {
        this.vicmsDeson = vicmsDeson;
    }

    public BigDecimal getVfcpufDest() {
        return vfcpufDest;
    }

    public void setVfcpufDest(BigDecimal vfcpufDest) {
        this.vfcpufDest = vfcpufDest;
    }

    public BigDecimal getVicmsufDest() {
        return vicmsufDest;
    }

    public void setVicmsufDest(BigDecimal vicmsufDest) {
        this.vicmsufDest = vicmsufDest;
    }

    public BigDecimal getVicmsufRemet() {
        return vicmsufRemet;
    }

    public void setVicmsufRemet(BigDecimal vicmsufRemet) {
        this.vicmsufRemet = vicmsufRemet;
    }

    public BigDecimal getVfcp() {
        return vfcp;
    }

    public void setVfcp(BigDecimal vfcp) {
        this.vfcp = vfcp;
    }

    public BigDecimal getVbcst() {
        return vbcst;
    }

    public void setVbcst(BigDecimal vbcst) {
        this.vbcst = vbcst;
    }

    public BigDecimal getVst() {
        return vst;
    }

    public void setVst(BigDecimal vst) {
        this.vst = vst;
    }

    public BigDecimal getVfcpst() {
        return vfcpst;
    }

    public void setVfcpst(BigDecimal vfcpst) {
        this.vfcpst = vfcpst;
    }

    public BigDecimal getVfcpstRet() {
        return vfcpstRet;
    }

    public void setVfcpstRet(BigDecimal vfcpstRet) {
        this.vfcpstRet = vfcpstRet;
    }

    public BigDecimal getvProd() {
        return vProd;
    }

    public void setvProd(BigDecimal vProd) {
        this.vProd = vProd;
    }

    public BigDecimal getvFrete() {
        return vFrete;
    }

    public void setvFrete(BigDecimal vFrete) {
        this.vFrete = vFrete;
    }

    public BigDecimal getvSeg() {
        return vSeg;
    }

    public void setvSeg(BigDecimal vSeg) {
        this.vSeg = vSeg;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public void setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
    }

    public BigDecimal getVii() {
        return vii;
    }

    public void setVii(BigDecimal vii) {
        this.vii = vii;
    }

    public BigDecimal getVipi() {
        return vipi;
    }

    public void setVipi(BigDecimal vipi) {
        this.vipi = vipi;
    }

    public BigDecimal getVipiDevol() {
        return vipiDevol;
    }

    public void setVipiDevol(BigDecimal vipiDevol) {
        this.vipiDevol = vipiDevol;
    }

    public BigDecimal getVpis() {
        return vpis;
    }

    public void setVpis(BigDecimal vpis) {
        this.vpis = vpis;
    }

    public BigDecimal getVcofins() {
        return vcofins;
    }

    public void setVcofins(BigDecimal vcofins) {
        this.vcofins = vcofins;
    }

    public BigDecimal getvOutro() {
        return vOutro;
    }

    public void setvOutro(BigDecimal vOutro) {
        this.vOutro = vOutro;
    }

    public BigDecimal getVnf() {
        return vnf;
    }

    public void setVnf(BigDecimal vnf) {
        this.vnf = vnf;
    }

    public BigDecimal getvTotTrib() {
        return vTotTrib;
    }

    public void setvTotTrib(BigDecimal vTotTrib) {
        this.vTotTrib = vTotTrib;
    }
}
