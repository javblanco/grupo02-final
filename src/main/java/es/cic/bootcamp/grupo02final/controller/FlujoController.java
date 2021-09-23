package es.cic.bootcamp.grupo02final.controller;

import java.time.LocalDate;
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
	
	@PostMapping("/crearListaInicial")
    public ResponseEntity<HttpStatus> crearListaInicial() {
        if (flujoService.findAll().isEmpty()) {
            Flujo flujo1 = new Flujo("S2D4", LocalDate.of(2020, 12, 12),LocalDate.of(2020, 12, 12), true);
            Flujo flujo2 = new Flujo("S54E", LocalDate.of(2020, 10, 12), LocalDate.of(2020, 10, 12), false);
            Flujo flujo3 = new Flujo("S9D8", LocalDate.of(2020, 8, 10), LocalDate.of(2020, 12, 12), false);
            
	
            flujoService.create(flujo1);
            flujoService.create(flujo2);
            flujoService.create(flujo3);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
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
	public Long create(@RequestBody Flujo flujo) {
		return flujoService.create(flujo);
	}
	
	@PutMapping("/detalle")
	@ResponseBody
	public Flujo update(@RequestBody Flujo flujo) {
		return flujoService.update(flujo);
	}
	
	@DeleteMapping("/detalle/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		flujoService.deleteById(id);
	}

}
