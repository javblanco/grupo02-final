package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/conector")
public class ConectorController {

	@Autowired
	private ConectorService conectorService;
	
	@Autowired
	private ConectorHelper conectorHelper;
	
	public void setConectorService(ConectorService conectorService) {
		this.conectorService = conectorService;
	}
	
	 @PostMapping("crearListaInicial")
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
	
	@PostMapping
	public Long create(@Valid @RequestBody ConectorDTO conectorDTO) {
		
		return conectorService.create(conectorDTO);
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ConectorDTO findById(@PathVariable(name = "id") Long id) {
		
		return conectorService.findById(id);
		
	}
	
	@GetMapping
	@ResponseBody
	public List<ConectorDTO> findAll(){
		
		return conectorService.findAll();
		
	}
	
	@PutMapping
	@ResponseBody
	public ConectorDTO update(@Valid @RequestBody ConectorDTO conectorDTO) {
		
		return conectorService.update(conectorDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable (name = "id") Long id) {
		
		conectorService.deleteById(id);
		
	}

}
