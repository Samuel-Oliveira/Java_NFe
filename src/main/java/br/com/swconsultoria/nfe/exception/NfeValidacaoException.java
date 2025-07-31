package br.com.swconsultoria.nfe.exception;

/**
 * Exceção a ser lançada na ocorrência de falhas provenientes da validação da Nota Fiscal Eletronica.
 * 
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
public class NfeValidacaoException extends NfeException {

	public NfeValidacaoException(Throwable e) {
		super(e);
	}

	public NfeValidacaoException(String message) {
		super(message);
	}

	public NfeValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}
}