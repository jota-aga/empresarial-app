package com.esmpresarial.josegomesdemoura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmpresarial.josegomesdemoura.models.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
	public List<Consulta> findByCliente_Id(Long id);
	public List<Consulta> findBySala_Id(Long id);
	void deleteAllBySala_Id(Long id);
}
