package br.com.swconsultoria.nfe;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;

public class NfeTest {
	static String pathCertificado;
	static Certificado certificado;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		URI uri = NfeTest.class.getClassLoader().getResource("CertificadoTesteCNPJ.pfx").toURI();
		pathCertificado = Paths.get(uri).toString();
		certificado = CertificadoService.certificadoPfx(pathCertificado, "123456");
	}

	@Test
	public final void testDistribuicaoDfe() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testStatusServicoConfiguracoesNfeDocumentoEnumString() {
		assertTrue("Certificado não foi carregado",certificado.getCnpjCpf().equals("99999999999999"));
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testConsultaXml() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testConsultaCadastro() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testConsultaRecibo() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testInutilizacaoConfiguracoesNfeTInutNFeDocumentoEnumBooleanString() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testMontaNfe() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testEnviarNfeConfiguracoesNfeTEnviNFeDocumentoEnumString() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testCancelarNfeConfiguracoesNfeTEnvEventoBooleanDocumentoEnumString() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testCancelarSubstituicaoNfeConfiguracoesNfeTEnvEventoBooleanString() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testEnviarEpecConfiguracoesNfeTEnvEventoBooleanString() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testCceConfiguracoesNfeTEnvEventoBooleanString() {
		//TODO fail("Not yet implemented");
	}

	@Test
	public final void testManifestacaoConfiguracoesNfeTEnvEventoBooleanString() {
		//TODO fail("Not yet implemented");
	}

}
