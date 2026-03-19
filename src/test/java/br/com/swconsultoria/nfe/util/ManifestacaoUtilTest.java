package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ManifestacaoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envConfRecebto.TEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManifestacaoUtilTest {

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

    private Evento novoEvento(ManifestacaoEnum tipo) {
        Evento e = new Evento();
        e.setChave(CHAVE);
        e.setCnpj(CNPJ);
        e.setTipoManifestacao(tipo);
        e.setSequencia(1);
        e.setDataEvento(LocalDateTime.of(2024, 4, 1, 12, 0, 0));
        return e;
    }

    @Test
    void montaManifestacao_confirmacao_retornaTEnvEvento() throws NfeException {
        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(
                novoEvento(ManifestacaoEnum.CONFIRMACAO_DA_OPERACAO), config);
        assertNotNull(resultado);
        assertEquals(1, resultado.getEvento().size());
    }

    @Test
    void montaManifestacao_tpEvento_confirmacao() throws NfeException {
        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(
                novoEvento(ManifestacaoEnum.CONFIRMACAO_DA_OPERACAO), config);
        TEvento.InfEvento info = resultado.getEvento().get(0).getInfEvento();
        assertEquals(ManifestacaoEnum.CONFIRMACAO_DA_OPERACAO.getCodigo(), info.getTpEvento());
    }

    @Test
    void montaManifestacao_ciencia_tpEvento() throws NfeException {
        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(
                novoEvento(ManifestacaoEnum.CIENCIA_DA_OPERACAO), config);
        assertEquals(ManifestacaoEnum.CIENCIA_DA_OPERACAO.getCodigo(),
                resultado.getEvento().get(0).getInfEvento().getTpEvento());
    }

    @Test
    void montaManifestacao_desconhecimento_tpEvento() throws NfeException {
        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(
                novoEvento(ManifestacaoEnum.DESCONHECIMENTO_DA_OPERACAO), config);
        assertEquals(ManifestacaoEnum.DESCONHECIMENTO_DA_OPERACAO.getCodigo(),
                resultado.getEvento().get(0).getInfEvento().getTpEvento());
    }

    @Test
    void montaManifestacao_operacaoNaoRealizada_comJustificativa() throws NfeException {
        Evento e = novoEvento(ManifestacaoEnum.OPERACAO_NAO_REALIZADA);
        e.setMotivo("Mercadoria nao recebida");

        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(e, config);
        TEvento.InfEvento.DetEvento det = resultado.getEvento().get(0).getInfEvento().getDetEvento();
        assertEquals("Mercadoria nao recebida", det.getXJust());
    }

    @Test
    void montaManifestacao_sequenciaZero_usaUm() throws NfeException {
        Evento e = novoEvento(ManifestacaoEnum.CONFIRMACAO_DA_OPERACAO);
        e.setSequencia(0); // deve ser corrigido para 1

        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(e, config);
        assertEquals("1", resultado.getEvento().get(0).getInfEvento().getNSeqEvento());
    }

    @Test
    void montaManifestacao_lote_retornaMultiplosEventos() throws NfeException {
        List<Evento> lista = new ArrayList<>();
        lista.add(novoEvento(ManifestacaoEnum.CONFIRMACAO_DA_OPERACAO));
        lista.add(novoEvento(ManifestacaoEnum.CIENCIA_DA_OPERACAO));

        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(lista, config);
        assertEquals(2, resultado.getEvento().size());
    }

    @Test
    void montaManifestacao_loteAcimaDe20_lancaExcecao() {
        List<Evento> lista = new ArrayList<>();
        for (int i = 0; i < 21; i++)
            lista.add(novoEvento(ManifestacaoEnum.CIENCIA_DA_OPERACAO));

        assertThrows(NfeException.class, () -> ManifestacaoUtil.montaManifestacao(lista, config));
    }

    @Test
    void montaManifestacao_chaveNFe_preenchida() throws NfeException {
        TEnvEvento resultado = ManifestacaoUtil.montaManifestacao(
                novoEvento(ManifestacaoEnum.CONFIRMACAO_DA_OPERACAO), config);
        assertEquals(CHAVE, resultado.getEvento().get(0).getInfEvento().getChNFe());
    }
}
