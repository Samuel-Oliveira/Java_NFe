package br.com.swconsultoria.nfe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.mock.MockImp;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.InutilizacaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;

final class NfeTest {

    private static ConfiguracoesNfe configuracoesNfe;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    	Mock mock = new MockImp();
    	
        URI uri = Objects.requireNonNull(NfeTest.class.getClassLoader().getResource("CertificadoTesteCNPJ.pfx")).toURI();
        Certificado certificado = CertificadoService.certificadoPfx(
                Paths.get(uri).toString(), "123456");
        configuracoesNfe = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO, certificado, "");
        
        configuracoesNfe.setMockStubs(mock);
        configuracoesNfe.setMocked(true);
    }

    @Test
    void testeStatusServico() throws Exception {        

        TRetConsStatServ retorno = Nfe.statusServico(configuracoesNfe, DocumentoEnum.NFE);

        assertEquals(StatusEnum.SERVICO_EM_OPERACAO.getCodigo(), retorno.getCStat());
        assertEquals(ConstantesUtil.VERSAO.NFE, retorno.getVersao());
        assertEquals(configuracoesNfe.getEstado().getCodigoUF(), retorno.getCUF());
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), retorno.getTpAmb());
    }

    private static TEnvEvento criaEventoCancelamento(ConfiguracoesNfe configuracoesNfe) throws NfeException {

        Evento cancela = new Evento();
        cancela.setChave("99999999999999999999999999999999999999999999");
        cancela.setProtocolo("000000000000000");
        cancela.setCnpj(configuracoesNfe.getCertificado().getCnpjCpf());
        cancela.setMotivo("Teste de Cancelamento");
        cancela.setDataEvento(LocalDateTime.now());
        return CancelamentoUtil.montaCancelamento(cancela, configuracoesNfe, ZoneId.systemDefault());
    }
    
    @Test
    void testeCancelamento() throws Exception {

        TRetEnvEvento retorno = Nfe.cancelarNfe(configuracoesNfe, criaEventoCancelamento(configuracoesNfe), false, DocumentoEnum.NFE);

        RetornoUtil.validaCancelamento(retorno);
        assertEquals(StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo(), retorno.getCStat());
        assertEquals(configuracoesNfe.getEstado().getCodigoUF(), retorno.getCOrgao());
        assertEquals(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO, retorno.getVersao());
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), retorno.getTpAmb());
        assertEquals(StatusEnum.EVENTO_VINCULADO.getCodigo(), retorno.getRetEvento().get(0).getInfEvento().getCStat());
    }
    
    @Test
    void testeInutilizacao() throws Exception {
    	TInutNFe inutNFe = InutilizacaoUtil.montaInutilizacao(DocumentoEnum.NFE, configuracoesNfe.getCertificado().getCnpjCpf(), 1, 100, 100, "motivo teste", LocalDateTime.now(), configuracoesNfe);
    	TRetInutNFe retorno = Nfe.inutilizacao(configuracoesNfe, inutNFe, DocumentoEnum.NFE, false);
    	assertEquals(StatusEnum.INUTILIZADO.getCodigo(), retorno.getInfInut().getCStat());
    }
    
    @Test
    void testeEnvio() throws Exception {
    	//monta enviNFe conforme exemplo wiki: https://github.com/Samuel-Oliveira/Java_NFe/wiki/04-:-Envio-Nfe
    	TEnviNFe enviNFe = EnvioNfeTeste.getEnviNFe(configuracoesNfe); 
    	
    	TRetEnviNFe retorno = Nfe.enviarNfe(configuracoesNfe, enviNFe, DocumentoEnum.NFE);
    	assertEquals(StatusEnum.LOTE_PROCESSADO.getCodigo(), retorno.getCStat());
    	assertEquals(StatusEnum.AUTORIZADO.getCodigo(), retorno.getProtNFe().getInfProt().getCStat());
    }
}
