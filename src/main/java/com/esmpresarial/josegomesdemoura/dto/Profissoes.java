package com.esmpresarial.josegomesdemoura.dto;

public class Profissoes {
	private String job;
	private int quantidade;
	
	public Profissoes(String job){
		this.job = job;
		quantidade = 1;
	}
	
	public void incrementarQuantidades() {
		this.quantidade++;
	}
	
	public String getJob() {
		return this.job;
	}
	
}
