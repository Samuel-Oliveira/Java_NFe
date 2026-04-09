package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.util.StubUtil;
import br.com.swconsultoria.nfe.wsdl.NFeDistribuicaoDFe.NFeDistribuicaoDFeStub;
import mockit.Mock;
import mockit.MockUp;
import org.apache.axiom.om.util.AXIOMUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DistribuicaoDFeTest {

    private static final String RET_DIST_XML =
            "<retDistDFeInt versao=\"1.01\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
            "<tpAmb>2</tpAmb><verAplic>SVRS</verAplic>" +
            "<cStat>137</cStat><xMotivo>Nenhum documento localizado</xMotivo>" +
            "<dhResp>2024-01-01T10:00:00-03:00</dhResp>" +
            "<ultNSU>000000000000000</ultNSU><maxNSU>000000000000000</maxNSU>" +
            "</retDistDFeInt>";

    private static final String CNPJ = "09158456000159";

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

    private void mockDistStub() {
        new MockUp<NFeDistribuicaoDFeStub>() {
            @Mock
            public void $init(String endpoint) { }

            @Mock
            public NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse nfeDistDFeInteresse(
                    NFeDistribuicaoDFeStub.NfeDistDFeInteresse data) throws Exception {
                NFeDistribuicaoDFeStub.NfeDistDFeInteresseResult_type0 resultType0 =
                        new NFeDistribuicaoDFeStub.NfeDistDFeInteresseResult_type0();
                resultType0.setExtraElement(AXIOMUtil.stringToOM(RET_DIST_XML));

                NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse response =
                        new NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse();
                response.setNfeDistDFeInteresseResult(resultType0);
                return response;
            }
        };
    }

    @Test
    void consultaNfe_comNsu_retornaDistribuicao() throws NfeException {
        mockStubUtil();
        mockDistStub();

        RetDistDFeInt ret = DistribuicaoDFe.consultaNfe(config, PessoaEnum.JURIDICA, CNPJ,
                ConsultaDFeEnum.NSU, "000000000000000");

        assertNotNull(ret);
        assertEquals("137", ret.getCStat());
    }

    @Test
    void consultaNfe_comChave_retornaDistribuicao() throws NfeException {
        mockStubUtil();
        mockDistStub();

        RetDistDFeInt ret = DistribuicaoDFe.consultaNfe(config, PessoaEnum.JURIDICA, CNPJ,
                ConsultaDFeEnum.CHAVE, "35240101234567890001550010000000011000000012");

        assertNotNull(ret);
        assertEquals("Nenhum documento localizado", ret.getXMotivo());
    }

    @Test
    void consultaNfe_comCpf_retornaDistribuicao() throws NfeException {
        mockStubUtil();
        mockDistStub();

        RetDistDFeInt ret = DistribuicaoDFe.consultaNfe(config, PessoaEnum.FISICA, "12345678901",
                ConsultaDFeEnum.NSU, "000000000000000");

        assertNotNull(ret);
        assertEquals("2", ret.getTpAmb());
    }
}
