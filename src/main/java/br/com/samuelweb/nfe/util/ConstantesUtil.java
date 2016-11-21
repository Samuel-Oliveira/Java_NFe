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
	
	interface ESTADO {
		public static final String GO = "52";
		public static final String RO = "11";
		public static final String AC = "12";
		public static final String AM = "13";
		public static final String RR = "14";
		public static final String PA = "15";
		public static final String AP = "16";
		public static final String TO = "17";
		public static final String MA = "21";
		public static final String PI = "22";
		public static final String CE = "23";
		public static final String RN = "24";
		public static final String PB = "25";
		public static final String PE = "26";
		public static final String AL = "27";
		public static final String SE = "28";
		public static final String BA = "29";
		public static final String MG = "31";
		public static final String ES = "32";
		public static final String RJ = "33";
		public static final String SP = "35";
		public static final String PR = "41";
		public static final String SC = "42";
		public static final String RS = "43";
		public static final String MS = "50";
		public static final String MT = "51";
		public static final String DF = "53";

	}
	
	interface VERSAO{
		public static final String V3_10 = "3.10";
		public static final String V1_00 = "1_00";
	}

}
