package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.ConsultaTributacao;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dto.ClassificacaoTributariaDTO;
import br.com.swconsultoria.nfe.dto.CstDTO;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas.*;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NonNull;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class IbsCbsUtil {

    private static final String TOTAL_BC_IBS_CBS = "TOTAL_BC_IBS_CBS";
    private static final String TOTAL_IBS_UF = "TOTAL_IBS_UF";
    private static final String TOTAL_IBS_MUN = "TOTAL_IBS_MUN";
    private static final String TOTAL_CBS = "TOTAL_CBS";
    private static final String TOTAL_DIFERIMENTO_IBS_UF = "TOTAL_DIFERIMENTO_IBS_UF";
    private static final String TOTAL_DIFERIMENTO_IBS_MUN = "TOTAL_DIFERIMENTO_IBS_MUN";
    private static final String TOTAL_DIFERIMENTO_CBS = "TOTAL_DIFERIMENTO_CBS";
    private static final String TOTAL_IBS_MONO = "TOTAL_IBS_MONO";
    private static final String TOTAL_CBS_MONO = "TOTAL_CBS_MONO";
    private static final String TOTAL_IBS_MONO_RETEN = "TOTAL_IBS_MONO_RETEN";
    private static final String TOTAL_CBS_MONO_RETEN = "TOTAL_CBS_MONO_RETEN";
    private static final String TOTAL_IBS_MONO_RET = "TOTAL_IBS_MONO_RET";
    private static final String TOTAL_CBS_MONO_RET = "TOTAL_CBS_MONO_RET";
    private static final String TOTAL_IBS_EST_CRED = "TOTAL_IBS_EST_CRED";
    private static final String TOTAL_CBS_EST_CRED = "TOTAL_CBS_EST_CRED";
    private static final BigDecimal CEM = BigDecimal.valueOf(100);
    private static final int SCALE_5 = 5;

    private final List<CstDTO> listaCstIbsCbs;
    private final DocumentoEnum documento;
    private final Map<String, BigDecimal> mapTotais = new HashMap<>();

    private ClassificacaoTributariaDTO classTribIbsCbs;
    private CstDTO cstIbsCbs;
    private ClassificacaoTributariaDTO classTribIbsCbsTribRegular;
    private CstDTO cstIbsCbsTribRegular;

    private BigDecimal pAliqIbsUf = new BigDecimal("0.1");
    private BigDecimal pAliqIbsMun = BigDecimal.ZERO;
    private BigDecimal pAliqCbs = new BigDecimal("0.9");
    private BigDecimal pAliqDiferimento = BigDecimal.ZERO;
    private BigDecimal baseCalculo = BigDecimal.ZERO;

    /**
     * Código de crédito presumido da tabela SEFAZ (TcCredPres).
     * Deve ser preenchido pelo integrador conforme tabela publicada pela SEFAZ
     * antes de chamar montaImpostosDet com um cClassTrib que ative IndCredPresOper.
     */
    private String cCredPres = null;

    /**
     * Percentual de crédito presumido para o grupo IBS (TCredPres.pCredPres).
     * Exigido quando IndCredPresOper=true.
     */
    private String pCredPresIBS = "0.0000";

    /**
     * Percentual de crédito presumido para o grupo CBS (TCredPres.pCredPres).
     * Exigido quando IndCredPresOper=true.
     */
    private String pCredPresCBS = "0.0000";

    /**
     * Competência de apuração para gAjusteCompet (formato gYearMonth, ex: "2025-01").
     * Deve ser preenchida pelo integrador antes de usar cClassTrib com IndAjusteCompet=true.
     */
    private javax.xml.datatype.XMLGregorianCalendar competApur = null;

    /**
     * Tipo de crédito presumido IBS-ZFM (TTpCredPresIBSZFM).
     * Deve ser preenchido pelo integrador conforme tabela SEFAZ antes de usar IndCredPresIBSZFM=true.
     */
    private String tpCredPresIBSZFM = null;

    /**
     * Tipo de ente governamental (TEnteGov) para gTribCompraGov.
     * Exigido quando IndCompraGov=true.
     */
    private String tpEnteGov = null;

    /**
     * Percentual redutor para gTribCompraGov (TDec_0302_04RTC).
     * Exigido quando IndCompraGov=true.
     */
    private String pRedutorCompraGov = "0.0000";

    /**
     * Tipo de operação governamental (TOperCompraGov) para gTribCompraGov.
     * Exigido quando IndCompraGov=true.
     */
    private String tpOperGov = null;

    /**
     * Modalidade dos grupos de monofasia. A NT que reestruturou o TMonofasia separou
     * a tributação em ad valorem (percentual sobre base) e ad rem (valor por unidade),
     * que são mutuamente exclusivas no XSD.
     * Padrão false (ad rem), modalidade usada em combustíveis.
     */
    private boolean monofasiaAdValorem = false;

    /**
     * Alíquota ad rem do IBS, em valor por unidade de medida (tabela de alíquotas
     * específicas de combustíveis). Usada em gMonoPadrao: vIBSMono = qBCMono x adRemIBS.
     */
    private BigDecimal adRemIBS = BigDecimal.ZERO;

    /**
     * Alíquota ad rem da CBS, em valor por unidade de medida.
     * Usada em gMonoPadrao: vCBSMono = qBCMono x adRemCBS.
     */
    private BigDecimal adRemCBS = BigDecimal.ZERO;

    /** Alíquota ad rem do IBS sujeito à retenção. Se não informada, usa {@link #adRemIBS}. */
    private BigDecimal adRemIBSReten = null;

    /** Alíquota ad rem da CBS sujeita à retenção. Se não informada, usa {@link #adRemCBS}. */
    private BigDecimal adRemCBSReten = null;

    /** Alíquota ad valorem do IBS Estadual, em percentual. vIBSMonoUF = vBCMono x pAliqMonoUF / 100. */
    private BigDecimal pAliqMonoUF = BigDecimal.ZERO;

    /** Alíquota ad valorem do IBS Municipal, em percentual. vIBSMonoMun = vBCMono x pAliqMonoMun / 100. */
    private BigDecimal pAliqMonoMun = BigDecimal.ZERO;

    /** Alíquota ad valorem da CBS, em percentual. vCBSMono = vBCMono x pAliqMonoCBS / 100. */
    private BigDecimal pAliqMonoCBS = BigDecimal.ZERO;

    /** Alíquota ad valorem do IBS sujeito à retenção, em percentual. Se não informada, usa pAliqMonoUF + pAliqMonoMun. */
    private BigDecimal pAliqMonoRetenIBS = null;

    /** Alíquota ad valorem da CBS sujeita à retenção, em percentual. Se não informada, usa {@link #pAliqMonoCBS}. */
    private BigDecimal pAliqMonoRetenCBS = null;

    /**
     * Valor do IBS retido anteriormente (gMonoRet). O XSD não tem alíquota nesse grupo,
     * apenas o valor, que vem da operação anterior da cadeia.
     */
    private BigDecimal vIBSMonoRet = BigDecimal.ZERO;

    /** Valor da CBS retida anteriormente (gMonoRet). */
    private BigDecimal vCBSMonoRet = BigDecimal.ZERO;

    /**
     * Quantidade tributada sujeita à retenção (qBCMonoReten / vBCMonoReten).
     * Se não informada, usa a quantidade comercializada do item.
     */
    private BigDecimal qBCMonoReten = null;

    /**
     * Quantidade de biocombustível (EAC) a recolher ou a ressarcir, para o grupo
     * gpBioDiferenca. Usada quando o cClassTrib tem
     * IndPercentualDiferencaBioCombustivel=true.
     */
    private BigDecimal qBCBioComb = BigDecimal.ZERO;

    /**
     * Valor do IBS referente à diferença de percentual de biocombustível.
     * Se não informado, é calculado como qBCBioComb x adRemIBS na modalidade ad rem.
     */
    private BigDecimal vIBSDiferenca = null;

    /**
     * Valor da CBS referente à diferença de percentual de biocombustível.
     * Se não informado, é calculado como qBCBioComb x adRemCBS na modalidade ad rem.
     */
    private BigDecimal vCBSDiferenca = null;

    public void setMonofasiaAdValorem(boolean monofasiaAdValorem) {
        this.monofasiaAdValorem = monofasiaAdValorem;
    }

    public void setAdRemIBS(BigDecimal adRemIBS) {
        this.adRemIBS = adRemIBS;
    }

    public void setAdRemCBS(BigDecimal adRemCBS) {
        this.adRemCBS = adRemCBS;
    }

    public void setAdRemIBSReten(BigDecimal adRemIBSReten) {
        this.adRemIBSReten = adRemIBSReten;
    }

    public void setAdRemCBSReten(BigDecimal adRemCBSReten) {
        this.adRemCBSReten = adRemCBSReten;
    }

    public void setPAliqMonoUF(BigDecimal pAliqMonoUF) {
        this.pAliqMonoUF = pAliqMonoUF;
    }

    public void setPAliqMonoMun(BigDecimal pAliqMonoMun) {
        this.pAliqMonoMun = pAliqMonoMun;
    }

    public void setPAliqMonoCBS(BigDecimal pAliqMonoCBS) {
        this.pAliqMonoCBS = pAliqMonoCBS;
    }

    public void setPAliqMonoRetenIBS(BigDecimal pAliqMonoRetenIBS) {
        this.pAliqMonoRetenIBS = pAliqMonoRetenIBS;
    }

    public void setPAliqMonoRetenCBS(BigDecimal pAliqMonoRetenCBS) {
        this.pAliqMonoRetenCBS = pAliqMonoRetenCBS;
    }

    public void setVIBSMonoRet(BigDecimal vIBSMonoRet) {
        this.vIBSMonoRet = vIBSMonoRet;
    }

    public void setVCBSMonoRet(BigDecimal vCBSMonoRet) {
        this.vCBSMonoRet = vCBSMonoRet;
    }

    public void setQBCMonoReten(BigDecimal qBCMonoReten) {
        this.qBCMonoReten = qBCMonoReten;
    }

    public void setQBCBioComb(BigDecimal qBCBioComb) {
        this.qBCBioComb = qBCBioComb;
    }

    public void setVIBSDiferenca(BigDecimal vIBSDiferenca) {
        this.vIBSDiferenca = vIBSDiferenca;
    }

    public void setVCBSDiferenca(BigDecimal vCBSDiferenca) {
        this.vCBSDiferenca = vCBSDiferenca;
    }

    public void setpAliqIbsUf(BigDecimal pAliqIbsUf) {
        this.pAliqIbsUf = pAliqIbsUf;
    }

    public void setpAliqIbsMun(BigDecimal pAliqIbsMun) {
        this.pAliqIbsMun = pAliqIbsMun;
    }

    public void setpAliqCbs(BigDecimal pAliqCbs) {
        this.pAliqCbs = pAliqCbs;
    }

    public void setpAliqDiferimento(BigDecimal pAliqDiferimento) {
        this.pAliqDiferimento = pAliqDiferimento;
    }

    /**
     * Código de crédito presumido (TcCredPres) da tabela SEFAZ.
     */
    public void setCCredPres(String cCredPres) {
        this.cCredPres = cCredPres;
    }

    /**
     * Percentual de crédito presumido para IBS (TDec_0302_04RTC).
     */
    public void setPCredPresIBS(String pCredPresIBS) {
        this.pCredPresIBS = pCredPresIBS;
    }

    /**
     * Percentual de crédito presumido para CBS (TDec_0302_04RTC).
     */
    public void setPCredPresCBS(String pCredPresCBS) {
        this.pCredPresCBS = pCredPresCBS;
    }

    /**
     * Competência de apuração para gAjusteCompet e gCredPresIBSZFM (gYearMonth).
     */
    public void setCompetApur(javax.xml.datatype.XMLGregorianCalendar competApur) {
        this.competApur = competApur;
    }

    /**
     * Tipo de crédito presumido IBS-ZFM (TTpCredPresIBSZFM), tabela SEFAZ.
     */
    public void setTpCredPresIBSZFM(String tpCredPresIBSZFM) {
        this.tpCredPresIBSZFM = tpCredPresIBSZFM;
    }

    /**
     * Tipo de ente governamental (TEnteGov) para gTribCompraGov, tabela SEFAZ.
     */
    public void setTpEnteGov(String tpEnteGov) {
        this.tpEnteGov = tpEnteGov;
    }

    /**
     * Percentual redutor de gTribCompraGov (TDec_0302_04RTC).
     */
    public void setPRedutorCompraGov(String pRedutorCompraGov) {
        this.pRedutorCompraGov = pRedutorCompraGov;
    }

    /**
     * Tipo de operação governamental (TOperCompraGov) para gTribCompraGov, tabela SEFAZ.
     */
    public void setTpOperGov(String tpOperGov) {
        this.tpOperGov = tpOperGov;
    }

    public IbsCbsUtil(@NonNull List<CstDTO> listaCstIbsCbs, @NonNull DocumentoEnum documento) {
        this.listaCstIbsCbs = listaCstIbsCbs;
        this.documento = documento;
        inicializarTotais();
    }

    public IbsCbsUtil(@NonNull String json, @NonNull DocumentoEnum documento) throws NfeException {
        this.listaCstIbsCbs = ConsultaTributacao.convertJsonToObject(json, new TypeReference<List<CstDTO>>() {});
        this.documento = documento;
        inicializarTotais();
    }

    private void inicializarTotais() {
        mapTotais.put(TOTAL_BC_IBS_CBS, BigDecimal.ZERO);
        mapTotais.put(TOTAL_IBS_UF, BigDecimal.ZERO);
        mapTotais.put(TOTAL_IBS_MUN, BigDecimal.ZERO);
        mapTotais.put(TOTAL_CBS, BigDecimal.ZERO);
        mapTotais.put(TOTAL_DIFERIMENTO_IBS_UF, BigDecimal.ZERO);
        mapTotais.put(TOTAL_DIFERIMENTO_IBS_MUN, BigDecimal.ZERO);
        mapTotais.put(TOTAL_DIFERIMENTO_CBS, BigDecimal.ZERO);
        mapTotais.put(TOTAL_IBS_MONO, BigDecimal.ZERO);
        mapTotais.put(TOTAL_CBS_MONO, BigDecimal.ZERO);
        mapTotais.put(TOTAL_IBS_MONO_RETEN, BigDecimal.ZERO);
        mapTotais.put(TOTAL_CBS_MONO_RETEN, BigDecimal.ZERO);
        mapTotais.put(TOTAL_IBS_MONO_RET, BigDecimal.ZERO);
        mapTotais.put(TOTAL_CBS_MONO_RET, BigDecimal.ZERO);
        mapTotais.put(TOTAL_IBS_EST_CRED, BigDecimal.ZERO);
        mapTotais.put(TOTAL_CBS_EST_CRED, BigDecimal.ZERO);
    }

    public TTribNFe montaImpostosDet(String cclassTrib, TNFe.InfNFe.Det det) throws NfeException {
        return montaImpostosDet(cclassTrib, det, null);
    }

    public TTribNFe montaImpostosDet(String cclassTrib, TNFe.InfNFe.Det det, String cclassTribRegular) throws NfeException {
        filtraCClasstrib(cclassTrib, cclassTribRegular);
        validaClassTrib(cclassTrib);
        calcularBaseCalculoIBSCBS(det);

        TTribNFe ibsCbs = new TTribNFe();
        ibsCbs.setCST(cstIbsCbs.getCst());
        ibsCbs.setCClassTrib(classTribIbsCbs.getCClassTrib());

        // choice: gIBSCBS | gIBSCBSMono | gTransfCred | gAjusteCompet
        if (Boolean.TRUE.equals(cstIbsCbs.getIndTransfCred())) {
            ibsCbs.setGTransfCred(montaTransfCred(det));
        } else if (Boolean.TRUE.equals(cstIbsCbs.getIndAjusteCompet())) {
            ibsCbs.setGAjusteCompet(montaAjusteCompet(det));
        } else if (Boolean.TRUE.equals(cstIbsCbs.getIndIBSCBSMono())) {
            ibsCbs.setGIBSCBSMono(montaGrupoMono(det));
        } else if (deveMontarGrupoIBSCBS()) {
            ibsCbs.setGIBSCBS(montarGrupoIBSCBS());
        }

        // gEstornoCred — independente do choice acima
        if (Boolean.TRUE.equals(classTribIbsCbs.getIndEstornoCred())) {
            ibsCbs.setGEstornoCred(montaEstornoCred(det));
        }

        // choice: gCredPresOper | gCredPresIBSZFM
        if (Boolean.TRUE.equals(classTribIbsCbs.getIndCredPresOper())) {
            ibsCbs.setGCredPresOper(montaCredPresOper(det));
        } else if (Boolean.TRUE.equals(cstIbsCbs.getIndCredPresIBSZFM())) {
            ibsCbs.setGCredPresIBSZFM(montaCredPresIBSZFM(det));
        }

        return ibsCbs;
    }

    /**
     * Monta o grupo de monofasia (TMonofasia).
     *
     * IBS e CBS tem grupos proprios, cada um em uma de duas modalidades mutuamente
     * exclusivas: ad rem (valor por unidade) ou ad valorem (percentual sobre a base).
     * Ver {@link #setMonofasiaAdValorem(boolean)}.
     *
     * As aliquotas monofasicas sao proprias por produto e nao sao as do regime regular,
     * por isso tem setters dedicados: {@link #setAdRemIBS(BigDecimal)} e
     * {@link #setAdRemCBS(BigDecimal)} para ad rem; {@link #setPAliqMonoUF(BigDecimal)},
     * {@link #setPAliqMonoMun(BigDecimal)} e {@link #setPAliqMonoCBS(BigDecimal)} para
     * ad valorem. Sem elas o grupo sai zerado, que e o comportamento seguro.
     *
     * O grupo gMonoDif (diferimento de monofasia) foi removido do XSD, portanto
     * {@code classTribIbsCbs.getMonofasiaDiferimento()} nao tem mais grupo correspondente.
     * O gpBioDiferenca que surgiu no lugar modela diferenca de biocombustivel, nao
     * diferimento, e por isso nao e preenchido automaticamente aqui.
     */
    private TMonofasia montaGrupoMono(TNFe.InfNFe.Det det) {
        TMonofasia gMono = new TMonofasia();
        BigDecimal qCom = ObjetoUtil.getBigDecimalOrZero(det.getProd().getQCom());
        BigDecimal qReten = qBCMonoReten != null ? qBCMonoReten : qCom;

        BigDecimal totIBS;
        BigDecimal totCBS;

        if (monofasiaAdValorem) {
            gMono.setGIBSMonoAdValorem(montaIbsMonoAdValorem(baseCalculo));
            gMono.setGCBSMonoAdValorem(montaCbsMonoAdValorem(baseCalculo));
            totIBS = totalIbsMonoAdValorem(baseCalculo);
            totCBS = totalCbsMonoAdValorem(baseCalculo);
        } else {
            gMono.setGIBSMonoAdRem(montaIbsMonoAdRem(qCom, qReten));
            gMono.setGCBSMonoAdRem(montaCbsMonoAdRem(qCom, qReten));
            totIBS = totalIbsMonoAdRem(qCom, qReten);
            totCBS = totalCbsMonoAdRem(qCom, qReten);
        }

        gMono.setVTotIBSMonoItem(ObjetoUtil.getValor2Casas(totIBS));
        gMono.setVTotCBSMonoItem(ObjetoUtil.getValor2Casas(totCBS));

        return gMono;
    }

    /** Valor ad rem: quantidade multiplicada pela aliquota por unidade de medida. */
    private BigDecimal valorAdRem(BigDecimal quantidade, BigDecimal aliquota) {
        return quantidade.multiply(ObjetoUtil.getOrZero(aliquota)).setScale(2, RoundingMode.HALF_UP);
    }

    /** Valor ad valorem: base multiplicada pela aliquota percentual. */
    private BigDecimal valorAdValorem(BigDecimal base, BigDecimal aliquotaPercentual) {
        return base.multiply(ObjetoUtil.getOrZero(aliquotaPercentual)
                .divide(CEM, SCALE_5, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal aliqRetenIBSAdRem() {
        return adRemIBSReten != null ? adRemIBSReten : adRemIBS;
    }

    private BigDecimal aliqRetenCBSAdRem() {
        return adRemCBSReten != null ? adRemCBSReten : adRemCBS;
    }

    private BigDecimal aliqRetenIBSAdValorem() {
        return pAliqMonoRetenIBS != null
                ? pAliqMonoRetenIBS
                : ObjetoUtil.getOrZero(pAliqMonoUF).add(ObjetoUtil.getOrZero(pAliqMonoMun));
    }

    private BigDecimal aliqRetenCBSAdValorem() {
        return pAliqMonoRetenCBS != null ? pAliqMonoRetenCBS : pAliqMonoCBS;
    }

    private boolean monoPadrao() {
        return Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaPadrao());
    }

    private boolean monoRetidaAnt() {
        return Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaRetidaAnt());
    }

    private boolean monoSujeitaRetencao() {
        return Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaSujeitaRetencao());
    }

    /**
     * Mistura de EAC com gasolina A fora do percentual obrigatório, que aciona o
     * gpBioDiferenca. Vem do cClassTrib, igual às demais flags de monofasia.
     */
    private boolean monoDiferencaBio() {
        return Boolean.TRUE.equals(classTribIbsCbs.getIndPercentualDiferencaBioCombustivel());
    }

    /** Diferença de biocombustível do IBS: valor informado ou, em ad rem, qBCBioComb x adRemIBS. */
    private BigDecimal valorDiferencaBioIBS() {
        if (vIBSDiferenca != null) {
            return vIBSDiferenca;
        }
        return monofasiaAdValorem ? BigDecimal.ZERO : valorAdRem(ObjetoUtil.getOrZero(qBCBioComb), adRemIBS);
    }

    /** Diferença de biocombustível da CBS: valor informado ou, em ad rem, qBCBioComb x adRemCBS. */
    private BigDecimal valorDiferencaBioCBS() {
        if (vCBSDiferenca != null) {
            return vCBSDiferenca;
        }
        return monofasiaAdValorem ? BigDecimal.ZERO : valorAdRem(ObjetoUtil.getOrZero(qBCBioComb), adRemCBS);
    }

    private TMonofasia.GIBSMonoAdRem montaIbsMonoAdRem(BigDecimal qCom, BigDecimal qReten) {
        TMonofasia.GIBSMonoAdRem grupo = new TMonofasia.GIBSMonoAdRem();

        if (monoPadrao()) {
            TMonofasia.GIBSMonoAdRem.GMonoPadrao padrao = new TMonofasia.GIBSMonoAdRem.GMonoPadrao();
            padrao.setQBCMono(ObjetoUtil.getValor4Casas(qCom));
            padrao.setAdRemIBS(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(adRemIBS)));
            padrao.setVIBSMono(ObjetoUtil.getValor2Casas(valorAdRem(qCom, adRemIBS)));
            grupo.setGMonoPadrao(padrao);
        }

        if (monoRetidaAnt()) {
            TMonofasia.GIBSMonoAdRem.GMonoRet ret = new TMonofasia.GIBSMonoAdRem.GMonoRet();
            ret.setVIBSMonoRet(ObjetoUtil.getValor2Casas(ObjetoUtil.getOrZero(vIBSMonoRet)));
            grupo.setGMonoRet(ret);
        }

        if (monoSujeitaRetencao()) {
            TMonofasia.GIBSMonoAdRem.GMonoReten reten = new TMonofasia.GIBSMonoAdRem.GMonoReten();
            reten.setQBCMonoReten(ObjetoUtil.getValor4Casas(qReten));
            reten.setAdRemIBSReten(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(aliqRetenIBSAdRem())));
            reten.setVIBSMonoReten(ObjetoUtil.getValor2Casas(valorAdRem(qReten, aliqRetenIBSAdRem())));
            grupo.setGMonoReten(reten);
        }

        if (monoDiferencaBio()) {
            TMonofasia.GIBSMonoAdRem.GpBioDiferenca bio = new TMonofasia.GIBSMonoAdRem.GpBioDiferenca();
            bio.setQBCBioComb(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(qBCBioComb)));
            bio.setVIBSDiferenca(ObjetoUtil.getValor2Casas(valorDiferencaBioIBS()));
            grupo.setGpBioDiferenca(bio);
        }

        return temAlgumSubgrupo() ? grupo : null;
    }

    private TMonofasia.GCBSMonoAdRem montaCbsMonoAdRem(BigDecimal qCom, BigDecimal qReten) {
        TMonofasia.GCBSMonoAdRem grupo = new TMonofasia.GCBSMonoAdRem();

        if (monoPadrao()) {
            TMonofasia.GCBSMonoAdRem.GMonoPadrao padrao = new TMonofasia.GCBSMonoAdRem.GMonoPadrao();
            padrao.setQBCMono(ObjetoUtil.getValor4Casas(qCom));
            padrao.setAdRemCBS(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(adRemCBS)));
            padrao.setVCBSMono(ObjetoUtil.getValor2Casas(valorAdRem(qCom, adRemCBS)));
            grupo.setGMonoPadrao(padrao);
        }

        if (monoRetidaAnt()) {
            TMonofasia.GCBSMonoAdRem.GMonoRet ret = new TMonofasia.GCBSMonoAdRem.GMonoRet();
            ret.setVCBSMonoRet(ObjetoUtil.getValor2Casas(ObjetoUtil.getOrZero(vCBSMonoRet)));
            grupo.setGMonoRet(ret);
        }

        if (monoSujeitaRetencao()) {
            TMonofasia.GCBSMonoAdRem.GMonoReten reten = new TMonofasia.GCBSMonoAdRem.GMonoReten();
            reten.setQBCMonoReten(ObjetoUtil.getValor4Casas(qReten));
            reten.setAdRemCBSReten(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(aliqRetenCBSAdRem())));
            reten.setVCBSMonoReten(ObjetoUtil.getValor2Casas(valorAdRem(qReten, aliqRetenCBSAdRem())));
            grupo.setGMonoReten(reten);
        }

        if (monoDiferencaBio()) {
            TMonofasia.GCBSMonoAdRem.GpBioDiferenca bio = new TMonofasia.GCBSMonoAdRem.GpBioDiferenca();
            bio.setQBCBioComb(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(qBCBioComb)));
            bio.setVCBSDiferenca(ObjetoUtil.getValor2Casas(valorDiferencaBioCBS()));
            grupo.setGpBioDiferenca(bio);
        }

        return temAlgumSubgrupo() ? grupo : null;
    }

    private TMonofasia.GIBSMonoAdValorem montaIbsMonoAdValorem(BigDecimal baseReten) {
        TMonofasia.GIBSMonoAdValorem grupo = new TMonofasia.GIBSMonoAdValorem();

        if (monoPadrao()) {
            TMonofasia.GIBSMonoAdValorem.GMonoPadrao padrao = new TMonofasia.GIBSMonoAdValorem.GMonoPadrao();
            BigDecimal vUf = valorAdValorem(baseCalculo, pAliqMonoUF);
            BigDecimal vMun = valorAdValorem(baseCalculo, pAliqMonoMun);
            padrao.setVBCMono(ObjetoUtil.getValor2Casas(baseCalculo));
            padrao.setPAliqMonoUF(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(pAliqMonoUF)));
            padrao.setVIBSMonoUF(ObjetoUtil.getValor2Casas(vUf));
            padrao.setPAliqMonoMun(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(pAliqMonoMun)));
            padrao.setVIBSMonoMun(ObjetoUtil.getValor2Casas(vMun));
            padrao.setVIBSMono(ObjetoUtil.getValor2Casas(vUf.add(vMun)));
            grupo.setGMonoPadrao(padrao);
        }

        if (monoRetidaAnt()) {
            TMonofasia.GIBSMonoAdValorem.GMonoRet ret = new TMonofasia.GIBSMonoAdValorem.GMonoRet();
            ret.setVIBSMonoRet(ObjetoUtil.getValor2Casas(ObjetoUtil.getOrZero(vIBSMonoRet)));
            grupo.setGMonoRet(ret);
        }

        if (monoSujeitaRetencao()) {
            TMonofasia.GIBSMonoAdValorem.GMonoReten reten = new TMonofasia.GIBSMonoAdValorem.GMonoReten();
            reten.setVBCMonoReten(ObjetoUtil.getValor2Casas(baseReten));
            reten.setPAliqMonoReten(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(aliqRetenIBSAdValorem())));
            reten.setVIBSMonoReten(ObjetoUtil.getValor2Casas(valorAdValorem(baseReten, aliqRetenIBSAdValorem())));
            grupo.setGMonoReten(reten);
        }

        if (monoDiferencaBio()) {
            TMonofasia.GIBSMonoAdValorem.GpBioDiferenca bio = new TMonofasia.GIBSMonoAdValorem.GpBioDiferenca();
            bio.setQBCBioComb(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(qBCBioComb)));
            bio.setVIBSDiferenca(ObjetoUtil.getValor2Casas(valorDiferencaBioIBS()));
            grupo.setGpBioDiferenca(bio);
        }

        return temAlgumSubgrupo() ? grupo : null;
    }

    private TMonofasia.GCBSMonoAdValorem montaCbsMonoAdValorem(BigDecimal baseReten) {
        TMonofasia.GCBSMonoAdValorem grupo = new TMonofasia.GCBSMonoAdValorem();

        if (monoPadrao()) {
            TMonofasia.GCBSMonoAdValorem.GMonoPadrao padrao = new TMonofasia.GCBSMonoAdValorem.GMonoPadrao();
            padrao.setVBCMono(ObjetoUtil.getValor2Casas(baseCalculo));
            padrao.setPAliqMonoCBS(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(pAliqMonoCBS)));
            padrao.setVCBSMono(ObjetoUtil.getValor2Casas(valorAdValorem(baseCalculo, pAliqMonoCBS)));
            grupo.setGMonoPadrao(padrao);
        }

        if (monoRetidaAnt()) {
            TMonofasia.GCBSMonoAdValorem.GMonoRet ret = new TMonofasia.GCBSMonoAdValorem.GMonoRet();
            ret.setVCBSMonoRet(ObjetoUtil.getValor2Casas(ObjetoUtil.getOrZero(vCBSMonoRet)));
            grupo.setGMonoRet(ret);
        }

        if (monoSujeitaRetencao()) {
            TMonofasia.GCBSMonoAdValorem.GMonoReten reten = new TMonofasia.GCBSMonoAdValorem.GMonoReten();
            reten.setVBCMonoReten(ObjetoUtil.getValor2Casas(baseReten));
            reten.setPAliqMonoReten(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(aliqRetenCBSAdValorem())));
            reten.setVCBSMonoReten(ObjetoUtil.getValor2Casas(valorAdValorem(baseReten, aliqRetenCBSAdValorem())));
            grupo.setGMonoReten(reten);
        }

        if (monoDiferencaBio()) {
            TMonofasia.GCBSMonoAdValorem.GpBioDiferenca bio = new TMonofasia.GCBSMonoAdValorem.GpBioDiferenca();
            bio.setQBCBioComb(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(qBCBioComb)));
            bio.setVCBSDiferenca(ObjetoUtil.getValor2Casas(valorDiferencaBioCBS()));
            grupo.setGpBioDiferenca(bio);
        }

        return temAlgumSubgrupo() ? grupo : null;
    }

    /** Evita emitir wrapper de modalidade vazio quando nenhuma flag do cClassTrib esta ativa. */
    private boolean temAlgumSubgrupo() {
        return monoPadrao() || monoRetidaAnt() || monoSujeitaRetencao() || monoDiferencaBio();
    }

    /**
     * Acumula os tres componentes nos totais da nota e devolve o total do item.
     * Os seis totais alimentam o gMono de {@link #preencheTotaisIbsCsb()}.
     */
    private BigDecimal acumulaMono(String chavePadrao, BigDecimal vPadrao,
                                   String chaveReten, BigDecimal vReten,
                                   String chaveRet, BigDecimal vRet) {
        mapTotais.merge(chavePadrao, vPadrao, BigDecimal::add);
        mapTotais.merge(chaveReten, vReten, BigDecimal::add);
        mapTotais.merge(chaveRet, vRet, BigDecimal::add);
        return vPadrao.add(vReten).add(vRet);
    }

    private BigDecimal totalIbsMonoAdRem(BigDecimal qCom, BigDecimal qReten) {
        return acumulaMono(
                TOTAL_IBS_MONO, monoPadrao() ? valorAdRem(qCom, adRemIBS) : BigDecimal.ZERO,
                TOTAL_IBS_MONO_RETEN, monoSujeitaRetencao()
                        ? valorAdRem(qReten, aliqRetenIBSAdRem()) : BigDecimal.ZERO,
                TOTAL_IBS_MONO_RET, monoRetidaAnt()
                        ? ObjetoUtil.getOrZero(vIBSMonoRet) : BigDecimal.ZERO);
    }

    private BigDecimal totalCbsMonoAdRem(BigDecimal qCom, BigDecimal qReten) {
        return acumulaMono(
                TOTAL_CBS_MONO, monoPadrao() ? valorAdRem(qCom, adRemCBS) : BigDecimal.ZERO,
                TOTAL_CBS_MONO_RETEN, monoSujeitaRetencao()
                        ? valorAdRem(qReten, aliqRetenCBSAdRem()) : BigDecimal.ZERO,
                TOTAL_CBS_MONO_RET, monoRetidaAnt()
                        ? ObjetoUtil.getOrZero(vCBSMonoRet) : BigDecimal.ZERO);
    }

    private BigDecimal totalIbsMonoAdValorem(BigDecimal baseReten) {
        BigDecimal padrao = monoPadrao()
                ? valorAdValorem(baseCalculo, pAliqMonoUF).add(valorAdValorem(baseCalculo, pAliqMonoMun))
                : BigDecimal.ZERO;
        return acumulaMono(
                TOTAL_IBS_MONO, padrao,
                TOTAL_IBS_MONO_RETEN, monoSujeitaRetencao()
                        ? valorAdValorem(baseReten, aliqRetenIBSAdValorem()) : BigDecimal.ZERO,
                TOTAL_IBS_MONO_RET, monoRetidaAnt()
                        ? ObjetoUtil.getOrZero(vIBSMonoRet) : BigDecimal.ZERO);
    }

    private BigDecimal totalCbsMonoAdValorem(BigDecimal baseReten) {
        return acumulaMono(
                TOTAL_CBS_MONO, monoPadrao()
                        ? valorAdValorem(baseCalculo, pAliqMonoCBS) : BigDecimal.ZERO,
                TOTAL_CBS_MONO_RETEN, monoSujeitaRetencao()
                        ? valorAdValorem(baseReten, aliqRetenCBSAdValorem()) : BigDecimal.ZERO,
                TOTAL_CBS_MONO_RET, monoRetidaAnt()
                        ? ObjetoUtil.getOrZero(vCBSMonoRet) : BigDecimal.ZERO);
    }

    private boolean deveMontarGrupoIBSCBS() {
        return Boolean.TRUE.equals(cstIbsCbs.getIndIBSCBS())
               || Boolean.TRUE.equals(cstIbsCbs.getIndRedAliq())
               || Boolean.TRUE.equals(cstIbsCbs.getIndDif());
    }

    private TCIBSNFe montarGrupoIBSCBS() {
        TCIBSNFe gIBSCBS = new TCIBSNFe();
        gIBSCBS.setVBC(ObjetoUtil.getValor2Casas(baseCalculo));

        TCIBSNFe.GIBSUF gIBSUF = criarGIBSUF();
        TCIBSNFe.GIBSMun gIBSMun = criarGIBSMun();
        TCIBSNFe.GCBS gCBS = criarGCBS();

        gIBSCBS.setGIBSUF(gIBSUF);
        gIBSCBS.setGIBSMun(gIBSMun);
        gIBSCBS.setGCBS(gCBS);
        gIBSCBS.setVIBS(ObjetoUtil.getValor2Casas(
                new BigDecimal(gIBSUF.getVIBSUF()).add(new BigDecimal(gIBSMun.getVIBSMun()))));

        if (Boolean.TRUE.equals(classTribIbsCbs.getIndTribRegular())) {
            gIBSCBS.setGTribRegular(criarGTribRegular());
        }

        if (Boolean.TRUE.equals(classTribIbsCbs.getIndCompraGov())) {
            gIBSCBS.setGTribCompraGov(criarGTribCompraGov());
        }

        atualizarTotais(gIBSUF, gIBSMun, gCBS);
        return gIBSCBS;
    }

    private void atualizarTotais(TCIBSNFe.GIBSUF gIBSUF, TCIBSNFe.GIBSMun gIBSMun, TCIBSNFe.GCBS gCBS) {
        mapTotais.merge(TOTAL_BC_IBS_CBS, baseCalculo, BigDecimal::add);
        mapTotais.merge(TOTAL_IBS_UF, new BigDecimal(gIBSUF.getVIBSUF()), BigDecimal::add);
        mapTotais.merge(TOTAL_IBS_MUN, new BigDecimal(gIBSMun.getVIBSMun()), BigDecimal::add);
        mapTotais.merge(TOTAL_CBS, new BigDecimal(gCBS.getVCBS()), BigDecimal::add);
        if (gIBSUF.getGDif() != null) {
            mapTotais.merge(TOTAL_DIFERIMENTO_IBS_UF, new BigDecimal(gIBSUF.getGDif().getVDif()), BigDecimal::add);
        }
        if (gIBSMun.getGDif() != null) {
            mapTotais.merge(TOTAL_DIFERIMENTO_IBS_MUN, new BigDecimal(gIBSMun.getGDif().getVDif()), BigDecimal::add);
        }
        if (gCBS.getGDif() != null) {
            mapTotais.merge(TOTAL_DIFERIMENTO_CBS, new BigDecimal(gCBS.getGDif().getVDif()), BigDecimal::add);
        }
    }

    private void filtraCClasstrib(String cclassTrib, String cclassTribRegular) {
        Optional<AbstractMap.SimpleEntry<CstDTO, ClassificacaoTributariaDTO>> achado =
                buscarCstEClassificacao(cclassTrib);
        cstIbsCbs = achado.map(AbstractMap.SimpleEntry::getKey).orElse(null);
        classTribIbsCbs = achado.map(AbstractMap.SimpleEntry::getValue).orElse(null);

        cstIbsCbsTribRegular = null;
        classTribIbsCbsTribRegular = null;
        if (cclassTribRegular != null && !cclassTribRegular.isEmpty()) {
            Optional<AbstractMap.SimpleEntry<CstDTO, ClassificacaoTributariaDTO>> achadoRegular =
                    buscarCstEClassificacao(cclassTribRegular);
            cstIbsCbsTribRegular = achadoRegular.map(AbstractMap.SimpleEntry::getKey).orElse(null);
            classTribIbsCbsTribRegular = achadoRegular.map(AbstractMap.SimpleEntry::getValue).orElse(null);
        }
    }

    private Optional<AbstractMap.SimpleEntry<CstDTO, ClassificacaoTributariaDTO>> buscarCstEClassificacao(String cclassTrib) {
        return listaCstIbsCbs.stream()
                .flatMap(cst -> cst.getClassificacoesTributarias().stream()
                        .map(classTrib -> new AbstractMap.SimpleEntry<>(cst, classTrib)))
                .filter(entry -> entry.getValue().getCClassTrib().equals(cclassTrib))
                .findFirst();
    }

    private void validaClassTrib(String cclassTrib) throws NfeException {
        if (classTribIbsCbs == null) {
            throw new NfeException("CClassTrib inválido ou não encontrado: " + cclassTrib);
        }

        if (documento.equals(DocumentoEnum.NFE) && Boolean.FALSE.equals(classTribIbsCbs.getIndNFe())) {
            throw new NfeException("CClassTrib não pode ser utilizado para NFe: " + cclassTrib);
        }

        if (documento.equals(DocumentoEnum.NFCE) && Boolean.FALSE.equals(classTribIbsCbs.getIndNFCe())) {
            throw new NfeException("CClassTrib não pode ser utilizado para NFCe: " + cclassTrib);
        }

        if (Boolean.TRUE.equals(classTribIbsCbs.getIndTribRegular()) && classTribIbsCbsTribRegular == null) {
            throw new NfeException("Obrigatório informar Tributação Regular para CClassTrib: " + cclassTrib);
        }
    }

    private TCIBSNFe.GIBSUF criarGIBSUF() {
        return criarGrupoImposto(
                pAliqIbsUf,
                pAliqDiferimento,
                classTribIbsCbs.getPRedIBS(),
                TCIBSNFe.GIBSUF::new,
                TCIBSNFe.GIBSUF::setPIBSUF,
                TCIBSNFe.GIBSUF::setGDif,
                TCIBSNFe.GIBSUF::setGRed,
                TCIBSNFe.GIBSUF::setVIBSUF
        );
    }

    private TCIBSNFe.GIBSMun criarGIBSMun() {
        return criarGrupoImposto(
                pAliqIbsMun,
                pAliqDiferimento,
                classTribIbsCbs.getPRedIBS(),
                TCIBSNFe.GIBSMun::new,
                TCIBSNFe.GIBSMun::setPIBSMun,
                TCIBSNFe.GIBSMun::setGDif,
                TCIBSNFe.GIBSMun::setGRed,
                TCIBSNFe.GIBSMun::setVIBSMun
        );
    }

    private TCIBSNFe.GCBS criarGCBS() {
        return criarGrupoImposto(
                pAliqCbs,
                pAliqDiferimento,
                classTribIbsCbs.getPRedCBS(),
                TCIBSNFe.GCBS::new,
                TCIBSNFe.GCBS::setPCBS,
                TCIBSNFe.GCBS::setGDif,
                TCIBSNFe.GCBS::setGRed,
                TCIBSNFe.GCBS::setVCBS
        );
    }

    @FunctionalInterface
    private interface GrupoImpostoFactory<T> {
        T create();
    }

    @FunctionalInterface
    private interface AliquotaSetter<T> {
        void set(T grupo, String valor);
    }

    @FunctionalInterface
    private interface RedutorSetter<T> {
        void set(T grupo, TRed redutor);
    }

    @FunctionalInterface
    private interface DifererimentoSetter<T> {
        void set(T grupo, TDif diferimento);
    }

    @FunctionalInterface
    private interface ValorSetter<T> {
        void set(T grupo, String valor);
    }

    private <T> T criarGrupoImposto(
            BigDecimal aliqPadrao,
            BigDecimal percentualDiferimento,
            BigDecimal percentualReducao,
            GrupoImpostoFactory<T> factory,
            AliquotaSetter<T> aliqSetter,
            DifererimentoSetter<T> difSetter,
            RedutorSetter<T> redSetter,
            ValorSetter<T> valorSetter) {

        T grupo = factory.create();
        BigDecimal aliq = Boolean.TRUE.equals(classTribIbsCbs.getIndTribRegular())
                ? BigDecimal.ZERO
                : ObjetoUtil.getOrZero(aliqPadrao);

        aliqSetter.set(grupo, ObjetoUtil.getValor4Casas(aliq));

        BigDecimal aliqEfet = aliq;

        if (Boolean.TRUE.equals(cstIbsCbs.getIndRedAliq())) {
            BigDecimal percentRed = ObjetoUtil.getOrZero(percentualReducao);
            TRed gRed = criarRedutor(percentRed, aliq);
            redSetter.set(grupo, gRed);
            aliqEfet = new BigDecimal(gRed.getPAliqEfet());
        }

        BigDecimal valor = calcularValorImposto(aliqEfet);

        if (Boolean.TRUE.equals(cstIbsCbs.getIndDif())) {
            BigDecimal percentDif = ObjetoUtil.getOrZero(percentualDiferimento);
            TDif gDif = criarDiferimento(percentDif, aliqEfet);
            difSetter.set(grupo, gDif);
            valor = valor.subtract(new BigDecimal(gDif.getVDif()));
        }

        valorSetter.set(grupo, ObjetoUtil.getValor2Casas(valor));

        return grupo;
    }

    private BigDecimal calcularValorImposto(BigDecimal aliquota) {
        return baseCalculo.multiply(aliquota.divide(CEM, SCALE_5, RoundingMode.HALF_UP));
    }

    private TTribRegular criarGTribRegular() {
        TTribRegular gTribRegular = new TTribRegular();
        gTribRegular.setCSTReg(cstIbsCbsTribRegular.getCst());
        gTribRegular.setCClassTribReg(classTribIbsCbsTribRegular.getCClassTrib());

        configurarTributoRegular(gTribRegular, pAliqIbsUf,
                TTribRegular::setPAliqEfetRegIBSUF, TTribRegular::setVTribRegIBSUF);
        configurarTributoRegular(gTribRegular, pAliqIbsMun,
                TTribRegular::setPAliqEfetRegIBSMun, TTribRegular::setVTribRegIBSMun);
        configurarTributoRegular(gTribRegular, pAliqCbs,
                TTribRegular::setPAliqEfetRegCBS, TTribRegular::setVTribRegCBS);

        return gTribRegular;
    }

    private void configurarTributoRegular(
            TTribRegular gTribRegular,
            BigDecimal aliquota,
            java.util.function.BiConsumer<TTribRegular, String> aliqSetter,
            java.util.function.BiConsumer<TTribRegular, String> valorSetter) {

        BigDecimal pAliqEfet = ObjetoUtil.getOrZero(aliquota);
        aliqSetter.accept(gTribRegular, ObjetoUtil.getValor4Casas(pAliqEfet));

        BigDecimal valor = calcularValorImposto(pAliqEfet);
        valorSetter.accept(gTribRegular, ObjetoUtil.getValor2Casas(valor));
    }

    private static TRed criarRedutor(BigDecimal percentualReducao, BigDecimal aliqOriginal) {
        TRed gRed = new TRed();
        gRed.setPRedAliq(ObjetoUtil.getValor4Casas(percentualReducao));

        BigDecimal aliqEfet = aliqOriginal.multiply(
                BigDecimal.ONE.subtract(percentualReducao.divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP)));
        gRed.setPAliqEfet(ObjetoUtil.getValor4Casas(aliqEfet));

        return gRed;
    }

    private TDif criarDiferimento(BigDecimal percentualDiferimento, BigDecimal aliqEfet) {
        TDif gDif = new TDif();
        gDif.setPDif(ObjetoUtil.getValor4Casas(percentualDiferimento));
        BigDecimal valorDif = calcularValorImposto(percentualDiferimento).multiply(aliqEfet.divide(CEM, SCALE_5, RoundingMode.HALF_UP));
        gDif.setVDif(ObjetoUtil.getValor2Casas(valorDif));
        return gDif;
    }

    @SuppressWarnings("unchecked")
    private void calcularBaseCalculoIBSCBS(TNFe.InfNFe.Det det) {
        BigDecimal vProd = ObjetoUtil.getBigDecimalOrZero(det.getProd().getVProd());
        BigDecimal vFrete = ObjetoUtil.getBigDecimalOrZero(det.getProd().getVFrete());
        BigDecimal vSeg = ObjetoUtil.getBigDecimalOrZero(det.getProd().getVSeg());
        BigDecimal vOutro = ObjetoUtil.getBigDecimalOrZero(det.getProd().getVOutro());
        BigDecimal vDesc = ObjetoUtil.getBigDecimalOrZero(det.getProd().getVDesc());

        List<JAXBElement<?>> impostos = det.getImposto().getContent();

        baseCalculo = vProd
                .add(vFrete)
                .add(vSeg)
                .add(vOutro)
                .subtract(vDesc)
                .add(XmlImpostoUtil.getVII(impostos))
                .add(XmlImpostoUtil.getVIS(impostos))
                .subtract(XmlImpostoUtil.getVPIS(impostos))
                .subtract(XmlImpostoUtil.getVCOFINS(impostos))
                .subtract(XmlImpostoUtil.getVICMS(impostos))
                .subtract(XmlImpostoUtil.getVICMSUFDest(impostos))
                .subtract(XmlImpostoUtil.getVFCP(impostos))
                .subtract(XmlImpostoUtil.getVFCPUFDest(impostos))
                .subtract(XmlImpostoUtil.getVICMSMono(impostos))
                .subtract(XmlImpostoUtil.getVISSQN(impostos));
    }

    public BigDecimal calculaVnfTot(String vnf) {
        return new BigDecimal(vnf)
                .add(mapTotais.getOrDefault(TOTAL_IBS_UF, BigDecimal.ZERO))
                .add(mapTotais.getOrDefault(TOTAL_IBS_MUN, BigDecimal.ZERO))
                .add(mapTotais.getOrDefault(TOTAL_CBS, BigDecimal.ZERO));
    }

    public TIBSCBSMonoTot preencheTotaisIbsCsb() {
        TIBSCBSMonoTot totalIbsCbs = new TIBSCBSMonoTot();
        totalIbsCbs.setVBCIBSCBS(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_BC_IBS_CBS, BigDecimal.ZERO)));
        totalIbsCbs.setGIBS(criarTotaisIBS());
        totalIbsCbs.setGCBS(criarTotaisCBS());

        BigDecimal ibsMono = mapTotais.getOrDefault(TOTAL_IBS_MONO, BigDecimal.ZERO);
        BigDecimal cbsMono = mapTotais.getOrDefault(TOTAL_CBS_MONO, BigDecimal.ZERO);
        BigDecimal ibsMonoReten = mapTotais.getOrDefault(TOTAL_IBS_MONO_RETEN, BigDecimal.ZERO);
        BigDecimal cbsMonoReten = mapTotais.getOrDefault(TOTAL_CBS_MONO_RETEN, BigDecimal.ZERO);
        BigDecimal ibsMonoRet = mapTotais.getOrDefault(TOTAL_IBS_MONO_RET, BigDecimal.ZERO);
        BigDecimal cbsMonoRet = mapTotais.getOrDefault(TOTAL_CBS_MONO_RET, BigDecimal.ZERO);

        boolean temMono = ibsMono.compareTo(BigDecimal.ZERO) != 0
                          || cbsMono.compareTo(BigDecimal.ZERO) != 0
                          || ibsMonoReten.compareTo(BigDecimal.ZERO) != 0
                          || cbsMonoReten.compareTo(BigDecimal.ZERO) != 0
                          || ibsMonoRet.compareTo(BigDecimal.ZERO) != 0
                          || cbsMonoRet.compareTo(BigDecimal.ZERO) != 0;

        if (temMono) {
            TIBSCBSMonoTot.GMono gMono = new TIBSCBSMonoTot.GMono();
            gMono.setVIBSMono(ObjetoUtil.getValor2Casas(ibsMono));
            gMono.setVCBSMono(ObjetoUtil.getValor2Casas(cbsMono));
            gMono.setVIBSMonoReten(ObjetoUtil.getValor2Casas(ibsMonoReten));
            gMono.setVCBSMonoReten(ObjetoUtil.getValor2Casas(cbsMonoReten));
            gMono.setVIBSMonoRet(ObjetoUtil.getValor2Casas(ibsMonoRet));
            gMono.setVCBSMonoRet(ObjetoUtil.getValor2Casas(cbsMonoRet));
            totalIbsCbs.setGMono(gMono);
        }

        BigDecimal ibsEstCred = mapTotais.getOrDefault(TOTAL_IBS_EST_CRED, BigDecimal.ZERO);
        BigDecimal cbsEstCred = mapTotais.getOrDefault(TOTAL_CBS_EST_CRED, BigDecimal.ZERO);

        if (ibsEstCred.compareTo(BigDecimal.ZERO) != 0 || cbsEstCred.compareTo(BigDecimal.ZERO) != 0) {
            TIBSCBSMonoTot.GEstornoCred gEstorno = new TIBSCBSMonoTot.GEstornoCred();
            gEstorno.setVIBSEstCred(ObjetoUtil.getValor2Casas(ibsEstCred));
            gEstorno.setVCBSEstCred(ObjetoUtil.getValor2Casas(cbsEstCred));
            totalIbsCbs.setGEstornoCred(gEstorno);
        }

        return totalIbsCbs;
    }

    private TIBSCBSMonoTot.GIBS criarTotaisIBS() {
        TIBSCBSMonoTot.GIBS gIbs = new TIBSCBSMonoTot.GIBS();
        gIbs.setGIBSUF(criarGIBSUFTotal());
        gIbs.setGIBSMun(criarGIBSMunTotal());

        BigDecimal totalIBS = mapTotais.getOrDefault(TOTAL_IBS_UF, BigDecimal.ZERO)
                .add(mapTotais.getOrDefault(TOTAL_IBS_MUN, BigDecimal.ZERO));
        gIbs.setVIBS(ObjetoUtil.getValor2Casas(totalIBS));
        gIbs.setVCredPres("0.00");
        gIbs.setVCredPresCondSus("0.00");

        return gIbs;
    }

    private TIBSCBSMonoTot.GIBS.GIBSUF criarGIBSUFTotal() {
        TIBSCBSMonoTot.GIBS.GIBSUF gIbsUF = new TIBSCBSMonoTot.GIBS.GIBSUF();
        gIbsUF.setVDif(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_DIFERIMENTO_IBS_UF, BigDecimal.ZERO)));
        gIbsUF.setVDevTrib("0.00");
        gIbsUF.setVIBSUF(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_IBS_UF, BigDecimal.ZERO)));
        return gIbsUF;
    }

    private TIBSCBSMonoTot.GIBS.GIBSMun criarGIBSMunTotal() {
        TIBSCBSMonoTot.GIBS.GIBSMun gIbsMun = new TIBSCBSMonoTot.GIBS.GIBSMun();
        gIbsMun.setVDif(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_DIFERIMENTO_IBS_MUN, BigDecimal.ZERO)));
        gIbsMun.setVDevTrib("0.00");
        gIbsMun.setVIBSMun(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_IBS_MUN, BigDecimal.ZERO)));
        return gIbsMun;
    }

    private TIBSCBSMonoTot.GCBS criarTotaisCBS() {
        TIBSCBSMonoTot.GCBS gCbs = new TIBSCBSMonoTot.GCBS();
        gCbs.setVDif(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_DIFERIMENTO_CBS, BigDecimal.ZERO)));
        gCbs.setVDevTrib("0.00");
        gCbs.setVCBS(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_CBS, BigDecimal.ZERO)));
        gCbs.setVCredPres("0.00");
        gCbs.setVCredPresCondSus("0.00");
        return gCbs;
    }

    private TTransfCred montaTransfCred(TNFe.InfNFe.Det det) {
        TTransfCred transfCred = new TTransfCred();
        BigDecimal ibsUf = calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsUf))
                .add(calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsMun)));
        BigDecimal cbs = calcularValorImposto(ObjetoUtil.getOrZero(pAliqCbs));
        transfCred.setVIBS(ObjetoUtil.getValor2Casas(ibsUf));
        transfCred.setVCBS(ObjetoUtil.getValor2Casas(cbs));
        return transfCred;
    }

    private TAjusteCompet montaAjusteCompet(TNFe.InfNFe.Det det) {
        TAjusteCompet ajusteCompet = new TAjusteCompet();
        ajusteCompet.setCompetApur(competApur);
        BigDecimal ibsTotal = calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsUf))
                .add(calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsMun)));
        BigDecimal cbs = calcularValorImposto(ObjetoUtil.getOrZero(pAliqCbs));
        ajusteCompet.setVIBS(ObjetoUtil.getValor2Casas(ibsTotal));
        ajusteCompet.setVCBS(ObjetoUtil.getValor2Casas(cbs));
        return ajusteCompet;
    }

    private TEstornoCred montaEstornoCred(TNFe.InfNFe.Det det) {
        TEstornoCred estornoCred = new TEstornoCred();
        BigDecimal ibsTotal = calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsUf))
                .add(calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsMun)));
        BigDecimal cbs = calcularValorImposto(ObjetoUtil.getOrZero(pAliqCbs));
        estornoCred.setVIBSEstCred(ObjetoUtil.getValor2Casas(ibsTotal));
        estornoCred.setVCBSEstCred(ObjetoUtil.getValor2Casas(cbs));
        mapTotais.merge(TOTAL_IBS_EST_CRED, ibsTotal, BigDecimal::add);
        mapTotais.merge(TOTAL_CBS_EST_CRED, cbs, BigDecimal::add);
        return estornoCred;
    }

    private TCredPresOper montaCredPresOper(TNFe.InfNFe.Det det) {
        TCredPresOper credPresOper = new TCredPresOper();
        credPresOper.setVBCCredPres(ObjetoUtil.getValor2Casas(baseCalculo));
        credPresOper.setCCredPres(cCredPres);

        TCredPres ibsCredPres = new TCredPres();
        ibsCredPres.setPCredPres(pCredPresIBS);
        BigDecimal pCredPresIBSBD = ObjetoUtil.getBigDecimalOrZero(pCredPresIBS);
        BigDecimal vCredPresIBS = baseCalculo.multiply(
                pCredPresIBSBD.divide(CEM, SCALE_5, RoundingMode.HALF_UP));
        ibsCredPres.setVCredPres(ObjetoUtil.getValor2Casas(vCredPresIBS));
        credPresOper.setGIBSCredPres(ibsCredPres);

        TCredPres cbsCredPres = new TCredPres();
        cbsCredPres.setPCredPres(pCredPresCBS);
        BigDecimal pCredPresCBSBD = ObjetoUtil.getBigDecimalOrZero(pCredPresCBS);
        BigDecimal vCredPresCBS = baseCalculo.multiply(
                pCredPresCBSBD.divide(CEM, SCALE_5, RoundingMode.HALF_UP));
        cbsCredPres.setVCredPres(ObjetoUtil.getValor2Casas(vCredPresCBS));
        credPresOper.setGCBSCredPres(cbsCredPres);

        return credPresOper;
    }

    private TCredPresIBSZFM montaCredPresIBSZFM(TNFe.InfNFe.Det det) {
        TCredPresIBSZFM credPresIBSZFM = new TCredPresIBSZFM();
        credPresIBSZFM.setCompetApur(competApur);
        credPresIBSZFM.setTpCredPresIBSZFM(tpCredPresIBSZFM);
        credPresIBSZFM.setVCredPresIBSZFM(ObjetoUtil.getValor2Casas(baseCalculo));
        return credPresIBSZFM;
    }

    private TTribCompraGov criarGTribCompraGov() {
        TTribCompraGov tribCompraGov = new TTribCompraGov();
        tribCompraGov.setPAliqIBSUF(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(pAliqIbsUf)));
        tribCompraGov.setVTribIBSUF(ObjetoUtil.getValor2Casas(calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsUf))));
        tribCompraGov.setPAliqIBSMun(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(pAliqIbsMun)));
        tribCompraGov.setVTribIBSMun(ObjetoUtil.getValor2Casas(calcularValorImposto(ObjetoUtil.getOrZero(pAliqIbsMun))));
        tribCompraGov.setPAliqCBS(ObjetoUtil.getValor4Casas(ObjetoUtil.getOrZero(pAliqCbs)));
        tribCompraGov.setVTribCBS(ObjetoUtil.getValor2Casas(calcularValorImposto(ObjetoUtil.getOrZero(pAliqCbs))));
        return tribCompraGov;
    }
}
