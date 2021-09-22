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
import es.cic.bootcamp.grupo02final.dto.ConectorDTO;
import es.cic.bootcamp.grupo02final.service.ConectorService;

class ConectorControllerTest {
	
	private ConectorController cut;
	
	private ConectorService dependencia;

	@BeforeEach
	void setUp() throws Exception {
		cut = new ConectorController();
		dependencia = mock(ConectorService.class);
		
		cut.setConectorService(dependencia);
	}

	@Test
	void testCreate() {
		ConectorDTO conectorDTO = new ConectorDTO();
		when(dependencia.create(conectorDTO)).thenReturn(1L);
		
		Long id = cut.create(conectorDTO);
		
		assertEquals(id, 1L);
		verify(dependencia, times(1)).create(conectorDTO);
		
	}
	
	@Test
	void testFindById() {
		ConectorDTO conectorDTO = new ConectorDTO();
		when(dependencia.findById(1L)).thenReturn(conectorDTO);
		
		ConectorDTO conectorDTORecogido = cut.findById(1L);
		
		assertEquals(conectorDTORecogido, conectorDTO);
		verify(dependencia, times(1)).findById(1L);
		
	}
	
	@Test
	void testFindAll() {
		ConectorDTO conectorDTO1 = new ConectorDTO();
		ConectorDTO conectorDTO2 = new ConectorDTO();
		List<ConectorDTO> conectoresDTO = new ArrayList<>();
		conectoresDTO.add(conectorDTO1);
		conectoresDTO.add(conectorDTO2);
		
		when(dependencia.findAll()).thenReturn(conectoresDTO);
		
		List<ConectorDTO> conectoresDTORecogidos = cut.findAll();
		
		assertEquals(conectoresDTORecogidos, conectoresDTO);
		verify(dependencia, times(1)).findAll();
		
	}
	
	@Test
	void testUpdate() {
		ConectorDTO conectorDTO = new ConectorDTO();
		when(dependencia.update(conectorDTO)).thenReturn(conectorDTO);
		
		ConectorDTO conectorDTORecogido = cut.update(conectorDTO);
		
		assertEquals(conectorDTO, conectorDTORecogido);
		verify(dependencia, times(1)).update(conectorDTO);
		
	}
	

}
