/**
 * 
 */
package br.com.swconsultoria.nfe.dom.enuns;

/**
 * @author Samuel Oliveira
 *
 */
/**
 * enuns que contém todos os estados brasileiros.
 * @author Samuel Oliveira
 *
 */
public enum StatusEnum {

	AUTORIZADO("100"),
	CANCELADO("101"),
	INUTILIZADO("102"),
	LOTE_RECEBIDO("103"),
	LOTE_PROCESSADO("104"),
	LOTE_EM_PROCESSAMENTO("105"),
    SERVICO_EM_OPERACAO("107"),
    USO_DENEGADO("110"),
	CADASTRO_ENCONTRADO("111"),
	LOTE_EVENTO_PROCESSADO("128"),
	EVENTO_VINCULADO("135"),
    EVENTO_REGISTRADO_NAO_VINCULADO("136"),
	NENHUM_DOC_LOCALIZADO_PARA_DESTINATARIO("137"),
	DOC_LOCALIZADO_PARA_DESTINATARIO("138"),
	AUTORIZADO_FORA_PRAZO("150"),
	CANCELADO_FORA_PRAZO("151"),
	CANCELAMENTO_FORA_PRAZO("155"),
	DUPLICIDADE("204"),
	DENEGADA_IE_EMITENTE("301"),
	DENEGADA_IE_DESTINATARIO("302"),
	DENEGADA_DESTINATARIO_NAO_HABILITADO("303"),
    CONSUMO_INDEVIDO("656");

	private final String codigo;

	StatusEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo(){
		return codigo;
	}
}
