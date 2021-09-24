package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import javax.validation.Valid;

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

import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.service.FlujoService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/flujos")
public class FlujoController {

	@Autowired
	private FlujoService flujoService;
	
	public void setFlujoService(FlujoService flujoService) {
		this.flujoService = flujoService;
	}
	
	@PostMapping(path = "/lista")
	public ResponseEntity <List<Flujo>> findAll(){
		List<Flujo> flujo = flujoService.findAll();
		return new ResponseEntity<List<Flujo>>(flujo, HttpStatus.OK);
	}

	@GetMapping("/detalle/{id}")
	@ResponseBody
	public Flujo findById(@PathVariable(name = "id") Long id) {
		return flujoService.findById(id);
	}

	@PostMapping("/detalle")
	public Long create(@Valid @RequestBody Flujo flujo) {
		return flujoService.create(flujo);
	}
	
	@PutMapping("/detalle")
	@ResponseBody
	public Flujo update(@Valid @RequestBody Flujo flujo) {
		return flujoService.update(flujo);
	}
	
	@DeleteMapping("/detalle/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		flujoService.deleteById(id);
	}


}
