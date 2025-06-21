package com.esmpresarial.josegomesdemoura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmpresarial.josegomesdemoura.models.Consulta;
import com.esmpresarial.josegomesdemoura.repository.ConsultaRepository;

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
}
