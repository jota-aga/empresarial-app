package com.esmpresarial.josegomesdemoura.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String numero;
	@ManyToOne
	@JoinColumn(name = "locatario_id", nullable = true)
	private Locatario locatario;
	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
	private List<Consulta> consultas = new ArrayList<>();
	
	public Sala(String numero, Locatario locatario) {
		super();
		this.numero = numero;
		this.locatario = locatario;
	}
	
	public Sala(String numero) {
		this.numero = numero;
		this.locatario = null;
	}
	
	public Sala() {
		
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
		this.numero = numero;
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
	
	public void adicionarConsulta(Consulta consulta) {
		if(!this.consultas.contains(consulta)) {
			consultas.add(consulta);
		}
	}
	
	public void removerConsulta(Consulta consulta) {
		if(this.consultas.contains(consulta)) {
			consultas.remove(consulta);
		}
	}
	
	
}
