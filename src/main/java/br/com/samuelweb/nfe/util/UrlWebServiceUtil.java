package br.com.samuelweb.nfe.util;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.samuelweb.nfe.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * Classe reposnsavel Por Retornar o URL do WebService. url = Endere�o do
 * WebService para cada Estado. Ver rela��o dos endere�os em": Para Homologacao":
 * http://hom.nfe.fazenda.gov.br/PORTAL/WebServices.aspx Para Produ��o":
 * http://www.nfe.fazenda.gov.br/portal/WebServices.aspx
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */

//TODO ARRUMAR TODOS OS WEBSERVICES
public class UrlWebServiceUtil {

	private static URL url;
	private static ConfiguracoesIniciaisNfe configuracaoNfe;
	
	//NfeConsultaProtocolo
	public static URL consultaXml() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2?wsdl"); // produção
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homnfe.sefaz.am.gov.br/services2/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.am.gov.br/services2/services/NfeConsulta2"); // produção
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produção
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produção
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produção
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeConsulta2?wsdl"); // produção
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2"); // produção
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/NfeConsulta/NfeConsulta.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/NfeConsulta/NfeConsulta.asmx"); // produção
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2"); // produção
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx"); // produção
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.pr.gov.br/nfe/NFeConsulta3?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.pr.gov.br/nfe/NFeConsulta3?wsdl"); // produção
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.sefazrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.ms.gov.br/producao/services2/NfeConsulta2"); // produção
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl"); // produção
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produção
				}
				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}
	
	//NfeConsultaProtocolo
	public static URL consultaRecibo() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRetAutorizacao?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRetAutorizacao?wsdl"); // produção
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homnfe.sefaz.am.gov.br/services2/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.am.gov.br/services2/services/NfeRetAutorizacao"); // produção
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://www.sefazvirtual.fazenda.gov.br/NfeRetAutorizacao/NfeRetAutorizacao.asmx"); // produção
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://www.sefazvirtual.fazenda.gov.br/NfeRetAutorizacao/NfeRetAutorizacao.asmx"); // produção
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://www.sefazvirtual.fazenda.gov.br/NfeRetAutorizacao/NfeRetAutorizacao.asmx"); // produção
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("	https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRetAutorizacao?wsdl"); // produção
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRetAutorizacao?wsdl"); // produção
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/NfeConsulta/NfeConsulta.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/NfeRetAutorizacao/NfeRetAutorizacao.asmx"); // produção
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRetAutorizacao"); // produção
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nferetautorizacao.asmx"); // produção
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.pr.gov.br/nfe/NFeConsulta3?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.pr.gov.br/nfe/NFeRetAutorizacao3?wsdl"); // produção
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.sefazrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.ms.gov.br/producao/services2/NfeRetAutorizacao"); // produção
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRetAutorizacao?wsdl"); // produção
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx"); // produção
				}
				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}

	//NfeStatusServico
	public static URL status() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl"); // produção
								   
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homnfe.sefaz.am.gov.br/services2/services/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.am.gov.br/services2/services/NfeStatusServico2"); // produção
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2?wsdl"); // produção
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2"); // produção
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/NfeStatusServico/NfeStatusServico.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/NfeStatusServico/NfeStatusServico.asmx"); // produção
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeStatus2"); // produção
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/ws/nfestatusservico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfestatusservico2.asmx"); // produção
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.pr.gov.br/nfe/NFeStatusServico3?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.pr.gov.br/nfe/NFeStatusServico3?wsdl"); // produção
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.sefazrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.ms.gov.br/producao/services2/NfeStatusServico2"); // produção
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2?wsdl"); // produção
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produção
				}
				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}

	//NFeAutorizacao
	public static URL enviarSincrono() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeAutorizacao?wsdl");
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeAutorizacao?wsdl"); // produção
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homnfe.sefaz.am.gov.br/services2/services/NfeAutorizacao"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.am.gov.br/services2/services/NfeAutorizacao"); // produção
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // produção
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // produção
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // produção
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeAutorizacao?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeAutorizacao?wsdl"); // produção
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeAutorizacao?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeAutorizacao?wsdl"); // produção
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/NfeAutorizacao/NfeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/NfeAutorizacao/NfeAutorizacao.asmx"); // produção
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeAutorizacao"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeAutorizacao"); // produção
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeautorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfeautorizacao.asmx"); // produção
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.pr.gov.br/nfe/NFeAutorizacao3?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.pr.gov.br/nfe/NFeAutorizacao3?wsdl"); // produção
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.sefazrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeAutorizacao"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.ms.gov.br/producao/services2/NfeAutorizacao"); // produção
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeAutorizacao?wsdl"); // Homologacao
				} else  {
					url = new URL("	https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeAutorizacao?wsdl"); // produção
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produção
				}
				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}

	//NfeInutilizacao
	public static URL inutilizar() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2?wsdl"); // produção
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homnfe.sefaz.am.gov.br/services2/services/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.am.gov.br/services2/services/NfeInutilizacao2"); // produção
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produção
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produção
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produção
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2?wsdl"); // produção
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2"); // produção
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/NfeInutilizacao/NfeInutilizacao.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/NfeInutilizacao/NfeInutilizacao.asmx"); // produção
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2"); // produção
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.pr.gov.br/nfe/NFeInutilizacao3?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.pr.gov.br/nfe/NFeInutilizacao3?wsdl"); // produção
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.sefazrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.ms.gov.br/producao/services2/NfeInutilizacao2"); // produção
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2?wsdl"); // produção
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produção
				}
				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}

	//RecepcaoEvento
	public static URL evento(String uf) throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/RecepcaoEvento?wsdl"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/RecepcaoEvento?wsdl");
					}
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://homnfe.sefaz.am.gov.br/services2/services/RecepcaoEvento"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.am.gov.br/services2/services/RecepcaoEvento");
					}
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					}
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					}
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					}
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento?wsdl"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento?wsdl");
					}
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento");
					}
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/sre/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.ba.gov.br/webservices/sre/recepcaoevento.asmx");
					}
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento");
					}
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/ws/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.fazenda.sp.gov.br/ws/recepcaoevento.asmx");
					}
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://homologacao.nfe.fazenda.pr.gov.br/nfe/NFeRecepcaoEvento?wsdl"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.fazenda.pr.gov.br/nfe/NFeRecepcaoEvento?wsdl");
					}
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.sefazrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefazrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://homologacao.nfe.ms.gov.br/homologacao/services2/RecepcaoEvento"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.fazenda.ms.gov.br/producao/services2/RecepcaoEvento");
					}
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento?wsdl"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento?wsdl");
					}
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					if (uf.equals("91")) {
						url = new URL("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
					}else{
						url = new URL("https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx"); // Homologacao
					}
							
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
					}
				}
				break;

			default:
				break;
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}
	
	public static URL consultaNfe() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			if (configuracaoNfe.getAmbiente().equals("2")) {
				url = new URL("https://hom.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx"); // Homologacao
			} else  {
				url = new URL("https://www.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx"); // Produção
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}
	
	public static URL downloadXml() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			if (configuracaoNfe.getAmbiente().equals("2")) {
				url = new URL("https://hom.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx"); // Homologacao
			} else  {
				url = new URL("https://www.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx"); // Produção
			}

		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}

	
	public static URL distribuicaoDfe() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();
		
		try {
			if (configuracaoNfe.getAmbiente().equals("2")) {
				url = new URL("https://hom.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx"); // Homologacao
			} else  {
				url = new URL("https://www1.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx"); // Produção
			}
			
		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}
	
	public static URL consultaQrCode() throws NfeException {

		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();
		
		try {
			switch (configuracaoNfe.getUf()) {
			//GOIAS
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("http://homolog.sefaz.go.gov.br/nfeweb/sites/nfce/danfeNFCe"); // homologa��o
				} else {
					url = new URL("http://nfe.sefaz.go.gov.br/nfeweb/sites/nfce/danfeNFCe"); // produ��o
				}
				break;
			
			//PARA
			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://appnfc.sefa.pa.gov.br/portal-homologacao/view/consultas/nfce/nfceForm.seam"); // homologa��o
				} else {
					url = new URL("https://appnfc.sefa.pa.gov.br/portal/view/consultas/nfce/nfceForm.seam"); // produ��o
				}
				break;
			
			//TOCANTINS
			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("http://homolog.sefaz.go.gov.br/nfeweb/sites/nfce/danfeNFCe"); // homologa��o
				} else {
					url = new URL("http://nfe.sefaz.go.gov.br/nfeweb/sites/nfce/danfeNFCe"); // produ��o
				}
				break;
			}
			
		} catch (MalformedURLException e) {
			throw new NfeException("Erro ao pegar Url WebService:"+e.getMessage());
		}
		return url;
	}
}
