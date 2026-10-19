package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dto.ClassificacaoTributariaDTO;
import br.com.swconsultoria.nfe.dto.CstDTO;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    // Regressão issue #349: IbsCbsUtil herdava cClassTrib do item anterior
    // quando o cClassTrib do item corrente era nulo/vazio/inexistente, pois
    // filtraCClasstrib só atribuía os campos de instância dentro do
    // ifPresent, sem limpá-los no caminho "não encontrado". A partir do 2º
    // item, um cClassTrib inválido deixava de lançar exceção porque
    // validaClassTrib valida o campo remanescente do item anterior, não o
    // argumento recebido.
    // -------------------------------------------------------------------------

    @Test
    void deveLancarExcecaoNoSegundoItemQuandoCClassTribForNuloAposItemValido() throws Exception {
        IbsCbsUtil util = new IbsCbsUtil(carregarJsonIbsCbs(), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(BigDecimal.ZERO);
        util.setpAliqCbs(new BigDecimal("9"));

        // Item 1 válido, processado com sucesso
        util.montaImpostosDet("000001", criarDetMinimo("100.00", "1.0000"));

        // Item 2 com cClassTrib nulo deve lançar, mesmo com estado do item 1 na instância
        NfeException excecao = assertThrows(NfeException.class,
                () -> util.montaImpostosDet(null, criarDetMinimo("100.00", "1.0000")));
        assertEquals("CClassTrib inválido ou não encontrado: null", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoNoSegundoItemQuandoCClassTribForVazioAposItemValido() throws Exception {
        IbsCbsUtil util = new IbsCbsUtil(carregarJsonIbsCbs(), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(BigDecimal.ZERO);
        util.setpAliqCbs(new BigDecimal("9"));

        util.montaImpostosDet("000001", criarDetMinimo("100.00", "1.0000"));

        NfeException excecao = assertThrows(NfeException.class,
                () -> util.montaImpostosDet("", criarDetMinimo("100.00", "1.0000")));
        assertEquals("CClassTrib inválido ou não encontrado: ", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoNoSegundoItemQuandoCClassTribForInexistenteAposItemValido() throws Exception {
        IbsCbsUtil util = new IbsCbsUtil(carregarJsonIbsCbs(), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(BigDecimal.ZERO);
        util.setpAliqCbs(new BigDecimal("9"));

        util.montaImpostosDet("000001", criarDetMinimo("100.00", "1.0000"));

        NfeException excecao = assertThrows(NfeException.class,
                () -> util.montaImpostosDet("999999", criarDetMinimo("100.00", "1.0000")));
        assertEquals("CClassTrib inválido ou não encontrado: 999999", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCClassTribInvalidoIndependenteDaPosicaoDoItem() throws Exception {
        String json = carregarJsonIbsCbs();

        // Mesmo cClassTrib inválido como 1º item de uma instância nova
        IbsCbsUtil utilPrimeiroItem = new IbsCbsUtil(json, DocumentoEnum.NFE);
        utilPrimeiroItem.setpAliqIbsUf(new BigDecimal("10"));
        utilPrimeiroItem.setpAliqCbs(new BigDecimal("9"));
        NfeException excecaoNoPrimeiroItem = assertThrows(NfeException.class,
                () -> utilPrimeiroItem.montaImpostosDet("999999", criarDetMinimo("100.00", "1.0000")));

        // Mesmo cClassTrib inválido como 2º item, após um 1º item válido na mesma instância
        IbsCbsUtil utilSegundoItem = new IbsCbsUtil(json, DocumentoEnum.NFE);
        utilSegundoItem.setpAliqIbsUf(new BigDecimal("10"));
        utilSegundoItem.setpAliqCbs(new BigDecimal("9"));
        utilSegundoItem.montaImpostosDet("000001", criarDetMinimo("100.00", "1.0000"));
        NfeException excecaoNoSegundoItem = assertThrows(NfeException.class,
                () -> utilSegundoItem.montaImpostosDet("999999", criarDetMinimo("100.00", "1.0000")));

        assertEquals(excecaoNoPrimeiroItem.getMessage(), excecaoNoSegundoItem.getMessage(),
                "O mesmo cClassTrib inválido deve lançar a mesma exceção independente da posição do item");
        assertEquals("CClassTrib inválido ou não encontrado: 999999", excecaoNoSegundoItem.getMessage());
    }

    @Test
    void deveManterCstEClassTribCorretosDeCadaItemEAcumularTotaisCorretamenteEmSequenciaValida() throws Exception {
        IbsCbsUtil util = new IbsCbsUtil(carregarJsonIbsCbs(), DocumentoEnum.NFE);
        util.setpAliqIbsUf(new BigDecimal("10"));
        util.setpAliqIbsMun(BigDecimal.ZERO);
        util.setpAliqCbs(new BigDecimal("9"));

        // Item 1: CST 000/cClassTrib 000001, sem redução de alíquota
        TTribNFe resultadoItem1 = util.montaImpostosDet("000001", criarDetMinimo("100.00", "1.0000"));
        assertEquals("000", resultadoItem1.getCST());
        assertEquals("000001", resultadoItem1.getCClassTrib());
        assertEquals("10.00", resultadoItem1.getGIBSCBS().getGIBSUF().getVIBSUF());
        assertEquals("9.00", resultadoItem1.getGIBSCBS().getGCBS().getVCBS());

        // Item 2: CST 200/cClassTrib 200013, com redução de 100% da alíquota
        TTribNFe resultadoItem2 = util.montaImpostosDet("200013", criarDetMinimo("100.00", "1.0000"));
        assertEquals("200", resultadoItem2.getCST());
        assertEquals("200013", resultadoItem2.getCClassTrib());
        assertEquals("100.00", resultadoItem2.getGIBSCBS().getVBC());
        assertEquals("0.00", resultadoItem2.getGIBSCBS().getGIBSUF().getVIBSUF());
        assertEquals("0.00", resultadoItem2.getGIBSCBS().getGCBS().getVCBS());

        // Totais acumulados corretamente via preencheTotaisIbsCsb()
        TIBSCBSMonoTot totais = util.preencheTotaisIbsCsb();
        assertEquals("200.00", totais.getVBCIBSCBS());
        assertEquals("10.00", totais.getGIBS().getVIBS());
        assertEquals("9.00", totais.getGCBS().getVCBS());
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private String carregarJsonIbsCbs() throws IOException {
        return XmlNfeUtil.leXml("src/test/resources/ibscbs.json");
    }

    private TNFe.InfNFe.Det criarDetMinimo(String vProd, String qCom) {
        TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
        prod.setVProd(vProd);
        prod.setQCom(qCom);
        // qTrib e obrigatorio no XSD e e a base quantitativa da monofasia; aqui as duas
        // unidades coincidem, que e o caso comum em combustivel (uCom = uTrib = litro)
        prod.setQTrib(qCom);
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

    // -------------------------------------------------------------------------
    // Monofasia: estrutura das modalidades ad rem / ad valorem
    // -------------------------------------------------------------------------

    @Test
    void deveMontarMonofasiaAdValoremPorPadraoComGruposIbsECbsSeparados() throws NfeException {
        // Ad valorem e o padrao porque a SEFAZ rejeita ad rem com cStat 1219,
        // verificado em homologacao da SEFAZ-GO
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("100.00", "1.0000"))
                .getGIBSCBSMono();

        assertNotNull(mono.getGIBSMonoAdValorem(), "ad valorem e o padrao: gIBSMonoAdValorem deve ser emitido");
        assertNotNull(mono.getGCBSMonoAdValorem(), "IBS e CBS são eixos independentes: ambos emitidos");
        assertNull(mono.getGIBSMonoAdRem(), "as duas modalidades do mesmo eixo são exclusivas");
        assertNull(mono.getGCBSMonoAdRem(), "as duas modalidades do mesmo eixo são exclusivas");

        assertNotNull(mono.getGIBSMonoAdValorem().getGMonoPadrao(), "MonofasiaPadrao=true deve emitir gMonoPadrao");
        assertNull(mono.getGIBSMonoAdValorem().getGMonoRet(), "RetidaAnt=false não deve emitir gMonoRet");
        assertNull(mono.getGIBSMonoAdValorem().getGMonoReten(), "SujeitaRetencao=false não deve emitir gMonoReten");
    }

    @Test
    void deveUsarModalidadesDiferentesPorEixoEntre2027E2028() throws NfeException {
        // NT 2025.002-RTC: em 2027 e 2028 o IBS ainda e ad valorem enquanto a CBS
        // ja e ad rem. Informar errado gera cStat 1219/1220 (IBS) ou 1245/1246 (CBS).
        for (int ano : new int[]{2027, 2028}) {
            TMonofasia mono = calcularMonoNoAno(ano);

            assertNotNull(mono.getGIBSMonoAdValorem(), "em " + ano + " o IBS e ad valorem");
            assertNull(mono.getGIBSMonoAdRem(), "em " + ano + " o IBS nao pode ser ad rem");
            assertNotNull(mono.getGCBSMonoAdRem(), "em " + ano + " a CBS ja e ad rem");
            assertNull(mono.getGCBSMonoAdValorem(), "em " + ano + " a CBS nao pode ser ad valorem");
        }
    }

    @Test
    void deveSeguirATabelaDeTransicaoDaNtEmCadaAno() throws NfeException {
        TMonofasia em2026 = calcularMonoNoAno(2026);
        assertNotNull(em2026.getGIBSMonoAdValorem(), "2026: IBS ad valorem");
        assertNotNull(em2026.getGCBSMonoAdValorem(), "2026: CBS ad valorem");

        TMonofasia em2029 = calcularMonoNoAno(2029);
        assertNotNull(em2029.getGIBSMonoAdRem(), "2029: IBS ad rem");
        assertNotNull(em2029.getGCBSMonoAdRem(), "2029: CBS ad rem");
    }

    private TMonofasia calcularMonoNoAno(int ano) throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(ano);
        return util.montaImpostosDet(CC_TRIB, criarDetMinimo("100.00", "1.0000")).getGIBSCBSMono();
    }

    @Test
    void deveMontarMonofasiaAdRemQuandoDesabilitadaPeloSetter() throws NfeException {
        TMonofasia mono = calcularMono(false, true, false, false);

        assertNotNull(mono.getGIBSMonoAdRem(), "setMonofasiaAdValorem(false) deve emitir gIBSMonoAdRem");
        assertNotNull(mono.getGCBSMonoAdRem(), "setMonofasiaAdValorem(false) deve emitir gCBSMonoAdRem");
        assertNull(mono.getGIBSMonoAdValorem(), "ad valorem e ad rem não coexistem no mesmo eixo");
        assertNull(mono.getGCBSMonoAdValorem(), "ad valorem e ad rem não coexistem no mesmo eixo");
    }

    @Test
    void naoDeveEmitirAliquotaMonofasicaDiferenteDeZeroEnquantoValorForZero() throws NfeException {
        // Alíquota monofásica é própria por produto e não é a alíquota do regime regular
        // configurada nos setters. Emitir alíquota real com valor zerado deixaria o grupo
        // aritmeticamente incoerente, então ambos ficam zerados. O helper configura as três
        // alíquotas regulares com valores não-zero justamente para que uma regressão que as
        // vazasse para cá seja detectada.
        TMonofasia adValorem = calcularMono(true, true, false, false);
        TMonofasia.GIBSMonoAdValorem.GMonoPadrao ibs = adValorem.getGIBSMonoAdValorem().getGMonoPadrao();

        assertEquals("0.0000", ibs.getPAliqMonoUF(), "pAliqMonoUF não deve receber a alíquota do regime regular");
        assertEquals("0.0000", ibs.getPAliqMonoMun(), "pAliqMonoMun não deve receber a alíquota do regime regular");
        assertCoerente(ibs.getPAliqMonoUF(), ibs.getVIBSMonoUF(), "pAliqMonoUF", "vIBSMonoUF");
        assertCoerente(ibs.getPAliqMonoMun(), ibs.getVIBSMonoMun(), "pAliqMonoMun", "vIBSMonoMun");

        TMonofasia.GCBSMonoAdValorem.GMonoPadrao cbs = adValorem.getGCBSMonoAdValorem().getGMonoPadrao();
        assertEquals("0.0000", cbs.getPAliqMonoCBS(), "pAliqMonoCBS não deve receber a alíquota do regime regular");
        assertCoerente(cbs.getPAliqMonoCBS(), cbs.getVCBSMono(), "pAliqMonoCBS", "vCBSMono");

        TMonofasia adRem = calcularMono(false, true, false, false);
        TMonofasia.GIBSMonoAdRem.GMonoPadrao ibsAdRem = adRem.getGIBSMonoAdRem().getGMonoPadrao();
        assertCoerente(ibsAdRem.getAdRemIBS(), ibsAdRem.getVIBSMono(), "adRemIBS", "vIBSMono");

        TMonofasia.GCBSMonoAdRem.GMonoPadrao cbsAdRem = adRem.getGCBSMonoAdRem().getGMonoPadrao();
        assertCoerente(cbsAdRem.getAdRemCBS(), cbsAdRem.getVCBSMono(), "adRemCBS", "vCBSMono");
    }

    /** Alíquota e valor precisam ser ambos zero ou ambos não-zero. */
    private void assertCoerente(String aliquota, String valor, String nomeAliquota, String nomeValor) {
        boolean aliqZero = new BigDecimal(aliquota).signum() == 0;
        boolean valorZero = new BigDecimal(valor).signum() == 0;
        assertTrue(aliqZero == valorZero, String.format(
                "%s=%s e %s=%s são incoerentes: devem ser ambos zero ou ambos não-zero",
                nomeAliquota, aliquota, nomeValor, valor));
    }

    @Test
    void deveEmitirApenasOsSubgruposCorrespondentesAsFlagsDoCClassTrib() throws NfeException {
        TMonofasia soRetido = calcularMono(false, false, true, false);
        assertNull(soRetido.getGIBSMonoAdRem().getGMonoPadrao(), "Padrao=false não emite gMonoPadrao");
        assertNotNull(soRetido.getGIBSMonoAdRem().getGMonoRet(), "RetidaAnt=true emite gMonoRet");
        assertNull(soRetido.getGIBSMonoAdRem().getGMonoReten(), "SujeitaRetencao=false não emite gMonoReten");

        TMonofasia soReten = calcularMono(false, false, false, true);
        assertNull(soReten.getGIBSMonoAdRem().getGMonoRet(), "RetidaAnt=false não emite gMonoRet");
        assertNotNull(soReten.getGIBSMonoAdRem().getGMonoReten(), "SujeitaRetencao=true emite gMonoReten");
    }

    @Test
    void naoDeveEmitirGrupoMonofasicoVazioQuandoNenhumaFlagEstaAtiva() throws NfeException {
        TMonofasia adRem = calcularMono(false, false, false, false);
        assertNull(adRem.getGIBSMonoAdRem(), "sem flag de monofasia, não emitir wrapper vazio");
        assertNull(adRem.getGCBSMonoAdRem(), "sem flag de monofasia, não emitir wrapper vazio");

        TMonofasia adValorem = calcularMono(true, false, false, false);
        assertNull(adValorem.getGIBSMonoAdValorem(), "o guard também vale para o eixo ad valorem");
        assertNull(adValorem.getGCBSMonoAdValorem(), "o guard também vale para o eixo ad valorem");

        // Com as 4 modalidades nulas sobram apenas os totais do item, que são obrigatórios
        // no XSD (as duas sequences de modalidade são minOccurs="0").
        assertNotNull(adRem.getVTotIBSMonoItem(), "vTotIBSMonoItem é obrigatório e deve sobreviver");
        assertNotNull(adRem.getVTotCBSMonoItem(), "vTotCBSMonoItem é obrigatório e deve sobreviver");
    }

    @Test
    void deveCalcularValorAdRemComAliquotaEspecificaDeCombustivel() throws NfeException {
        // Gasolina: R$ 0,7925 por litro (tabela de aliquotas especificas).
        // Repartido aqui como exemplo entre IBS e CBS; 1000 litros.
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        util.setAdRemIBS(new BigDecimal("0.5000"));
        util.setAdRemCBS(new BigDecimal("0.2925"));

        TTribNFe tributo = util.montaImpostosDet(CC_TRIB, criarDetMinimo("5000.00", "1000.0000"));
        TMonofasia mono = tributo.getGIBSCBSMono();

        TMonofasia.GIBSMonoAdRem.GMonoPadrao ibs = mono.getGIBSMonoAdRem().getGMonoPadrao();
        assertEquals("1000.0000", ibs.getQBCMono());
        assertEquals("0.5000", ibs.getAdRemIBS());
        assertEquals("500.00", ibs.getVIBSMono(), "1000 x 0,5000");

        TMonofasia.GCBSMonoAdRem.GMonoPadrao cbs = mono.getGCBSMonoAdRem().getGMonoPadrao();
        assertEquals("0.2925", cbs.getAdRemCBS());
        assertEquals("292.50", cbs.getVCBSMono(), "1000 x 0,2925");

        assertEquals("500.00", mono.getVTotIBSMonoItem());
        assertEquals("292.50", mono.getVTotCBSMonoItem());
    }

    @Test
    void deveCalcularValorAdValoremSobreABaseDoItem() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2026);  // 2026: IBS e CBS ad valorem
        util.setPAliqMonoUF(new BigDecimal("10"));
        util.setPAliqMonoMun(new BigDecimal("2"));
        util.setPAliqMonoCBS(new BigDecimal("8"));

        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("1000.00", "1.0000"))
                .getGIBSCBSMono();

        TMonofasia.GIBSMonoAdValorem.GMonoPadrao ibs = mono.getGIBSMonoAdValorem().getGMonoPadrao();
        assertEquals("1000.00", ibs.getVBCMono());
        assertEquals("100.00", ibs.getVIBSMonoUF(), "1000 x 10%");
        assertEquals("20.00", ibs.getVIBSMonoMun(), "1000 x 2%");
        assertEquals("120.00", ibs.getVIBSMono(), "soma UF + Mun");

        TMonofasia.GCBSMonoAdValorem.GMonoPadrao cbs = mono.getGCBSMonoAdValorem().getGMonoPadrao();
        assertEquals("80.00", cbs.getVCBSMono(), "1000 x 8%");

        assertEquals("120.00", mono.getVTotIBSMonoItem());
        assertEquals("80.00", mono.getVTotCBSMonoItem());
    }

    @Test
    void deveCalcularRetencaoEUsarAliquotaDoPadraoQuandoNaoInformada() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaSujeitaRetencao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        util.setAdRemIBS(new BigDecimal("0.3515"));
        util.setAdRemCBS(new BigDecimal("0.1000"));

        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("500.00", "100.0000"))
                .getGIBSCBSMono();

        TMonofasia.GIBSMonoAdRem.GMonoReten reten = mono.getGIBSMonoAdRem().getGMonoReten();
        assertEquals("100.0000", reten.getQBCMonoReten(), "sem setQBCMonoReten, usa a quantidade do item");
        assertEquals("0.3515", reten.getAdRemIBSReten(), "sem aliquota propria de retencao, herda a do padrao");
        assertEquals("35.15", reten.getVIBSMonoReten(), "100 x 0,3515");
    }

    @Test
    void deveUsarAliquotaEQuantidadeProprasDeRetencaoQuandoInformadas() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaSujeitaRetencao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        util.setAdRemIBS(new BigDecimal("0.3515"));
        util.setAdRemIBSReten(new BigDecimal("0.2000"));
        util.setQBCMonoReten(new BigDecimal("40"));

        TMonofasia.GIBSMonoAdRem.GMonoReten reten = util
                .montaImpostosDet(CC_TRIB, criarDetMinimo("500.00", "100.0000"))
                .getGIBSCBSMono().getGIBSMonoAdRem().getGMonoReten();

        assertEquals("40.0000", reten.getQBCMonoReten());
        assertEquals("0.2000", reten.getAdRemIBSReten());
        assertEquals("8.00", reten.getVIBSMonoReten(), "40 x 0,2000");
    }

    @Test
    void deveUsarValorInformadoNoRetidoAnteriormente() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaRetidaAnt(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        util.setVIBSMonoRet(new BigDecimal("12.34"));
        util.setVCBSMonoRet(new BigDecimal("56.78"));

        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("500.00", "10.0000"))
                .getGIBSCBSMono();

        assertEquals("12.34", mono.getGIBSMonoAdRem().getGMonoRet().getVIBSMonoRet());
        assertEquals("56.78", mono.getGCBSMonoAdRem().getGMonoRet().getVCBSMonoRet());

        // UB105a-10 e UB105b-10: vTot*MonoItem = padrao + retencao, sem o retido
        // anteriormente. Como so o gMonoRet foi emitido, os totais do item ficam zero.
        assertEquals("0.00", mono.getVTotIBSMonoItem(), "o retido anteriormente nao entra no total do item");
        assertEquals("0.00", mono.getVTotCBSMonoItem(), "o retido anteriormente nao entra no total do item");
    }

    @Test
    void deveAcumularTotaisMonofasicosDaNotaEEmitirGMono() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        util.setAdRemIBS(new BigDecimal("0.5000"));
        util.setAdRemCBS(new BigDecimal("0.2925"));

        // dois itens de 1000 litros cada
        util.montaImpostosDet(CC_TRIB, criarDetMinimo("5000.00", "1000.0000"));
        util.montaImpostosDet(CC_TRIB, criarDetMinimo("5000.00", "1000.0000"));

        TIBSCBSMonoTot totais = util.preencheTotaisIbsCsb();

        assertNotNull(totais.getGMono(), "com valores monofasicos nao-zero, gMono deve ser emitido");
        assertEquals("1000.00", totais.getGMono().getVIBSMono(), "2 x 500,00");
        assertEquals("585.00", totais.getGMono().getVCBSMono(), "2 x 292,50");
    }

    @Test
    void deveMontarGpBioDiferencaQuandoCClassTribIndicaDiferencaDeBiocombustivel() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setIndPercentualDiferencaBioCombustivel(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        // adRemIBS e a aliquota do PRODUTO; o gpBioDiferenca usa a do BIOCOMBUSTIVEL,
        // que e outra coluna da mesma tabela (UB89b-10). Informar so a do produto nao
        // deve fazer a lib calcular o vIBSDiferenca.
        util.setAdRemIBS(new BigDecimal("0.7925"));
        util.setAdRemCBS(new BigDecimal("0.5000"));
        util.setQBCBioComb(new BigDecimal("50"));

        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("500.00", "1000.0000"))
                .getGIBSCBSMono();

        TMonofasia.GIBSMonoAdRem.GpBioDiferenca ibs = mono.getGIBSMonoAdRem().getGpBioDiferenca();
        assertNotNull(ibs, "IndPercentualDiferencaBioCombustivel=true deve emitir gpBioDiferenca");
        assertEquals("50.0000", ibs.getQBCBioComb());
        assertEquals("0.00", ibs.getVIBSDiferenca(), "sem a aliquota do biocombustivel nao se calcula");

        TMonofasia.GCBSMonoAdRem.GpBioDiferenca cbs = mono.getGCBSMonoAdRem().getGpBioDiferenca();
        assertEquals("0.00", cbs.getVCBSDiferenca(), "sem a aliquota do biocombustivel nao se calcula");
    }

    @Test
    void deveUsarQuantidadeTributavelComoBaseDaMonofasiaENaoAComercial() throws NfeException {
        // UB86a: "a BC em quantidade conforme unidade de medida estabelecida na
        // legislacao", que e a de uTrib. Quando uCom != uTrib (ex.: venda em caixa,
        // tributacao por litro) usar qCom erraria o imposto pelo fator de conversao,
        // e sem rejeicao, porque UB86c-10 so confere a consistencia interna do grupo.
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);
        util.setAdRemIBS(new BigDecimal("0.5000"));

        TNFe.InfNFe.Det det = criarDetMinimo("500.00", "2.0000");  // 2 caixas
        det.getProd().setQTrib("24.0000");                          // de 12 litros cada

        TMonofasia.GIBSMonoAdRem.GMonoPadrao padrao = util
                .montaImpostosDet(CC_TRIB, det)
                .getGIBSCBSMono().getGIBSMonoAdRem().getGMonoPadrao();

        assertEquals("24.0000", padrao.getQBCMono(), "a base e a quantidade tributavel");
        assertEquals("12.00", padrao.getVIBSMono(), "24 litros x 0,5000, nao 2 caixas");
    }

    @Test
    void deveCalcularDiferencaDeBioComAAliquotaDoBiocombustivel() throws NfeException {
        // UB89b-10: vIBSDiferenca = qBCBioComb x adRemIBSBioComb
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setIndPercentualDiferencaBioCombustivel(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);
        util.setAdRemIBS(new BigDecimal("0.7925"));          // aliquota do produto: ignorada aqui
        util.setAdRemIBSBioComb(new BigDecimal("0.1922"));   // EAC: R$ 0,1922 por litro
        util.setAdRemCBSBioComb(new BigDecimal("0.1000"));
        util.setQBCBioComb(new BigDecimal("50"));

        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("500.00", "1000.0000"))
                .getGIBSCBSMono();

        assertEquals("9.61", mono.getGIBSMonoAdRem().getGpBioDiferenca().getVIBSDiferenca(),
                "50 x 0,1922, a aliquota do biocombustivel e nao a do produto");
        assertEquals("5.00", mono.getGCBSMonoAdRem().getGpBioDiferenca().getVCBSDiferenca(),
                "50 x 0,1000");
    }

    @Test
    void deveCalcularQBCBioCombPelaFormulaDaNtQuandoPBioObrigInformado() throws NfeException {
        // NT 2025.002-RTC, UB94a-10 / 620004 (a recolher):
        //   qBCBioComb = qTrib x (pBio - pBioObrig) / (1 - pBioObrig)
        //   100 x (0,30 - 0,27) / (1 - 0,27) = 100 x 0,03 / 0,73 = 4,1096
        TMonofasia.GIBSMonoAdValorem.GpBioDiferenca bio =
                calcularBio("620004", "30.0000", new BigDecimal("27"));

        assertEquals("4.1096", bio.getQBCBioComb(), "gasolina com EAC acima do obrigatorio");
    }

    @Test
    void deveInverterAFormulaDoBioNoCClassTrib620005() throws NfeException {
        // 620005 (a ressarcir): qBCBioComb = qTrib x (pBioObrig - pBio) / (1 - pBioObrig)
        //   100 x (0,27 - 0,20) / 0,73 = 9,5890
        TMonofasia.GIBSMonoAdValorem.GpBioDiferenca bio =
                calcularBio("620005", "20.0000", new BigDecimal("27"));

        assertEquals("9.5890", bio.getQBCBioComb(), "mistura abaixo do obrigatorio: ressarcir");
    }

    @Test
    void deveUsarQuantidadeInformadaNoBioQuandoPBioObrigDesconhecido() throws NfeException {
        // Sem o pBioObrig (tabela Indice de Mistura, consultada pelo cProdANP) nao da
        // para aplicar a formula, entao vale o valor informado pelo integrador.
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib("620004", false, false, false);
        ct.setIndPercentualDiferencaBioCombustivel(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2026);
        util.setQBCBioComb(new BigDecimal("7.5"));

        TMonofasia.GIBSMonoAdValorem.GpBioDiferenca bio = util
                .montaImpostosDet("620004", criarDetComBio("100.0000", "30.0000"))
                .getGIBSCBSMono().getGIBSMonoAdValorem().getGpBioDiferenca();

        assertEquals("7.5000", bio.getQBCBioComb());
    }

    private TMonofasia.GIBSMonoAdValorem.GpBioDiferenca calcularBio(
            String cClassTrib, String pBio, BigDecimal pBioObrig) throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(cClassTrib, false, false, false);
        ct.setIndPercentualDiferencaBioCombustivel(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2026);
        util.setPBioObrig(pBioObrig);

        return util.montaImpostosDet(cClassTrib, criarDetComBio("100.0000", pBio))
                .getGIBSCBSMono().getGIBSMonoAdValorem().getGpBioDiferenca();
    }

    /** Item de combustivel com qTrib e pBio, que a formula do gpBioDiferenca consome. */
    private TNFe.InfNFe.Det criarDetComBio(String qTrib, String pBio) {
        TNFe.InfNFe.Det det = criarDetMinimo("500.00", qTrib);
        det.getProd().setQTrib(qTrib);
        TNFe.InfNFe.Det.Prod.Comb comb = new TNFe.InfNFe.Det.Prod.Comb();
        comb.setCProdANP("320102001");
        comb.setDescANP("GASOLINA COMUM");
        comb.setPBio(pBio);
        det.getProd().setComb(comb);
        return det;
    }

    @Test
    void naoDeveMontarGpBioDiferencaQuandoIndicadorEstaDesligado() throws NfeException {
        TMonofasia mono = calcularMono(false, true, false, false);

        assertNull(mono.getGIBSMonoAdRem().getGpBioDiferenca(),
                "sem o indicador do cClassTrib, gpBioDiferenca nao deve sair");
        assertNull(mono.getGCBSMonoAdRem().getGpBioDiferenca(),
                "sem o indicador do cClassTrib, gpBioDiferenca nao deve sair");
    }

    @Test
    void devePermitirInformarValorDaDiferencaDeBioDiretamente() throws NfeException {
        CstDTO cst = montarCst("620", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setIndPercentualDiferencaBioCombustivel(true);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        util.setAnoEmissao(2029);  // 2029: IBS e CBS ad rem
        util.setQBCBioComb(new BigDecimal("50"));
        util.setVIBSDiferenca(new BigDecimal("77.77"));

        TMonofasia.GIBSMonoAdRem.GpBioDiferenca bio = util
                .montaImpostosDet(CC_TRIB, criarDetMinimo("500.00", "1000.0000"))
                .getGIBSCBSMono().getGIBSMonoAdRem().getGpBioDiferenca();

        assertEquals("77.77", bio.getVIBSDiferenca(), "valor informado prevalece sobre o calculado");
    }

    @Test
    void deveEmitirOsTresSubgruposQuandoTodasAsFlagsEstaoAtivas() throws NfeException {
        TMonofasia mono = calcularMono(false, true, true, true);

        TMonofasia.GIBSMonoAdRem ibs = mono.getGIBSMonoAdRem();
        assertNotNull(ibs.getGMonoPadrao(), "gMonoPadrao deve sair com Padrao=true");
        assertNotNull(ibs.getGMonoRet(), "gMonoRet deve sair com RetidaAnt=true");
        assertNotNull(ibs.getGMonoReten(), "gMonoReten deve sair com SujeitaRetencao=true");

        TMonofasia.GCBSMonoAdRem cbs = mono.getGCBSMonoAdRem();
        assertNotNull(cbs.getGMonoPadrao(), "o eixo CBS acompanha as mesmas flags");
        assertNotNull(cbs.getGMonoRet(), "o eixo CBS acompanha as mesmas flags");
        assertNotNull(cbs.getGMonoReten(), "o eixo CBS acompanha as mesmas flags");
    }

    /**
     * Monta o grupo de monofasia para as flags informadas.
     * Parâmetros: adValorem, monofasiaPadrao, monofasiaRetidaAnt, monofasiaSujeitaRetencao.
     */
    private TMonofasia calcularMono(boolean adValorem, boolean padrao,
                                    boolean retidaAnt, boolean sujeitaRetencao) throws NfeException {
        CstDTO cst = montarCst("TST", false, false, false, true, false, false);
        ClassificacaoTributariaDTO ct = montarClassTrib(CC_TRIB, false, false, false);
        ct.setMonofasiaPadrao(padrao);
        ct.setMonofasiaRetidaAnt(retidaAnt);
        ct.setMonofasiaSujeitaRetencao(sujeitaRetencao);
        cst.setClassificacoesTributarias(Collections.singletonList(ct));

        IbsCbsUtil util = new IbsCbsUtil(Collections.singletonList(cst), DocumentoEnum.NFE);
        // As tres aliquotas regulares precisam ser nao-zero: e isso que permite detectar
        // uma regressao que as vaze para os campos monofasicos.
        util.setpAliqIbsUf(new BigDecimal("0.1"));
        util.setpAliqIbsMun(new BigDecimal("0.05"));
        util.setpAliqCbs(new BigDecimal("0.9"));
        util.setAnoEmissao(adValorem ? 2026 : 2029);

        TMonofasia mono = util.montaImpostosDet(CC_TRIB, criarDetMinimo("100.00", "1.0000")).getGIBSCBSMono();
        assertNotNull(mono, "IndIBSCBSMono=true deve emitir gIBSCBSMono");
        return mono;
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
