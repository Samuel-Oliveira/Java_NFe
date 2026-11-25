package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ReaderException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.openhtmltopdf.util.XRLog;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.DrawObject;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.apache.pdfbox.contentstream.operator.state.Restore;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Margem do QR Code dentro da imagem (ISO/IEC 18004: 4 módulos; NT 2026.003, item 3.4: 10% do lado acima de 25 mm) e
 * dimensões mínimas na bobina (imagem de 25 mm no mínimo, sendo 22 mm de conteúdo e 3 mm de margem segura).
 */
class QrCodeV2Test {

    private static final float PONTOS_POR_MM = 72f / 25.4f;
    private static final String CHAVE = "35261212345678000195550020000007891000007890";
    /** O menor QR da NF-e: a URL de consulta mais curta (PB) com o QR v3 online, 37 módulos. */
    private static final String QR_MINIMO = "http://www.sefaz.pb.gov.br/nfce?p=" + CHAVE + "|3|1";
    /** Consulta online (QR v3) de SP. */
    private static final String QR_ONLINE = "https://www.homologacao.nfce.fazenda.sp.gov.br/qrcode?p=" + CHAVE + "|3|2";
    /**
     * Contingência off-line (QR v3 com dia, valor, destinatário e assinatura). A assinatura é SHA1withRSA em Base64
     * (NFCeUtil): com chave RSA de 2048 bits, 256 bytes e 344 caracteres, o código passa de 72 módulos.
     */
    private static final String QR_OFFLINE = QR_ONLINE + "|01|194.28|2|98765432000110|"
            + Base64.getEncoder().encodeToString(bytes(256));

    private static String xmlTipo2;
    private static String xmlNfce;

    @BeforeAll
    static void leXml() throws IOException {
        for (String logger : XRLog.listRegisteredLoggers()) {
            XRLog.setLevel(logger, Level.WARNING);
        }
        xmlTipo2 = XmlNfeUtil.leXml("src/test/resources/DanfeSimplificadoTipo2.xml");
        xmlNfce = XmlNfeUtil.leXml("src/test/resources/DanfeNfce.xml");
    }

    @Test
    void margemDentroDaImagemSemSobra() throws IOException {
        // 0: um pixel por módulo; 360: múltiplo exato de 41 + 4, onde a margem 2 caberia sem sobra; 400: o tamanho
        // usado pelo ImpressaoDanfeV2Util
        for (String conteudo : new String[]{QR_MINIMO, QR_ONLINE, QR_OFFLINE}) {
            for (int tamanho : new int[]{0, 100, 360, 400}) {
                BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(ImagemDanfeV2Util.qrCode(conteudo, tamanho)));
                Qr qr = Qr.medir(imagem);
                String caso = conteudo.length() + " caracteres em " + tamanho + " px";
                int margem = qr.margemEsquerda;

                assertTrue(imagem.getWidth() >= tamanho, caso + ": pelo menos o tamanho pedido");
                assertEquals(imagem.getWidth(), imagem.getHeight(), caso);
                assertEquals(0, imagem.getWidth() % qr.ladoEmModulos(), caso + ": múltiplo exato dos módulos");
                assertEquals(margem, qr.margemSuperior, caso + ": margem de cima igual à esquerda");
                assertEquals(margem, qr.margemDireita, caso + ": margem direita");
                assertEquals(margem, qr.margemInferior, caso + ": margem de baixo");
                assertTrue(margem >= 4, caso + ": zona de silêncio de " + margem + " módulos (ISO/IEC 18004)");
                assertTrue(2.0 * margem / qr.ladoEmModulos() >= 0.10, caso + ": margem segura de 10% do lado");
                assertEquals(Math.max(4, (int) Math.ceil(qr.modulos / 18.0)), margem, caso + ": sem margem a mais que o necessário");
            }
        }
    }

    @Test
    void extremosReaisDoQrDaNfe() throws IOException {
        Qr minimo = Qr.medir(ImageIO.read(new ByteArrayInputStream(ImagemDanfeV2Util.qrCode(QR_MINIMO, 400))));
        assertEquals(37, minimo.modulos, "o menor QR da NF-e é a versão 5");

        Qr offline = Qr.medir(ImageIO.read(new ByteArrayInputStream(ImagemDanfeV2Util.qrCode(QR_OFFLINE, 400))));
        assertTrue(offline.modulos > 72, "a assinatura de 344 caracteres passa de 72 módulos: " + offline.modulos);
        assertTrue(offline.margemEsquerda > 4, "com 4 módulos a margem ficaria abaixo de 10% do lado");
    }

    @Test
    void tamanhoNegativoEhRecusado() {
        assertThrows(DanfeException.class, () -> ImagemDanfeV2Util.qrCode(QR_ONLINE, -1));
    }

    @Test
    void bobinaAtendeAsDimensoesMinimasDaNt() throws IOException {
        ImpressaoDanfeV2DTO tipo2 = ImpressaoDanfeV2Util.impressaoPadraoNFe(xmlTipo2);
        ImpressaoDanfeV2DTO estreito = ImpressaoDanfeV2Util.impressaoPadraoNFe(xmlTipo2);
        estreito.setLarguraPapelMm(58);
        ImpressaoDanfeV2DTO nfce = ImpressaoDanfeV2Util.impressaoPadraoNFCe(xmlNfce);
        ImpressaoDanfeV2DTO offline = ImpressaoDanfeV2Util.impressaoPadraoNFe(comQrCode(QR_OFFLINE));
        ImpressaoDanfeV2DTO minimo = ImpressaoDanfeV2Util.impressaoPadraoNFe(comQrCode(QR_MINIMO));

        String[] nomes = {"Tipo 2 em 80 mm (lateral)", "Tipo 2 em 58 mm (centralizado)", "NFC-e", "Tipo 2 off-line",
                "Tipo 2 com o menor QR"};
        ImpressaoDanfeV2DTO[] impressoes = {tipo2, estreito, nfce, offline, minimo};
        for (int i = 0; i < impressoes.length; i++) {
            try (PDDocument documento = PDDocument.load(ImpressaoDanfeV2Util.impressaoPdfByte(impressoes[i]))) {
                QrDesenhado desenhado = QrDesenhado.procurar(documento);
                assertNotNull(desenhado, nomes[i] + ": QR Code no PDF");
                Qr qr = Qr.medir(desenhado.imagem);
                double ladoMm = desenhado.ladoPontos / PONTOS_POR_MM;
                double moduloMm = ladoMm / qr.ladoEmModulos();
                double conteudoMm = moduloMm * qr.modulos;
                double margemMm = moduloMm * Math.min(Math.min(qr.margemEsquerda, qr.margemDireita),
                        Math.min(qr.margemSuperior, qr.margemInferior));

                assertEquals(30, ladoMm, 0.1, nomes[i] + ": imagem de 30 mm, como no CSS da bobina");
                assertTrue(ladoMm >= 25, nomes[i] + ": imagem de " + ladoMm + " mm (mínimo 25)");
                assertTrue(conteudoMm >= 22, nomes[i] + ": conteúdo de " + conteudoMm + " mm (mínimo 22)");
                assertTrue(margemMm * 2 >= 3 && margemMm * 2 >= ladoMm * 0.10,
                        nomes[i] + ": margem segura de " + margemMm + " mm de cada lado (3 mm ou 10% no total)");
            }
        }
    }

    private static String comQrCode(String qrCode) {
        return xmlTipo2.replaceAll("<qrCode>[^<]*</qrCode>",
                Matcher.quoteReplacement("<qrCode>" + qrCode.replace("&", "&amp;") + "</qrCode>"));
    }

    private static byte[] bytes(int quantidade) {
        byte[] bytes = new byte[quantidade];
        for (int i = 0; i < quantidade; i++) {
            bytes[i] = (byte) (i * 37 + 11);
        }
        return bytes;
    }

    /** Medidas de um QR Code numa imagem: módulos do código e margem branca de cada lado, em módulos. */
    private static final class Qr {
        int modulos;
        int margemEsquerda;
        int margemSuperior;
        int margemDireita;
        int margemInferior;

        int ladoEmModulos() {
            return modulos + margemEsquerda + margemDireita;
        }

        static Qr medir(BufferedImage imagem) {
            int minX = imagem.getWidth(), minY = imagem.getHeight(), maxX = -1, maxY = -1;
            for (int y = 0; y < imagem.getHeight(); y++) {
                for (int x = 0; x < imagem.getWidth(); x++) {
                    if (escuro(imagem, x, y)) {
                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                }
            }
            assertTrue(maxX >= 0, "imagem sem QR Code");
            // A primeira linha do padrão de posição do canto superior esquerdo tem 7 módulos escuros seguidos
            int linhaDoPadrao = 0;
            while (minX + linhaDoPadrao < imagem.getWidth() && escuro(imagem, minX + linhaDoPadrao, minY)) {
                linhaDoPadrao++;
            }
            assertEquals(0, linhaDoPadrao % 7, "padrão de posição com 7 módulos de pixels inteiros");
            int modulo = linhaDoPadrao / 7;
            Qr qr = new Qr();
            qr.modulos = (maxX - minX + 1) / modulo;
            qr.margemEsquerda = minX / modulo;
            qr.margemSuperior = minY / modulo;
            qr.margemDireita = (imagem.getWidth() - 1 - maxX) / modulo;
            qr.margemInferior = (imagem.getHeight() - 1 - maxY) / modulo;
            return qr;
        }

        private static boolean escuro(BufferedImage imagem, int x, int y) {
            return (imagem.getRGB(x, y) & 0xFF) < 128;
        }
    }

    /** A imagem do QR Code no PDF e o lado com que ela foi desenhada na folha, em pontos. */
    private static final class QrDesenhado {
        BufferedImage imagem;
        float ladoPontos;

        static QrDesenhado procurar(PDDocument documento) throws IOException {
            QrDesenhado[] achado = new QrDesenhado[1];
            PDFStreamEngine engine = new PDFStreamEngine() {
                {
                    addOperator(new Concatenate());
                    addOperator(new DrawObject());
                    addOperator(new SetGraphicsStateParameters());
                    addOperator(new Save());
                    addOperator(new Restore());
                    addOperator(new SetMatrix());
                }

                @Override
                protected void processOperator(Operator operador, List<COSBase> operandos) throws IOException {
                    if (!"Do".equals(operador.getName())) {
                        super.processOperator(operador, operandos);
                        return;
                    }
                    PDXObject objeto = getResources().getXObject((COSName) operandos.get(0));
                    if (objeto instanceof PDFormXObject) {
                        showForm((PDFormXObject) objeto);
                    } else if (objeto instanceof PDImageXObject && ehQrCode(((PDImageXObject) objeto).getImage())) {
                        Matrix ctm = getGraphicsState().getCurrentTransformationMatrix();
                        QrDesenhado qr = new QrDesenhado();
                        qr.imagem = ((PDImageXObject) objeto).getImage();
                        qr.ladoPontos = ctm.getScalingFactorX();
                        assertEquals(ctm.getScalingFactorX(), ctm.getScalingFactorY(), 0.01f, "QR Code quadrado");
                        achado[0] = qr;
                    }
                }
            };
            engine.processPage(documento.getPage(0));
            return achado[0];
        }

        private static boolean ehQrCode(BufferedImage imagem) {
            if (imagem.getWidth() < 21 || imagem.getWidth() != imagem.getHeight()) {
                return false;
            }
            Map<DecodeHintType, Object> pura = new EnumMap<>(DecodeHintType.class);
            pura.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            try {
                new QRCodeReader().decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(imagem))), pura);
                return true;
            } catch (ReaderException e) {
                return false;
            }
        }
    }
}
