package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.ConsultaTributacao;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dto.ClassificacaoTributariaDTO;
import br.com.swconsultoria.nfe.dto.CstDTO;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.*;
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
    private BigDecimal baseCalculo = BigDecimal.ZERO;

    public void setpAliqIbsUf(BigDecimal pAliqIbsUf) {
        this.pAliqIbsUf = pAliqIbsUf;
    }

    public void setpAliqIbsMun(BigDecimal pAliqIbsMun) {
        this.pAliqIbsMun = pAliqIbsMun;
    }

    public void setpAliqCbs(BigDecimal pAliqCbs) {
        this.pAliqCbs = pAliqCbs;
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

        if (deveMontarGrupoIBSCBS()) {
            ibsCbs.setGIBSCBS(montarGrupoIBSCBS());
        }

        if (Boolean.TRUE.equals(cstIbsCbs.getIndIBSCBSMono())) {
            ibsCbs.setGIBSCBSMono(montaGrupoMono(det));
        }

        return ibsCbs;
    }

    private TMonofasia montaGrupoMono(TNFe.InfNFe.Det det) {
        TMonofasia gMono = new TMonofasia();
        if(Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaPadrao())) {
            TMonofasia.GMonoPadrao monoPadrao = new TMonofasia.GMonoPadrao();
            monoPadrao.setQBCMono(ObjetoUtil.getValor4Casas(new BigDecimal(det.getProd().getQCom())));
            monoPadrao.setAdRemIBS("0.00");
            monoPadrao.setAdRemCBS("0.00");
            monoPadrao.setVIBSMono("0.00");
            monoPadrao.setVCBSMono("0.00");
            gMono.setGMonoPadrao(monoPadrao);
        }

        if(Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaRetidaAnt())) {
            TMonofasia.GMonoRet monoRet = new TMonofasia.GMonoRet();
            monoRet.setQBCMonoRet(ObjetoUtil.getValor4Casas(new BigDecimal(det.getProd().getQCom())));
            monoRet.setAdRemCBSRet("0.00");
            monoRet.setAdRemIBSRet("0.00");
            monoRet.setVCBSMonoRet("0.00");
            monoRet.setVIBSMonoRet("0.00");
            gMono.setGMonoRet(monoRet);
        }

        if(Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaSujeitaRetencao())) {
            TMonofasia.GMonoReten monoReten = new TMonofasia.GMonoReten();
            monoReten.setQBCMonoReten(ObjetoUtil.getValor4Casas(new BigDecimal(det.getProd().getQCom())));
            monoReten.setAdRemCBSReten("0.00");
            monoReten.setAdRemIBSReten("0.00");
            monoReten.setVCBSMonoReten("0.00");
            monoReten.setVIBSMonoReten("0.00");
            gMono.setGMonoReten(monoReten);
        }

        if(Boolean.TRUE.equals(classTribIbsCbs.getMonofasiaDiferimento())) {
            TMonofasia.GMonoDif gMonoDif = new TMonofasia.GMonoDif();
            gMonoDif.setPDifCBS("0.00");
            gMonoDif.setPDifIBS("0.00");
            gMonoDif.setVCBSMonoDif("0.00");
            gMonoDif.setVIBSMonoDif("0.00");
            gMono.setGMonoDif(gMonoDif);

        }
        gMono.setVTotCBSMonoItem("0.00");
        gMono.setVTotIBSMonoItem("0.00");

        return gMono;
    }

    private boolean deveMontarGrupoIBSCBS() {
        return Boolean.TRUE.equals(cstIbsCbs.getIndIBSCBS())
                || Boolean.TRUE.equals(cstIbsCbs.getIndRedAliq())
                || Boolean.TRUE.equals(cstIbsCbs.getIndDif())
                || Boolean.TRUE.equals(cstIbsCbs.getIndTransfCred());
    }

    private TCIBS montarGrupoIBSCBS() {
        TCIBS gIBSCBS = new TCIBS();
        gIBSCBS.setVBC(ObjetoUtil.getValor2Casas(baseCalculo));

        TCIBS.GIBSUF gIBSUF = criarGIBSUF();
        TCIBS.GIBSMun gIBSMun = criarGIBSMun();
        TCIBS.GCBS gCBS = criarGCBS();

        gIBSCBS.setGIBSUF(gIBSUF);
        gIBSCBS.setGIBSMun(gIBSMun);
        gIBSCBS.setGCBS(gCBS);
        gIBSCBS.setVIBS(ObjetoUtil.getValor2Casas(
                new BigDecimal(gIBSUF.getVIBSUF()).add(new BigDecimal(gIBSMun.getVIBSMun()))));

        if (Boolean.TRUE.equals(classTribIbsCbs.getIndTribRegular())) {
            gIBSCBS.setGTribRegular(criarGTribRegular());
        }

        atualizarTotais(gIBSUF, gIBSMun, gCBS);
        return gIBSCBS;
    }

    private void atualizarTotais(TCIBS.GIBSUF gIBSUF, TCIBS.GIBSMun gIBSMun, TCIBS.GCBS gCBS) {
        mapTotais.merge(TOTAL_BC_IBS_CBS, baseCalculo, BigDecimal::add);
        mapTotais.merge(TOTAL_IBS_UF, new BigDecimal(gIBSUF.getVIBSUF()), BigDecimal::add);
        mapTotais.merge(TOTAL_IBS_MUN, new BigDecimal(gIBSMun.getVIBSMun()), BigDecimal::add);
        mapTotais.merge(TOTAL_CBS, new BigDecimal(gCBS.getVCBS()), BigDecimal::add);
    }

    private void filtraCClasstrib(String cclassTrib, String cclassTribRegular) {
        buscarCstEClassificacao(cclassTrib).ifPresent(entry -> {
            cstIbsCbs = entry.getKey();
            classTribIbsCbs = entry.getValue();
        });

        if (cclassTribRegular != null && !cclassTribRegular.isEmpty()) {
            buscarCstEClassificacao(cclassTribRegular).ifPresent(entry -> {
                cstIbsCbsTribRegular = entry.getKey();
                classTribIbsCbsTribRegular = entry.getValue();
            });
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

    private TCIBS.GIBSUF criarGIBSUF() {
        return criarGrupoImposto(
                pAliqIbsUf,
                classTribIbsCbs.getPRedIBS(),
                TCIBS.GIBSUF::new,
                TCIBS.GIBSUF::setPIBSUF,
                TCIBS.GIBSUF::setGRed,
                TCIBS.GIBSUF::setVIBSUF
        );
    }

    private TCIBS.GIBSMun criarGIBSMun() {
        return criarGrupoImposto(
                pAliqIbsMun,
                classTribIbsCbs.getPRedIBS(),
                TCIBS.GIBSMun::new,
                TCIBS.GIBSMun::setPIBSMun,
                TCIBS.GIBSMun::setGRed,
                TCIBS.GIBSMun::setVIBSMun
        );
    }

    private TCIBS.GCBS criarGCBS() {
        return criarGrupoImposto(
                pAliqCbs,
                classTribIbsCbs.getPRedCBS(),
                TCIBS.GCBS::new,
                TCIBS.GCBS::setPCBS,
                TCIBS.GCBS::setGRed,
                TCIBS.GCBS::setVCBS
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
    private interface ValorSetter<T> {
        void set(T grupo, String valor);
    }

    private <T> T criarGrupoImposto(
            BigDecimal aliqPadrao,
            BigDecimal percentualReducao,
            GrupoImpostoFactory<T> factory,
            AliquotaSetter<T> aliqSetter,
            RedutorSetter<T> redSetter,
            ValorSetter<T> valorSetter) {

        T grupo = factory.create();
        BigDecimal aliq = Boolean.TRUE.equals(classTribIbsCbs.getIndTribRegular())
                ? BigDecimal.ZERO
                : ObjetoUtil.getOrZero(aliqPadrao);

        aliqSetter.set(grupo, ObjetoUtil.getValor4Casas(aliq));

        BigDecimal percentRed = ObjetoUtil.getOrZero(percentualReducao);
        BigDecimal aliqEfet = aliq;

        if (Boolean.TRUE.equals(cstIbsCbs.getIndRedAliq())) {
            TRed gRed = criarRedutor(percentRed, aliq);
            redSetter.set(grupo, gRed);
            aliqEfet = new BigDecimal(gRed.getPAliqEfet());
        }

        BigDecimal valor = calcularValorImposto(aliqEfet);
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
        gIbsUF.setVDif("0.00");
        gIbsUF.setVDevTrib("0.00");
        gIbsUF.setVIBSUF(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_IBS_UF, BigDecimal.ZERO)));
        return gIbsUF;
    }

    private TIBSCBSMonoTot.GIBS.GIBSMun criarGIBSMunTotal() {
        TIBSCBSMonoTot.GIBS.GIBSMun gIbsMun = new TIBSCBSMonoTot.GIBS.GIBSMun();
        gIbsMun.setVDif("0.00");
        gIbsMun.setVDevTrib("0.00");
        gIbsMun.setVIBSMun(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_IBS_MUN, BigDecimal.ZERO)));
        return gIbsMun;
    }

    private TIBSCBSMonoTot.GCBS criarTotaisCBS() {
        TIBSCBSMonoTot.GCBS gCbs = new TIBSCBSMonoTot.GCBS();
        gCbs.setVDif("0.00");
        gCbs.setVDevTrib("0.00");
        gCbs.setVCBS(ObjetoUtil.getValor2Casas(mapTotais.getOrDefault(TOTAL_CBS, BigDecimal.ZERO)));
        gCbs.setVCredPres("0.00");
        gCbs.setVCredPresCondSus("0.00");
        return gCbs;
    }
}
