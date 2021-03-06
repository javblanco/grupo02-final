package es.cic.bootcamp.grupo02final.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import es.cic.bootcamp.grupo02final.model.Instancia;

@DataJpaTest
class InstanciaRepositoryTest {

	@Autowired
	private InstanciaRepository cut;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testCreate() {
		Instancia instancia = generarInstancia();
		
		Instancia instanciaCreado = cut.save(instancia);
		entityManager.flush();
		entityManager.detach(instanciaCreado);
		
		Instancia instanciaEnBBDD = entityManager.find(Instancia.class, instanciaCreado.getId());
		
		assertThat(instanciaCreado)
		.usingRecursiveComparison()
		.isEqualTo(instanciaEnBBDD);
	}

	@Test
	void testFindById() {
		Instancia instanciaALeer = generarInstancia();
		entityManager.persist(instanciaALeer);
		entityManager.flush();
		entityManager.detach(instanciaALeer);
		
		Instancia instanciaLeido = cut.findById(instanciaALeer.getId()).get();
		
		assertThat(instanciaALeer)
		.usingRecursiveComparison()
		.isEqualTo(instanciaLeido);
	}

	@Test
	void testFindAll() {
		Instancia instancia1 = generarInstancia();
		Instancia instancia2 = generarInstancia();
		entityManager.persist(instancia1);
		entityManager.persist(instancia2);
		entityManager.flush();
		
		List<Instancia> listaALeer = List.of(instancia1, instancia2);
		
		List<Instancia> listaLeida = new ArrayList<>();
		
		cut.findAll()
			.forEach(listaLeida::add);
		
		assertEquals(listaALeer.size(), listaLeida.size(), "No se han podido leer todos los registros");
	}
	
	@Test
	void update() {
		Instancia instancia = generarInstancia();
		entityManager.persist(instancia);
		entityManager.flush();
		entityManager.detach(instancia);
		
		Instancia instanciaAModificar = entityManager.find(Instancia.class, instancia.getId());
		instanciaAModificar.setNombre("Instancia 2");
		entityManager.flush();
		entityManager.detach(instanciaAModificar);
		
		Instancia instanciaEnBBDD = entityManager.find(Instancia.class, instanciaAModificar.getId());
		
		assertThat(instanciaEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(instanciaAModificar);
	}

	@Test
	void testDeleteById() {
		Instancia instancia = generarInstancia();
		entityManager.persist(instancia);
		entityManager.flush();
		entityManager.detach(instancia);
		
		cut.deleteById(instancia.getId());
		
		Instancia instanciaEnBBDD = entityManager.find(Instancia.class, instancia.getId());
		
		assertNull(instanciaEnBBDD, "No se ha podido borrar el registro de la base de datos.");
	}
	
	private Instancia generarInstancia() {
		
		Instancia instancia = new Instancia();
		instancia.setNombre("Instancia 1");
		
		return instancia;
		
	}

}
