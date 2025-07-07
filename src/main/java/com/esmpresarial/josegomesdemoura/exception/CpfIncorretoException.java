package com.esmpresarial.josegomesdemoura.exception;

public class CpfIncorretoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CpfIncorretoException() {
		super("CPF deve conter 11 dígitos!");
	}

}
