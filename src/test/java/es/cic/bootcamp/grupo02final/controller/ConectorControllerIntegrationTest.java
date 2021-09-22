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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.bootcamp.grupo02final.dto.ConectorDTO;
import es.cic.bootcamp.grupo02final.helper.ConectorHelper;
import es.cic.bootcamp.grupo02final.model.Conector;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ConectorControllerIntegrationTest {
	
	@Autowired
	private ConectorHelper conectorHelper;

	@PersistenceContext
	private EntityManager entityManager;	

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		MvcResult result = this.mvc
				.perform(mockRequest)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(
						jsonPath("$", notNullValue()))
				.andReturn();

		String contenido = result.getResponse().getContentAsString();
		long idResultado = Long.parseLong(contenido);

		conectorDTO.setId(idResultado);

		ConectorDTO conectorDTOEnBBDD = conectorHelper.entity2DTO(entityManager.find(Conector.class, idResultado));

		assertThat(conectorDTOEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(conectorDTO);
	}
	
	@Test
	void testTamañoNombreNoPermitido_create() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_create() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testLenguajeVacio_create() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setLenguaje("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testTipoServicioVacio_create() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setTipoServicio("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}

	@Test
	void testRegsitroNoExisteException_create() throws Exception {

		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(conectorDTO));

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
			
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(conector);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/conector/{id}", conectorDTO.getId())
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
				jsonPath("$.id", is(conectorDTO.getId().intValue())))
		.andExpect(
				jsonPath("$.lenguaje", is(conectorDTO.getLenguaje())))
		.andExpect(
				jsonPath("$.tipoServicio", is(conectorDTO.getTipoServicio())))
		.andExpect(
				jsonPath("$.nombre", is(conectorDTO.getNombre())));
	}

	@Test
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
	void testUpdate() throws Exception {
		
		Conector conector = generarConector();
		entityManager.persist(conector);
		entityManager.flush();

		ConectorDTO conectorDTOModificado = conectorHelper.entity2DTO(generarConector());
		conectorDTOModificado.setId(conector.getId());
		conectorDTOModificado.setLenguaje("HTML");
		conectorDTOModificado.setNombre("Conector 2");
		conectorDTOModificado.setTipoServicio("Servicio 2");

		MockHttpServletRequestBuilder request = put("/conector")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(conectorDTOModificado));

		mvc.perform(request)
		.andDo(print())
		.andExpect(status().isOk());



		ConectorDTO conectorDTOEnBBDD = conectorHelper.entity2DTO(entityManager.find(Conector.class, conector.getId()));


		assertThat(conectorDTOEnBBDD)
		.usingRecursiveComparison()
		.isEqualTo(conectorDTOModificado);
	}
	
	@Test
	void testTamañoNombreNoPermitido_update() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testNombreVacio_update() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setNombre("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testLenguajeVacio_update() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setLenguaje("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testTipoServicioVacio_update() throws Exception {
		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setTipoServicio("");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		mvc.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isBadRequest());

		
	}
	
	@Test
	void testRegistroNoExisteException_update() throws Exception {

		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setId(1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}

	@Test
	void testRegistroNoExisteException_update_idNull() throws Exception {

		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

		this.mvc
		.perform(mockRequest)
		.andDo(print())
		.andExpect(status().isBadRequest());

	}
	
	@Test
	void testIdNoValidoException_update() throws Exception {

		ConectorDTO conectorDTO = conectorHelper.entity2DTO(generarConector());
		conectorDTO.setId(-1L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/conector")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(conectorDTO));

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

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", conector.getId())
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

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", -1L)
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

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/conector/{id}", 1L)
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
