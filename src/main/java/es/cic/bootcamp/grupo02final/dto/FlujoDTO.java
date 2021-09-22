package es.cic.bootcamp.grupo02final.dto;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import es.cic.bootcamp.grupo02final.model.Instancia;

public class FlujoDTO {
	
	private Long id;

	@Length(max = 15)
	@NotBlank
	private String nombre;
	
	@ManyToOne
	private Instancia instancia;
	
	private LocalDate tiempoInicio;
	
	private LocalDate tiempoFin;	

	private boolean estado;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Instancia getInstancia() {
		return instancia;
	}

	public LocalDate getTiempoInicio() {
		return tiempoInicio;
	}

	public LocalDate getTiempoFin() {
		return tiempoFin;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setInstancia(Instancia instancia) {
		this.instancia = instancia;
	}

	public void setTiempoInicio(LocalDate tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public void setTiempoFin(LocalDate tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

}
