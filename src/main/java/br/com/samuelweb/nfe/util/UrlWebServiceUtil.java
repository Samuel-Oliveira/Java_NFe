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
	
	public static URL cancelar() throws NfeException {

		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();
		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeCancelamento2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeCancelamento2?wsdl"); // produ��o
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeCancelamento2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeCancelamento2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeCancelamento2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeCancelamento2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeCancelamento2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeCancelamento2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/nfeweb/services/nfecancelamento2.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeCancelamento2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NFeCancelamento2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeCancelamento2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeCancelamento2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx"); // produ��o
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

	public static URL consultaXml() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2?wsdl"); // produ��o
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeConsulta2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NfeConsulta2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx"); // produ��o
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

	public static URL status() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl"); // produ��o
								   
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/NfeStatusServico/NfeStatusServico.asmx "); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeStatusServico2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeStatusServico2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/nfeweb/services/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NfeStatusServico2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NfeStatusServico2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); // produ��o
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

	public static URL enviar() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					 url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRecepcao2?wsdl");
					//  Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRecepcao2?wsdl"); // produ��o
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfeautorizacao.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NfeRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NfeRecepcao2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
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
	
	public static URL enviarSincrono() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeAutorizacao?wsdl");
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeAutorizacao?wsdl"); // produ��o
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeAutorizacao/NfeAutorizacao.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfeautorizacao.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NfeRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NfeRecepcao2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRecepcao/NfeRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx"); // produ��o
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

	public static URL inutilizar() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2?wsdl"); // produ��o
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("	https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/nfeweb/services/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NfeInutilizacao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NfeInutilizacao2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeInutilizacao/NfeInutilizacao2.asmx"); // produ��o
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

	public static URL retorno() throws NfeException {
		
		configuracaoNfe = ConfiguracoesIniciaisNfe.getInstance();

		try {
			switch (configuracaoNfe.getUf()) {
			case "52":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRetRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRetRecepcao2?wsdl"); // produ��o
				}
				break;

			case "11":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRetRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRetRecepcao2"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRetRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRetRecepcao2"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRetRecepcao2"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRetRecepcao2"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.sp.gov.br/ws/nferetautorizacao.asmx"); // produ��o
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NfeRetRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/NfeRetRecepcao2?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRetRecepcao2?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRetRecepcao2?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx"); // produ��o
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
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "12":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "13":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "14":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "15":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
					}
				}
				break;

			case "16":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "17":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.sefaz.go.gov.br/nfe/services/v2/RecepcaoEvento?wsdl");
					}
				}
				break;

			case "21":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "22":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "23":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfeh.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento"); // produ��o
				}
				break;

			case "24":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "25":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "26":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento"); // produ��o
				}
				break;

			case "27":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "28":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "29":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.sefaz.ba.gov.br/webservices/nfenw/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.ba.gov.br/webservices/nfenw/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "31":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hnfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento"); // Homologacao
				} else  {
					url = new URL("https://nfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento"); // produ��o
				}
				break;

			case "32":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "33":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "35":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/RecepcaoEvento.asmx"); // Homologacao
				} else  {

					if (uf.equals("91")) {
						url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx");
					} else {
						url = new URL("https://nfe.fazenda.sp.gov.br/nfeweb/services/RecepcaoEvento.asmx"); // produ��o
					}
				}
				break;

			case "41":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe2.fazenda.pr.gov.br/nfe/RecepcaoEvento?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe2.fazenda.pr.gov.br/nfe/RecepcaoEvento?wsdl"); // produ��o
				}
				break;

			case "42":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "43":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "50":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
				}
				break;

			case "51":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento?wsdl"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento?wsdl"); // produ��o
				}
				break;

			case "53":
				if (configuracaoNfe.getAmbiente().equals("2")) {
					url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // Homologacao
				} else  {
					url = new URL("https://nfe.sefazvirtual.rs.gov.br/ws/RecepcaoEvento/RecepcaoEvento.asmx"); // produ��o
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
