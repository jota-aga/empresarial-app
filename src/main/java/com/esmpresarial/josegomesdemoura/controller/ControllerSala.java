package com.esmpresarial.josegomesdemoura.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.esmpresarial.josegomesdemoura.dto.ConsultaDto;
import com.esmpresarial.josegomesdemoura.dto.SalaDto;
import com.esmpresarial.josegomesdemoura.exception.NumeroDeSalaJaExisteException;
import com.esmpresarial.josegomesdemoura.models.Cliente;
import com.esmpresarial.josegomesdemoura.models.Consulta;
import com.esmpresarial.josegomesdemoura.models.Locatario;
import com.esmpresarial.josegomesdemoura.models.Sala;
import com.esmpresarial.josegomesdemoura.service.ClienteService;
import com.esmpresarial.josegomesdemoura.service.ConsultaService;
import com.esmpresarial.josegomesdemoura.service.LocatarioService;
import com.esmpresarial.josegomesdemoura.service.SalaService;

import jakarta.validation.Valid;

@Controller
public class ControllerSala {
	@Autowired
	SalaService salaService;
	
	@Autowired 
	LocatarioService locatarioService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ConsultaService consultaService;
	
	@GetMapping("/empresarial/sala")
	public String telaOpcoes() {
		return "sala/OpçõesSala";
	}
	@GetMapping("/empresarial/sala/cadastro")
	public String telaCadastrarSala(Model model) {
		SalaDto salaDto = new SalaDto();
		model.addAttribute("salaDto", salaDto);
		return "sala/CadastroSala";
	}
	@GetMapping("/empresarial/sala/procurar")
	public String telaProcurarSala() {
		return "sala/ProcurarSala";
	}
	
	@GetMapping("/empresarial/sala/editar/procurar")
	public String telaProcurarEditar() {
		return "sala/ProcurarEditarSala";
	}
	
	@GetMapping("/empresarial/sala/editar/edit")
	public String telaEditar() {
		return "sala/EditarSala";
	}
	
	@GetMapping("/empresarial/sala/delete")
	public String telaRemoverSala() {
		return "sala/RemoverSala";
	}
	
	@GetMapping("/empresarial/sala/consulta")
	public String telaConsultaSala(Model model) {
		ConsultaDto consultaDto = new ConsultaDto();
		List<Sala> salas = salaService.salasDisponiveisParaConsulta();
		if(!salas.isEmpty()) {
			model.addAttribute("salas", salas);
		}
		else {
			model.addAttribute("mensagemSalasErro", "Nenhuma sala Disponível!");
		}

		model.addAttribute("consultaDto", consultaDto);
		return "sala/ConsultaSala";
	}
	
	@PostMapping("empresarial/sala/cadastro")
	public String cadastrarSala(Model model, SalaDto salaDto) {
		Sala sala = salaDto.cast();
		try {
			salaService.salvarSala(sala);
			model.addAttribute("mensagemSucesso", "Sala cadastrada com sucesso!");
		} catch (NumeroDeSalaJaExisteException e) {
			model.addAttribute("mensagemErro", e.getMessage());
		}
		return "sala/CadastroSala";
	}
	
	@PostMapping("/empresarial/sala/procurar")
	public String procurarSala(@RequestParam String numero, Model model) {
		try {
			Sala sala = salaService.procurarSalaPorNumero(numero);
			model.addAttribute("sala", sala);
			model.addAttribute("mensagemSucesso", "Sala encontrada");
		}
		catch(Exception e){
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "sala/ProcurarSala";
	}
	
	@PostMapping("/empresarial/sala/editar/procurar")
	public String editarSala(@RequestParam String numero, Model model) {
		try {
			Sala sala = salaService.procurarSalaPorNumero(numero);
			SalaDto salaDto = new SalaDto();
			
			salaDto.setId(sala.getId());
			salaDto.setNumero(sala.getNumero());
			if(sala.getLocatario() != null) {
				salaDto.setLocatarioId(sala.getLocatario().getId());
			}
			salaDto.setConsultas(sala.getConsultas());
			
			model.addAttribute("salaDto", salaDto);
			model.addAttribute("sala", sala);
			List<Locatario> locatarios = new ArrayList<>();
			locatarios = locatarioService.todosLocatarios();
			model.addAttribute("locatarios", locatarios);
			return "sala/EditarSala";
			
		}catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
			return "sala/ProcurarEditarSala";
		}
	}
	
	@PostMapping("/empresarial/sala/editar/edit")
	public String editarSala(Model model,@ModelAttribute SalaDto salaDto) {
		Locatario locatario = null;
		try {
			Sala sala = salaService.procurarSalaPorId(salaDto.getId());
			sala.setNumero(salaDto.getNumero());
			
			if(salaDto.getLocatarioId() == null) {
				
				sala.setLocatario(null);
			}
			
			else if(sala.getLocatario() == null || sala.getLocatario().getId() != salaDto.getLocatarioId()) {
				locatario = locatarioService.procurarLocatarioPorId(salaDto.getLocatarioId());
				sala.setLocatario(locatario);
				
				
			}
			
			salaService.salvarSalaEditada(sala);
			model.addAttribute("mensagemSucesso", "Sala editada com sucesso!");			
			return "sala/EditarSala";
			
		}catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
			return "sala/ProcurarEditarSala";
		}
	}
	
	@PostMapping("empresarial/sala/delete")
	public String removerSala(@RequestParam String numero, Model model) {
		try {
			salaService.removerSalaPorNumero(numero);
			model.addAttribute("mensagemSucesso", "Sala removida com sucesso!");
		}
		catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "sala/RemoverSala";
	}
	
	@PostMapping("empresarial/sala/consulta")
	public String consultaSala(Model model, @Valid @ModelAttribute ConsultaDto consultaDto, BindingResult result) {
		if(result.hasErrors()) {
			return "sala/ConsultaSala";
		}
		try {
			Cliente cliente = clienteService.procurarClientePorNome(consultaDto.getClienteNome());
			Sala sala = salaService.procurarSalaPorId(consultaDto.getSalaId());
			Consulta consulta = new Consulta(LocalDate.now(), LocalTime.now(), cliente, sala);
			sala.adicionarConsulta(consulta);
			cliente.adicionarConsulta(consulta);
			consultaService.salvarConsulta(consulta);
			
			model.addAttribute("mensagemSucesso", "Consulta adicionada com sucesso");
		}
		catch(Exception e) {
			List<Sala> salas = salaService.salasDisponiveisParaConsulta();
			if(!salas.isEmpty()) {
				model.addAttribute("salas", salas);
			}
			model.addAttribute("mensagemErro", e.getMessage());
		}
		List<Sala> salas = salaService.salasDisponiveisParaConsulta();
		if(!salas.isEmpty()) {
			model.addAttribute("salas", salas);
		}
		else {
			model.addAttribute("mensagemSalasErro", "Nenhuma sala Disponível!");
		}
		return "sala/ConsultaSala";
	}
}
