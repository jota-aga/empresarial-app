package com.esmpresarial.josegomesdemoura.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esmpresarial.josegomesdemoura.models.Locatario;

public interface LocatarioRepository extends JpaRepository<Locatario, Long> {
	Optional<Locatario> findByNameIgnoreCase(String name);
	Optional<Locatario> findByCpf(String cpf);
	Optional<Locatario> findByRg(String rg);
}
