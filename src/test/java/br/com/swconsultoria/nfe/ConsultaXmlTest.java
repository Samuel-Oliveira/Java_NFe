package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.StubUtil;
import br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub;
import mockit.Mock;
import mockit.MockUp;
import org.apache.axiom.om.util.AXIOMUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsultaXmlTest {

    private static final String CHAVE = "35240101234567890001550010000000011000000012";

    private static final String RET_CONSULTA_XML =
            "<retConsSitNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
            "<tpAmb>2</tpAmb><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo>" +
            "<cUF>35</cUF><dhRecbto>2024-01-01T10:00:00-03:00</dhRecbto>" +
            "</retConsSitNFe>";

    private ConfiguracoesNfe config;

    @BeforeEach
    void setUp() {
        config = new ConfiguracoesNfe();
        config.setEstado(EstadosEnum.SP);
        config.setAmbiente(AmbienteEnum.HOMOLOGACAO);
        config.setEncode("UTF-8");
    }

    private void mockStubUtil() {
        new MockUp<StubUtil>() {
            @Mock
            public void configuraHttpClient(org.apache.axis2.client.Stub stub,
                    ConfiguracoesNfe cfg, String url) { }
        };
    }

    private void mockConsultaStub() {
        new MockUp<NFeConsultaProtocolo4Stub>() {
            @Mock
            public void $init(String endpoint) { }

            @Mock
            public NFeConsultaProtocolo4Stub.NfeResultMsg nfeConsultaNF(
                    NFeConsultaProtocolo4Stub.NfeDadosMsg data) throws Exception {
                NFeConsultaProtocolo4Stub.NfeResultMsg result = new NFeConsultaProtocolo4Stub.NfeResultMsg();
                result.setExtraElement(AXIOMUtil.stringToOM(RET_CONSULTA_XML));
                return result;
            }
        };
    }

    @Test
    void consultaXml_chaveValida_retornaResultado() throws NfeException {
        mockStubUtil();
        mockConsultaStub();

        TRetConsSitNFe ret = ConsultaXml.consultaXml(config, CHAVE, DocumentoEnum.NFE);

        assertNotNull(ret);
        assertEquals("100", ret.getCStat());
    }

    @Test
    void consultaXml_retornaMotivo() throws NfeException {
        mockStubUtil();
        mockConsultaStub();

        TRetConsSitNFe ret = ConsultaXml.consultaXml(config, CHAVE, DocumentoEnum.NFE);

        assertEquals("Autorizado o uso da NF-e", ret.getXMotivo());
    }

    @Test
    void consultaXml_retornaAmbienteHomologacao() throws NfeException {
        mockStubUtil();
        mockConsultaStub();

        TRetConsSitNFe ret = ConsultaXml.consultaXml(config, CHAVE, DocumentoEnum.NFE);

        assertEquals("2", ret.getTpAmb());
    }
}
