package com.esmpresarial.josegomesdemoura.service;

import java.util.ArrayList;
import java.util.List;
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
		if(repo.findByNumero(sala.getNumero()).isPresent() && sala.getId() == 0) {
			throw new NumeroDeSalaJaExisteException();
		}
		
		repo.save(sala);
	}
	
	public Sala procurarSalaPorNumero(String numero) throws SalaNaoExisteException {
		Optional<Sala> sala = repo.findByNumero(numero);
		
		if(sala.isEmpty()) {
			throw new SalaNaoExisteException();
		}
		else {
			return sala.get();
		}
	}
	
	public void salvarSalaEditada(Sala sala) throws NumeroDeSalaJaExisteException {
		Optional <Sala> salaAux = repo.findByNumero(sala.getNumero());
		
		if(salaAux.isPresent()){
			Sala salaPresente = salaAux.get();
			if(salaPresente.getId() != sala.getId()) {
				throw new NumeroDeSalaJaExisteException();
			}
		}
		
		repo.save(sala);
	}
	
	public Sala procurarSalaPorId(long id) throws SalaNaoExisteException {
		Optional<Sala> sala = repo.findById(id);
		
		if(sala.isPresent()) {
			return sala.get();
		}
		else {
			throw new SalaNaoExisteException();
		}
		
	}
	
	public void removerSalaPorNumero(String numero) throws SalaNaoExisteException {
		Optional<Sala> sala = repo.findByNumero(numero);
		
		if(sala.isPresent()) {
			repo.delete(sala.get());
		}
		else {
			throw new SalaNaoExisteException();
		}
		
	}
	
	public List<Sala> todasSalas(){
		return repo.findAll();
	}
	
	public List<Sala> salasDisponiveisParaConsulta(){
		List<Sala> salas = new ArrayList<>();
		List<Sala> salasDisponiveis = new ArrayList<>();
		salas = repo.findAll();
		
		for(Sala sala : salas) {
			if(sala.getLocatario() != null) {
				salasDisponiveis.add(sala);
			}
		}
		
		return salasDisponiveis;
		
	}
}
