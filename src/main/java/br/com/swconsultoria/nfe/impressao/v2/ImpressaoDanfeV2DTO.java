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
 * Model responsavel por armazenar as informações da impressão do DANFE v2 (Thymeleaf).
 * A fonte de dados é sempre o XML da NF-e. Obtenha o padrão com {@link ImpressaoDanfeV2Util#impressaoPadraoNFe(String)}.
 * <p>
 * Customizações equivalentes às do layout Jasper ({@link br.com.swconsultoria.nfe.impressao.ImpressaoDTO}):
 * <ul>
 *     <li>logo: {@link #setLogo(InputStream)} / {@link #setLogo(byte[])} / {@link #setLogoArquivo(String)};
 *     {@link #removerLogo()} imprime sem logo (setLogo(null) não compila por causa das sobrecargas)</li>
 *     <li>template: caminho de um arquivo .html Thymeleaf próprio no lugar do padrão</li>
 *     <li>parametros: valores livres disponíveis no template como ${parametros.nome}</li>
 *     <li>orientacao e exibirCanhoto</li>
 * </ul>
 */
public class ImpressaoDanfeV2DTO {

    private String xml;
    private byte[] logo;
    private String template;
    private OrientacaoDanfeEnum orientacao = OrientacaoDanfeEnum.AUTOMATICA;
    private boolean exibirCanhoto = true;
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

    public Map<String, Object> getParametros() {
        if (this.parametros == null) {
            this.parametros = new HashMap<>();
        }
        return parametros;
    }
}
