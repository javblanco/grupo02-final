package es.cic.bootcamp.grupo02final.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.model.Instancia;
import es.cic.bootcamp.grupo02final.service.ConectorService;
import es.cic.bootcamp.grupo02final.service.FlujoService;
import es.cic.bootcamp.grupo02final.service.InstanciaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/inicio")
public class InicioController {

	@Autowired
	private ConectorService conectorService;
	@Autowired
	private InstanciaService instanciaService;
	@Autowired
	private FlujoService flujoService;
	
	public void setConectorService(ConectorService conectorService) {
		this.conectorService = conectorService;
	}
	
	@PostMapping("/rellenarTablas")
    public ResponseEntity<HttpStatus> crearListaInicial() {

		// Rellenamos la tabla de conexiones
        if (conectorService.findAll().isEmpty()) {
            Conector conector1 = new Conector("BBDD", "SQL", "Base de datos");
            Conector conector2 = new Conector("JavaContection", "Java", "Contector de java");
            Conector conector3 = new Conector(".NET", "C#", "Microsoft .NET");
            
            conectorService.create(conector1);
            conectorService.create(conector2);
            conectorService.create(conector3);
        }

		// Rellenamos la tabla de instancias
		if (instanciaService.findAll().isEmpty()) {
			Instancia instancia1 = new Instancia("Instancia 1");
			Instancia instancia2 = new Instancia("Instancia 2");
			Instancia instancia3 = new Instancia("Instancia 3");

			instanciaService.create(instancia1);
			instanciaService.create(instancia2);
			instanciaService.create(instancia3);
		}

		// Rellenamos la tabla de flujo
		if (flujoService.findAll().isEmpty()) {
            Flujo flujo1 = new Flujo("A001", LocalDate.of(2020, 12, 12),LocalDate.of(2020, 12, 12), instanciaService.findById(1L), true);
            Flujo flujo2 = new Flujo("A002", LocalDate.of(2020, 10, 12), LocalDate.of(2020, 10, 12), instanciaService.findById(1L), false);
            Flujo flujo3 = new Flujo("B001", LocalDate.of(2020, 8, 10), LocalDate.of(2020, 12, 12), instanciaService.findById(2L), false);
			Flujo flujo4 = new Flujo("B002", LocalDate.of(2020, 8, 10), LocalDate.of(2020, 12, 12), instanciaService.findById(2L), false);
			Flujo flujo5 = new Flujo("C001", LocalDate.of(2020, 8, 10), LocalDate.of(2020, 12, 12), instanciaService.findById(3L), false);
            
	
            flujoService.create(flujo1);
            flujoService.create(flujo2);
            flujoService.create(flujo3);
			flujoService.create(flujo4);
			flujoService.create(flujo5);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
	
}
