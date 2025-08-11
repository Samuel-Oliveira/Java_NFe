package br.com.swconsultoria.nfe.exception;

/**
 * Exceção a ser lançada na ocorrência de falhas provenientes da Nota Fiscal Eletronica.
 * 
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
public class NfeException extends Exception {

	public NfeException(String message) {
		super(message);
	}

	public NfeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NfeException(Throwable cause) {
		super(cause);
	}
}