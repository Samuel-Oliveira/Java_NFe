/**
 * 
 */
package br.com.samuelweb.nfe.util;

/**
 * @author Samuel Oliveira
 *
 */
public interface ConstantesUtil {
	
	public enum TipoDoc_e{
		NFE ("NFe"), NFCE ("NFCe");
		private final String tipo;
		private TipoDoc_e(String tipo) {
			this.tipo = tipo;
		}
		public String getTipo(){
			return tipo;
		}
	}
	
	public enum SERVICOS {
		/*
		 * o enum deve ser mantido conforme ex: AQUI_MAIUSCULO ("AquiConformeColocadoNaPropert")
		 */
		
		//nfe
		ENVIO ("NfeAutorizacao_4_00"), 
		CONSULTA_RECIBO ("NfeRetAutorizacao_4_00"), 
		INUTILIZACAO ("NfeInutilizacao_4_00"),
		CONSULTA_XML ("NfeConsultaProtocolo_4_00"),	
		STATUS_SERVICO ("NfeStatusServico_4_00"),
		EVENTO ("RecepcaoEvento_4_00"),
		CONSULTA_CADASTRO ("NfeConsultaCadastro_4_00"),
		DISTRIBUICAO_DFE ("NfeDistribuicaoDFe_1_01"),
		MANIFESTACAO ("MANIFESTACAO"),
		
		//nfce
		URL_QRCODE ("URL-QRCode"),
		URL_CONSULTANFCE ("URL-ConsultaNFCe"),
		CSC ("AdministrarCSCNFCe_1_00");
		
		private final String servico;
		
		private SERVICOS(String servico) {
			this.servico = servico;
		}

		public String getServico(){
			return servico;
		}
		
	}

	interface TIPOS {
		String CNPJ = "CNPJ";
		String CPF = "CPF";
		String NSU = "NSU";
		String CHAVE = "CHAVE";
	}

	interface EVENTO {
		String CANCELAR = "110111";
		String CCE = "110110";
        String EPEC = "110140";
		String MANIFESTACAO = "MANIFESTACAO";
	}

	
	interface AMBIENTE {
		String HOMOLOGACAO = "2";
		String PRODUCAO = "1";
	}
	
	interface VERSAO{
		String NFE = "4.00";
		String INUTILIZACAO = "4.00";
		String DIST_DFE = "1.01";
		String EVENTO_CANCELAMENTO = "1.00";
		String EVENTO_CCE = "1.00";
		String EVENTO_MANIFESTAR = "1.00";
        String EVENTO_EPEC = "1.00";
	}

	interface DEST{
		String XNOME_HOMOLOGACAO = "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL";
	}

}
