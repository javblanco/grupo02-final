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
import es.cic.bootcamp.grupo02final.dto.FlujoDTO;
import es.cic.bootcamp.grupo02final.service.FlujoService;

class FlujoControllerTest {

	private FlujoController cut;
	
	private FlujoService dependencia;

	@BeforeEach
	void setUp() throws Exception {
		cut = new FlujoController();
		dependencia = mock(FlujoService.class);
		
		cut.setFlujoService(dependencia);
	}

	@Test
	void testCreate() {
		FlujoDTO flujoDTO = new FlujoDTO();
		when(dependencia.create(flujoDTO)).thenReturn(1L);
		
		Long id = cut.create(flujoDTO);
		
		assertEquals(id, 1L);
		verify(dependencia, times(1)).create(flujoDTO);
		
	}
	
	@Test
	void testFindById() {
		FlujoDTO flujoDTO = new FlujoDTO();
		when(dependencia.findById(1L)).thenReturn(flujoDTO);
		
		FlujoDTO flujoDTORecogido = cut.findById(1L);
		
		assertEquals(flujoDTORecogido, flujoDTO);
		verify(dependencia, times(1)).findById(1L);
		
	}
	
	@Test
	void testFindAll() {
		FlujoDTO flujoDTO1 = new FlujoDTO();
		FlujoDTO flujoDTO2 = new FlujoDTO();
		List<FlujoDTO> flujosDTO = new ArrayList<>();
		flujosDTO.add(flujoDTO1);
		flujosDTO.add(flujoDTO2);
		
		when(dependencia.findAll()).thenReturn(flujosDTO);
		
		List<FlujoDTO> flujosDTORecogidos = cut.findAll();
		
		assertEquals(flujosDTORecogidos, flujosDTO);
		verify(dependencia, times(1)).findAll();
		
	}
	
	@Test
	void testUpdate() {
		FlujoDTO flujoDTO = new FlujoDTO();
		when(dependencia.update(flujoDTO)).thenReturn(flujoDTO);
		
		FlujoDTO flujoDTORecogido = cut.update(flujoDTO);
		
		assertEquals(flujoDTO, flujoDTORecogido);
		verify(dependencia, times(1)).update(flujoDTO);
		
	}
	

}
