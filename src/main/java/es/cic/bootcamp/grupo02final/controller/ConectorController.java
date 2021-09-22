package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
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
	
<<<<<<< HEAD
<<<<<<< HEAD
	@PostMapping
	public Long save(@Valid @RequestBody Conector conector) {
		
		return conectorService.save(conector);
=======
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
	public void setConectorService(ConectorService conectorService) {
		this.conectorService = conectorService;
	}
	
	 @PostMapping("crearListaInicial")
    public ResponseEntity<HttpStatus> crearListaInicial() {
        if (conectorService.findAll().isEmpty()) {
            Conector conector1 = new Conector("BBDD", "SQL", "Base de datos");
            Conector conector2 = new Conector("JavaContection", "Java", "Contector de java");
            Conector conector3 = new Conector(".NET", "C#", "Microsoft .NET");
            
            conectorService.create(conector1);
            conectorService.create(conector2);
            conectorService.create(conector3);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
	
	@PostMapping
	public Long create(@Valid @RequestBody Conector conector) {
		
		return conectorService.create(conector);
<<<<<<< HEAD
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
		
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
