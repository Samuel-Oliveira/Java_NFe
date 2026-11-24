package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

/**
 * Geração das imagens da impressão v2 (código de barras, QR Code) e conversão para data URI, com o zxing.
 */
public final class ImagemDanfeV2Util {

    /** Largura de cada módulo em pixels: a chave em Code-128C tem 277 módulos, logo ~830 px; o CSS redimensiona. */
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
     * Código de barras CODE-128 da chave de acesso, em PNG, sem margem nem texto. O zxing usa o conjunto C (dois
     * dígitos por símbolo) nas sequências numéricas, como o DANFE pede para a chave de 44 dígitos.
     */
    public static byte[] codigoBarras128(String conteudo) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            boolean[] modulos = new Code128Writer().encode(conteudo);
            BufferedImage imagem = new BufferedImage(modulos.length * LARGURA_BARRA, ALTURA_BARRAS, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = imagem.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, imagem.getWidth(), imagem.getHeight());
            g.setColor(Color.BLACK);
            for (int i = 0; i < modulos.length; i++) {
                if (modulos[i]) {
                    g.fillRect(i * LARGURA_BARRA, 0, LARGURA_BARRA, ALTURA_BARRAS);
                }
            }
            g.dispose();
            ImageIO.write(imagem, "png", out);
            return out.toByteArray();
        } catch (IllegalArgumentException | IOException e) {
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
