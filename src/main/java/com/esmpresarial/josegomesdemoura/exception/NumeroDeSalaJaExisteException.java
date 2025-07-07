package com.esmpresarial.josegomesdemoura.exception;

public class NumeroDeSalaJaExisteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NumeroDeSalaJaExisteException() {
		super("Esse número de sala já existe!");
	}

}
