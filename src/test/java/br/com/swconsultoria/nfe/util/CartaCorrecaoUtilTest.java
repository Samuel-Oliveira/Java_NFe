package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envcce.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartaCorrecaoUtilTest {

    private static final String CHAVE     = "52230309158456000159550010000731791567812345";
    private static final String CNPJ      = "09158456000159";
    private static final String CORRECAO  = "Correcao no campo de endereco do destinatario";

    private ConfiguracoesNfe config;

    @BeforeEach
    void setUp() {
        config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.GO);
        config.setAmbiente(AmbienteEnum.HOMOLOGACAO);
        config.setZoneId(ZoneId.of("America/Sao_Paulo"));
    }

    private Evento novoCCe() {
        Evento e = new Evento();
        e.setChave(CHAVE);
        e.setCnpj(CNPJ);
        e.setMotivo(CORRECAO);
        e.setSequencia(1);
        e.setDataEvento(LocalDateTime.of(2024, 3, 10, 9, 0, 0));
        return e;
    }

    @Test
    void montaCCe_unico_retornaTEnvEvento() throws NfeException {
        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(novoCCe(), config);
        assertNotNull(resultado);
        assertEquals(1, resultado.getEvento().size());
    }

    @Test
    void montaCCe_tpEvento_ehCCe() throws NfeException {
        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(novoCCe(), config);
        TEvento.InfEvento info = resultado.getEvento().get(0).getInfEvento();
        assertEquals(EventosEnum.CCE.getCodigo(), info.getTpEvento());
    }

    @Test
    void montaCCe_chaveNFe_preenchida() throws NfeException {
        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(novoCCe(), config);
        assertEquals(CHAVE, resultado.getEvento().get(0).getInfEvento().getChNFe());
    }

    @Test
    void montaCCe_correcao_preenchida() throws NfeException {
        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(novoCCe(), config);
        assertEquals(CORRECAO, resultado.getEvento().get(0).getInfEvento().getDetEvento().getXCorrecao());
    }

    @Test
    void montaCCe_descEvento_cartaDeCorrecao() throws NfeException {
        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(novoCCe(), config);
        assertEquals("Carta de Correcao",
                resultado.getEvento().get(0).getInfEvento().getDetEvento().getDescEvento());
    }

    @Test
    void montaCCe_lote_retornaMultiplosEventos() throws NfeException {
        List<Evento> lista = new ArrayList<>();
        lista.add(novoCCe());
        lista.add(novoCCe());

        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(lista, config);
        assertEquals(2, resultado.getEvento().size());
    }

    @Test
    void montaCCe_loteAcimaDe20_lancaExcecao() {
        List<Evento> lista = new ArrayList<>();
        for (int i = 0; i < 21; i++) lista.add(novoCCe());

        assertThrows(NfeException.class, () -> CartaCorrecaoUtil.montaCCe(lista, config));
    }

    @Test
    void montaCCe_idComecaComID() throws NfeException {
        TEnvEvento resultado = CartaCorrecaoUtil.montaCCe(novoCCe(), config);
        assertTrue(resultado.getEvento().get(0).getInfEvento().getId().startsWith("ID"));
    }
}
