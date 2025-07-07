package com.esmpresarial.josegomesdemoura.dto;

import java.util.List;
import java.util.Map;

public class RelatorioDto {
	private int totalConsultas;
	private int feminino;
	private float porcentagemFeminino;
	private int masculino;
	private float porcentagemMasculino;
	Map<String, Integer> profissoes;
	List<FaixaEtaria> faixasEtarias;
	
	public RelatorioDto() {
		
	}
	
	public RelatorioDto(int totalConsultas, int feminino, float porcentagemFeminino, int masculino, float porcentagemMasculino,
			Map<String, Integer> profissoes, List<FaixaEtaria> faixasEtarias) {
		super();
		this.totalConsultas = totalConsultas;
		this.feminino = feminino;
		this.porcentagemFeminino = porcentagemFeminino;
		this.masculino = masculino;
		this.porcentagemMasculino = porcentagemMasculino;
		this.profissoes = profissoes;
		this.faixasEtarias = faixasEtarias;		
	}

	public int getTotalConsultas() {
		return totalConsultas;
	}

	public void setTotalConsultas(int totalConsultas) {
		this.totalConsultas = totalConsultas;
	}

	public int getFeminino() {
		return feminino;
	}

	public void setFeminino(int feminino) {
		this.feminino = feminino;
	}

	public float getPorcentagemFeminino() {
		return porcentagemFeminino;
	}

	public void setPorcentagemFeminino(float porcentagemFeminino) {
		this.porcentagemFeminino = porcentagemFeminino;
	}

	public int getMasculino() {
		return masculino;
	}

	public void setMasculino(int masculino) {
		this.masculino = masculino;
	}

	public float getPorcentagemMasculino() {
		return porcentagemMasculino;
	}

	public void setPorcentagemMasculino(float porcentagemMasculino) {
		this.porcentagemMasculino = porcentagemMasculino;
	}

	public Map<String, Integer> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(Map<String, Integer> profissoes) {
		this.profissoes = profissoes;
	}

	public List<FaixaEtaria> getFaixasEtarias() {
		return faixasEtarias;
	}

	public void setFaixasEtarias(List<FaixaEtaria> faixasEtarias) {
		this.faixasEtarias = faixasEtarias;
	}

	@Override
	public String toString() {
		return String.format(
				"RelatorioDto [totalConsultas=%s, feminino=%s, porcentagemFeminino=%s, masculino=%s, porcentagemMasculino=%s, profissoes=%s]",
				totalConsultas, feminino, porcentagemFeminino, masculino, porcentagemMasculino, profissoes);
	}
	
	
}
