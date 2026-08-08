package br.com.swconsultoria.nfe.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Referência: Informe Técnico 2025.002 v1.60 e tabela cClassTrib publicada em 23/06/2026.
 * https://www.nfe.fazenda.gov.br/portal/listaConteudo.aspx?tipoConteudo=%2FNJarYc9nus%3D
 */
class ClassificacaoTributariaV160Test {

    private static List<CstDTO> csts;

    @BeforeAll
    static void carregarTabelaOficial() throws Exception {
        try (InputStream input = ClassificacaoTributariaV160Test.class.getResourceAsStream("/ibscbs.json")) {
            assertNotNull(input, "Recurso ibscbs.json não encontrado");
            csts = new ObjectMapper().readValue(input, new TypeReference<List<CstDTO>>() { });
        }
    }

    @Test
    void deveConterTodosOsCodigosDaTabelaV160() {
        assertEquals(18, csts.size());
        assertEquals(164, csts.stream()
                .mapToInt(cst -> cst.getClassificacoesTributarias().size())
                .sum());
    }

    @Test
    void deveRespeitarVigenciaEIndicadorNFeOficiais() {
        assertEquals("2026-01-01T00:00:00", classificacao("000001").getInicioVigencia());
        assertFalse(classificacao("550002").getIndNFe());
    }

    @Test
    void deveRespeitarIndicadoresDuimpOficiais() {
        assertTrue(classificacao("410037").getIndDUIMP());
        assertTrue(classificacao("550024").getIndDUIMP());
        assertTrue(classificacao("550025").getIndDUIMP());
    }

    @Test
    void deveRespeitarTipoReceitaBrutaDoSimplesNacional() {
        assertTrue(classificacao("220001").getTipoReceitaBrutaSN().startsWith("9 -"));
        assertTrue(classificacao("220002").getTipoReceitaBrutaSN().startsWith("9 -"));
        assertTrue(classificacao("220003").getTipoReceitaBrutaSN().startsWith("1 -"));
    }

    @Test
    void deveReferenciarArtigoCorretoParaLocacaoDeImovel() {
        assertTrue(classificacao("221001").getLink().trim().endsWith("#art487"));
    }

    private static ClassificacaoTributariaDTO classificacao(String codigo) {
        return csts.stream()
                .flatMap(cst -> cst.getClassificacoesTributarias().stream())
                .filter(item -> codigo.equals(item.getCClassTrib()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("cClassTrib não encontrado: " + codigo));
    }
}
