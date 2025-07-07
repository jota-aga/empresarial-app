package com.esmpresarial.josegomesdemoura.exception;

public class ClienteNaoEncotradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncotradoException() {
		super("Cliente não existe no sistema!");
	}

}
