/**
 * 
 */
package br.com.samuelweb.nfe.util;

/**
 * @author Samuel Oliveira
 *
 */
public interface ConstantesUtil {
	
	String NFE = "NFe";
	String NFCE = "NFCe";
	
	interface SERVICOS {
		
		String STATUS_SERVICO = "NfeStatusServico_4.00";
		String ENVIO = "NfeAutorizacao_4.00";
		String CONSULTA_RECIBO = "NfeRetAutorizacao_4.00";
		String CONSULTA_CADASTRO = "NfeConsultaCadastro_4.00";
		String URL_QRCODE = "URL-QRCode";
		String URL_CONSULTANFCE = "URL-ConsultaNFCe";
		String EVENTO = "RecepcaoEvento_4.00";
		String INUTILIZACAO = "NfeInutilizacao_4.00";
		String CONSULTA_XML = "NfeConsultaProtocolo_4.00";
		String DISTRIBUICAO_DFE = "NfeDistribuicaoDFe_1.01";
		String MANIFESTACAO = "MANIFESTACAO";
		String CSC = "AdministrarCSCNFCe_1.00";
		
	}

	interface EVENTO {
		String CANCELAR = "110111";
		String CCE = "110110";
		String MANIFESTACAO = "MANIFESTACAO";
	}

	
	interface AMBIENTE {
		String HOMOLOGACAO = "2";
		String PRODUCAO = "1";
	}
	
	interface VERSAO{
		String NFE = "4.00";
		String DIST_DFE = "1.01";
		String EVENTO_CANCELAMENTO = "4.00";
		String EVENTO_CCE = "4.00";
		String EVENTO_MANIFESTAR = "4.00";
	}

}
