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
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.service.ConectorService;

@RestController
@RequestMapping("/conector")
public class ConectorController {

	@Autowired
	private ConectorService conectorService;
	
	@PostMapping
	public Long save(@Valid @RequestBody Conector conector) {
		
		return conectorService.save(conector);
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Conector findById(@PathVariable(name = "id") Long id) {
		
		return conectorService.findById(id);
		
	}
	
	@GetMapping
	@ResponseBody
	public List<Conector> findAll(){
		
		return conectorService.findAll();
		
	}
	
	@PutMapping
	@ResponseBody
	public Conector update(@Valid @RequestBody Conector conector) {
		
		return conectorService.update(conector);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		
		conectorService.deleteById(id);
		
	}

}
