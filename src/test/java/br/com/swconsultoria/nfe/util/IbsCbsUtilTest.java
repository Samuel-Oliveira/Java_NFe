package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dto.ClassificacaoTributariaDTO;
import br.com.swconsultoria.nfe.dto.CstDTO;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para IbsCbsUtil cobrindo os 8 novos grupos da reforma tributária.
 */
class IbsCbsUtilTest {

    private static final String CC_TRIB = "TST001";

    private TNFe.InfNFe.Det detMinimo;

    @BeforeEach
    void setUp() {
        detMinimo = criarDetMinimo("100.00", "1.0000");
    }

    // -------------------------------------------------------------------------
    // 1. gTransfCred
    // -------------------------------------------------------------------------

    @Test
    void deveMontarGrupoTransfCredQuandoIndTransfCredAtivo() throws NfeException {
        CstDTO cst = montarCst("TST", false, false, true, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(new BigDecimal("5"));
        util.setpAliqCbs(new BigDecimal("9"));

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNotNull(resultado.getGTransfCred(), "gTransfCred deve estar preenchido");
        assertNull(resultado.getGIBSCBS(), "gIBSCBS não deve ser montado quando IndTransfCred=true");
        assertNull(resultado.getGIBSCBSMono());
        assertNotNull(resultado.getGTransfCred().getVIBS());
        assertNotNull(resultado.getGTransfCred().getVCBS());
    }

    // -------------------------------------------------------------------------
    // 2. gAjusteCompet
    // -------------------------------------------------------------------------

    @Test
    void deveMontarGrupoAjusteCompetQuandoIndAjusteCompetAtivo() throws Exception {
        CstDTO cst = montarCst("TST", false, false, false, false, true, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));

        javax.xml.datatype.XMLGregorianCalendar competApur =
                javax.xml.datatype.DatatypeFactory.newInstance()
                        .newXMLGregorianCalendarDate(2025, 1, 1,
                                javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED);
        util.setCompetApur(competApur);

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNotNull(resultado.getGAjusteCompet(), "gAjusteCompet deve estar preenchido");
        assertNull(resultado.getGIBSCBS());
        assertNull(resultado.getGTransfCred());
        assertEquals(competApur, resultado.getGAjusteCompet().getCompetApur());
        assertNotNull(resultado.getGAjusteCompet().getVIBS());
        assertNotNull(resultado.getGAjusteCompet().getVCBS());
    }

    // -------------------------------------------------------------------------
    // 3. gEstornoCred
    // -------------------------------------------------------------------------

    @Test
    void deveMontarGrupoEstornoCredQuandoIndEstornoCredAtivo() throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, true, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNotNull(resultado.getGEstornoCred(), "gEstornoCred deve estar preenchido");
        assertNotNull(resultado.getGEstornoCred().getVIBSEstCred());
        assertNotNull(resultado.getGEstornoCred().getVCBSEstCred());
        // Com BC=100, aliqUF=10% → vIBSEstCred = 10.00
        assertEquals("10.00", resultado.getGEstornoCred().getVIBSEstCred());
        assertEquals("9.00", resultado.getGEstornoCred().getVCBSEstCred());
    }

    // -------------------------------------------------------------------------
    // 4. gCredPresOper
    // -------------------------------------------------------------------------

    @Test
    void deveMontarGrupoCredPresOperQuandoIndCredPresOperAtivo() throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, true, false, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));
        util.setCCredPres("01");
        util.setPCredPresIBS("5.0000");
        util.setPCredPresCBS("9.0000");

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNotNull(resultado.getGCredPresOper(), "gCredPresOper deve estar preenchido");
        assertEquals("01", resultado.getGCredPresOper().getCCredPres());
        assertNotNull(resultado.getGCredPresOper().getGIBSCredPres());
        assertNotNull(resultado.getGCredPresOper().getGCBSCredPres());
        assertEquals("5.0000", resultado.getGCredPresOper().getGIBSCredPres().getPCredPres());
        assertNull(resultado.getGCredPresIBSZFM());
    }

    // -------------------------------------------------------------------------
    // 5. gCredPresIBSZFM
    // -------------------------------------------------------------------------

    @Test
    void deveMontarGrupoCredPresIBSZFMQuandoIndCredPresZFMAtivo() throws Exception {
        CstDTO cst = montarCst("TST", true, false, false, false, false, true);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));

        javax.xml.datatype.XMLGregorianCalendar competApur =
                javax.xml.datatype.DatatypeFactory.newInstance()
                        .newXMLGregorianCalendarDate(2025, 6, 1,
                                javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED);
        util.setCompetApur(competApur);
        util.setTpCredPresIBSZFM("1");

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNotNull(resultado.getGCredPresIBSZFM(), "gCredPresIBSZFM deve estar preenchido");
        assertEquals("1", resultado.getGCredPresIBSZFM().getTpCredPresIBSZFM());
        assertEquals(competApur, resultado.getGCredPresIBSZFM().getCompetApur());
        assertNull(resultado.getGCredPresOper());
    }

    // -------------------------------------------------------------------------
    // 6. gTribCompraGov
    // -------------------------------------------------------------------------

    @Test
    void deveMontarGrupoTribCompraGovQuandoIndCompraGovAtivo() throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(new BigDecimal("2"));
        util.setpAliqCbs(new BigDecimal("9"));

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNotNull(resultado.getGIBSCBS(), "gIBSCBS deve ser montado junto com gTribCompraGov");
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov(), "gTribCompraGov deve estar preenchido em TCIBS");
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov().getPAliqIBSUF());
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov().getVTribIBSUF());
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov().getPAliqIBSMun());
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov().getVTribIBSMun());
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov().getPAliqCBS());
        assertNotNull(resultado.getGIBSCBS().getGTribCompraGov().getVTribCBS());
        // Com BC=100, pAliqIBSUF=10% → vTribIBSUF = 10.00
        assertEquals("10.0000", resultado.getGIBSCBS().getGTribCompraGov().getPAliqIBSUF());
        assertEquals("10.00", resultado.getGIBSCBS().getGTribCompraGov().getVTribIBSUF());
    }

    // -------------------------------------------------------------------------
    // 7 e 8. Totais: gMono e gEstornoCred em preencheTotaisIbsCsb
    // -------------------------------------------------------------------------

    @Test
    void deveAcumularTotaisEstornoEmPreencheTotais() throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, true, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(BigDecimal.ZERO);
        util.setpAliqCbs(new BigDecimal("9"));

        // Processa dois itens — cada um acumula 10.00 IBS + 9.00 CBS de estorno
        util.montaImpostosDet(CC_TRIB, detMinimo);
        util.montaImpostosDet(CC_TRIB, detMinimo);

        TIBSCBSMonoTot totais = util.preencheTotaisIbsCsb();

        assertNotNull(totais.getGEstornoCred(), "gEstornoCred deve aparecer nos totais quando há estorno acumulado");
        assertEquals("20.00", totais.getGEstornoCred().getVIBSEstCred());
        assertEquals("18.00", totais.getGEstornoCred().getVCBSEstCred());
        assertNull(totais.getGMono(), "gMono não deve aparecer quando não há itens monofásicos");
    }

    @Test
    void naoDevePreencherGEstornoCredNosToatisQuandoNaoHaEstorno() throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));

        util.montaImpostosDet(CC_TRIB, detMinimo);
        TIBSCBSMonoTot totais = util.preencheTotaisIbsCsb();

        assertNull(totais.getGEstornoCred(), "gEstornoCred não deve aparecer quando não há estorno");
        assertNull(totais.getGMono(), "gMono não deve aparecer quando não há monofasia");
    }

    // -------------------------------------------------------------------------
    // Regressão: grupos opcionais ausentes quando indicadores são falsos/null
    // -------------------------------------------------------------------------

    @Test
    void naoDeveMontarGruposOpcionaisQuandoIndicadoresFalsos() throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));

        TTribNFe resultado = util.montaImpostosDet(CC_TRIB, detMinimo);

        assertNull(resultado.getGTransfCred());
        assertNull(resultado.getGAjusteCompet());
        assertNull(resultado.getGEstornoCred());
        assertNull(resultado.getGCredPresOper());
        assertNull(resultado.getGCredPresIBSZFM());
        assertNotNull(resultado.getGIBSCBS(), "gIBSCBS deve aparecer com IndIBSCBS=true");
        assertNull(resultado.getGIBSCBS().getGTribCompraGov());
    }

    @Test
    void naoDeveQuebrarComCstDtoSemNovosIndicadores() throws NfeException {
        CstDTO cst = new CstDTO();
        cst.setCst("000");
        cst.setIndIBSCBS(true);
        // Todos os novos indicadores ficam null — simula DTOs antigos sem os novos campos

        ClassificacaoTributariaDTO ct = new ClassificacaoTributariaDTO();
        ct.setCClassTrib(CC_TRIB);
        ct.setIndNFe(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqCbs(new BigDecimal("9"));

        TTribNFe resultado = assertDoesNotThrow(() -> util.montaImpostosDet(CC_TRIB, detMinimo),
                "Nao deve lancar excecao com indicadores null");

        assertNotNull(resultado.getGIBSCBS());
        assertNull(resultado.getGTransfCred());
        assertNull(resultado.getGAjusteCompet());
        assertNull(resultado.getGEstornoCred());
        assertNull(resultado.getGCredPresOper());
        assertNull(resultado.getGCredPresIBSZFM());
    }

    // -------------------------------------------------------------------------
    // Base de calculo do IBS/CBS (gIBSCBS/vBC)
    // -------------------------------------------------------------------------

    /**
     * Regressao: item com ICMS70 (reducao de BC + ST) tinha o vICMS ignorado por
     * XmlImpostoUtil.getVICMS, inflando a base do IBS/CBS.
     * Cenario real: NF-e 735190 / Puratos, item 1.
     * 668.57 - 9.71 (PIS) - 44.71 (COFINS) - 80.23 (ICMS) = 533.92
     */
    @Test
    void deveSubtrairVicmsDoGrupoIcms70NaBaseDeCalculo() throws NfeException {
        TNFe.InfNFe.Det det = criarDetMinimo("668.57", "2.0000");
        adicionarIcms70(det, "80.23", null);
        adicionarPisCofins(det, "9.71", "44.71");

        TTribNFe resultado = calcular(det);

        assertEquals("533.92", resultado.getGIBSCBS().getVBC());
        assertEquals("0.53", resultado.getGIBSCBS().getGIBSUF().getVIBSUF());
        assertEquals("4.81", resultado.getGIBSCBS().getGCBS().getVCBS());
    }

    @Test
    void deveSubtrairVfcpDoGrupoIcms70NaBaseDeCalculo() throws NfeException {
        TNFe.InfNFe.Det det = criarDetMinimo("668.57", "2.0000");
        adicionarIcms70(det, "80.23", "10.00");
        adicionarPisCofins(det, "9.71", "44.71");

        TTribNFe resultado = calcular(det);

        assertEquals("523.92", resultado.getGIBSCBS().getVBC());
    }

    @Test
    void deveSubtrairVicmsDoGrupoIcmsPartNaBaseDeCalculo() throws NfeException {
        TNFe.InfNFe.Det det = criarDetMinimo("100.00", "1.0000");
        TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
        TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart part = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart();
        part.setVICMS("18.00");
        icms.setICMSPart(part);
        det.getImposto().getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));

        TTribNFe resultado = calcular(det);

        assertEquals("82.00", resultado.getGIBSCBS().getVBC());
    }

    @Test
    void deveSomarViiEVisNaBaseDeCalculo() throws NfeException {
        TNFe.InfNFe.Det det = criarDetMinimo("100.00", "1.0000");
        ObjectFactory of = new ObjectFactory();

        TNFe.InfNFe.Det.Imposto.II ii = new TNFe.InfNFe.Det.Imposto.II();
        ii.setVII("20.00");
        det.getImposto().getContent().add(of.createTNFeInfNFeDetImpostoII(ii));

        TIS is = new TIS();
        is.setVIS("5.00");
        det.getImposto().getContent().add(of.createTNFeInfNFeDetImpostoIS(is));

        TTribNFe resultado = calcular(det);

        assertEquals("125.00", resultado.getGIBSCBS().getVBC());
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private TNFe.InfNFe.Det criarDetMinimo(String vProd, String qCom) {
        TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
        prod.setVProd(vProd);
        prod.setQCom(qCom);
        det.setProd(prod);
        det.setImposto(new TNFe.InfNFe.Det.Imposto());
        return det;
    }

    /**
     * Monta CstDTO com os flags principais.
     * Parâmetros: indIBSCBS, indRedAliq, indTransfCred, indIBSCBSMono, indAjusteCompet, indCredPresIBSZFM.
     */
    private CstDTO montarCst(String cst, boolean indIBSCBS, boolean indRedAliq,
                              boolean indTransfCred, boolean indIBSCBSMono,
                              boolean indAjusteCompet, boolean indCredPresIBSZFM) {
        CstDTO dto = new CstDTO();
        dto.setCst(cst);
        dto.setIndIBSCBS(indIBSCBS);
        dto.setIndRedAliq(indRedAliq);
        dto.setIndTransfCred(indTransfCred);
        dto.setIndIBSCBSMono(indIBSCBSMono);
        dto.setIndAjusteCompet(indAjusteCompet);
        dto.setIndCredPresIBSZFM(indCredPresIBSZFM);
        return dto;
    }

    /**
     * Monta ClassificacaoTributariaDTO com os flags relevantes para os novos grupos.
     * Parâmetros: indCredPresOper, indEstornoCred, indCompraGov.
     */
    private ClassificacaoTributariaDTO montarClassTrib(String cClassTrib, boolean indCredPresOper,
                                                       boolean indEstornoCred, boolean indCompraGov) {
        ClassificacaoTributariaDTO dto = new ClassificacaoTributariaDTO();
        dto.setCClassTrib(cClassTrib);
        dto.setIndNFe(true);
        dto.setIndCredPresOper(indCredPresOper);
        dto.setIndEstornoCred(indEstornoCred);
        dto.setIndCompraGov(indCompraGov);
        return dto;
    }

    /** Monta o IbsCbsUtil padrao (IBS UF 0,10% / CBS 0,90%) e calcula os impostos do item. */
    private TTribNFe calcular(TNFe.InfNFe.Det det) throws NfeException {
        CstDTO cst = montarCst("TST", true, false, false, false, false, false);
        cst.setClassificacoesTributarias(
                Collections.singletonList(montarClassTrib(CC_TRIB, false, false, false)));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("0.1"));
        util.setpAliqIbsMun(BigDecimal.ZERO);
        util.setpAliqCbs(new BigDecimal("0.9"));
        return util.montaImpostosDet(CC_TRIB, det);
    }

    private void adicionarIcms70(TNFe.InfNFe.Det det, String vIcms, String vFcp) {
        TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 icms70 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
        icms70.setVICMS(vIcms);
        icms70.setVFCP(vFcp);
        // ICMS-ST nao entra na base do IBS/CBS
        icms70.setVICMSST("157.23");
        icms.setICMS70(icms70);
        det.getImposto().getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
    }

    private void adicionarPisCofins(TNFe.InfNFe.Det det, String vPis, String vCofins) {
        ObjectFactory of = new ObjectFactory();

        TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();
        TNFe.InfNFe.Det.Imposto.PIS.PISAliq pisAliq = new TNFe.InfNFe.Det.Imposto.PIS.PISAliq();
        pisAliq.setVPIS(vPis);
        pis.setPISAliq(pisAliq);
        det.getImposto().getContent().add(of.createTNFeInfNFeDetImpostoPIS(pis));

        TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq cofAliq = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
        cofAliq.setVCOFINS(vCofins);
        cofins.setCOFINSAliq(cofAliq);
        det.getImposto().getContent().add(of.createTNFeInfNFeDetImpostoCOFINS(cofins));
    }
}
