package com.esmpresarial.josegomesdemoura.exception;

public class CpfJaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CpfJaExisteException() {
		super("CPF já existe no sistema!");
	}

}
