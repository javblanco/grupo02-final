package es.cic.bootcamp.grupo02final.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.bootcamp.grupo02final.dto.InstanciaDTO;
import es.cic.bootcamp.grupo02final.service.InstanciaService;

class InstanciaControllerTest {

	private InstanciaController cut;
	
	private InstanciaService dependencia;

	@BeforeEach
	void setUp() throws Exception {
		cut = new InstanciaController();
		dependencia = mock(InstanciaService.class);
		
		cut.setInstanciaService(dependencia);
	}

	@Test
	void testCreate() {
		InstanciaDTO instanciaDTO = new InstanciaDTO();
		when(dependencia.create(instanciaDTO)).thenReturn(1L);
		
		Long id = cut.create(instanciaDTO);
		
		assertEquals(id, 1L);
		verify(dependencia, times(1)).create(instanciaDTO);
		
	}
	
	@Test
	void testFindById() {
		InstanciaDTO instanciaDTO = new InstanciaDTO();
		when(dependencia.findById(1L)).thenReturn(instanciaDTO);
		
		InstanciaDTO instanciaDTORecogido = cut.findById(1L);
		
		assertEquals(instanciaDTORecogido, instanciaDTO);
		verify(dependencia, times(1)).findById(1L);
		
	}
	
	@Test
	void testFindAll() {
		InstanciaDTO instanciaDTO1 = new InstanciaDTO();
		InstanciaDTO instanciaDTO2 = new InstanciaDTO();
		List<InstanciaDTO> instanciasDTO = new ArrayList<>();
		instanciasDTO.add(instanciaDTO1);
		instanciasDTO.add(instanciaDTO2);
		
		when(dependencia.findAll()).thenReturn(instanciasDTO);
		
		List<InstanciaDTO> instanciasDTORecogidos = cut.findAll();
		
		assertEquals(instanciasDTORecogidos, instanciasDTO);
		verify(dependencia, times(1)).findAll();
		
	}
	
	@Test
	void testUpdate() {
		InstanciaDTO instanciaDTO = new InstanciaDTO();
		when(dependencia.update(instanciaDTO)).thenReturn(instanciaDTO);
		
		InstanciaDTO instanciaDTORecogido = cut.update(instanciaDTO);
		
		assertEquals(instanciaDTO, instanciaDTORecogido);
		verify(dependencia, times(1)).update(instanciaDTO);
		
	}
	


}
