package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancelamentoUtilTest {

    private static final String CHAVE = "52230309158456000159550010000731791567812345";
    private static final String CNPJ  = "09158456000159";
    private static final String PROTOCOLO = "352230000123456";
    private static final String MOTIVO    = "Cancelamento por erro na emissao";

    private ConfiguracoesNfe config;

    @BeforeEach
    void setUp() {
        config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.GO);
        config.setAmbiente(AmbienteEnum.HOMOLOGACAO);
        config.setZoneId(ZoneId.of("America/Sao_Paulo"));
    }

    private Evento novoEvento() {
        Evento e = new Evento();
        e.setChave(CHAVE);
        e.setCnpj(CNPJ);
        e.setProtocolo(PROTOCOLO);
        e.setMotivo(MOTIVO);
        e.setDataEvento(LocalDateTime.of(2024, 1, 15, 10, 0, 0));
        return e;
    }

    @Test
    void montaCancelamento_unico_retornaTEnvEvento() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        assertNotNull(resultado);
        assertEquals(1, resultado.getEvento().size());
    }

    @Test
    void montaCancelamento_tpEvento_ehCancelamento() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        TEvento.InfEvento info = resultado.getEvento().get(0).getInfEvento();
        assertEquals(EventosEnum.CANCELAMENTO.getCodigo(), info.getTpEvento());
    }

    @Test
    void montaCancelamento_chaveNFe_preenchida() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        assertEquals(CHAVE, resultado.getEvento().get(0).getInfEvento().getChNFe());
    }

    @Test
    void montaCancelamento_idComecaComID() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        String id = resultado.getEvento().get(0).getInfEvento().getId();
        assertTrue(id.startsWith("ID"));
    }

    @Test
    void montaCancelamento_protocolo_preenchido() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        assertEquals(PROTOCOLO, resultado.getEvento().get(0).getInfEvento().getDetEvento().getNProt());
    }

    @Test
    void montaCancelamento_justificativa_preenchida() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        assertEquals(MOTIVO, resultado.getEvento().get(0).getInfEvento().getDetEvento().getXJust());
    }

    @Test
    void montaCancelamento_lote_retornaMultiplosEventos() throws NfeException {
        List<Evento> lista = new ArrayList<>();
        lista.add(novoEvento());
        lista.add(novoEvento());

        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(lista, config);
        assertEquals(2, resultado.getEvento().size());
    }

    @Test
    void montaCancelamento_loteAcimaDe20_lancaExcecao() {
        List<Evento> lista = new ArrayList<>();
        for (int i = 0; i < 21; i++) lista.add(novoEvento());

        assertThrows(NfeException.class, () -> CancelamentoUtil.montaCancelamento(lista, config));
    }

    @Test
    void montaCancelamento_ambienteHomologacao_preenchido() throws NfeException {
        TEnvEvento resultado = CancelamentoUtil.montaCancelamento(novoEvento(), config);
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(),
                resultado.getEvento().get(0).getInfEvento().getTpAmb());
    }
}
