package es.cic.bootcamp.grupo02final.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Flujo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return "Flujo [id=" + id + ", nombre=" + nombre + ", instancia=" + instancia + ", conectores=" 
				+ ", tiempoInicio=" + tiempoInicio + ", tiempoFin=" + tiempoFin + ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flujo other = (Flujo) obj;
		return estado == other.estado && Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	

}
