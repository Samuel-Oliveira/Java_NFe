/**
 * 
 */
package br.com.samuelweb.nfe.util;

/**
 * @author Samuel Oliveira
 *
 */
public interface ConstantesUtil {
	
	public static final String NFE = "NFe";
	public static final String NFCE = "NFCe";
	
	interface SERVICOS {
		
		public static final String STATUS_SERVICO = "NfeStatusServico_3.10";
		public static final String ENVIO = "NfeAutorizacao_3.10";
		public static final String CONSULTA_RECIBO = "NFeRetAutorizacao_3.10";
		public static final String CONSULTA_CADASTRO = "NfeConsultaCadastro_2.00";
		public static final String URL_QRCODE = "URL-QRCode";
		public static final String URL_CONSULTANFCE = "URL-ConsultaNFCe";
		public static final String CONSULTA_DEST = "ConsultaDest_1.01";
		public static final String EVENTO = "RecepcaoEvento_1.00";
		public static final String INUTILIZACAO = "NfeInutilizacao_3.10";
		public static final String CONSULTA_XML = "NfeConsultaProtocolo_3.10";
		public static final String DISTRIBUICAO_DFE = "NFeDistribuicaoDFe_1.00";
		public static final String DOWNLOAD = "NfeDownloadNF_1.00";
		public static final String MANIFESTACAO = "MANIFESTACAO";
		public static final String CSC = "AdministrarCSCNFCe_1.00";
		
	}
	
	interface AMBIENTE {
		public static final String HOMOLOGACAO = "2";
		public static final String PRODUCAO = "1";
	}
	
	interface VERSAO{
		public static final String V3_10 = "3.10";
		public static final String V1_00 = "1.00";
	}

}
