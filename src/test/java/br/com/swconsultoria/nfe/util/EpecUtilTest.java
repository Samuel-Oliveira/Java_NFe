package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.EventoEpec;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEpec.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEpec.TEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpecUtilTest {

    private static final String CHAVE = "52230309158456000159550010000731791567812345";
    private static final String CNPJ  = "09158456000159";

    private ConfiguracoesNfe config;

    @BeforeEach
    void setUp() {
        config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.GO);
        config.setAmbiente(AmbienteEnum.HOMOLOGACAO);
        config.setZoneId(ZoneId.of("America/Sao_Paulo"));
    }

    private Evento novoEpec() {
        EventoEpec epecDetalhe = new EventoEpec();
        epecDetalhe.setTipoNF("1");
        epecDetalhe.setIeEmitente("104282215");
        epecDetalhe.setEstadoDestinatario(EstadosEnum.SP);
        epecDetalhe.setCnpjDestinatario("08944957000360");
        epecDetalhe.setvNF("1000.00");
        epecDetalhe.setvICMS("0.00");
        epecDetalhe.setvST("0.00");

        Evento e = new Evento();
        e.setChave(CHAVE);
        e.setCnpj(CNPJ);
        e.setDataEvento(LocalDateTime.of(2024, 2, 28, 15, 30, 0));
        e.setEventoEpec(epecDetalhe);
        return e;
    }

    @Test
    void montaEpec_unico_retornaTEnvEvento() throws NfeException {
        TEnvEvento resultado = EpecUtil.montaEpec(novoEpec(), config);
        assertNotNull(resultado);
        assertEquals(1, resultado.getEvento().size());
    }

    @Test
    void montaEpec_tpEvento_ehEpec() throws NfeException {
        TEnvEvento resultado = EpecUtil.montaEpec(novoEpec(), config);
        TEvento.InfEvento info = resultado.getEvento().get(0).getInfEvento();
        assertEquals(EventosEnum.EPEC.getCodigo(), info.getTpEvento());
    }

    @Test
    void montaEpec_chaveNFe_preenchida() throws NfeException {
        TEnvEvento resultado = EpecUtil.montaEpec(novoEpec(), config);
        assertEquals(CHAVE, resultado.getEvento().get(0).getInfEvento().getChNFe());
    }

    @Test
    void montaEpec_idComecaComID() throws NfeException {
        TEnvEvento resultado = EpecUtil.montaEpec(novoEpec(), config);
        assertTrue(resultado.getEvento().get(0).getInfEvento().getId().startsWith("ID"));
    }

    @Test
    void montaEpec_descEvento_epec() throws NfeException {
        TEnvEvento resultado = EpecUtil.montaEpec(novoEpec(), config);
        assertEquals("EPEC", resultado.getEvento().get(0).getInfEvento().getDetEvento().getDescEvento());
    }

    @Test
    void montaEpec_lote_retornaMultiplosEventos() throws NfeException {
        List<Evento> lista = new ArrayList<>();
        lista.add(novoEpec());
        lista.add(novoEpec());

        TEnvEvento resultado = EpecUtil.montaEpec(lista, config);
        assertEquals(2, resultado.getEvento().size());
    }

    @Test
    void montaEpec_loteAcimaDe20_lancaExcecao() {
        List<Evento> lista = new ArrayList<>();
        for (int i = 0; i < 21; i++) lista.add(novoEpec());

        assertThrows(NfeException.class, () -> EpecUtil.montaEpec(lista, config));
    }

    @Test
    void montaEpec_ambiente_preenchido() throws NfeException {
        TEnvEvento resultado = EpecUtil.montaEpec(novoEpec(), config);
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(),
                resultado.getEvento().get(0).getInfEvento().getTpAmb());
    }
}
