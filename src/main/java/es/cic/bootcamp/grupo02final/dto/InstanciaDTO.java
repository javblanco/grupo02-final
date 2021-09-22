package es.cic.bootcamp.grupo02final.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import es.cic.bootcamp.grupo02final.model.Flujo;

public class InstanciaDTO {
	
	private Long id;

	@Length(max = 15)
	@NotBlank
	private String nombre;
	
	private List<Flujo> flujos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Flujo> getFlujos() {
		return flujos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFlujos(List<Flujo> flujos) {
		this.flujos = flujos;
	}
	
	


}
