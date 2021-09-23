package es.cic.bootcamp.grupo02final.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.bootcamp.grupo02final.model.Flujo;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FlujoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCreate() throws Exception {
		Flujo flujo = generarFlujo();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		MvcResult result = this.mvc
				.perform(mockRequest)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andReturn();

		String contenido = result.getResponse().getContentAsString();
		long idResultado = Long.parseLong(contenido);

		flujo.setId(idResultado);

		Flujo flujoEnBBDD = entityManager.find(Flujo.class, idResultado);

		assertThat(flujoEnBBDD)
			.usingRecursiveComparison()
			.isEqualTo(flujo);

	}
	
	@Test
	void testTamañoNombreNoPermitido_create() throws Exception {
		Flujo flujo = generarFlujo();
		flujo.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_create() throws Exception {
		Flujo flujo = generarFlujo();
		flujo.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testRegsitroNoExisteException_create() throws Exception {

		Flujo flujo = generarFlujo();
		flujo.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(flujo));

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testFindById() throws Exception {
		Flujo flujo = generarFlujo();
		entityManager.persist(flujo);
		entityManager.flush();
		
		MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders.get("/flujo/detalle/{id}", flujo.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
		
		this.mvc
			.perform(mockRequest)
			.andDo
				(print())
			.andExpect(status().isOk())
			.andExpect(
					jsonPath("$", notNullValue()))
			.andExpect(
					jsonPath("$.id", is(flujo.getId().intValue())))
			.andExpect(
					jsonPath("$.nombre", is(flujo.getNombre())));
	}
	
	@Test
	void testIdNoValidoException_findById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/flujo/detalle/{id}", -1L)
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

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/flujo/detalle/{id}", 1L)
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
		Flujo flujo = generarFlujo();
		Flujo flujo2 = generarFlujo();
		entityManager.persist(flujo);
		entityManager.flush();
		entityManager.persist(flujo2);
		entityManager.flush();
		
		MockHttpServletRequestBuilder request =
				get("/flujo/lista")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(request)
			.andDo
				(print())
			.andExpect(
					status().isOk())
			.andExpect(
					jsonPath("$", notNullValue()))
			.andExpect(
					jsonPath("$", hasSize(2)));

	}

	@Test
	void testUpdate() throws Exception {
		Flujo flujo = generarFlujo();
		entityManager.persist(flujo);
		entityManager.flush();
		entityManager.detach(flujo);
		
		Flujo flujoModificado = new Flujo();
		flujoModificado.setId(flujo.getId());
		flujoModificado.setNombre("Modificado");
		flujoModificado.setEstado(false);
		
		MockHttpServletRequestBuilder requestBuilder = put("/flujo/detalle")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(flujoModificado));
		
		mvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk());
		
		Flujo flujoEnBBDD = entityManager.find(Flujo.class, flujo.getId());
		
		assertThat(flujoEnBBDD)
			.usingRecursiveComparison()
			.isEqualTo(flujoModificado);
	}
	
	@Test
	void testTamañoNombreNoPermitido_update() throws Exception {
		Flujo flujo = generarFlujo();
		flujo.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_update() throws Exception {
		Flujo flujo = generarFlujo();
		flujo.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testRegistroNoExisteException_update() throws Exception {

		Flujo flujo = generarFlujo();
		flujo.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testRegistroNoExisteException_update_idNull() throws Exception {

		Flujo flujo = generarFlujo();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	void testIdNoValidoException_update() throws Exception {

		Flujo flujo = generarFlujo();
		flujo.setId(-1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/flujo/detalle")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(flujo));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	private Flujo generarFlujo() {
		Flujo flujo = new Flujo();
		flujo.setNombre("Trial");
		flujo.setTiempoInicio(LocalDate.now());
		flujo.setTiempoFin(LocalDate.of(2021, 12, 25));
		flujo.setEstado(true);
		return flujo;
	}
}
