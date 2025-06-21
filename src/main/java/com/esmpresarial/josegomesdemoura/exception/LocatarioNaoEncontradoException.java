package com.esmpresarial.josegomesdemoura.exception;

public class LocatarioNaoEncontradoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LocatarioNaoEncontradoException() {
		super("Locatário não existe no sistema!");
	}

}
