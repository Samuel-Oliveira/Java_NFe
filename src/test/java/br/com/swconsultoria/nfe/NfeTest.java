package br.com.swconsultoria.nfe;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.mock.MockCancelar;
import br.com.swconsultoria.nfe.mock.MockStatus;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;
import mockit.Delegate;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class NfeTest {

    private static ConfiguracoesNfe configuracoesNfe;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        URI uri = Objects.requireNonNull(NfeTest.class.getClassLoader().getResource("CertificadoTesteCNPJ.pfx")).toURI();
        Certificado certificado = CertificadoService.certificadoPfx(
                Paths.get(uri).toString(), "123456");
        configuracoesNfe = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO, certificado, "");
    }

    @Test
    void testeStatusServico(@Mocked NFeStatusServico4Stub stub) throws Exception {

        new Expectations() {{
            stub.nfeStatusServicoNF((NFeStatusServico4Stub.NfeDadosMsg) any);
            result = new Delegate() {
                NFeStatusServico4Stub.NfeResultMsg aDelegateMethod(NFeStatusServico4Stub.NfeDadosMsg dados) throws Exception {
                    return MockStatus.getNfeResultMsg(dados, StatusEnum.SERVICO_EM_OPERACAO.getCodigo(), "Serviço em Operação");
                }
            };
        }};

        TRetConsStatServ retorno = Nfe.statusServico(configuracoesNfe, DocumentoEnum.NFE);

        assertEquals(StatusEnum.SERVICO_EM_OPERACAO.getCodigo(), retorno.getCStat());
        assertEquals(ConstantesUtil.VERSAO.NFE, retorno.getVersao());
        assertEquals(configuracoesNfe.getEstado().getCodigoUF(), retorno.getCUF());
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), retorno.getTpAmb());
    }

    @Test
    void testeCancelamento(@Mocked NFeRecepcaoEvento4Stub stub) throws Exception {

        new Expectations() {{
            stub.nfeRecepcaoEvento((NFeRecepcaoEvento4Stub.NfeDadosMsg) any);
            result = new Delegate() {
                NFeRecepcaoEvento4Stub.NfeResultMsg aDelegateMethod(NFeRecepcaoEvento4Stub.NfeDadosMsg dados) throws Exception {
                    return MockCancelar.getNfeResultMsg(dados , StatusEnum.EVENTO_VINCULADO.getCodigo(), "Evento registrado e vinculado a NF-e");
                }
            };
        }};

        TRetEnvEvento retorno = Nfe.cancelarNfe(configuracoesNfe, MockCancelar.criaEventoCancelamento(configuracoesNfe), false, DocumentoEnum.NFE);

        RetornoUtil.validaCancelamento(retorno);
        assertEquals(StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo(), retorno.getCStat());
        assertEquals(configuracoesNfe.getEstado().getCodigoUF(), retorno.getCOrgao());
        assertEquals(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO, retorno.getVersao());
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), retorno.getTpAmb());
        assertEquals(StatusEnum.EVENTO_VINCULADO.getCodigo(), retorno.getRetEvento().get(0).getInfEvento().getCStat());
    }

}
