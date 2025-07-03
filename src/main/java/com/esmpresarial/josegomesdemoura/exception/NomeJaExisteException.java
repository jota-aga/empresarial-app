package com.esmpresarial.josegomesdemoura.exception;

public class NomeJaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NomeJaExisteException() {
		super("O nome já existe no sistema.");
	}

}
