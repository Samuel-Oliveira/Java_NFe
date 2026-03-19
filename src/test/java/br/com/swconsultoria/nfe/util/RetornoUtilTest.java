package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TProtNFe;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetornoUtilTest {

    // -------------------------------------------------------------------------
    // isRetornoAssincrono
    // -------------------------------------------------------------------------

    @Test
    void isRetornoAssincrono_loteRecebido_retornaTrue() throws NfeException {
        TRetEnviNFe retorno = criaRetEnviNFe(StatusEnum.LOTE_RECEBIDO.getCodigo(), "Lote recebido");
        assertTrue(RetornoUtil.isRetornoAssincrono(retorno));
    }

    @Test
    void isRetornoAssincrono_loteProcessado_retornaFalse() throws NfeException {
        TRetEnviNFe retorno = criaRetEnviNFeComProt(
                StatusEnum.LOTE_PROCESSADO.getCodigo(), "Lote processado",
                StatusEnum.AUTORIZADO.getCodigo(), "Autorizado");
        assertFalse(RetornoUtil.isRetornoAssincrono(retorno));
    }

    @Test
    void isRetornoAssincrono_cStatErrado_lancaExcecao() {
        TRetEnviNFe retorno = criaRetEnviNFe("999", "Erro generico");
        assertThrows(NfeException.class, () -> RetornoUtil.isRetornoAssincrono(retorno));
    }

    // -------------------------------------------------------------------------
    // validaSincrono
    // -------------------------------------------------------------------------

    @Test
    void validaSincrono_autorizado_naoLancaExcecao() {
        TRetEnviNFe retorno = criaRetEnviNFeComProt(
                StatusEnum.LOTE_PROCESSADO.getCodigo(), "Lote processado",
                StatusEnum.AUTORIZADO.getCodigo(), "Autorizado o uso da NF-e");
        assertDoesNotThrow(() -> RetornoUtil.validaSincrono(retorno));
    }

    @Test
    void validaSincrono_autorizadoForaPrazo_naoLancaExcecao() {
        TRetEnviNFe retorno = criaRetEnviNFeComProt(
                StatusEnum.LOTE_PROCESSADO.getCodigo(), "Lote processado",
                StatusEnum.AUTORIZADO_FORA_PRAZO.getCodigo(), "Autorizado fora do prazo");
        assertDoesNotThrow(() -> RetornoUtil.validaSincrono(retorno));
    }

    @Test
    void validaSincrono_cStatEnvelopeErrado_lancaExcecao() {
        TRetEnviNFe retorno = criaRetEnviNFe("999", "Servico indisponivel");
        assertThrows(NfeException.class, () -> RetornoUtil.validaSincrono(retorno));
    }

    @Test
    void validaSincrono_cStatProtocoloErrado_lancaExcecao() {
        TRetEnviNFe retorno = criaRetEnviNFeComProt(
                StatusEnum.LOTE_PROCESSADO.getCodigo(), "Lote processado",
                "204", "Duplicidade de NF-e");
        assertThrows(NfeException.class, () -> RetornoUtil.validaSincrono(retorno));
    }

    // -------------------------------------------------------------------------
    // validaAssincrono
    // -------------------------------------------------------------------------

    @Test
    void validaAssincrono_loteProcessadoAutorizado_naoLancaExcecao() {
        TRetConsReciNFe retorno = criaRetConsReciNFe(
                StatusEnum.LOTE_PROCESSADO.getCodigo(), "Lote processado",
                StatusEnum.AUTORIZADO.getCodigo(), "Autorizado");
        assertDoesNotThrow(() -> RetornoUtil.validaAssincrono(retorno));
    }

    @Test
    void validaAssincrono_loteEmProcessamento_lancaExcecao() {
        TRetConsReciNFe retorno = new TRetConsReciNFe();
        retorno.setCStat(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo());
        retorno.setXMotivo("Lote em processamento");
        assertThrows(NfeException.class, () -> RetornoUtil.validaAssincrono(retorno));
    }

    @Test
    void validaAssincrono_protocoloComErro_lancaExcecao() {
        TRetConsReciNFe retorno = criaRetConsReciNFe(
                StatusEnum.LOTE_PROCESSADO.getCodigo(), "Lote processado",
                "204", "Duplicidade de NF-e");
        assertThrows(NfeException.class, () -> RetornoUtil.validaAssincrono(retorno));
    }

    // -------------------------------------------------------------------------
    // validaCancelamento
    // -------------------------------------------------------------------------

    @Test
    void validaCancelamento_eventoVinculado_naoLancaExcecao() {
        TRetEnvEvento retorno = criaRetEnvEvento(
                StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo(),
                StatusEnum.EVENTO_VINCULADO.getCodigo());
        assertDoesNotThrow(() -> RetornoUtil.validaCancelamento(retorno));
    }

    @Test
    void validaCancelamento_cancelamentoForaPrazo_naoLancaExcecao() {
        TRetEnvEvento retorno = criaRetEnvEvento(
                StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo(),
                StatusEnum.CANCELAMENTO_FORA_PRAZO.getCodigo());
        assertDoesNotThrow(() -> RetornoUtil.validaCancelamento(retorno));
    }

    @Test
    void validaCancelamento_envelopeErrado_lancaExcecao() {
        TRetEnvEvento retorno = criaRetEnvEvento("999", StatusEnum.EVENTO_VINCULADO.getCodigo());
        assertThrows(NfeException.class, () -> RetornoUtil.validaCancelamento(retorno));
    }

    @Test
    void validaCancelamento_eventoErrado_lancaExcecao() {
        TRetEnvEvento retorno = criaRetEnvEvento(
                StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo(), "999");
        assertThrows(NfeException.class, () -> RetornoUtil.validaCancelamento(retorno));
    }

    // -------------------------------------------------------------------------
    // validaInutilizacao
    // -------------------------------------------------------------------------

    @Test
    void validaInutilizacao_inutilizado_naoLancaExcecao() {
        TRetInutNFe retorno = new TRetInutNFe();
        TRetInutNFe.InfInut infInut = new TRetInutNFe.InfInut();
        infInut.setCStat(StatusEnum.INUTILIZADO.getCodigo());
        infInut.setXMotivo("Inutilizacao de numero de NF-e homologada");
        retorno.setInfInut(infInut);

        assertDoesNotThrow(() -> RetornoUtil.validaInutilizacao(retorno));
    }

    @Test
    void validaInutilizacao_cStatErrado_lancaExcecao() {
        TRetInutNFe retorno = new TRetInutNFe();
        TRetInutNFe.InfInut infInut = new TRetInutNFe.InfInut();
        infInut.setCStat("999");
        infInut.setXMotivo("Erro");
        retorno.setInfInut(infInut);

        assertThrows(NfeException.class, () -> RetornoUtil.validaInutilizacao(retorno));
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private TRetEnviNFe criaRetEnviNFe(String cStat, String xMotivo) {
        TRetEnviNFe retorno = new TRetEnviNFe();
        retorno.setCStat(cStat);
        retorno.setXMotivo(xMotivo);
        return retorno;
    }

    private TRetEnviNFe criaRetEnviNFeComProt(String cStatEnvelope, String xMotivoEnvelope,
                                               String cStatProt, String xMotivoProt) {
        TRetEnviNFe retorno = criaRetEnviNFe(cStatEnvelope, xMotivoEnvelope);

        br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe protNFe =
                new br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe();
        br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.InfProt infProt =
                new br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.InfProt();
        infProt.setCStat(cStatProt);
        infProt.setXMotivo(xMotivoProt);
        protNFe.setInfProt(infProt);
        retorno.setProtNFe(protNFe);

        return retorno;
    }

    private TRetConsReciNFe criaRetConsReciNFe(String cStatEnvelope, String xMotivoEnvelope,
                                                String cStatProt, String xMotivoProt) {
        TRetConsReciNFe retorno = new TRetConsReciNFe();
        retorno.setCStat(cStatEnvelope);
        retorno.setXMotivo(xMotivoEnvelope);

        TProtNFe protNFe = new TProtNFe();
        TProtNFe.InfProt infProt = new TProtNFe.InfProt();
        infProt.setCStat(cStatProt);
        infProt.setXMotivo(xMotivoProt);
        infProt.setChNFe("52240310732644000128550010000000011234567890");
        protNFe.setInfProt(infProt);
        retorno.getProtNFe().add(protNFe);

        return retorno;
    }

    private TRetEnvEvento criaRetEnvEvento(String cStatEnvelope, String cStatEvento) {
        TRetEnvEvento retorno = new TRetEnvEvento();
        retorno.setCStat(cStatEnvelope);
        retorno.setXMotivo("Lote de Evento Processado");

        TRetEvento retEvento = new TRetEvento();
        TRetEvento.InfEvento infEvento = new TRetEvento.InfEvento();
        infEvento.setCStat(cStatEvento);
        infEvento.setXMotivo("Evento registrado");
        infEvento.setChNFe("52240310732644000128550010000000011234567890");
        retEvento.setInfEvento(infEvento);
        retorno.getRetEvento().add(retEvento);

        return retorno;
    }
}
