package com.esmpresarial.josegomesdemoura.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmpresarial.josegomesdemoura.dto.FaixaEtaria;
import com.esmpresarial.josegomesdemoura.dto.RelatorioDto;
import com.esmpresarial.josegomesdemoura.models.Cliente;
import com.esmpresarial.josegomesdemoura.models.Consulta;

@Service
public class RelatorioService {
	@Autowired
	ConsultaService consultaService;
	
	public void calcularDados(RelatorioDto relatorio, List<Cliente> clientes, List<Consulta> consultas) {
		int masculino = 0;
		int feminino = 0;
		float porcentagemMasculino = 0;
		float porcentagemFeminino = 0;
		List<FaixaEtaria> faixas = criarListaDeFaixaEtaria();
		LocalDate agora = LocalDate.now();
		
		for(Cliente cliente : clientes) {
			if(compararGeneros(cliente.getGenero())==1) {
				masculino++;
			}
			else if(compararGeneros(cliente.getGenero()) == 2) {
				feminino++;
			}
			
			int idade = Period.between(cliente.getDataNascimento(), agora).getYears();
			
			for(FaixaEtaria f: faixas) {
				f.comparar(idade);
			}
		}
		
		porcentagemMasculino = (float)masculino / clientes.size()*100;
		porcentagemFeminino = (float)feminino / clientes.size()*100;
		
		adicionarProfissoes(relatorio, clientes);
		
		relatorio.setTotalConsultas(consultas.size());
		relatorio.setFeminino(feminino);
		relatorio.setMasculino(masculino);
		relatorio.setPorcentagemFeminino(porcentagemFeminino);
		relatorio.setPorcentagemMasculino(porcentagemMasculino);
		relatorio.setFaixasEtarias(faixas);
	}
	
	public int compararGeneros(String genero) {
		if(genero.equalsIgnoreCase("masculino")) {
			return 1;
		}
		else if(genero.equalsIgnoreCase("feminino")) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	public List<FaixaEtaria> compararIdades(List<FaixaEtaria> faixas, int idade) {
		for(FaixaEtaria faixa : faixas) {
			if(faixa.comparar(idade) == true) {
				break;
			}
		}
		return faixas;
	}
	public List<FaixaEtaria> criarListaDeFaixaEtaria(){
		List<FaixaEtaria> faixas = new ArrayList<>();
		
		FaixaEtaria faixa1 = new FaixaEtaria(0, 13);
		FaixaEtaria faixa2 = new FaixaEtaria(14, 18);
		FaixaEtaria faixa3 = new FaixaEtaria(19, 35);
		FaixaEtaria faixa4 = new FaixaEtaria(35, 59);
		FaixaEtaria faixa5 = new FaixaEtaria(60, 105);
		faixas.add(faixa1);
		faixas.add(faixa2);
		faixas.add(faixa3);
		faixas.add(faixa4);
		faixas.add(faixa5);
		
		return faixas;	
	}
	
	public void adicionarProfissoes(RelatorioDto relatorio, List<Cliente> clientes) {
		Map<String, Integer> profissoes = clientes.stream()
										  .filter(cliente -> cliente.getJob() != null && !cliente.getJob().trim().isEmpty())
				                          .collect(Collectors.groupingBy(cliente -> cliente.getJob().trim(), Collectors.summingInt(cliente -> 1)));
		relatorio.setProfissoes(profissoes);	
	}
}
