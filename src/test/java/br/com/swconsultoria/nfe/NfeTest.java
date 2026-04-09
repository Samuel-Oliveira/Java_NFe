package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de Nfe — fachada principal da biblioteca.
 * Verifica o contrato da API (construtor privado, rejeição de config nula).
 * As chamadas de rede não são testadas aqui (requerem certificado + SEFAZ).
 */
class NfeTest {

    // -------------------------------------------------------------------------
    // Construtor privado
    // -------------------------------------------------------------------------

    @Test
    void nfe_construtorEPrivado() throws Exception {
        Constructor<Nfe> constructor = Nfe.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                "Nfe deve ter construtor privado para impedir instanciação");
    }

    // -------------------------------------------------------------------------
    // Rejeição de ConfiguracoesNfe nula (lança NfeException antes da chamada WS)
    // -------------------------------------------------------------------------

    @Test
    void statusServico_configNula_lancaNfeException() {
        assertThrows(NfeException.class,
                () -> Nfe.statusServico(null, DocumentoEnum.NFE));
    }

    @Test
    void consultaXml_configNula_lancaNfeException() {
        assertThrows(NfeException.class,
                () -> Nfe.consultaXml(null, "52230309158456000159550010000731791567812345", DocumentoEnum.NFE));
    }

    @Test
    void consultaRecibo_configNula_lancaNfeException() {
        assertThrows(NfeException.class,
                () -> Nfe.consultaRecibo(null, "123456789012345", DocumentoEnum.NFE));
    }

    @Test
    void distribuicaoDfe_configNula_lancaNfeException() {
        assertThrows(NfeException.class,
                () -> Nfe.distribuicaoDfe(null, PessoaEnum.JURIDICA, "09158456000159",
                        br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum.NSU, "000000000000001"));
    }

    @Test
    void inutilizacao_configNula_lancaNfeException() {
        br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe inutNFe =
                new br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe();
        br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe.InfInut infInut =
                new br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe.InfInut();
        infInut.setCNPJ("09158456000159");
        inutNFe.setInfInut(infInut);

        assertThrows(NfeException.class,
                () -> Nfe.inutilizacao(null, inutNFe, DocumentoEnum.NFE, false));
    }

    // -------------------------------------------------------------------------
    // Config sem certificado lança NfeException (getCertificado() retorna null)
    // -------------------------------------------------------------------------

    @Test
    void statusServico_configSemCertificado_lancaNfeException() {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(br.com.swconsultoria.nfe.dom.enuns.EstadosEnum.GO);
        config.setAmbiente(br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum.HOMOLOGACAO);

        assertThrows(Exception.class,
                () -> Nfe.statusServico(config, DocumentoEnum.NFE));
    }

    @Test
    void consultaXml_configSemCertificado_lancaNfeException() {
        ConfiguracoesNfe config = new ConfiguracoesNfe();
        config.setEstado(br.com.swconsultoria.nfe.dom.enuns.EstadosEnum.GO);
        config.setAmbiente(br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum.HOMOLOGACAO);

        assertThrows(Exception.class,
                () -> Nfe.consultaXml(config, "52230309158456000159550010000731791567812345", DocumentoEnum.NFE));
    }
}
