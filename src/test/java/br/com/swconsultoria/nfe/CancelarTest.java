package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.StubUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import mockit.Mock;
import mockit.MockUp;
import org.apache.axiom.om.util.AXIOMUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CancelarTest {

    private static final String RET_EVENTO_XML =
            "<retEnvEvento versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
            "<idLote>1</idLote><tpAmb>2</tpAmb><verAplic>TESTE</verAplic>" +
            "<cOrgao>91</cOrgao><cStat>128</cStat>" +
            "<xMotivo>Lote de Evento Processado</xMotivo>" +
            "</retEnvEvento>";

    private ConfiguracoesNfe config;
    private TEnvEvento enviEvento;

    @BeforeEach
    void setUp() throws NfeException {
        config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.SP);
        config.setAmbiente(AmbienteEnum.HOMOLOGACAO);
        config.setEncode("UTF-8");

        config.setZoneId(java.time.ZoneId.of("America/Sao_Paulo"));

        Evento evento = new Evento();
        evento.setChave("52230309158456000159550010000731791567812345");
        evento.setCnpj("09158456000159");
        evento.setProtocolo("352230000123456");
        evento.setMotivo("Cancelamento por erro na emissao do documento fiscal");
        evento.setDataEvento(java.time.LocalDateTime.of(2024, 1, 15, 10, 0, 0));
        enviEvento = CancelamentoUtil.montaCancelamento(evento, config);
    }

    private void mockStubUtil() {
        new MockUp<StubUtil>() {
            @Mock
            public void configuraHttpClient(org.apache.axis2.client.Stub stub,
                    ConfiguracoesNfe cfg, String url) { }
        };
    }

    private void mockAssinar() {
        new MockUp<Assinar>() {
            @Mock
            public String assinaNfe(ConfiguracoesNfe cfg, String xml,
                    AssinaturaEnum tipo) throws NfeException {
                return xml;
            }
        };
    }

    private void mockEventosStub() {
        new MockUp<NFeRecepcaoEvento4Stub>() {
            @Mock
            public void $init(String endpoint) { }

            @Mock
            public NFeRecepcaoEvento4Stub.NfeResultMsg nfeRecepcaoEvento(
                    NFeRecepcaoEvento4Stub.NfeDadosMsg data) throws Exception {
                NFeRecepcaoEvento4Stub.NfeResultMsg result = new NFeRecepcaoEvento4Stub.NfeResultMsg();
                result.setExtraElement(AXIOMUtil.stringToOM(RET_EVENTO_XML));
                return result;
            }
        };
    }

    @Test
    void eventoCancelamento_semValidacao_retornaEvento() throws NfeException {
        mockStubUtil();
        mockAssinar();
        mockEventosStub();

        TRetEnvEvento ret = Cancelar.eventoCancelamento(config, enviEvento, false, DocumentoEnum.NFE);

        assertNotNull(ret);
        assertEquals("128", ret.getCStat());
    }

    @Test
    void eventoCancelamento_retornaLoteProcessado() throws NfeException {
        mockStubUtil();
        mockAssinar();
        mockEventosStub();

        TRetEnvEvento ret = Cancelar.eventoCancelamento(config, enviEvento, false, DocumentoEnum.NFE);

        assertEquals("Lote de Evento Processado", ret.getXMotivo());
    }

    @Test
    void eventoCancelamento_retornaAmbienteHomologacao() throws NfeException {
        mockStubUtil();
        mockAssinar();
        mockEventosStub();

        TRetEnvEvento ret = Cancelar.eventoCancelamento(config, enviEvento, false, DocumentoEnum.NFE);

        assertEquals("2", ret.getTpAmb());
    }
}
