package com.esmpresarial.josegomesdemoura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.esmpresarial.josegomesdemoura.dto.SalaDto;
import com.esmpresarial.josegomesdemoura.exception.NumeroDeSalaJaExisteException;
import com.esmpresarial.josegomesdemoura.models.Sala;
import com.esmpresarial.josegomesdemoura.service.SalaService;

@Controller
public class ControllerSala {
	@Autowired
	SalaService salaService;
	
	@GetMapping("/empresarial/sala/opcoes")
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
			Sala sala = salaService.procurarSala(numero);
			model.addAttribute("sala", sala);
			model.addAttribute("mensagemSucesso", "Sala encontrada");
		}
		catch(Exception e){
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "sala/ProcurarSala";
	}
}
