package com.esmpresarial.josegomesdemoura.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmpresarial.josegomesdemoura.exception.ClienteNaoEncotradoException;
import com.esmpresarial.josegomesdemoura.exception.CpfIncorretoException;
import com.esmpresarial.josegomesdemoura.exception.CpfJaExisteException;
import com.esmpresarial.josegomesdemoura.exception.NomeJaExisteException;
import com.esmpresarial.josegomesdemoura.models.Cliente;
import com.esmpresarial.josegomesdemoura.repository.ClientesRepository;

@Service
public class ClienteService {
	@Autowired
	ClientesRepository repo;
	
	public void addCliente(Cliente cliente) throws CpfJaExisteException, CpfIncorretoException, NomeJaExisteException {
		if(!cliente.getCpf().isEmpty() && cliente.getId() == 0) {
			if(cliente.getCpf().length() != 11 ) {
				throw new CpfIncorretoException();
			}
			
			if(repo.findByCpf(cliente.getCpf()).isPresent()) {
				throw new CpfJaExisteException();
			}
		}
		
		if(repo.findByNameIgnoreCase(cliente.getName()).isPresent()) {
			throw new NomeJaExisteException();
		}
		repo.save(cliente);
	}
	
	public void salvarClienteEditado(Cliente cliente) throws CpfIncorretoException, CpfJaExisteException {
		if(cliente.getCpf() != null && !cliente.getCpf().isEmpty() && cliente.getCpf().length() != 11) {
			throw new CpfIncorretoException();
		}
		if(!cliente.getCpf().isEmpty()) {
			Optional<Cliente> clienteAux = repo.findByCpf(cliente.getCpf());
			if(clienteAux.isPresent() && clienteAux.get().getId() != cliente.getId()) {
				throw new CpfJaExisteException();
			}
		}
		
		
		
		repo.save(cliente);
	}
	
	public Cliente procurarClientePorCPf(String cpf) throws ClienteNaoEncotradoException {
		Optional<Cliente> cliente = repo.findByCpf(cpf);
		if(cliente.isEmpty()) {
			throw new ClienteNaoEncotradoException();
		}
		else {
			return cliente.get();
		}
	}
	
	
	public void removerCliente(String name) throws ClienteNaoEncotradoException {
		Optional<Cliente> cliente = repo.findByNameIgnoreCase(name);
		if(cliente.isEmpty()) {
			throw new ClienteNaoEncotradoException();
		}
		else {
			repo.delete(cliente.get());
		}
	}
	
	public Cliente procurarClientePorNome(String name) throws ClienteNaoEncotradoException {
		Optional<Cliente> cliente = repo.findByNameIgnoreCase(name);
		
		if(cliente.isEmpty()) {
			throw new ClienteNaoEncotradoException();
		}
		return cliente.get();
	}
	
	public Cliente findById(long id) throws ClienteNaoEncotradoException {
		Optional<Cliente> cliente = repo.findById(id);
		if(cliente.isPresent()) {
			return cliente.get();
		}
		else {
			throw new ClienteNaoEncotradoException();
		}
	}
		
}
