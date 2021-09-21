package es.cic.bootcamp.grupo02final.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.bootcamp.grupo02final.model.Conector;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ConectorControllerIntegrationTest {
	
	private static final String ADMIN = "ADMIN";
	
	private static final String NOMBRE_USUARIO = "administrador";

	@PersistenceContext
	private EntityManager entityManager;	

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testCreate() throws Exception {
		Conector conector = generarConector();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.with(csrf())
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
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testTamañoNombreNoPermitido_create() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testNombreVacio_create() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testLenguajeVacio_create() throws Exception {
		Conector conector = generarConector();
		conector.setLenguaje("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testTipoServicioVacio_create() throws Exception {
		Conector conector = generarConector();
		conector.setTipoServicio("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testRegsitroNoExisteException_create() throws Exception {

		Conector conector = generarConector();
		conector.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.with(csrf())
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
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testFindById() throws Exception {

		Conector conector = generarConector();

		entityManager.persist(conector);
		entityManager.flush();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conector/{id}", conector.getId())
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
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testIdNoValidoException_findById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conector/{id}", -1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testRegistroNoExiste_findById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conector/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testFindAll() throws Exception {

		Conector conector1 = generarConector();
		entityManager.persist(conector1);
		entityManager.flush();
		Conector conector2 = generarConector();
		entityManager.persist(conector2);
		entityManager.flush();

		MockHttpServletRequestBuilder request =
				get("/conector")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(request)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$", hasSize(2)));

	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testUpdate() throws Exception {

		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();
		entityManager.detach(conector);

		Conector conectorModificado = generarConector();
		conectorModificado.setId(conector.getId());
		conectorModificado.setLenguaje("HTML");
		conectorModificado.setNombre("Conector 2");
		conectorModificado.setTipoServicio("Servicio 2");

		MockHttpServletRequestBuilder request = put("/conector")
				.with(csrf())
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
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testTamañoNombreNoPermitido_update() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testNombreVacio_update() throws Exception {
		Conector conector = generarConector();
		conector.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testLenguajeVacio_update() throws Exception {
		Conector conector = generarConector();
		conector.setLenguaje("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testTipoServicioVacio_update() throws Exception {
		Conector conector = generarConector();
		conector.setTipoServicio("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testRegistroNoExisteException_update() throws Exception {

		Conector conector = generarConector();
		conector.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testRegistroNoExisteException_update_idNull() throws Exception {

		Conector conector = generarConector();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testIdNoValidoException_update() throws Exception {

		Conector conector = generarConector();
		conector.setId(-1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conector));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testDeleteById() throws Exception {

		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();


		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", conector.getId())
				.with(csrf())
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
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testIdNoValidoException_deleteById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", -1L)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username = NOMBRE_USUARIO, roles = ADMIN)
	void testRegistroNoEncontrado_deleteById() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", 1L)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mvc
		.perform(mockRequest)
		.andDo(
				print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	void testAccesoNoAutorizado() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", 1L)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		this.mvc
			.perform(mockRequest)
			.andDo(
					print())
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = NOMBRE_USUARIO)
	void testPermisosInsuficientes() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", 1L)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		this.mvc
			.perform(mockRequest)
			.andDo(
					print())
			.andExpect(status().isForbidden());
	}
	
	
	private Conector generarConector() {
		Conector conector = new Conector();
		conector.setLenguaje("Java");
		conector.setNombre("Conector 1");
		conector.setTipoServicio("Servicio 1");
		
		return conector;

	}


}
