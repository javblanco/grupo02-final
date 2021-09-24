package es.cic.bootcamp.grupo02final.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.bootcamp.grupo02final.model.Instancia;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class InstanciaControllerIntegrationTest {

	@PersistenceContext
	private EntityManager entityManager;	

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		mapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);
		Instancia instancia = generarInstancia();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		MvcResult result = this.mvc
				.perform(mockRequest)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(
						jsonPath("$", notNullValue()))
				.andReturn();

		String contenido = result.getResponse().getContentAsString();
		long idResultado = Long.parseLong(contenido);

		instancia.setId(idResultado);

		Instancia instanciaEnBBDD = entityManager.find(Instancia.class, idResultado);

		assertThat(instanciaEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(instancia);
	}
	
	@Test
	void testTamañoNombreNoPermitido_create() throws Exception {
		Instancia instancia = generarInstancia();
		instancia.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_create() throws Exception {
		Instancia instancia = generarInstancia();
		instancia.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	


	@Test
	void testRegsitroNoExisteException_create() throws Exception {

		Instancia instancia = generarInstancia();
		instancia.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(instancia));

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testFindById() throws Exception {

		Instancia instancia = generarInstancia();

		entityManager.persist(instancia);
		entityManager.flush();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/instancias/detalle/{id}", instancia.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);		

		this.mvc
			.perform(mockRequest)
			.andDo(
				print())
			.andExpect(status().isOk())
			.andExpect(
					jsonPath("$",notNullValue()))
			.andExpect(
					jsonPath("$.id", is(instancia.getId().intValue())))
			.andExpect(
					jsonPath("$.nombre", is(instancia.getNombre())));
	}

	@Test
	void testIdNoValidoException_findById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/instancias/detalle/{id}", -1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testRegistroNoExiste_findById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/instancias/detalle/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testFindAll() throws Exception {

		Instancia instancia1 = generarInstancia();
		entityManager.persist(instancia1);
		entityManager.flush();
		Instancia instancia2 = generarInstancia();
		entityManager.persist(instancia2);
		entityManager.flush();

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/instancias/lista")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(request)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$", hasSize(2)));

	}

	@Test
	void testUpdate() throws Exception {

		Instancia instancia = generarInstancia();
		entityManager.persist(instancia);
		entityManager.flush();
		entityManager.detach(instancia);

		Instancia instanciaModificado = generarInstancia();
		instanciaModificado.setId(instancia.getId());
		instanciaModificado.setNombre("Instancia 2");

		MockHttpServletRequestBuilder request = put("/instancias/detalle")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(instanciaModificado));

		mvc.perform(request)
		.andDo(print())
		.andExpect(status().isOk());



		Instancia instanciaEnBBDD = entityManager.find(Instancia.class, instancia.getId());


		assertThat(instanciaEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(instanciaModificado);
	}
	
	@Test
	void testTamañoNombreNoPermitido_update() throws Exception {
		Instancia instancia = generarInstancia();
		instancia.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_update() throws Exception {
		Instancia instancia = generarInstancia();
		instancia.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testRegistroNoExisteException_update() throws Exception {

		Instancia instancia = generarInstancia();
		instancia.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testRegistroNoExisteException_update_idNull() throws Exception {

		Instancia instancia = generarInstancia();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	void testIdNoValidoException_update() throws Exception {

		Instancia instancia = generarInstancia();
		instancia.setId(-1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/instancias/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(instancia));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testDeleteById() throws Exception {

		Instancia instancia = generarInstancia();
		entityManager.persist(instancia);
		entityManager.flush();


		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/instancias/detalle/{id}", instancia.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isOk());

		Instancia instanciaEnBBDD = entityManager.find(Instancia.class, instancia.getId());
		assertNull(instanciaEnBBDD, "No borra el registro"); 
	}

	@Test
	void testIdNoValidoException_deleteById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/instancias/detalle/{id}", -1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testRegistroNoEncontrado_deleteById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/instancias/detalle/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}
	
	private Instancia generarInstancia() {
		Instancia instancia = new Instancia();
		instancia.setNombre("Instancia 1");
		
		
		return instancia;

	}
	

}
