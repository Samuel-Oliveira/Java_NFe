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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.swconsultoria.nfe.impressao.ConstantesImpressaoNfeUtil.PATH_LOGO_NFCE;
import static br.com.swconsultoria.nfe.impressao.ConstantesImpressaoNfeUtil.PATH_LOGO_NFE;

/**
 * Classe responsavel pela impressão da biblioteca: XML, template Thymeleaf (HTML) e PDF (openhtmltopdf).
 * O template padrão é escolhido pelo XML:
 * <ul>
 *     <li>NF-e (modelo 55): DANFE no leiaute da NT 2026.010 (Reforma Tributária - IBS/CBS/IS), retrato ou paisagem;
 *     com tpImp=6, o DANFE Simplificado Tipo 2 da NT 2026.003, em bobina;</li>
 *     <li>NFC-e (modelo 65): DANFE NFC-e, em bobina, no mesmo template do Simplificado Tipo 2;</li>
 *     <li>evento (procEventoNFe): Carta de Correção eletrônica (tpEvento 110110), em A4.</li>
 * </ul>
 * Todos os erros são lançados como {@link DanfeException}.
 * <p>
 * O openhtmltopdf registra várias linhas INFO (java.util.logging) a cada PDF. Esta classe não altera essa
 * configuração, que é da aplicação; para silenciar, use {@code com.openhtmltopdf.util.XRLog.setLevel} ou
 * {@code XRLog.setLoggingEnabled(false)} na inicialização da aplicação.
 */
public final class ImpressaoDanfeV2Util {

    /** Template padrão do DANFE A4 da NF-e (classpath: /danfe/v2/danfe.html). */
    public static final String TEMPLATE_PADRAO = "danfe";
    /**
     * Template do DANFE em bobina (classpath: /danfe/v2/danfe-bobina.html): o da NFC-e (modelo 65) e o Simplificado
     * Tipo 2 da NF-e (tpImp=6), usado sem template informado nesses dois casos. Para imprimir o DANFE A4 de uma NF-e
     * com tpImp=6, informe {@link #TEMPLATE_PADRAO} em {@link ImpressaoDanfeV2DTO#setTemplate(String)}.
     */
    public static final String TEMPLATE_BOBINA = "danfe-bobina";
    /**
     * Template da Carta de Correção eletrônica (classpath: /danfe/v2/cce.html), usado sem template informado quando
     * o XML é de um evento 110110. Os demais eventos só saem com template próprio.
     */
    public static final String TEMPLATE_CCE = "cce";
    /**
     * id (no HTML) do {@code <style>} com a regra {@code @page { size: <largura>mm <altura>mm; ... }} de um DANFE em
     * bobina. Com ele, {@link #htmlParaPdf} troca a altura da folha pela altura do conteúdo.
     */
    public static final String ID_PAGINA_BOBINA = "pagina-bobina";
    /** Altura da folha da primeira diagramação da bobina, em mm; fica abaixo do limite de 200 polegadas do PDF. */
    public static final int ALTURA_MAXIMA_BOBINA_MM = 5000;
    /** A partir desta largura de papel (mm), o QR Code do DANFE em bobina sai ao lado da identificação. */
    public static final int LARGURA_MINIMA_QRCODE_LATERAL_MM = 72;
    /** id (no HTML) do bloco de dados adicionais, que é encostado no rodapé da última folha em {@link #htmlParaPdf}. */
    public static final String ID_DADOS_ADICIONAIS = "dados-adicionais";
    /** id (no HTML) do espaçador vazio logo antes do bloco de dados adicionais; a altura dele é calculada em {@link #htmlParaPdf}. */
    public static final String ID_ESPACO_DADOS_ADICIONAIS = "espaco-dados-adicionais";
    /**
     * Chave em {@link ImpressaoDanfeV2DTO#getParametros()} do campo "Tipo de Regime de Apuração do IBS e da CBS"
     * do template padrão. Por padrão o campo vem de emit/tpRegimeIBSCBS (tag criada pela NT 2026.005). Quando a
     * chave existe no Map com valor não nulo, ela tem prioridade sobre o XML, inclusive "", que deixa o campo em
     * branco. Para usar o XML, não informe a chave.
     */
    public static final String PARAM_TIPO_REGIME_APURACAO = "tipoRegimeApuracao";
    /** Folga entre o fim do bloco de dados adicionais e o fim da área útil da folha, em mm. */
    private static final float FOLGA_RODAPE_MM = 1f;
    /** Folga entre o fim do conteúdo e o pé da folha da bobina, em mm: evita que um arredondamento gere outra folha. */
    private static final float FOLGA_BOBINA_MM = 1f;
    /** "size: 80mm 5000mm" da regra @page da bobina; o grupo 1 é a largura, mantida no ajuste da altura. */
    private static final Pattern TAMANHO_BOBINA = Pattern.compile("(size\\s*:\\s*[0-9.]+mm\\s+)[0-9.]+mm");
    /** Lado da imagem do QR Code gerada pelo zxing, em pixels; o CSS do template define o tamanho impresso. */
    private static final int TAMANHO_QRCODE_PX = 400;

    private static final TemplateEngine TEMPLATE_ENGINE = criaTemplateEngine();
    private static final FormatadorDanfeV2 FORMATADOR = new FormatadorDanfeV2();

    private ImpressaoDanfeV2Util() {
    }

    /**
     * Gera Objeto padrão para impressão do DANFE da NF-e: logo padrão de NF-e, orientação pelo tpImp do XML e
     * canhoto. Com tpImp=6 sai o DANFE Simplificado Tipo 2, em bobina de 80 mm.
     *
     * @param xml XML autorizado (nfeProc) ou NFe sem protocolo
     */
    public static ImpressaoDanfeV2DTO impressaoPadraoNFe(String xml) {
        ImpressaoDanfeV2DTO impressao = new ImpressaoDanfeV2DTO();
        impressao.setXml(xml);
        impressao.setLogo(ImpressaoDanfeV2Util.class.getResourceAsStream(PATH_LOGO_NFE));
        return impressao;
    }

    /**
     * Gera Objeto padrão para impressão do DANFE NFC-e: logo padrão de NFC-e, bobina de 80 mm. A URL de consulta
     * pela chave vem do infNFeSupl/urlChave do XML.
     *
     * @param xml XML autorizado (nfeProc) ou NFe sem protocolo (contingência off-line)
     */
    public static ImpressaoDanfeV2DTO impressaoPadraoNFCe(String xml) {
        ImpressaoDanfeV2DTO impressao = new ImpressaoDanfeV2DTO();
        impressao.setXml(xml);
        impressao.setLogo(ImpressaoDanfeV2Util.class.getResourceAsStream(PATH_LOGO_NFCE));
        return impressao;
    }

    /**
     * Gera Objeto padrão para impressão da Carta de Correção eletrônica, em A4 e sem logo (o XML do evento não
     * traz o nome do emitente; um logo próprio entra com {@link ImpressaoDanfeV2DTO#setLogo(byte[])}).
     *
     * @param xml procEventoNFe da CC-e, ou o evento sem o retorno da SEFAZ
     */
    public static ImpressaoDanfeV2DTO impressaoPadraoCCe(String xml) {
        ImpressaoDanfeV2DTO impressao = new ImpressaoDanfeV2DTO();
        impressao.setXml(xml);
        return impressao;
    }

    /**
     * Gera o HTML do documento (DANFE ou CC-e), com o template informado ou o padrão do tipo de XML.
     */
    public static String gerarHtml(ImpressaoDanfeV2DTO impressao) {
        XmlDanfeV2 xml = new XmlDanfeV2(impressao.getXml());

        Context contexto = new Context();
        contexto.setVariable("xml", xml);
        contexto.setVariable("fmt", FORMATADOR);
        contexto.setVariable("logo", ImagemDanfeV2Util.dataUri(impressao.getLogo()));
        contexto.setVariable("parametros", impressao.getParametros());

        String template = isEvento(xml) ? preparaEvento(impressao, xml, contexto) : preparaDanfe(impressao, xml, contexto);
        try {
            return TEMPLATE_ENGINE.process(template, contexto);
        } catch (TemplateEngineException e) {
            // Template inexistente, HTML inválido ou erro numa expressão: mantém o contrato de só lançar DanfeException
            throw new DanfeException("Erro ao processar o template " + template + ": " + e.getMessage(), e);
        }
    }

    /** XML de evento: sem infNFe e com evento/infEvento (procEventoNFe ou evento). */
    private static boolean isEvento(XmlDanfeV2 xml) {
        return xml.no("//infNFe") == null && xml.no("//evento/infEvento") != null;
    }

    /** Variáveis do DANFE (NF-e e NFC-e) e o template a usar. */
    private static String preparaDanfe(ImpressaoDanfeV2DTO impressao, XmlDanfeV2 xml, Context contexto) {
        DanfeV2DTO danfe = LeitorDanfeV2.ler(xml);
        contexto.setVariable("danfe", danfe);
        contexto.setVariable("paisagem", isPaisagem(impressao, danfe));
        contexto.setVariable("exibirCanhoto", impressao.isExibirCanhoto());
        contexto.setVariable("codigoBarras", codigoBarras(danfe.getChave()));
        contexto.setVariable("qrCode", danfe.isExibirQrCode()
                ? ImagemDanfeV2Util.dataUri(ImagemDanfeV2Util.qrCode(danfe.getSupl().get("qrCode"), TAMANHO_QRCODE_PX)) : null);
        // DANFE em bobina: a altura da folha é a máxima aqui e vira a do conteúdo em htmlParaPdf
        contexto.setVariable("larguraPapelMm", impressao.getLarguraPapelMm());
        contexto.setVariable("alturaPaginaMm", ALTURA_MAXIMA_BOBINA_MM);
        contexto.setVariable("qrCodeLateral", impressao.getLarguraPapelMm() >= LARGURA_MINIMA_QRCODE_LATERAL_MM);
        contexto.setVariable("viaEstabelecimento", impressao.isViaEstabelecimento());

        if (impressao.getTemplate() != null) {
            return impressao.getTemplate();
        }
        return danfe.isBobina() ? TEMPLATE_BOBINA : TEMPLATE_PADRAO;
    }

    /** Variáveis do evento e o template a usar; sem template próprio, só a CC-e tem leiaute. */
    private static String preparaEvento(ImpressaoDanfeV2DTO impressao, XmlDanfeV2 xml, Context contexto) {
        EventoV2DTO evento = LeitorEventoV2.ler(xml);
        contexto.setVariable("evento", evento);
        contexto.setVariable("codigoBarras", codigoBarras(evento.getChave()));

        if (impressao.getTemplate() != null) {
            return impressao.getTemplate();
        }
        if (!evento.isCartaCorrecao()) {
            throw new DanfeException("O evento " + evento.getEvento().get("tpEvento")
                    + " não tem leiaute de impressão padrão; informe um template próprio.");
        }
        return TEMPLATE_CCE;
    }

    private static String codigoBarras(String chave) {
        return chave == null ? null : ImagemDanfeV2Util.dataUri(ImagemDanfeV2Util.codigoBarras128(chave));
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
     * Como no antigo DANFE Jasper, o bloco de dados adicionais fica encostado no rodapé da última folha e o espaço que
     * sobra depois dos itens fica em branco entre os dois. O openhtmltopdf não tem "última folha" nem altura
     * flexível, então o documento é diagramado uma vez para medir quanto sobra na folha em que o bloco caiu, o
     * espaçador {@value #ID_ESPACO_DADOS_ADICIONAIS} recebe essa altura e o documento é diagramado de novo.
     * Sem os marcadores {@value #ID_ESPACO_DADOS_ADICIONAIS} e {@value #ID_DADOS_ADICIONAIS} (template próprio),
     * o bloco sai logo após os itens.
     * <p>
     * DANFE em bobina (DANFE Simplificado Tipo 2): com o {@code <style id="pagina-bobina">}, o documento é
     * diagramado numa folha de {@value #ALTURA_MAXIMA_BOBINA_MM} mm e diagramado de novo com a altura do conteúdo,
     * para o PDF sair numa folha só, do tamanho do cupom. A regra precisa de margem 0 (o respiro fica no body).
     */
    public static byte[] htmlParaPdf(String html) {
        try (ByteArrayOutputStream saida = new ByteArrayOutputStream();
             ByteArrayOutputStream saidaAjustada = new ByteArrayOutputStream();
             PdfBoxRenderer renderer = novoRenderer(html, null, saida)) {
            renderer.layout();

            Document documento = renderer.getDocument();
            Element paginaBobina = elementoPorId(documento, ID_PAGINA_BOBINA);
            if (paginaBobina != null) {
                String regra = regraNaAlturaDoConteudo(renderer, paginaBobina.getTextContent());
                if (regra != null) {
                    paginaBobina.setTextContent(regra);
                    try (PdfBoxRenderer ajustado = novoRenderer(null, documento, saidaAjustada)) {
                        ajustado.layout();
                        ajustado.createPDF();
                        renderer.getPdfDocument().close(); // close() do renderer não fecha o PDDocument que não foi gravado
                        return saidaAjustada.toByteArray();
                    }
                }
                renderer.createPDF();
                return saida.toByteArray();
            }

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

    /**
     * Regra @page da bobina com a altura da folha trocada pela altura do conteúdo (mais a folga), ou null quando
     * não há o que ajustar: regra sem "size: Lmm Amm" ou conteúdo que já não coube numa folha.
     */
    private static String regraNaAlturaDoConteudo(PdfBoxRenderer renderer, String regra) {
        Matcher tamanho = TAMANHO_BOBINA.matcher(regra);
        BlockBox raiz = renderer.getRootBox();
        if (!tamanho.find() || raiz.getLayer().getPages().size() != 1) {
            return null;
        }
        float unidadesPorMm = renderer.getSharedContext().getDPI() / 25.4f;
        float alturaMm = raiz.getHeight() / unidadesPorMm + FOLGA_BOBINA_MM;
        return tamanho.replaceFirst("$1" + String.format(Locale.ROOT, "%.1f", alturaMm) + "mm");
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
