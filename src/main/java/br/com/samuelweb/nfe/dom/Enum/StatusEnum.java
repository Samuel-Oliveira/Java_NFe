/**
 * 
 */
package br.com.samuelweb.nfe.dom.Enum;

/**
 * @author Samuel Oliveira
 *
 */
/**
 * Enum que contém todos os estados brasileiros.
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
	CADASTRO_ENCONTRADO("111"),
	LOTE_EVENTO_PROCESSADO("128"),
	EVENTO_VINCULADO("135"),
	NENHUM_DOC_LOCALIZADO_PARA_DESTINATARIO("137"),
	DOC_LOCALIZADO_PARA_DESTINATARIO("138"),
    CONSUMO_INDEVIDO("656");

	private final String codigo;

	StatusEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo(){
		return codigo;
	}
}
