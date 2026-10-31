package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

/**
 * Geração das imagens do DANFE v2 (código de barras, QR Code) e conversão para data URI.
 * Usa as mesmas bibliotecas do leiaute Jasper (barbecue e zxing), já dependências do projeto.
 */
public final class ImagemDanfeV2Util {

    /** Largura de cada barra em pixels: a chave em Code-128C tem ~300 módulos, logo ~900 px; o CSS redimensiona. */
    private static final int LARGURA_BARRA = 3;
    private static final int ALTURA_BARRAS = 80;

    private ImagemDanfeV2Util() {
    }

    /**
     * Converte a imagem em data URI (PNG, JPEG, GIF ou BMP) para uso direto no HTML.
     */
    public static String dataUri(byte[] imagem) {
        if (imagem == null || imagem.length == 0) {
            return null;
        }
        return "data:" + tipo(imagem) + ";base64," + Base64.getEncoder().encodeToString(imagem);
    }

    /**
     * Código de barras CODE-128 da chave de acesso, em PNG (128C quando o conteúdo é numérico de tamanho par,
     * como a chave de 44 dígitos; é o mesmo tipo usado no danfe.jrxml).
     */
    public static byte[] codigoBarras128(String conteudo) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            boolean numericoPar = conteudo.matches("\\d+") && conteudo.length() % 2 == 0;
            Barcode barcode = numericoPar ? BarcodeFactory.createCode128C(conteudo) : BarcodeFactory.createCode128(conteudo);
            barcode.setDrawingText(false);
            barcode.setDrawingQuietSection(false);
            barcode.setBarWidth(LARGURA_BARRA);
            barcode.setBarHeight(ALTURA_BARRAS);
            BufferedImage imagem = BarcodeImageHandler.getImage(barcode);
            ImageIO.write(imagem, "png", out);
            return out.toByteArray();
        } catch (BarcodeException | OutputException | IOException e) {
            throw new DanfeException("Erro ao gerar código de barras", e);
        }
    }

    /**
     * QR Code em PNG (tamanho em pixels).
     */
    public static byte[] qrCode(String conteudo, int tamanho) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, 0);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix matriz = new QRCodeWriter().encode(conteudo, BarcodeFormat.QR_CODE, tamanho, tamanho, hints);
            MatrixToImageWriter.writeToStream(matriz, "PNG", out);
            return out.toByteArray();
        } catch (WriterException | IOException e) {
            throw new DanfeException("Erro ao gerar QR Code", e);
        }
    }

    private static String tipo(byte[] b) {
        if (b.length > 3 && (b[0] & 0xFF) == 0x89 && b[1] == 'P' && b[2] == 'N' && b[3] == 'G') {
            return "image/png";
        }
        if (b.length > 2 && (b[0] & 0xFF) == 0xFF && (b[1] & 0xFF) == 0xD8) {
            return "image/jpeg";
        }
        if (b.length > 2 && b[0] == 'G' && b[1] == 'I' && b[2] == 'F') {
            return "image/gif";
        }
        if (b.length > 1 && b[0] == 'B' && b[1] == 'M') {
            return "image/bmp";
        }
        return "image/png";
    }
}
