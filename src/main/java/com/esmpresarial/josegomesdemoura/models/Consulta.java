package com.esmpresarial.josegomesdemoura.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Consulta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private LocalDate data;
	@Column
	private LocalTime horario;
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable=false)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "sala_id", nullable=false)
	private Sala sala;

	public Consulta(LocalDate data, LocalTime horario, Cliente cliente, Sala sala) {
		super();
		this.data = data;
		this.horario = horario;
		this.cliente = cliente;
		this.sala = sala;
	}
	
	public Consulta() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
		
}
