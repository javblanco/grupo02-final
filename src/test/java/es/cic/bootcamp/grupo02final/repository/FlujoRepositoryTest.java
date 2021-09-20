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
class FlujoRepositoryTest {

	@Autowired
	private FlujoRepository cut;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testSave() {
		Flujo flujo = generarFlujo();
		
		Flujo flujoCreado = cut.save(flujo);
		entityManager.flush();
		entityManager.detach(flujoCreado);
		
		Flujo flujoEnBBDD = entityManager.find(Flujo.class, flujoCreado.getId());
		
		assertThat(flujoCreado)
		.usingRecursiveComparison()
		.isEqualTo(flujoEnBBDD);
	}

	@Test
	void testFindById() {
		Flujo flujoALeer = generarFlujo();
		entityManager.persist(flujoALeer);
		entityManager.flush();
		entityManager.detach(flujoALeer);
		
		Flujo flujoLeido = cut.findById(flujoALeer.getId()).get();
		
		assertThat(flujoALeer)
		.usingRecursiveComparison()
		.isEqualTo(flujoLeido);
	}

	@Test
	void testFindAll() {
		Flujo flujo1 = generarFlujo();
		Flujo flujo2 = generarFlujo();
		entityManager.persist(flujo1);
		entityManager.persist(flujo2);
		entityManager.flush();
		
		List<Flujo> listaALeer = List.of(flujo1, flujo2);
		
		List<Flujo> listaLeida = new ArrayList<>();
		
		cut.findAll()
			.forEach(listaLeida::add);
		
		assertEquals(listaALeer.size(), listaLeida.size(), "No se han podido leer todos los registros");
	}
	
	@Test
	void update() {
		Flujo flujo = generarFlujo();
		entityManager.persist(flujo);
		entityManager.flush();
		entityManager.detach(flujo);
		
		Flujo flujoAModificar = entityManager.find(Flujo.class, flujo.getId());
		flujoAModificar.setNombre("Flujo 2");
		flujoAModificar.setEstado(false);
		entityManager.flush();
		entityManager.detach(flujoAModificar);
		
		Flujo flujoEnBBDD = entityManager.find(Flujo.class, flujoAModificar.getId());
		
		assertThat(flujoEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(flujoAModificar);
	}

	@Test
	void testDeleteById() {
		Flujo flujo = generarFlujo();
		entityManager.persist(flujo);
		entityManager.flush();
		entityManager.detach(flujo);
		
		cut.deleteById(flujo.getId());
		
		Flujo flujoEnBBDD = entityManager.find(Flujo.class, flujo.getId());
		
		assertNull(flujoEnBBDD, "No se ha podido borrar el registro de la base de datos.");
	}
	
	private Flujo generarFlujo() {
		
		Flujo flujo = new Flujo();
		flujo.setNombre("Flujo 1");
		flujo.setEstado(true);
		flujo.setTiempoInicio(LocalDate.now());
		flujo.setTiempoFin(LocalDate.of(2021, 10, 3));
		
		Instancia instancia = generarInstancia();
		flujo.setInstancia(instancia);
		
		List<Conector> conectores = new ArrayList<>();
		Conector conector = generarConector();
		conectores.add(conector);
		flujo.setConectores(conectores);
		
		return flujo;
		
	}
	
	private Instancia generarInstancia() {
		
		Instancia instancia = new Instancia();
		instancia.setNombre("Instancia 1");
		
		entityManager.persist(instancia);
		entityManager.flush();
		
		return instancia;
		
	}
	
	private Conector generarConector() {
		
		Conector conector = new Conector();
		conector.setLenguaje("Java");
		conector.setNombre("Conector 1");
		conector.setTipoServicio("Servicio 1");
		
		entityManager.persist(conector);
		entityManager.flush();
		
		return conector;
	}


}
