package com.esmpresarial.josegomesdemoura.dto;

import com.esmpresarial.josegomesdemoura.models.Endereco;
import com.esmpresarial.josegomesdemoura.models.Locatario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class LocatarioDto {
	
	private long id;
	@NotEmpty(message = "Campo nome não pode estar vazio")
	@Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
	private String name;
	@NotEmpty(message = "Campo CPF não pode estar vazio")
	@Size(min = 11, message = "Campo CPF não pode ter menos que 11 dígitos")
	@Size(max = 11, message = "Campo CPF não pode ter mais que 11 dígitos")
	private String cpf;
	@NotEmpty(message = "Campo RG não pode estar vazio")
	@Size(min = 7, message = "Campo RG não pode ter menos que 7 dígitos")
	@Size(max = 7, message = "Campo RG não pode ter mais que 7 dígitos vazio")
	private String rg;
	private String phone;
	private String nacionalidade;
	private String job;
	private String estadoCivil;
	private String genero;
	private String email;
	private Endereco endereco = new Endereco();
	
	public Locatario cast() {
		Locatario locatario = new Locatario(this.name, this.cpf, this.rg, this.phone, this.nacionalidade, this.job, this.estadoCivil, this.genero, this.email, this.endereco);
		return locatario;
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
		this.name = (name != null) ? name.trim() : null;
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
		this.nacionalidade = (nacionalidade != null)? nacionalidade.trim() : null;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = (job != null)? job.trim() : null;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = (estadoCivil != null)? estadoCivil.trim() : null;
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
		this.email = (email != null)? email.trim() : null;
	}

	@Override
	public String toString() {
		return String.format(
				"Locatario [id=%s, name=%s, cpf=%s, rg=%s, phone=%s, nacionalidade=%s, job=%s, estadoCivil=%s, genero=%s, email=%s, endereco=%s]",
				id, name, cpf, rg, phone, nacionalidade, job, estadoCivil, genero, email, endereco);
	}

}

