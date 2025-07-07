package com.esmpresarial.josegomesdemoura.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column(unique=true)
	private String cpf;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String genero;
	
	@Column
	private String job;
	
	@Column 
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Consulta> consultas = new ArrayList();

	public Cliente(String name, String cpf, String phone, String email, String genero, String job, LocalDate dataNascimento) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
		this.email = email;
		this.genero = genero;
		this.job = job;
		this.dataNascimento = dataNascimento;
	}

	public Cliente() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public void adicionarConsulta(Consulta consulta) {
		this.consultas.add(consulta);
	}
	
	public void removerConsulta(Consulta consulta) {
		this.consultas.remove(consulta);
	}
	
	public List<Consulta> getConsultas(){
		return consultas;
	}
	
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return String.format("Cliente [id=%s, name=%s, cpf=%s, phone=%s, email=%s, genero=%s, job=%s]", id, name,
				cpf, phone, email, genero, job);
	}
	
	
	
	
}
