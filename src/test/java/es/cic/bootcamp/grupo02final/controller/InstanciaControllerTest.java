package es.cic.bootcamp.grupo02final.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cic.bootcamp.grupo02final.model.Instancia;
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
		Instancia instancia = new Instancia();
		when(dependencia.create(instancia)).thenReturn(1L);
		
		Long id = cut.create(instancia);
		
		assertEquals(id, 1L);
		verify(dependencia, times(1)).create(instancia);
		
	}
	
	@Test
	void testFindById() {
		Instancia instancia = new Instancia();
		when(dependencia.findById(1L)).thenReturn(instancia);
		
		Instancia instanciaRecogido = cut.findById(1L);
		
		assertEquals(instanciaRecogido, instancia);
		verify(dependencia, times(1)).findById(1L);
		
	}
	
	
	
	@Test
	void testUpdate() {
		Instancia instancia = new Instancia();
		when(dependencia.update(instancia)).thenReturn(instancia);
		
		Instancia instanciaRecogido = cut.update(instancia);
		
		assertEquals(instancia, instanciaRecogido);
		verify(dependencia, times(1)).update(instancia);
		
	}
	


}
