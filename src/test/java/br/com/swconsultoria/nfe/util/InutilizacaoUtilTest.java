package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class InutilizacaoUtilTest {

    private static final String CNPJ          = "09158456000159";
    private static final String JUSTIFICATIVA  = "Numero de NF-e inutilizado por falha de sistema";

    private ConfiguracoesNfe config;
    private final LocalDateTime dataInut = LocalDateTime.of(2024, 5, 20, 8, 0, 0);

    @BeforeEach
    void setUp() {
        config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.GO);
        config.setAmbiente(AmbienteEnum.HOMOLOGACAO);
        config.setZoneId(ZoneId.of("America/Sao_Paulo"));
    }

    @Test
    void montaInutilizacao_retornaTInutNFe() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 100, 110, JUSTIFICATIVA, dataInut, config);

        assertNotNull(resultado);
        assertNotNull(resultado.getInfInut());
    }

    @Test
    void montaInutilizacao_id_comecaComID() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 100, 110, JUSTIFICATIVA, dataInut, config);

        assertTrue(resultado.getInfInut().getId().startsWith("ID"));
    }

    @Test
    void montaInutilizacao_serieFormatada3digitos() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 5, 1, 1, JUSTIFICATIVA, dataInut, config);

        // ID contains zero-padded serie
        String id = resultado.getInfInut().getId();
        assertTrue(id.contains("005"), "ID deve conter serie com 3 digitos: " + id);
    }

    @Test
    void montaInutilizacao_numeroFormatado9digitos() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 42, 50, JUSTIFICATIVA, dataInut, config);

        String id = resultado.getInfInut().getId();
        assertTrue(id.contains("000000042"), "ID deve conter nNFIni com 9 digitos: " + id);
        assertTrue(id.contains("000000050"), "ID deve conter nNFFin com 9 digitos: " + id);
    }

    @Test
    void montaInutilizacao_justificativa_preenchida() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 1, 1, JUSTIFICATIVA, dataInut, config);

        assertEquals(JUSTIFICATIVA, resultado.getInfInut().getXJust());
    }

    @Test
    void montaInutilizacao_cnpj_preenchido() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 1, 1, JUSTIFICATIVA, dataInut, config);

        assertEquals(CNPJ, resultado.getInfInut().getCNPJ());
    }

    @Test
    void montaInutilizacao_xServ_inutilizar() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 1, 1, JUSTIFICATIVA, dataInut, config);

        assertEquals("INUTILIZAR", resultado.getInfInut().getXServ());
    }

    @Test
    void montaInutilizacao_ambiente_homologacao() {
        TInutNFe resultado = InutilizacaoUtil.montaInutilizacao(
                DocumentoEnum.NFE, CNPJ, 1, 1, 1, JUSTIFICATIVA, dataInut, config);

        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), resultado.getInfInut().getTpAmb());
    }
}
