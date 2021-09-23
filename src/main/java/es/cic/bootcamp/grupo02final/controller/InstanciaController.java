package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/instancias")
public class InstanciaController {

	@Autowired
	private InstanciaService instanciaService;
	
	public void setInstanciaService(InstanciaService instanciaService) {
		this.instanciaService = instanciaService;
	}
	
	@PostMapping(path = "/lista")
	public ResponseEntity <List<Instancia>> findAll(){
		List<Instancia> instancia = instanciaService.findAll();
		return new ResponseEntity<List<Instancia>>(instancia, HttpStatus.OK);
	}

	@GetMapping("/detalle/{id}")
	@ResponseBody
	public Instancia findById(@PathVariable(name = "id") Long id) {
		return instanciaService.findById(id);
	}

	@PostMapping("/detalle")
	public Long create(@RequestBody Instancia instancia) {
		return instanciaService.create(instancia);
	}
	
	@PutMapping("/detalle")
	@ResponseBody
	public Instancia update(@RequestBody Instancia instancia) {
		return instanciaService.update(instancia);
	}
	
	@DeleteMapping("/detalle/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		instanciaService.deleteById(id);
	}

}
