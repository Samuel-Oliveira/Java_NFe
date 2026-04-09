package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.util.StubUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRetAutorizacao.NFeRetAutorizacao4Stub;
import mockit.Mock;
import mockit.MockUp;
import org.apache.axiom.om.util.AXIOMUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsultaReciboTest {

    private static final String RET_RECIBO_XML =
            "<retConsReciNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
            "<tpAmb>2</tpAmb><cStat>104</cStat><xMotivo>Lote processado</xMotivo>" +
            "<cUF>35</cUF>" +
            "</retConsReciNFe>";

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

    private void mockReciboStub() {
        new MockUp<NFeRetAutorizacao4Stub>() {
            @Mock
            public void $init(String endpoint) { }

            @Mock
            public NFeRetAutorizacao4Stub.NfeResultMsg nfeRetAutorizacaoLote(
                    NFeRetAutorizacao4Stub.NfeDadosMsg data) throws Exception {
                NFeRetAutorizacao4Stub.NfeResultMsg result = new NFeRetAutorizacao4Stub.NfeResultMsg();
                result.setExtraElement(AXIOMUtil.stringToOM(RET_RECIBO_XML));
                return result;
            }
        };
    }

    @Test
    void reciboNfe_reciboValido_retornaLoteProcessado() throws NfeException {
        mockStubUtil();
        mockReciboStub();

        TRetConsReciNFe ret = ConsultaRecibo.reciboNfe(config, "135240000000001", DocumentoEnum.NFE);

        assertNotNull(ret);
        assertEquals("104", ret.getCStat());
    }

    @Test
    void reciboNfe_retornaMotivo() throws NfeException {
        mockStubUtil();
        mockReciboStub();

        TRetConsReciNFe ret = ConsultaRecibo.reciboNfe(config, "135240000000001", DocumentoEnum.NFE);

        assertEquals("Lote processado", ret.getXMotivo());
    }

    @Test
    void reciboNfe_nfce_retornaResultado() throws NfeException {
        mockStubUtil();
        mockReciboStub();

        TRetConsReciNFe ret = ConsultaRecibo.reciboNfe(config, "135240000000001", DocumentoEnum.NFCE);

        assertNotNull(ret);
        assertEquals("104", ret.getCStat());
    }
}
