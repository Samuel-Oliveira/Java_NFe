package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Model responsavel por armazenar as informações da impressão (Thymeleaf): DANFE da NF-e, DANFE NFC-e e CC-e.
 * A fonte de dados é sempre o XML. Obtenha o padrão com {@link ImpressaoDanfeV2Util#impressaoPadraoNFe(String)},
 * {@link ImpressaoDanfeV2Util#impressaoPadraoNFCe(String)} ou {@link ImpressaoDanfeV2Util#impressaoPadraoCCe(String)}.
 * <p>
 * Customizações:
 * <ul>
 *     <li>logo: {@link #setLogo(InputStream)} / {@link #setLogo(byte[])} / {@link #setLogoArquivo(String)};
 *     {@link #removerLogo()} imprime sem logo (setLogo(null) não compila por causa das sobrecargas)</li>
 *     <li>template: caminho de um arquivo .html Thymeleaf próprio no lugar do padrão</li>
 *     <li>parametros: valores livres disponíveis no template como ${parametros.nome}</li>
 *     <li>orientacao e exibirCanhoto (DANFE A4)</li>
 *     <li>larguraPapelMm e viaEstabelecimento (DANFE em bobina: NFC-e e Simplificado Tipo 2)</li>
 * </ul>
 */
public class ImpressaoDanfeV2DTO {

    /** Largura padrão da bobina do DANFE NFC-e e do Simplificado Tipo 2, em mm. */
    public static final int LARGURA_PAPEL_PADRAO_MM = 80;
    /** Largura mínima do papel do DANFE em bobina (Ajuste SINIEF 19/16; NT 2026.003 item 3.3), em mm. */
    public static final int LARGURA_PAPEL_MINIMA_MM = 56;

    private String xml;
    private byte[] logo;
    private String template;
    private OrientacaoDanfeEnum orientacao = OrientacaoDanfeEnum.AUTOMATICA;
    private boolean exibirCanhoto = true;
    private int larguraPapelMm = LARGURA_PAPEL_PADRAO_MM;
    private boolean viaEstabelecimento;
    private Map<String, Object> parametros;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    /**
     * Lê e fecha o InputStream informado.
     */
    public void setLogo(InputStream logo) {
        if (logo == null) {
            this.logo = null;
            return;
        }
        try (InputStream in = logo; ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int lidos;
            while ((lidos = in.read(buffer)) != -1) {
                out.write(buffer, 0, lidos);
            }
            this.logo = out.toByteArray();
        } catch (IOException e) {
            throw new DanfeException("Erro ao ler logo", e);
        }
    }

    /**
     * Lê o logo de um arquivo (PNG, JPEG, GIF ou BMP). Caminho nulo/vazio remove o logo.
     */
    public void setLogoArquivo(String caminho) {
        if (caminho == null || caminho.trim().isEmpty()) {
            this.logo = null;
            return;
        }
        try {
            this.logo = Files.readAllBytes(Paths.get(caminho));
        } catch (IOException | InvalidPathException e) {
            throw new DanfeException("Erro ao ler logo " + caminho, e);
        }
    }

    /**
     * Imprime o DANFE sem logo no quadro do emitente.
     */
    public void removerLogo() {
        this.logo = null;
    }

    public String getTemplate() {
        return template;
    }

    /**
     * Caminho de um template Thymeleaf (.html) próprio. Null usa o template padrão.
     * O template pode reaproveitar os fragmentos padrão: th:replace="~{fragmentos :: campo(...)}"
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    public OrientacaoDanfeEnum getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(OrientacaoDanfeEnum orientacao) {
        this.orientacao = orientacao == null ? OrientacaoDanfeEnum.AUTOMATICA : orientacao;
    }

    public boolean isExibirCanhoto() {
        return exibirCanhoto;
    }

    public void setExibirCanhoto(boolean exibirCanhoto) {
        this.exibirCanhoto = exibirCanhoto;
    }

    public int getLarguraPapelMm() {
        return larguraPapelMm;
    }

    /**
     * Largura do papel (bobina) do DANFE NFC-e e do Simplificado Tipo 2, em mm: 80 (padrão), 58 etc. A altura da folha
     * acompanha o conteúdo. Abaixo de 72 mm o QR Code sai centralizado; a partir daí, ao lado da identificação.
     * Não se aplica ao DANFE A4.
     *
     * @throws DanfeException largura menor que {@value #LARGURA_PAPEL_MINIMA_MM} mm (NT 2026.003 item 3.3)
     */
    public void setLarguraPapelMm(int larguraPapelMm) {
        if (larguraPapelMm < LARGURA_PAPEL_MINIMA_MM) {
            throw new DanfeException("Largura do papel do DANFE em bobina menor que "
                    + LARGURA_PAPEL_MINIMA_MM + " mm: " + larguraPapelMm);
        }
        this.larguraPapelMm = larguraPapelMm;
    }

    public boolean isViaEstabelecimento() {
        return viaEstabelecimento;
    }

    /**
     * DANFE em bobina (NFC-e ou Simplificado Tipo 2) em contingência: a segunda via, que fica no estabelecimento
     * até a autorização da nota, sai com "Via do Estabelecimento" ao lado da data de emissão (NT 2026.003 item
     * 3.1.9). Sem esta marca, a nota em contingência sai como "Via do Consumidor". Imprima as duas.
     */
    public void setViaEstabelecimento(boolean viaEstabelecimento) {
        this.viaEstabelecimento = viaEstabelecimento;
    }

    public Map<String, Object> getParametros() {
        if (this.parametros == null) {
            this.parametros = new HashMap<>();
        }
        return parametros;
    }
}
