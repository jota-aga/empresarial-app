package com.esmpresarial.josegomesdemoura.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ConsultaDto {
	@NotEmpty(message = "O campo nome não pode estar vazio")
	private String clienteNome;
	@NotNull (message = "Selecione um sala")
	private Long salaId;
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public Long getSalaId() {
		return salaId;
	}
	public void setSalaId(Long salaId) {
		this.salaId = salaId;
	}	
}
