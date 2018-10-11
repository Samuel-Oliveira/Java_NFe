package br.com.samuelweb.nfe.util;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil.SERVICOS;

public class WebServiceUtilTest {

	private static ConfiguracoesIniciaisNfe config;
	private static Certificado cert;

	@BeforeClass
	public static void ini() {
		cert = new Certificado();
		config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.MG, "2", cert, "", false);
	}

	private void validaUrl(String tipo) {
		System.out.println("=================" + tipo + "=====================");

		for (Estados e : Estados.values()) {
			boolean ok = true;
			config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(e, "2", cert, "", false);
			for (SERVICOS s : SERVICOS.values()) {
				try {
					// if(e.equals(Estados.RO)) {
					// System.out.println("aqui");
					// }
					WebServiceUtil.getUrl(config, tipo, s.name());
				} catch (NfeException ex) {
					if (tipo.toUpperCase().equals("NFE")) {
						// nfe nao tem esses serviços de nfce
						if (s.equals(SERVICOS.URL_QRCODE)) {
							assertTrue(true);// serviço da nfce
						} else if (s.equals(SERVICOS.URL_CONSULTANFCE)) {
							assertTrue(true);// serviço da nfce
						} else if (s.equals(SERVICOS.CSC)) {
							assertTrue(true);// serviço da nfce
						} else
						// serviço so existe para DF
						if (s.equals(SERVICOS.DISTRIBUICAO_DFE) && !e.equals(Estados.DF)) {
							assertTrue(true);// serviço so existe para DF
						} else {
							// aqui sera verificado por UF
							switch (e) {
							case PA:
								if (s.equals(SERVICOS.CONSULTA_CADASTRO)) {
									assertTrue(true);// serviço não existe para UF PA
								} else {
									ok = false;
									ex.printStackTrace();
									assertTrue(false);
								}
								break;
							case MA:
								if (s.equals(SERVICOS.CONSULTA_CADASTRO)) {
									assertTrue(true);// serviço não existe para UF MA
								} else {
									ok = false;
									ex.printStackTrace();
									assertTrue(false);
								}
								break;
							default:
								ok = false;
								ex.printStackTrace();
								assertTrue(false);
								break;
							}
						}
					} else {
						// nfce nao tem esse serviço
						if (s.equals(SERVICOS.CONSULTA_CADASTRO)) {
							assertTrue(true);
						} else
						// valida somente serviço CSC pq maioria da uf de nfce nao tem esse servico. assim fica menos codigo
						if (s.equals(SERVICOS.CSC)) {
							switch (e) {
							case RO: case AC: case RR: case PA: case AP: case TO:
							case MA: case PI: case RN: case PB: case PE: case AL:
							case SE: case BA: case ES: case RJ:	case SP: case PR:
							case RS: case MS: case MT: case GO: case DF:	
							
							// estados sem nfce	
							case MG: case CE:	case SC:
								assertTrue(true);
								break;
							default:
								ok = false;
								ex.printStackTrace();
								assertTrue(false);
								break;
							}
						} else {
							// valida restante dos serviços por UFs.
							switch (e) {
							case RO:
								if (s.equals(SERVICOS.CONSULTA_CADASTRO)) {
									assertTrue(true);// UF não tem esse serviço
								} else {
									ok = false;
									ex.printStackTrace();
									assertTrue(false);
								}
								break;
							case CE:
								if ((s.equals(SERVICOS.URL_QRCODE)) || (s.equals(SERVICOS.URL_CONSULTANFCE))) {
									// se o erro for nestes dois serviços entao realmente e erro.
									ok = false;
									ex.printStackTrace();
									assertTrue(false);
								} else {
									assertTrue(true);// UF não usa nfce, tem apenas dois serviços
								}
								break;
							case MG:
								assertTrue(true);// UF não tem nfce
								break;
							case SC:
								// sc nao tem nfce, acho que deveria ser igual MG, inclusive propert
								// nao alterei propert para manter compatibilidade e aqui para nao quebrar o test
								if (s.equals(SERVICOS.URL_QRCODE)) {
									assertTrue(true);// UF não tem esse serviço
								} else if (s.equals(SERVICOS.URL_CONSULTANFCE)) {
									assertTrue(true);// UF não tem esse serviço
								} else {
									ok = false;
									ex.printStackTrace();
									assertTrue(false);
								}
								break;	
							default:
								ok = false;
								ex.printStackTrace();
								assertTrue(false);
								break;
							}

						}
					}
				}
			}
			if (ok) {
				System.out.println("Todos os serviços do estado " + e + " contém urls.");
			} else {
				System.out.println("Alguns serviços do estado " + e + " contém erros.");
			}
		}
	}

	@Test
	public void testUrlsNFe() {
		validaUrl("NFE");
	}

	@Test
	public void testUrlsNFCe() {
		validaUrl("NFCE");
	}
	
	@Test
	public void testUrlsRetornadaNFe() {
		//valida se estou retornando a url do estado 
		//e não a url do "Usar" que deve ser usado somente se não 
		//encontrar o serviço especifico na UF
		try {
			config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.ES, "2", cert, "", false);
			String url = WebServiceUtil.getUrl(config, "NFE", "CONSULTA_CADASTRO");
			if(!url.contains("sefaz.es")) {
				//senao contem esta string que esta na url entao não retornou a correta.
				assertTrue(false);
			}
		}catch (NfeException ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
		
	}
}
