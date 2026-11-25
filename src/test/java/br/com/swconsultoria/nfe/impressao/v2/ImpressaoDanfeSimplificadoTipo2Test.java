package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.openhtmltopdf.util.XRLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DANFE Simplificado Tipo 2 (tpImp=6, NT 2026.003) com a NF-e fictícia de homologação
 * src/test/resources/DanfeSimplificadoTipo2.xml: 3 itens, outras despesas e desconto, IBS/CBS e IS, cartão e
 * dinheiro com troco, destinatário CNPJ com endereço, vTotTrib, infAdFisco, infCpl e protocolo.
 */
class ImpressaoDanfeSimplificadoTipo2Test {

    private static final float PONTOS_POR_MM = 72f / 25.4f;

    private static String xml;

    @BeforeAll
    static void leXml() throws IOException {
        for (String logger : XRLog.listRegisteredLoggers()) {
            XRLog.setLevel(logger, Level.WARNING);
        }
        xml = XmlNfeUtil.leXml("src/test/resources/DanfeSimplificadoTipo2.xml");
    }

    @Test
    void tpImp6UsaOTemplateSimplificadoComAsDivisoesDaNt() {
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));

        assertTrue(html.contains("DANFE Simplificado Tipo 2"), "Divisão I");
        assertTrue(html.contains("CNPJ: 12.345.678/0001-95"));
        assertTrue(html.contains("Rua Exemplo, 100 - Sala 2, Centro, São Paulo - SP, CEP 01001-000"), "endereço sem o país");
        assertFalse(html.contains("DADOS DOS PRODUTOS / SERVIÇOS"), "não é o DANFE A4");
        assertTrue(html.contains("OLEO LUBRIFICANTE SINTETICO"), "Divisão II");
        assertTrue(html.contains("22,272 LT"));
        assertTrue(html.contains("Consulte pela Chave de Acesso em"), "Divisão IV");
        assertTrue(html.contains("https://www.homologacao.nfce.fazenda.sp.gov.br/consulta"), "urlChave do infNFeSupl");
        assertTrue(html.contains("3526 1212 3456 7800 0195 5500 2000 0007 8910 0000 7890"), "chave em 11 blocos");
        assertTrue(html.contains("class=\"qr-lateral\""), "Divisão V: 80 mm, QR Code ao lado");
        assertTrue(html.contains("CONSUMIDOR CNPJ:") && html.contains("98.765.432/0001-10"), "Divisão VI");
        assertTrue(html.contains("Avenida Cliente, 200, Jardim Industrial, São Paulo - SP, CEP 01310-000"));
        assertTrue(html.contains("nº 000.000.789") && html.contains("Série 002") && html.contains("01/12/2026 15:03:53"), "Divisão VII");
        assertTrue(html.contains("135260000078901") && html.contains("01/12/2026 15:04:02"), "protocolo e data de autorização");
        assertTrue(html.contains("EMITIDA EM AMBIENTE DE HOMOLOGAÇÃO - SEM VALOR FISCAL"), "Divisão VIII");
        assertTrue(html.contains("Mensagem de interesse do Fisco."), "infAdFisco");
        assertTrue(html.contains("Tributos Totais Incidentes (Lei Federal 12.741/2012): R$ 52,70"), "Divisão IX");
        assertTrue(html.contains("Obrigado pela preferência. Volte sempre!"), "infCpl");
        assertFalse(html.contains("CONTINGÊNCIA"), "autorizada em emissão normal");
        assertFalse(html.contains("Via do"));
        assertFalse(html.contains("null"), "nenhuma concatenação imprime o literal null");
    }

    @Test
    void totaisTributosEPagamento() {
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));

        assertEquals("3", valorDaLinha(html, "Qtde. total de itens"), "itens distintos, não a soma das quantidades");
        assertEquals("200,28", valorDaLinha(html, "Valor total R$"));
        assertEquals("2,00", valorDaLinha(html, "Outras despesas R$"));
        assertEquals("-8,00", valorDaLinha(html, "Desconto R$"));
        assertNull(valorDaLinha(html, "Frete R$"), "frete zero não imprime a linha");
        assertEquals("194,28", valorDaLinha(html, "Valor a Pagar R$"));
        assertEquals("1,75", valorDaLinha(html, "(+) CBS R$"));
        assertEquals("0,20", valorDaLinha(html, "(+) IBS R$"));
        assertEquals("0,78", valorDaLinha(html, "(+) IS R$"));
        assertEquals("100,00", valorDaLinha(html, "Cartão de Crédito"));
        assertEquals("100,00", valorDaLinha(html, "Dinheiro"));
        assertEquals("5,72", valorDaLinha(html, "Troco R$"));
    }

    @Test
    void linhasOpcionaisSaemSoComValor() {
        String semAcrescimo = xml.replace("<vDesc>8.00</vDesc>", "<vDesc>0.00</vDesc>")
                .replace("<vOutro>2.00</vOutro>", "<vOutro>0.00</vOutro>")
                .replaceAll("<ISTot>\\s*<vIS>0.78</vIS>\\s*</ISTot>", "")
                .replace("<vTroco>5.72</vTroco>", "");
        assertNotEquals(xml, semAcrescimo);

        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(semAcrescimo));

        assertNull(valorDaLinha(html, "Desconto R$"));
        assertNull(valorDaLinha(html, "Outras despesas R$"));
        assertNull(valorDaLinha(html, "Valor a Pagar R$"), "só com acréscimo ou desconto (item 3.1.3)");
        assertNull(valorDaLinha(html, "(+) IS R$"), "o IS só sai se houver (item 3.1.4)");
        assertEquals("1,75", valorDaLinha(html, "(+) CBS R$"));
        assertEquals("0,00", valorDaLinha(html, "Troco R$"), "o troco é obrigatório, mesmo sem vTroco no XML");
    }

    @Test
    void contingenciaPendenteImprimeOAvisoNosDoisLugaresESemProtocolo() {
        String contingencia = contingencia(xml);

        String via1 = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(contingencia));
        assertEquals(2, ocorrencias(via1, "EMITIDA EM CONTINGÊNCIA"), "abaixo do cabeçalho e da identificação (item 3.1.9)");
        assertEquals(2, ocorrencias(via1, "Pendente de autorização"));
        assertTrue(via1.contains("Via do Consumidor"));
        assertFalse(via1.contains("Protocolo de autorização"), "contingência suprime o protocolo (item 3.1.8)");

        ImpressaoDanfeV2DTO estabelecimento = ImpressaoDanfeV2Util.impressaoPadraoNFe(contingencia);
        estabelecimento.setViaEstabelecimento(true);
        String via2 = ImpressaoDanfeV2Util.gerarHtml(estabelecimento);
        assertTrue(via2.contains("Via do Estabelecimento"));
        assertFalse(via2.contains("Via do Consumidor"));

        String autorizadaDepois = xml.replace("<tpEmis>1</tpEmis>", "<tpEmis>9</tpEmis>");
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(autorizadaDepois));
        assertFalse(html.contains("CONTINGÊNCIA"), "com o protocolo, a contingência deixa de estar pendente");
        assertTrue(html.contains("Protocolo de autorização"));
    }

    @Test
    void consumidorNaoIdentificadoOuPorCpf() {
        String semDest = xml.replaceAll("(?s)<dest>.*</dest>", "");
        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(semDest));
        assertTrue(html.contains("CONSUMIDOR NÃO IDENTIFICADO"));
        assertFalse(html.contains("null"));

        String cpf = xml.replace("<CNPJ>98765432000110</CNPJ>", "<CPF>12345678909</CPF>");
        String htmlCpf = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(cpf));
        assertTrue(Pattern.compile("CONSUMIDOR CPF:</span>\\s*<span class=\"negrito nw\">123\\.456\\.789-09<")
                .matcher(htmlCpf).find(), "rótulo e número do CPF");
    }

    @Test
    void producaoSemProtocoloEhPreVisualizacao() {
        String producao = xml.replace("<tpAmb>2</tpAmb>", "<tpAmb>1</tpAmb>")
                .replaceAll("(?s)<protNFe.*</protNFe>", "");

        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(producao));

        assertTrue(html.contains("PRÉ-VISUALIZAÇÃO - SEM VALOR FISCAL"));
        assertFalse(html.contains("AMBIENTE DE HOMOLOGAÇÃO"));
        assertFalse(html.contains("CONTINGÊNCIA"));
    }

    @Test
    void papelEstreitoCentralizaOQrCode() {
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        impressao.setLarguraPapelMm(58);

        String html = ImpressaoDanfeV2Util.gerarHtml(impressao);

        assertTrue(html.contains("class=\"qr-centro\""), "figura 1B");
        assertFalse(html.contains("class=\"qr-lateral\""));
        assertTrue(html.contains("<body class=\"estreito\">"));
        assertTrue(html.contains("size: 58mm"));
    }

    @Test
    void larguraMinimaDoPapel() {
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        assertEquals(80, impressao.getLarguraPapelMm(), "padrão");
        assertThrows(DanfeException.class, () -> impressao.setLarguraPapelMm(55), "NT 2026.003 item 3.3: 56 mm");
        impressao.setLarguraPapelMm(56);
        assertEquals(56, impressao.getLarguraPapelMm());
    }

    @Test
    void templatePadraoImprimeODanfeA4DeUmaNotaTipo2() {
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        impressao.setTemplate(ImpressaoDanfeV2Util.TEMPLATE_PADRAO);

        String html = ImpressaoDanfeV2Util.gerarHtml(impressao);

        assertTrue(html.contains("DADOS DOS PRODUTOS / SERVIÇOS"));
        assertFalse(html.contains("DANFE Simplificado Tipo 2"));
    }

    @Test
    void pdfNumaFolhaDoTamanhoDoCupom() throws IOException {
        byte[] pdf = ImpressaoDanfeV2Util.impressaoPdfByte(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml));

        try (PDDocument documento = PDDocument.load(pdf)) {
            assertEquals(1, documento.getNumberOfPages());
            PDRectangle folha = documento.getPage(0).getMediaBox();
            assertEquals(80f, folha.getWidth() / PONTOS_POR_MM, 0.5f, "largura da bobina");
            float alturaMm = folha.getHeight() / PONTOS_POR_MM;
            assertTrue(alturaMm > 100 && alturaMm < 300, "altura do conteúdo, não a máxima: " + alturaMm);
            assertTrue(textoDaFaixa(documento.getPage(0), 0.9f, 1f).contains("Volte sempre"),
                    "a última linha fica no pé da folha, sem sobra em branco");
        }

        ImpressaoDanfeV2DTO estreito = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        estreito.setLarguraPapelMm(58);
        try (PDDocument documento = PDDocument.load(ImpressaoDanfeV2Util.impressaoPdfByte(estreito))) {
            assertEquals(1, documento.getNumberOfPages());
            assertEquals(58f, documento.getPage(0).getMediaBox().getWidth() / PONTOS_POR_MM, 0.5f);
            String texto = new PDFTextStripper().getText(documento);
            assertTrue(texto.contains("3526 1212 3456 7800 0195 5500 2000 0007 8910 0000 7890"),
                    "a chave cabe numa linha no papel de 58 mm");
        }
    }

    @Test
    void muitosItensContinuamNumaFolhaSo() throws IOException {
        Matcher det = Pattern.compile("(?s)<det nItem=\"2\">.*?</det>").matcher(xml);
        assertTrue(det.find());
        StringBuilder itens = new StringBuilder();
        for (int i = 2; i <= 80; i++) {
            itens.append(det.group().replace("<det nItem=\"2\">", "<det nItem=\"" + i + "\">"));
        }
        String xml80Itens = xml.replace(det.group(), itens.toString());

        byte[] pdf = ImpressaoDanfeV2Util.impressaoPdfByte(ImpressaoDanfeV2Util.impressaoPadraoNFe(xml80Itens));

        try (PDDocument documento = PDDocument.load(pdf)) {
            assertEquals(1, documento.getNumberOfPages(), "bobina não quebra folha");
            float alturaMm = documento.getPage(0).getMediaBox().getHeight() / PONTOS_POR_MM;
            assertTrue(alturaMm > 400 && alturaMm < ImpressaoDanfeV2Util.ALTURA_MAXIMA_BOBINA_MM, "altura: " + alturaMm);
            assertTrue(textoDaFaixa(documento.getPage(0), 0.95f, 1f).contains("Volte sempre"));
        }
    }

    @Test
    void leitorDoPagamentoEDosTributos() {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(new XmlDanfeV2(xml));

        assertTrue(danfe.isSimplificadoTipo2());
        assertEquals(2, danfe.getPagamentos().size());
        assertEquals("03", danfe.getPagamentos().get(0).get("tPag"));
        assertEquals("100.00", danfe.getPagamentos().get(1).get("vPag"));
        assertEquals("5.72", danfe.getPag().get("vTroco"));
        assertEquals("1", danfe.getIde().get("indPres"));
        assertEquals("1.75", danfe.getCbsTotal());
        assertEquals("0.20", danfe.getIbsTotal());
        assertTrue(danfe.isExibirAcrescimoDesconto());
        assertTrue(danfe.isExibirLinhaIs());
        assertTrue(danfe.isExibirTributosTotais());
        assertTrue(danfe.isAmbienteHomologacao());
        assertFalse(danfe.isContingenciaPendente());
        assertFalse(danfe.isPreVisualizacao());
    }

    @Test
    void ibsECbsTotaisSomamOMonofasico() throws IOException {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(new XmlDanfeV2(XmlNfeUtil.leXml("src/test/resources/DanfeV2Mono.xml")));

        BigDecimal ibs = new BigDecimal(danfe.getIbsCbsTot().get("vIBS")).add(new BigDecimal(danfe.getIbsCbsTot().get("vIBSMono")));
        BigDecimal cbs = new BigDecimal(danfe.getIbsCbsTot().get("vCBS")).add(new BigDecimal(danfe.getIbsCbsTot().get("vCBSMono")));
        assertEquals(0, ibs.compareTo(new BigDecimal(danfe.getIbsTotal())));
        assertEquals(0, cbs.compareTo(new BigDecimal(danfe.getCbsTotal())));
        assertNull(new DanfeV2DTO().getCbsTotal(), "sem IBSCBSTot");
    }

    @Test
    void cnpjAlfanumericoSaiComAsLetras() {
        // CNPJ alfanumérico do emitente na tag e dentro da chave (Id, chNFe e QR Code); o DV não é conferido na impressão
        String alfanumerico = xml.replace("12345678000195", "12ABC345000195");
        assertNotEquals(xml, alfanumerico);

        String html = ImpressaoDanfeV2Util.gerarHtml(ImpressaoDanfeV2Util.impressaoPadraoNFe(alfanumerico));

        assertTrue(html.contains("12.ABC.345/0001-95"), "CNPJ do emitente com a máscara de CNPJ");
        assertTrue(html.contains("3526 1212 ABC3 4500 0195 5500 2000 0007 8910 0000 7890"), "chave com as letras");
    }

    @Test
    void formatador() {
        FormatadorDanfeV2 fmt = new FormatadorDanfeV2();

        assertEquals("Dinheiro", fmt.formaPagamento("01", null));
        assertEquals("PIX", fmt.formaPagamento("17", null));
        assertEquals("Outros", fmt.formaPagamento("99", null));
        assertEquals("Vale Gás", fmt.formaPagamento("99", "Vale Gás"), "99 usa o xPag");
        assertEquals("77", fmt.formaPagamento("77", null), "código desconhecido devolve o código");
        assertEquals("", fmt.formaPagamento(null, null));

        assertEquals("CONSUMIDOR NÃO IDENTIFICADO", fmt.consumidor(null));
        assertEquals("CONSUMIDOR ID. ESTRANGEIRO:", fmt.consumidor(java.util.Collections.singletonMap("idEstrangeiro", "AB123")));

        java.util.Map<String, String> endereco = new java.util.HashMap<>();
        endereco.put("xMun", "Goiânia");
        endereco.put("UF", "GO");
        endereco.put("CEP", "74000000");
        assertEquals("Goiânia - GO, CEP 74000-000", fmt.enderecoCompleto(endereco), "sem logradouro não começa com vírgula");
        assertEquals("", fmt.enderecoCompleto(null));

        assertTrue(fmt.naoZero("0.01"));
        assertFalse(fmt.naoZero("0.00"));
        assertFalse(fmt.naoZero(null));
    }

    /** NF-e do XML de teste emitida em contingência off-line e ainda sem protocolo. */
    private static String contingencia(String xml) {
        String semProtocolo = xml.replace("<tpEmis>1</tpEmis>", "<tpEmis>9</tpEmis>")
                .replaceAll("(?s)<protNFe.*</protNFe>", "");
        assertNotEquals(xml, semProtocolo);
        return semProtocolo;
    }

    /** Valor da linha rótulo/valor das tabelas de totais e pagamento, ou null se o rótulo não existe. */
    private static String valorDaLinha(String html, String rotulo) {
        Matcher m = Pattern.compile(">" + Pattern.quote(rotulo) + "</td>\\s*<td class=\"num\">([^<]*)</td>").matcher(html);
        return m.find() ? m.group(1) : null;
    }

    private static int ocorrencias(String texto, String trecho) {
        int total = 0;
        for (int i = texto.indexOf(trecho); i >= 0; i = texto.indexOf(trecho, i + 1)) {
            total++;
        }
        return total;
    }

    /** Texto da faixa horizontal da folha entre as duas frações da altura (0 = topo, 1 = pé da folha). */
    private static String textoDaFaixa(PDPage folha, float de, float ate) throws IOException {
        PDRectangle tamanho = folha.getMediaBox();
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.addRegion("faixa", new Rectangle2D.Float(0, tamanho.getHeight() * de,
                tamanho.getWidth(), tamanho.getHeight() * (ate - de)));
        stripper.extractRegions(folha);
        return stripper.getTextForRegion("faixa");
    }
}
