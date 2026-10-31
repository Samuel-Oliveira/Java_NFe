package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DANFE v2 (Thymeleaf) com as NF-e fictícias de homologação de src/test/resources:
 * DanfeV2Rtc.xml (2 itens, IBSCBS com e sem gRed, IS, duplicatas, transporte e QR Code)
 * e DanfeV2Rtc50Itens.xml (50 itens, para a quebra de página).
 */
class ImpressaoDanfeV2UtilTest {

    private static String xml;
    private static String xml50Itens;

    @BeforeAll
    static void leXmls() throws IOException {
        xml = XmlNfeUtil.leXml("src/test/resources/DanfeV2Rtc.xml");
        xml50Itens = XmlNfeUtil.leXml("src/test/resources/DanfeV2Rtc50Itens.xml");
    }

    @Test
    void gerarHtmlComDadosDaNota() {
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));

        assertTrue(html.contains("EMPRESA EXEMPLO LTDA"));
        assertTrue(html.contains("000.000.123"));
        assertTrue(html.contains("3526 1212 3456 7800 0195 5500 1000 0001 2310 0000 1234"), "chave em grupos de 4");
        assertTrue(html.contains("1.775,00"), "vNF");
        assertTrue(html.contains("TOTAL DO IBS / CBS / IS"));
        assertTrue(html.contains("SEM VALOR FISCAL"), "tpAmb=2 imprime a marca d'água");
        assertTrue(html.contains("data:image/png;base64,"), "logo, código de barras e QR Code em data URI");
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
        ImpressaoDanfeV2DTO retrato = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml50Itens);
        retrato.setOrientacao(OrientacaoDanfeEnum.RETRATO);
        assertEquals(3, folhas(ImpressaoDanfeV2Util.impressaoPdfByte(retrato)));

        ImpressaoDanfeV2DTO paisagem = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml50Itens);
        paisagem.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);
        assertEquals(4, folhas(ImpressaoDanfeV2Util.impressaoPdfByte(paisagem)));
    }

    @Test
    void dadosAdicionaisNoRodapeDaUltimaFolha() throws IOException {
        // 2 itens em retrato: o bloco sai do meio da folha e encosta no rodapé, como no DANFE Jasper
        byte[] retrato = ImpressaoDanfeV2Util.impressaoPdfByte(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));
        assertEquals(1, folhas(retrato));
        assertTrue(textoDaUltimaFolha(retrato, 0.80f, 1f).contains("RESERVADO AO FISCO"), "bloco nos últimos 20% da folha");
        assertFalse(textoDaUltimaFolha(retrato, 0.25f, 0.75f).contains("RESERVADO AO FISCO"), "bloco fora do meio da folha");

        // 50 itens em paisagem: mesmo número de folhas e o bloco no rodapé da última
        ImpressaoDanfeV2DTO paisagem = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml50Itens);
        paisagem.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);
        byte[] pdf50 = ImpressaoDanfeV2Util.impressaoPdfByte(paisagem);
        assertEquals(4, folhas(pdf50));
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
