package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Reader;
import com.openhtmltopdf.util.XRLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Impressão que substituiu o Jasper: DANFE NFC-e (src/test/resources/DanfeNfce.xml, NFC-e fictícia de homologação
 * com consumidor CPF e PIX), Carta de Correção eletrônica (CartaCorrecao.xml, procEventoNFe fictício) e o código de
 * barras da chave, que passou do barbecue para o zxing.
 */
class ImpressaoNfceECceTest {

    private static final float PONTOS_POR_MM = 72f / 25.4f;
    private static final String CHAVE_CCE = "35261212345678000195550010000001231000001234";

    private static String nfce;
    private static String cce;

    @BeforeAll
    static void leXmls() throws IOException {
        for (String logger : XRLog.listRegisteredLoggers()) {
            XRLog.setLevel(logger, Level.WARNING);
        }
        nfce = XmlNfeUtil.leXml("src/test/resources/DanfeNfce.xml");
        cce = XmlNfeUtil.leXml("src/test/resources/CartaCorrecao.xml");
    }

    @Test
    void nfceSaiNoTemplateDeBobinaComOsTextosDaNfce() {
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFCe(nfce);
        assertNotNull(impressao.getLogo(), "logo padrão /img/nfce.png");

        String html = ImpressaoDanfeV2Util.gerarHtml(impressao);

        assertTrue(html.contains("Documento Auxiliar da Nota Fiscal de Consumidor Eletrônica"), "Divisão I da NFC-e");
        assertFalse(html.contains(">DANFE Simplificado Tipo 2</div>"), "título do Tipo 2 (o comentário do template também cita)");
        assertTrue(html.contains(">NFC-e</span>") && html.contains("nº 000.000.321") && html.contains("Série 001"));
        assertTrue(html.contains("CONSUMIDOR CPF:") && html.contains("123.456.789-09"));
        assertTrue(html.contains(">PIX</td>"), "tPag 17");
        assertTrue(html.contains("3526 1212 3456 7800 0195 6500 1000 0003 2110 0000 3210"));
        assertTrue(html.contains("Protocolo de autorização") && html.contains("135260000032101"));
        assertTrue(html.contains("EMITIDA EM AMBIENTE DE HOMOLOGAÇÃO - SEM VALOR FISCAL"));
        assertFalse(html.contains("null"));
    }

    @Test
    void nfceEmContingenciaOffLine() {
        String contingencia = nfce.replace("<tpEmis>1</tpEmis>", "<tpEmis>9</tpEmis>")
                .replaceAll("(?s)<protNFe.*</protNFe>", "");
        assertNotEquals(nfce, contingencia);

        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFCe(contingencia));

        assertTrue(html.contains("EMITIDA EM CONTINGÊNCIA"));
        assertTrue(html.contains("Via do Consumidor"));
        assertFalse(html.contains("Protocolo de autorização"));
    }

    @Test
    void nfceEmPdfNumaFolhaDaLarguraDaBobina() throws IOException {
        byte[] pdf = ImpressaoDanfeV2Util.impressaoPdfByte(ImpressaoDanfeV2Util.impressaoPadraoNFCe(nfce));

        try (PDDocument documento = PDDocument.load(pdf)) {
            assertEquals(1, documento.getNumberOfPages());
            PDRectangle folha = documento.getPage(0).getMediaBox();
            assertEquals(80f, folha.getWidth() / PONTOS_POR_MM, 0.5f);
            assertTrue(folha.getHeight() / PONTOS_POR_MM < 300, "altura do cupom, não a máxima");
        }
    }

    @Test
    void ccePadrao() {
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoCCe(cce));

        assertTrue(html.contains("CARTA DE CORREÇÃO ELETRÔNICA"));
        assertTrue(html.contains("NF-e nº 000.000.123 - SÉRIE 001"), "número e série tirados da chave");
        assertTrue(html.contains("3526 1212 3456 7800 0195 5500 1000 0001 2310 0000 1234"), "chave inteira, em 11 blocos");
        assertTrue(html.contains("12.345.678/0001-95"), "emitente");
        assertTrue(html.contains("98.765.432/0001-10"), "destinatário do retorno do evento");
        assertTrue(html.contains("02/12/2026 09:30:00") && html.contains("02/12/2026 09:30:05"), "evento e registro");
        assertTrue(html.contains("135260000099999"));
        assertTrue(html.contains("135 - Evento registrado e vinculado a NF-e"));
        assertTrue(html.contains("leia-se \"Volume: 11 caixas\"") || html.contains("leia-se &quot;Volume: 11 caixas&quot;"),
                "texto da correção");
        assertTrue(html.contains("A Carta de Correcao e disciplinada"), "condições de uso do XML");
        assertTrue(html.contains("SEM VALOR FISCAL"), "tpAmb=2");
        assertTrue(html.contains("data:image/png;base64,"), "código de barras da chave");
        assertFalse(html.contains("class=\"logo\""), "sem logo por padrão");
        assertFalse(html.contains("null"));
    }

    @Test
    void leitorDoEvento() {
        EventoV2DTO evento = LeitorEventoV2.ler(new XmlDanfeV2(cce));

        assertTrue(evento.isCartaCorrecao());
        assertEquals(CHAVE_CCE, evento.getChave());
        assertEquals("55", evento.getModelo());
        assertEquals("001", evento.getSerie());
        assertEquals("000000123", evento.getNumero());
        assertEquals("2", evento.getEvento().get("nSeqEvento"));
        assertEquals("ID1101103526121234567800019555001000000123100000123402", evento.getEvento().get("Id"));
        assertEquals("135", evento.getRetEvento().get("cStat"), "cStat do retEvento, não do evento");
        assertTrue(evento.isHomologacao());
    }

    @Test
    void ccePdfEmA4() throws IOException {
        byte[] pdf = ImpressaoDanfeV2Util.impressaoPdfByte(ImpressaoDanfeV2Util.impressaoPadraoCCe(cce));

        try (PDDocument documento = PDDocument.load(pdf)) {
            assertEquals(1, documento.getNumberOfPages());
            assertEquals(210f, documento.getPage(0).getMediaBox().getWidth() / PONTOS_POR_MM, 0.5f);
        }
    }

    @Test
    void eventoSemRetornoSaiSemProtocolo() {
        String semRetorno = cce.replaceAll("(?s)<retEvento.*</retEvento>", "");
        assertNotEquals(cce, semRetorno);

        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoCCe(semRetorno));

        assertFalse(html.contains("135260000099999"));
        assertTrue(html.contains("SEM VALOR FISCAL"), "sem protocolo de registro");
        assertFalse(html.contains("null"));
    }

    @Test
    void outrosEventosSoComTemplateProprio() {
        String cancelamento = cce.replace("<tpEvento>110110</tpEvento>", "<tpEvento>110111</tpEvento>");

        DanfeException erro = assertThrows(DanfeException.class,
                () -> ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoCCe(cancelamento)));
        assertTrue(erro.getMessage().contains("110111"));

        ImpressaoDanfeV2DTO comTemplate = ImpressaoDanfeV2Util.impressaoPadraoCCe(cancelamento);
        comTemplate.setTemplate(ImpressaoDanfeV2Util.TEMPLATE_CCE);
        assertDoesNotThrow(() -> ImpressaoDanfeV2Util.gerarHtml(comTemplate), "template informado vale para qualquer evento");
    }

    @Test
    void codigoDeBarrasDaChaveEmCode128C() throws Exception {
        BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(ImagemDanfeV2Util.codigoBarras128(CHAVE_CCE)));

        // Conjunto C: início + 22 pares de dígitos + verificador (11 módulos cada) + parada (13) = 277 módulos de 3 px
        assertEquals(277 * 3, imagem.getWidth());
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(imagem)));
        assertEquals(CHAVE_CCE, new Code128Reader().decode(bitmap).getText(), "leitura de volta pelo zxing");
    }
}
