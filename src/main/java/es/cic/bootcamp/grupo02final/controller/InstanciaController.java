package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import es.cic.bootcamp.grupo02final.dto.InstanciaDTO;
import es.cic.bootcamp.grupo02final.service.InstanciaService;

@RestController
@RequestMapping("/instancia")
public class InstanciaController {

	@Autowired
	private InstanciaService instanciaService;

	public void setInstanciaService(InstanciaService instanciaService) {
		this.instanciaService = instanciaService;
	}
	
	@PostMapping
	public Long create(@Valid @RequestBody InstanciaDTO instanciaDTO) {
		
		return instanciaService.create(instanciaDTO);
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public InstanciaDTO findById(@PathVariable(name = "id") Long id) {
		
		return instanciaService.findById(id);
		
	}
	
	@GetMapping
	@ResponseBody
	public List<InstanciaDTO> findAll(){
		
		return instanciaService.findAll();
		
	}
	
	@PutMapping
	@ResponseBody
	public InstanciaDTO update(@Valid @RequestBody InstanciaDTO instanciaDTO) {
		
		return instanciaService.update(instanciaDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		
		instanciaService.deleteById(id);
		
	}

}
