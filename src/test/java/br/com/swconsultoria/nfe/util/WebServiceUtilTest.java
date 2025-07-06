package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceUtilTest {

    private static Stream<Object[]> provideStateAndServiceCombinationsNfe() {
        return Stream.of(EstadosEnum.values())
                .flatMap(estado ->
                        Stream.of(ServicosEnum.values())
                                .filter(servico ->
                                        servico != ServicosEnum.CONSULTA_CADASTRO &&
                                        servico != ServicosEnum.URL_CONSULTANFCE &&
                                        servico != ServicosEnum.PROC &&
                                        servico != ServicosEnum.URL_QRCODE)
                                .flatMap(servico ->
                                        Stream.of(AmbienteEnum.values())
                                                .map(ambiente -> new Object[]{estado, servico, ambiente})));
    }

    private static Stream<Object[]> provideStateAndServiceCombinationsNfce() {
        return Stream.of(EstadosEnum.values())
                .flatMap(estado ->
                        Stream.of(ServicosEnum.values())
                                .filter(servico ->
                                        servico != ServicosEnum.CONSULTA_CADASTRO &&
                                        servico != ServicosEnum.PROC)
                                .flatMap(servico ->
                                        Stream.of(AmbienteEnum.values())
                                                .map(ambiente -> new Object[]{estado, servico, ambiente})));
    }

    @Test
    void testGetUrlWithCustomFile() throws NfeException, IOException {
        String TEMP_WS_FILE = "temp_WebServicesNfe.ini";

        // Cria uma cópia temporária do arquivo INI para testes
        try (InputStream is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.ini")) {
            assertNotNull(is);
            Files.copy(is, Paths.get(TEMP_WS_FILE), StandardCopyOption.REPLACE_EXISTING);
        }

        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.SP);
        config.setAmbiente(AmbienteEnum.PRODUCAO);
        config.setArquivoWebService(TEMP_WS_FILE);

        String url = WebServiceUtil.getUrl(config, DocumentoEnum.NFE, ServicosEnum.STATUS_SERVICO);
        assertNotNull(url);

        // Remove o arquivo temporário após os testes
        Files.deleteIfExists(Paths.get(TEMP_WS_FILE));
    }

    @Test
    void testGetUrlWithFileNotFound() {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.SP);
        config.setAmbiente(AmbienteEnum.PRODUCAO);
        config.setArquivoWebService("nonexistent_file.ini");

        assertThrows(NfeException.class, () -> WebServiceUtil.getUrl(config, DocumentoEnum.NFE, ServicosEnum.STATUS_SERVICO));
    }

    @ParameterizedTest
    @MethodSource("provideStateAndServiceCombinationsNfe")
    void testGetUrlForStateServiceCombinationsNfe(EstadosEnum estado, ServicosEnum servico, AmbienteEnum ambienteEnum) throws NfeException {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(estado);
        config.setAmbiente(ambienteEnum);

        String url = WebServiceUtil.getUrl(config,DocumentoEnum.NFE, servico);
        assertNotNull(url);
        assertTrue(url.startsWith("http"));
    }

    @ParameterizedTest
    @MethodSource("provideStateAndServiceCombinationsNfce")
    void testGetUrlForStateServiceCombinationsNfce(EstadosEnum estado, ServicosEnum servico, AmbienteEnum ambienteEnum) throws NfeException {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(estado);
        config.setAmbiente(ambienteEnum);

        String url = WebServiceUtil.getUrl(config,DocumentoEnum.NFCE, servico);
        assertNotNull(url);
    }

    @Test
    void testGetUrlForDistribuicaoDFe() throws NfeException {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.SP); // Qualquer estado serve para AN
        config.setAmbiente(AmbienteEnum.PRODUCAO);

        String url = WebServiceUtil.getUrl(config, DocumentoEnum.NFE, ServicosEnum.DISTRIBUICAO_DFE);
        assertNotNull(url);
        assertTrue(url.startsWith("https://www1.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx"));
    }

    @Test
    void testGetUrlForContingenciaSVC() throws NfeException {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.SP);
        config.setAmbiente(AmbienteEnum.PRODUCAO);
        config.setContigenciaSVC(true);

        // SP no SVC deve usar SVRS
        String url = WebServiceUtil.getUrl(config,DocumentoEnum.NFE, ServicosEnum.STATUS_SERVICO);
        assertNotNull(url);
        assertTrue(url.contains("sefazvirtual"));
    }

}