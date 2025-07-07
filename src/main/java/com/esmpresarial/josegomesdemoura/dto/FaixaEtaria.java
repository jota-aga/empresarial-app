package com.esmpresarial.josegomesdemoura.dto;

public class FaixaEtaria {
	private int minimo;
	private int maximo;
	private int quantidade;
	
	public FaixaEtaria(int minimo, int maximo) {
		this.minimo = minimo;
		this.maximo = maximo;
		this.quantidade = 0;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void incrementarQuantidade() {
		this.quantidade++;
	}
	
	public boolean comparar(int idade) {
		if(idade >= minimo && idade <= maximo) {
			incrementarQuantidade();
		}
		return true;
	}
	
	public String toStringFaixas() {
		String faixas = String.format("%d - %d", minimo, maximo);
		return faixas;
	}
	
	
}
