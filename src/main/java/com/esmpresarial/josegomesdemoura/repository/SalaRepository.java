package com.esmpresarial.josegomesdemoura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmpresarial.josegomesdemoura.models.Locatario;
import com.esmpresarial.josegomesdemoura.models.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	Optional<Sala> findByNumero(String numero);
	Optional<Sala> findByLocatario(Locatario locatario);
}
