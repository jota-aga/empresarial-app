package com.esmpresarial.josegomesdemoura.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String rua;
	private String bairro;
	private String numero;
	private String cep;
	
	
	public Endereco(String rua, String bairro, String numero, String cep) {
		super();
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
	}
	
	public Endereco() {
		
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return String.format("Endereco [rua=%s, bairro=%s, numero=%s, cep=%s]", rua, bairro, numero, cep);
	}
	
}
