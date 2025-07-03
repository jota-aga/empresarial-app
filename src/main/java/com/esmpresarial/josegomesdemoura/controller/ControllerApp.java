package com.esmpresarial.josegomesdemoura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerApp {
	@GetMapping("/empresarial")
	public String telaInicial() {
		return "Inicio";
	}
}
