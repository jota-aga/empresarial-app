package com.esmpresarial.josegomesdemoura.dto;

import java.util.ArrayList;
import java.util.List;

import com.esmpresarial.josegomesdemoura.models.Consulta;
import com.esmpresarial.josegomesdemoura.models.Locatario;
import com.esmpresarial.josegomesdemoura.models.Sala;

public class SalaDto {
	private long id;
	private String numero;
	private Locatario locatario;
	private List<Consulta> consultas = new ArrayList<>();
	
	public Sala cast() {
		Sala sala = new Sala(this.numero);
		return sala;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = (numero!=null)? numero.trim() : null;
	}
	public Locatario getLocatario() {
		return locatario;
	}
	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public void adicionarConsultas(Consulta consulta) {
		this.consultas.add(consulta);
	}
	
	public void removerConsultas(Consulta consulta) {
		this.consultas.remove(consulta);
	}
	
}
