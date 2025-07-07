package com.esmpresarial.josegomesdemoura.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.esmpresarial.josegomesdemoura.dto.ClienteRequest;
import com.esmpresarial.josegomesdemoura.exception.CpfIncorretoException;
import com.esmpresarial.josegomesdemoura.exception.CpfJaExisteException;
import com.esmpresarial.josegomesdemoura.exception.NomeJaExisteException;
import com.esmpresarial.josegomesdemoura.models.Cliente;
import com.esmpresarial.josegomesdemoura.models.Consulta;
import com.esmpresarial.josegomesdemoura.service.ClienteService;
import com.esmpresarial.josegomesdemoura.service.ConsultaService;

import jakarta.validation.Valid;

@Controller
public class ControllerCliente {
	@Autowired
	ClienteService service;
	
	@Autowired
	ConsultaService consultaService;
	
	@GetMapping("/empresarial/cliente")
	public String telaClienteOpcoes() {
		return"cliente/ClientesOpcoes";
	}
	
	@GetMapping("/empresarial/cliente/delete")
	public String telaRemoverCliente() {
		return "cliente/RemoverCliente";
	}
	
	@GetMapping("/empresarial/cliente/cadastro")
	public String telaCadastroCliente(Model model) {
		ClienteRequest clienteRequest = new ClienteRequest();
		model.addAttribute("clienteRequest", clienteRequest);
		return "cliente/CadastroCliente";
	}
	
	@GetMapping("/empresarial/cliente/procurar")
	public String telaCadastroCliente() {
		return "cliente/ProcurarCliente";
	}
	
	@GetMapping("/empresarial/cliente/editar")
	public String telaProcurarClienteEditar() {
		return "cliente/ProcurarClienteEditar";
	}
	
	@GetMapping("/empresarial/cliente/editar/salvar")
	public String telaEditarCliente() {
		return "cliente/EditarCliente";
	}	
	
	@PostMapping("/empresarial/cliente/delete")
	public String removerClientePorCpf(@RequestParam("name") String name, Model model) {
		try {
			service.removerCliente(name);
			model.addAttribute("mensagemSucesso", "Cliente removido com sucesso!");
		}
		catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "cliente/RemoverCliente";
	}
	
	@PostMapping("/empresarial/cliente/cadastro")
	public String cadastrarCliente(@Valid ClienteRequest clienteRequest, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("mensagemErro", "Não foi possível realizar o cadastro do cliente!");
			return "cliente/CadastroCliente";
		}
		else{
			try {
				Cliente cliente = clienteRequest.cast();
				service.addCliente(cliente);
				redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente cadastrado com sucesso!");
				return "redirect:/empresarial/cliente/cadastro";
			}
			catch(CpfJaExisteException e){
				result.rejectValue("cpf", "error.cpf.duplicado", e.getMessage());
			}
			catch(CpfIncorretoException e) {
				result.rejectValue("cpf", "error.cpf.malFormatado", e.getMessage());
			}
			catch(NomeJaExisteException e) {
				result.rejectValue("name", "error.nome.duplicado", e.getMessage());
			}
			
			model.addAttribute("mensagemErro", "Não foi possível realizar o cadastro do cliente!");
			return "cliente/CadastroCliente";
		}
	}
	
	@PostMapping("/empresarial/cliente/procurar")
	public String procurarCliente(@RequestParam String name, Model model) {
		try{
			Cliente cliente = service.procurarClientePorNome(name.trim());
			List<Consulta> consultas = new ArrayList<>();
			consultas = consultaService.procurarConsultasPorClienteId(cliente.getId());
			model.addAttribute("consultas", consultas);
			model.addAttribute("cliente", cliente);
		}catch(Exception e){
			model.addAttribute("mensagemErro", e.getMessage());
		}
		
		return "cliente/ProcurarCliente";		
	}
	
	@PostMapping("/empresarial/cliente/editar")
	public String procurarClienteParaEdicao(Model model, @RequestParam String name) {
		try {
			Cliente cliente = service.procurarClientePorNome(name.trim());
			ClienteRequest clienteDto = new ClienteRequest();
			
			clienteDto.setId(cliente.getId());
			clienteDto.setCpf(cliente.getCpf());
			clienteDto.setName(cliente.getName());
			clienteDto.setPhone(cliente.getPhone());
			clienteDto.setJob(cliente.getJob());
			clienteDto.setGenero(cliente.getGenero());
			clienteDto.setDataNascimento(cliente.getDataNascimento());
			clienteDto.setEmail(cliente.getEmail());
			model.addAttribute("clienteDto", clienteDto);
			model.addAttribute("cliente", cliente);
			return "cliente/EditarCliente";
			
		}catch(Exception e) {
			model.addAttribute("mensagemErro", e.getMessage());
			return "cliente/ProcurarClienteEditar";
		}
	}
	@PostMapping("/empresarial/cliente/editar/salvar")
	public String editarCliente(@Valid@ModelAttribute("clienteDto") ClienteRequest clienteDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "cliente/EditarCliente"; 
		}
		try{
			Cliente cliente = service.findById(clienteDto.getId());
			cliente.setCpf(clienteDto.getCpf().trim());
			cliente.setDataNascimento(clienteDto.getDataNascimento());
			cliente.setName(clienteDto.getName().trim());
			cliente.setPhone(clienteDto.getPhone());
			cliente.setJob(clienteDto.getJob().trim());
			cliente.setGenero(clienteDto.getGenero());
			cliente.setEmail(clienteDto.getEmail().trim());
			
			service.salvarClienteEditado(cliente);
			redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente editado com sucesso.");
 
		}catch(Exception e){
			model.addAttribute("mensagemErro", e.getMessage());
			return "cliente/EditarCliente";
		}
		return "redirect:/empresarial/cliente/editar";
	}	
}
