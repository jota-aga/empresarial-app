package com.esmpresarial.josegomesdemoura.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.esmpresarial.josegomesdemoura.dto.RelatorioDto;
import com.esmpresarial.josegomesdemoura.dto.SalaDto;
import com.esmpresarial.josegomesdemoura.models.Cliente;
import com.esmpresarial.josegomesdemoura.models.Consulta;
import com.esmpresarial.josegomesdemoura.models.Sala;
import com.esmpresarial.josegomesdemoura.service.ClienteService;
import com.esmpresarial.josegomesdemoura.service.ConsultaService;
import com.esmpresarial.josegomesdemoura.service.RelatorioService;
import com.esmpresarial.josegomesdemoura.service.SalaService;

@Controller
public class ControllerRelatorio {
	@Autowired 
	ConsultaService consultaService;
	
	@Autowired 
	ClienteService clienteService;
	
	@Autowired
	RelatorioService relatorioService;
	
	@Autowired
	SalaService salaService;
	
	@GetMapping("/empresarial/relatorio")
	public String paginaRelatorioOpcoes() {
		return "relatorio/OpçõesRelatorio";
	}
	
	@GetMapping("/empresarial/relatorio/geral")
	public String paginaRelatorioGeral(Model model) {
		List<Consulta> consultas = consultaService.todasConsultas();
		
		if(consultas.isEmpty() || consultas == null) {
			model.addAttribute("mensagemErro", "As salas ainda não têm nenhuma consulta no histórico!");
		}
		
		else {
			List<Cliente> clientes = consultaService.clientesDeUmaListaDeConsultas(consultas);
			RelatorioDto relatorio = new RelatorioDto();
			
			relatorioService.calcularDados(relatorio, clientes, consultas);
			System.out.println(relatorio);
			model.addAttribute("relatorio", relatorio);
		}
		return "relatorio/RelatorioGeral";
	}
	
	@GetMapping("/empresarial/relatorio/sala")
	public String paginaRelatorioDeSalaEspecifica(Model model) {
		List<Sala> salas = new ArrayList<>();
		salas = salaService.salasDisponiveisParaConsulta();
		
		if(salas.isEmpty() || salas == null) {
			model.addAttribute("mensagemSalasErro", "Não tem nenhuma sala disponível para consulta");
		}
		else {
			SalaDto salaDto = new SalaDto();
			model.addAttribute("salas", salas);
			model.addAttribute("salaDto", salaDto);
		}
		return "relatorio/EscolherSala";
	}
	
	@GetMapping("/empresarial/relatorio/sala/gerar")
	public String relatorioDeSalaEspecifico(Model model, @ModelAttribute SalaDto salaDto) {
		try{
			System.out.println("AAAAAAAA: "+salaDto.getNumero());
			Sala sala = salaService.procurarSalaPorNumero(salaDto.getNumero());
			List<Consulta> consultas = consultaService.procurarConsultasPorSalaId(sala.getId());
			List<Cliente> clientes = consultaService.clientesDeUmaListaDeConsultas(consultas);
			
			RelatorioDto relatorio = new RelatorioDto();
			
			relatorioService.calcularDados(relatorio, clientes, consultas);
			model.addAttribute("relatorio", relatorio);
			model.addAttribute("SalaNumero", sala.getNumero());
		}
		catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "relatorio/RelatorioEspecifico";
	}
}
	

