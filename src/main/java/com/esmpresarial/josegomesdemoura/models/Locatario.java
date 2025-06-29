package com.esmpresarial.josegomesdemoura.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Locatario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column(unique=true)
	private String cpf;
	@Column(unique=true)
	private String rg;
	@Column
	private String phone;
	@Column
	private String nacionalidade;
	@Column
	private String job;
	@Column
	private String estadoCivil;
	@Column
	private String genero;
	@Column
	private String email;
	@Embedded
	private Endereco endereco = new Endereco();
	@OneToMany(mappedBy = "locatario", cascade = CascadeType.ALL)
	List<Sala> salas = new ArrayList<>();
	

	public Locatario(String name, String cpf, String rg, String phone, String nacionalidade, String job,
			String estadoCivil, String genero, String email, Endereco endereco) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.rg = rg;
		this.phone = phone;
		this.nacionalidade = nacionalidade;
		this.job = job;
		this.estadoCivil = estadoCivil;
		this.genero = genero;
		this.email = email;
		this.endereco = endereco;
	}
	

	public Locatario() {
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	@Override
	public String toString() {
		return String.format(
				"Locatario [id=%s, name=%s, cpf=%s, rg=%s, phone=%s, nacionalidade=%s, job=%s, estadoCivil=%s, genero=%s, email=%s, endereco=%s]",
				id, name, cpf, rg, phone, nacionalidade, job, estadoCivil, genero, email, endereco);
	}

}
