package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.openhtmltopdf.util.XRLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DANFE v2 (Thymeleaf) com as NF-e fictícias de homologação de src/test/resources:
 * DanfeV2Rtc.xml (2 itens, IBSCBS com e sem gRed, IS, duplicatas, transporte e QR Code),
 * DanfeV2Rtc50Itens.xml (50 itens, para a quebra de página), DanfeV2Mono.xml (monofásico ad rem e ad valorem,
 * transferência de crédito e ajuste de competência) e DanfeV2SemRtc.xml (NF-e sem RTC e sem protocolo).
 */
class ImpressaoDanfeV2UtilTest {

    private static String xml;
    private static String xml50Itens;
    private static String xmlMono;
    private static String xmlSemRtc;

    @BeforeAll
    static void leXmls() throws IOException {
        // A biblioteca não mexe no log do openhtmltopdf; aqui só para não poluir a saída do build
        for (String logger : XRLog.listRegisteredLoggers()) {
            XRLog.setLevel(logger, Level.WARNING);
        }
        xml = XmlNfeUtil.leXml("src/test/resources/DanfeV2Rtc.xml");
        xml50Itens = XmlNfeUtil.leXml("src/test/resources/DanfeV2Rtc50Itens.xml");
        xmlMono = XmlNfeUtil.leXml("src/test/resources/DanfeV2Mono.xml");
        xmlSemRtc = XmlNfeUtil.leXml("src/test/resources/DanfeV2SemRtc.xml");
    }

    @Test
    void gerarHtmlComDadosDaNota() {
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));

        assertTrue(html.contains("EMPRESA EXEMPLO LTDA"));
        assertTrue(html.contains("000.000.123"));
        assertTrue(html.contains("3526 1212 3456 7800 0195 5500 1000 0001 2310 0000 1234"), "chave em grupos de 4");
        assertTrue(html.contains("1.775,00"), "vNF");
        assertTrue(html.contains("TOTAL DO IBS / CBS / IS"));
        assertTrue(html.contains("1.656,00"), "base de cálculo total do IBS/CBS");
        assertTrue(html.contains("IBS/CBS 000") && html.contains("IBS/CBS 200"), "CST do IBS/CBS de cada item");
        assertTrue(html.contains("SEM VALOR FISCAL"), "tpAmb=2 imprime a marca d'água");
        assertTrue(html.contains("data:image/png;base64,"), "logo, código de barras e QR Code em data URI");
        assertFalse(html.contains("null"), "nenhuma concatenação imprime o literal null");
    }

    @Test
    void aliquotaEfetivaQuandoHaGRed() {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(new XmlDanfeV2(xml));

        assertEquals(2, danfe.getItens().size());
        Map<String, String> item1 = danfe.getItens().get(0);
        Map<String, String> item2 = danfe.getItens().get(1);
        assertEquals("0.9000", item1.get("pCBS"), "sem gRed imprime a alíquota vigente");
        assertEquals("0.3600", item2.get("pCBS"), "com gRed imprime pAliqEfet (NT 2026.010 item 4.3)");
        assertEquals("0.0400", item2.get("pIBSUF"));
        assertEquals("0.9000", item2.get("pCBSVigente"));
        assertEquals("000", item1.get("cstIBSCBS"));
        assertEquals("200", item2.get("cstIBSCBS"));
        assertEquals("1.50", item1.get("vIBS"), "gIBSCBS/vIBS também fica disponível");
        assertNull(item1.get("qBCMono"), "item gIBSCBS não tem chaves do monofásico");
    }

    @Test
    void itensMonofasicosTransferenciaEAjusteCoerentesComOTotal() throws IOException {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(new XmlDanfeV2(xmlMono));

        assertEquals(4, danfe.getItens().size());
        Map<String, String> adRem = danfe.getItens().get(0);
        Map<String, String> adValorem = danfe.getItens().get(1);
        Map<String, String> transferencia = danfe.getItens().get(2);
        Map<String, String> ajuste = danfe.getItens().get(3);

        // Ad rem (CST 620): base em quantidade, alíquota em R$/unidade, valor sem abertura UF/Município
        assertEquals("620", adRem.get("cstIBSCBS"));
        assertNull(adRem.get("vBCIBSCBS"));
        assertEquals("1000.0000", adRem.get("qBCMono"));
        assertEquals("0.0500", adRem.get("adRemIBS"));
        assertEquals("0.2000", adRem.get("adRemCBS"));
        assertEquals("50.00", adRem.get("vIBS"));
        assertEquals("200.00", adRem.get("vCBS"));
        assertNull(adRem.get("vIBSUF"));
        assertNull(adRem.get("pIBSUF"));
        assertEquals("10.00", adRem.get("vIBSMonoReten"));
        assertEquals("30.00", adRem.get("vCBSMonoReten"));
        assertEquals("60.00", adRem.get("vTotIBSMonoItem"));

        // Ad valorem: entra nas mesmas colunas do gIBSCBS (base em R$ e alíquota em %)
        assertEquals("5000.00", adValorem.get("vBCIBSCBS"));
        assertEquals("0.1000", adValorem.get("pIBSUF"));
        assertEquals("5.00", adValorem.get("vIBSUF"));
        assertEquals("0.0000", adValorem.get("pIBSMun"));
        assertEquals("0.9000", adValorem.get("pCBS"));
        assertEquals("45.00", adValorem.get("vCBS"));
        assertEquals("1.00", adValorem.get("vIBSMonoRet"));
        assertEquals("2.00", adValorem.get("vCBSMonoRet"));
        assertNull(adValorem.get("adRemIBS"));

        // Transferência de crédito (CST 800) e ajuste de competência (CST 811): só valores
        assertEquals("800", transferencia.get("cstIBSCBS"));
        assertEquals("10.00", transferencia.get("vIBS"));
        assertEquals("30.00", transferencia.get("vCBS"));
        assertEquals("811", ajuste.get("cstIBSCBS"));
        assertEquals("1.00", ajuste.get("vIBS"));
        assertEquals("3.00", ajuste.get("vCBS"));
        assertEquals("2026-11", ajuste.get("competApur"));

        // Totais: o quadro de IBS/CBS/IS e a linha de monofasia saem com os valores do XML
        assertTrue(danfe.isExibirIbsCbsIs());
        assertTrue(danfe.isExibirLinhaIbsCbsMono());
        assertEquals("55.00", danfe.getIbsCbsTot().get("vIBSMono"));
        assertEquals("245.00", danfe.getIbsCbsTot().get("vCBSMono"));

        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xmlMono);
        String html = ImpressaoDanfeV2Util.gerarHtml(impressao);
        assertTrue(html.contains("VALOR DO IBS MONOFÁSICO"));
        assertTrue(html.contains("IBS/CBS QTD") && html.contains("1.000,00"), "base em quantidade do ad rem");
        assertTrue(html.contains("0,0500") && html.contains("0,2000"), "alíquotas ad rem");
        assertTrue(html.contains("IBS/CBS 620") && html.contains("IBS/CBS 800") && html.contains("IBS/CBS 811"));
        assertTrue(html.contains("Retenção monofásica IBS 10,00 CBS 30,00"));
        assertTrue(html.contains("Retido anteriormente IBS 1,00 CBS 2,00"));
        assertTrue(html.contains("Ajuste de competência 11/2026"));
        assertFalse(html.contains("null"));
        assertEquals(1, folhas(ImpressaoDanfeV2Util.impressaoPdfByte(impressao)));
    }

    @Test
    void notaSemGruposOpcionaisNaoImprimeQuadrosFacultativos() throws IOException {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(new XmlDanfeV2(xmlSemRtc));

        assertFalse(danfe.isExibirIbsCbsIs());
        assertFalse(danfe.isExibirQrCode());
        assertFalse(danfe.isExibirFatura());
        assertFalse(danfe.isExibirTransporte(), "só modFrete não basta");
        assertFalse(danfe.isExibirIssqn());
        assertTrue(danfe.getProt().isEmpty());
        assertEquals("35261212345678000195550010000004561000004567", danfe.getChave(), "sem protNFe a chave vem do infNFe/@Id");
        assertEquals("102", danfe.getItens().get(0).get("CST"), "CSOSN no lugar do CST");
        assertNull(danfe.getItens().get(0).get("cstIBSCBS"));

        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xmlSemRtc);
        impressao.removerLogo();
        String html = ImpressaoDanfeV2Util.gerarHtml(impressao);
        assertFalse(html.contains("TOTAL DO IBS / CBS / IS"));
        assertFalse(html.contains("QR CODE"));
        assertFalse(html.contains("FATURA / DUPLICATAS"));
        assertFalse(html.contains("TRANSPORTADOR / VOLUMES"));
        assertFalse(html.contains("class=\"logo\""), "removerLogo: o template não gera o bloco do logo");
        assertFalse(html.contains("null"), "nenhuma concatenação imprime o literal null");
        assertTrue(html.contains("1 - SIMPLES NACIONAL"));
        assertEquals(1, folhas(ImpressaoDanfeV2Util.impressaoPdfByte(impressao)));
    }

    @Test
    void blocosFacultativos() {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(new XmlDanfeV2(xml));

        assertTrue(danfe.isHomologacao());
        assertFalse(danfe.isPaisagem(), "tpImp=1");
        assertTrue(danfe.isExibirIbsCbsIs());
        assertTrue(danfe.isExibirQrCode());
        assertTrue(danfe.isExibirFatura());
        assertTrue(danfe.isExibirTransporte());
        assertFalse(danfe.isExibirLinhaFcp(), "vFCP/vFCPST vêm 0.00 em toda NF-e 4.00: a linha não sai");
        assertFalse(danfe.isExibirLinhaIcmsMono());
        assertFalse(danfe.isExibirLinhaIbsCbsMono());
    }

    @Test
    void pdfRetratoEPaisagemEmUmaFolha() throws IOException {
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        assertEquals(1, folhas(ImpressaoDanfeV2Util.impressaoPdfByte(impressao)));

        impressao.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);
        byte[] paisagem = ImpressaoDanfeV2Util.impressaoPdfByte(impressao);
        assertEquals(1, folhas(paisagem));
        try (PDDocument documento = PDDocument.load(paisagem)) {
            PDRectangle folha = documento.getPage(0).getMediaBox();
            assertTrue(folha.getWidth() > folha.getHeight(), "folha em paisagem");
        }
    }

    @Test
    void quebraDePaginaCom50Itens() throws IOException {
        // O que importa é quebrar em várias folhas, repetir o cabeçalho e não cortar item; a contagem exata
        // (hoje 3 em retrato e 4 em paisagem) muda a cada ajuste de CSS e não é o contrato
        ImpressaoDanfeV2DTO retrato = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml50Itens);
        retrato.setOrientacao(OrientacaoDanfeEnum.RETRATO);
        byte[] pdfRetrato = ImpressaoDanfeV2Util.impressaoPdfByte(retrato);
        assertTrue(folhas(pdfRetrato) >= 3, "retrato em várias folhas");
        assertTrue(textoDaUltimaFolha(pdfRetrato, 0f, 0.25f).contains("DANFE"), "cabeçalho repetido na última folha");
        assertTrue(textoDaUltimaFolha(pdfRetrato, 0f, 1f).contains("DISJUNTOR TERMOMAGNETICO"), "item 50 na última folha");

        ImpressaoDanfeV2DTO paisagem = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml50Itens);
        paisagem.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);
        byte[] pdfPaisagem = ImpressaoDanfeV2Util.impressaoPdfByte(paisagem);
        assertTrue(folhas(pdfPaisagem) > folhas(pdfRetrato), "paisagem cabe menos itens por folha");
        assertTrue(textoDaUltimaFolha(pdfPaisagem, 0f, 0.25f).contains("DANFE"));
    }

    @Test
    void dadosAdicionaisNoRodapeDaUltimaFolha() throws IOException {
        // 2 itens em retrato: o bloco sai do meio da folha e encosta no rodapé, como no DANFE Jasper
        byte[] retrato = ImpressaoDanfeV2Util.impressaoPdfByte(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));
        assertEquals(1, folhas(retrato));
        assertTrue(textoDaUltimaFolha(retrato, 0.80f, 1f).contains("RESERVADO AO FISCO"), "bloco nos últimos 20% da folha");
        assertFalse(textoDaUltimaFolha(retrato, 0.25f, 0.75f).contains("RESERVADO AO FISCO"), "bloco fora do meio da folha");

        // 50 itens em paisagem: várias folhas e o bloco no rodapé da última
        ImpressaoDanfeV2DTO paisagem = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml50Itens);
        paisagem.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);
        byte[] pdf50 = ImpressaoDanfeV2Util.impressaoPdfByte(paisagem);
        assertTrue(folhas(pdf50) >= 3);
        assertTrue(textoDaUltimaFolha(pdf50, 0.80f, 1f).contains("RESERVADO AO FISCO"));

        // Sem os marcadores (template próprio), o PDF continua saindo, com o bloco logo após os itens
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml))
                .replace("id=\"" + ImpressaoDanfeV2Util.ID_ESPACO_DADOS_ADICIONAIS + "\"", "");
        byte[] semMarcador = ImpressaoDanfeV2Util.htmlParaPdf(html);
        assertEquals(1, folhas(semMarcador));
        assertTrue(textoDaUltimaFolha(semMarcador, 0.25f, 0.75f).contains("RESERVADO AO FISCO"));
    }

    @Test
    void xmlComBomEhAceito() {
        assertDoesNotThrow(() -> ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe("\uFEFF" + xml)));
    }

    @Test
    void errosViramDanfeException() {
        assertThrows(DanfeException.class, () -> ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(null)));
        assertThrows(DanfeException.class, () -> ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe("<nfeProc/>")));

        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        impressao.setTemplate("d:/nao/existe/danfe.html");
        DanfeException erro = assertThrows(DanfeException.class, () -> ImpressaoDanfeV2Util.gerarHtml(impressao));
        assertTrue(erro.getMessage().startsWith("Erro ao processar o template"));

        // Caminho inválido (InvalidPathException no Windows, NoSuchFileException nos demais): sempre DanfeException
        ImpressaoDanfeV2DTO valida = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        assertThrows(DanfeException.class, () -> ImpressaoDanfeV2Util.impressaoHtml(valida, "nao/existe/da|nfe\0.html"));
        assertThrows(DanfeException.class, () -> ImpressaoDanfeV2Util.impressaoPdfArquivo(valida, "nao/existe/da|nfe\0.pdf"));
    }

    @Test
    void logo() {
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        assertNotNull(impressao.getLogo(), "logo padrão /img/nfe.png");

        impressao.removerLogo();
        assertNull(impressao.getLogo());

        impressao.setLogoArquivo("src/main/resources/img/nfe.png");
        assertNotNull(impressao.getLogo());

        impressao.setLogoArquivo(null);
        assertNull(impressao.getLogo());

        assertThrows(DanfeException.class, () -> impressao.setLogoArquivo("d:/nao/existe/logo.png"));
    }

    @Test
    void formatador() {
        FormatadorDanfeV2 fmt = new FormatadorDanfeV2();

        assertEquals("1.234,50", fmt.valor("1234.5"));
        assertEquals("1,01", fmt.valor("1.005"), "HALF_UP, não HALF_EVEN");
        assertEquals("18,00%", fmt.aliquota("18.00"));
        assertEquals("12.345.678/0001-95", fmt.documento("12345678000195"));
        assertEquals("000.000.123", fmt.numeroNota("123"));
        assertEquals("001", fmt.serie("1"));
        assertEquals("25/12/2026 10:30:00", fmt.dataHora("2026-12-25T10:30:00-03:00"));
        assertEquals("000", fmt.cst("0", "00"));
        assertEquals("", fmt.valor(null));
        assertEquals("0,0500", fmt.adRem("0.0500"));
        assertEquals("11/2026", fmt.competencia("2026-11"));
        assertEquals("", fmt.complementoIbsCbs(null));
    }

    private static int folhas(byte[] pdf) throws IOException {
        try (PDDocument documento = PDDocument.load(pdf)) {
            return documento.getNumberOfPages();
        }
    }

    /** Texto da faixa horizontal da última folha entre as duas frações da altura (0 = topo, 1 = pé da folha). */
    private static String textoDaUltimaFolha(byte[] pdf, float de, float ate) throws IOException {
        try (PDDocument documento = PDDocument.load(pdf)) {
            PDPage ultima = documento.getPage(documento.getNumberOfPages() - 1);
            PDRectangle folha = ultima.getMediaBox();
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.addRegion("faixa", new Rectangle2D.Float(0, folha.getHeight() * de,
                    folha.getWidth(), folha.getHeight() * (ate - de)));
            stripper.extractRegions(ultima);
            return stripper.getTextForRegion("faixa");
        }
    }
}
