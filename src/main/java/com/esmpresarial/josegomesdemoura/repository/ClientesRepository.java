package com.esmpresarial.josegomesdemoura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmpresarial.josegomesdemoura.models.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByCpf(String cpf);
	Optional<Cliente> findByNameIgnoreCase(String name);
}
