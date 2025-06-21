package com.esmpresarial.josegomesdemoura.exception;

public class RgJaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RgJaExisteException() {
		super("RG já existe no sistema");
	}

}
