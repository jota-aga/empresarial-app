package com.esmpresarial.josegomesdemoura.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmpresarial.josegomesdemoura.exception.CpfJaExisteException;
import com.esmpresarial.josegomesdemoura.exception.LocatarioNaoEncontradoException;
import com.esmpresarial.josegomesdemoura.exception.RgJaExisteException;
import com.esmpresarial.josegomesdemoura.models.Locatario;
import com.esmpresarial.josegomesdemoura.repository.LocatarioRepository;

@Service
public class LocatarioService {
	@Autowired
	private LocatarioRepository repo;
	
	public void salvarLocatario(Locatario locatario) throws CpfJaExisteException, RgJaExisteException{
		if(repo.findByCpf(locatario.getCpf()).isPresent()) {
			throw new CpfJaExisteException();
		}
		if(repo.findByRg(locatario.getRg()).isPresent()) {
			throw new RgJaExisteException();
		}
		repo.save(locatario);
	}
	
	public void salvarLocatarioEditado(Locatario locatario) {
		repo.save(locatario);
	}
	
	public Locatario procurarLocatarioPorCpf(String cpf) throws LocatarioNaoEncontradoException {
		Optional<Locatario> locatario = repo.findByCpf(cpf);
		
		if(locatario.isEmpty()) {
			throw new LocatarioNaoEncontradoException();
		}
		
		return locatario.get();	
	}
	
	public Locatario procurarLocatarioPorNome(String name) throws LocatarioNaoEncontradoException {
		Optional<Locatario> locatario = repo.findByNameIgnoreCase(name);
		
		if(locatario.isEmpty()) {
			throw new LocatarioNaoEncontradoException();
		}
		
		return locatario.get();	
	}
	
	public void removerLocatario(Locatario locatario) throws LocatarioNaoEncontradoException{
		repo.delete(locatario);

	}
	
	public Locatario procurarLocatarioPorId(Long id) throws LocatarioNaoEncontradoException{
		Optional<Locatario> locatario = repo.findById(id);
		
		if(locatario.isPresent()) {
			return locatario.get();
		}
		else {
			throw new LocatarioNaoEncontradoException();
		}
	}
	
	public List<Locatario> todosLocatarios(){
		return repo.findAll();
	}
}
