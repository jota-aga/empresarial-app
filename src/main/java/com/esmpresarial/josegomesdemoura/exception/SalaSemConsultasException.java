package com.esmpresarial.josegomesdemoura.exception;

public class SalaSemConsultasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SalaSemConsultasException() {
		super("Sala ainda não possui consultas!");
	}
 
}
