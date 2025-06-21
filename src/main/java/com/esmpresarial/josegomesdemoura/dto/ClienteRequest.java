package com.esmpresarial.josegomesdemoura.dto;

import com.esmpresarial.josegomesdemoura.models.Cliente;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ClienteRequest {
	private long id;
	@NotEmpty(message = "Campo nome não pode estar vazio")
	@Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
	private String name;
	private String cpf;
	private String phone;
	private String email;
	private String genero;
	private String job;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente cast() {
		Cliente cliente = new Cliente(this.name, this.cpf, this.phone, this.email, this.genero, this.job);
		return cliente;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = (name != null)? name.trim() : null;
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
		this.phone = (phone != null)? phone.trim() : null;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = (email != null)? email.trim() : null;
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
		this.job = (job != null)? job.trim() : null;
	}
	
	
	
	
}
