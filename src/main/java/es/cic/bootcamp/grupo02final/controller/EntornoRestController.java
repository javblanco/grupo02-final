package es.cic.bootcamp.grupo02final.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EntornoRestController {
	
	@GetMapping(value="/entorno")
	public ResponseEntity<String> getEntorno() {
		return new ResponseEntity<String>("local", HttpStatus.OK);
	}

}
