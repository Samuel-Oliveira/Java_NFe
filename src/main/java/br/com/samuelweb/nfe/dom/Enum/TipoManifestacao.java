package br.com.samuelweb.nfe.dom.Enum;

/**
 * Enum com os Codigos UF do IBGE
 * 
 * @author Denilson Godinho
 * 
 */
public enum TipoManifestacao {
	
	    CONFIRMACAO_DA_OPERACAO     ("210200",  "Confirmação da Operação",      "Confirmacao da Operacao"),
	    CIENCIA_DA_OPERACAO         ("210210",  "Ciência da Emissão",      "Ciencia da Operacao"),
	    DESCONHECIMENTO_DA_OPERACAO ("210220",  "Desconhecimento da Operação",  "Desconhecimento da Operacao"),
	    OPERACAO_NAO_REALIZADA      ("210240",  "Operação não Realizada",       "Operacao nao Realizada");

	    private final String codigo;
	    private final String descricao;
	    private final String valor;

	    private TipoManifestacao(String codigo, String descricao, String valor) {
	        this.codigo = codigo;
	        this.descricao = descricao;
	        this.valor = valor;
	    }

		/**
		 * @return the codigo
		 */
		public String getCodigo() {
			return codigo;
		}

		/**
		 * @return the descricao
		 */
		public String getDescricao() {
			return descricao;
		}

		/**
		 * @return the valor
		 */
		public String getValor() {
			return valor;
		}
		
		/**
		 * @return TipoManifestacao
		 */
		public static TipoManifestacao getTipo(String codigo){
			TipoManifestacao tipo = null;
			switch (codigo) {
	
			case "210200":
				tipo = CONFIRMACAO_DA_OPERACAO;
				break;
			case "210210":
				tipo = CIENCIA_DA_OPERACAO;
				break;
			case "210220":
				tipo = DESCONHECIMENTO_DA_OPERACAO;
				break;
			case "210240":
				tipo = OPERACAO_NAO_REALIZADA;
				break;
			default:
				break;
			}
	
			return tipo;
		}

	    
}
