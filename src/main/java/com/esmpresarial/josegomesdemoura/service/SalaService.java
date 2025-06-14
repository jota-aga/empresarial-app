package com.esmpresarial.josegomesdemoura.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmpresarial.josegomesdemoura.exception.NumeroDeSalaJaExisteException;
import com.esmpresarial.josegomesdemoura.exception.SalaNaoExisteException;
import com.esmpresarial.josegomesdemoura.models.Sala;
import com.esmpresarial.josegomesdemoura.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	SalaRepository repo;
	
	public void salvarSala(Sala sala) throws NumeroDeSalaJaExisteException {
		if(repo.findByNumero(sala.getNumero()).isPresent()) {
			throw new NumeroDeSalaJaExisteException();
		}
		
		repo.save(sala);
	}
	
	public Sala procurarSala(String numero) throws SalaNaoExisteException {
		Optional<Sala> sala = repo.findByNumero(numero);
		
		if(sala.isEmpty()) {
			throw new SalaNaoExisteException();
		}
		else {
			return sala.get();
		}
	}
}
