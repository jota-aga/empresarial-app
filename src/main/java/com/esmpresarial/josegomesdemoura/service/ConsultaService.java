package com.esmpresarial.josegomesdemoura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmpresarial.josegomesdemoura.exception.SalaSemConsultasException;
import com.esmpresarial.josegomesdemoura.models.Cliente;
import com.esmpresarial.josegomesdemoura.models.Consulta;
import com.esmpresarial.josegomesdemoura.repository.ConsultaRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
	@Autowired
	ConsultaRepository repo;
	
	public void salvarConsulta(Consulta consulta) {
		repo.save(consulta);
	}
	
	public List<Consulta> procurarConsultasPorClienteId(Long id) {
		List<Consulta> consultas = new ArrayList<>();
		
		consultas = repo.findByCliente_Id(id);
		
		return consultas;
	}
	
	@Transactional
	public void removerConsultasPorIdDeSala(long id) {
		repo.deleteAllBySala_Id(id);
	}
	
	public List<Consulta> procurarConsultasPorSalaId(Long id) throws SalaSemConsultasException{
		List<Consulta> consultas = new ArrayList<>();
		
		consultas = repo.findBySala_Id(id);
		
		if(consultas.isEmpty()) {
			throw new SalaSemConsultasException();
		}
		
		return consultas;
	}
	
	public List<Consulta> todasConsultas(){
		List<Consulta> consultas = repo.findAll();
		return consultas;
	}
	
	public List<Cliente> clientesDeUmaListaDeConsultas(List<Consulta> consultas){
		List<Cliente> clientes = new ArrayList<>();
		
		for(Consulta c : consultas) {
			if(!clientes.contains(c.getCliente())) {
				clientes.add(c.getCliente());
			}
		}
		return clientes;
	}
}
