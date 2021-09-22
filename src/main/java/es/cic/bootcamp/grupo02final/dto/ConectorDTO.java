package es.cic.bootcamp.grupo02final.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ConectorDTO {
	
	private Long id;
	
	@Length(max = 15)
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String lenguaje;
	
	@NotBlank
	private String tipoServicio;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

}
