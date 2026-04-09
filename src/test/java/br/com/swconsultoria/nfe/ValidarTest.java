package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários de Validar — validação de XML contra esquemas XSD locais.
 * Nenhuma chamada de rede é realizada.
 */
class ValidarTest {

    /* -----------------------------------------------------------------------
     * Constantes
     * --------------------------------------------------------------------- */
    private static final String SCHEMAS_PATH = "schemas";
    private static final String STATUS_XSD =
            SCHEMAS_PATH + File.separator + "consStatServ_v4.00.xsd";

    /** XML mínimo e válido para consStatServ (homologação, Goiás). */
    private static final String XML_STATUS_VALIDO =
            "<consStatServ versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
            "<tpAmb>2</tpAmb><cUF>52</cUF><xServ>STATUS</xServ></consStatServ>";

    /** XML inválido: campo obrigatório cUF ausente. */
    private static final String XML_STATUS_SEM_CUF =
            "<consStatServ versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
            "<tpAmb>2</tpAmb><xServ>STATUS</xServ></consStatServ>";

    /** XML malformado (tag não fechada). */
    private static final String XML_MALFORMADO = "<consStatServ><tpAmb>2</tpAmb>";

    /** XML completamente vazio. */
    private static final String XML_VAZIO = "";

    private Validar validar;

    @BeforeEach
    void setUp() {
        validar = new Validar();
    }

    // -------------------------------------------------------------------------
    // isValidXml(String xsd, String xml)
    // -------------------------------------------------------------------------

    @Test
    void isValidXml_xsdInexistente_retornaFalso() {
        assertFalse(validar.isValidXml("/caminho/nao/existe.xsd", XML_STATUS_VALIDO));
    }

    @Test
    void isValidXml_xmlValidoComXsdReal_retornaVerdadeiro() {
        assertTrue(validar.isValidXml(STATUS_XSD, XML_STATUS_VALIDO));
    }

    @Test
    void isValidXml_xmlInvalidoSemCampoObrigatorio_retornaFalso() {
        assertFalse(validar.isValidXml(STATUS_XSD, XML_STATUS_SEM_CUF));
    }

    @Test
    void isValidXml_xmlMalformado_retornaFalso() {
        assertFalse(validar.isValidXml(STATUS_XSD, XML_MALFORMADO));
    }

    @Test
    void isValidXml_xmlVazio_retornaFalso() {
        assertFalse(validar.isValidXml(STATUS_XSD, XML_VAZIO));
    }

    // -------------------------------------------------------------------------
    // isValidXml(String pastaSchemas, String xml, ServicosEnum servico)
    // -------------------------------------------------------------------------

    @Test
    void isValidXml_comPastaEServico_xmlValido_retornaVerdadeiro() {
        assertTrue(validar.isValidXml(SCHEMAS_PATH, XML_STATUS_VALIDO, ServicosEnum.STATUS_SERVICO));
    }

    @Test
    void isValidXml_comPastaEServico_xmlInvalido_retornaFalso() {
        assertFalse(validar.isValidXml(SCHEMAS_PATH, XML_STATUS_SEM_CUF, ServicosEnum.STATUS_SERVICO));
    }

    @Test
    void isValidXml_comPastaInexistente_retornaFalso() {
        assertFalse(validar.isValidXml("/pasta/invalida", XML_STATUS_VALIDO, ServicosEnum.STATUS_SERVICO));
    }

    // -------------------------------------------------------------------------
    // isValidXml(ConfiguracoesNfe config, String xml, ServicosEnum servico)
    // -------------------------------------------------------------------------

    @Test
    void isValidXml_comConfigSemPastaSchemas_retornaFalso() {
        ConfiguracoesNfe config = new ConfiguracoesNfe(); // pastaSchemas == null
        assertFalse(validar.isValidXml(config, XML_STATUS_VALIDO, ServicosEnum.STATUS_SERVICO));
    }

    @Test
    void isValidXml_comConfigComPastaValida_xmlValido_retornaVerdadeiro() throws Exception {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        // Injetar pastaSchemas via reflexão (setter é privado)
        java.lang.reflect.Field campo = ConfiguracoesNfe.class.getDeclaredField("pastaSchemas");
        campo.setAccessible(true);
        campo.set(config, SCHEMAS_PATH);

        assertTrue(validar.isValidXml(config, XML_STATUS_VALIDO, ServicosEnum.STATUS_SERVICO));
    }

    @Test
    void isValidXml_comConfigComPastaValida_xmlInvalido_retornaFalso() throws Exception {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        java.lang.reflect.Field campo = ConfiguracoesNfe.class.getDeclaredField("pastaSchemas");
        campo.setAccessible(true);
        campo.set(config, SCHEMAS_PATH);

        assertFalse(validar.isValidXml(config, XML_STATUS_SEM_CUF, ServicosEnum.STATUS_SERVICO));
    }

    // -------------------------------------------------------------------------
    // ErrorHandler — error(), fatalError(), warning()
    // -------------------------------------------------------------------------

    @Test
    void error_comMensagemContada_naoLancaExcecao() {
        // "cvc-type.3.1.3:" é contado como erro (não está nos filtros de isError)
        assertDoesNotThrow(() ->
                validar.error(new SAXParseException("cvc-type.3.1.3: campo inválido", null)));
    }

    @Test
    void error_comMensagemIgnorada_enumeracao_naoLancaExcecao() {
        // "cvc-enumeration-valid:" é filtrado pelo isError() e não conta como erro
        assertDoesNotThrow(() ->
                validar.error(new SAXParseException("cvc-enumeration-valid: valor invalido", null)));
    }

    @Test
    void error_comMensagemIgnorada_pattern_naoLancaExcecao() {
        assertDoesNotThrow(() ->
                validar.error(new SAXParseException("cvc-pattern-valid: padrao invalido", null)));
    }

    @Test
    void error_comMensagemIgnorada_maxLength_naoLancaExcecao() {
        assertDoesNotThrow(() ->
                validar.error(new SAXParseException("cvc-maxLength-valid: tamanho excedido", null)));
    }

    @Test
    void error_comMensagemIgnorada_datatype_naoLancaExcecao() {
        assertDoesNotThrow(() ->
                validar.error(new SAXParseException("cvc-datatype-valid: tipo invalido", null)));
    }

    @Test
    void fatalError_naoLancaExcecao() {
        assertDoesNotThrow(() ->
                validar.fatalError(new SAXParseException("erro fatal de parse", null)));
    }

    @Test
    void warning_naoLancaExcecao() {
        assertDoesNotThrow(() ->
                validar.warning(new SAXParseException("aviso de validacao", null)));
    }
}
