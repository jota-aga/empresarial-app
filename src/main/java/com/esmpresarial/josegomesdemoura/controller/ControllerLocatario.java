package com.esmpresarial.josegomesdemoura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.esmpresarial.josegomesdemoura.dto.LocatarioDto;
import com.esmpresarial.josegomesdemoura.exception.CpfJaExisteException;
import com.esmpresarial.josegomesdemoura.exception.RgJaExisteException;
import com.esmpresarial.josegomesdemoura.models.Locatario;
import com.esmpresarial.josegomesdemoura.service.LocatarioService;

import jakarta.validation.Valid;

@Controller
public class ControllerLocatario {
	
	@Autowired
	private LocatarioService service;
	
	@GetMapping("/empresarial/locatario")
	public String TelaOpcoesLocatario() {
		return "locatario/OpçõesLocatario";
	}
	
	@GetMapping("/empresarial/locatario/cadastro")
	public String TelaCadastro(Model model) {
		LocatarioDto locatarioDto = new LocatarioDto();
		model.addAttribute("locatarioDto", locatarioDto);
		return "locatario/CadastroLocatario";
	}
	
	@GetMapping("/empresarial/locatario/delete")
	public String TelaRemover() {
		return "locatario/RemoverLocatario";
	}
	
	@GetMapping("/empresarial/locatario/editar/procurar")
	public String TelaProcurarEditar() {
		return "locatario/ProcurarEditarLocatario";
	}
	
	@GetMapping("/empresarial/locatario/editar/edit")
	public String TelaEditar() {
		return "locatario/EditarLocatario";
	}
	
	@GetMapping("/empresarial/locatario/procurar")
	public String TelaProcurar() {
		return "locatario/ProcurarLocatario";
	}
	
	@PostMapping("/empresarial/locatario/cadastro")
	public String LocatarioCadastro(RedirectAttributes redirectAttributes,Model model, @Valid LocatarioDto locatarioDto, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("mensagemErro", "Não foi possível realizar o cadastro do locatário");
			return "locatario/CadastroLocatario";
		}
		
		try {
			Locatario locatario = locatarioDto.cast();
			service.salvarLocatario(locatario);
			redirectAttributes.addFlashAttribute("mensagemSucesso", "O locatário foi cadastrado com sucesso");
			return "redirect:/empresarial/locatario/cadastro";
		}
		catch(CpfJaExisteException e) {
			result.rejectValue("cpf", "error.cpfDuplicado", e.getMessage());
			model.addAttribute("mensagemErro", "Não foi possível realizar o cadastro do locatário");
			return "locatario/CadastroLocatario";
		}
		catch(RgJaExisteException e) {
			result.rejectValue("rg", "error.rgDuplicado", e.getMessage());
			model.addAttribute("mensagemErro", "Não foi possível realizar o cadastro do locatário");
			return "locatario/CadastroLocatario";
		}
		
	}
	
	@PostMapping("/empresarial/locatario/delete")
	public String LocatarioRemover(@RequestParam("cpf") String cpf, Model model) {
		try {
			Locatario locatario = service.procurarLocatarioPorCpf(cpf);
			service.removerLocatario(locatario);
			model.addAttribute("mensagemSucesso", "O locatário foi removido com sucesso");
		}
		catch(Exception e){
			model.addAttribute("mensagemErro", e.getMessage());
		}
		return "locatario/RemoverLocatario";
	}
	
	@PostMapping("/empresarial/locatario/procurar")
	public String LocatarioProcurar(@RequestParam("name") String name, Model model) {
		try {
			Locatario locatario = service.procurarLocatarioPorNome(name);
			model.addAttribute("locatario", locatario);
		}catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
		}
		return "locatario/ProcurarLocatario";
	}
	
	@PostMapping("/empresarial/locatario/editar/procurar")
	public String procurarLocatarioParaEditar(Model model, @RequestParam String name) {
		try {
			Locatario locatario = service.procurarLocatarioPorNome(name);
			LocatarioDto locatarioDto = new LocatarioDto();
			
			locatarioDto.setName(locatario.getName());
			locatarioDto.setCpf(locatario.getCpf());
			locatarioDto.setEmail(locatario.getEmail());
			locatarioDto.setRg(locatario.getRg());
			locatarioDto.setEstadoCivil(locatario.getEstadoCivil());
			locatarioDto.setGenero(locatario.getGenero());
			locatarioDto.setJob(locatario.getJob());
			locatarioDto.setNacionalidade(locatario.getNacionalidade());
			locatarioDto.setPhone(locatario.getPhone());
			locatarioDto.setEndereco(locatario.getEndereco());
			locatarioDto.setId(locatario.getId());
			model.addAttribute("locatarioDto", locatarioDto);
			model.addAttribute("locatario", locatario);
			return "locatario/EditarLocatario";
		}catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
			return "locatario/ProcurarEditarLocatario";
		}
		
	}
	
	@PostMapping("/empresarial/locatario/editar/edit")
	public String editarLocatario(Model model, @Valid @ModelAttribute LocatarioDto locatarioDto, BindingResult result) {
		if(result.hasErrors()) {
			return "locatario/EditarLocatario"; 
		}
		try {
			Locatario locatario = service.procurarLocatarioPorId(locatarioDto.getId());
			
			locatario.setName(locatarioDto.getName());
			locatario.setCpf(locatarioDto.getCpf());
			locatario.setEmail(locatarioDto.getEmail());
			locatario.setRg(locatarioDto.getRg());
			locatario.setEstadoCivil(locatarioDto.getEstadoCivil());
			locatario.setGenero(locatarioDto.getGenero());
			locatario.setJob(locatarioDto.getJob());
			locatario.setNacionalidade(locatarioDto.getNacionalidade());
			locatario.setPhone(locatarioDto.getPhone());
			locatario.setEndereco(locatarioDto.getEndereco());
			locatario.setId(locatarioDto.getId());
			service.salvarLocatarioEditado(locatario);
			model.addAttribute("mensagemSucesso", "Locatário atualizado com sucesso!");
		}catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "locatario/EditarLocatario";
	}
}
