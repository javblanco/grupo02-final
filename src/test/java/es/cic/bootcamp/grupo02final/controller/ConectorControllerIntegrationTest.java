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
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.bootcamp.grupo02final.model.Conector;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ConectorControllerIntegrationTest {

	@PersistenceContext
	private EntityManager entityManager;	

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Conector conector = generarConector();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		MvcResult result = this.mvc
				.perform(mockRequest)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(
						jsonPath("$", notNullValue()))
				.andReturn();

		String contenido = result.getResponse().getContentAsString();
		long idResultado = Long.parseLong(contenido);

		conector.setId(idResultado);

		Conector conectorEnBBDD = entityManager.find(Conector.class, idResultado);

		assertThat(conectorEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(conector);
	}
	
	@Test
	void testTamañoNombreNoPermitido_create() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_create() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testLenguajeVacio_create() throws Exception {
		Conector conector = generarConector();
		conector.setLenguaje("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testTipoServicioVacio_create() throws Exception {
		Conector conector = generarConector();
		conector.setTipoServicio("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}

	@Test
	void testRegsitroNoExisteException_create() throws Exception {

		Conector conector = generarConector();
		conector.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	void testIdNoValidoException_create() throws Exception {

		Conector conector = generarConector();
		conector.setId(-1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testFindById() throws Exception {
		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();
			
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conexiones/detalle/{id}", conector.getId())
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
				jsonPath("$.id", is(conector.getId().intValue())))
		.andExpect(
				jsonPath("$.lenguaje", is(conector.getLenguaje())))
		.andExpect(
				jsonPath("$.tipoServicio", is(conector.getTipoServicio())))
		.andExpect(
				jsonPath("$.nombre", is(conector.getNombre())));
	}

	@Test
	void testIdNoValidoException_findById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conexiones/detalle/{id}", -1L)
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

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conexiones/detalle/{id}", 1L)
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
		Conector conector1 = generarConector();
		entityManager.persist(conector1);
		entityManager.flush();
		
		Conector conector2 = generarConector();
		entityManager.persist(conector2);
		entityManager.flush();

		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.post("/conexiones/lista")
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
		
		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();

		Conector conectorModificado = generarConector();
		conectorModificado.setId(conector.getId());
		conectorModificado.setLenguaje("HTML");
		conectorModificado.setNombre("Conector 2");
		conectorModificado.setTipoServicio("Servicio 2");

		MockHttpServletRequestBuilder request = put("/conexiones/detalle")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(conectorModificado));

		mvc.perform(request)
		.andDo(print())
		.andExpect(status().isOk());



		Conector conectorEnBBDD = entityManager.find(Conector.class, conector.getId());


		assertThat(conectorEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(conectorModificado);
	}
	
	@Test
	void testTamañoNombreNoPermitido_update() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_update() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testLenguajeVacio_update() throws Exception {
		Conector conector = generarConector();
		conector.setLenguaje("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testTipoServicioVacio_update() throws Exception {
		Conector conector = generarConector();
		conector.setTipoServicio("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testRegistroNoExisteException_update() throws Exception {

		Conector conector = generarConector();
		conector.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testRegistroNoExisteException_update_idNull() throws Exception {

		Conector conector = generarConector();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	void testIdNoValidoException_update() throws Exception {

		Conector conector = generarConector();
		conector.setId(-1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conexiones/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testDeleteById() throws Exception {
		
		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conexiones/detalle/{id}", conector.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isOk());

		Conector conectorEnBBDD = entityManager.find(Conector.class, conector.getId());
		assertNull(conectorEnBBDD, "No borra el registro"); 
	}

	@Test
	void testIdNoValidoException_deleteById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conexiones/detalle/{id}", -1L)
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

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conexiones/detalle/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}
	
	private Conector generarConector() {
		Conector conector = new Conector();
		conector.setLenguaje("Java");
		conector.setNombre("Conector 1");
		conector.setTipoServicio("Servicio 1");
		
		return conector;

	}


}
