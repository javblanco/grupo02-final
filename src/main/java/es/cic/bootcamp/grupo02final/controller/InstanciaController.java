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
import es.cic.bootcamp.grupo02final.model.Instancia;
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
	public Long create(@Valid @RequestBody Instancia instancia) {
		
		return instanciaService.create(instancia);
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Instancia findById(@PathVariable(name = "id") Long id) {
		
		return instanciaService.findById(id);
		
	}
	
	@GetMapping
	@ResponseBody
	public List<Instancia> findAll(){
		
		return instanciaService.findAll();
		
	}
	
	@PutMapping
	@ResponseBody
	public Instancia update(@Valid @RequestBody Instancia instancia) {
		
		return instanciaService.update(instancia);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		
		instanciaService.deleteById(id);
		
	}

}
