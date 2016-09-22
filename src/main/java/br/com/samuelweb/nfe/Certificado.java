package br.com.samuelweb.nfe;

import java.time.LocalDate;

/**
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */
public class Certificado {
	
	private String nome;
	
	private LocalDate vencimento;
	
	private Long diasRestantes;
	
	private boolean valido;

	/**
	 * Singleton
	 */
	public Certificado(String nome, LocalDate vencimento, Long diasRestantes, boolean valido) {
	
		this.nome = nome;
		this.vencimento = vencimento;
		this.diasRestantes = diasRestantes;
		this.valido = valido;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the vencimento
	 */
	public LocalDate getVencimento() {
		return vencimento;
	}

	/**
	 * @param vencimento the vencimento to set
	 */
	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	/**
	 * @return the diasRestantes
	 */
	public Long getDiasRestantes() {
		return diasRestantes;
	}

	/**
	 * @param diasRestantes the diasRestantes to set
	 */
	public void setDiasRestantes(Long diasRestantes) {
		this.diasRestantes = diasRestantes;
	}

	/**
	 * @return the valido
	 */
	public boolean isValido() {
		return valido;
	}

	/**
	 * @param valido the valido to set
	 */
	public void setValido(boolean valido) {
		this.valido = valido;
	}

}
