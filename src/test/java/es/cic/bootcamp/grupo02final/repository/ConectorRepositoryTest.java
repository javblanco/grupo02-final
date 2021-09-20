package es.cic.bootcamp.grupo02final.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.model.Instancia;

@DataJpaTest
class ConectorRepositoryTest {
	
	@Autowired
	private ConectorRepository cut;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testSave() {
		Conector conector = generarConector();
		
		Conector conectorCreado = cut.save(conector);
		entityManager.flush();
		entityManager.detach(conectorCreado);
		
		Conector conectorEnBBDD = entityManager.find(Conector.class, conectorCreado.getId());
		
		assertThat(conectorCreado)
		.usingRecursiveComparison()
		.isEqualTo(conectorEnBBDD);
	}

	@Test
	void testFindById() {
		Conector conectorALeer = generarConector();
		entityManager.persist(conectorALeer);
		entityManager.flush();
		entityManager.detach(conectorALeer);
		
		Conector conectorLeido = cut.findById(conectorALeer.getId()).get();
		
		assertThat(conectorALeer)
		.usingRecursiveComparison()
		.isEqualTo(conectorLeido);
	}

	@Test
	void testFindAll() {
		Conector conector1 = generarConector();
		Conector conector2 = generarConector();
		entityManager.persist(conector1);
		entityManager.persist(conector2);
		entityManager.flush();
		
		List<Conector> listaALeer = List.of(conector1, conector2);
		
		List<Conector> listaLeida = new ArrayList<>();
		
		cut.findAll()
			.forEach(listaLeida::add);
		
		assertEquals(listaALeer.size(), listaLeida.size(), "No se han podido leer todos los registros");
	}
	
	@Test
	void update() {
		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();
		entityManager.detach(conector);
		
		Conector conectorAModificar = entityManager.find(Conector.class, conector.getId());
		conectorAModificar.setNombre("Conector 2");
		conectorAModificar.setLenguaje("HTML");
		entityManager.flush();
		entityManager.detach(conectorAModificar);
		
		Conector conectorEnBBDD = entityManager.find(Conector.class, conectorAModificar.getId());
		
		assertThat(conectorEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(conectorAModificar);
	}

	@Test
	void testDeleteById() {
		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();
		entityManager.detach(conector);
		
		cut.deleteById(conector.getId());
		
		Conector conectorEnBBDD = entityManager.find(Conector.class, conector.getId());
		
		assertNull(conectorEnBBDD, "No se ha podido borrar el registro de la base de datos.");
	}
	
	private Conector generarConector() {
		
		Conector conector = new Conector();
		conector.setLenguaje("Java");
		conector.setNombre("Conector 1");
		conector.setTipoServicio("Servicio 1");
		
		Instancia instancia = generarInstancia();
		conector.setInstancia(instancia);
		
		Flujo flujo = instancia.getFlujo();
		conector.setFlujo(flujo);
		
		return conector;
		
	}
	
	private Instancia generarInstancia() {
		
		Instancia instancia = new Instancia();
		instancia.setNombre("Instancia 1");
		
		Flujo flujo = generarFlujo();
		instancia.setFlujo(flujo);
		
		entityManager.persist(instancia);
		entityManager.flush();
		
		return instancia;
		
	}
	
	private Flujo generarFlujo() {
		
		Flujo flujo = new Flujo();
		flujo.setNombre("Flujo 1");
		flujo.setEstado(true);
		flujo.setTiempoInicio(LocalDate.now());
		flujo.setTiempoFin(LocalDate.of(2021, 10, 3));
		
		entityManager.persist(flujo);
		entityManager.flush();
		
		return flujo;
		
	}

}
