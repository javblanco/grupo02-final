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
import es.cic.bootcamp.grupo02final.model.Flujo;
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
		Flujo flujo = new Flujo();
		when(dependencia.create(flujo)).thenReturn(1L);
		
		Long id = cut.create(flujo);
		
		assertEquals(id, 1L);
		verify(dependencia, times(1)).create(flujo);
		
	}
	
	@Test
	void testFindById() {
		Flujo flujo = new Flujo();
		when(dependencia.findById(1L)).thenReturn(flujo);
		
		Flujo flujoRecogido = cut.findById(1L);
		
		assertEquals(flujoRecogido, flujo);
		verify(dependencia, times(1)).findById(1L);
		
	}
	
	@Test
	void testFindAll() {
		Flujo flujo1 = new Flujo();
		Flujo flujo2 = new Flujo();
		List<Flujo> flujos = new ArrayList<>();
		flujos.add(flujo1);
		flujos.add(flujo2);
		
		when(dependencia.findAll()).thenReturn(flujos);
		
		List<Flujo> flujosRecogidos = cut.findAll();
		
		assertEquals(flujosRecogidos, flujos);
		verify(dependencia, times(1)).findAll();
		
	}
	
	@Test
	void testUpdate() {
		Flujo flujo = new Flujo();
		when(dependencia.update(flujo)).thenReturn(flujo);
		
		Flujo flujoRecogido = cut.update(flujo);
		
		assertEquals(flujo, flujoRecogido);
		verify(dependencia, times(1)).update(flujo);
		
	}
	

}
