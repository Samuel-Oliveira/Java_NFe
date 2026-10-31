package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.render.BlockBox;
import com.openhtmltopdf.render.Box;
import com.openhtmltopdf.render.PageBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateEngineException;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import static br.com.swconsultoria.nfe.impressao.ConstantesImpressaoNfeUtil.PATH_LOGO_NFE;

/**
 * Classe responsavel pela impressão do DANFE v2 da NF-e (modelo 55): XML da NF-e, template Thymeleaf (HTML)
 * e PDF (openhtmltopdf). Leiaute conforme NT 2026.010 (Reforma Tributária - IBS/CBS/IS), retrato ou paisagem.
 * Convive com a impressão Jasper de {@link br.com.swconsultoria.nfe.impressao.ImpressaoNfeUtil}.
 * Todos os erros são lançados como {@link DanfeException}.
 * <p>
 * O openhtmltopdf registra várias linhas INFO (java.util.logging) a cada PDF. Esta classe não altera essa
 * configuração, que é da aplicação; para silenciar, use {@code com.openhtmltopdf.util.XRLog.setLevel} ou
 * {@code XRLog.setLoggingEnabled(false)} na inicialização da aplicação.
 */
public final class ImpressaoDanfeV2Util {

    /** Template padrão (classpath: /danfe/v2/danfe.html). */
    public static final String TEMPLATE_PADRAO = "danfe";
    /** id (no HTML) do bloco de dados adicionais, que é encostado no rodapé da última folha em {@link #htmlParaPdf}. */
    public static final String ID_DADOS_ADICIONAIS = "dados-adicionais";
    /** id (no HTML) do espaçador vazio logo antes do bloco de dados adicionais; a altura dele é calculada em {@link #htmlParaPdf}. */
    public static final String ID_ESPACO_DADOS_ADICIONAIS = "espaco-dados-adicionais";
    /**
     * Chave em {@link ImpressaoDanfeV2DTO#getParametros()} do campo "Tipo de Regime de Apuração do IBS e da CBS"
     * do template padrão. A NT 2026.010 reserva o campo e a tag ainda não foi publicada, por isso ele sai vazio
     * a menos que a aplicação informe o valor.
     */
    public static final String PARAM_TIPO_REGIME_APURACAO = "tipoRegimeApuracao";
    /** Folga entre o fim do bloco de dados adicionais e o fim da área útil da folha, em mm. */
    private static final float FOLGA_RODAPE_MM = 1f;
    /** Lado da imagem do QR Code gerada pelo zxing, em pixels; o CSS do template define o tamanho impresso. */
    private static final int TAMANHO_QRCODE_PX = 400;

    private static final TemplateEngine TEMPLATE_ENGINE = criaTemplateEngine();
    private static final FormatadorDanfeV2 FORMATADOR = new FormatadorDanfeV2();

    private ImpressaoDanfeV2Util() {
    }

    /**
     * Gera Objeto padrão para impressão do DANFE v2: logo padrão de NF-e, orientação pelo tpImp do XML e canhoto.
     *
     * @param xml XML autorizado (nfeProc) ou NFe sem protocolo
     * @return
     */
    public static ImpressaoDanfeV2DTO impressaoPadraoNFe(String xml) {
        ImpressaoDanfeV2DTO impressao = new ImpressaoDanfeV2DTO();
        impressao.setXml(xml);
        impressao.setLogo(ImpressaoDanfeV2Util.class.getResourceAsStream(PATH_LOGO_NFE));
        return impressao;
    }

    /**
     * Gera o HTML do DANFE.
     */
    public static String gerarHtml(ImpressaoDanfeV2DTO impressao) {
        XmlDanfeV2 xml = new XmlDanfeV2(impressao.getXml());
        DanfeV2DTO danfe = LeitorDanfeV2.ler(xml);

        Context contexto = new Context();
        contexto.setVariable("danfe", danfe);
        contexto.setVariable("xml", xml);
        contexto.setVariable("fmt", FORMATADOR);
        contexto.setVariable("paisagem", isPaisagem(impressao, danfe));
        contexto.setVariable("exibirCanhoto", impressao.isExibirCanhoto());
        contexto.setVariable("logo", ImagemDanfeV2Util.dataUri(impressao.getLogo()));
        contexto.setVariable("codigoBarras", danfe.getChave() == null ? null
                : ImagemDanfeV2Util.dataUri(ImagemDanfeV2Util.codigoBarras128(danfe.getChave())));
        contexto.setVariable("qrCode", danfe.isExibirQrCode()
                ? ImagemDanfeV2Util.dataUri(ImagemDanfeV2Util.qrCode(danfe.getSupl().get("qrCode"), TAMANHO_QRCODE_PX)) : null);
        contexto.setVariable("parametros", impressao.getParametros());

        String template = impressao.getTemplate() == null ? TEMPLATE_PADRAO : impressao.getTemplate();
        try {
            return TEMPLATE_ENGINE.process(template, contexto);
        } catch (TemplateEngineException e) {
            // Template inexistente, HTML inválido ou erro numa expressão: mantém o contrato de só lançar DanfeException
            throw new DanfeException("Erro ao processar o template " + template + ": " + e.getMessage(), e);
        }
    }

    /**
     * Cria a impressão no caminho definido e no formato HTML.
     */
    public static void impressaoHtml(ImpressaoDanfeV2DTO impressao, String destinoHtml) {
        try {
            Files.write(Paths.get(destinoHtml), gerarHtml(impressao).getBytes(StandardCharsets.UTF_8));
        } catch (IOException | InvalidPathException e) {
            throw new DanfeException("Erro ao gravar HTML " + destinoHtml, e);
        }
    }

    /**
     * Cria a impressão Retornando o byte[] do PDF.
     */
    public static byte[] impressaoPdfByte(ImpressaoDanfeV2DTO impressao) {
        return htmlParaPdf(gerarHtml(impressao));
    }

    /**
     * Cria a impressão no caminho definido e no formato PDF.
     */
    public static void impressaoPdfArquivo(ImpressaoDanfeV2DTO impressao, String destinoPdf) {
        try {
            Files.write(Paths.get(destinoPdf), impressaoPdfByte(impressao));
        } catch (IOException | InvalidPathException e) {
            throw new DanfeException("Erro ao gravar PDF " + destinoPdf, e);
        }
    }

    /**
     * Cria a impressão direta na impressora padrão.
     */
    public static void impressaoDireta(ImpressaoDanfeV2DTO impressao) {
        impressaoDireta(impressao, PrintServiceLookup.lookupDefaultPrintService());
    }

    /**
     * Cria a impressão direta na impressora informada.
     */
    public static void impressaoDireta(ImpressaoDanfeV2DTO impressao, PrintService impressora) {
        if (impressora == null) {
            throw new DanfeException("Impressora não encontrada");
        }
        byte[] pdf = impressaoPdfByte(impressao);
        try (PDDocument documento = PDDocument.load(pdf)) {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintService(impressora);
            printerJob.setPageable(new PDFPageable(documento));
            printerJob.print();
        } catch (IOException | PrinterException e) {
            throw new DanfeException("Erro ao imprimir DANFE", e);
        }
    }

    /**
     * Converte um HTML (XHTML bem formado) em PDF.
     * <p>
     * Como no DANFE Jasper, o bloco de dados adicionais fica encostado no rodapé da última folha e o espaço que
     * sobra depois dos itens fica em branco entre os dois. O openhtmltopdf não tem "última folha" nem altura
     * flexível, então o documento é diagramado uma vez para medir quanto sobra na folha em que o bloco caiu, o
     * espaçador {@value #ID_ESPACO_DADOS_ADICIONAIS} recebe essa altura e o documento é diagramado de novo.
     * Sem os marcadores {@value #ID_ESPACO_DADOS_ADICIONAIS} e {@value #ID_DADOS_ADICIONAIS} (template próprio),
     * o bloco sai logo após os itens.
     */
    public static byte[] htmlParaPdf(String html) {
        try (ByteArrayOutputStream saida = new ByteArrayOutputStream();
             ByteArrayOutputStream saidaAjustada = new ByteArrayOutputStream();
             PdfBoxRenderer renderer = novoRenderer(html, null, saida)) {
            renderer.layout();

            Document documento = renderer.getDocument();
            Element espaco = elementoPorId(documento, ID_ESPACO_DADOS_ADICIONAIS);
            Element dadosAdicionais = elementoPorId(documento, ID_DADOS_ADICIONAIS);
            int altura = alturaDoEspacoAteORodape(renderer, espaco, dadosAdicionais);
            if (altura > 0) {
                float pixels = altura / (float) renderer.getSharedContext().getDotsPerPixel();
                // Preserva um estilo inline que um template próprio tenha dado ao espaçador
                String estilo = espaco.getAttribute("style");
                espaco.setAttribute("style", (estilo.isEmpty() ? "" : estilo + ";")
                        + "height:" + String.format(Locale.ROOT, "%.2f", pixels) + "px");
                try (PdfBoxRenderer ajustado = novoRenderer(null, documento, saidaAjustada)) {
                    ajustado.layout();
                    // Se algum arredondamento empurrou o bloco para outra folha, vale a diagramação original
                    if (folhaDoElemento(ajustado, dadosAdicionais) == folhaDoElemento(renderer, dadosAdicionais)
                            && totalFolhas(ajustado) == totalFolhas(renderer)) {
                        ajustado.createPDF();
                        renderer.getPdfDocument().close(); // close() do renderer não fecha o PDDocument que não foi gravado
                        return saidaAjustada.toByteArray();
                    }
                    ajustado.getPdfDocument().close();
                }
            }
            renderer.createPDF();
            return saida.toByteArray();
        } catch (IOException | RuntimeException e) {
            // RuntimeException também: getRootBox()/getElementBoxes() são API interna do openhtmltopdf
            throw new DanfeException("Erro ao gerar PDF do DANFE (" + e.getClass().getSimpleName() + ")", e);
        }
    }

    private static PdfBoxRenderer novoRenderer(String html, Document documento, ByteArrayOutputStream saida) {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        if (documento != null) {
            builder.withW3cDocument(documento, null);
        } else {
            builder.withHtmlContent(html, null);
        }
        builder.toStream(saida);
        return builder.buildPdfRenderer();
    }

    /**
     * Altura que o espaçador precisa ter para o bloco de dados adicionais terminar no fim da área útil da folha
     * em que ele caiu (menos a folga), em unidades de layout do openhtmltopdf. 0 quando não há o que ajustar:
     * marcadores ausentes, bloco maior que a folha ou ganho menor que a folga.
     */
    private static int alturaDoEspacoAteORodape(PdfBoxRenderer renderer, Element espaco, Element dadosAdicionais) {
        if (espaco == null || dadosAdicionais == null) {
            return 0;
        }
        BlockBox raiz = renderer.getRootBox();
        Box caixaEspaco = caixaDoElemento(raiz, espaco);
        Box caixaBloco = caixaDoElemento(raiz, dadosAdicionais);
        if (caixaEspaco == null || caixaBloco == null) {
            return 0;
        }
        PageBox folha = folhaEm(raiz, caixaBloco.getAbsY() + caixaBloco.getHeight() - 1);
        if (folha == null || caixaBloco.getHeight() > folha.getBottom() - folha.getTop()) {
            return 0;
        }
        int folga = Math.round(renderer.getSharedContext().getDPI() / 25.4f * FOLGA_RODAPE_MM);
        int altura = folha.getBottom() - folga - caixaBloco.getHeight() - caixaEspaco.getAbsY();
        return altura - caixaEspaco.getHeight() > folga ? altura : 0;
    }

    /** Índice da folha em que o elemento termina, ou -1 se ele não foi diagramado. */
    private static int folhaDoElemento(PdfBoxRenderer renderer, Element elemento) {
        BlockBox raiz = renderer.getRootBox();
        Box caixa = caixaDoElemento(raiz, elemento);
        if (caixa == null) {
            return -1;
        }
        List<PageBox> folhas = raiz.getLayer().getPages();
        return folhas.indexOf(folhaEm(raiz, caixa.getAbsY() + caixa.getHeight() - 1));
    }

    private static int totalFolhas(PdfBoxRenderer renderer) {
        return renderer.getRootBox().getLayer().getPages().size();
    }

    private static Box caixaDoElemento(BlockBox raiz, Element elemento) {
        List<Box> caixas = raiz.getElementBoxes(elemento);
        return caixas.isEmpty() ? null : caixas.get(0);
    }

    /** Folha cuja área útil contém a coordenada vertical absoluta informada. */
    private static PageBox folhaEm(BlockBox raiz, int y) {
        for (PageBox folha : raiz.getLayer().getPages()) {
            if (y >= folha.getTop() && y < folha.getBottom()) {
                return folha;
            }
        }
        return null;
    }

    /** O DOM do openhtmltopdf não conhece atributos ID (sem DTD), por isso getElementById não serve. */
    private static Element elementoPorId(Document documento, String id) {
        NodeList elementos = documento.getElementsByTagName("*");
        for (int i = 0; i < elementos.getLength(); i++) {
            Element elemento = (Element) elementos.item(i);
            if (id.equals(elemento.getAttribute("id"))) {
                return elemento;
            }
        }
        return null;
    }

    private static boolean isPaisagem(ImpressaoDanfeV2DTO impressao, DanfeV2DTO danfe) {
        if (impressao.getOrientacao() == OrientacaoDanfeEnum.AUTOMATICA) {
            return danfe.isPaisagem();
        }
        return impressao.getOrientacao() == OrientacaoDanfeEnum.PAISAGEM;
    }

    /**
     * Templates customizados são buscados primeiro como arquivo; os nomes não encontrados
     * (ex.: "danfe", "fragmentos") caem no classpath /danfe/v2/.
     */
    private static TemplateEngine criaTemplateEngine() {
        FileTemplateResolver arquivo = new FileTemplateResolver();
        arquivo.setOrder(1);
        arquivo.setCheckExistence(true);
        arquivo.setCacheable(false);
        arquivo.setTemplateMode(TemplateMode.HTML);
        arquivo.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ClassLoaderTemplateResolver classpath = new ClassLoaderTemplateResolver(ImpressaoDanfeV2Util.class.getClassLoader());
        classpath.setOrder(2);
        classpath.setCheckExistence(true);
        classpath.setPrefix("danfe/v2/");
        classpath.setSuffix(".html");
        classpath.setTemplateMode(TemplateMode.HTML);
        classpath.setCharacterEncoding(StandardCharsets.UTF_8.name());

        TemplateEngine engine = new TemplateEngine();
        engine.addTemplateResolver(arquivo);
        engine.addTemplateResolver(classpath);
        return engine;
    }
}
