package com.esmpresarial.josegomesdemoura.exception;

public class SalaNaoExisteException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SalaNaoExisteException() {
		super("Número de sala não existe no sistema!");
	}

}
