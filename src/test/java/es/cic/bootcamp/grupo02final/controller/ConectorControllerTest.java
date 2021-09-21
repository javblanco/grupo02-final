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

import es.cic.bootcamp.grupo02final.model.Conector;
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
		Conector conector = new Conector();
		when(dependencia.create(conector)).thenReturn(1L);
		
		Long id = cut.create(conector);
		
		assertEquals(id, 1L);
		verify(dependencia, times(1)).create(conector);
		
	}
	
	@Test
	void testFindById() {
		Conector conector = new Conector();
		when(dependencia.findById(1L)).thenReturn(conector);
		
		Conector conectorRecogido = cut.findById(1L);
		
		assertEquals(conectorRecogido, conector);
		verify(dependencia, times(1)).findById(1L);
		
	}
	
	@Test
	void testFindAll() {
		Conector conector1 = new Conector();
		Conector conector2 = new Conector();
		List<Conector> conectores = new ArrayList<>();
		conectores.add(conector1);
		conectores.add(conector2);
		
		when(dependencia.findAll()).thenReturn(conectores);
		
		List<Conector> conectoresRecogidos = cut.findAll();
		
		assertEquals(conectoresRecogidos, conectores);
		verify(dependencia, times(1)).findAll();
		
	}
	
	@Test
	void testUpdate() {
		Conector conector = new Conector();
		when(dependencia.update(conector)).thenReturn(conector);
		
		Conector conectorRecogido = cut.update(conector);
		
		assertEquals(conector, conectorRecogido);
		verify(dependencia, times(1)).update(conector);
		
	}
	

}
