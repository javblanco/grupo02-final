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

import es.cic.bootcamp.grupo02final.dto.ConectorDTO;
import es.cic.bootcamp.grupo02final.helper.ConectorHelper;
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.service.ConectorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/conexiones")
public class ConectorController {

	@Autowired
	private ConectorService conectorService;
	
	@Autowired
	private ConectorHelper conectorHelper;
	
	public void setConectorService(ConectorService conectorService) {
		this.conectorService = conectorService;
	}
	
	@PostMapping("/crearListaInicial")
    public ResponseEntity<HttpStatus> crearListaInicial() {
        if (conectorService.findAll().isEmpty()) {
            Conector conector1 = new Conector("BBDD", "SQL", "Base de datos");
            Conector conector2 = new Conector("JavaContection", "Java", "Contector de java");
            Conector conector3 = new Conector(".NET", "C#", "Microsoft .NET");
            
            conectorService.create(conectorHelper.entity2DTO(conector1));
            conectorService.create(conectorHelper.entity2DTO(conector2));
            conectorService.create(conectorHelper.entity2DTO(conector3));
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
	
<<<<<<< HEAD
	@PostMapping
	public Long create(@Valid @RequestBody ConectorDTO conectorDTO) {
		
		return conectorService.create(conectorDTO);
		
=======
	@PostMapping(path = "/lista")
	public ResponseEntity <List<Conector>> findAll(){
		List<Conector> conector = conectorService.findAll();
		return new ResponseEntity<List<Conector>>(conector, HttpStatus.OK);
>>>>>>> feature/angular/TESTJASMINE
	}

	@GetMapping("/detalle/{id}")
	@ResponseBody
<<<<<<< HEAD
	public ConectorDTO findById(@PathVariable(name = "id") Long id) {
		
=======
	public Conector findById(@PathVariable(name = "id") Long id) {
>>>>>>> feature/angular/TESTJASMINE
		return conectorService.findById(id);
	}
<<<<<<< HEAD
	
	@GetMapping
	@ResponseBody
	public List<ConectorDTO> findAll(){
		
		return conectorService.findAll();
		
=======

	@PostMapping("/detalle")
	public Long create(@RequestBody Conector conector) {
		return conectorService.create(conector);
>>>>>>> feature/angular/TESTJASMINE
	}
	
	@PutMapping("/detalle")
	@ResponseBody
<<<<<<< HEAD
	public ConectorDTO update(@Valid @RequestBody ConectorDTO conectorDTO) {
		
		return conectorService.update(conectorDTO);
		
=======
	public Conector update(@RequestBody Conector conector) {
		return conectorService.update(conector);
>>>>>>> feature/angular/TESTJASMINE
	}
	
	@DeleteMapping("/detalle/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		conectorService.deleteById(id);
	}

}
